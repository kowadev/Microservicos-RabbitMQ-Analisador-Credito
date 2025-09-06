package com.pieropan.propostaapp.config;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {
    private final String exchangePendingProposal;
    private final String exchangeCompletedProposal;

    public RabbitMQConfiguration(@Value("${rabbitmq.pendingproposal.exchange}") String exchangePendingProposal,
                                 @Value("${rabbitmq.proposalcompleted.exchange}") String exchangeCompletedProposal){
        this.exchangePendingProposal = exchangePendingProposal;
        this.exchangeCompletedProposal = exchangeCompletedProposal;
    }


    @Bean
    public Queue pendingProposalAnalysisQueue(){
        return QueueBuilder.durable("proposta-pendente.ms-analise-credito").build();
    }



    @Bean
    public Queue completedProposalAnalysisQueue(){
        return QueueBuilder.durable("proposta-concluida.ms-analise-credito").build();
    }


    @Bean
    public RabbitAdmin createRabbitAdmin(ConnectionFactory connectionFactory){
        return  new RabbitAdmin(connectionFactory);
    }

    @Bean
    public ApplicationListener<ApplicationReadyEvent> initializeAdmin(RabbitAdmin rabbitAdmin){
        return event ->  rabbitAdmin.initialize();
    }

    @Bean
    public FanoutExchange createFanoutExchangePendingProposal(){
        return ExchangeBuilder.fanoutExchange(exchangePendingProposal).build();
    }
    @Bean
    public FanoutExchange createFanoutExchangeCompletedProposal(){
        return ExchangeBuilder.fanoutExchange(exchangeCompletedProposal).build();
    }

    @Bean
    public Binding createBindingPendingProposalAnalysisCreditQueue(){
        return BindingBuilder.bind(pendingProposalAnalysisQueue())
                .to(createFanoutExchangePendingProposal());
    }
    @Bean
    public Binding createBindingProposalCompletedAnalysisCreditQueue(){
        return BindingBuilder.bind(completedProposalAnalysisQueue())
                .to(createFanoutExchangeCompletedProposal());
    }

    @Bean
    public MessageConverter jackson2JsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter());

        return  rabbitTemplate;
    }
}

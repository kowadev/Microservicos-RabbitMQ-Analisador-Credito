package com.pieropan.propostaapp.scheduler;

import com.pieropan.propostaapp.repository.ProposalRepository;
import com.pieropan.propostaapp.service.NotificationRabbitService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

@Component
public class NonIntegratedProposal {
    private final ProposalRepository proposalRepository;

    private final NotificationRabbitService notificationRabbitService;

    private final String exchange;

    public NonIntegratedProposal(ProposalRepository proposalRepository,
                                 NotificationRabbitService notificationRabbitService,
                                 @Value("${rabbitmq.pendingproposal.exchange}") String exchange){
        this.proposalRepository = proposalRepository;
        this.notificationRabbitService = notificationRabbitService;
        this.exchange = exchange;
    }

    @Scheduled(fixedDelay = 10, timeUnit = TimeUnit.SECONDS)
    @Transactional
    public void searchNonIntegratedProposal(){
        proposalRepository.findAllByIntegratedIsFalse().forEach(proposal ->{
            try{
                notificationRabbitService.notify(proposal, exchange);
                proposalRepository.updateIntegratedStatus(proposal.getId(), true);
            } catch (RuntimeException ex) {
                throw new RuntimeException(ex.getMessage());
            }
        });
    }
}

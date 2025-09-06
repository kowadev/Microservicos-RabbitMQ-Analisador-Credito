package com.pieropan.propostaapp.listener;

import com.pieropan.propostaapp.entity.Proposal;
import com.pieropan.propostaapp.mapper.ProposalMapper;
import com.pieropan.propostaapp.repository.ProposalRepository;
import com.pieropan.propostaapp.service.WebSocketService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProposalCompletedListener {
    @Autowired
    private ProposalRepository proposalRepository;

    @Autowired
    private WebSocketService webSocketService;


    @RabbitListener(queues = "${rabbitmq.queue.proposalcompleted}")
    public void proposalCompleted(Proposal proposal){
        updateProposal(proposal);
        webSocketService.toNotify(ProposalMapper.INSTANCE.convertEntityToDto(proposal));
    }

    public void updateProposal(Proposal proposal){
        proposalRepository.updateProposal(proposal.getId(), proposal.getApproved(), proposal.getObservation());
    }
}

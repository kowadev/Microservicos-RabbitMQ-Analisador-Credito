package com.pieropan.analisecredito.listener;

import com.pieropan.analisecredito.domain.Proposal;
import com.pieropan.analisecredito.service.AnalysisCreditService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProposalUnderAnalysisListener {

    @Autowired
    private AnalysisCreditService analysisCreditService;

    @RabbitListener(queues = "${rabbitmq.queue.pendingproposal}")
    public void proposalUnderAnalysis (Proposal proposal){
        analysisCreditService.toAnalyze(proposal);
    }
}

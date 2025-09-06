package com.pieropan.propostaapp.service;

import com.pieropan.propostaapp.dto.ProposalRequestDto;
import com.pieropan.propostaapp.dto.ProposalResponseDto;
import com.pieropan.propostaapp.entity.Proposal;
import com.pieropan.propostaapp.mapper.ProposalMapper;
import com.pieropan.propostaapp.repository.ProposalRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProposalService {

    private final ProposalRepository proposalRepository;
    private final NotificationRabbitService notificationRabbitService;

    private final String exchange;

    public ProposalService(ProposalRepository proposalRepository,
                           NotificationRabbitService notificationRabbitService,
                           @Value("${rabbitmq.pendingproposal.exchange}") String exchange) {
        this.proposalRepository = proposalRepository;
        this.notificationRabbitService = notificationRabbitService;
        this.exchange = exchange;
    }


    @Transactional
    public ProposalResponseDto createProposal(ProposalRequestDto dto){
        Proposal entity = proposalRepository.save(ProposalMapper.INSTANCE.convertDtoToProprosal(dto));
        notifyProposal(entity);

        return ProposalMapper.INSTANCE.convertEntityToDto(entity);
    }


    @Transactional(readOnly = true)
    public List<ProposalResponseDto> findAll(){
        List<Proposal> proposalList  = proposalRepository.findAll();
        return proposalList.stream().map(ProposalMapper.INSTANCE::convertEntityToDto).toList();
    }


    public void notifyProposal(Proposal entity){
        try{
            notificationRabbitService.notify(entity, exchange);
        }catch(RuntimeException ex){
            entity.setIntegrated(false);
            proposalRepository.save(entity);
        }

    }
}

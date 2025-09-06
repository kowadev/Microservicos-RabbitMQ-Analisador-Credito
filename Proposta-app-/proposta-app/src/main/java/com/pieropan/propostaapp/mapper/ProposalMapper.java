package com.pieropan.propostaapp.mapper;

import com.pieropan.propostaapp.dto.ProposalRequestDto;
import com.pieropan.propostaapp.dto.ProposalResponseDto;
import com.pieropan.propostaapp.entity.Proposal;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.text.NumberFormat;

@Mapper
public interface ProposalMapper {

    ProposalMapper INSTANCE = Mappers.getMapper(ProposalMapper.class);

    @Mapping(target = "user.name", source = "name")
    @Mapping(target = "user.lastName", source = "lastName")
    @Mapping(target = "user.cpf", source = "cpf")
    @Mapping(target = "user.phone", source = "phone")
    @Mapping(target = "user.income", source = "income")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "approved", ignore = true)
    @Mapping(target = "observation", ignore = true)
    @Mapping(target = "integrated", constant = "true")
    Proposal convertDtoToProprosal(ProposalRequestDto proposalRequestDto);


    @Mapping(target = "name", source = "user.name")
    @Mapping(target = "lastName", source = "user.lastName")
    @Mapping(target = "phone", source = "user.phone")
    @Mapping(target = "cpf", source = "user.cpf")
    @Mapping(target = "income", source = "user.income")
    @Mapping(target = "requestedAmount",  expression = "java(setRequestedAmount(entity))")
    ProposalResponseDto convertEntityToDto(Proposal entity);

    default String setRequestedAmount(Proposal proposal){
        return NumberFormat.getCurrencyInstance().format(proposal.getRequestedAmount());
    }
}

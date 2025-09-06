package com.pieropan.propostaapp.dto;

import com.pieropan.propostaapp.entity.Proposal;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@NoArgsConstructor
public class ProposalResponseDto {
    private Long id;

    private String name;
    private String lastName;
    private String phone;
    private String cpf;
    private Double income;
    private  String requestedAmount;
    private Integer paymentTerm;
    private Boolean approved;
    private String observation;


    public ProposalResponseDto(Proposal entity){
        BeanUtils.copyProperties(entity, this);
    }
}

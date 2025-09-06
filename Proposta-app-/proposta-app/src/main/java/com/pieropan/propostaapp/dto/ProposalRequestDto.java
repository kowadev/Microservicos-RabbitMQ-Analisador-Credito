package com.pieropan.propostaapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
@AllArgsConstructor
public class ProposalRequestDto {

    private String name;

    private String lastName;
    private String cpf;

    private String phone;


    private Double income;
    private  Double requestedAmount;
    private Integer paymentTerm;
}

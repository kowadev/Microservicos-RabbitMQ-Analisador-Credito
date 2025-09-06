package com.pieropan.analisecredito.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

    private Long id;

    private String name;

    private String  lastName;

    private String cpf;

    private String phone;

    private Double income;


}

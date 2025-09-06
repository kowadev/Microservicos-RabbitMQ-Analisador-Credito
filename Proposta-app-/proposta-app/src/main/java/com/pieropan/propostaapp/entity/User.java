package com.pieropan.propostaapp.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@Entity
@Table(name = "tb_users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String  lastName;

    private String cpf;

    private String phone;

    private Double income;

    @OneToMany(mappedBy = "user")
    private List<Proposal> proposals;

    public User(Long id, Double income, String phone, String cpf, String lastName, String name) {
        this.id = id;
        this.income = income;
        this.phone = phone;
        this.cpf = cpf;
        this.lastName = lastName;
        this.name = name;
    }
}

package com.pieropan.propostaapp.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tb_proposals")
public class Proposal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double requestedAmount;

    private Integer paymentTerm;

    private Boolean approved;

    private Boolean integrated;

    private String observation;

    @ManyToOne(cascade =  CascadeType.PERSIST )
    @JoinColumn(name = "patient_id")
    private User user;

    public Proposal(Long id, User user, String observation, Boolean integrated, Boolean approved, Integer paymentTerm, Double requestedAmount) {
        this.id = id;
        this.user = user;
        this.observation = observation;
        this.integrated = integrated;
        this.approved = approved;
        this.paymentTerm = paymentTerm;
        this.requestedAmount = requestedAmount;
    }
}

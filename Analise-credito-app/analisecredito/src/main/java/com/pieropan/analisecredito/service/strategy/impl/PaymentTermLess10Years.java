package com.pieropan.analisecredito.service.strategy.impl;

import com.pieropan.analisecredito.domain.Proposal;
import com.pieropan.analisecredito.service.strategy.ScoreCalculation;
import org.springframework.stereotype.Component;

@Component
public class PaymentTermLess10Years implements ScoreCalculation {
    @Override
    public int calculate(Proposal proposal) {
        return proposal.getPaymentTerm() <= 120 ? 80 : 0;
    }
}

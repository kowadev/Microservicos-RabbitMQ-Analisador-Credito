package com.pieropan.analisecredito.service.strategy.impl;

import com.pieropan.analisecredito.domain.Proposal;
import com.pieropan.analisecredito.service.strategy.ScoreCalculation;
import org.springframework.stereotype.Component;

@Component
public class IncomeGreaterThanRequestedAmount implements ScoreCalculation {
    @Override
    public int calculate(Proposal proposal) {
        return incomeGreaterThanRequestedAmount(proposal) ? 100 :0 ;
    }

    private boolean incomeGreaterThanRequestedAmount(Proposal proposal){
        return proposal.getUser().getIncome() > proposal.getRequestedAmount();
    }
}

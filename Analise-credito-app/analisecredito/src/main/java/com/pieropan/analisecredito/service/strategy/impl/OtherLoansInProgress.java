package com.pieropan.analisecredito.service.strategy.impl;

import com.pieropan.analisecredito.domain.Proposal;
import com.pieropan.analisecredito.service.strategy.ScoreCalculation;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class OtherLoansInProgress implements ScoreCalculation {
    @Override
    public int calculate(Proposal proposal) {
        return otherLoansInProgress() ? 0 : 80 ;

    }
    private boolean otherLoansInProgress(){
        return new Random().nextBoolean();
    }
}

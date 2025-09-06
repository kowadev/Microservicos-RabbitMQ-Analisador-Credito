package com.pieropan.analisecredito.service.strategy.impl;

import com.pieropan.analisecredito.domain.Proposal;
import com.pieropan.analisecredito.exceptions.StrategyException;
import com.pieropan.analisecredito.service.strategy.ScoreCalculation;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Random;

@Order(1)
@Component
public class NegativeNameImpl implements ScoreCalculation {
    @Override
    public int calculate(Proposal proposal) {
        if(negativeName()){
            throw new StrategyException("Nome negativado");
        }
        return 100;
    }
    private boolean negativeName(){
        return new Random().nextBoolean();
    }
}

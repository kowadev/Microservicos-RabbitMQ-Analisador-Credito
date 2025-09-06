package com.pieropan.analisecredito.service.strategy;

import com.pieropan.analisecredito.domain.Proposal;

public interface ScoreCalculation {
    int calculate(Proposal proposal);
}

package com.example.restservice.service;

import org.springframework.beans.factory.annotation.Autowired;

public class SelectionStrategyFactory {

    @Autowired
    public static PricingStrategy pricingStrategy;

    public static SelectionStrategy get(StrategyTypeEnum strategyType){
        if(strategyType == StrategyTypeEnum.PRICE){
            return pricingStrategy;
        }

        return pricingStrategy; // default strategy
    }
}

package com.salesforce.framework.enums;

import java.util.List;

public enum MainCompetitors {
    PEPSI_COLA("Pepsi Cola"),
    COKE_COLA("Coke Cola"),
    SPRITE("Sprite"),
    FANTA("Fanta");

    private final String mainCompetitor;

    MainCompetitors(String mainCompetitor){
        this.mainCompetitor = mainCompetitor;
    }

    public String getMainCompetitor(){
        return this.mainCompetitor;
    }
}

package com.salesforce.framework.enums;

public enum SalesTabLabels {
    HOME("Home"),
    OPPORTUNITIES("Opportunities"),
    LEADS("Leads"),
    TASKS("Tasks"),
    FILES("Files"),
    ACCOUNTS("Accounts");

    private final String tabLabel;

    SalesTabLabels(String tabLabel){
        this.tabLabel = tabLabel;
    }

    public String getTabLabel() {
        return tabLabel;
    }
}

package com.dataart.edu.ms.domain;

public enum EventType {
    BUSINESS_AREA_CREATED("businessAreaCreated"),
    BUSINESS_AREA_CREATION_ROLLBACK("businessAreaCreationRollback"),
    CLIENT_CREATED("clientCreated"),
    CLIENT_CREATION_ROLLBACK("clientCreationRollback"),
    PRODUCT_CREATED("productCreated"),
    PRODUCT_CREATION_ROLLBACK("productCreationRollback"),
    RESEARCH_PROJECT_CREATED("researchProjectCreated"),
    RESEARCH_PROJECT_CREATION_ROLLBACK("researchProjectCreationRollback"),
    RESEARCH_PROJECT_UPDATED("researchProjectUpdated"),
    RESEARCH_PROJECT_UPDATE_ROLLBACK("researchProjectUpdateRollback"),
    SERVER_ADDED("serverAdded"),
    SERVER_ADD_ROLLBACK("serverAddRollback"),
    TEAM_ADDED("teamAdded"),
    TEAM_ADD_ROLLBACK("teamAddRollback"),
    PRODUCT_ESTABLISH_STARTED("productEstablishStarted"),
    RESEARCH_PROJECT_STARTED("researchProjectStarted"),
    RESEARCH_PROJECT_REVERT_START("researchProjectRevertStart"),
    TEAM_FOUND("teamFound"),
    TEAM_ASSIGNED_TO_RESEARCH_PROJECT("teamAssignedToResearchProject"),
    TEAM_ASSIGN_TO_RESEARCH_PROJECT_ROLLBACK("teamAssignToResearchProjectRollback"),
    TEAM_ASSIGNED_TO_PRODUCT("teamAssignedToProduct"),
    TEAM_ASSIGN_TO_PRODUCT_ROLLBACK("teamAssignToProductRollback");

    public String type;

    EventType(String type) {
        this.type = type;
    }

}

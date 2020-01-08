package com.dataart.edu.ms.research.domain;

import com.dataart.edu.ms.domain.AppEntity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "research_project")
public class ResearchProject implements AppEntity {
    @Id
    private String id = "";
    private String name = "";
    private String description = "";
    private String teamId;
    private String serverId;
    private String productId;
    @Enumerated(EnumType.STRING)
    private Status status = Status.INITIALIZED;
    @Enumerated(EnumType.STRING)
    private Phase phase = Phase.RESEARCH;
    @Version
    private Long version;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getServerId() {
        return serverId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Phase getPhase() {
        return phase;
    }

    public void setPhase(Phase phase) {
        this.phase = phase;
    }

    @Override
    public Long getVersion() {
        return version;
    }

    @Override
    public void setVersion(Long version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResearchProject that = (ResearchProject) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(teamId, that.teamId) &&
                Objects.equals(serverId, that.serverId) &&
                Objects.equals(productId, that.productId) &&
                Objects.equals(status, that.status) &&
                Objects.equals(phase, that.phase) &&
                Objects.equals(version, that.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, teamId, serverId, productId, status, phase, version);
    }

    @Override
    public String toString() {
        return "ResearchProject{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", teamId='" + teamId + '\'' +
                ", serverId='" + serverId + '\'' +
                ", productId='" + productId + '\'' +
                ", status=" + status +
                ", phase=" + phase +
                ", version=" + version +
                '}';
    }

}

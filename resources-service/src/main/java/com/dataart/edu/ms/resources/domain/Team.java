package com.dataart.edu.ms.resources.domain;

import com.dataart.edu.ms.domain.AppEntity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "team")
public class Team implements AppEntity {
    @Id
    private String id = "";
    private String name = "";
    private Integer engineersCount = 0;
    private Integer qasCount = 0;
    private Boolean busy = false;
    @Version
    private Long version;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getEngineersCount() {
        return engineersCount;
    }

    public void setEngineersCount(Integer engineersCount) {
        this.engineersCount = engineersCount;
    }

    public Integer getQasCount() {
        return qasCount;
    }

    public void setQasCount(Integer qasCount) {
        this.qasCount = qasCount;
    }

    public Boolean getBusy() {
        return busy;
    }

    public void setBusy(Boolean busy) {
        this.busy = busy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return Objects.equals(id, team.id) &&
                Objects.equals(name, team.name) &&
                Objects.equals(engineersCount, team.engineersCount) &&
                Objects.equals(qasCount, team.qasCount) &&
                Objects.equals(busy, team.busy) &&
                Objects.equals(version, team.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, engineersCount, qasCount, busy, version);
    }

    @Override
    public String toString() {
        return "Team{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", engineersCount=" + engineersCount +
                ", qasCount=" + qasCount +
                ", busy=" + busy +
                ", version=" + version +
                '}';
    }

}

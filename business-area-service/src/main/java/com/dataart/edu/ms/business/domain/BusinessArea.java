package com.dataart.edu.ms.business.domain;

import com.dataart.edu.ms.domain.AppEntity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import java.util.Objects;

@Entity
@Table(name = "business")
public class BusinessArea implements AppEntity {
    @Id
    private String id = "";
    private String name = "";
    private String description = "";
    @Version
    private Long version;

    public BusinessArea(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public BusinessArea() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public Long getVersion() {
        return version;
    }

    @Override
    public void setVersion(Long version) {
        this.version = version;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BusinessArea businessArea = (BusinessArea) o;
        return Objects.equals(id, businessArea.id) &&
                Objects.equals(name, businessArea.name) &&
                Objects.equals(description, businessArea.description) &&
                Objects.equals(version, businessArea.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, version);
    }

    @Override
    public String toString() {
        return "BusinessArea{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", version=" + version +
                '}';
    }

}

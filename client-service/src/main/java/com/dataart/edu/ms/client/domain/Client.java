package com.dataart.edu.ms.client.domain;

import com.dataart.edu.ms.domain.AppEntity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "client")
public class Client implements AppEntity {
    @Id
    private String id = "";
    private String email = "";
    private String name = "";
    private String businessAreaId = "";
    @Version
    private Long version;

    public Client() {
    }

    public Client(String id, String name, String businessAreaId, String email) {
        this.id = id;
        this.name = name;
        this.businessAreaId = businessAreaId;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBusinessAreaId() {
        return businessAreaId;
    }

    public void setBusinessAreaId(String businessAreaId) {
        this.businessAreaId = businessAreaId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        Client client = (Client) o;
        return Objects.equals(id, client.id) &&
                Objects.equals(name, client.name) &&
                Objects.equals(businessAreaId, client.businessAreaId) &&
                Objects.equals(email, client.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, businessAreaId, email);
    }

}

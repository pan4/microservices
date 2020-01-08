package com.dataart.edu.ms.product.domain;

import com.dataart.edu.ms.domain.AppEntity;
import com.dataart.edu.ms.utils.IntArrayToStringConverter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "product")
public class Product implements AppEntity {
    @Id
    private String id = "";
    private String name = "";
    private String businessAreaId = "";
    private String clientId = "";
    @Convert(converter = IntArrayToStringConverter.class)
    private List<String> prototypesIds = new ArrayList<>();
    @Version
    private Long version;

    public Product() {
    }

    public Product(String id, String name, String businessAreaId, String clientId) {
        this.id = id;
        this.name = name;
        this.businessAreaId = businessAreaId;
        this.clientId = clientId;
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

    public String getBusinessAreaId() {
        return businessAreaId;
    }

    public void setBusinessAreaId(String businessAreaId) {
        this.businessAreaId = businessAreaId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public List<String> getPrototypesIds() {
        return prototypesIds;
    }

    public void setPrototypesIds(List<String> prototypesIds) {
        this.prototypesIds = prototypesIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) &&
                Objects.equals(name, product.name) &&
                Objects.equals(businessAreaId, product.businessAreaId) &&
                Objects.equals(clientId, product.clientId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, businessAreaId, clientId);
    }
}

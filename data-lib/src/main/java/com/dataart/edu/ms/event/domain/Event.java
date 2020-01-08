package com.dataart.edu.ms.event.domain;

import com.dataart.edu.ms.domain.AppEntity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "event", indexes = {
        @Index(columnList = "payloadId", name = "payloadId_idx")
})
public class Event implements AppEntity {
    @Id
    private String id;
    private String name;
    private String type;
    private String payload;
    private String payloadId;
    private String payloadType;
    private Long payloadVersion;
    private String transactionType;
    private String transactionId;
    @Version
    private Long version;

    public Event() {
    }

    public Event(String id, String name, String type, String payload, String payloadType, Long payloadVersion,
                 String transactionType, String transactionId) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.payload = payload;
        this.payloadType = payloadType;
        this.payloadVersion = payloadVersion;
        this.transactionType = transactionType;
        this.transactionId = transactionId;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public String getPayloadId() {
        return payloadId;
    }

    public void setPayloadId(String payloadId) {
        this.payloadId = payloadId;
    }

    public String getPayloadType() {
        return payloadType;
    }

    public void setPayloadType(String payloadType) {
        this.payloadType = payloadType;
    }

    public Long getPayloadVersion() {
        return payloadVersion;
    }

    public void setPayloadVersion(Long payloadVersion) {
        this.payloadVersion = payloadVersion;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(id, event.id) &&
                Objects.equals(name, event.name) &&
                Objects.equals(type, event.type) &&
                Objects.equals(payload, event.payload) &&
                Objects.equals(payloadId, event.payloadId) &&
                Objects.equals(payloadType, event.payloadType) &&
                Objects.equals(payloadVersion, event.payloadVersion) &&
                Objects.equals(transactionType, event.transactionType) &&
                Objects.equals(transactionId, event.transactionId) &&
                Objects.equals(version, event.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, payload, payloadId, payloadType, payloadVersion, transactionType, transactionId, version);
    }

    @Override
    public String toString() {
        return "Event{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", payload='" + payload + '\'' +
                ", payloadId='" + payloadId + '\'' +
                ", payloadType='" + payloadType + '\'' +
                ", payloadVersion=" + payloadVersion +
                ", transactionType='" + transactionType + '\'' +
                ", transactionId='" + transactionId + '\'' +
                ", version=" + version +
                '}';
    }

}

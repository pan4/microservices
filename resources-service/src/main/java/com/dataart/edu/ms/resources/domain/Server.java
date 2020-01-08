package com.dataart.edu.ms.resources.domain;

import com.dataart.edu.ms.domain.AppEntity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "server")
public class Server implements AppEntity {
    @Id
    private String id = "";
    private String name = "";
    private String host = "";
    private Integer port = 0;
    private Boolean busy = false;
    @Version
    private Long version;

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

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Boolean getBusy() {
        return busy;
    }

    public void setBusy(Boolean busy) {
        this.busy = busy;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Server server = (Server) o;
        return Objects.equals(id, server.id) &&
                Objects.equals(name, server.name) &&
                Objects.equals(host, server.host) &&
                Objects.equals(port, server.port) &&
                Objects.equals(busy, server.busy) &&
                Objects.equals(version, server.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, host, port, busy, version);
    }

    @Override
    public String toString() {
        return "Server{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", host='" + host + '\'' +
                ", port=" + port +
                ", busy=" + busy +
                ", version=" + version +
                '}';
    }

}
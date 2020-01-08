import {observable} from "mobx";

export default class ProjectModel {
    id = Math.random();
    @observable name;
    @observable description;
    @observable teamId;
    @observable serverId;
    @observable productId;
    @observable phase;
    @observable status;

    constructor(id, name, description, teamId, serverId, productId, phase, status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.teamId = teamId;
        this.serverId = serverId;
        this.productId = productId;
        this.phase = phase;
        this.status = status;
    }
}

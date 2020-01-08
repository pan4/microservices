import {observable} from "mobx";

export default class ServerModel {
    id = Math.random();
    @observable name;
    @observable host;
    @observable port;
    @observable busy;

    constructor(id, name, host, port, busy) {
        this.id = id;
        this.name = name;
        this.host = host;
        this.port = port;
        this.busy = busy;
    }
}


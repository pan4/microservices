import {observable} from "mobx";

export default class TeamModel {
    id = Math.random();
    @observable name;
    @observable engineersCount;
    @observable qasCount;
    @observable busy;

    constructor(id, name, engineersCount, qasCount, busy) {
        this.id = id;
        this.name = name;
        this.engineersCount = engineersCount;
        this.qasCount = qasCount;
        this.busy = busy;
    }
}

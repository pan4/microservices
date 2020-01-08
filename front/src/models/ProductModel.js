import {observable} from "mobx";

export default class ProductModel {
    id = Math.random();
    @observable name;
    @observable businessAreaId;
    @observable clientId;

    constructor(id, name, businessAreaId, clientId) {
        this.id = id;
        this.name = name;
        this.businessAreaId = businessAreaId;
        this.clientId = clientId;
    }
}

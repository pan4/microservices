import { observable } from "mobx";

export default class ClientModel {
  id = Math.random();
  @observable email;
  @observable name;
  @observable businessAreaId;

  constructor(id, email, name, businessAreaId) {
    this.id = id;
    this.email = email;
    this.name = name;
    this.businessAreaId = businessAreaId;
  }
}

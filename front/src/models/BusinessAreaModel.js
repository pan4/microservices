import { observable } from "mobx";

export default class BusinessAreaModel {
  id = Math.random();
  @observable name;
  @observable description;

  constructor(id, name, description) {
    this.id = id;
    this.name = name;
    this.description = description;
  }
}

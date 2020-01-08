import {action, observable} from "mobx";

class AppState {
    @observable selectedClientId = '';

    constructor() {
        this.selectedClientId = '';
    }

    @action
    selectClient(clientId) {
        this.selectedClientId = clientId;
    }

}

export default new AppState();

import {observable, computed, action} from "mobx";

import Configuration from "../configuration";
import ClientModel from "./ClientModel";
import AppState from './AppState'

export default class ClientListModel {
    @observable clients = [];
    @observable state = 'pending';

    @action
    async addClient(email, name, baId) {
        this._postClient(email, name, baId)
            .then(s => {
                this.fetchClients();
            });
    }

    getClientName(clientId) {
        let client = this.clients.filter(s => s.id === clientId);
        if(client.length === 1) {
            return client[0].name;
        }

        return '';
    }

    @action
    async fetchClients() {
        this.state = "pending";
        this._fetchClients().then(
            bas => {
                this.clients = bas.map(item => (
                    new ClientModel(item.id, item.email, item.name, item.businessAreaId)
                ));
                this.state = "done";
                if (this.clients.length > 0) {
                    AppState.selectClient(this.clients[0].id)
                }
            },
            error => {
                this.state = "error"
            }
        )
    }

    @action
    async _fetchClients() {
        const url = Configuration.clientHost + `/client/list`;
        const response = await fetch(url);
        return await response.json();
    };

    @action
    async _postClient(email, name, baId) {
        const postUrl = Configuration.clientHost + '/client';
        const response = await fetch(postUrl, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                // 'Content-Type': 'application/x-www-form-urlencoded',
            },
            body: JSON.stringify({name: name, email: email, businessAreaId: baId})
        });
        return await response.json();
    }
}

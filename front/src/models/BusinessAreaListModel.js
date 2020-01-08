import {observable, action} from "mobx";

import BusinessAreaModel from "./BusinessAreaModel";
import Configuration from "../configuration";

export default class BusinessAreaListModel {
    @observable businessAreas = [];
    @observable state = 'pending';

    getBusinessAreaName(baId) {
        let ba = this.businessAreas.filter(s => s.id === baId);
        if(ba.length === 1) {
            return ba[0].name;
        }

        return '';
    }

    @action
    async addBusinessArea(name, description) {
        this._postBusinessArea(name, description)
            .then(s => {
                console.log("created");
                this.fetchBas();
            });

    }

    @action
    async fetchBas() {
        this.state = "pending";
        this._fetchBusinessAreas().then(
            bas => {
                this.businessAreas = bas.map(item => (
                    new BusinessAreaModel(item.id, item.name, item.description)
                ));
                this.state = "done"
            },
            error => {
                this.state = "error"
            }
        )
    }

    @action
    async _fetchBusinessAreas() {
        const url = Configuration.host + `/business_area/list`;
        const response = await fetch(url);
        return await response.json();
    };

    @action
    async _postBusinessArea(name, description) {
        const postUrl = Configuration.host + '/business_area';
        const response = await fetch(postUrl, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                // 'Content-Type': 'application/x-www-form-urlencoded',
            },
            body: JSON.stringify({name: name, description: description})
        });
        return await response.json();
    }
}

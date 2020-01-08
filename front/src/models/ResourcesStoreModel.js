import {observable, computed, action} from "mobx";

import ServerModel from "./ServerModel";
import Configuration from "../configuration";
import TeamModel from "./TeamModel";

export default class ResourcesStoreModel {
    @observable teams = [];
    @observable servers = [];
    @observable state = 'pending';

    getServerName(serverId) {
        let server = this.servers.filter(s => s.id === serverId);
        if(server.length === 1) {
            return server[0].name;
        }

        return '';
    }

    getTeamName(teamId) {
        let team = this.teams.filter(s => s.id === teamId);
        if(team.length === 1) {
            return team[0].name;
        }

        return '';
    }

    @action
    async addServer(name, host, port) {
        this._postServer(name, host, port)
            .then(s => {
                this.fetchServers();
            });

    }

    @action
    async fetchServers() {
        this.state = "pending";
        this._fetchServers().then(
            bas => {
                this.servers = bas.map(item => (
                    new ServerModel(item.id, item.name, item.host, item.port, item.busy)
                ));
                this.state = "done"
            },
            error => {
                this.state = "error"
            }
        )
    }

    @action
    async _fetchServers() {
        const url = Configuration.resourcesHost + `/server/list`;
        const response = await fetch(url);
        return await response.json();
    };

    @action
    async _postServer(name, host, port) {
        const postUrl = Configuration.resourcesHost + '/server';
        const response = await fetch(postUrl, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                // 'Content-Type': 'application/x-www-form-urlencoded',
            },
            body: JSON.stringify({name: name, host: host, port: port, busy: false})
        });
        return await response.json();
    }


    ///// teams
    @action
    async addTeam(name, engineersCount, qasCount) {
        this._postTeam(name, engineersCount, qasCount)
            .then(s => {
                this.fetchTeams();
            });

    }

    @action
    async fetchTeams() {
        this.state = "pending";
        this._fetchTeams().then(
            bas => {
                this.teams = bas.map(item => (
                    new TeamModel(item.id, item.name, item.engineersCount, item.qasCount, item.busy)
                ));
                this.state = "done"
            },
            error => {
                this.state = "error"
            }
        )
    }

    @action
    async _fetchTeams() {
        const url = Configuration.resourcesHost + `/team/list`;
        const response = await fetch(url);
        return await response.json();
    };

    @action
    async _postTeam(name, engineersCount, qasCount) {
        const postUrl = Configuration.resourcesHost + '/team';
        const response = await fetch(postUrl, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                // 'Content-Type': 'application/x-www-form-urlencoded',
            },
            body: JSON.stringify({name: name, engineersCount: engineersCount, qasCount: qasCount, busy: false})
        });
        return await response.json();
    }

    _asBoolean(busy) {
        return busy === 'on';
    }
}

import {action, observable} from "mobx";

import Configuration from "../configuration";
import ProjectModel from "./ProjectModel";

export default class ProjectListModel {
    @observable projects = [];
    @observable state = 'pending';

    @action
    async addProject(name, description, teamId, serverId, productId) {
        this._postProject(name, description, teamId, serverId, productId)
            .then(s => {
                this.fetchProjects();
            });
    }

    @action
    async start(projectId) {
        this._postStart(projectId)
            .then(() => this.fetchProjects())
    }

    @action
    async close(projectId) {
        this._postClose(projectId).then(() => this.fetchProjects())
    }

    @action
    async startDevelopment(projectId) {
        this._postStartDevelopment(projectId).then(() => this.fetchProjects())
    }

    @action
    async closeDevelopment(projectId) {
        this._postCloseDevelopment(projectId).then(() => this.fetchProjects())
    }

    @action
    async fetchProjects() {
        this.state = "pending";
        this._fetchProjects().then(
            bas => {
                this.projects = bas.map(item => (
                    new ProjectModel(
                        item.id,
                        item.name,
                        item.description,
                        item.teamId,
                        item.serverId,
                        item.productId,
                        item.phase,
                        item.status
                    )
                ));
                this.state = "done";
            },
            error => {
                this.state = "error"
            }
        )
    }

    @action
    async _fetchProjects() {
        const url = Configuration.projectHost + `/research/list`;
        const response = await fetch(url);
        return await response.json();
    };

    @action
    async _postStart(projectId) {
        const actionUrl = Configuration.projectHost + '/research/' + projectId + '/start';
        return await fetch(actionUrl, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            }
        });
    };

    @action
    async _postStartDevelopment(projectId) {
        const actionUrl = Configuration.projectHost + '/development/' + projectId + '/start';
        return await fetch(actionUrl, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            }
        });
    };

    @action
    async _postCloseDevelopment(projectId) {
        const actionUrl = Configuration.projectHost + '/development/' + projectId + '/close';
        return await fetch(actionUrl, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            }
        });
    };

    @action
    async _postClose(projectId) {
        const actionUrl = Configuration.projectHost + '/research/' + projectId + '/close';
        return await fetch(actionUrl, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            }
        });
    };


    @action
    async _postProject(name, description, teamId, serverId, productId) {
        const postUrl = Configuration.projectHost + '/research';
        const response = await fetch(postUrl, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                // 'Content-Type': 'application/x-www-form-urlencoded',
            },
            body: JSON.stringify({
                name: name,
                description: description,
                teamId: teamId,
                serverId: serverId,
                productId: productId
            })
        });
        return await response.json();
    }
}

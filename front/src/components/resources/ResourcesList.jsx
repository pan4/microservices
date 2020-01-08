import {observer} from "mobx-react";
import React from "react";
import {Team} from "./Team";
import TeamForm from "./TeamForm";
import ServerForm from "./ServerForm";
import Table from "react-bootstrap/Table";
import Button from "react-bootstrap/Button";
import Modal from "react-bootstrap/Modal";
import ClientForm from "../client/ClientForm";
import {action, observable} from "mobx";
import {Server} from "./Server";

@observer
class ResourcesList extends React.Component {
    @observable isShowTeamDialog = false;
    @observable isShowServerDialog = false;

    @action
    showAddTeamDialog = () => {
        this.isShowTeamDialog = true;
    };

    @action
    closeTeamDialog = () => {
        this.isShowTeamDialog = false;
    };

    @action
    showAddServerDialog = () => {
        this.isShowServerDialog = true;
    };

    @action
    closeServerDialog = () => {
        this.isShowServerDialog = false;
    };

    render() {
        return (
            <div style={{width: '800px'}}>
                Teams:
                <Table striped bordered hover size="sm">
                    <thead>
                    <tr>
                        <th>Id</th>
                        <th>Name</th>
                        <th>Engineers</th>
                        <th>QAs</th>
                        <th>Busy</th>
                    </tr>
                    </thead>
                    <tbody>
                    {this.props.resourcesStore.teams.map(c => (
                        <Team item={c} key={c.id}/>
                    ))}
                    </tbody>
                </Table>

                Servers:
                <Table striped bordered hover size="sm">
                    <thead>
                    <tr>
                        <th>Id</th>
                        <th>Name</th>
                        <th>Host</th>
                        <th>Port</th>
                        <th>Busy</th>
                    </tr>
                    </thead>
                    <tbody>
                    {this.props.resourcesStore.servers.map(c => (
                        <Server item={c} key={c.id}/>
                    ))}
                    </tbody>
                </Table>

                <Button variant="primary" onClick={this.showAddTeamDialog}>
                    Add team
                </Button>
                <Button variant="primary" onClick={this.showAddServerDialog}>
                    Add server
                </Button>

                <Modal show={this.isShowTeamDialog} onHide={this.closeTeamDialog}>
                    <Modal.Header closeButton>
                        <Modal.Title>Team</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        <TeamForm {...this.props}
                                    onAdd={this.closeTeamDialog}/>
                    </Modal.Body>
                </Modal>

                <Modal show={this.isShowServerDialog} onHide={this.closeServerDialog}>
                    <Modal.Header closeButton>
                        <Modal.Title>Server</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        <ServerForm {...this.props}
                                  onAdd={this.closeServerDialog}/>
                    </Modal.Body>
                </Modal>


            </div>
        )
    }
}

export default ResourcesList;

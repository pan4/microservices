import {observer} from "mobx-react";
import React from "react";
import {action, observable} from "mobx";
import Form from "react-bootstrap/Form";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import Button from "react-bootstrap/Button";

@observer
class ProjectForm extends React.Component {
    @observable name = '';
    @observable description = '';
    @observable teamId = '';
    @observable serverId = '';
    @observable productId = '';

    @action
    createNewProject = event => {
        this.props.projectsStore.addProject(this.name,
            this.description,
            this.selectedTeamId(),
            this.selectedServerId(),
            this.selectedProductId()
        );

        this.name = "";
        this.description = "";
        this.teamId = "";
        this.serverId = "";
        this.productId = "";

        event.preventDefault();

        this.props.onAdd();
    };

    selectedTeamId() {
        return this.teamId === '' ? this.props.resourcesStore.teams[0].id : this.teamId;
    }

    selectedServerId() {
        return this.serverId === '' ? this.props.resourcesStore.servers[0].id : this.serverId;
    }

    selectedProductId() {
        return this.productId === '' ? this.props.productsStore.products[0].id : this.productId;
    }

    render() {
        return (
            <Form>
                <Form.Group as={Row} controlId="nameId">
                    <Form.Label column sm='4'>Name: </Form.Label>
                    <Col sm="8">
                        <Form.Control
                            type="text"
                            value={this.name}
                            onChange={this.applyName}
                        />
                    </Col>
                </Form.Group>
                <Form.Group as={Row} controlId="descriptionId">
                    <Form.Label column sm='4'>Description:</Form.Label>
                    <Col sm='8'>
                        <Form.Control
                            type="text"
                            value={this.description}
                            onChange={this.applyDescription}
                        />
                    </Col>
                </Form.Group>
                <Form.Group as={Row} controlId={"teamId"}>
                    <Form.Label column sm={'4'}>Team</Form.Label>
                    <Col sm={'8'}>
                        <Form.Control as={'select'}
                                      onChange={this.applyTeam}
                                      value={this.selectedTeamId()}
                                      defaultValue={this.selectedTeamId()}>
                            {this.props.resourcesStore.teams.map(team => (
                                <option key={team.id} value={team.id}>{team.name}</option>
                            ))}
                        </Form.Control>
                    </Col>
                </Form.Group>
                <Form.Group as={Row} controlId={"serverId"}>
                    <Form.Label column sm={'4'}>Server</Form.Label>
                    <Col sm={'8'}>
                        <Form.Control as={'select'}
                                      onChange={this.applyServer}
                                      value={this.selectedServerId()}
                                      defaultValue={this.selectedServerId()}>
                            {this.props.resourcesStore.servers.map(server => (
                                <option key={server.id} value={server.id}>{server.name}</option>
                            ))}
                        </Form.Control>
                    </Col>
                </Form.Group>
                <Form.Group as={Row} controlId={"productId"}>
                    <Form.Label column sm={'4'}>Product</Form.Label>
                    <Col sm={'8'}>
                        <Form.Control as={'select'}
                                      onChange={this.applyProduct}
                                      value={this.selectedProductId()}
                                      defaultValue={this.selectedProductId()}>
                            {this.props.productsStore.products.map(product => (
                                <option key={product.id} value={product.id}>{product.name}</option>
                            ))}
                        </Form.Control>
                    </Col>
                </Form.Group>
                <Button type="submit" onClick={this.createNewProject}>Create new project</Button>
            </Form>
        )
    }

    @action
    applyName = event => {
        this.name = event.target.value;
    };

    @action
    applyDescription = event => {
        this.description = event.target.value;
    };

    @action
    applyTeam = event => {
        this.teamId = event.target.value;
    };

    @action
    applyServer = event => {
        this.serverId = event.target.value;
    };

    @action
    applyProduct = event => {
        this.productId = event.target.value;
    };

}

export default ProjectForm;

import {observer} from "mobx-react";
import React from "react";
import {action, observable} from "mobx";
import Form from "react-bootstrap/Form";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import Button from "react-bootstrap/Button";

@observer
class TeamForm extends React.Component {
    @observable teamName;
    @observable engineerCount;
    @observable qasCount;

    @action
    createNewTeam = event => {
        this.props.resourcesStore.addTeam(this.teamName, this.engineerCount, this.qasCount);

        this.teamName = "";
        this.engineerCount = 0;
        this.qasCount = 0;

        event.preventDefault();

        this.props.onAdd();
    };

    render() {
        return (

            <Form>
                <Form.Group as={Row} controlId="teamNameId">
                    <Form.Label column sm='4'>Team Name: </Form.Label>
                    <Col sm="8">
                        <Form.Control
                            type="text"
                            value={this.teamName}
                            onChange={this.applyTeamName}
                        />
                    </Col>
                </Form.Group>
                <Form.Group as={Row} controlId="teamEngCountId">
                    <Form.Label column sm='4'>Engineers count :</Form.Label>
                    <Col sm='8'>
                        <Form.Control
                            type="text"
                            value={this.engineerCount}
                            onChange={this.applyEngineersCount}
                        />
                    </Col>
                </Form.Group>
                <Form.Group as={Row} controlId="qasCountId">
                    <Form.Label column sm='4'>QAs count :</Form.Label>
                    <Col sm='8'>
                        <Form.Control
                            type="text"
                            value={this.qasCount}
                            onChange={this.applyQasCount}
                        />
                    </Col>
                </Form.Group>
                <Button type="submit" onClick={this.createNewTeam}>Create Team</Button>
            </Form>
        )
    }

    @action
    applyTeamName = event => {
        this.teamName = event.target.value;
    };

    @action
    applyEngineersCount = event => {
        this.engineerCount = event.target.value;
    };

    @action
    applyQasCount = event => {
        this.qasCount = event.target.value;
    };

}

export default TeamForm;

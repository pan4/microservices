import {observer} from "mobx-react";
import React from "react";
import {action, observable} from "mobx";
import Form from "react-bootstrap/Form";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import Button from "react-bootstrap/Button";

@observer
class ServerForm extends React.Component {
    @observable name;
    @observable host;
    @observable port;

    @action
    createNewServer = event => {
        this.props.resourcesStore.addServer(this.name, this.host, this.port);

        this.name = "";
        this.host = 0;
        this.port = 0;

        event.preventDefault();

        this.props.onAdd();
    };

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
                <Form.Group as={Row} controlId="hostId">
                    <Form.Label column sm='4'>Host:</Form.Label>
                    <Col sm='8'>
                        <Form.Control
                            type="text"
                            value={this.host}
                            onChange={this.applyHost}
                        />
                    </Col>
                </Form.Group>
                <Form.Group as={Row} controlId="portId">
                    <Form.Label column sm='4'>Port: </Form.Label>
                    <Col sm='8'>
                        <Form.Control
                            type="text"
                            value={this.port}
                            onChange={this.applyPort}
                        />
                    </Col>
                </Form.Group>
                <Button type="submit" onClick={this.createNewServer}>Create new server</Button>
            </Form>
        )
    }

    @action
    applyName = event => {
        this.name = event.target.value;
    };

    @action
    applyHost = event => {
        this.host = event.target.value;
    };

    @action
    applyPort = event => {
        this.port = event.target.value;
    };

}

export default ServerForm;

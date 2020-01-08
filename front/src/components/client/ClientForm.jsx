import {observer} from "mobx-react";
import React from "react";
import Button from "react-bootstrap/Button";
import {action, observable} from "mobx";
import Form from "react-bootstrap/Form";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";

@observer
class ClientForm extends React.Component {
    @observable clientEmail = '';
    @observable clientName = '';
    @observable clientBaId = '';

    @action
    createNewClient = event => {
        this.props.clientsStore.addClient(this.clientEmail, this.clientName, this.selectedBaId());

        this.clientEmail = "";
        this.clientName = "";
        this.clientBaId = "";

        event.preventDefault();

        this.props.onAdd();
    };

    @action
    applyClientName = event => {
        this.clientName = event.target.value;
    };

    @action
    applyClientEmail = event => {
        this.clientEmail = event.target.value;
    };

    @action
    applyClientBaId = event => {
        this.clientBaId = event.target.value;
    };

    selectedBaId() {
        if (this.props.basStore.businessAreas.length === 0) {
            return this.clientBaId;
        } else {
            return this.clientBaId === '' ? this.props.basStore.businessAreas[0].id : this.clientBaId;
        }
    }

    render() {
        return (
            <Form>
                <Form.Group as={Row} controlId="clientEmailId">
                    <Form.Label column sm='4'>Client email: </Form.Label>
                    <Col sm="8">
                        <Form.Control
                            type="text"
                            value={this.clientEmail}
                            onChange={this.applyClientEmail}
                        />
                    </Col>
                </Form.Group>
                <Form.Group as={Row} controlId="clientNameId">
                    <Form.Label column sm='4'>Client Name: </Form.Label>
                    <Col sm="8">
                        <Form.Control
                            type="text"
                            value={this.clientName}
                            onChange={this.applyClientName}
                        />
                    </Col>
                </Form.Group>
                <Form.Group as={Row} controlId={"clientBusinessAreaId"}>
                    <Form.Label column sm={'4'}>Business Area</Form.Label>
                    <Col sm={'8'}>
                        <Form.Control as={'select'}
                                      value={this.selectedBaId()}
                                      onChange={this.applyClientBaId}>
                            {this.props.basStore.businessAreas.map(ba => (
                                <option key={ba.id} value={ba.id}>{ba.name}</option>
                            ))}
                        </Form.Control>
                    </Col>
                </Form.Group>
                <Button type="submit" onClick={this.createNewClient}>Add</Button>
            </Form>
        )
    }
}

export default ClientForm;

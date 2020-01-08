import Form from "react-bootstrap/Form";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import Button from "react-bootstrap/Button";
import React from "react";
import {action, observable} from "mobx";

export default  class BusinessAreaForm extends React.Component {
    @observable baName;
    @observable baDescription;

    @action
    createNewBa = event => {
        this.props.basStore.addBusinessArea(this.baName, this.baDescription);

        this.baName = "";
        this.baDescription = "";

        event.preventDefault();

        this.props.onAdd();
    };

    @action
    applyBaName = event => {
        this.baName = event.target.value;
    };

    @action
    applyBaDescription = event => {
        this.baDescription = event.target.value;
    };

    render() {
        return (
            <Form>
                <Form.Group as={Row} controlId="baNameId">
                    <Form.Label column sm='4'>Business Area Name: </Form.Label>
                    <Col sm="8">
                        <Form.Control
                            type="text"
                            value={this.baName}
                            onChange={this.applyBaName}
                        />
                    </Col>
                </Form.Group>
                <Form.Group as={Row} controlId="baDescriptionId">
                    <Form.Label column sm='4'>Business Area description :</Form.Label>
                    <Col sm='8'>
                        <Form.Control
                            type="text"
                            value={this.baDescription}
                            onChange={this.applyBaDescription}
                        />
                    </Col>
                </Form.Group>
                <Button type="submit" onClick={this.createNewBa}>Add</Button>
            </Form>
        )
    }
}
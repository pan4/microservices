import React from "react";
import Form from "react-bootstrap/Form";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import Button from "react-bootstrap/Button";
import {action, observable} from "mobx";
import AppState from "../../models/AppState";
import {observer} from "mobx-react";

@observer
export default class ProductForm extends React.Component {
    @observable productName;
    @observable productType;
    @observable productBusinessArea = '';
    @observable clientId = '';

    @action
    createNewProduct = event => {
        this.props.productsStore.addProduct(this.productName, this.baSelected(), this.clientSelected());

        this.productName = "";
        this.productType = "";

        event.preventDefault();

        this.props.onAdd();
    };

    @action
    applyProductName = event => {
        this.productName = event.target.value;
    };

    @action
    applyProductType = event => {
        this.productType = event.target.value;
    };

    @action
    applyProductBusinessArea = event => {
        this.productBusinessArea = event.target.value;
    };

    @action
    applyProductClientId = event => {
        this.clientId = event.target.value;
    };

    baSelected() {
        return this.productBusinessArea === '' ? this.props.basStore.businessAreas[0].id : this.productBusinessArea;
    }

    clientSelected() {
        return this.clientId === '' ? AppState.selectedClientId : this.clientId;
    }

    render() {
        return (
            <Form>
                <Form.Group as={Row} controlId="productNameId">
                    <Form.Label column sm='4'>Product Name: </Form.Label>
                    <Col sm="8">
                        <Form.Control
                            type="text"
                            value={this.productName}
                            onChange={this.applyProductName}
                        />
                    </Col>
                </Form.Group>
                <Form.Group as={Row} controlId="productTypeId">
                    <Form.Label column sm='4'>Product Type:</Form.Label>
                    <Col sm='8'>
                        <Form.Control
                            type="text"
                            value={this.productType}
                            onChange={this.applyProductType}
                        />
                    </Col>
                </Form.Group>
                <Form.Group as={Row} controlId={"productBusinessAreaId"}>
                    <Form.Label column sm={'4'}>Business Area</Form.Label>
                    <Col sm={'8'}>
                        <Form.Control as={'select'}
                                      onChange={this.applyProductBusinessArea}
                                      value={this.baSelected()}>
                            {this.props.basStore.businessAreas.map(ba => (
                                <option key={ba.id} value={ba.id}>{ba.name}</option>
                            ))}
                        </Form.Control>
                    </Col>
                </Form.Group>
                <Form.Group as={Row} controlId={"clientIdId"}>
                    <Form.Label column sm={'4'}>Client Id</Form.Label>
                    <Col sm={'8'}>
                        <Form.Control as={'select'}
                                      onChange={this.applyProductClientId}
                                      value={this.clientSelected()}>
                            {this.props.clientsStore.clients.map(client => (
                                <option key={client.id} value={client.id}>{client.name}</option>
                            ))}
                        </Form.Control>
                    </Col>
                </Form.Group>
                <Button type="submit" onClick={this.createNewProduct}>Add</Button>
            </Form>
        )
    }
}

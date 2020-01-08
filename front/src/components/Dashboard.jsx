import React from "react";
import Tabs from "react-bootstrap/Tabs";
import Tab from "react-bootstrap/Tab";
import {observer} from "mobx-react";
import ProductList from "./product/ProductList";
import BusinessAreaList from "./businessArea/BusinessAreaList";
import ClientList from "./client/ClientList";
import ResourcesList from "./resources/ResourcesList";
import ProjectList from './project/ProjectList'
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import Container from "react-bootstrap/Container";
import Form from "react-bootstrap/Form";
import {action, observable} from "mobx";
import ApplicationState from '../models/AppState'
import Button from "react-bootstrap/Button";
import Modal from "react-bootstrap/Modal";
import ClientForm from "./client/ClientForm";

@observer
class Dashboard extends React.Component {
    @observable applicationState = ApplicationState;
    @observable isAddClientDialogVisible = false;

    @action
    changeCurrentClient = event => {
        this.applicationState.selectClient(event.target.value);
    };

    @action
    showAddClientDialog = () => {
        this.isAddClientDialogVisible = true;
    };

    @action
    closeAddClientDialog = () => {
        this.isAddClientDialogVisible = false;
    };

    render() {
        return (
            <Container>
                <Row>
                    <Col>
                        <Form>
                            <Form.Group as={Row} controlId={"clientId"}>
                                <Form.Label column sm={'4'}>Current client</Form.Label>
                                <Col sm={'8'}>
                                    <Form.Control as={'select'} onChange={this.changeCurrentClient}>
                                        {this.props.clientsStore.clients.map(client => (
                                            <option key={client.id} value={client.id}>{client.name}</option>
                                        ))}
                                    </Form.Control>
                                </Col>
                            </Form.Group>
                        </Form>
                    </Col>
                    <Col>
                        <Button variant="primary" onClick={this.showAddClientDialog}>
                            Add client
                        </Button>
                        <Modal show={this.isAddClientDialogVisible} onHide={this.closeAddClientDialog}>
                            <Modal.Header closeButton>
                                <Modal.Title>Modal heading</Modal.Title>
                            </Modal.Header>
                            <Modal.Body>
                                <ClientForm {...this.props}
                                            onAdd={this.closeAddClientDialog}/>
                            </Modal.Body>
                        </Modal>
                    </Col>
                </Row>
                <hr/>
                <Row>
                    <Col>
                        <Tabs defaultActiveKey="home" id="uncontrolled-tab-example">
                            <Tab eventKey="home" title={'Products (' + this.props.productsStore.products.length + ')'}>
                                <ProductList {...this.props}/>
                            </Tab>
                            <Tab eventKey="ba" title={'Business Area (' + this.props.basStore.businessAreas.length + ')'}>
                                <BusinessAreaList {...this.props}/>
                            </Tab>
                            <Tab eventKey="client" title={'Clients (' + this.props.clientsStore.clients.length + ')'}>
                                <ClientList {...this.props}/>
                            </Tab>
                            <Tab eventKey="resources" title={'Resources'}>
                                <ResourcesList {...this.props}/>
                            </Tab>
                            <Tab eventKey="projects" title={'Projects'}>
                                <ProjectList {...this.props}/>
                            </Tab>
                        </Tabs>
                    </Col>
                </Row>
            </Container>
        )
    }
}


export default Dashboard;

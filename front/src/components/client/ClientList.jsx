import {observer} from "mobx-react";
import React from "react";
import ClientForm from "./ClientForm";
import {Client} from "./Client";
import Table from "react-bootstrap/Table";
import {action, observable} from "mobx";
import Button from "react-bootstrap/Button";
import Modal from "react-bootstrap/Modal";
import Col from "react-bootstrap/Col";

@observer
class ClientList extends React.Component {
    @observable isAddClientDialogVisible = false;

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
            <div style={{width: '800px'}}>
                <Table striped bordered hover size="sm">
                    <thead>
                    <tr>
                        <th>Id</th>
                        <th>Name</th>
                        <th>Email</th>
                        <th>Business Area Id</th>
                    </tr>
                    </thead>
                    <tbody>
                    {this.props.clientsStore.clients.map(c => (
                        <Client item={c} key={c.id} {...this.props}/>
                    ))}
                    </tbody>
                </Table>

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
            </div>
        )
    }

}

export default ClientList;

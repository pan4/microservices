import {observer} from "mobx-react";
import React from "react";
import {BusinessArea} from "./BusinessArea";
import BusinessAreaForm from "./BusinessAreaForm";
import {action, observable} from "mobx";
import Button from "react-bootstrap/Button";
import Modal from "react-bootstrap/Modal";
import Table from "react-bootstrap/Table";

@observer
class BusinessAreaList extends React.Component {
    @observable isShowCreateBADialog = false;

    @action
    showCreateBADialog = () => {
        this.isShowCreateBADialog = true;
    };

    @action
    closeCreateBADialog = () => {
        this.isShowCreateBADialog = false;
    };

    render() {
        return (
            <div style={{width: '800px'}}>
                <Table striped bordered hover size="sm">
                    <thead>
                    <tr>
                        <th>Id</th>
                        <th>Name</th>
                        <th>Description</th>
                    </tr>
                    </thead>
                    <tbody>
                    {this.props.basStore.businessAreas.map(ba => (
                        <BusinessArea item={ba} key={ba.id}/>
                    ))}
                    </tbody>
                </Table>

                <Button variant="primary" onClick={this.showCreateBADialog}>
                    Add business area
                </Button>

                <Modal show={this.isShowCreateBADialog} onHide={this.closeCreateBADialog}>
                    <Modal.Header closeButton>
                        <Modal.Title>Business Area</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        <BusinessAreaForm {...this.props}
                                    onAdd={this.closeCreateBADialog}/>
                    </Modal.Body>
                </Modal>


            </div>
        )
    }


}

export default BusinessAreaList;

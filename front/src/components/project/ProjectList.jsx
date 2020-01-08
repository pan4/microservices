import {observer} from "mobx-react";
import React from "react";
import Project from "./Project";
import ProjectForm from "./ProjectForm";
import Table from "react-bootstrap/Table";
import Button from "react-bootstrap/Button";
import Modal from "react-bootstrap/Modal";
import {action, observable} from "mobx";

@observer
class ProjectList extends React.Component {
    @observable isShowProjectAddDialog = false;

    @action
    showAddProjectDialog = () => {
        this.isShowProjectAddDialog = true;
    };

    @action
    closeProjectDialog = () => {
        this.isShowProjectAddDialog = false;
    };

    render() {
        return (
            <div style={{width: '800px'}}>
                Projects:
                <Table striped bordered hover size="sm">
                    <thead>
                    <tr>
                        <th>id</th>
                        <th>Name</th>
                        <th>Description</th>
                        <th>Team</th>
                        <th>Server</th>
                        <th>Product</th>
                        <th>Phase</th>
                        <th>Status</th>
                        <th>Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    {this.props.projectsStore.projects.map(c => (
                        <Project item={c} key={c.id} {...this.props}/>
                    ))}
                    </tbody>
                </Table>


                <Button variant="primary" onClick={this.showAddProjectDialog}>
                    Add project
                </Button>

                <Modal show={this.isShowProjectAddDialog} onHide={this.closeProjectDialog}>
                    <Modal.Header closeButton>
                        <Modal.Title>Project</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        <ProjectForm {...this.props}
                                     onAdd={this.closeProjectDialog}/>
                    </Modal.Body>
                </Modal>


            </div>
        )
    }
}

export default ProjectList;

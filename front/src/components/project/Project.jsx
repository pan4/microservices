import {observer} from "mobx-react";
import React from "react";
import Button from "react-bootstrap/Button";

@observer
export default class Project extends React.Component {
    render() {
        let item = this.props.item;
        let productName = this.props.productsStore.getProductName(item.productId);
        let serverName = this.props.resourcesStore.getServerName(item.serverId);
        let teamName = this.props.resourcesStore.getTeamName(item.teamId);
        return (
            <tr>
                <td>{item.id}</td>
                <td>{item.name}</td>
                <td>{item.description}</td>
                <td>{teamName}</td>
                <td>{serverName}</td>
                <td>{productName}</td>
                <td>{item.phase}</td>
                <td>{item.status}</td>
                <td>{this.actions()}</td>
            </tr>
        )
    }


    start = () => {
        this.props.projectsStore.start(this.props.item.id)
    };

    close = () => {
        this.props.projectsStore.close(this.props.item.id)
    };

    startDevelopment = () => {
        this.props.projectsStore.startDevelopment(this.props.item.id)
    };

    closeDevelopment = () => {
        this.props.projectsStore.closeDevelopment(this.props.item.id)
    };

    actions = () => {
        let project = this.props.item;
        if (project.phase === 'RESEARCH' && project.status === 'INITIALIZED') {
            return <Button type="submit" onClick={this.start}>Start</Button>;
        }

        if (project.phase === 'RESEARCH' && project.status === 'IN_PROGRESS') {
            return <Button type="submit" onClick={this.close}>Close</Button>
        }

        if(project.phase === 'RESEARCH' && project.status === 'DONE') {
            return <Button type="submit" onClick={this.startDevelopment}>Develop</Button>
        }

        if(project.phase === 'DEVELOPMENT' && project.status === 'IN_PROGRESS') {
            return <Button type="submit" onClick={this.closeDevelopment}>Close dev</Button>
        }

        return '';
    };
}

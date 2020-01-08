import {observer} from "mobx-react";
import React from "react";

@observer
export class Team extends React.Component {
    render() {
        return (
            <tr>
                <td>{this.props.item.id}</td>
                <td>{this.props.item.name}</td>
                <td>{this.props.item.engineersCount}</td>
                <td>{this.props.item.qasCount}</td>
                <td>{this.props.item.busy ? 'busy' : 'available'}</td>
            </tr>
        )
    }
}

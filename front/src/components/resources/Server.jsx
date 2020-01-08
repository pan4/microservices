import {observer} from "mobx-react";
import React from "react";

@observer
export class Server extends React.Component {
    render() {
        return (
            <tr>
                <td>{this.props.item.id} </td>
                <td>{this.props.item.name}</td>
                <td>{this.props.item.host}</td>
                <td>{this.props.item.port}</td>
                <td>{this.props.item.busy ? 'busy' : 'available'}</td>
            </tr>
        )
    }
}

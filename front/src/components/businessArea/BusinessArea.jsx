import {observer} from "mobx-react";
import React from "react";

@observer
export class BusinessArea extends React.Component {
    render() {
        return (
            <tr>
                <td>{this.props.item.id}</td>
                <td>{this.props.item.name}</td>
                <td>{this.props.item.description}</td>
            </tr>
        )
    }
}

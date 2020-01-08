import {observer} from "mobx-react";
import React from "react";

@observer
export class Product extends React.Component {
    render() {
        let item = this.props.item;
        let clientName = this.props.clientsStore.getClientName(item.clientId);
        let baName = this.props.basStore.getBusinessAreaName(item.businessAreaId);
        return (
            <tr>
                <td>{item.id}</td>
                <td>{item.name}</td>
                <td>{baName}</td>
                <td>{clientName}</td>
            </tr>
        )
    }
}

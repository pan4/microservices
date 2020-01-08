import {observer} from "mobx-react";
import React from "react";

@observer
export class Client extends React.Component {
    render() {
        let item = this.props.item;
        let baName = this.props.basStore.getBusinessAreaName(item.businessAreaId);
        return (
            <tr>
                <td>{item.id}</td>
                <td>{item.name}</td>
                <td>{item.email}</td>
                <td>{baName}</td>
            </tr>

        )
    }
}

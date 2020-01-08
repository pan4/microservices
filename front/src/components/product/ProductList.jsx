import {observer} from "mobx-react";
import React from "react";
import {Product} from "./Product";
import {action, observable} from "mobx";
import Table from "react-bootstrap/Table";
import Button from "react-bootstrap/Button";
import Modal from "react-bootstrap/Modal";
import ProductForm from "./ProductForm";
import AppState from './../../models/AppState'

@observer
class ProductList extends React.Component {
    @observable isShowCreateProductDialog = false;

    @action
    showCreateProductDialog = () => {
        this.isShowCreateProductDialog = true;
    };

    @action
    closeCreateProductDialog = () => {
        this.isShowCreateProductDialog = false;
    };

    render() {
        let clientProducts = this.props.productsStore.filterByClient(AppState.selectedClientId);
        return (
            <div style={{width: '800px'}}>
                <Table striped bordered hover size="sm">
                    <thead>
                    <tr>
                        <th>Id</th>
                        <th>Name</th>
                        <th>Business Area</th>
                        <th>Client Id</th>
                    </tr>
                    </thead>
                    <tbody>
                    {this.props.productsStore.products.map(product => (
                        <Product item={product} key={product.id} {...this.props}/>
                    ))}
                    </tbody>
                </Table>

                <Button variant="primary" onClick={this.showCreateProductDialog}>
                    Add product
                </Button>
                <Modal show={this.isShowCreateProductDialog} onHide={this.closeCreateProductDialog}>
                    <Modal.Header closeButton>
                        <Modal.Title>Product</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        <ProductForm {...this.props}
                                    onAdd={this.closeCreateProductDialog}/>
                    </Modal.Body>
                </Modal>
            </div>
        )
    }

}

export default ProductList;

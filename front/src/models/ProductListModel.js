import {observable, action} from "mobx";

import Configuration from "../configuration";
import ProductModel from "./ProductModel";

export default class ProductListModel {
    @observable products = [];
    @observable state = 'pending';

    getProductName(productId) {
        let product = this.products.filter(p => p.id === productId);
        if(product.length === 1) {
            return product[0].name;
        }

        return '';
    }

    filterByClient(clientId) {
        return this.products.filter(p => p.id === clientId)
    }

    @action
    async addProduct(name, baId, clientId) {
        this._postProduct(name, baId, clientId)
            .then(s => {
                this.fetchProducts();
            });
    }

    @action
    async fetchProducts() {
        this.state = "pending";
        this._fetchProducts().then(
            bas => {
                this.products = bas.map(item => (
                    new ProductModel(item.id, item.name, item.businessAreaId, item.clientId)
                ));
                this.state = "done"
            },
            error => {
                this.state = "error"
            }
        )
    }

    @action
    async _fetchProducts() {
        const url = Configuration.productHost + `/product/list`;
        const response = await fetch(url);
        return await response.json();
    };

    @action
    async _postProduct(name, baId, clientId) {
        console.log(name, baId, clientId);
        const postUrl = Configuration.productHost + '/product';
        const response = await fetch(postUrl, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                // 'Content-Type': 'application/x-www-form-urlencoded',
            },
            body: JSON.stringify({name: name, businessAreaId: baId, clientId: clientId})
        });
        return await response.json();
    }
}

import React from "react";
import { render } from "react-dom";
import DevTools from "mobx-react-devtools";

import 'bootstrap/dist/css/bootstrap.css'
import 'react-bootstrap/dist/react-bootstrap'
import 'react-bootstrap/dist/react-bootstrap.min'
import Dashboard from "./components/Dashboard";
import ProductListModel from "./models/ProductListModel";
import BusinessAreaListModel from "./models/BusinessAreaListModel";
import ClientListModel from "./models/ClientListModel";
import TeamListModel from "./models/ResourcesStoreModel";
import ProjectListModel from "./models/ProjectListModel";

const productsStore = new ProductListModel();
const basStore = new BusinessAreaListModel();
const clientsStore = new ClientListModel();
const resourcesStore = new TeamListModel();
const projectsStore = new ProjectListModel();

render(
  <div>
    <DevTools />
    <Dashboard productsStore={productsStore}
               basStore={basStore}
               clientsStore={clientsStore}
               resourcesStore={resourcesStore}
               projectsStore={projectsStore}
    />
  </div>,
  document.getElementById("root")
);

productsStore.fetchProducts();
basStore.fetchBas();
clientsStore.fetchClients();
resourcesStore.fetchServers();
resourcesStore.fetchTeams();
projectsStore.fetchProjects();

// playing around in the console
window.products = productsStore;
window.ba = basStore;

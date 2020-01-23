class Configuration {
    constructor() {
        this.host = "http://localhost:8180";
        this.productHost = "http://localhost:8280";
        this.clientHost = 'http://localhost:8380';
        this.resourcesHost = 'http://localhost:8580';
        this.projectHost = 'http://localhost:8480';
    }
}

const defaultConfiguration = new Configuration();

export default defaultConfiguration

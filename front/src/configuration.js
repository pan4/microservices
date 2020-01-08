class Configuration {
    constructor() {
        this.host = "http://localhost:25100";
        this.productHost = "http://localhost:25200";
        this.clientHost = 'http://localhost:25300';
        this.resourcesHost = 'http://localhost:25500';
        this.projectHost = 'http://localhost:25400';
    }
}

const defaultConfiguration = new Configuration();

export default defaultConfiguration

package za.co.wethinkcode.fixme.broker;

import za.co.wethinkcode.fixme.core.client.Client;

public class Main {
    public static void main(String[] args){
        Client brokerClient = new BrokerClient(5001);
        brokerClient.run();
    }
}
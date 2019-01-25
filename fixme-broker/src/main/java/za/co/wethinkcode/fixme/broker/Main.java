package za.co.wethinkcode.fixme.broker;

public class Main {
    public static void main(String[] args){
        BrokerClient brokerClient = new BrokerClient(5001);
        brokerClient.run();
    }
}
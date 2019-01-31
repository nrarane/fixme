package za.co.wethinkcode.fixme.market;

import za.co.wethinkcode.fixme.core.client.Client;

public class Main {
    public static void main(String[] args){
        Client marketClient = new MarketClient(5000);
        marketClient.run();
    }
}
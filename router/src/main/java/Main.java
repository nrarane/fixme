
public class Main {

    public static void main(String[] args) {
        Thread marketThread = new Thread(new MarketRouter(5000, GlobalData.marketChannels, GlobalData.brokerChannels ));
        marketThread.start();
        Thread brokerThread = new Thread(new BrokerRouter(5001, GlobalData.brokerChannels, GlobalData.marketChannels ));
        brokerThread.start();
    }
}

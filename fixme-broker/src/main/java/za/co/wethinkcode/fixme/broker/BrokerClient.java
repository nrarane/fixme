package za.co.wethinkcode.fixme.broker;

import io.netty.channel.ChannelHandlerContext;
import za.co.wethinkcode.fixme.broker.handlers.IMessageHandler;
import za.co.wethinkcode.fixme.broker.handlers.IdHandler;
import za.co.wethinkcode.fixme.core.client.Client;
import za.co.wethinkcode.fixme.core.fixprotocol.FixMessage;

import java.util.Scanner;

public  class BrokerClient extends Client {
    BrokerClient(int port) {
        super(port);
    }

    public void startMainProgram() {
        new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Welcome to Nyameko's trading platform");

            while (true){
                System.out.println("Markets available\n\t\t 1. Fruit Market" );
                System.out.print("Enter market number : ");
                String market = scanner.nextLine();
                System.out.println("Available stock : \n\t\t 1. Bananas at R1 \n\t\t 2. Oranges R2 \n\t\t 3. Berries R3");
                System.out.print("Enter instrument number : ");
                String instrument = scanner.nextLine();
                System.out.print("Quantity : ");
                String quantity = scanner.nextLine();
                requestNewSingleOrder(market, instrument, quantity);
            }

        }).start();
    }

    private void requestNewSingleOrder(String market, String instrument, String quantity) {
        FixMessage fixMessage  = new FixMessage();

        fixMessage.setMessageType("D");
        fixMessage.setTargetComputer(market);
        fixMessage.setSenderCompID(String.valueOf(id));
        fixMessage.setInstrument(instrument);
        fixMessage.setQuantity(quantity);
        fixMessage.setPrice(1.1);

        String encodedFixMessage = fixMessage.encode();
        lastChannel.channel().writeAndFlush(encodedFixMessage +"\r\n");

    }

    @Override
    public void messageRead(ChannelHandlerContext ctx, String message) {
        FixMessage fixMessage = FixMessage.fromString(message);
        IMessageHandler messageHandler = new IdHandler();

        messageHandler.process(ctx, fixMessage, this);
    }


}

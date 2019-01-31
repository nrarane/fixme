package za.co.wethinkcode.fixme.market.handlers;

import io.netty.channel.ChannelHandlerContext;
import za.co.wethinkcode.fixme.core.fixprotocol.FixMessage;
import za.co.wethinkcode.fixme.market.MarketClient;

import java.util.Random;

public class NewOrderHandler extends IMessageHandler {
    Random random = new Random();

    protected NewOrderHandler() {
        super(null);
    }
    @Override
    public void process(ChannelHandlerContext ctx, FixMessage message, MarketClient client) {
        if (message.msgType != null && message.msgType.equals("D")){

            boolean isPurchasedAllowed = random.nextBoolean();

            sendExecutionReport(ctx, message, client, isPurchasedAllowed);
            System.out.println("New order received" + message);
        }
        else
            next(ctx, message, client);    }

    private void sendExecutionReport(ChannelHandlerContext ctx, FixMessage message, MarketClient client, boolean isPurchasedAllowed) {
        FixMessage fixMessage = new FixMessage();

        fixMessage.setMessageType("87");
        fixMessage.setTargetComputer(message.senderCompId);
        fixMessage.setSenderCompID(String.valueOf(client.id));
        fixMessage.setText("Purchase result: " + (isPurchasedAllowed ? "success" : "failure"));

        String encodedMessage = fixMessage.encode();

        ctx.channel().writeAndFlush( encodedMessage+"\r\n");
    }
}

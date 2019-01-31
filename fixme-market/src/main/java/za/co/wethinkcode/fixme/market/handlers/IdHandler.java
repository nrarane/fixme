package za.co.wethinkcode.fixme.market.handlers;

import io.netty.channel.ChannelHandlerContext;
import za.co.wethinkcode.fixme.core.client.Client;
import za.co.wethinkcode.fixme.core.fixprotocol.FixMessage;
import za.co.wethinkcode.fixme.market.MarketClient;

public class IdHandler extends IMessageHandler {
    public IdHandler() {
        super(new NewOrderHandler());
    }

    @Override
    public void process(ChannelHandlerContext ctx, FixMessage message, MarketClient client) {
        if (message.msgType != null && message.msgType.equals("0")){
            client.id = Integer.parseInt(message.targetComputer);
            System.out.println("My Id is " + client.id);
        }
        else
            next(ctx, message, client);
    }
}

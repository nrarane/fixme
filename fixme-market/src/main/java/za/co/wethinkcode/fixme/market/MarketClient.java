package za.co.wethinkcode.fixme.market;

import io.netty.channel.ChannelHandlerContext;
import za.co.wethinkcode.fixme.core.client.Client;
import za.co.wethinkcode.fixme.core.fixprotocol.FixMessage;
import za.co.wethinkcode.fixme.market.handlers.IMessageHandler;
import za.co.wethinkcode.fixme.market.handlers.IdHandler;

public  class MarketClient extends Client {
    MarketClient(int port) {
        super(port);
    }

    @Override
    public void messageRead(ChannelHandlerContext ctx, String message) {
        FixMessage fixMessage = FixMessage.fromString(message);
        IMessageHandler messageHandler = new IdHandler();

        messageHandler.process(ctx, fixMessage, this);
    }


}

package za.co.wethinkcode.fixme.market.handlers;

import io.netty.channel.ChannelHandlerContext;
import za.co.wethinkcode.fixme.core.client.Client;
import za.co.wethinkcode.fixme.core.fixprotocol.FixMessage;
import za.co.wethinkcode.fixme.market.MarketClient;

public abstract class  IMessageHandler {
    private IMessageHandler successor;

    protected IMessageHandler(IMessageHandler successor) {
        this.successor = successor;
    }

    void next(ChannelHandlerContext ctx, FixMessage message, MarketClient client) {
        if (successor != null){
            successor.process(ctx, message, client);
        }
    }

    public abstract void process(ChannelHandlerContext channelHandlerContext, FixMessage message, MarketClient client);
}

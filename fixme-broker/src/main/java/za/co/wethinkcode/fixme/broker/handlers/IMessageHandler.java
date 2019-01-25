package za.co.wethinkcode.fixme.broker.handlers;

import io.netty.channel.ChannelHandlerContext;
import za.co.wethinkcode.fixme.broker.BrokerClient;
import za.co.wethinkcode.fixme.core.fixprotocol.FixMessage;

public abstract class  IMessageHandler {
    private IMessageHandler successor;

    protected IMessageHandler(IMessageHandler successor) {
        this.successor = successor;
    }

    void next(ChannelHandlerContext ctx, FixMessage message, BrokerClient client) {
        if (successor != null){
            successor.process(ctx, message, client);
        }
    }

    abstract void process(ChannelHandlerContext channelHandlerContext, FixMessage message, BrokerClient client);
}

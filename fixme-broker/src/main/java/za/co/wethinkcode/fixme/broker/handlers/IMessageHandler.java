package za.co.wethinkcode.fixme.broker.handlers;

import com.sun.corba.se.pept.broker.Broker;
import io.netty.channel.ChannelHandlerContext;
import za.co.wethinkcode.fixme.broker.BrokerClient;
import za.co.wethinkcode.fixme.core.client.Client;
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

    public abstract void process(ChannelHandlerContext channelHandlerContext, FixMessage message, BrokerClient client);
}

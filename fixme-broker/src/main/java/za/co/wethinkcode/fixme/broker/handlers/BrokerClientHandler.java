package za.co.wethinkcode.fixme.broker.handlers;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import za.co.wethinkcode.fixme.broker.BrokerClient;
import za.co.wethinkcode.fixme.core.fixprotocol.FixMessage;

public class BrokerClientHandler extends SimpleChannelInboundHandler<String> {


    private BrokerClient client;

    public BrokerClientHandler(BrokerClient client) {
        this.client = client;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String message) throws Exception {
        FixMessage fixMessage = FixMessage.fromString(message);
        IMessageHandler messageHandler = new IdHandler();

        messageHandler.process(ctx, fixMessage, client);
    }
}

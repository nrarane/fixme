package za.co.wethinkcode.fixme.broker.handlers;

import io.netty.channel.ChannelHandlerContext;
import za.co.wethinkcode.fixme.broker.BrokerClient;
import za.co.wethinkcode.fixme.core.fixprotocol.FixMessage;

class IdHandler extends IMessageHandler {
    IdHandler() {
        super(null);
    }

    @Override
    void process(ChannelHandlerContext ctx, FixMessage message, BrokerClient client) {
        if (message.msgType != null && message.msgType.equals("0")){
            client.id = Integer.parseInt(message.targetComputer);
            System.out.println("My Id is " + client.id);
            client.startMainProgram();
        }
        else
            next(ctx, message, client);
    }
}

package za.co.wethinkcode.fixme.broker.handlers;

import io.netty.channel.ChannelHandlerContext;
import za.co.wethinkcode.fixme.broker.BrokerClient;
import za.co.wethinkcode.fixme.core.client.Client;
import za.co.wethinkcode.fixme.core.fixprotocol.FixMessage;

public class ExecutionReportHandler extends IMessageHandler {
    public ExecutionReportHandler() {
        super(null);
    }

    @Override
    public void process(ChannelHandlerContext ctx, FixMessage message, BrokerClient client) {
        if (message.msgType != null && message.msgType.equals("87")){
            System.out.println("result " + message.text);
        }
        else
            next(ctx, message, client);
    }
}

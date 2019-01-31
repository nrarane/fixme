package za.co.wethinkcode.fixme.core.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import za.co.wethinkcode.fixme.core.fixprotocol.FixMessage;

public class ClientHandler extends SimpleChannelInboundHandler<String> {


    private Client client;

    public ClientHandler(Client client) {
        this.client = client;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String message) throws Exception {
        client.messageRead(ctx, message);
    }
}

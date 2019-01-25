package za.co.wethinkcode.fixme.core.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import za.co.wethinkcode.fixme.core.fixprotocol.FixMessage;

public class ServerHandler extends SimpleChannelInboundHandler<String> {

    private final Server server;

    public ServerHandler(Server server) {
        this.server = server;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        int id = server.counter++;
        server.channels.put(id, ctx.channel());
        System.out.println("client connected. Id = " + id);
        sendIdToClient(ctx.channel(), id);
        server.channelActive(ctx);

    }

    private void sendIdToClient(Channel channel, int id) {
        FixMessage fixMessage = new FixMessage();
        fixMessage.setMessageType("0");
        fixMessage.setTargetComputer(String.valueOf(id));

        String encodedMessage = fixMessage.encode();

        channel.writeAndFlush( encodedMessage+"\r\n");

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String message) throws Exception {
        FixMessage fixMessage = FixMessage.fromString(message);

        if (fixMessage.targetComputer != null && !fixMessage.targetComputer.isEmpty()){
            Channel destination = server.respondChannels.get(Integer.parseInt(fixMessage.targetComputer));

            if (destination != null){
                destination.writeAndFlush(message+"\r\n");
            }
            else
                System.out.println("Could not find destination channel");
        }
        else
            System.out.println("Target computer is empty or null");

        server.messageRead(ctx, message);
    }
}

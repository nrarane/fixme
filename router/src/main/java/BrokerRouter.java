import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import za.co.wethinkcode.fixme.core.server.Server;

import java.util.HashMap;

public class BrokerRouter extends Server {


    BrokerRouter(int port, HashMap<Integer, Channel> channels, HashMap<Integer, Channel> respondChannels) {
        super(port, channels, respondChannels);
        counter = 500000;
    }
    @Override
    public void channelActive(ChannelHandlerContext ctx) {

        System.out.println("Broker has connected " );

    }

    @Override
    public void messageRead(ChannelHandlerContext ctx, String message) {
        System.out.print("Router read message from broker: " + message );
    }
}

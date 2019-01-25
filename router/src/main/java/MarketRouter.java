import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import za.co.wethinkcode.fixme.core.server.*;

import java.util.HashMap;

public class MarketRouter extends Server {

    MarketRouter(int port, HashMap<Integer, Channel> channels, HashMap<Integer, Channel> respondChannels) {
        super(port, channels, respondChannels);
        counter = 100000;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {

    }

    @Override
    public void messageRead(ChannelHandlerContext ctx, String message) {

    }
}

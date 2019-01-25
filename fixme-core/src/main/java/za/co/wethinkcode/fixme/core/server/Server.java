package za.co.wethinkcode.fixme.core.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.util.HashMap;

public abstract class Server implements Runnable {
    private int port;

    final HashMap<Integer, Channel> respondChannels;
    protected final HashMap<Integer, Channel> channels;
    protected int counter;

    public Server(int port, HashMap<Integer, Channel> channels, HashMap<Integer, Channel> respondChannels) {
        this.port = port;
        this.channels = channels;
        this.respondChannels = respondChannels;
    }

    public void run() {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {

            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup);
            b.channel(NioServerSocketChannel.class);
            b.childHandler(new ServerInitializer(this));

            ChannelFuture f = b.bind(port).sync();
            System.out.println("Router started on port " + port);
            f.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }


    public abstract void channelActive(ChannelHandlerContext ctx);

    public abstract void messageRead(ChannelHandlerContext ctx, String message);
}


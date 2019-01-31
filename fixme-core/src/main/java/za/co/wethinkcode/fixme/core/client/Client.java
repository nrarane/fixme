package za.co.wethinkcode.fixme.core.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import za.co.wethinkcode.fixme.core.fixprotocol.FixMessage;

import java.util.Scanner;


public abstract class Client implements Runnable{
    public int id;
    public ChannelFuture lastChannel;

    String HOST = "localhost";
    int PORT;

    protected Client(int port){
        this.PORT = port;
    }
    public void initialize() {

        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(workerGroup);
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
            bootstrap.handler(new Initializer(this));

            lastChannel = bootstrap.connect(HOST, PORT).sync();
            lastChannel.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }

    public void run() {
        initialize();
    }

    public abstract void messageRead(ChannelHandlerContext ctx, String message);
}

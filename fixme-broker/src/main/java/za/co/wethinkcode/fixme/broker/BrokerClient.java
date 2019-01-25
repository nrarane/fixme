package za.co.wethinkcode.fixme.broker;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import za.co.wethinkcode.fixme.core.fixprotocol.FixMessage;

import java.util.Scanner;


public class BrokerClient implements Runnable{
    public int id;
    ChannelFuture lastChannel;

    String HOST = "localhost";
    int PORT;

    BrokerClient(int port){
        this.PORT = port;
    }
    public void initialize() {

        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(workerGroup);
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
            bootstrap.handler(new BrokerInitializer(this));

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

    public void startMainProgram() {
        new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Welcome to Nyameko's trading platform");

            while (true){
                System.out.println("Markets available\n\t\t 1. Fruit Market" );
                System.out.print("Enter market number : ");
                String market = scanner.nextLine();
                System.out.println("Available stock : \n\t\t 1. Bananas at R1 \n\t\t 2. Oranges R2 \n\t\t 3. Berries R3");
                System.out.print("Enter instrument number : ");
                String instrument = scanner.nextLine();
                System.out.print("Quantity : ");
                String quantity = scanner.nextLine();
                requestNewSingleOrder(market, instrument, quantity);
            }

        }).start();
    }

    private void requestNewSingleOrder(String market, String instrument, String quantity) {
        FixMessage fixMessage  = new FixMessage();

        fixMessage.setTargetComputer(market);
        fixMessage.setInstrument(instrument);
        fixMessage.setQuantity(quantity);
        fixMessage.setPrice(1.1);

        String encodedFixMessage = fixMessage.encode();
        lastChannel.channel().writeAndFlush(encodedFixMessage +"\r\n");

    }
}

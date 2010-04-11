package de.eeriedaily.namqp.v08;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;

import java.util.concurrent.Executors;

/**
 * @author Joern Barthel <joern.barthel@acm.org>
 */
public class Example {

    public static void main(String[] args) {

        ChannelFactory factory =
                new NioClientSocketChannelFactory(
                        Executors.newCachedThreadPool(),
                        Executors.newCachedThreadPool());

        ClientBootstrap bootstrap = new ClientBootstrap(factory);

        Client client = new Client(bootstrap, new Configuration());

        ChannelFuture future = client.connect();

        if (!future.isSuccess()) {
            bootstrap.releaseExternalResources();
            throw new RuntimeException(future.getCause());
        }

    }

}

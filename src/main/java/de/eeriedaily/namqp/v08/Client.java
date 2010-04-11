package de.eeriedaily.namqp.v08;

import de.eeriedaily.namqp.v08.handler.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelPipeline;

import java.net.InetSocketAddress;

/**
 * @author Joern Barthel <joern.barthel@acm.org>
 */
public class Client {

    private static final Log log = LogFactory.getLog(Client.class);

    private final ClientBootstrap bootstrap;
    private final Configuration configuration;

    private Channel channel;

    public Client(ClientBootstrap bootstrap, Configuration configuration) {
        this.bootstrap = bootstrap;
        this.configuration = configuration;
    }

    public ChannelFuture connect() {

        if (log.isInfoEnabled())
            log.info(String.format("Connecting with configuration '%s'", configuration));

        ChannelPipeline pipeline = bootstrap.getPipeline();
        pipeline.addLast("assembler", new FrameAssembler());
        pipeline.addLast("decoder", new FrameDecoder());

        if (configuration.getHeartbeat() > 0)
            pipeline.addLast("heartbeat", new HeartbeatHandler(configuration));

        pipeline.addLast("handshakeHandler", new HandshakeHandler(configuration));

        ChannelFuture future = bootstrap.connect(new InetSocketAddress(configuration.getBrokerHostname(),
                configuration.getBrokerPort()));

        this.channel = future.awaitUninterruptibly().getChannel();

        return future;

    }

    public Channel getChannel() {
        return channel;
    }

}

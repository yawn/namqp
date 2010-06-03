package de.eeriedaily.namqp.v08;

import com.google.common.util.concurrent.ListenableFuture;
import de.eeriedaily.namqp.v08.handler.*;
import de.eeriedaily.namqp.v08.handler.channel.ChannelDelegation;
import de.eeriedaily.namqp.v08.methods.connection.ConnectionOpenOk;
import de.eeriedaily.namqp.v08.types.UnsignedShort;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;

import java.net.InetSocketAddress;

import static de.eeriedaily.namqp.v08.framing.Frame.ADMIN_CHANNEL;

/**
 * @author Joern Barthel <joern.barthel@acm.org>
 */
public class Client {

    private static final Log log = LogFactory.getLog(Client.class);

    private final ClientBootstrap bootstrap;
    private final Configuration configuration;
    private final ChannelDelegation channelDelegation;

    public Client(ClientBootstrap bootstrap, Configuration configuration) {
        this.bootstrap = bootstrap;
        this.configuration = configuration;
        this.channelDelegation = new ChannelDelegation();
    }

    public ListenableFuture<ConnectionOpenOk> connect() {

        if (log.isInfoEnabled())
            log.info(String.format("Connecting with configuration '%s'", configuration));

        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {

            public ChannelPipeline getPipeline() throws Exception {

                ChannelPipeline pipeline = Channels.pipeline();

                // downstream
                pipeline.addLast("encoder", new FrameEncoder());
                
                // upstream
                pipeline.addLast("assembler", new FrameAssembler());
                pipeline.addLast("decoder", new FrameDecoder());
                pipeline.addLast("heartbeatHandler", new HeartbeatHandler(configuration));
                pipeline.addLast("channels", channelDelegation);
                pipeline.addLast("handshakeHandler", new HandshakeHandler(configuration,
                        channelDelegation.getChannel(ADMIN_CHANNEL)));

                return pipeline;

            }

        });

        ListenableFuture<ConnectionOpenOk> future = getChannel(ADMIN_CHANNEL).addMethodListener(ConnectionOpenOk.class);

        bootstrap.connect(new InetSocketAddress(configuration.getBrokerHostname(),
                configuration.getBrokerPort()));

        return future;

    }

    public de.eeriedaily.namqp.v08.handler.channel.Channel getChannel(UnsignedShort channel) {
        return channelDelegation.getChannel(channel);
    }

}

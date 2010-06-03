package de.eeriedaily.namqp.v08.handler.channel;

import com.google.common.base.Function;
import com.google.common.collect.MapMaker;
import de.eeriedaily.namqp.v08.Configuration;
import de.eeriedaily.namqp.v08.framing.Frame;
import de.eeriedaily.namqp.v08.handler.AbstractFrameUpstreamHandler;
import de.eeriedaily.namqp.v08.types.UnsignedShort;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.MessageEvent;

import java.util.Map;

/**
 * @author Joern Barthel <joern.barthel@acm.org>
 */
public class ChannelDelegation extends AbstractFrameUpstreamHandler implements
        Function<UnsignedShort, Channel> {

    private final Map<UnsignedShort, Channel> delegates = new MapMaker()
            .makeComputingMap(this);

    private org.jboss.netty.channel.Channel channel;

    public Channel apply(UnsignedShort from) {
        return new Channel(from, this);
    }

    @Override
    public void channelOpen(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        this.channel = ctx.getChannel();
        ctx.sendUpstream(e);
    }

    @Override
    public void frameReceived(Frame frame, ChannelHandlerContext ctx, MessageEvent e) {
        delegates.get(frame.getChannel()).frameReceived(frame, ctx, e);
        ctx.sendUpstream(e);
    }

    public Channel getChannel(UnsignedShort channel) {
        return delegates.get(channel);
    }

    ChannelFuture write(Object message) {
        return channel.write(message);
    }

}

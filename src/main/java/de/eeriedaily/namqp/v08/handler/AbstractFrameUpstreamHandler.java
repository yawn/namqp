package de.eeriedaily.namqp.v08.handler;

import de.eeriedaily.namqp.v08.framing.Frame;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;

/**
 * @author Joern Barthel <joern.barthel@acm.org>
 */
public abstract class AbstractFrameUpstreamHandler extends SimpleChannelUpstreamHandler implements FrameUpstreamHandler {

    @Override
    public final void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {

        if (e.getMessage() instanceof Frame)
            frameReceived((Frame) e.getMessage(), ctx, e);
        else
            ctx.sendUpstream(e);

    }

    public void frameReceived(Frame frame, ChannelHandlerContext ctx, MessageEvent e) {
        ctx.sendUpstream(e);
    }

}

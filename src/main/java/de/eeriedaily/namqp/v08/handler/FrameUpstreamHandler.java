package de.eeriedaily.namqp.v08.handler;

import de.eeriedaily.namqp.v08.framing.Frame;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;

/**
 * @author Joern Barthel <joern.barthel@acm.org>
 */
public interface FrameUpstreamHandler {

    public void frameReceived(Frame frame, ChannelHandlerContext ctx, MessageEvent e);

}

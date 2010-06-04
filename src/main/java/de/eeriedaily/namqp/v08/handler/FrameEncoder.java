package de.eeriedaily.namqp.v08.handler;

import de.eeriedaily.namqp.v08.framing.Frame;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;

/**
 * Encodes frames into a channel buffer.
 *
 * @see de.eeriedaily.namqp.v08.handler.HandshakeHandler
 * @see de.eeriedaily.namqp.v08.framing.Frame
 * @author Joern Barthel <joern.barthel@acm.org>
 */
public class FrameEncoder extends OneToOneEncoder {

    private static final Log log = LogFactory.getLog(FrameEncoder.class);

    @Override
    protected Object encode(ChannelHandlerContext ctx, Channel channel, Object msg) throws Exception {

        if (msg instanceof ChannelBuffer)
            return msg;

        Frame frame = (Frame) msg;

        if (log.isDebugEnabled())
            log.debug(String.format("Encoding frame '%s' into channel buffer", frame));

        ChannelBuffer buffer = ChannelBuffers.buffer(frame.getSize());
        frame.writeTo(buffer);

        return buffer;

    }

}

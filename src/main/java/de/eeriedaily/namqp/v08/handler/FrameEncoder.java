package de.eeriedaily.namqp.v08.handler;

import de.eeriedaily.namqp.v08.framing.Frame;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;

/**
 * Encodes frames into a channel buffer. This encoder is installed by the HandshakeHandler, after sending
 * the initial protocol frame.
 *
 * @see de.eeriedaily.namqp.v08.handler.HandshakeHandler
 * @see de.eeriedaily.namqp.v08.framing.Frame
 * @author Joern Barthel <joern.barthel@acm.org>
 */
public class FrameEncoder extends OneToOneEncoder {

    private static final Log log = LogFactory.getLog(FrameEncoder.class);

    public FrameEncoder(ChannelPipeline pipeline) {
        pipeline.addFirst("encoder", this);
    }

    @Override
    protected Object encode(ChannelHandlerContext ctx, Channel channel, Object msg) throws Exception {

        Frame frame = (Frame) msg;

        if (log.isDebugEnabled())
            log.debug(String.format("Encoding frame '%s' into channel buffer", frame));

        ChannelBuffer buffer = ChannelBuffers.buffer(frame.getSizeAsInt());
        frame.writeTo(buffer);

        return buffer;

    }

}

package de.eeriedaily.namqp.v08.handler;

import de.eeriedaily.namqp.v08.framing.Frame;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandler;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneDecoder;

/**
 * @author Joern Barthel <joern.barthel@acm.org>
 */
@ChannelHandler.Sharable
public class FrameDecoder extends OneToOneDecoder {

    private static final Log log = LogFactory.getLog(FrameDecoder.class);

    @Override
    protected Object decode(ChannelHandlerContext ctx, Channel channel, Object msg) throws Exception {

        ChannelBuffer assembledBuffer = (ChannelBuffer) msg;

        Frame frame = new Frame(assembledBuffer);

        if (log.isDebugEnabled())
            log.debug(String.format("Decoded frame '%s'", frame));

        return frame;

    }

}

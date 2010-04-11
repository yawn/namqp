package de.eeriedaily.namqp.v08.handler;

import org.jboss.netty.channel.ChannelHandler;
import org.jboss.netty.handler.codec.frame.LengthFieldBasedFrameDecoder;

/**
 * @author Joern Barthel <joern.barthel@acm.org>
 */
@ChannelHandler.Sharable
public class FrameAssembler extends LengthFieldBasedFrameDecoder {

    public FrameAssembler() {
        super(Integer.MAX_VALUE, 3, 4, 1, 0);
    }

}

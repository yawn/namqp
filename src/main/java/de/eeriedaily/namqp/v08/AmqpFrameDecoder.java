package de.eeriedaily.namqp.v08;

import org.jboss.netty.handler.codec.frame.LengthFieldBasedFrameDecoder;

/**
 * @author Joern Barthel <joern.barthel@acm.org>
 */
public class AmqpFrameDecoder extends LengthFieldBasedFrameDecoder {

    public AmqpFrameDecoder() {
        super(Integer.MAX_VALUE, 3, 4, 0, 0);
    }

}

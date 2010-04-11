package de.eeriedaily.namqp.v08.framing;

import de.eeriedaily.namqp.v08.ClientException;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;

/**
 * @author Joern Barthel <joern.barthel@acm.org>
 */
public class MissingFrameDelimiter extends ClientException {

    public MissingFrameDelimiter(ChannelBuffer channelBuffer) {
        super(String.format("Frame not delimited: '%s'", ChannelBuffers.hexDump(channelBuffer)));
    }

}

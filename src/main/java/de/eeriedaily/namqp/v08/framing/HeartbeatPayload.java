package de.eeriedaily.namqp.v08.framing;

import de.eeriedaily.namqp.v08.types.Octet;
import org.jboss.netty.buffer.ChannelBuffer;

/**
 * @author Joern Barthel <joern.barthel@acm.org>
 */
public class HeartbeatPayload implements Payload {

    public static final Octet FRAME_ID = new Octet(8);
    public static final int SIZE = 0;

    public Octet getFrameId() {
        return FRAME_ID;
    }

    public long getSize() {
        return SIZE;
    }

    public void writeTo(ChannelBuffer channelBuffer) {
    }
    
}

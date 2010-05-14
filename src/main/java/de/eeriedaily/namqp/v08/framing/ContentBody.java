package de.eeriedaily.namqp.v08.framing;

import de.eeriedaily.namqp.v08.AbstractTransmittable;
import de.eeriedaily.namqp.v08.types.Octet;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;

import static org.jboss.netty.buffer.ChannelBuffers.hexDump;
import static org.jboss.netty.buffer.ChannelBuffers.unmodifiableBuffer;
import static org.jboss.netty.buffer.ChannelBuffers.wrappedBuffer;

/**
 * @author Joern Barthel <joern.barthel@acm.org>
 */
public class ContentBody extends AbstractTransmittable implements FrameBody {

    public static final Octet FRAME_ID = new Octet(3);

    private final ChannelBuffer content;

    public ContentBody(byte... bytes) {
        this.content = unmodifiableBuffer(wrappedBuffer(bytes));
    }

    public ContentBody(ChannelBuffer channelBuffer) {
        this.content = unmodifiableBuffer(channelBuffer.readBytes(channelBuffer.readableBytes() - 1));
    }

    public Octet getFrameId() {
        return FRAME_ID;
    }

    public long getSize() {
        return content.readableBytes();
    }

    public void writeTo(ChannelBuffer channelBuffer) {
        channelBuffer.writeBytes(content);
    }

    @Override
    public String toString() {
        return hexDump(content);
    }

}

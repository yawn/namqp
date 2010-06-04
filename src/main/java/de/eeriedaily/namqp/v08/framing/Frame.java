package de.eeriedaily.namqp.v08.framing;

import de.eeriedaily.namqp.v08.AbstractTransmittableContainer;
import de.eeriedaily.namqp.v08.Transmittable;
import de.eeriedaily.namqp.v08.types.Octet;
import de.eeriedaily.namqp.v08.types.UnsignedInt;
import de.eeriedaily.namqp.v08.types.UnsignedShort;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.netty.buffer.ChannelBuffer;

/**
 * @author Joern Barthel <joern.barthel@acm.org>
 */
public class Frame extends AbstractTransmittableContainer {

    private static final Log log = LogFactory.getLog(Frame.class);

    public static final UnsignedShort ADMIN_CHANNEL = new UnsignedShort(0);
    private static final Octet DELIMITER = new Octet(206);

    private final Octet frameId;
    private final UnsignedShort channel;
    private final UnsignedInt payloadSize;
    private final Payload payload;

    public Frame(Octet frameId, UnsignedShort channel, Payload payload) {
        this.frameId = frameId;
        this.channel = channel;
        this.payloadSize = new UnsignedInt(payload.getSize());
        this.payload = payload;
    }

    public Frame(ChannelBuffer buffer) {

        this.frameId = new Octet(buffer);
        this.channel = new UnsignedShort(buffer);
        this.payloadSize = new UnsignedInt(buffer);

        if (log.isDebugEnabled())
            log.debug(String.format("Decoding frame '%s' on channel '%s'", frameId, channel));

        this.payload = newPayload(frameId, payloadSize, buffer);

        if (!new Octet(buffer).equals(DELIMITER))
            throw new MissingFrameDelimiter(buffer);

    }

    protected static Payload newPayload(Octet frameId, UnsignedInt payloadSize, ChannelBuffer buffer) {

        if (frameId.equals(MethodPayload.FRAME_ID))
            return new MethodPayload(buffer);
        else if (frameId.equals(ContentHeaderPayload.FRAME_ID))
            return new ContentHeaderPayload(buffer);
        else if (frameId.equals(ContentBodyPayload.FRAME_ID))
            return new ContentBodyPayload(buffer.readBytes((int) payloadSize.getUnsignedInt()));
        else if (frameId.equals(HeartbeatPayload.FRAME_ID))
            return new HeartbeatPayload();
        else
            throw new UnknownFrameException(frameId);

    }

    public Octet getFrameId() {
        return frameId;
    }

    public UnsignedShort getChannel() {
        return channel;
    }

    public UnsignedInt getPayloadSize() {
        return payloadSize;
    }

    public boolean isContentHeaderFrame() {
        return getPayload() instanceof ContentHeaderPayload;
    }

    public boolean isContentBodyFrame() {
        return getPayload() instanceof ContentBodyPayload;
    }

    public boolean isMethodFrame() {
        return getPayload() instanceof MethodPayload;
    }

    public boolean isHeartbeatFrame() {
        return getPayload() instanceof HeartbeatPayload;
    }

    @SuppressWarnings({"unchecked"})
    public <T extends Payload> T getPayload() {
        return (T) payload;
    }

    @Override
    protected Transmittable[] getTransmittables() {
        return all(frameId, channel, payloadSize, payload, DELIMITER);
    }

    @Override
    public String toString() {
        return "Frame{" +
                "channel=" + channel +
                ", frameId=" + frameId +
                ", payloadSize=" + payloadSize +
                ", payload=" + payload +
                "} " + super.toString();
    }
    
}

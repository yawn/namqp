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
    private final UnsignedInt frameBodySize;
    private final FrameBody frameBody;

    public Frame(Octet frameId, UnsignedShort channel, FrameBody frameBody) {
        this.frameId = frameId;
        this.channel = channel;
        this.frameBodySize = new UnsignedInt(frameBody.getSize());
        this.frameBody = frameBody;
    }

    public Frame(ChannelBuffer buffer) {

        this.frameId = new Octet(buffer);
        this.channel = new UnsignedShort(buffer);
        this.frameBodySize = new UnsignedInt(buffer);

        if (log.isDebugEnabled())
            log.debug(String.format("Decoding frame '%s' on channel '%s'", frameId, channel));

        this.frameBody = newFrameBody(frameId, frameBodySize, buffer);

        if (!new Octet(buffer).equals(DELIMITER))
            throw new MissingFrameDelimiter(buffer);

    }

    protected static FrameBody newFrameBody(Octet frameId, UnsignedInt frameBodySize, ChannelBuffer buffer) {

        if (frameId.equals(MethodBody.FRAME_ID))
            return new MethodBody(buffer);
        else if (frameId.equals(ContentHeader.FRAME_ID))
            return new ContentHeader(buffer);
        else if (frameId.equals(ContentBody.FRAME_ID))
            return new ContentBody(buffer.readBytes((int) frameBodySize.getUnsignedInt()));
        else if (frameId.equals(HeartbeatBody.FRAME_ID))
            return new HeartbeatBody();
        else
            throw new UnknownFrameException(frameId);

    }

    public Octet getFrameId() {
        return frameId;
    }

    public UnsignedShort getChannel() {
        return channel;
    }

    public UnsignedInt getFrameBodySize() {
        return frameBodySize;
    }

    public boolean isContentHeaderFrame() {
        return getFrameBody() instanceof ContentHeader;
    }

    public boolean isContentBodyFrame() {
        return getFrameBody() instanceof ContentBody;
    }

    public boolean isMethodFrame() {
        return getFrameBody() instanceof MethodBody;
    }

    public boolean isHeartbeatFrame() {
        return getFrameBody() instanceof HeartbeatBody;
    }

    @SuppressWarnings({"unchecked"})
    public <T extends FrameBody> T getFrameBody() {
        return (T) frameBody;
    }

    @Override
    protected Transmittable[] getTransmittables() {
        return all(frameId, channel, frameBodySize, frameBody, DELIMITER);
    }

    @Override
    public String toString() {
        return "Frame{" +
                "channel=" + channel +
                ", frameId=" + frameId +
                ", frameBodySize=" + frameBodySize +
                ", frameBody=" + frameBody +
                "} " + super.toString();
    }
    
}

package de.eeriedaily.namqp.v08.framing;

import de.eeriedaily.namqp.v08.AbstractTransmittableContainer;
import de.eeriedaily.namqp.v08.Transmittable;
import de.eeriedaily.namqp.v08.framing.FrameBody;
import de.eeriedaily.namqp.v08.framing.HeartbeatBody;
import de.eeriedaily.namqp.v08.framing.MethodBody;
import de.eeriedaily.namqp.v08.framing.UnknownFrameException;
import de.eeriedaily.namqp.v08.types.Octet;
import de.eeriedaily.namqp.v08.types.UnsignedInt;
import de.eeriedaily.namqp.v08.types.UnsignedShort;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelHandlerContext;

/**
 * @author Joern Barthel <joern.barthel@acm.org>
 */
public class Frame extends AbstractTransmittableContainer {

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
        this.frameBody = newFrameBody(frameId, buffer);

        if (!new Octet(buffer).equals(DELIMITER))
            throw new MissingFrameDelimiter(buffer);

    }

    protected static FrameBody newFrameBody(Octet frameId, ChannelBuffer buffer) {

        if (frameId.equals(MethodBody.FRAME_ID))
            return new MethodBody(buffer);
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

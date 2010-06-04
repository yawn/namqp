package de.eeriedaily.namqp.v08;

import org.jboss.netty.buffer.ChannelBuffer;

/**
 * @author Joern Barthel <joern.barthel@acm.org>
 */
public abstract class AbstractTransmittableContainer extends AbstractTransmittable {

    protected static Transmittable[] all(Transmittable... transmittables) {
        return transmittables;
    }

    protected abstract Transmittable[] getTransmittables();

    public int getSize() {
        return sizeOf(getTransmittables());
    }

    public void writeTo(ChannelBuffer channelBuffer) {
        writeTo(channelBuffer, getTransmittables());
    }

}

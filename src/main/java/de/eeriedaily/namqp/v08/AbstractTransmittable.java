package de.eeriedaily.namqp.v08;

import org.jboss.netty.buffer.ChannelBuffer;

/**
 * @author Joern Barthel <joern.barthel@acm.org>
 */
public abstract class AbstractTransmittable implements Transmittable {

    protected static int sizeOf(Transmittable... transmittables) {

        int size = 0;

        for (Transmittable t : transmittables)
            size += t.getSize();

        return size;

    }

    protected static void writeTo(ChannelBuffer channelBuffer, Transmittable... transmittables) {

        for (Transmittable transmittable : transmittables)
            transmittable.writeTo(channelBuffer);

    }

}

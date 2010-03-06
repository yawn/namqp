package de.eeriedaily.namqp.v08;

import org.jboss.netty.buffer.ChannelBuffer;

/**
 * @author Joern Barthel <joern.barthel@acm.org>
 */
public abstract class AbstractTransmittable implements Transmittable {

    protected static long sizeOf(Iterable<? extends Transmittable> transmittables) {

        long size = 0;

        for (Transmittable t : transmittables)
            size += t.getSize();

        return size;

    }

    protected static long sizeOf(Transmittable... transmittables) {

        long size = 0;

        for (Transmittable t : transmittables)
            size += t.getSize();

        return size;

    }

    protected static int checkIntOverflow(long size) {

        if (size > Integer.MAX_VALUE)
            throw new UnsupportedOperationException(String.format("Sizes of '%d' are not (yet) supported", size));

        return (int) size;

    }


    protected static void writeTo(ChannelBuffer channelBuffer, Transmittable... transmittables) {

        for (Transmittable transmittable : transmittables)
            transmittable.writeTo(channelBuffer);

    }

}

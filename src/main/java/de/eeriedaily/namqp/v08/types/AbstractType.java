package de.eeriedaily.namqp.v08.types;

import de.eeriedaily.namqp.v08.AbstractTransmittable;
import de.eeriedaily.namqp.v08.Transmittable;

import java.util.Collection;

/**
 * @author Joern Barthel <joern.barthel@acm.org>
 */
public abstract class AbstractType extends AbstractTransmittable implements Type {

    public abstract Octet getIdentifier();

    protected static long sizeOf(Iterable<? extends Transmittable> transmittables) {

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

}

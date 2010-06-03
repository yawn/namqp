package de.eeriedaily.namqp.v08.types;

import com.google.common.base.Function;
import com.google.common.collect.MapMaker;
import de.eeriedaily.namqp.v08.AbstractTransmittable;
import org.jboss.netty.buffer.ChannelBuffer;

import java.util.Map;

/**
 * @author Joern Barthel <joern.barthel@acm.org>
 */
public class UnsignedShort extends AbstractTransmittable implements Type {

    public static final long SIZE = 2;

    private final int unsignedShort;

    private static final Map<Integer, UnsignedShort> cache = new MapMaker()
            .softValues()
            .makeComputingMap(new Function<Integer, UnsignedShort>() {

                public UnsignedShort apply(Integer from) {
                    return new UnsignedShort(from);
                }

            });

    public static UnsignedShort valueOf(int unsignedShort) {
        return cache.get(unsignedShort);
    }

    public UnsignedShort(int unsignedShort) {
        this.unsignedShort = unsignedShort;
    }

    public UnsignedShort(ChannelBuffer channelBuffer) {
        this(channelBuffer.readUnsignedShort());
    }

    public int getUnsignedShort() {
        return unsignedShort;
    }

    public long getSize() {
        return SIZE;
    }

    public void writeTo(ChannelBuffer channelBuffer) {
        channelBuffer.writeBytes(new byte[] { (byte)(unsignedShort >>> 8), (byte)(unsignedShort) });
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UnsignedShort that = (UnsignedShort) o;

        if (unsignedShort != that.unsignedShort) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return unsignedShort;
    }

    @Override
    public String toString() {
        return String.valueOf(unsignedShort);
    }

}

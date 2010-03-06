package de.eeriedaily.namqp.v08.types;

import de.eeriedaily.namqp.v08.AbstractTransmittable;
import org.jboss.netty.buffer.ChannelBuffer;

/**
 * @author Joern Barthel <joern.barthel@acm.org>
 */
public class UnsignedInt extends AbstractTransmittable implements Type {

    public static final long SIZE = 4;

    private final long unsignedInt;

    public UnsignedInt(long unsignedInt) {
        this.unsignedInt = unsignedInt;
    }

    public UnsignedInt(ChannelBuffer channelBuffer) {
        this(channelBuffer.readUnsignedInt());
    }

    public long getUnsignedInt() {
        return unsignedInt;
    }

    public long getSize() {
        return SIZE;
    }

    public void writeTo(ChannelBuffer channelBuffer) {
        channelBuffer.writeBytes(new byte[] {
                (byte)(unsignedInt >>> 24),
                (byte)(unsignedInt >>> 16),
                (byte)(unsignedInt >>> 8),
                (byte)(unsignedInt) });
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UnsignedInt that = (UnsignedInt) o;

        if (unsignedInt != that.unsignedInt) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (unsignedInt ^ (unsignedInt >>> 32));
    }

    @Override
    public String toString() {
        return String.valueOf(unsignedInt);
    }

}

package de.eeriedaily.namqp.v08.types;

import de.eeriedaily.namqp.v08.AbstractTransmittable;
import org.jboss.netty.buffer.ChannelBuffer;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Arrays;
import java.util.List;

/**
 * A write-only type. For reading bits see Bit.
 *
 * @see Bit
 * @author Joern Barthel <joern.barthel@acm.org>
 */
public class BitField extends AbstractTransmittable implements Type {

    public static final long SIZE = 1;

    private final List<Bit> bits;

    public BitField(Bit... bits) {
        this(Arrays.asList(bits));
    }

    public BitField(List<Bit> bits) {
        assert bits.size() <= 8;
        this.bits = bits;
    }
    
    protected static byte set(byte b, boolean bit, int idx) {
        return b |= (bit ? 1 : 0) << idx;
    }

    public long getSize() {
        return SIZE;
    }

    public void writeTo(ChannelBuffer channelBuffer) {

        byte b = 0;

        for (short i =0; i < bits.size(); i++)
            b = set(b, bits.get(i).isTrue(), i);

        channelBuffer.writeByte(b);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BitField bitField = (BitField) o;

        if (!bits.equals(bitField.bits)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return bits.hashCode();
    }

    @Override
    public String toString() {
        return bits.toString();
    }

}

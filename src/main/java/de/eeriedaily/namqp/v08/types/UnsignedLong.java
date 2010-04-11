package de.eeriedaily.namqp.v08.types;

import de.eeriedaily.namqp.v08.AbstractTransmittable;
import org.jboss.netty.buffer.ChannelBuffer;

import java.math.BigInteger;

/**
 * @author Joern Barthel <joern.barthel@acm.org>
 */
public class UnsignedLong extends AbstractTransmittable implements Type {

    public static final int SIZE = 4;

    private final BigInteger unsignedLong;

    public UnsignedLong(int unsignedLong) {
        this(BigInteger.valueOf(unsignedLong));
    }

    public UnsignedLong(BigInteger unsignedLong) {
        this.unsignedLong = unsignedLong;
    }

    public UnsignedLong(ChannelBuffer channelBuffer) {

        byte[] buffer = new byte[SIZE];
        channelBuffer.readBytes(buffer);

        this.unsignedLong = new BigInteger(buffer);

    }

    public BigInteger getUnsignedLong() {
        return unsignedLong;
    }

    public long getSize() {
        return SIZE;
    }

    public void writeTo(ChannelBuffer channelBuffer) {

        byte[] buffer = unsignedLong.toByteArray();

        channelBuffer.writeZero(SIZE - buffer.length);
        channelBuffer.writeBytes(buffer);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UnsignedLong that = (UnsignedLong) o;

        if (!unsignedLong.equals(that.unsignedLong)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return unsignedLong.hashCode();
    }

    @Override
    public String toString() {
        return unsignedLong.toString();
    }

}

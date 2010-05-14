package de.eeriedaily.namqp.v08.types;

import de.eeriedaily.namqp.v08.AbstractTransmittable;
import org.jboss.netty.buffer.ChannelBuffer;

import java.math.BigInteger;

/**
 * @author Joern Barthel <joern.barthel@acm.org>
 */
public class UnsignedLongLong extends AbstractTransmittable implements Type {

    public static final int SIZE = 8;

    private final BigInteger unsignedLongLong;

    public UnsignedLongLong(int unsignedLongLong) {
        this(BigInteger.valueOf(unsignedLongLong));
    }

    public UnsignedLongLong(BigInteger unsignedLongLong) {
        this.unsignedLongLong = unsignedLongLong;
    }

    public UnsignedLongLong(ChannelBuffer channelBuffer) {

        byte[] buffer = new byte[SIZE];
        channelBuffer.readBytes(buffer);

        this.unsignedLongLong = new BigInteger(buffer);

    }

    public BigInteger getUnsignedLongLong() {
        return unsignedLongLong;
    }

    public long getSize() {
        return SIZE;
    }

    public void writeTo(ChannelBuffer channelBuffer) {

        byte[] buffer = unsignedLongLong.toByteArray();

        channelBuffer.writeZero(SIZE - buffer.length);
        channelBuffer.writeBytes(buffer);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UnsignedLongLong that = (UnsignedLongLong) o;

        if (!unsignedLongLong.equals(that.unsignedLongLong)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return unsignedLongLong.hashCode();
    }

    @Override
    public String toString() {
        return unsignedLongLong.toString();
    }

}

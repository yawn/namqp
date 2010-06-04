package de.eeriedaily.namqp.v08.types;

import de.eeriedaily.namqp.v08.AbstractTransmittable;
import org.jboss.netty.buffer.ChannelBuffer;

/**
* @author Joern Barthel <joern.barthel@acm.org>
 */
public class Octet extends AbstractTransmittable implements Type {

    public static final int SIZE = 1;

    private final byte octet;

    public Octet(int i) {

        if (i < 0 || i > 255)
            throw new IllegalArgumentException();

        this.octet = (byte) i;

    }

    public Octet(byte octet) {
        this.octet = octet;
    }

    public Octet(ChannelBuffer channelBuffer) {
        this(channelBuffer.readByte());
    }

    public boolean getBoolean() {

        if (octet != 0 && octet != 1) {
            throw new IllegalStateException(String.format("Cannot determine boolean value from octet value '%s'", octet));
        }

        return octet == 1;

    }

    public int getInt() {
        return octet;
    }

    public byte getOctet() {
        return octet;
    }

    public int getSize() {
        return SIZE;
    }

    public void writeTo(ChannelBuffer channelBuffer) {
        channelBuffer.writeByte(octet);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Octet octet1 = (Octet) o;

        if (octet != octet1.octet) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) octet;
    }

    @Override
    public String toString() {
        return String.valueOf(octet);
    }

}

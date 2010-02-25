package de.eeriedaily.namqp.v08.types;

import org.jboss.netty.buffer.ChannelBuffer;

/**
 * @author Joern Barthel <joern.barthel@acm.org>
 */
public class UnsignedShort extends AbstractType {

    public static final Octet IDENTIFIER = new Octet('u');
    public static final long SIZE = 2;

    private final int unsignedShort;

    public UnsignedShort(int unsignedShort) {
        this.unsignedShort = unsignedShort;
    }

    public UnsignedShort(ChannelBuffer channelBuffer) {
        this(channelBuffer.readUnsignedShort());
    }

    public int getUnsignedShort() {
        return unsignedShort;
    }

    @Override
    public Octet getIdentifier() {
        return IDENTIFIER;
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

package de.eeriedaily.namqp.v08.types;

import org.jboss.netty.buffer.ChannelBuffer;

/**
 * TODO: support large sizes / fetch on demand
 *
 * @author Joern Barthel <joern.barthel@acm.org>
 */
public class LongString extends AbstractType {

    public static final Octet IDENTIFIER = new Octet('S');

    private final String string;

    public LongString(String string) {
        this.string = string;
    }

    public LongString(ChannelBuffer channelBuffer) {
        int size = checkIntOverflow(new UnsignedInt(channelBuffer).getUnsignedInt());
        this.string = channelBuffer.readBytes(size).toString("utf-8");
    }

    @Override
    public Octet getIdentifier() {
        return IDENTIFIER;
    }

    public long getSize() {
        return UnsignedInt.SIZE + string.length();
    }

    public void writeTo(ChannelBuffer channelBuffer) {
        new UnsignedInt(string.length()).writeTo(channelBuffer);
        channelBuffer.writeBytes(string.getBytes());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LongString that = (LongString) o;

        if (!string.equals(that.string)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return string.hashCode();
    }

    @Override
    public String toString() {
        return string;
    }

}

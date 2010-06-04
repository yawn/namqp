package de.eeriedaily.namqp.v08.types;

import de.eeriedaily.namqp.v08.AbstractTransmittable;
import org.jboss.netty.buffer.ChannelBuffer;

import java.nio.charset.Charset;

/**
 * TODO: support large sizes / fetch on demand
 *
 * @author Joern Barthel <joern.barthel@acm.org>
 */
public class LongString extends AbstractTransmittable implements FieldTableValueType {

    public static final Octet IDENTIFIER = new Octet('S');

    private final String string;

    public LongString(String string) {
        this.string = string;
    }

    public LongString(ChannelBuffer channelBuffer) {
        int size = (int) new UnsignedInt(channelBuffer).getUnsignedInt();
        this.string = channelBuffer.readBytes(size).toString(Charset.forName("utf-8"));
    }

    public Octet getType() {
        return IDENTIFIER;
    }

    public int getSize() {
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

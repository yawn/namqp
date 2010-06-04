package de.eeriedaily.namqp.v08.types;

import de.eeriedaily.namqp.v08.AbstractTransmittable;
import org.jboss.netty.buffer.ChannelBuffer;

import java.nio.charset.Charset;

import static java.lang.String.*;

/**
 * @author Joern Barthel <joern.barthel@acm.org>
 */
public class ShortString extends AbstractTransmittable implements FieldTableValueType {

    public static final Octet IDENTIFIER = new Octet('s');
    public static final ShortString EMPTY = new ShortString("");

    private final String string;

    public ShortString(String string) {

        this.string = string;

        if (string.length() > Byte.MAX_VALUE * 2)
            throw new IllegalArgumentException(format("Short string length '%d' is too long", string.length()));

    }

    public ShortString(ChannelBuffer channelBuffer) {
        this(channelBuffer.readBytes(new Octet(channelBuffer).getOctet()).toString(Charset.forName("utf-8")));
    }

    public String getString() {
        return string;
    }

    public Octet getType() {
        return IDENTIFIER;
    }

    public int getSize() {
        return Octet.SIZE + string.length();
    }

    public void writeTo(ChannelBuffer channelBuffer) {
        new Octet(string.length()).writeTo(channelBuffer);
        channelBuffer.writeBytes(string.getBytes());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShortString that = (ShortString) o;

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

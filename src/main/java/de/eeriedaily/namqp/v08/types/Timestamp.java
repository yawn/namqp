package de.eeriedaily.namqp.v08.types;

import de.eeriedaily.namqp.v08.AbstractTransmittable;
import org.jboss.netty.buffer.ChannelBuffer;

import java.math.BigInteger;
import java.util.Date;

/**
 * @author Joern Barthel <joern.barthel@acm.org>
 */
public class Timestamp extends AbstractTransmittable implements FieldTableValueType {

    public static long SIZE = UnsignedLong.SIZE;
    public static final Octet IDENTIFIER = new Octet('T');

    private final Date date;

    public Timestamp(Date date) {
        this.date = date;
    }

    public Timestamp(ChannelBuffer channelBuffer) {
        this.date = new Date(new UnsignedLong(channelBuffer).getUnsignedLong().longValue());
    }

    public Octet getType() {
        return IDENTIFIER;
    }

    public long getSize() {
        return SIZE;
    }

    public void writeTo(ChannelBuffer channelBuffer) {
        new UnsignedLong(BigInteger.valueOf(date.getTime())).writeTo(channelBuffer);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Timestamp timestamp = (Timestamp) o;

        if (!date.equals(timestamp.date)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return date.hashCode();
    }

    @Override
    public String toString() {
        return "Timestamp{" +
                "date=" + date +
                "} " + super.toString();
    }

}

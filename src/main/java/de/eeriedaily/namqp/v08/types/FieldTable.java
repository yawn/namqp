package de.eeriedaily.namqp.v08.types;

import de.eeriedaily.namqp.v08.AbstractTransmittable;
import org.jboss.netty.buffer.ChannelBuffer;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import static java.lang.String.format;

/**
 * TODO: support pattern
 * Field names MUST start with a letter, '$' or '#' and may continue with letters, '$' or '#', digits, or underlines, to a maximum length of 128 characters.
 *
 * TODO: decimal, signed int
 *
 * @author Joern Barthel <joern.barthel@acm.org>
 */
public class FieldTable extends AbstractTransmittable implements FieldTableValueType {

    public static final Octet IDENTIFIER = new Octet('F');  

    private final Map<ShortString, FieldTableValueType> map;

    @SuppressWarnings({"unchecked"})
    public FieldTable() {
        this.map = Collections.EMPTY_MAP;
    }

    public FieldTable(Map<ShortString, FieldTableValueType> map) {
        this.map = map;
    }

    public FieldTable(ChannelBuffer channelBuffer) {

        map = new LinkedHashMap<ShortString, FieldTableValueType>();

        int size = (int) new UnsignedInt(channelBuffer).getUnsignedInt();
        ChannelBuffer mapBuffer = channelBuffer.readBytes(size);

        while (mapBuffer.readableBytes() > 0) {

            ShortString key = new ShortString(mapBuffer);
            FieldTableValueType value = readFieldTableValue(mapBuffer);

            if (!map.containsKey(key))
                map.put(key, value);

        }

    }

    public Map<ShortString, FieldTableValueType> getMap() {
        return Collections.unmodifiableMap(map);
    }

    protected FieldTableValueType readFieldTableValue(ChannelBuffer mapBuffer) {

        Octet type = new Octet(mapBuffer);

        if (type.equals(LongString.IDENTIFIER))
            return new LongString(mapBuffer);
        else if (type.equals(ShortString.IDENTIFIER))
            return new ShortString(mapBuffer);
        else if (type.equals(Timestamp.IDENTIFIER))
            return new Timestamp(mapBuffer);
        else
            throw new UnsupportedOperationException(format("Field table value of '%s' is not supported (yet)", type));

    }

    public Octet getType() {
        return IDENTIFIER;
    }

    public int getSize() {

        int size = UnsignedInt.SIZE;

        for (Map.Entry<ShortString, FieldTableValueType> e : map.entrySet())
            size += e.getKey().getSize() + e.getValue().getType().getSize() + e.getValue().getSize();

        return size;

    }

    public void writeTo(ChannelBuffer channelBuffer) {

        new UnsignedInt(getSize() - UnsignedInt.SIZE).writeTo(channelBuffer);

        for (Map.Entry<ShortString, FieldTableValueType> e : map.entrySet())
            writeTo(channelBuffer,
                    e.getKey(),
                    e.getValue().getType(),
                    e.getValue());

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FieldTable that = (FieldTable) o;

        if (!map.equals(that.map)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return map.hashCode();
    }

    @Override
    public String toString() {
        return map.toString();
    }

}

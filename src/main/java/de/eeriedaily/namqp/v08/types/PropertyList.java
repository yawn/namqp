package de.eeriedaily.namqp.v08.types;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import de.eeriedaily.namqp.v08.AbstractTransmittableContainer;
import de.eeriedaily.namqp.v08.Transmittable;
import de.eeriedaily.namqp.v08.methods.Property;
import de.eeriedaily.namqp.v08.methods.PropertyMap;
import org.jboss.netty.buffer.ChannelBuffer;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author Joern Barthel <joern.barthel@acm.org>
 */
public class PropertyList<T extends Enum & Property> extends AbstractTransmittableContainer {

    private static final short WIDTH = 16;

    @SuppressWarnings({"unchecked"})
    public static PropertyList EMPTY = new PropertyList(new PropertyMap());

    private Map<T, ?> propertyMap;

    public PropertyList(PropertyMap<T> propertyMap) {
        this.propertyMap = propertyMap;
    }

    public PropertyList(ChannelBuffer channelBuffer, Class<T> klass, T[] values) {

        Map<T, Object> propertyMap = new PropertyMap<T>(klass);

        final int propertyFlags = channelBuffer.readUnsignedShort();
        final List<T> stack = Lists.newArrayList();

        for (short i=1; i<WIDTH; i++)
            if ((propertyFlags & (1 << i)) != 0)
                stack.add(values[WIDTH - i - 1]);

        for (T property : Iterables.reverse(stack)) {

            Class<? extends Type> type = property.getValueType();

            if (type == FieldTable.class)
                propertyMap.put(property, new FieldTable(channelBuffer));
            else if (type == Octet.class)
                propertyMap.put(property, new Octet(channelBuffer));
            else if (type == ShortString.class)
                propertyMap.put(property, new ShortString(channelBuffer));
            else if (type == Timestamp.class)
                propertyMap.put(property, new Timestamp(channelBuffer));
            else
                throw new RuntimeException("");

            this.propertyMap = Collections.unmodifiableMap(propertyMap);

        }

    }

    @Override
    protected Transmittable[] getTransmittables() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public String toString() {
        return "PropertyList{" +
                "propertyMap=" + propertyMap +
                "} " + super.toString();
    }
    
}

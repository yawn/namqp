package de.eeriedaily.namqp.v08.methods;

import com.google.common.collect.ForwardingMap;
import com.google.common.collect.Maps;

import java.util.Collections;
import java.util.Map;

/**
 * @author Joern Barthel <joern.barthel@acm.org>
 */
public class PropertyMap<T extends Enum & Property> extends ForwardingMap<T, Object> {

    private final Map<T, Object> delegate;

    @SuppressWarnings({"unchecked"})
    public PropertyMap() {
        this.delegate = Collections.EMPTY_MAP;
    }

    public PropertyMap(Class<T> klass) {
        delegate = Maps.newEnumMap(klass);
    }

    @Override
    protected Map<T, Object> delegate() {
        return delegate;
    }

    @Override
    public Object put(T key, Object value) {

        if (!key.getValueType().equals(value.getClass()))
            throw new IllegalArgumentException(String.format("Invalid value '%s' for key '%s'",
                    value,
                    key));

        return super.put(key, value);
        
    }

}

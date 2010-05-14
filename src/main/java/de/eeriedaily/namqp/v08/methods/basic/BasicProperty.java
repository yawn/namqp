package de.eeriedaily.namqp.v08.methods.basic;

import de.eeriedaily.namqp.v08.methods.Property;
import de.eeriedaily.namqp.v08.types.*;

/**
 * @author Joern Barthel <joern.barthel@acm.org>
 */
public enum BasicProperty implements Property {

    /**
     * MIME content type.
     */
    CONTENT_TYPE(ShortString.class),

    /**
     * MIME content encoding.
     */
    CONTENT_ENCODING(ShortString.class),

    /**
     * Message header field table.
     */
    HEADERS(FieldTable.class),

    /**
     * Non-persistent (1) or persistent (2).
     */
    DELIVERY_MODE(Octet.class),

    /**
     * The message priority, 0 to 9.
     */
    PRIORITY(Octet.class),

    /**
     * The application correlation identifier.
     */
    CORRELATION_ID(ShortString.class),

    /**
     * The destination to reply to.
     */
    REPLY_TO(ShortString.class),

    /**
     * Message expiration specification.
     */
    EXPIRATION(ShortString.class),

    /**
     * The application message identifier.
     */
    MESSAGE_ID(ShortString.class),

    /**
     * The message timestamp.
     */
    TIMESTAMP(Timestamp.class),

    /**
     * The message type name.
     */
    TYPE(ShortString.class),

    /**
     * The creating user id.
     */
    USER_ID(ShortString.class),

    /**
     * The creating application id.
     */
    APP_ID(ShortString.class),

    /**
     * Intra-cluster routing identifier.
     */
    CLUSTER_ID(ShortString.class);

    public static final UnsignedShort CLASS_ID = Basic.CLASS_ID;

    private final Class<? extends Type> type;

    BasicProperty(Class<? extends Type> type) {
        this.type = type;
    }

    public Class<? extends Type> getValueType() {
        return type;
    }

}

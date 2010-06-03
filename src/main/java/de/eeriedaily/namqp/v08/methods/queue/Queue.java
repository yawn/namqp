package de.eeriedaily.namqp.v08.methods.queue;

import de.eeriedaily.namqp.v08.methods.AbstractMethod;
import de.eeriedaily.namqp.v08.types.*;

import javax.annotation.Generated;

/**
 * Queues store and forward messages. Queues can be configured in the server
 * or created at runtime. Queues must be attached to at least one exchange
 * in order to receive messages from publishers.
 * 
 * @author Joern Barthel <joern.barthel@acm.org>
 */
@Generated(value = "generator.rb")  
public abstract class Queue extends AbstractMethod {

    public static final UnsignedShort CLASS_ID = new UnsignedShort(50);
    protected static final String CLASS_NAME = "queue";

    public final UnsignedShort getMethodClassId() {
        return CLASS_ID;
    }

    @Override
    protected final String getMethodClassName() {
        return CLASS_NAME;
    }
    
}    

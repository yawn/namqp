package de.eeriedaily.namqp.v08.methods.exchange;

import de.eeriedaily.namqp.v08.methods.AbstractMethod;
import de.eeriedaily.namqp.v08.types.*;

import javax.annotation.Generated;

/**
 * Exchanges match and distribute messages across queues. Exchanges can be
 * configured in the server or created at runtime.
 * 
 * @author Joern Barthel <joern.barthel@acm.org>
 */
@Generated(value = "generator.rb")  
public abstract class Exchange extends AbstractMethod {

    public static final UnsignedShort CLASS_ID = new UnsignedShort(40);
    protected static final String CLASS_NAME = "exchange";

    public final UnsignedShort getMethodClassId() {
        return CLASS_ID;
    }

    @Override
    protected final String getMethodClassName() {
        return CLASS_NAME;
    }
    
}    

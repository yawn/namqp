package de.eeriedaily.namqp.v08.methods.tx;

import de.eeriedaily.namqp.v08.methods.AbstractMethod;
import de.eeriedaily.namqp.v08.types.*;

import javax.annotation.Generated;

/**
 * Standard transactions provide so-called "1.5 phase commit". We can
 * ensure that work is never lost, but there is a chance of confirmations
 * being lost, so that messages may be resent. Applications that use
 * standard transactions must be able to detect and ignore duplicate
 * messages.
 * 
 * @author Joern Barthel <joern.barthel@acm.org>
 */
@Generated(value = "generator.rb")  
public abstract class Tx extends AbstractMethod {

    public static final UnsignedShort CLASS_ID = new UnsignedShort(90);
    protected static final String CLASS_NAME = "tx";

    public final UnsignedShort getMethodClassId() {
        return CLASS_ID;
    }

    @Override
    protected final String getMethodClassName() {
        return CLASS_NAME;
    }
    
}    

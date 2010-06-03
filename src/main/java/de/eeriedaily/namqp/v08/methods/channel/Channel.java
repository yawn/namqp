package de.eeriedaily.namqp.v08.methods.channel;

import de.eeriedaily.namqp.v08.methods.AbstractMethod;
import de.eeriedaily.namqp.v08.types.*;

import javax.annotation.Generated;

/**
 * The channel class provides methods for a client to establish a virtual
 * connection - a channel - to a server and for both peers to operate the
 * virtual connection thereafter.
 * 
 * @author Joern Barthel <joern.barthel@acm.org>
 */
@Generated(value = "generator.rb")  
public abstract class Channel extends AbstractMethod {

    public static final UnsignedShort CLASS_ID = new UnsignedShort(20);
    protected static final String CLASS_NAME = "channel";

    public final UnsignedShort getMethodClassId() {
        return CLASS_ID;
    }

    @Override
    protected final String getMethodClassName() {
        return CLASS_NAME;
    }
    
}    

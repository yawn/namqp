package de.eeriedaily.namqp.v08.methods.connection;

import de.eeriedaily.namqp.v08.methods.AbstractMethod;
import de.eeriedaily.namqp.v08.types.*;

import javax.annotation.Generated;

/**
 * The connection class provides methods for a client to establish a
 * network connection to a server, and for both peers to operate the
 * connection thereafter.
 * 
 * @author Joern Barthel <joern.barthel@acm.org>
 */
@Generated(value = "generator.rb")  
public abstract class Connection extends AbstractMethod {

    public static final UnsignedShort CLASS_ID = new UnsignedShort(10);
    protected static final String CLASS_NAME = "connection";

    public final UnsignedShort getMethodClassId() {
        return CLASS_ID;
    }

    @Override
    protected final String getMethodClassName() {
        return CLASS_NAME;
    }
    
}    

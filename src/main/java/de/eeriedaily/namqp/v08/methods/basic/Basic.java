package de.eeriedaily.namqp.v08.methods.basic;

import de.eeriedaily.namqp.v08.methods.AbstractMethod;
import de.eeriedaily.namqp.v08.types.*;

import javax.annotation.Generated;

/**
 * The Basic class provides methods that support an industry-standard
 * messaging model.
 * 
 * @author Joern Barthel <joern.barthel@acm.org>
 */
@Generated(value = "generator.rb")  
public abstract class Basic extends AbstractMethod {

    public static final UnsignedShort CLASS_ID = new UnsignedShort(60);
    protected static final String CLASS_NAME = "basic";

    public final UnsignedShort getMethodClassId() {
        return CLASS_ID;
    }

    @Override
    protected final String getMethodClassName() {
        return CLASS_NAME;
    }
    
}    

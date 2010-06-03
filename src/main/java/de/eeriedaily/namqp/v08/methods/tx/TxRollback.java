package de.eeriedaily.namqp.v08.methods.tx;

import de.eeriedaily.namqp.v08.Transmittable;
import de.eeriedaily.namqp.v08.types.*;
import org.jboss.netty.buffer.ChannelBuffer;  

import javax.annotation.Generated;

/**
 * This method abandons all messages published and acknowledged in
 * the current transaction. A new transaction starts immediately
 * after a rollback.
 * 
 * @author Joern Barthel <joern.barthel@acm.org>
 */
@Generated(value = "generator.rb")  
public final class TxRollback extends Tx {

    public static final UnsignedShort METHOD_ID = new UnsignedShort(30);
    protected static final String METHOD_NAME = "rollback";

    

    public TxRollback(ChannelBuffer channelBuffer) {
        this();
    }

    public TxRollback() {
        
    }

    public UnsignedShort getMethodMethodId() {
        return METHOD_ID;
    }

    @Override
    protected String getMethodMethodName() {
        return METHOD_NAME;
    }



    @Override
    protected Transmittable[] getTransmittables() {
        return all();
    }

}

package de.eeriedaily.namqp.v08.methods.tx;

import de.eeriedaily.namqp.v08.Transmittable;
import de.eeriedaily.namqp.v08.types.*;
import org.jboss.netty.buffer.ChannelBuffer;  

import javax.annotation.Generated;

/**
 * This method confirms to the client that the rollback succeeded.
 * Note that if an rollback fails, the server raises a channel exception.
 * 
 * @author Joern Barthel <joern.barthel@acm.org>
 */
@Generated(value = "generator.rb")  
public final class TxRollbackOk extends Tx {

    public static final UnsignedShort METHOD_ID = new UnsignedShort(31);
    protected static final String METHOD_NAME = "rollback-ok";

    

    public TxRollbackOk(ChannelBuffer channelBuffer) {
        this();
    }

    public TxRollbackOk() {
        
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

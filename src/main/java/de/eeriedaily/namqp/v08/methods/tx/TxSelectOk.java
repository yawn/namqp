package de.eeriedaily.namqp.v08.methods.tx;

import de.eeriedaily.namqp.v08.Transmittable;
import de.eeriedaily.namqp.v08.types.*;
import org.jboss.netty.buffer.ChannelBuffer;  

import javax.annotation.Generated;

/**
 * This method confirms to the client that the channel was successfully
 * set to use standard transactions.
 * 
 * @author Joern Barthel <joern.barthel@acm.org>
 */
@Generated(value = "generator.rb")  
public final class TxSelectOk extends Tx {

    public static final UnsignedShort METHOD_ID = new UnsignedShort(11);
    protected static final String METHOD_NAME = "select-ok";

    

    public TxSelectOk(ChannelBuffer channelBuffer) {
        this();
    }

    public TxSelectOk() {
        
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

package de.eeriedaily.namqp.v08.methods.tx;

import de.eeriedaily.namqp.v08.Transmittable;
import de.eeriedaily.namqp.v08.types.*;
import org.jboss.netty.buffer.ChannelBuffer;  

import javax.annotation.Generated;

/**
 * This method commits all messages published and acknowledged in
 * the current transaction. A new transaction starts immediately
 * after a commit.
 * 
 * @author Joern Barthel <joern.barthel@acm.org>
 */
@Generated(value = "generator.rb")  
public final class TxCommit extends Tx {

    public static final UnsignedShort METHOD_ID = new UnsignedShort(20);
    protected static final String METHOD_NAME = "commit";

    

    public TxCommit(ChannelBuffer channelBuffer) {
        this();
    }

    public TxCommit() {
        
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

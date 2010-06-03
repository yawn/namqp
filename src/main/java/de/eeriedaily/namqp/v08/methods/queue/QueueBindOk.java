package de.eeriedaily.namqp.v08.methods.queue;

import de.eeriedaily.namqp.v08.Transmittable;
import de.eeriedaily.namqp.v08.types.*;
import org.jboss.netty.buffer.ChannelBuffer;  

import javax.annotation.Generated;

/**
 * This method confirms that the bind was successful.
 * 
 * @author Joern Barthel <joern.barthel@acm.org>
 */
@Generated(value = "generator.rb")  
public final class QueueBindOk extends Queue {

    public static final UnsignedShort METHOD_ID = new UnsignedShort(21);
    protected static final String METHOD_NAME = "bind-ok";

    

    public QueueBindOk(ChannelBuffer channelBuffer) {
        this();
    }

    public QueueBindOk() {
        
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

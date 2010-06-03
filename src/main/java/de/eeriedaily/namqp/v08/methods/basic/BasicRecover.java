package de.eeriedaily.namqp.v08.methods.basic;

import de.eeriedaily.namqp.v08.Transmittable;
import de.eeriedaily.namqp.v08.types.*;
import org.jboss.netty.buffer.ChannelBuffer;  

import javax.annotation.Generated;

/**
 * This method asks the broker to redeliver all unacknowledged messages on a
 * specifieid channel. Zero or more messages may be redelivered.
 * 
 * @author Joern Barthel <joern.barthel@acm.org>
 */
@Generated(value = "generator.rb")  
public final class BasicRecover extends Basic {

    public static final UnsignedShort METHOD_ID = new UnsignedShort(100);
    protected static final String METHOD_NAME = "recover";

    private Bit requeue;

    public BasicRecover(ChannelBuffer channelBuffer) {
        this(new Bit(channelBuffer, 0));
    }

    public BasicRecover(Bit requeue) {
        this.requeue = requeue;
    }

    public UnsignedShort getMethodMethodId() {
        return METHOD_ID;
    }

    @Override
    protected String getMethodMethodName() {
        return METHOD_NAME;
    }

    public Bit getRequeue() {
        return this.requeue;
    }    


    @Override
    protected Transmittable[] getTransmittables() {
        return all(new BitField(requeue));
    }

}

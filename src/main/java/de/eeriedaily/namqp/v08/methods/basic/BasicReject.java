package de.eeriedaily.namqp.v08.methods.basic;

import de.eeriedaily.namqp.v08.Transmittable;
import de.eeriedaily.namqp.v08.types.*;
import org.jboss.netty.buffer.ChannelBuffer;  

import javax.annotation.Generated;

/**
 * This method allows a client to reject a message. It can be used to
 * interrupt and cancel large incoming messages, or return untreatable
 * messages to their original queue.
 * 
 * @author Joern Barthel <joern.barthel@acm.org>
 */
@Generated(value = "generator.rb")  
public final class BasicReject extends Basic {

    public static final UnsignedShort METHOD_ID = new UnsignedShort(90);
    protected static final String METHOD_NAME = "reject";

    private UnsignedLongLong deliveryTag;
	private Bit requeue;

    public BasicReject(ChannelBuffer channelBuffer) {
        this(new UnsignedLongLong(channelBuffer), new Bit(channelBuffer, 0));
    }

    public BasicReject(UnsignedLongLong deliveryTag, Bit requeue) {
        this.deliveryTag = deliveryTag;
		this.requeue = requeue;
    }

    public UnsignedShort getMethodMethodId() {
        return METHOD_ID;
    }

    @Override
    protected String getMethodMethodName() {
        return METHOD_NAME;
    }

    public UnsignedLongLong getDeliveryTag() {
        return this.deliveryTag;
    }    

    public Bit getRequeue() {
        return this.requeue;
    }    


    @Override
    protected Transmittable[] getTransmittables() {
        return all(deliveryTag, new BitField(requeue));
    }

}

package de.eeriedaily.namqp.v08.methods.basic;

import de.eeriedaily.namqp.v08.Transmittable;
import de.eeriedaily.namqp.v08.types.*;
import org.jboss.netty.buffer.ChannelBuffer;  

import javax.annotation.Generated;

/**
 * This method acknowledges one or more messages delivered via the
 * Deliver or Get-Ok methods. The client can ask to confirm a
 * single message or a set of messages up to and including a specific
 * message.
 * 
 * @author Joern Barthel <joern.barthel@acm.org>
 */
@Generated(value = "generator.rb")  
public final class BasicAck extends Basic {

    public static final UnsignedShort METHOD_ID = new UnsignedShort(80);
    protected static final String METHOD_NAME = "ack";

    private UnsignedLongLong deliveryTag;
	private Bit multiple;

    public BasicAck(ChannelBuffer channelBuffer) {
        this(new UnsignedLongLong(channelBuffer), new Bit(channelBuffer, 0));
    }

    public BasicAck(UnsignedLongLong deliveryTag, Bit multiple) {
        this.deliveryTag = deliveryTag;
		this.multiple = multiple;
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

    public Bit getMultiple() {
        return this.multiple;
    }    


    @Override
    protected Transmittable[] getTransmittables() {
        return all(deliveryTag, new BitField(multiple));
    }

}

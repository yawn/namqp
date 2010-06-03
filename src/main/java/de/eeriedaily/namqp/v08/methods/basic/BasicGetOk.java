package de.eeriedaily.namqp.v08.methods.basic;

import de.eeriedaily.namqp.v08.Transmittable;
import de.eeriedaily.namqp.v08.types.*;
import org.jboss.netty.buffer.ChannelBuffer;  

import javax.annotation.Generated;

/**
 * This method delivers a message to the client following a get
 * method. A message delivered by 'get-ok' must be acknowledged
 * unless the no-ack option was set in the get method.
 * 
 * @author Joern Barthel <joern.barthel@acm.org>
 */
@Generated(value = "generator.rb")  
public final class BasicGetOk extends Basic {

    public static final UnsignedShort METHOD_ID = new UnsignedShort(71);
    protected static final String METHOD_NAME = "get-ok";

    private UnsignedLongLong deliveryTag;
	private Bit redelivered;
	private ShortString exchange;
	private ShortString routingKey;
	private UnsignedLong messageCount;

    public BasicGetOk(ChannelBuffer channelBuffer) {
        this(new UnsignedLongLong(channelBuffer), new Bit(channelBuffer, 0), new ShortString(channelBuffer), new ShortString(channelBuffer), new UnsignedLong(channelBuffer));
    }

    public BasicGetOk(UnsignedLongLong deliveryTag, Bit redelivered, ShortString exchange, ShortString routingKey, UnsignedLong messageCount) {
        this.deliveryTag = deliveryTag;
		this.redelivered = redelivered;
		this.exchange = exchange;
		this.routingKey = routingKey;
		this.messageCount = messageCount;
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

    public Bit getRedelivered() {
        return this.redelivered;
    }    

    public ShortString getExchange() {
        return this.exchange;
    }    

    public ShortString getRoutingKey() {
        return this.routingKey;
    }    

    public UnsignedLong getMessageCount() {
        return this.messageCount;
    }    


    @Override
    protected Transmittable[] getTransmittables() {
        return all(deliveryTag, new BitField(redelivered), exchange, routingKey, messageCount);
    }

}

package de.eeriedaily.namqp.v08.methods.basic;

import de.eeriedaily.namqp.v08.Transmittable;
import de.eeriedaily.namqp.v08.types.*;
import org.jboss.netty.buffer.ChannelBuffer;  

import javax.annotation.Generated;

/**
 * This method delivers a message to the client, via a consumer. In
 * the asynchronous message delivery model, the client starts a
 * consumer using the Consume method, then the server responds with
 * Deliver methods as and when messages arrive for that consumer.
 * 
 * @author Joern Barthel <joern.barthel@acm.org>
 */
@Generated(value = "generator.rb")  
public final class BasicDeliver extends Basic {

    public static final UnsignedShort METHOD_ID = new UnsignedShort(60);
    protected static final String METHOD_NAME = "deliver";

    private ShortString consumerTag;
	private UnsignedLongLong deliveryTag;
	private Bit redelivered;
	private ShortString exchange;
	private ShortString routingKey;

    public BasicDeliver(ChannelBuffer channelBuffer) {
        this(new ShortString(channelBuffer), new UnsignedLongLong(channelBuffer), new Bit(channelBuffer, 0), new ShortString(channelBuffer), new ShortString(channelBuffer));
    }

    public BasicDeliver(ShortString consumerTag, UnsignedLongLong deliveryTag, Bit redelivered, ShortString exchange, ShortString routingKey) {
        this.consumerTag = consumerTag;
		this.deliveryTag = deliveryTag;
		this.redelivered = redelivered;
		this.exchange = exchange;
		this.routingKey = routingKey;
    }

    public UnsignedShort getMethodMethodId() {
        return METHOD_ID;
    }

    @Override
    protected String getMethodMethodName() {
        return METHOD_NAME;
    }

    public ShortString getConsumerTag() {
        return this.consumerTag;
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


    @Override
    protected Transmittable[] getTransmittables() {
        return all(consumerTag, deliveryTag, new BitField(redelivered), exchange, routingKey);
    }

}

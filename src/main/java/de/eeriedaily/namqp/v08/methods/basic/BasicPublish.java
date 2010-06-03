package de.eeriedaily.namqp.v08.methods.basic;

import de.eeriedaily.namqp.v08.Transmittable;
import de.eeriedaily.namqp.v08.types.*;
import org.jboss.netty.buffer.ChannelBuffer;  

import javax.annotation.Generated;

/**
 * This method publishes a message to a specific exchange. The message
 * will be routed to queues as defined by the exchange configuration
 * and distributed to any active consumers when the transaction, if any,
 * is committed.
 * 
 * @author Joern Barthel <joern.barthel@acm.org>
 */
@Generated(value = "generator.rb")  
public final class BasicPublish extends Basic {

    public static final UnsignedShort METHOD_ID = new UnsignedShort(40);
    protected static final String METHOD_NAME = "publish";

    private UnsignedShort ticket;
	private ShortString exchange;
	private ShortString routingKey;
	private Bit mandatory;
	private Bit immediate;

    public BasicPublish(ChannelBuffer channelBuffer) {
        this(new UnsignedShort(channelBuffer), new ShortString(channelBuffer), new ShortString(channelBuffer), new Bit(channelBuffer, 0), new Bit(channelBuffer, 1));
    }

    public BasicPublish(UnsignedShort ticket, ShortString exchange, ShortString routingKey, Bit mandatory, Bit immediate) {
        this.ticket = ticket;
		this.exchange = exchange;
		this.routingKey = routingKey;
		this.mandatory = mandatory;
		this.immediate = immediate;
    }

    public UnsignedShort getMethodMethodId() {
        return METHOD_ID;
    }

    @Override
    protected String getMethodMethodName() {
        return METHOD_NAME;
    }

    public UnsignedShort getTicket() {
        return this.ticket;
    }    

    public ShortString getExchange() {
        return this.exchange;
    }    

    public ShortString getRoutingKey() {
        return this.routingKey;
    }    

    public Bit getMandatory() {
        return this.mandatory;
    }    

    public Bit getImmediate() {
        return this.immediate;
    }    


    @Override
    protected Transmittable[] getTransmittables() {
        return all(ticket, exchange, routingKey, new BitField(mandatory, immediate));
    }

}

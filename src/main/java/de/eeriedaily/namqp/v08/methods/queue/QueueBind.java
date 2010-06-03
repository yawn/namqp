package de.eeriedaily.namqp.v08.methods.queue;

import de.eeriedaily.namqp.v08.Transmittable;
import de.eeriedaily.namqp.v08.types.*;
import org.jboss.netty.buffer.ChannelBuffer;  

import javax.annotation.Generated;

/**
 * This method binds a queue to an exchange. Until a queue is
 * bound it will not receive any messages. In a classic messaging
 * model, store-and-forward queues are bound to a dest exchange
 * and subscription queues are bound to a dest_wild exchange.
 * 
 * @author Joern Barthel <joern.barthel@acm.org>
 */
@Generated(value = "generator.rb")  
public final class QueueBind extends Queue {

    public static final UnsignedShort METHOD_ID = new UnsignedShort(20);
    protected static final String METHOD_NAME = "bind";

    private UnsignedShort ticket;
	private ShortString queue;
	private ShortString exchange;
	private ShortString routingKey;
	private Bit nowait;
	private FieldTable arguments;

    public QueueBind(ChannelBuffer channelBuffer) {
        this(new UnsignedShort(channelBuffer), new ShortString(channelBuffer), new ShortString(channelBuffer), new ShortString(channelBuffer), new Bit(channelBuffer, 0), new FieldTable(channelBuffer));
    }

    public QueueBind(UnsignedShort ticket, ShortString queue, ShortString exchange, ShortString routingKey, Bit nowait, FieldTable arguments) {
        this.ticket = ticket;
		this.queue = queue;
		this.exchange = exchange;
		this.routingKey = routingKey;
		this.nowait = nowait;
		this.arguments = arguments;
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

    public ShortString getQueue() {
        return this.queue;
    }    

    public ShortString getExchange() {
        return this.exchange;
    }    

    public ShortString getRoutingKey() {
        return this.routingKey;
    }    

    public Bit getNowait() {
        return this.nowait;
    }    

    public FieldTable getArguments() {
        return this.arguments;
    }    


    @Override
    protected Transmittable[] getTransmittables() {
        return all(ticket, queue, exchange, routingKey, new BitField(nowait), arguments);
    }

}

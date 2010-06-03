package de.eeriedaily.namqp.v08.methods.queue;

import de.eeriedaily.namqp.v08.Transmittable;
import de.eeriedaily.namqp.v08.types.*;
import org.jboss.netty.buffer.ChannelBuffer;  

import javax.annotation.Generated;

/**
 * This method creates or checks a queue. When creating a new queue
 * the client can specify various properties that control the durability
 * of the queue and its contents, and the level of sharing for the queue.
 * 
 * @author Joern Barthel <joern.barthel@acm.org>
 */
@Generated(value = "generator.rb")  
public final class QueueDeclare extends Queue {

    public static final UnsignedShort METHOD_ID = new UnsignedShort(10);
    protected static final String METHOD_NAME = "declare";

    private UnsignedShort ticket;
	private ShortString queue;
	private Bit passive;
	private Bit durable;
	private Bit exclusive;
	private Bit autoDelete;
	private Bit nowait;
	private FieldTable arguments;

    public QueueDeclare(ChannelBuffer channelBuffer) {
        this(new UnsignedShort(channelBuffer), new ShortString(channelBuffer), new Bit(channelBuffer, 0), new Bit(channelBuffer, 1), new Bit(channelBuffer, 2), new Bit(channelBuffer, 3), new Bit(channelBuffer, 4), new FieldTable(channelBuffer));
    }

    public QueueDeclare(UnsignedShort ticket, ShortString queue, Bit passive, Bit durable, Bit exclusive, Bit autoDelete, Bit nowait, FieldTable arguments) {
        this.ticket = ticket;
		this.queue = queue;
		this.passive = passive;
		this.durable = durable;
		this.exclusive = exclusive;
		this.autoDelete = autoDelete;
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

    public Bit getPassive() {
        return this.passive;
    }    

    public Bit getDurable() {
        return this.durable;
    }    

    public Bit getExclusive() {
        return this.exclusive;
    }    

    public Bit getAutoDelete() {
        return this.autoDelete;
    }    

    public Bit getNowait() {
        return this.nowait;
    }    

    public FieldTable getArguments() {
        return this.arguments;
    }    


    @Override
    protected Transmittable[] getTransmittables() {
        return all(ticket, queue, new BitField(passive, durable, exclusive, autoDelete, nowait), arguments);
    }

}

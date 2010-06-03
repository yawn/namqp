package de.eeriedaily.namqp.v08.methods.queue;

import de.eeriedaily.namqp.v08.Transmittable;
import de.eeriedaily.namqp.v08.types.*;
import org.jboss.netty.buffer.ChannelBuffer;  

import javax.annotation.Generated;

/**
 * This method deletes a queue. When a queue is deleted any pending
 * messages are sent to a dead-letter queue if this is defined in the
 * server configuration, and all consumers on the queue are cancelled.
 * 
 * @author Joern Barthel <joern.barthel@acm.org>
 */
@Generated(value = "generator.rb")  
public final class QueueDelete extends Queue {

    public static final UnsignedShort METHOD_ID = new UnsignedShort(40);
    protected static final String METHOD_NAME = "delete";

    private UnsignedShort ticket;
	private ShortString queue;
	private Bit ifUnused;
	private Bit ifEmpty;
	private Bit nowait;

    public QueueDelete(ChannelBuffer channelBuffer) {
        this(new UnsignedShort(channelBuffer), new ShortString(channelBuffer), new Bit(channelBuffer, 0), new Bit(channelBuffer, 1), new Bit(channelBuffer, 2));
    }

    public QueueDelete(UnsignedShort ticket, ShortString queue, Bit ifUnused, Bit ifEmpty, Bit nowait) {
        this.ticket = ticket;
		this.queue = queue;
		this.ifUnused = ifUnused;
		this.ifEmpty = ifEmpty;
		this.nowait = nowait;
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

    public Bit getIfUnused() {
        return this.ifUnused;
    }    

    public Bit getIfEmpty() {
        return this.ifEmpty;
    }    

    public Bit getNowait() {
        return this.nowait;
    }    


    @Override
    protected Transmittable[] getTransmittables() {
        return all(ticket, queue, new BitField(ifUnused, ifEmpty, nowait));
    }

}

package de.eeriedaily.namqp.v08.methods.queue;

import de.eeriedaily.namqp.v08.Transmittable;
import de.eeriedaily.namqp.v08.types.*;
import org.jboss.netty.buffer.ChannelBuffer;  

import javax.annotation.Generated;

/**
 * This method removes all messages from a queue. It does not cancel
 * consumers. Purged messages are deleted without any formal "undo"
 * mechanism.
 * 
 * @author Joern Barthel <joern.barthel@acm.org>
 */
@Generated(value = "generator.rb")  
public final class QueuePurge extends Queue {

    public static final UnsignedShort METHOD_ID = new UnsignedShort(30);
    protected static final String METHOD_NAME = "purge";

    private UnsignedShort ticket;
	private ShortString queue;
	private Bit nowait;

    public QueuePurge(ChannelBuffer channelBuffer) {
        this(new UnsignedShort(channelBuffer), new ShortString(channelBuffer), new Bit(channelBuffer, 0));
    }

    public QueuePurge(UnsignedShort ticket, ShortString queue, Bit nowait) {
        this.ticket = ticket;
		this.queue = queue;
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

    public Bit getNowait() {
        return this.nowait;
    }    


    @Override
    protected Transmittable[] getTransmittables() {
        return all(ticket, queue, new BitField(nowait));
    }

}

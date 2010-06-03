package de.eeriedaily.namqp.v08.methods.queue;

import de.eeriedaily.namqp.v08.Transmittable;
import de.eeriedaily.namqp.v08.types.*;
import org.jboss.netty.buffer.ChannelBuffer;  

import javax.annotation.Generated;

/**
 * This method confirms a Declare method and confirms the name of the
 * queue, essential for automatically-named queues.
 * 
 * @author Joern Barthel <joern.barthel@acm.org>
 */
@Generated(value = "generator.rb")  
public final class QueueDeclareOk extends Queue {

    public static final UnsignedShort METHOD_ID = new UnsignedShort(11);
    protected static final String METHOD_NAME = "declare-ok";

    private ShortString queue;
	private UnsignedLong messageCount;
	private UnsignedLong consumerCount;

    public QueueDeclareOk(ChannelBuffer channelBuffer) {
        this(new ShortString(channelBuffer), new UnsignedLong(channelBuffer), new UnsignedLong(channelBuffer));
    }

    public QueueDeclareOk(ShortString queue, UnsignedLong messageCount, UnsignedLong consumerCount) {
        this.queue = queue;
		this.messageCount = messageCount;
		this.consumerCount = consumerCount;
    }

    public UnsignedShort getMethodMethodId() {
        return METHOD_ID;
    }

    @Override
    protected String getMethodMethodName() {
        return METHOD_NAME;
    }

    public ShortString getQueue() {
        return this.queue;
    }    

    public UnsignedLong getMessageCount() {
        return this.messageCount;
    }    

    public UnsignedLong getConsumerCount() {
        return this.consumerCount;
    }    


    @Override
    protected Transmittable[] getTransmittables() {
        return all(queue, messageCount, consumerCount);
    }

}

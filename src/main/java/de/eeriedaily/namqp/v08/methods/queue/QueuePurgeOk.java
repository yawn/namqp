package de.eeriedaily.namqp.v08.methods.queue;

import de.eeriedaily.namqp.v08.Transmittable;
import de.eeriedaily.namqp.v08.types.*;
import org.jboss.netty.buffer.ChannelBuffer;  

import javax.annotation.Generated;

/**
 * This method confirms the purge of a queue.
 * 
 * @author Joern Barthel <joern.barthel@acm.org>
 */
@Generated(value = "generator.rb")  
public final class QueuePurgeOk extends Queue {

    public static final UnsignedShort METHOD_ID = new UnsignedShort(31);
    protected static final String METHOD_NAME = "purge-ok";

    private UnsignedLong messageCount;

    public QueuePurgeOk(ChannelBuffer channelBuffer) {
        this(new UnsignedLong(channelBuffer));
    }

    public QueuePurgeOk(UnsignedLong messageCount) {
        this.messageCount = messageCount;
    }

    public UnsignedShort getMethodMethodId() {
        return METHOD_ID;
    }

    @Override
    protected String getMethodMethodName() {
        return METHOD_NAME;
    }

    public UnsignedLong getMessageCount() {
        return this.messageCount;
    }    


    @Override
    protected Transmittable[] getTransmittables() {
        return all(messageCount);
    }

}

package de.eeriedaily.namqp.v08.methods.basic;

import de.eeriedaily.namqp.v08.Transmittable;
import de.eeriedaily.namqp.v08.types.*;
import org.jboss.netty.buffer.ChannelBuffer;  

import javax.annotation.Generated;

/**
 * This method tells the client that the queue has no messages
 * available for the client.
 * 
 * @author Joern Barthel <joern.barthel@acm.org>
 */
@Generated(value = "generator.rb")  
public final class BasicGetEmpty extends Basic {

    public static final UnsignedShort METHOD_ID = new UnsignedShort(72);
    protected static final String METHOD_NAME = "get-empty";

    private ShortString clusterId;

    public BasicGetEmpty(ChannelBuffer channelBuffer) {
        this(new ShortString(channelBuffer));
    }

    public BasicGetEmpty(ShortString clusterId) {
        this.clusterId = clusterId;
    }

    public UnsignedShort getMethodMethodId() {
        return METHOD_ID;
    }

    @Override
    protected String getMethodMethodName() {
        return METHOD_NAME;
    }

    public ShortString getClusterId() {
        return this.clusterId;
    }    


    @Override
    protected Transmittable[] getTransmittables() {
        return all(clusterId);
    }

}

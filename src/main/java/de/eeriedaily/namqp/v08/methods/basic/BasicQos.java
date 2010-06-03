package de.eeriedaily.namqp.v08.methods.basic;

import de.eeriedaily.namqp.v08.Transmittable;
import de.eeriedaily.namqp.v08.types.*;
import org.jboss.netty.buffer.ChannelBuffer;  

import javax.annotation.Generated;

/**
 * This method requests a specific quality of service. The QoS can
 * be specified for the current channel or for all channels on the
 * connection. The particular properties and semantics of a qos method
 * always depend on the content class semantics. Though the qos method
 * could in principle apply to both peers, it is currently meaningful
 * only for the server.
 * 
 * @author Joern Barthel <joern.barthel@acm.org>
 */
@Generated(value = "generator.rb")  
public final class BasicQos extends Basic {

    public static final UnsignedShort METHOD_ID = new UnsignedShort(10);
    protected static final String METHOD_NAME = "qos";

    private UnsignedLong prefetchSize;
	private UnsignedShort prefetchCount;
	private Bit global;

    public BasicQos(ChannelBuffer channelBuffer) {
        this(new UnsignedLong(channelBuffer), new UnsignedShort(channelBuffer), new Bit(channelBuffer, 0));
    }

    public BasicQos(UnsignedLong prefetchSize, UnsignedShort prefetchCount, Bit global) {
        this.prefetchSize = prefetchSize;
		this.prefetchCount = prefetchCount;
		this.global = global;
    }

    public UnsignedShort getMethodMethodId() {
        return METHOD_ID;
    }

    @Override
    protected String getMethodMethodName() {
        return METHOD_NAME;
    }

    public UnsignedLong getPrefetchSize() {
        return this.prefetchSize;
    }    

    public UnsignedShort getPrefetchCount() {
        return this.prefetchCount;
    }    

    public Bit getGlobal() {
        return this.global;
    }    


    @Override
    protected Transmittable[] getTransmittables() {
        return all(prefetchSize, prefetchCount, new BitField(global));
    }

}

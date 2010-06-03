package de.eeriedaily.namqp.v08.methods.connection;

import de.eeriedaily.namqp.v08.Transmittable;
import de.eeriedaily.namqp.v08.types.*;
import org.jboss.netty.buffer.ChannelBuffer;  

import javax.annotation.Generated;

/**
 * This method opens a connection to a virtual host, which is a
 * collection of resources, and acts to separate multiple application
 * domains within a server.
 * 
 * @author Joern Barthel <joern.barthel@acm.org>
 */
@Generated(value = "generator.rb")  
public final class ConnectionOpen extends Connection {

    public static final UnsignedShort METHOD_ID = new UnsignedShort(40);
    protected static final String METHOD_NAME = "open";

    private ShortString virtualHost;
	private ShortString capabilities;
	private Bit insist;

    public ConnectionOpen(ChannelBuffer channelBuffer) {
        this(new ShortString(channelBuffer), new ShortString(channelBuffer), new Bit(channelBuffer, 0));
    }

    public ConnectionOpen(ShortString virtualHost, ShortString capabilities, Bit insist) {
        this.virtualHost = virtualHost;
		this.capabilities = capabilities;
		this.insist = insist;
    }

    public UnsignedShort getMethodMethodId() {
        return METHOD_ID;
    }

    @Override
    protected String getMethodMethodName() {
        return METHOD_NAME;
    }

    public ShortString getVirtualHost() {
        return this.virtualHost;
    }    

    public ShortString getCapabilities() {
        return this.capabilities;
    }    

    public Bit getInsist() {
        return this.insist;
    }    


    @Override
    protected Transmittable[] getTransmittables() {
        return all(virtualHost, capabilities, new BitField(insist));
    }

}

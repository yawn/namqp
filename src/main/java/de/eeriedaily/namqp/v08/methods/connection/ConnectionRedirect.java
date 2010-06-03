package de.eeriedaily.namqp.v08.methods.connection;

import de.eeriedaily.namqp.v08.Transmittable;
import de.eeriedaily.namqp.v08.types.*;
import org.jboss.netty.buffer.ChannelBuffer;  

import javax.annotation.Generated;

/**
 * This method redirects the client to another server, based on the
 * requested virtual host and/or capabilities.
 * 
 * @author Joern Barthel <joern.barthel@acm.org>
 */
@Generated(value = "generator.rb")  
public final class ConnectionRedirect extends Connection {

    public static final UnsignedShort METHOD_ID = new UnsignedShort(50);
    protected static final String METHOD_NAME = "redirect";

    private ShortString host;
	private ShortString knownHosts;

    public ConnectionRedirect(ChannelBuffer channelBuffer) {
        this(new ShortString(channelBuffer), new ShortString(channelBuffer));
    }

    public ConnectionRedirect(ShortString host, ShortString knownHosts) {
        this.host = host;
		this.knownHosts = knownHosts;
    }

    public UnsignedShort getMethodMethodId() {
        return METHOD_ID;
    }

    @Override
    protected String getMethodMethodName() {
        return METHOD_NAME;
    }

    public ShortString getHost() {
        return this.host;
    }    

    public ShortString getKnownHosts() {
        return this.knownHosts;
    }    


    @Override
    protected Transmittable[] getTransmittables() {
        return all(host, knownHosts);
    }

}

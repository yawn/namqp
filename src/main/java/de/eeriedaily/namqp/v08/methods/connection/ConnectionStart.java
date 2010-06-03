package de.eeriedaily.namqp.v08.methods.connection;

import de.eeriedaily.namqp.v08.Transmittable;
import de.eeriedaily.namqp.v08.types.*;
import org.jboss.netty.buffer.ChannelBuffer;  

import javax.annotation.Generated;

/**
 * This method starts the connection negotiation process by telling
 * the client the protocol version that the server proposes, along
 * with a list of security mechanisms which the client can use for
 * authentication.
 * 
 * @author Joern Barthel <joern.barthel@acm.org>
 */
@Generated(value = "generator.rb")  
public final class ConnectionStart extends Connection {

    public static final UnsignedShort METHOD_ID = new UnsignedShort(10);
    protected static final String METHOD_NAME = "start";

    private Octet versionMajor;
	private Octet versionMinor;
	private FieldTable serverProperties;
	private LongString mechanisms;
	private LongString locales;

    public ConnectionStart(ChannelBuffer channelBuffer) {
        this(new Octet(channelBuffer), new Octet(channelBuffer), new FieldTable(channelBuffer), new LongString(channelBuffer), new LongString(channelBuffer));
    }

    public ConnectionStart(Octet versionMajor, Octet versionMinor, FieldTable serverProperties, LongString mechanisms, LongString locales) {
        this.versionMajor = versionMajor;
		this.versionMinor = versionMinor;
		this.serverProperties = serverProperties;
		this.mechanisms = mechanisms;
		this.locales = locales;
    }

    public UnsignedShort getMethodMethodId() {
        return METHOD_ID;
    }

    @Override
    protected String getMethodMethodName() {
        return METHOD_NAME;
    }

    public Octet getVersionMajor() {
        return this.versionMajor;
    }    

    public Octet getVersionMinor() {
        return this.versionMinor;
    }    

    public FieldTable getServerProperties() {
        return this.serverProperties;
    }    

    public LongString getMechanisms() {
        return this.mechanisms;
    }    

    public LongString getLocales() {
        return this.locales;
    }    


    @Override
    protected Transmittable[] getTransmittables() {
        return all(versionMajor, versionMinor, serverProperties, mechanisms, locales);
    }

}

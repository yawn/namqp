package de.eeriedaily.namqp.v08.methods.connection;

import de.eeriedaily.namqp.v08.Transmittable;
import de.eeriedaily.namqp.v08.types.*;
import org.jboss.netty.buffer.ChannelBuffer;  

import javax.annotation.Generated;

/**
 * This method selects a SASL security mechanism. ASL uses SASL
 * (RFC2222) to negotiate authentication and encryption.
 * 
 * @author Joern Barthel <joern.barthel@acm.org>
 */
@Generated(value = "generator.rb")  
public final class ConnectionStartOk extends Connection {

    public static final UnsignedShort METHOD_ID = new UnsignedShort(11);
    protected static final String METHOD_NAME = "start-ok";

    private FieldTable clientProperties;
	private ShortString mechanism;
	private FieldTable response;
	private ShortString locale;

    public ConnectionStartOk(ChannelBuffer channelBuffer) {
        this(new FieldTable(channelBuffer), new ShortString(channelBuffer), new FieldTable(channelBuffer), new ShortString(channelBuffer));
    }

    public ConnectionStartOk(FieldTable clientProperties, ShortString mechanism, FieldTable response, ShortString locale) {
        this.clientProperties = clientProperties;
		this.mechanism = mechanism;
		this.response = response;
		this.locale = locale;
    }

    public UnsignedShort getMethodMethodId() {
        return METHOD_ID;
    }

    @Override
    protected String getMethodMethodName() {
        return METHOD_NAME;
    }

    public FieldTable getClientProperties() {
        return this.clientProperties;
    }    

    public ShortString getMechanism() {
        return this.mechanism;
    }    

    public FieldTable getResponse() {
        return this.response;
    }    

    public ShortString getLocale() {
        return this.locale;
    }    


    @Override
    protected Transmittable[] getTransmittables() {
        return all(clientProperties, mechanism, response, locale);
    }

}

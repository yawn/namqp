package de.eeriedaily.namqp.v08.handler;

import de.eeriedaily.namqp.v08.ClientException;
import de.eeriedaily.namqp.v08.Configuration;
import de.eeriedaily.namqp.v08.framing.Frame;
import de.eeriedaily.namqp.v08.framing.MethodBody;
import de.eeriedaily.namqp.v08.methods.Method;
import de.eeriedaily.namqp.v08.methods.channel.ChannelOpen;
import de.eeriedaily.namqp.v08.methods.connection.*;
import de.eeriedaily.namqp.v08.types.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.*;

import java.util.LinkedHashMap;
import java.util.Map;

import static de.eeriedaily.namqp.v08.framing.Frames.methodFrame;
import static org.jboss.netty.buffer.ChannelBuffers.wrappedBuffer;

/**
 * @author Joern Barthel <joern.barthel@acm.org>
 */
@ChannelHandler.Sharable
public class HandshakeHandler extends SimpleChannelUpstreamHandler {

    public class HandshakeSequenceDeviationException extends ClientException {

        private Method method;

        public HandshakeSequenceDeviationException(Method method) {
            this.method = method;
        }

        @Override
        public String toString() {
            return "HandshakeSequenceDeviationException{" +
                    "method=" + method +
                    "} " + super.toString();
        }

    }

    private static final Log log = LogFactory.getLog(HandshakeHandler.class);

    private final Configuration configuration;

    public HandshakeHandler(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {

        if (log.isDebugEnabled())
            log.debug("Sending AMQP protocol frame");

        ChannelBuffer header = wrappedBuffer(new byte[]{'A', 'M', 'Q', 'P',
                1, 1, 8, 0});

        ctx.getChannel().write(header).addListener(ChannelFutureListener.CLOSE_ON_FAILURE);

        new FrameEncoder(ctx.getPipeline());

    }

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {

        Frame frame = (Frame) e.getMessage();

        MethodBody body = frame.getFrameBody();
        Method method = body.getMethod();

        if (log.isDebugEnabled())
            log.debug(String.format("Receiving '%s'", method.getMethodClassAndMethodName()));

        if (method instanceof ConnectionStart)
            connectionStart(ctx, (ConnectionStart) method);
        else if (method instanceof ConnectionTune)
            connectionTune(ctx, (ConnectionTune) method);
        else
            throw new HandshakeSequenceDeviationException(method);

    }

    protected void connectionStart(ChannelHandlerContext ctx, ConnectionStart method) {

        if (log.isDebugEnabled())
            log.debug("Sending 'connection.start-ok'");

        Map<ShortString, FieldTableValueType> p = new LinkedHashMap<ShortString, FieldTableValueType>();
        p.put(new ShortString("information"), new LongString("http://github.com/yawn/namqp"));
        p.put(new ShortString("platform"), new LongString("Java/Netty"));
        p.put(new ShortString("version"), new LongString("0.1"));

        Map<ShortString, FieldTableValueType> login = new LinkedHashMap<ShortString, FieldTableValueType>();
        login.put(new ShortString("LOGIN"), new LongString(configuration.getLogin()));
        login.put(new ShortString("PASSWORD"), new LongString(configuration.getPassword()));

        FieldTable loginFT = new FieldTable(login);

        write(ctx, methodFrame(new ConnectionStartOk(new FieldTable(p),
                new ShortString("AMQPLAIN"),
                loginFT,
                new ShortString("en_US"))));

    }

    protected void connectionTune(final ChannelHandlerContext ctx, ConnectionTune method) {

        if (log.isDebugEnabled())
            log.debug("Sending 'connection.tune-ok'");

        write(ctx, methodFrame(new ConnectionTuneOk(method.getChannelMax(),
                method.getFrameMax(),
                new UnsignedShort(configuration.getHeartbeat())))).addListener(new ChannelFutureListener() {

            public void operationComplete(ChannelFuture future) throws Exception {
                connectionOpen(ctx);
            }

        });

    }

    protected void connectionOpen(final ChannelHandlerContext ctx) {

        if (log.isDebugEnabled())
            log.debug("Sending 'connection.open'");

        write(ctx, methodFrame(new ConnectionOpen(new ShortString(configuration.getVirtualHost()),
                new ShortString(""),
                new Bit(true)))).addListener(new ChannelFutureListener() {

            public void operationComplete(ChannelFuture future) throws Exception {
                handshakeComplete(ctx);
            }

        });

    }

    protected void handshakeComplete(ChannelHandlerContext ctx) throws Exception {

        if (log.isInfoEnabled())
            log.info("Handshake complete");

        // handshake complete, remove handler from handler
        ctx.getPipeline().remove(this);

    }

    protected ChannelFuture write(ChannelHandlerContext ctx, Frame frame) {
        return ctx.getChannel().write(frame);
    }

}
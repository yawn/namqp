package de.eeriedaily.namqp.v08.handler;

import de.eeriedaily.namqp.v08.ClientException;
import de.eeriedaily.namqp.v08.Configuration;
import de.eeriedaily.namqp.v08.framing.Frame;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.netty.channel.ChannelHandler;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.util.HashedWheelTimer;
import org.jboss.netty.util.Timeout;
import org.jboss.netty.util.TimerTask;

import java.util.concurrent.TimeUnit;

import static de.eeriedaily.namqp.v08.framing.Frames.heartbeatFrame;

/**
 * @author Joern Barthel <joern.barthel@acm.org>
 */
@ChannelHandler.Sharable
public class HeartbeatHandler extends AbstractFrameUpstreamHandler {

    public class MissedHeartbeatException extends ClientException {
    }

    private static final Log log = LogFactory.getLog(HeartbeatHandler.class);

    private final Configuration configuration;
    private final HashedWheelTimer timer = new HashedWheelTimer(1, TimeUnit.SECONDS);

    private volatile boolean ping = true;
    private volatile Timeout currentTimeout;

    public HeartbeatHandler(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public void channelBound(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {

        if (configuration.getHeartbeat() > 0)
            startTimer();

        ctx.sendUpstream(e);
    
    }

    protected void startTimer() {

        if (currentTimeout != null)
            currentTimeout.cancel();

        currentTimeout = timer.newTimeout(new TimerTask() {

            public void run(Timeout timeout) throws Exception {

                if (!timeout.isCancelled())
                    throw new MissedHeartbeatException();

            }

        }, configuration.getHeartbeat() + 1, TimeUnit.SECONDS);

    }

    @Override
    public void frameReceived(Frame frame, ChannelHandlerContext ctx, MessageEvent e) {

        if (frame.isHeartbeatFrame()) {

            startTimer();

            if (log.isDebugEnabled())
                log.debug(ping ? "ping" : "pong");

            ping = !ping;

            ctx.getChannel().write(heartbeatFrame());
            
        } else {
            ctx.sendUpstream(e);
        }

    }

}

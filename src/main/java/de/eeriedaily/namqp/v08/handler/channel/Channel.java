package de.eeriedaily.namqp.v08.handler.channel;

import com.google.common.base.Function;
import com.google.common.collect.MapMaker;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ValueFuture;
import de.eeriedaily.namqp.v08.framing.*;
import de.eeriedaily.namqp.v08.handler.FrameUpstreamHandler;
import de.eeriedaily.namqp.v08.methods.Method;
import de.eeriedaily.namqp.v08.types.UnsignedShort;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author Joern Barthel <joern.barthel@acm.org>
 */
public class Channel implements FrameUpstreamHandler {

    private static final Log log = LogFactory.getLog(Channel.class);

    private final UnsignedShort channel;
    private final ChannelDelegation channelDelegation;

    private final Queue<Reference<ContentListener>> contentListeners =
            new ConcurrentLinkedQueue<Reference<ContentListener>>();

    private final Map<Class<? extends Method>, Queue<Reference<ValueFuture<Method>>>> methodListeners = new MapMaker()
            .makeComputingMap(new Function<Class<? extends Method>, Queue<Reference<ValueFuture<Method>>>>() {

                public Queue<Reference<ValueFuture<Method>>> apply(Class<? extends Method> from) {
                    return new ConcurrentLinkedQueue<Reference<ValueFuture<Method>>>();
                }

            });

    private volatile ContentHeaderPayload lastContentHeaderPayload;

    public Channel(UnsignedShort channel, ChannelDelegation channelDelegation) {
        this.channel = channel;
        this.channelDelegation = channelDelegation;
    }

    public void frameReceived(Frame frame, ChannelHandlerContext ctx, MessageEvent e) {

        if (log.isDebugEnabled())
            log.debug(String.format("Got some data on channel '%s': '%s'", channel, frame));

        if (frame.isMethodFrame()) {

            MethodPayload payload = frame.getPayload();
            Method method = payload.getMethod();

            Reference<ValueFuture<Method>> futureReference;

            while ((futureReference = methodListeners.get(method.getClass()).poll()) != null) {

                ValueFuture<Method> future = futureReference.get();

                if (future != null)
                    future.set(method);

            }

        } else if (frame.isContentHeaderFrame()) {

            lastContentHeaderPayload = frame.getPayload();

        } else if (frame.isContentBodyFrame()) {

            ContentBodyPayload bodyPayload = frame.getPayload();
            Content content = new Content(lastContentHeaderPayload, bodyPayload);

            for (Reference<ContentListener> reference : contentListeners) {

                ContentListener listener = reference.get();

                if (listener != null)
                    listener.contentReceived(channel, content);

            }

        }

    }

    public void addContentListener(ContentListener contentListener) {
        contentListeners.add(new WeakReference<ContentListener>(contentListener));
    }

    public <T extends Method> ListenableFuture<T> addMethodListener(Class<T> method) {

        ValueFuture<T> future = ValueFuture.create();
        methodListeners.get(method).add((Reference)new WeakReference<ValueFuture<T>>(future));

        return future;

    }

    public ChannelFuture write(Payload payload) {

        Frame frame = new Frame(payload.getFrameId(),
                channel,
                payload);

        return channelDelegation.write(frame);

    }

    public ChannelFuture write(Method payload) {
        return write(new MethodPayload(payload));
    }

    public <T extends Method> ListenableFuture<T> write(Payload payload, Class<T> method) {

        ListenableFuture<T> future = addMethodListener(method);
        write(payload);

        return future;

    }

    public <T extends Method> ListenableFuture<T> write(Method payload, Class<T> method) {
        return write(new MethodPayload(payload), method);
    }

}

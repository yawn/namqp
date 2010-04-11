package de.eeriedaily.namqp.v08.framing;

import de.eeriedaily.namqp.v08.methods.Method;
import de.eeriedaily.namqp.v08.types.UnsignedShort;

/**
 * @author Joern Barthel <joern.barthel@acm.org>
 */
public final class Frames {

    private static final Frame HEARTBEAT_FRAME = new Frame(HeartbeatBody.FRAME_ID,
                Frame.ADMIN_CHANNEL,
                new HeartbeatBody());

    private Frames() {
    }

    public static Frame heartbeatFrame() {
        return HEARTBEAT_FRAME;
    }

    public static Frame methodFrame(Method method) {
        return methodFrame(Frame.ADMIN_CHANNEL, method);
    }

    public static Frame methodFrame(UnsignedShort channel, Method method) {
        return new Frame(MethodBody.FRAME_ID,
                channel,
                new MethodBody(method));
    }

}

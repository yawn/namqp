package de.eeriedaily.namqp.v08.framing;

/**
 * @author Joern Barthel <joern.barthel@acm.org>
 */
public final class Frames {

    private static final Frame HEARTBEAT_FRAME = new Frame(HeartbeatPayload.FRAME_ID,
                Frame.ADMIN_CHANNEL,
                new HeartbeatPayload());

    private Frames() {
    }

    public static Frame heartbeatFrame() {
        return HEARTBEAT_FRAME;
    }

}

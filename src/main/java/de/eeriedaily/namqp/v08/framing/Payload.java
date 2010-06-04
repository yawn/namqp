package de.eeriedaily.namqp.v08.framing;

import de.eeriedaily.namqp.v08.Transmittable;
import de.eeriedaily.namqp.v08.types.Octet;

/**
 * @author Joern Barthel <joern.barthel@acm.org>
 */
public interface Payload extends Transmittable {

    public Octet getFrameId();

}

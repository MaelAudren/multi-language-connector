package org.ow2.proactive.procci.model.infrastructure.action;

import java.util.HashSet;

import org.ow2.proactive.procci.model.infrastructure.constants.Identifiers;
import org.ow2.proactive.procci.model.metamodel.Attribute;

/**
 * Created by mael on 3/4/16.
 */

/**
 * Change a Compute instance state for Inactive
 */
public final class StopCompute extends ComputeAction {

    private static StopCompute STOP_COMPUTE = new StopCompute();

    private StopCompute() {
        super(Identifiers.COMPUTE_ACTION_SCHEME, Identifiers.STOP, Identifiers.STOP,
                new HashSet<Attribute>());
    }

    public static StopCompute getInstance() {
        return STOP_COMPUTE;
    }
}
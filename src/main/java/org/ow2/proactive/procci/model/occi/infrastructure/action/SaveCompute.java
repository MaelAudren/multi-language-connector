package org.ow2.proactive.procci.model.occi.infrastructure.action;

import java.util.HashSet;

import org.ow2.proactive.procci.model.occi.infrastructure.constants.Identifiers;
import org.ow2.proactive.procci.model.occi.metamodel.Attribute;

/**
 * Created by the Activeeon Team on 3/4/16.
 */

/**
 * Change a Compute instance state for Active
 */
public final class SaveCompute extends ComputeAction {

    private static SaveCompute SAVE_COMPUTE = new SaveCompute();

    private SaveCompute() {
        super(Identifiers.COMPUTE_ACTION_SCHEME, Identifiers.SAVE, Identifiers.SAVE,
                new HashSet<Attribute>());
    }

    public static SaveCompute getInstance() {
        return SAVE_COMPUTE;
    }
}

package org.ow2.proactive.procci.service.transformer;


import org.ow2.proactive.procci.model.InstanceModel;
import org.ow2.proactive.procci.model.cloud.automation.Model;
import org.ow2.proactive.procci.model.occi.infrastructure.Compute;
import org.springframework.stereotype.Component;

import static org.ow2.proactive.procci.model.occi.infrastructure.constants.InfrastructureAttributes.ARCHITECTURE_NAME;
import static org.ow2.proactive.procci.model.occi.infrastructure.constants.InfrastructureAttributes.COMPUTE_STATE_NAME;
import static org.ow2.proactive.procci.model.occi.infrastructure.constants.InfrastructureAttributes.CORES_NAME;
import static org.ow2.proactive.procci.model.occi.infrastructure.constants.InfrastructureAttributes.HOSTNAME_NAME;
import static org.ow2.proactive.procci.model.occi.infrastructure.constants.InfrastructureAttributes.MEMORY_NAME;
import static org.ow2.proactive.procci.model.occi.infrastructure.constants.InfrastructureAttributes.SHARE_NAME;
import static org.ow2.proactive.procci.model.occi.infrastructure.constants.InfrastructureIdentifiers.COMPUTE_SCHEME;
import static org.ow2.proactive.procci.model.occi.metamodel.constants.MetamodelAttributes.ENTITY_TITLE_NAME;
import static org.ow2.proactive.procci.model.occi.metamodel.constants.MetamodelAttributes.ID_NAME;
import static org.ow2.proactive.procci.model.occi.metamodel.constants.MetamodelAttributes.SUMMARY_NAME;

@Component
public class ComputeTransformer extends TransformerProvider {

    public TransformerType getType() {
        return TransformerType.COMPUTE;
    }

    /**
     * Convert OCCI compute to Proactive Cloud Automation Compute
     *
     * @param actionType is the action to apply on the compute
     * @return the proactive cloud automation model for the compute
     */
    @Override
    public Model toCloudAutomationModel(InstanceModel instanceModel, String actionType) {

        Compute compute = castInstanceModel(Compute.class, instanceModel);

        Model.Builder serviceBuilder = new Model.Builder(COMPUTE_SCHEME, actionType)
                .addVariable(ID_NAME, compute.getId());
        compute.getTitle().ifPresent(title -> serviceBuilder.addVariable(ENTITY_TITLE_NAME, title));
        compute.getSummary().ifPresent(summary -> serviceBuilder.addVariable(SUMMARY_NAME, summary));
        compute.getArchitecture().ifPresent(archi -> serviceBuilder.addVariable(ARCHITECTURE_NAME, archi));
        compute.getCores().ifPresent(coresNumber -> serviceBuilder.addVariable(CORES_NAME, coresNumber));
        compute.getMemory().ifPresent(memoryNumber -> serviceBuilder.addVariable(MEMORY_NAME, memoryNumber));
        compute.getShare().ifPresent(shareNumber -> serviceBuilder.addVariable(SHARE_NAME, shareNumber));
        compute.getHostname().ifPresent(host -> serviceBuilder.addVariable(HOSTNAME_NAME, host));
        compute.getState().ifPresent(
                currentState -> serviceBuilder.addVariable(COMPUTE_STATE_NAME, currentState));

        compute.getMixins().forEach(mixin -> mixin.toCloudAutomationModel(serviceBuilder));

        return serviceBuilder.build();
    }
}

package org.ow2.proactive.procci.model.occi.infrastructure;

import java.io.IOException;

import org.ow2.proactive.procci.model.exception.CloudAutomationException;
import org.ow2.proactive.procci.model.exception.SyntaxException;
import org.ow2.proactive.procci.model.occi.infrastructure.constants.InfrastructureKinds;
import org.ow2.proactive.procci.model.occi.infrastructure.state.NetworkState;
import org.ow2.proactive.procci.model.occi.metamodel.ProviderMixin;
import org.ow2.proactive.procci.request.CloudAutomationVariables;
import com.google.common.truth.Truth;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static com.google.common.truth.Truth.assertThat;

/**
 * Created by mael on 2/25/16.
 */

public class NetworkInterfaceTest {

    @Mock
    CloudAutomationVariables cloudAutomationVariables;
    @Mock
    private ProviderMixin providerMixin;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void maximalConstructorTest() throws IOException, CloudAutomationException {
        Compute compute = new ComputeBuilder(providerMixin, cloudAutomationVariables).url(
                "url:compute").build();
        try {
            NetworkInterface networkInterface = new NetworkInterface.Builder(compute, "url:target", "mac",
                    "linktarget")
                    .url("url:networkinterface")
                    .title("titleTest")
                    .state(NetworkState.ACTIVE)
                    .targetKind(InfrastructureKinds.STORAGE)
                    .build();

            Truth.assertThat(networkInterface.getTitle().get()).isEqualTo("titleTest");
            Truth.assertThat(networkInterface.getState()).isEquivalentAccordingToCompareTo(
                    NetworkState.ACTIVE);
            Truth.assertThat(networkInterface.getTarget().toString()).isEqualTo("url:target");
            assertThat(networkInterface.getMac()).isEqualTo("mac");
            Truth.assertThat(networkInterface.getSource()).isEqualTo(compute);
            Truth.assertThat(networkInterface.getId().toString()).contains("url:networkinterface");
            assertThat(networkInterface.getLinkInterface()).isEqualTo("linktarget");
        } catch (SyntaxException e) {
            e.printStackTrace();
        }
    }
}

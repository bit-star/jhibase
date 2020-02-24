package com.lazulite.base.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.lazulite.base.web.rest.TestUtil;

public class ServiceWindowTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ServiceWindow.class);
        ServiceWindow serviceWindow1 = new ServiceWindow();
        serviceWindow1.setId(1L);
        ServiceWindow serviceWindow2 = new ServiceWindow();
        serviceWindow2.setId(serviceWindow1.getId());
        assertThat(serviceWindow1).isEqualTo(serviceWindow2);
        serviceWindow2.setId(2L);
        assertThat(serviceWindow1).isNotEqualTo(serviceWindow2);
        serviceWindow1.setId(null);
        assertThat(serviceWindow1).isNotEqualTo(serviceWindow2);
    }
}

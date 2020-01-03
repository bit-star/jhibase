package com.lazulite.base.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.lazulite.base.web.rest.TestUtil;

public class LocationVMTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(LocationVM.class);
        LocationVM locationVM1 = new LocationVM();
        locationVM1.setId(1L);
        LocationVM locationVM2 = new LocationVM();
        locationVM2.setId(locationVM1.getId());
        assertThat(locationVM1).isEqualTo(locationVM2);
        locationVM2.setId(2L);
        assertThat(locationVM1).isNotEqualTo(locationVM2);
        locationVM1.setId(null);
        assertThat(locationVM1).isNotEqualTo(locationVM2);
    }
}

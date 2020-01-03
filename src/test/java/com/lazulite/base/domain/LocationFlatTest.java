package com.lazulite.base.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.lazulite.base.web.rest.TestUtil;

public class LocationFlatTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(LocationFlat.class);
        LocationFlat locationFlat1 = new LocationFlat();
        locationFlat1.setId(1L);
        LocationFlat locationFlat2 = new LocationFlat();
        locationFlat2.setId(locationFlat1.getId());
        assertThat(locationFlat1).isEqualTo(locationFlat2);
        locationFlat2.setId(2L);
        assertThat(locationFlat1).isNotEqualTo(locationFlat2);
        locationFlat1.setId(null);
        assertThat(locationFlat1).isNotEqualTo(locationFlat2);
    }
}

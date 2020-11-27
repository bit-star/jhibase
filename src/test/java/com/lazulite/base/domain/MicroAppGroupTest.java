package com.lazulite.base.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.lazulite.base.web.rest.TestUtil;

public class MicroAppGroupTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MicroAppGroup.class);
        MicroAppGroup microAppGroup1 = new MicroAppGroup();
        microAppGroup1.setId(1L);
        MicroAppGroup microAppGroup2 = new MicroAppGroup();
        microAppGroup2.setId(microAppGroup1.getId());
        assertThat(microAppGroup1).isEqualTo(microAppGroup2);
        microAppGroup2.setId(2L);
        assertThat(microAppGroup1).isNotEqualTo(microAppGroup2);
        microAppGroup1.setId(null);
        assertThat(microAppGroup1).isNotEqualTo(microAppGroup2);
    }
}

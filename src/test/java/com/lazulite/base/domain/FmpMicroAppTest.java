package com.lazulite.base.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.lazulite.base.web.rest.TestUtil;

public class FmpMicroAppTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FmpMicroApp.class);
        FmpMicroApp fmpMicroApp1 = new FmpMicroApp();
        fmpMicroApp1.setId(1L);
        FmpMicroApp fmpMicroApp2 = new FmpMicroApp();
        fmpMicroApp2.setId(fmpMicroApp1.getId());
        assertThat(fmpMicroApp1).isEqualTo(fmpMicroApp2);
        fmpMicroApp2.setId(2L);
        assertThat(fmpMicroApp1).isNotEqualTo(fmpMicroApp2);
        fmpMicroApp1.setId(null);
        assertThat(fmpMicroApp1).isNotEqualTo(fmpMicroApp2);
    }
}

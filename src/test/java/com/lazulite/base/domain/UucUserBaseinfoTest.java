package com.lazulite.base.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.lazulite.base.web.rest.TestUtil;

public class UucUserBaseinfoTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UucUserBaseinfo.class);
        UucUserBaseinfo uucUserBaseinfo1 = new UucUserBaseinfo();
        uucUserBaseinfo1.setId(1L);
        UucUserBaseinfo uucUserBaseinfo2 = new UucUserBaseinfo();
        uucUserBaseinfo2.setId(uucUserBaseinfo1.getId());
        assertThat(uucUserBaseinfo1).isEqualTo(uucUserBaseinfo2);
        uucUserBaseinfo2.setId(2L);
        assertThat(uucUserBaseinfo1).isNotEqualTo(uucUserBaseinfo2);
        uucUserBaseinfo1.setId(null);
        assertThat(uucUserBaseinfo1).isNotEqualTo(uucUserBaseinfo2);
    }
}

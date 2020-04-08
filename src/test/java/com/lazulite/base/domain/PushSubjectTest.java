package com.lazulite.base.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.lazulite.base.web.rest.TestUtil;

public class PushSubjectTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PushSubject.class);
        PushSubject pushSubject1 = new PushSubject();
        pushSubject1.setId(1L);
        PushSubject pushSubject2 = new PushSubject();
        pushSubject2.setId(pushSubject1.getId());
        assertThat(pushSubject1).isEqualTo(pushSubject2);
        pushSubject2.setId(2L);
        assertThat(pushSubject1).isNotEqualTo(pushSubject2);
        pushSubject1.setId(null);
        assertThat(pushSubject1).isNotEqualTo(pushSubject2);
    }
}

package com.lazulite.base.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.lazulite.base.web.rest.TestUtil;

public class MsgReceiverGroupTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MsgReceiverGroup.class);
        MsgReceiverGroup msgReceiverGroup1 = new MsgReceiverGroup();
        msgReceiverGroup1.setId(1L);
        MsgReceiverGroup msgReceiverGroup2 = new MsgReceiverGroup();
        msgReceiverGroup2.setId(msgReceiverGroup1.getId());
        assertThat(msgReceiverGroup1).isEqualTo(msgReceiverGroup2);
        msgReceiverGroup2.setId(2L);
        assertThat(msgReceiverGroup1).isNotEqualTo(msgReceiverGroup2);
        msgReceiverGroup1.setId(null);
        assertThat(msgReceiverGroup1).isNotEqualTo(msgReceiverGroup2);
    }
}

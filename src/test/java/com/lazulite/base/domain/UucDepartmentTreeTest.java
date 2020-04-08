package com.lazulite.base.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.lazulite.base.web.rest.TestUtil;

public class UucDepartmentTreeTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UucDepartmentTree.class);
        UucDepartmentTree uucDepartmentTree1 = new UucDepartmentTree();
        uucDepartmentTree1.setId(1L);
        UucDepartmentTree uucDepartmentTree2 = new UucDepartmentTree();
        uucDepartmentTree2.setId(uucDepartmentTree1.getId());
        assertThat(uucDepartmentTree1).isEqualTo(uucDepartmentTree2);
        uucDepartmentTree2.setId(2L);
        assertThat(uucDepartmentTree1).isNotEqualTo(uucDepartmentTree2);
        uucDepartmentTree1.setId(null);
        assertThat(uucDepartmentTree1).isNotEqualTo(uucDepartmentTree2);
    }
}

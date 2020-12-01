package com.project.contrader.votomj.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.project.contrader.votomj.web.rest.TestUtil;

public class VotoTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Voto.class);
        Voto voto1 = new Voto();
        voto1.setId(1L);
        Voto voto2 = new Voto();
        voto2.setId(voto1.getId());
        assertThat(voto1).isEqualTo(voto2);
        voto2.setId(2L);
        assertThat(voto1).isNotEqualTo(voto2);
        voto1.setId(null);
        assertThat(voto1).isNotEqualTo(voto2);
    }
}

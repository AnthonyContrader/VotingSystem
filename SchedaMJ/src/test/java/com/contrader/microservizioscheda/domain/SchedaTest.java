package com.contrader.microservizioscheda.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.contrader.microservizioscheda.web.rest.TestUtil;

public class SchedaTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Scheda.class);
        Scheda scheda1 = new Scheda();
        scheda1.setId(1L);
        Scheda scheda2 = new Scheda();
        scheda2.setId(scheda1.getId());
        assertThat(scheda1).isEqualTo(scheda2);
        scheda2.setId(2L);
        assertThat(scheda1).isNotEqualTo(scheda2);
        scheda1.setId(null);
        assertThat(scheda1).isNotEqualTo(scheda2);
    }
}

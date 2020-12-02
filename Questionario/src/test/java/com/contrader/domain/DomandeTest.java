package com.contrader.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.contrader.web.rest.TestUtil;

public class DomandeTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Domande.class);
        Domande domande1 = new Domande();
        domande1.setId(1L);
        Domande domande2 = new Domande();
        domande2.setId(domande1.getId());
        assertThat(domande1).isEqualTo(domande2);
        domande2.setId(2L);
        assertThat(domande1).isNotEqualTo(domande2);
        domande1.setId(null);
        assertThat(domande1).isNotEqualTo(domande2);
    }
}

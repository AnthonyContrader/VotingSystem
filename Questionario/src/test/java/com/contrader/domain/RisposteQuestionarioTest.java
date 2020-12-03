package com.contrader.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.contrader.web.rest.TestUtil;

public class RisposteQuestionarioTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RisposteQuestionario.class);
        RisposteQuestionario risposteQuestionario1 = new RisposteQuestionario();
        risposteQuestionario1.setId(1L);
        RisposteQuestionario risposteQuestionario2 = new RisposteQuestionario();
        risposteQuestionario2.setId(risposteQuestionario1.getId());
        assertThat(risposteQuestionario1).isEqualTo(risposteQuestionario2);
        risposteQuestionario2.setId(2L);
        assertThat(risposteQuestionario1).isNotEqualTo(risposteQuestionario2);
        risposteQuestionario1.setId(null);
        assertThat(risposteQuestionario1).isNotEqualTo(risposteQuestionario2);
    }
}

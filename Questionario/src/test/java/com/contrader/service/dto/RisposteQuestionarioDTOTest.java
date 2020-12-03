package com.contrader.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.contrader.web.rest.TestUtil;

public class RisposteQuestionarioDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RisposteQuestionarioDTO.class);
        RisposteQuestionarioDTO risposteQuestionarioDTO1 = new RisposteQuestionarioDTO();
        risposteQuestionarioDTO1.setId(1L);
        RisposteQuestionarioDTO risposteQuestionarioDTO2 = new RisposteQuestionarioDTO();
        assertThat(risposteQuestionarioDTO1).isNotEqualTo(risposteQuestionarioDTO2);
        risposteQuestionarioDTO2.setId(risposteQuestionarioDTO1.getId());
        assertThat(risposteQuestionarioDTO1).isEqualTo(risposteQuestionarioDTO2);
        risposteQuestionarioDTO2.setId(2L);
        assertThat(risposteQuestionarioDTO1).isNotEqualTo(risposteQuestionarioDTO2);
        risposteQuestionarioDTO1.setId(null);
        assertThat(risposteQuestionarioDTO1).isNotEqualTo(risposteQuestionarioDTO2);
    }
}

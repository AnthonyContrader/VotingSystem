package com.contrader.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.contrader.web.rest.TestUtil;

public class QuestionarioDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(QuestionarioDTO.class);
        QuestionarioDTO questionarioDTO1 = new QuestionarioDTO();
        questionarioDTO1.setId(1L);
        QuestionarioDTO questionarioDTO2 = new QuestionarioDTO();
        assertThat(questionarioDTO1).isNotEqualTo(questionarioDTO2);
        questionarioDTO2.setId(questionarioDTO1.getId());
        assertThat(questionarioDTO1).isEqualTo(questionarioDTO2);
        questionarioDTO2.setId(2L);
        assertThat(questionarioDTO1).isNotEqualTo(questionarioDTO2);
        questionarioDTO1.setId(null);
        assertThat(questionarioDTO1).isNotEqualTo(questionarioDTO2);
    }
}

package com.contrader.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.contrader.web.rest.TestUtil;

public class VotoDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(VotoDTO.class);
        VotoDTO votoDTO1 = new VotoDTO();
        votoDTO1.setId(1L);
        VotoDTO votoDTO2 = new VotoDTO();
        assertThat(votoDTO1).isNotEqualTo(votoDTO2);
        votoDTO2.setId(votoDTO1.getId());
        assertThat(votoDTO1).isEqualTo(votoDTO2);
        votoDTO2.setId(2L);
        assertThat(votoDTO1).isNotEqualTo(votoDTO2);
        votoDTO1.setId(null);
        assertThat(votoDTO1).isNotEqualTo(votoDTO2);
    }
}

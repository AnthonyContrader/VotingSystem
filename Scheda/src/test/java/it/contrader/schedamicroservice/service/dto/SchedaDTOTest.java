package it.contrader.schedamicroservice.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import it.contrader.schedamicroservice.web.rest.TestUtil;

public class SchedaDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SchedaDTO.class);
        SchedaDTO schedaDTO1 = new SchedaDTO();
        schedaDTO1.setId(1L);
        SchedaDTO schedaDTO2 = new SchedaDTO();
        assertThat(schedaDTO1).isNotEqualTo(schedaDTO2);
        schedaDTO2.setId(schedaDTO1.getId());
        assertThat(schedaDTO1).isEqualTo(schedaDTO2);
        schedaDTO2.setId(2L);
        assertThat(schedaDTO1).isNotEqualTo(schedaDTO2);
        schedaDTO1.setId(null);
        assertThat(schedaDTO1).isNotEqualTo(schedaDTO2);
    }
}

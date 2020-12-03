package com.contrader.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.contrader.web.rest.TestUtil;

public class NotificaTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Notifica.class);
        Notifica notifica1 = new Notifica();
        notifica1.setId(1L);
        Notifica notifica2 = new Notifica();
        notifica2.setId(notifica1.getId());
        assertThat(notifica1).isEqualTo(notifica2);
        notifica2.setId(2L);
        assertThat(notifica1).isNotEqualTo(notifica2);
        notifica1.setId(null);
        assertThat(notifica1).isNotEqualTo(notifica2);
    }
}

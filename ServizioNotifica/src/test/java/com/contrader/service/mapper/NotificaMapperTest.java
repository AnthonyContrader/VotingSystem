package com.contrader.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class NotificaMapperTest {

    private NotificaMapper notificaMapper;

    @BeforeEach
    public void setUp() {
        notificaMapper = new NotificaMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(notificaMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(notificaMapper.fromId(null)).isNull();
    }
}

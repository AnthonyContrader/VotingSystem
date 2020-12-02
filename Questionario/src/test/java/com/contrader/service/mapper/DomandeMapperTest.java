package com.contrader.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DomandeMapperTest {

    private DomandeMapper domandeMapper;

    @BeforeEach
    public void setUp() {
        domandeMapper = new DomandeMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(domandeMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(domandeMapper.fromId(null)).isNull();
    }
}

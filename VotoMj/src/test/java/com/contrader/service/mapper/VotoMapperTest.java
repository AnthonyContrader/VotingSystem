package com.contrader.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class VotoMapperTest {

    private VotoMapper votoMapper;

    @BeforeEach
    public void setUp() {
        votoMapper = new VotoMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(votoMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(votoMapper.fromId(null)).isNull();
    }
}

package com.contrader.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class RisposteQuestionarioMapperTest {

    private RisposteQuestionarioMapper risposteQuestionarioMapper;

    @BeforeEach
    public void setUp() {
        risposteQuestionarioMapper = new RisposteQuestionarioMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(risposteQuestionarioMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(risposteQuestionarioMapper.fromId(null)).isNull();
    }
}

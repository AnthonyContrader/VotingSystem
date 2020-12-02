package com.contrader.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class QuestionarioMapperTest {

    private QuestionarioMapper questionarioMapper;

    @BeforeEach
    public void setUp() {
        questionarioMapper = new QuestionarioMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(questionarioMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(questionarioMapper.fromId(null)).isNull();
    }
}

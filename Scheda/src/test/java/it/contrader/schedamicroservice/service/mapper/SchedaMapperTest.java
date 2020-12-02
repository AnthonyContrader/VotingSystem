package it.contrader.schedamicroservice.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class SchedaMapperTest {

    private SchedaMapper schedaMapper;

    @BeforeEach
    public void setUp() {
        schedaMapper = new SchedaMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(schedaMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(schedaMapper.fromId(null)).isNull();
    }
}

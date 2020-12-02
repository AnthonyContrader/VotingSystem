package com.contrader.service.mapper;


import com.contrader.domain.*;
import com.contrader.service.dto.DomandeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Domande} and its DTO {@link DomandeDTO}.
 */
@Mapper(componentModel = "spring", uses = {QuestionarioMapper.class})
public interface DomandeMapper extends EntityMapper<DomandeDTO, Domande> {

    @Mapping(source = "questionario.id", target = "questionarioId")
    DomandeDTO toDto(Domande domande);

    @Mapping(source = "questionarioId", target = "questionario")
    Domande toEntity(DomandeDTO domandeDTO);

    default Domande fromId(Long id) {
        if (id == null) {
            return null;
        }
        Domande domande = new Domande();
        domande.setId(id);
        return domande;
    }
}

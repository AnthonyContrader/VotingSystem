package com.contrader.service.mapper;


import com.contrader.domain.*;
import com.contrader.service.dto.QuestionarioDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Questionario} and its DTO {@link QuestionarioDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface QuestionarioMapper extends EntityMapper<QuestionarioDTO, Questionario> {


    @Mapping(target = "domandes", ignore = true)
    @Mapping(target = "removeDomande", ignore = true)
    Questionario toEntity(QuestionarioDTO questionarioDTO);

    default Questionario fromId(Long id) {
        if (id == null) {
            return null;
        }
        Questionario questionario = new Questionario();
        questionario.setId(id);
        return questionario;
    }
}

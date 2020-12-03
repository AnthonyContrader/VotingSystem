package com.contrader.service.mapper;


import com.contrader.domain.*;
import com.contrader.service.dto.RisposteQuestionarioDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link RisposteQuestionario} and its DTO {@link RisposteQuestionarioDTO}.
 */
@Mapper(componentModel = "spring", uses = {DomandeMapper.class})
public interface RisposteQuestionarioMapper extends EntityMapper<RisposteQuestionarioDTO, RisposteQuestionario> {

    @Mapping(source = "domande.id", target = "domandeId")
    RisposteQuestionarioDTO toDto(RisposteQuestionario risposteQuestionario);

    @Mapping(source = "domandeId", target = "domande")
    RisposteQuestionario toEntity(RisposteQuestionarioDTO risposteQuestionarioDTO);

    default RisposteQuestionario fromId(Long id) {
        if (id == null) {
            return null;
        }
        RisposteQuestionario risposteQuestionario = new RisposteQuestionario();
        risposteQuestionario.setId(id);
        return risposteQuestionario;
    }
}

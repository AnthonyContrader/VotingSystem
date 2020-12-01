package com.project.contrader.votomj.service.mapper;


import com.project.contrader.votomj.domain.*;
import com.project.contrader.votomj.service.dto.VotoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Voto} and its DTO {@link VotoDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface VotoMapper extends EntityMapper<VotoDTO, Voto> {



    default Voto fromId(Long id) {
        if (id == null) {
            return null;
        }
        Voto voto = new Voto();
        voto.setId(id);
        return voto;
    }
}

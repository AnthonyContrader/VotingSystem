package com.contrader.microservizioscheda.service.mapper;


import com.contrader.microservizioscheda.domain.*;
import com.contrader.microservizioscheda.service.dto.SchedaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Scheda} and its DTO {@link SchedaDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SchedaMapper extends EntityMapper<SchedaDTO, Scheda> {



    default Scheda fromId(Long id) {
        if (id == null) {
            return null;
        }
        Scheda scheda = new Scheda();
        scheda.setId(id);
        return scheda;
    }
}

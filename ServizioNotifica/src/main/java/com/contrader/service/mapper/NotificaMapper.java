package com.contrader.service.mapper;


import com.contrader.domain.*;
import com.contrader.service.dto.NotificaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Notifica} and its DTO {@link NotificaDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface NotificaMapper extends EntityMapper<NotificaDTO, Notifica> {



    default Notifica fromId(Long id) {
        if (id == null) {
            return null;
        }
        Notifica notifica = new Notifica();
        notifica.setId(id);
        return notifica;
    }
}

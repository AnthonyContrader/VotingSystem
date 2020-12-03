package com.contrader.service.impl;

import com.contrader.service.NotificaService;
import com.contrader.domain.Notifica;
import com.contrader.repository.NotificaRepository;
import com.contrader.service.dto.NotificaDTO;
import com.contrader.service.mapper.NotificaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Notifica}.
 */
@Service
@Transactional
public class NotificaServiceImpl implements NotificaService {

    private final Logger log = LoggerFactory.getLogger(NotificaServiceImpl.class);

    private final NotificaRepository notificaRepository;

    private final NotificaMapper notificaMapper;

    public NotificaServiceImpl(NotificaRepository notificaRepository, NotificaMapper notificaMapper) {
        this.notificaRepository = notificaRepository;
        this.notificaMapper = notificaMapper;
    }

    @Override
    public NotificaDTO save(NotificaDTO notificaDTO) {
        log.debug("Request to save Notifica : {}", notificaDTO);
        Notifica notifica = notificaMapper.toEntity(notificaDTO);
        notifica = notificaRepository.save(notifica);
        return notificaMapper.toDto(notifica);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<NotificaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Notificas");
        return notificaRepository.findAll(pageable)
            .map(notificaMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<NotificaDTO> findOne(Long id) {
        log.debug("Request to get Notifica : {}", id);
        return notificaRepository.findById(id)
            .map(notificaMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Notifica : {}", id);
        notificaRepository.deleteById(id);
    }
}

package com.contrader.service.impl;

import com.contrader.service.DomandeService;
import com.contrader.domain.Domande;
import com.contrader.repository.DomandeRepository;
import com.contrader.service.dto.DomandeDTO;
import com.contrader.service.mapper.DomandeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Domande}.
 */
@Service
@Transactional
public class DomandeServiceImpl implements DomandeService {

    private final Logger log = LoggerFactory.getLogger(DomandeServiceImpl.class);

    private final DomandeRepository domandeRepository;

    private final DomandeMapper domandeMapper;

    public DomandeServiceImpl(DomandeRepository domandeRepository, DomandeMapper domandeMapper) {
        this.domandeRepository = domandeRepository;
        this.domandeMapper = domandeMapper;
    }

    @Override
    public DomandeDTO save(DomandeDTO domandeDTO) {
        log.debug("Request to save Domande : {}", domandeDTO);
        Domande domande = domandeMapper.toEntity(domandeDTO);
        domande = domandeRepository.save(domande);
        return domandeMapper.toDto(domande);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<DomandeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Domandes");
        return domandeRepository.findAll(pageable)
            .map(domandeMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<DomandeDTO> findOne(Long id) {
        log.debug("Request to get Domande : {}", id);
        return domandeRepository.findById(id)
            .map(domandeMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Domande : {}", id);
        domandeRepository.deleteById(id);
    }
}

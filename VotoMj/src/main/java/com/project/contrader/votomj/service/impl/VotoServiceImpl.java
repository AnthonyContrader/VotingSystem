package com.project.contrader.votomj.service.impl;

import com.project.contrader.votomj.service.VotoService;
import com.project.contrader.votomj.domain.Voto;
import com.project.contrader.votomj.repository.VotoRepository;
import com.project.contrader.votomj.service.dto.VotoDTO;
import com.project.contrader.votomj.service.mapper.VotoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Voto}.
 */
@Service
@Transactional
public class VotoServiceImpl implements VotoService {

    private final Logger log = LoggerFactory.getLogger(VotoServiceImpl.class);

    private final VotoRepository votoRepository;

    private final VotoMapper votoMapper;

    public VotoServiceImpl(VotoRepository votoRepository, VotoMapper votoMapper) {
        this.votoRepository = votoRepository;
        this.votoMapper = votoMapper;
    }

    @Override
    public VotoDTO save(VotoDTO votoDTO) {
        log.debug("Request to save Voto : {}", votoDTO);
        Voto voto = votoMapper.toEntity(votoDTO);
        voto = votoRepository.save(voto);
        return votoMapper.toDto(voto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<VotoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Votos");
        return votoRepository.findAll(pageable)
            .map(votoMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<VotoDTO> findOne(Long id) {
        log.debug("Request to get Voto : {}", id);
        return votoRepository.findById(id)
            .map(votoMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Voto : {}", id);
        votoRepository.deleteById(id);
    }
}

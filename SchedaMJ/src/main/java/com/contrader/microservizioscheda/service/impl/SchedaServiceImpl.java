package com.contrader.microservizioscheda.service.impl;

import com.contrader.microservizioscheda.service.SchedaService;
import com.contrader.microservizioscheda.domain.Scheda;
import com.contrader.microservizioscheda.repository.SchedaRepository;
import com.contrader.microservizioscheda.service.dto.SchedaDTO;
import com.contrader.microservizioscheda.service.mapper.SchedaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Scheda}.
 */
@Service
@Transactional
public class SchedaServiceImpl implements SchedaService {

    private final Logger log = LoggerFactory.getLogger(SchedaServiceImpl.class);

    private final SchedaRepository schedaRepository;

    private final SchedaMapper schedaMapper;

    public SchedaServiceImpl(SchedaRepository schedaRepository, SchedaMapper schedaMapper) {
        this.schedaRepository = schedaRepository;
        this.schedaMapper = schedaMapper;
    }

    @Override
    public SchedaDTO save(SchedaDTO schedaDTO) {
        log.debug("Request to save Scheda : {}", schedaDTO);
        Scheda scheda = schedaMapper.toEntity(schedaDTO);
        scheda = schedaRepository.save(scheda);
        return schedaMapper.toDto(scheda);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<SchedaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Schedas");
        return schedaRepository.findAll(pageable)
            .map(schedaMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<SchedaDTO> findOne(Long id) {
        log.debug("Request to get Scheda : {}", id);
        return schedaRepository.findById(id)
            .map(schedaMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Scheda : {}", id);
        schedaRepository.deleteById(id);
    }
    
    @Transactional(readOnly = true)
    public List<SchedaDTO> findAll(){
    	log.debug("Request to get all Schedas");
    	List<Scheda> list = schedaRepository.findAll(); 
        return schedaMapper.toDto(list);
            
    	
    }
}

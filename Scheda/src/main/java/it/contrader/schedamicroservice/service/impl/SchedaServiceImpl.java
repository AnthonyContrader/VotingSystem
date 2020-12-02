package it.contrader.schedamicroservice.service.impl;

import it.contrader.schedamicroservice.service.SchedaService;
import it.contrader.schedamicroservice.domain.Scheda;
import it.contrader.schedamicroservice.repository.SchedaRepository;
import it.contrader.schedamicroservice.service.dto.SchedaDTO;
import it.contrader.schedamicroservice.service.mapper.SchedaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public List<SchedaDTO> findAll() {
        log.debug("Request to get all Schedas");
        return schedaRepository.findAll().stream()
            .map(schedaMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
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
}

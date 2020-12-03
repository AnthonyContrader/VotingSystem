package com.contrader.service.impl;

import com.contrader.service.RisposteQuestionarioService;
import com.contrader.domain.RisposteQuestionario;
import com.contrader.repository.RisposteQuestionarioRepository;
import com.contrader.service.dto.RisposteQuestionarioDTO;
import com.contrader.service.mapper.RisposteQuestionarioMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link RisposteQuestionario}.
 */
@Service
@Transactional
public class RisposteQuestionarioServiceImpl implements RisposteQuestionarioService {

    private final Logger log = LoggerFactory.getLogger(RisposteQuestionarioServiceImpl.class);

    private final RisposteQuestionarioRepository risposteQuestionarioRepository;

    private final RisposteQuestionarioMapper risposteQuestionarioMapper;

    public RisposteQuestionarioServiceImpl(RisposteQuestionarioRepository risposteQuestionarioRepository, RisposteQuestionarioMapper risposteQuestionarioMapper) {
        this.risposteQuestionarioRepository = risposteQuestionarioRepository;
        this.risposteQuestionarioMapper = risposteQuestionarioMapper;
    }

    @Override
    public RisposteQuestionarioDTO save(RisposteQuestionarioDTO risposteQuestionarioDTO) {
        log.debug("Request to save RisposteQuestionario : {}", risposteQuestionarioDTO);
        RisposteQuestionario risposteQuestionario = risposteQuestionarioMapper.toEntity(risposteQuestionarioDTO);
        risposteQuestionario = risposteQuestionarioRepository.save(risposteQuestionario);
        return risposteQuestionarioMapper.toDto(risposteQuestionario);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<RisposteQuestionarioDTO> findAll(Pageable pageable) {
        log.debug("Request to get all RisposteQuestionarios");
        return risposteQuestionarioRepository.findAll(pageable)
            .map(risposteQuestionarioMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<RisposteQuestionarioDTO> findOne(Long id) {
        log.debug("Request to get RisposteQuestionario : {}", id);
        return risposteQuestionarioRepository.findById(id)
            .map(risposteQuestionarioMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete RisposteQuestionario : {}", id);
        risposteQuestionarioRepository.deleteById(id);
    }
}

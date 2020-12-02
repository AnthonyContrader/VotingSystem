package com.contrader.service.impl;

import com.contrader.service.QuestionarioService;
import com.contrader.domain.Questionario;
import com.contrader.repository.QuestionarioRepository;
import com.contrader.service.dto.QuestionarioDTO;
import com.contrader.service.mapper.QuestionarioMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Questionario}.
 */
@Service
@Transactional
public class QuestionarioServiceImpl implements QuestionarioService {

    private final Logger log = LoggerFactory.getLogger(QuestionarioServiceImpl.class);

    private final QuestionarioRepository questionarioRepository;

    private final QuestionarioMapper questionarioMapper;

    public QuestionarioServiceImpl(QuestionarioRepository questionarioRepository, QuestionarioMapper questionarioMapper) {
        this.questionarioRepository = questionarioRepository;
        this.questionarioMapper = questionarioMapper;
    }

    @Override
    public QuestionarioDTO save(QuestionarioDTO questionarioDTO) {
        log.debug("Request to save Questionario : {}", questionarioDTO);
        Questionario questionario = questionarioMapper.toEntity(questionarioDTO);
        questionario = questionarioRepository.save(questionario);
        return questionarioMapper.toDto(questionario);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<QuestionarioDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Questionarios");
        return questionarioRepository.findAll(pageable)
            .map(questionarioMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<QuestionarioDTO> findOne(Long id) {
        log.debug("Request to get Questionario : {}", id);
        return questionarioRepository.findById(id)
            .map(questionarioMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Questionario : {}", id);
        questionarioRepository.deleteById(id);
    }
}

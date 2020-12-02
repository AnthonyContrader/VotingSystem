package com.contrader.service.impl;

import com.contrader.service.VotoService;
import com.contrader.domain.Voto;
import com.contrader.repository.VotoRepository;
import com.contrader.service.dto.VotoDTO;
import com.contrader.service.mapper.VotoMapper;
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
    
    @Transactional(readOnly = true)
    public boolean checkUser(Long id_utente, Long id_scheda) {
    	log.debug("Request to check user : {}", id_utente, id_scheda);
    	
    	Voto voto = votoRepository.findByIdutenteAndIdscheda(id_utente, id_scheda);
    	
    	if(voto != null) {
			
			return true;//utente ha già votato
		}
		return false;//utente deve ancora votare
  
    }
    
    @Transactional(readOnly = true)
    public double[] getStatistica(Long id_scheda) {
    	double[] ar = new double[3];
    	log.debug("Request to get the number of votes for a scheda : {}", id_scheda);
    	ar[0] = (double) votoRepository.countVoto(id_scheda, (long) 1);
    	ar[1] = (double) votoRepository.countVoto(id_scheda, (long) 2);
    	ar[2] = (double) votoRepository.countVoto(id_scheda, (long) 3);
    	return ar;
    }
}

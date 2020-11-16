package it.contrader.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import it.contrader.converter.SchedaVotazioneConverter;
import it.contrader.dao.SchedaVotazioneRepository;
import it.contrader.dto.SchedaVotazioneDTO;
import it.contrader.model.SchedaVotazione;

@Service
public class SchedaVotazioneService extends AbstractService<SchedaVotazione, SchedaVotazioneDTO> {

	@Autowired
	private SchedaVotazioneConverter converter;
	@Autowired
	private SchedaVotazioneRepository crudRepository;

}

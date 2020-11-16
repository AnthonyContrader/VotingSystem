package it.contrader.converter;

import org.springframework.stereotype.Component;

import it.contrader.dto.SchedaVotazioneDTO;
import it.contrader.model.SchedaVotazione;


@Component
public class SchedaVotazioneConverter extends AbstractConverter<SchedaVotazione, SchedaVotazioneDTO> {
	
	public SchedaVotazione toEntity(SchedaVotazioneDTO schedaDTO) {
		SchedaVotazione scheda = null;
		if (schedaDTO != null) {
			scheda = new SchedaVotazione(schedaDTO.getId(), schedaDTO.getTitolo(), schedaDTO.getDomanda(), schedaDTO.getRisposta1(), schedaDTO.getRisposta2(), schedaDTO.getRisposta3() );
		}
		return scheda;
	}

	public SchedaVotazioneDTO toDTO(SchedaVotazione scheda) {
		SchedaVotazioneDTO schedaDTO = null;
		if (scheda != null) {
			schedaDTO = new SchedaVotazioneDTO(scheda.getId(), scheda.getTitolo(), scheda.getDomanda(), scheda.getRisposta1(), scheda.getRisposta2(), scheda.getRisposta3() );

		}
		return schedaDTO;
	}

}


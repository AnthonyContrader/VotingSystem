package it.contrader.converter;

import org.springframework.stereotype.Component;

import it.contrader.dto.UtenteVotanteDTO;
import it.contrader.model.UtenteVotante;

@Component
public class UtenteVotanteConverter extends AbstractConverter<UtenteVotante, UtenteVotanteDTO> {
	
	public UtenteVotante toEntity(UtenteVotanteDTO utentevDTO) {
		UtenteVotante utentev = null;
		if (utentevDTO != null) {
			utentev = new UtenteVotante(utentevDTO.getId(), utentevDTO.getId_utente(), utentevDTO.getId_scheda(), utentevDTO.getVoto());
		}
		return utentev;
	}

	public UtenteVotanteDTO toDTO(UtenteVotante utentev) {
		UtenteVotanteDTO utentevDTO = null;
		if (utentev != null) {
			utentevDTO = new UtenteVotanteDTO(utentev.getId(), utentev.getId_utente(), utentev.getId_scheda(), utentev.getVoto());

		}
		return utentevDTO;
	}

	public boolean checkUser(UtenteVotante utentev) {
		if(utentev != null) {
			return true;
		}
		return false;
	}
	
	
}


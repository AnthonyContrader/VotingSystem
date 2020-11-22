package it.contrader.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import it.contrader.converter.UtenteVotanteConverter;
import it.contrader.dao.UtenteVotanteRepository;
import it.contrader.dto.UtenteVotanteDTO;
import it.contrader.model.UtenteVotante;

@Service
public class UtenteVotanteService extends AbstractService<UtenteVotante, UtenteVotanteDTO> {

	@Autowired
	private UtenteVotanteConverter converter;
	@Autowired
	private UtenteVotanteRepository repository;

	public boolean checkUser(int id_scheda, int id_utente) {
		return converter.checkUser(repository.findByIdutenteAndIdscheda(id_utente, id_scheda));
	}
	
	public double[] getStatistica(int id_scheda) {
		double[] ar = new double[3];
		ar[0] = (double)repository.countVoto(id_scheda, 1);
		ar[1] = (double)repository.countVoto(id_scheda, 2);
		ar[2] = (double)repository.countVoto(id_scheda, 3);
		return  ar;

	}

}

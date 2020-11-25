package it.contrader.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.contrader.dto.SchedaVotazioneDTO;
import it.contrader.dto.UtenteVotanteDTO;
import it.contrader.service.SchedaVotazioneService;
import it.contrader.service.UtenteVotanteService;


@RestController
@RequestMapping("/utentevotante")
@CrossOrigin(origins = "http://localhost:4200")
public class UtenteVotanteController extends AbstractController<UtenteVotanteDTO> {
	
	@Autowired
	private UtenteVotanteService service;
	
	@Autowired
	private SchedaVotazioneService servicescheda;
	
	
	@GetMapping(value = "/control")
	public SchedaVotazioneDTO control(@RequestParam("id_scheda") int id_scheda, @RequestParam("id_utente") int id_utente) {	

		boolean check = service.checkUser(id_scheda, id_utente);
		if(check == false) {
			SchedaVotazioneDTO s = (SchedaVotazioneDTO) servicescheda.read(id_scheda);
			return s;
			
			
		}
		return null;
	}
	
	@GetMapping(value = "/statistica")
	public double[] statistica(@RequestParam("id_scheda") int id_scheda) {
		double[] risultati = service.getStatistica(id_scheda);
		double totale = risultati[0] + risultati[1] + risultati[2];
		risultati[0] = (risultati[0]/totale)*100;
		risultati[1] = (risultati[1]/totale)*100;
		risultati[2] = (risultati[2]/totale)*100;
		return risultati;
		
	}
	
	@GetMapping(value = "/voti")
	public double[] voti(@RequestParam("id_scheda") int id_scheda) {
		double[] voti = service.getStatistica(id_scheda);
		return voti;
	}

}

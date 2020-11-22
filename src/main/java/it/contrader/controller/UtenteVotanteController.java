package it.contrader.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.contrader.dto.SchedaVotazioneDTO;
import it.contrader.dto.UserDTO;
import it.contrader.dto.UtenteVotanteDTO;
import it.contrader.service.SchedaVotazioneService;
import it.contrader.service.UtenteVotanteService;

@Controller
@RequestMapping("/utentevotante")
public class UtenteVotanteController {
	
	@Autowired
	private UtenteVotanteService service;
	
	@Autowired
	private SchedaVotazioneService servicescheda;
	
	
	@GetMapping("/getall")
	public String getAll(HttpServletRequest request) {
		setAll(request);
		return "ListaVotanti";
	}
	
	@PostMapping("/insert")
	public String insert(HttpServletRequest request,
		@RequestParam("id_scheda") int id_scheda, @RequestParam("voto") int voto ) {
		UserDTO u = (UserDTO) request.getSession().getAttribute("user");
		int id_utente = u.getId();
		UtenteVotanteDTO dto = new UtenteVotanteDTO();
		dto.setId_scheda(id_scheda);
		dto.setId_utente(id_utente);
		dto.setVoto(voto);
		service.insert(dto);
		setAllschede(request);
		return "homeuser";
	}
	
	@GetMapping("/control")
	public String control(HttpServletRequest request, @RequestParam("id_scheda") int id_scheda) {
		UserDTO u = (UserDTO) request.getSession().getAttribute("user");
		int id_utente = u.getId();
		boolean check = service.checkUser(id_scheda, id_utente);
		if(check) {
			SchedaVotazioneDTO s = (SchedaVotazioneDTO) servicescheda.read(id_scheda);
			request.setAttribute("scheda", s);
			
		}
		request.setAttribute("check", check);
		return "VotazioneView";
	}
	
	@GetMapping("/statistica")
	public String statistica(HttpServletRequest request, @RequestParam("id_scheda") int id_scheda) {
		SchedaVotazioneDTO s = servicescheda.read(id_scheda);
		double[] risultati = service.getStatistica(id_scheda);
		double totale = risultati[0] + risultati[1] + risultati[2];
		risultati[0] = (risultati[0]/totale)*100;
		risultati[1] = (risultati[1]/totale)*100;
		risultati[2] = (risultati[2]/totale)*100;
		request.setAttribute("risposta1", s.getRisposta1());
		request.setAttribute("risposta2", s.getRisposta2());
		request.setAttribute("risposta3", s.getRisposta3());
		request.setAttribute("risultati", risultati);
		request.setAttribute("id_scheda", id_scheda);
		return "/scheda/schedastat";
		
	}
	
	private void setAll(HttpServletRequest request) {
		request.setAttribute("list", service.getAll());
	}
	
	private void setAllschede(HttpServletRequest request) {
		request.setAttribute("list", servicescheda.getAll());
	}
}

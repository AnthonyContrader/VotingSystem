package it.contrader.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.contrader.dto.SchedaVotazioneDTO;
import it.contrader.service.SchedaVotazioneService;


@Controller
@RequestMapping("/schedavotazione")
public class SchedaVotazioneController {
	
	@Autowired
	private SchedaVotazioneService service;
	
	@GetMapping("/getall")
	public String getAll(HttpServletRequest request) {
		setAll(request);
		return "/scheda/schedamanager";
	}

	@GetMapping("/delete")
	public String delete(HttpServletRequest request, @RequestParam("id_scheda") int id_scheda) {
		service.delete(id_scheda);
		setAll(request);
		return "/scheda/schedamanager";
	}

	@GetMapping("/preupdate")
	public String preUpdate(HttpServletRequest request, @RequestParam("id_scheda") int id_scheda) {
		request.setAttribute("dto", service.read(id_scheda));
		return "/scheda/schedaupdate";
	}

	@PostMapping("/update")
	public String update(HttpServletRequest request, @RequestParam("id_scheda") int id_scheda, @RequestParam("titolo") String titolo,
			@RequestParam("domanda") String domanda, @RequestParam("risposta1") String risposta1, @RequestParam("risposta2") String risposta2,
			@RequestParam("risposta3") String risposta3) {

		SchedaVotazioneDTO dto = new SchedaVotazioneDTO();
		dto.setId(id_scheda);
		dto.setTitolo(titolo);
		dto.setDomanda(domanda);
		dto.setRisposta1(risposta1);
		dto.setRisposta2(risposta2);
		dto.setRisposta3(risposta3);
		service.update(dto);
		setAll(request);
		return "/scheda/schedamanager";

	}

	@PostMapping("/insert")
	public String insert(HttpServletRequest request, @RequestParam("titolo") String titolo,
		@RequestParam("domanda") String domanda, @RequestParam("risposta1") String risposta1, @RequestParam("risposta2") String risposta2,
		@RequestParam("risposta3") String risposta3) {
		System.out.println("t1");
		SchedaVotazioneDTO dto = new SchedaVotazioneDTO();
		dto.setTitolo(titolo);
		dto.setDomanda(domanda);
		dto.setRisposta1(risposta1);
		dto.setRisposta2(risposta2);
		dto.setRisposta3(risposta3);
		System.out.println("t2");
		service.insert(dto);
		setAll(request);
		
		return "/scheda/schedamanager";
	}

	@GetMapping("/read")
	public String read(HttpServletRequest request, @RequestParam("id_scheda") int id_scheda) {
		request.setAttribute("dto", service.read(id_scheda));
		return "/scheda/readscheda";
	}

	private void setAll(HttpServletRequest request) {
		request.setAttribute("list", service.getAll());
	}

	
}

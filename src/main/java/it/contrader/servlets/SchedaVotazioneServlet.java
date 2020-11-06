package it.contrader.servlets;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import it.contrader.dto.SchedaVotazioneDTO;
import it.contrader.service.Service;
import it.contrader.service.SchedaVotazioneService;


public class SchedaVotazioneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public SchedaVotazioneServlet() {	
	}
	
	public void updateList(HttpServletRequest request) {
		Service<SchedaVotazioneDTO> service = new SchedaVotazioneService();
		List<SchedaVotazioneDTO>listDTO = service.getAll();
		request.setAttribute("list", listDTO);
	}
	
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Service<SchedaVotazioneDTO> service = new SchedaVotazioneService();
		SchedaVotazioneDTO scheda = new SchedaVotazioneDTO();
		String mode = (String) request.getParameter("mode");
		SchedaVotazioneDTO dto;
		int id_scheda;
		boolean ans;
		
		switch (mode.toUpperCase()) {
		
		case "SCHEDELIST":
			updateList(request);
			getServletContext().getRequestDispatcher("/scheda/schedamanager.jsp").forward(request, response);
			break;
			
		case "READ":
			id_scheda = Integer.parseInt(request.getParameter("id_scheda"));
			dto = service.read(id_scheda);
			request.setAttribute("dto", dto);
			
			if (request.getParameter("update") == null) {
				 getServletContext().getRequestDispatcher("/scheda/readscheda.jsp").forward(request, response);
				
			}
			
			else getServletContext().getRequestDispatcher("/scheda/schedaupdate.jsp").forward(request, response);
			
			break;	
			
		case "INSERT":
			String titolo = request.getParameter("titolo");
			String domanda = request.getParameter("domanda");
			String risposta1 = request.getParameter("risposta1");
			String risposta2 = request.getParameter("risposta2");
			String risposta3 = request.getParameter("risposta3");
			dto = new SchedaVotazioneDTO(titolo,domanda,risposta1, risposta2, risposta3);
			ans = service.insert(dto);
			request.setAttribute("ans", ans);
			updateList(request);
			getServletContext().getRequestDispatcher("/scheda/schedamanager.jsp").forward(request, response);
			break;
			
			
		case "UPDATE":
			titolo = request.getParameter("titolo");
			domanda = request.getParameter("domanda");
			risposta1 = request.getParameter("risposta1");
			risposta2 = request.getParameter("risposta2");
			risposta3 = request.getParameter("risposta3");
			id_scheda = Integer.parseInt(request.getParameter("id_scheda"));
			dto = new SchedaVotazioneDTO(id_scheda,titolo,domanda,risposta1, risposta2, risposta3);
			ans = service.update(dto);
			updateList(request);
			getServletContext().getRequestDispatcher("/scheda/schedamanager.jsp").forward(request, response);
			break;	
			
		case "DELETE":
			id_scheda = Integer.parseInt(request.getParameter("id_scheda"));
			ans = service.delete(id_scheda);
			request.setAttribute("ans", ans);
			updateList(request);
			getServletContext().getRequestDispatcher("/scheda/schedamanager.jsp").forward(request, response);
			break;	
			
		case "BACK":
			List<SchedaVotazioneDTO>listDTO = service.getAll();
			request.setAttribute("list", listDTO);
			getServletContext().getRequestDispatcher("/homeuser.jsp").forward(request, response);
			break;
		
		}
		
	
	
	}
}


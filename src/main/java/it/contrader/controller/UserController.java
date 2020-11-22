package it.contrader.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import it.contrader.dto.UserDTO;
import it.contrader.service.SchedaVotazioneService;
import it.contrader.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService service;
	
	@Autowired
	private SchedaVotazioneService schedaService;

	@PostMapping("/login")
	public String login(HttpServletRequest request, @RequestParam(value = "username", required = true) String username,
			@RequestParam(value = "password", required = true) String password) {

		UserDTO userDTO = (UserDTO) service.findByUsernameAndPassword(username, password);
		
		if(userDTO != null) {
		
			String tipo = userDTO.getUsertype();
			request.getSession().setAttribute("user", userDTO);

		
			switch (tipo) {

			case "ADMIN":
				return "homeadmin";

			case "USER":			
				request.setAttribute("list", schedaService.getAll());
				return "homeuser";

			default:
				return "index";
			}
		}
		
		return "index";
	}

	@GetMapping("/getall")
	public String getAll(HttpServletRequest request) {
		setAll(request);
		return "/user/users";
	}

	@GetMapping("/delete")
	public String delete(HttpServletRequest request, @RequestParam("id") int id) {
		service.delete(id);
		setAll(request);
		return "/user/users";
	}

	@GetMapping("/preupdate")
	public String preUpdate(HttpServletRequest request, @RequestParam("id") int id) {
		request.setAttribute("dto", service.read(id));
		return "/user/updateuser";
	}

	@PostMapping("/update")
	public String update(HttpServletRequest request, @RequestParam("id") int id, @RequestParam("username") String username,
			@RequestParam("password") String password, @RequestParam("usertype") String usertype) {

		UserDTO dto = new UserDTO();
		dto.setId(id);
		dto.setUsername(username);
		dto.setPassword(password);
		dto.setUsertype(usertype);
		service.update(dto);
		setAll(request);
		return "/user/users";

	}

	@PostMapping("/insert")
	public String insert(HttpServletRequest request, @RequestParam("username") String username,
			@RequestParam("password") String password, @RequestParam("usertype") String usertype) {
		UserDTO dto = new UserDTO();
		dto.setUsername(username);
		dto.setPassword(password);
		dto.setUsertype(usertype);
		service.insert(dto);
		setAll(request);
		return "/user/users";
	}

	@GetMapping("/read")
	public String read(HttpServletRequest request, @RequestParam("id") int id) {
		request.setAttribute("dto", service.read(id));
		return "/user/readuser";
	}
	
	@GetMapping("/home")
	public String home(HttpServletRequest request) {
		
		UserDTO u = (UserDTO) request.getSession().getAttribute("user");
		if(u.getUsertype().equals("ADMIN"))			
			return "homeadmin";
		setAllschede(request);
		return "homeuser";
	}
	


	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return "index";
	}

	private void setAll(HttpServletRequest request) {
		request.setAttribute("list", service.getAll());
	}
	
	private void setAllschede(HttpServletRequest request) {
		request.setAttribute("list", schedaService.getAll());
	}
	
}

package it.contrader.service;

import java.util.List;

/**
 * Questa interfaccia firma i metodi dei Service. Osservare che, a differenza di
 * AbstactService, dipende solo dal parametro DTO.
 * 
 * @author Vittorio Valent & Girolamo Murdaca
 *
 * @param <DTO>
 * 
 * @see AbstractService.
 */
public interface ServiceDTO<DTO> {
	
	public List<DTO> getAll();

	public DTO read(int id);
	
	public DTO insert (DTO dto);
	
	public DTO update (DTO dto);
	
	public void delete (int id);
}
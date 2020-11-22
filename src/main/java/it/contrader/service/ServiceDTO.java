package it.contrader.service;

import java.util.List;

public interface ServiceDTO<DTO> {

	public List<DTO> getAll();

	public DTO read(int id);

	public DTO insert(DTO dto);

	public DTO update(DTO dto);

	public void delete(int id);

}

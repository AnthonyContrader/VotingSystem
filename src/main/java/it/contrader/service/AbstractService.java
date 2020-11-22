package it.contrader.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import it.contrader.service.ServiceDTO;
import it.contrader.converter.Converter;

@Service
public abstract class AbstractService<Entity, DTO> implements ServiceDTO<DTO> {

	@Autowired
	protected CrudRepository<Entity, Integer> crudRepository;
	@Autowired
	protected Converter<Entity, DTO> converter;

	public AbstractService() {
	}

	public DTO insert(DTO dto) {
		return converter.toDTO(crudRepository.save(converter.toEntity(dto)));
	}

	public List<DTO> getAll() {
		List<DTO> list = new ArrayList<DTO>();
		Iterator<DTO> iterate = converter.toDTOList(crudRepository.findAll()).iterator();
		while(iterate.hasNext()) {
			list.add(iterate.next());
		}
		
		return list;
	}

	public DTO read(int id) {
		return converter.toDTO(crudRepository.findById(id).get());
	}

	public DTO update(DTO dto) {
		return converter.toDTO(crudRepository.save(converter.toEntity(dto)));
	}

	public void delete(int id) {
		crudRepository.deleteById(id);
	}

}
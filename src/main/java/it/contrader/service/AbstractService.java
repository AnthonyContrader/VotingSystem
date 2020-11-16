package it.contrader.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.repository.CrudRepository;

import it.contrader.converter.Converter;


/**
 * Questa classe astratta implementa tutti i metodi CRUD firmati in ServiceDTO.
 * Il converter agisce due volte nei metodi  insert e update per avere sia come input che come output
 * un oggetto DTO.
 * 
 * @author Vittorio Valent & Girolamo Murdaca
 *
 * @param <Entity>
 * @param <DTO>
 * 
 * @see ServiceDTO
 */
public abstract class AbstractService<Entity,DTO> implements ServiceDTO<DTO> {
	
	@Autowired
	protected CrudRepository<Entity,Integer> crudRepository;
	@Autowired
	protected Converter<Entity,DTO> converter;
	
	@Override
	public DTO insert(DTO dto) {
		return converter.toDTO(crudRepository.save(converter.toEntity(dto)));
	}

	@Override
	public List<DTO> getAll() {
		List<DTO> list = new ArrayList<DTO>();
		Iterator<DTO> iterate = converter.toDTOList(crudRepository.findAll()).iterator();
		while(iterate.hasNext()) {
			list.add(iterate.next());
		}
		
		return list;
	}

	@Override
	public DTO read(int id) {
		return converter.toDTO(crudRepository.findById(id).get());
	}

	@Override
	public DTO update(DTO dto) {
		return converter.toDTO(crudRepository.save(converter.toEntity(dto)));
	}

	@Override
	public void delete(int id) {
		crudRepository.deleteById(id);
	}
}
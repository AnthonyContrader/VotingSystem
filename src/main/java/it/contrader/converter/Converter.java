package it.contrader.converter;



public interface Converter<Entity, DTO> {

	public Entity toEntity(DTO dto);

	public DTO toDTO(Entity entity);

	public Iterable<DTO> toDTOList(Iterable<Entity> entityList);

}
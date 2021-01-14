package br.com.sebastiao.junior.infrastructure.mapper;

public interface Mapper<E, D> {

	D toDto(E entity);
	
	E toEntity(D dto);
}

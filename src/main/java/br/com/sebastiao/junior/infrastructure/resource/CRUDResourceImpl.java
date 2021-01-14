package br.com.sebastiao.junior.infrastructure.resource;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import br.com.sebastiao.junior.common.constants.Constants;
import br.com.sebastiao.junior.common.jpa.entity.GenericEntity;
import br.com.sebastiao.junior.infrastructure.exception.BusinessException;
import br.com.sebastiao.junior.infrastructure.mapper.Mapper;
import br.com.sebastiao.junior.infrastructure.service.CRUDService;

public class CRUDResourceImpl<D, E extends GenericEntity<I>, S extends CRUDService<E, I>, M extends Mapper<E, D>, I>
		implements CRUDResource<D, I> {
	
	private final Logger log = LoggerFactory.getLogger(super.getClass());

	private S service;

	private M mapper;

	public CRUDResourceImpl(S service, M mapper) {
		super();
		this.service = service;
		this.mapper = mapper;
	}

	@Override
	public List<D> findAll() {
		return service.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
	}

	@Override
	public D findById(I id) {
		return mapper.toDto(service.find(id));
	}

	@Override
	public D insert(D dto) {
		return mapper.toDto(service.insert(mapper.toEntity(dto)));
	}

	@Override
	public D update(D dto) {
		return mapper.toDto(service.update(mapper.toEntity(dto)));
	}

	@Override
	public void delete(I id) {
		this.service.delete(id);
	}

	public S getService() {
		return service;
	}

	@ExceptionHandler({ BusinessException.class })
	public ResponseEntity<Object> handleBusinessException(BusinessException ex, WebRequest request) {
		return ResponseEntity.badRequest().body(ex.getMessage());
	}

	@ExceptionHandler({ Exception.class })
	public ResponseEntity<Object> handleInternalError(Exception ex, WebRequest request) {
		log.error(Constants.INTERNAL_ERROR, ex);
		return ResponseEntity.badRequest().body(Constants.INTERNAL_ERROR);
	}
}

package br.com.sebastiao.junior.infrastructure.service;


import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import br.com.sebastiao.junior.common.constants.Constants;
import br.com.sebastiao.junior.common.jpa.entity.GenericEntity;
import br.com.sebastiao.junior.infrastructure.exception.BusinessException;

@Transactional
public class CRUDServiceImpl<T extends GenericEntity<I>, R extends JpaRepository<T, I>, I>
		implements CRUDService<T, I> {

	private R repository;

	private List<Consumer<T>> beforeInserts;
	private List<Consumer<T>> afterInserts;

	private List<Consumer<T>> beforeUpdates;
	private List<Consumer<T>> afterUpdates;

	private List<Consumer<T>> beforeDeletes;
	private List<Consumer<T>> afterDeletes;

	public CRUDServiceImpl(R repository) {
		this.repository = repository;
	}

	public void beforeInsert(Consumer<T> onPreInsert) {
		beforeInserts = addEvent(beforeInserts, onPreInsert);
	}

	public void afterInsert(Consumer<T> onPostInsert) {
		afterInserts = addEvent(afterInserts, onPostInsert);
	}

	public void beforeUpdate(Consumer<T> onPreUpdate) {
		beforeUpdates = addEvent(beforeUpdates, onPreUpdate);
	}

	public void afterUpdate(Consumer<T> onPostUpdate) {
		afterUpdates = addEvent(afterUpdates, onPostUpdate);
	}

	public void beforeDelete(Consumer<T> onPreDelete) {
		beforeDeletes = addEvent(beforeDeletes, onPreDelete);
	}

	public void afterDelete(Consumer<T> onPostDelete) {
		afterDeletes = addEvent(afterDeletes, onPostDelete);
	}

	private List<Consumer<T>> addEvent(List<Consumer<T>> eventsList, Consumer<T> event) {
		if (eventsList == null) {
			eventsList = new ArrayList<Consumer<T>>();
		}
		eventsList.add(event);
		return eventsList;
	}

	private void executeConsumers(List<Consumer<T>> consumers, T entity) {
		if (consumers != null) {
			for (Consumer<T> consumer : consumers) {
				consumer.accept(entity);
			}
		}
	}

	@Override
	public List<T> findAll() {
		return repository.findAll();
	}

	@Override
	public T insert(T entity) {

		executeConsumers(beforeInserts, entity);

		entity = repository.save(entity);

		executeConsumers(afterInserts, entity);

		return entity;
	}

	@Override
	public T update(T entity) {
		
		if (Objects.isNull(entity.getId())) {
			
			throw new BusinessException(Constants.ID_CANNOT_BE_NULL);
		}
		
		executeConsumers(beforeUpdates, entity);

		entity = repository.save(entity);

		executeConsumers(afterUpdates, entity);

		return entity;
	}

	@Override
	public void delete(I idEntity) {

		T found = repository.findById(idEntity).orElseThrow(
				() -> new BusinessException(Constants.ENTITY_NOT_FOUND, getEntityClass().getSimpleName(), idEntity));

		executeConsumers(beforeDeletes, found);

		repository.delete(found);

		executeConsumers(afterDeletes, found);
	}

	@Override
	public void delete(List<T> entitys) {
		repository.deleteAll(entitys);
	}

	@Override
	public T find(I primaryKey) {
		return repository.findById(primaryKey).orElse(null);
	}
	
	@Override
	public List<T> find(List<I> primaryKeys) {
		return repository.findAllById(primaryKeys);
	}

	public R getRepository() {
		return repository;
	}

	@SuppressWarnings("unchecked")
	public Class<T> getEntityClass() {
		Type[] actualTypeArguments = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments();
		return (Class<T>) actualTypeArguments[0];
	}
}

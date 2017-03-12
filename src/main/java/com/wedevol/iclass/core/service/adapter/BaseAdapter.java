package com.wedevol.iclass.core.service.adapter;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Currency;
import java.util.List;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.builtin.PassThroughConverter;
import ma.glasnost.orika.impl.DefaultMapperFactory;

/**
 * Service Adapter Base Class
 *
 * @author Charz++
 */
public abstract class BaseAdapter {

	private MapperFacade mapperFacade;

	public BaseAdapter() {
		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().mapNulls(false).build();
		mapperFactory.getConverterFactory()
						.registerConverter(
								new PassThroughConverter(LocalDate.class, LocalDateTime.class, LocalTime.class,
										Currency.class, Date.class));
		defineMappings(mapperFactory);
		mapperFacade = mapperFactory.getMapperFacade();
	}

	protected abstract void defineMappings(MapperFactory mapperFactory);

	public <S, T> S convertTo(T entity, Class<S> toClazz) {
		if (entity == null) {
			return null;
		}
		return mapperFacade.map(entity, toClazz);
	}

	public <S, T> List<S> convertTo(List<T> entities, Class<S> toClazz) {
		if (entities == null) {
			return null;
		}
		return mapperFacade.mapAsList(entities, toClazz);
	}

	public <S, T> void convertTo(T sourceEntity, S destEntity) {
		if (sourceEntity == null) {
			destEntity = null;
		}
		mapperFacade.map(sourceEntity, destEntity);
	}
}

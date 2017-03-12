package com.wedevol.iclass.core.adapter;

import ma.glasnost.orika.MapperFactory;

import com.wedevol.iclass.core.entity.Student;
import com.wedevol.iclass.core.view.request.UserView;

/**
 * Service Adapter Class
 *
 * @author Charz++
 */
public class ServiceAdapter extends BaseAdapter {

	@Override
	protected void defineMappings(MapperFactory mapperFactory) {

		mapperFactory.classMap(Student.class, UserView.class)
						.byDefault()
						.mapNullsInReverse(false)
						.register();

	}

}

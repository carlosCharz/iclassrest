package com.wedevol.iclass.core.util;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public interface DefaultInterface {
	
	default <T> void addIfNotExist(List<T> list, T elem){
		if (!list.contains(elem)){
			list.add(elem);
		}
	}
	
	default boolean isNullOrEmpty(String element){
		return element == null || element.isEmpty();
	}

	default boolean isNullOrEmpty(List<?> element){
		return element == null || element.isEmpty();
	}
	
	default <T> List<T> union(List<T> a, List<T> b){
		a.addAll(b);	
		return a;
	}
	
	default <T> List<T> except(List<T> a, List<T> except){
		List<T> list = new ArrayList<T>(a);
		list.removeAll(except);
		return list;
	}
	
	default <T> List<T> newList(){
		return new ArrayList<T>();
	}
	
	default LocalDateTime now(){
		return ZonedDateTime.now(ZoneOffset.UTC).toLocalDateTime();
	}	
}

package com.wedevol.iclass.core.amazon;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;

/**
 * File Metadata Entity
 * 
 * @author charz
 * 
 */
public final class FileMetadata implements Serializable {

	private static final long serialVersionUID = -880698846815195613L;
	private Map<String, String> map;
	
	public FileMetadata() {
		// Use an initial capacity of 2 elements since the metadata will be used only for width and height
		this.map = new HashMap<String, String>((int)(2 / 0.75) + 1);
	}

	public String add(String key, String value) {
		return map.put(key, value);
	}

	public void addAll(FileMetadata m) {
		map.putAll(m.map);
	}

	public void addAll(Set<java.util.Map.Entry<String, String>> set) {
		set.forEach(e -> map.put(e.getKey(), e.getValue()));
	}

	public void clear() {
		map.clear();
	}

	public boolean containsKey(String key) {
		return map.containsKey(key);
	}

	public void forEach(BiConsumer<String, String> action) {
		map.entrySet().forEach(e -> action.accept(e.getKey(), e.getValue()));
	}

	public String get(String key) {
		return map.get(key);
	}

	public Set<java.util.Map.Entry<String, String>> getAll() {
		return map.entrySet();
	}

	public boolean isEmpty() {
		return map.isEmpty();
	}

	public String remove(String key) {
		return map.remove(key);
	}

	@Override
	public String toString() {
		return map.toString();
	}

}

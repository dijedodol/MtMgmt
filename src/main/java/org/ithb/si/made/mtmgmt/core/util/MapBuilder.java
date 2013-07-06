/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ithb.si.made.mtmgmt.core.util;

import java.util.Map;

/**
 *
 * @author Uyeee
 */
public class MapBuilder<K, V> {

	private final Map<K, V> map;

	public MapBuilder(Map<K, V> storage) {
		this.map = storage;
	}

	public Map<K, V> getMap() {
		return map;
	}

	public MapBuilder<K, V> put(K key, V value) {
		map.put(key, value);
		return this;
	}

	public MapBuilder<K, V> remove(K key) {
		map.remove(key);
		return this;
	}
}

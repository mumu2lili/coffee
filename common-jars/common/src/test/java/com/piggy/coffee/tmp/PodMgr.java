package com.piggy.coffee.tmp;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class PodMgr {

    private ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
	
	public boolean put(String name) {
		String old = map.put(name, name);
		if(old == null) {
			return true;
		} else {
			return false;
		}

	}
	
	public int size() {
		return map.size();
	}
}

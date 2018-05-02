package it.polito.tdp.poweroutages.model;

import java.util.*;

import id.polito.tdp.poweroutages.bean.Nerc;

public class IdMapNerc {
	
	private Map<Integer, Nerc> map;
	
	public IdMapNerc() {
		map = new HashMap<>(); 
	}

	public Nerc get(Nerc nerc) {
		Nerc old = map.get(nerc.getId());
		if(old == null) {
			
			map.put(nerc.getId(), nerc);
			return nerc;
		}
		return old;
	}
	
	public void put(int id, Nerc nerc) {
		map.put(id, nerc);
	}

	public Nerc get(int int1) {
		// TODO Auto-generated method stub
		return map.get(int1);
	}
}

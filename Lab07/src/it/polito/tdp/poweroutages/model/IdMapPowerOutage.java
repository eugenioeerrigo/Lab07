package it.polito.tdp.poweroutages.model;

import java.util.*;

import id.polito.tdp.poweroutages.bean.Nerc;
import id.polito.tdp.poweroutages.bean.PowerOutage;

public class IdMapPowerOutage {
	
	private Map<Integer, PowerOutage> map;
	
	public IdMapPowerOutage() {
		map = new HashMap<>(); 
	}

	public PowerOutage get(PowerOutage po) {
		PowerOutage old = map.get(po.getId());
		if(old == null) {
			
			map.put(po.getId(), po);
			return po;
		}
		return old;
	}
	
	public void put(int id, PowerOutage po) {
		map.put(id, po);
	}

	public PowerOutage get(int id) {
		return map.get(id);
	}

	public Map<Integer, PowerOutage> getMap() {
		return map;
	}
	
	
}

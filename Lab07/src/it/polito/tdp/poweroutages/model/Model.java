package it.polito.tdp.poweroutages.model;

import java.util.List;

import id.polito.tdp.poweroutages.bean.Nerc;
import id.polito.tdp.poweroutages.bean.PowerOutage;
import it.polito.tdp.poweroutages.db.PowerOutageDAO;

public class Model {

	PowerOutageDAO podao;
	
	private IdMapNerc nercMap;
	private IdMapPowerOutage poMap;
	
	private List<PowerOutage> poList;
	
	public Model() {
		podao = new PowerOutageDAO();
		
		nercMap = new IdMapNerc();
		poMap = new IdMapPowerOutage();
		
		poList = podao.getTuttiPO(poMap, nercMap);
		
	}
	
	public List<Nerc> getNercList() {
		return podao.getNercList(nercMap);
	}


	public String worstCaseAnalysis(int anni, int ore, Nerc value) {
		
		return null;
	}

	
	
}

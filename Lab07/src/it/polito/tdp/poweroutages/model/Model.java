package it.polito.tdp.poweroutages.model;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.*;

import id.polito.tdp.poweroutages.bean.Nerc;
import id.polito.tdp.poweroutages.bean.PowerOutage;
import it.polito.tdp.poweroutages.db.PowerOutageDAO;

public class Model {

	PowerOutageDAO podao;
	
	private IdMapNerc nercMap;
	private IdMapPowerOutage poMap;
	
	private List<PowerOutage> risultato;
	private List<PowerOutage> po;
	private List<Nerc> nerc;

	private int best;
	private int hours;
	
	public Model() {
		podao = new PowerOutageDAO();
		
		hours = 0;
		
		nercMap = new IdMapNerc();
		poMap = new IdMapPowerOutage();
		
		nerc = podao.getNercList(nercMap);
		po = podao.getTuttiPO(poMap, nercMap);
		
		for (Nerc n : nerc) 
			podao.getPOFromNerc(poMap, n);
		
	}
	
	public List<Nerc> getNercList() {
		return nerc;
	}
	
	public List<PowerOutage> getPOList(){
		return po;
	}
	
	public Collection<PowerOutage> getPOMap(){
		return poMap.getMap().values();
	}
	
	public Collection<Nerc> getNercMap(){
		return nercMap.getMap().values();
	}

	public String worstCaseAnalysis(int anniMax, int oreMax, Nerc nerc) {
		String sol = "";
		List<PowerOutage> parziale = new ArrayList<>();
		risultato = null;
		best = 0;
		
		//Ordino i PO del nerc in modo da ottenere un parziale già ordinato per anno ed essere agevolati nel check anniMax
		nerc.getPolist().sort(new Comparator<PowerOutage>() {
			@Override
			public int compare(PowerOutage o1, PowerOutage o2) {
				return o1.getBegin().compareTo(o2.getEnd());
			}
		});
		
		this.ricorsiva(0, anniMax, oreMax, nerc, parziale);
		
		if(risultato!=null) {
			
		for(PowerOutage p : risultato)
			hours += ChronoUnit.HOURS.between(p.getBegin(), p.getEnd());
		
			sol += "Tot people affected: "+conteggioPersone(risultato)+"\nTot hours of outage: "+hours+"\n";
			for(PowerOutage po : risultato)
				sol += po.toString();
		}else
			return "Nessun risultato!";
		return sol;
	}

	private void ricorsiva(int livello, int anniMax, int oreMax, Nerc nerc, List<PowerOutage> parziale) {
		
		System.out.println(livello+" "+parziale.toString()+" "+conteggioPersone(parziale)+" "+best);
		
		///if(livello == nerc.getPolist().size()) {
		//	if(controllaParziale(parziale, anniMax, oreMax)){
				if(this.conteggioPersone(parziale)>best) {
					risultato = new ArrayList<>(parziale);
					best = conteggioPersone(parziale);
				}
		
		for(PowerOutage po : nerc.getPolist()) {
			if(!parziale.contains(po)) {
			parziale.add(po);
			
			if(controllaParziale(parziale, anniMax, oreMax))
				this.ricorsiva(livello+1, anniMax, oreMax, nerc, parziale);
			
			parziale.remove(po);
			}
		}
	}

	private boolean controllaParziale(List<PowerOutage> parziale, int anniMax, int oreMax) {
		
//		LocalDateTime bestY = null;
//		LocalDateTime oldY = null;
		int sum = 0;
		
		for(int i=0; i<parziale.size(); i++) {
			sum += (int) ChronoUnit.HOURS.between(parziale.get(i).getBegin(), parziale.get(i).getEnd());
			
//			SOSTITUITO MA FUNZIONANTE
//			LocalDateTime curr = parziale.get(i).getBegin();
//			if(bestY==null) {
//				bestY = curr;
//			}else {
//				if(curr.isBefore(bestY)) 
//					bestY = curr;
//			}
//			
//			if(oldY==null) {
//				oldY = curr;
//			} else if (curr.isAfter(oldY))
//				oldY = curr;
		}
		
		if(sum >oreMax)
			return false;
		
//		a = (int) ChronoUnit.YEARS.between(bestY, oldY);
//		if(a>anniMax)
//			return false;
		
		//Check su anno
		if(parziale.size()>=2) {
		int y1 = parziale.get(0).getBegin().getYear();
		int y2 = parziale.get(parziale.size()-1).getBegin().getYear();
		if((y2 - y1 +1) > anniMax)
			return false;
		}
		
		
		return true;
		
	}

	private int conteggioPersone(List<PowerOutage> parziale) {
		int score = 0;
		
		for(PowerOutage po : parziale)
			score += po.getCostumersAffected();
		
		return score;
	}

	
	
}

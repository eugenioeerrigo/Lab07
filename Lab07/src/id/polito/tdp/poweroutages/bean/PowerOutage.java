package id.polito.tdp.poweroutages.bean;

import java.time.LocalDateTime;

public class PowerOutage {

	private int id;
	private int idEventType;
	private int idTag;
	private int idArea;
	private Nerc nerc;
	private int idResp;
	private int costumersAffected;
	private int demandLoss;
	private LocalDateTime begin;
	private LocalDateTime end;
	

	public PowerOutage(int id, int idEventType, int idTag, int idArea, Nerc nerc, int idResp, int costumersAffected,
			int demandLoss, LocalDateTime begin, LocalDateTime end) {
		this.id = id;
		this.idEventType = idEventType;
		this.idTag = idTag;
		this.idArea = idArea;
		this.nerc = nerc;
		this.idResp = idResp;
		this.costumersAffected = costumersAffected;
		this.demandLoss = demandLoss;
		this.begin = begin;
		this.end = end;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdEventType() {
		return idEventType;
	}

	public void setIdEventType(int idEventType) {
		this.idEventType = idEventType;
	}

	public int getIdTag() {
		return idTag;
	}

	public void setIdTag(int idTag) {
		this.idTag = idTag;
	}

	public int getIdArea() {
		return idArea;
	}

	public void setIdArea(int idArea) {
		this.idArea = idArea;
	}

	public Nerc getNerc() {
		return nerc;
	}

	public void setNerc(Nerc nerc) {
		this.nerc = nerc;
	}

	public int getIdResp() {
		return idResp;
	}

	public void setIdResp(int idResp) {
		this.idResp = idResp;
	}

	public int getCostumersAffected() {
		return costumersAffected;
	}

	public void setCostumersAffected(int costumersAffected) {
		this.costumersAffected = costumersAffected;
	}

	public int getDemandLoss() {
		return demandLoss;
	}

	public void setDemandLoss(int demandLoss) {
		this.demandLoss = demandLoss;
	}

	public LocalDateTime getBegin() {
		return begin;
	}

	public void setBegin(LocalDateTime begin) {
		this.begin = begin;
	}

	public LocalDateTime getEnd() {
		return end;
	}

	public void setEnd(LocalDateTime end) {
		this.end = end;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PowerOutage other = (PowerOutage) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PowerOutage [id=" + id + ", nerc=" + nerc + ", costumersAffected=" + costumersAffected + ", begin="
				+ begin + ", end=" + end + "]\n";
	}

	
}

package it.polito.tdp.poweroutages.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import id.polito.tdp.poweroutages.bean.Nerc;
import id.polito.tdp.poweroutages.bean.PowerOutage;
import it.polito.tdp.poweroutages.model.IdMapNerc;
import it.polito.tdp.poweroutages.model.IdMapPowerOutage;

public class PowerOutageDAO {
	
	public List<Nerc> getNercList(IdMapNerc nercmap) {

		String sql = "SELECT id, value FROM nerc";
		List<Nerc> nercList = new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Nerc n = new Nerc(res.getInt("id"), res.getString("value"));
				nercList.add(nercmap.get(n));
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return nercList;
	}

	public List<PowerOutage> getTuttiPO(IdMapPowerOutage poMap, IdMapNerc nercmap) {
		
		String sql = "SELECT * FROM poweroutages";
		List<PowerOutage> result = new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				PowerOutage po = new PowerOutage(res.getInt("id"), res.getInt("event_type_id"), res.getInt("tag_id"), res.getInt("area_id"), nercmap.get(res.getInt("nerc_id")), res.getInt("responsible_id"), res.getInt("customers_affected"), res.getInt("demand_loss"), res.getTimestamp("date_event_began").toLocalDateTime(), res.getTimestamp("date_event_finished").toLocalDateTime());
				result.add(poMap.get(po));
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return result;
	}
	
	
	public void getPOFromNerc(IdMapPowerOutage poMap, Nerc nerc) {
		
		String sql = "SELECT * FROM poweroutages WHERE nerc_id = ?";

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setInt(1, nerc.getId());
			ResultSet res = st.executeQuery();

			while (res.next()) {
				PowerOutage po = new PowerOutage(res.getInt("id"), res.getInt("event_type_id"), res.getInt("tag_id"), res.getInt("area_id"), nerc , res.getInt("responsible_id"), res.getInt("customers_affected"), res.getInt("demand_loss"), res.getTimestamp("date_event_began").toLocalDateTime(), res.getTimestamp("date_event_finished").toLocalDateTime());
				nerc.getPolist().add(poMap.get(po));
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

}

package de.hdm.itprojekt.server.db;

import java.sql.*;
import java.util.Vector;

import de.hdm.itprojekt.shared.bo.Eigenschaft;

public class EigenschaftMapper {

	private static EigenschaftMapper eigenschaftMapper = null;

	protected EigenschaftMapper() {
	};

	public static EigenschaftMapper eigenschaftMapper() {
		if (eigenschaftMapper == null) {
			eigenschaftMapper = new EigenschaftMapper();
		}
		return eigenschaftMapper;
	}

	public Eigenschaft insert(Eigenschaft eig) {

		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT MAX(idEigenschaft) AS maxid " + "FROM eigenschaft ");

			if (rs.next()) {

				eig.setId(rs.getInt("maxid") + 1);

				stmt = con.createStatement();

				stmt.executeUpdate(" INSERT INTO eigenschaft (id, ausbildung, abschluss, berufserfahrungsJahre, arbeitsgebiet, sprachkenntnisse, employmentStatus)"

								+ " VALUES (" + eig.getIdEigenschaft() + " ,'" + eig.getAusbildung() + "','" + eig.getAbschluss()
								+ "','" + eig.getBerufserfahrungsJahre() + "','" + eig.getArbeitsgebiet() + "', '"
								+ eig.getSprachkenntnisse() + " ,'" + eig.getEmploymentStatus());

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return eig;
	}

	public Eigenschaft findByKey(int idEigenschaft) {

		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			
			ResultSet rs = stmt.executeQuery(
					"SELECT idEigenschaft, arbeitsgebiet, ausbildung, berufserfahrungsJahre, sprachkenntnisse, employmentStatus, abschluss" +" FROM eigenschaft " + "WHERE idEigenschaft=" + idEigenschaft );


			
			
			
			

			if (rs.next()) {
				Eigenschaft eig = new Eigenschaft();
				eig.setIdEigenschaft(rs.getInt("idEigenschaft"));
				eig.setAusbildung(rs.getString("ausbildung"));
				eig.setAbschluss(rs.getString("abschluss"));
				eig.setBerufserfahrungsJahre(rs.getFloat("berufserfahrungsJahre"));
				eig.setArbeitsgebiet(rs.getString("arbeitsgebiet"));
				eig.setSprachkenntnisse(rs.getString("sprachkenntnisse"));
				eig.setEmploymentStatus(rs.getString("employmentStatus"));

				return eig;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	public Vector<Eigenschaft> findAll() {
		Connection con = DBConnection.connection();
		Vector<Eigenschaft> vec = new Vector<Eigenschaft>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(
					"SELECT idEigenschaft, ausbildung, abschluss, berufserfahrungsJahre, arbeitsgebiet, sprachkenntnisse, employmentStatus "
							+ "FROM eigenschaft " + "ORDER BY idEigenschaft");

			while (rs.next()) {
				Eigenschaft eig = new Eigenschaft();
				eig.setId(rs.getInt("idEigenschaft"));
				eig.setAusbildung(rs.getString("ausbildung"));
				eig.setAbschluss(rs.getString("abschluss"));
				eig.setBerufserfahrungsJahre(rs.getFloat("berufserfahrungsJahre"));
				eig.setArbeitsgebiet(rs.getString("arbeitsgebiet"));
				eig.setSprachkenntnisse(rs.getString("sprachkenntnisse"));
				eig.setEmploymentStatus(rs.getString("employmentStatus"));

				vec.addElement(eig);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return vec;
	}

	public Eigenschaft update(Eigenschaft eig) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			// Update Daten eingeben
			stmt.executeUpdate("UPDATE eigenschaft " + "SET ausbildung=\"" + eig.getAusbildung() + "\", "
					+ "abschluss=\"" + eig.getAbschluss() + "\" " + "berufserfahrungsJahre=\""
					+ eig.getBerufserfahrungsJahre() + "\" " + "arbeitsgebiet=\"" + eig.getArbeitsgebiet() + "\" "
					+ "sprachkenntnisse=\"" + eig.getSprachkenntnisse() + "\" " + "employmentStatus=\""
					+ eig.getEmploymentStatus() + "\" " + "WHERE idEigenschaft=" + eig.getIdEigenschaft());

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return eig;
	}

	public void delete(Eigenschaft eig) {

		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM eigenschaft " + "WHERE id=" + eig.getIdEigenschaft());
		} catch (SQLException e) {
			e.printStackTrace();
			}}}
		
	

	//public Vector<Eigenschaft> getEigeschaftOf(Eigenschaft eig) {


//		return PersonMapper.personMapper().findByKey(eig);
	//}

	
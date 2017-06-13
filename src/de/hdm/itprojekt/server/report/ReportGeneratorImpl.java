package de.hdm.itprojekt.server.report;
import java.util.Date;
import java.util.Vector;


import com.google.gwt.user.client.Window;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.itprojekt.server.ProjektmarktplatzAdministrationImpl;
import de.hdm.itprojekt.shared.ProjektmarktplatzAdministration;
import de.hdm.itprojekt.shared.ReportGenerator;
import de.hdm.itprojekt.shared.bo.Ausschreibung;
import de.hdm.itprojekt.shared.bo.Eigenschaft;
import de.hdm.itprojekt.shared.bo.Organisationseinheit;
import de.hdm.itprojekt.shared.bo.Partnerprofil;
import de.hdm.itprojekt.shared.bo.Person;
import de.hdm.itprojekt.shared.bo.Projekt;
import de.hdm.itprojekt.shared.bo.Team;
import de.hdm.itprojekt.shared.bo.Unternehmen;
import de.hdm.itprojekt.shared.report.AllAusschreibungenByPartnerprofilReport;
import de.hdm.itprojekt.shared.report.AllAusschreibungenReport;
import de.hdm.itprojekt.shared.report.AllBeteiligungenToProjectReport;
import de.hdm.itprojekt.shared.report.AllBewerbungenByAusschreibungReport;
import de.hdm.itprojekt.shared.report.AllBewerbungenByOrganisationseinheitReport;
import de.hdm.itprojekt.shared.report.AllBewerbungenToOneAusschreibungReport;
import de.hdm.itprojekt.shared.report.AllBewerbungenWithAusschreibungenReport;
import de.hdm.itprojekt.shared.report.Column;
import de.hdm.itprojekt.shared.report.FanIn;
import de.hdm.itprojekt.shared.report.FanInFanOutReport;
import de.hdm.itprojekt.shared.report.ProjektverflechtungReport;
import de.hdm.itprojekt.shared.report.Row;
import de.hdm.itprojekt.shared.report.SimpleParagraph;
import de.hdm.itprojekt.shared.report.SimpleReport;
@SuppressWarnings("serial")
public class ReportGeneratorImpl extends RemoteServiceServlet implements ReportGenerator {

	//ReportGenerator greift auf ProjektmarktplatzAdministration zu
	private ProjektmarktplatzAdministration projektmarktplatzadministration = null;
	
	public ReportGeneratorImpl() throws IllegalArgumentException{	
	}
	
	// ProjektmarktplatzAdministrationImpl Instanz erzeugt um auf Methoden zugreifen zu können
	public void init() throws IllegalArgumentException{
		ProjektmarktplatzAdministrationImpl pm = new ProjektmarktplatzAdministrationImpl();
		pm.init();
		this.projektmarktplatzadministration = pm;
	}
	
	//Auslesen der zugehörigen ProjektmarktplatzAdministration
	public ProjektmarktplatzAdministration getProjektmarktplatzAdministration(){
		 return this.projektmarktplatzadministration;
	}

	@Override
	public void setPerson() throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public AllAusschreibungenByPartnerprofilReport createAllAusschreibungenByPartnerprofilReport(Organisationseinheit o) throws IllegalArgumentException {
		
	
		Vector<Ausschreibung> allAusschreibungen = projektmarktplatzadministration.getAllAusschreibungen();
	
		Partnerprofil passendesPartnerprofil = projektmarktplatzadministration.getPartnerprofilByOrganisationseinheit(o);

		Vector<Eigenschaft> passendeEigenschaften = projektmarktplatzadministration.getEigenschaftByPartnerprofil(passendesPartnerprofil); 

for (Ausschreibung ausschreibung : allAusschreibungen) {
	Vector<Eigenschaft> eigenschaftenDerAusschreibung = projektmarktplatzadministration.getEigenschaftByIdPartnerprofil(ausschreibung.getIdPartnerprofil());
}

for (Eigenschaft eigenschaft : passendeEigenschaften) {
	
}	
	return null;
	}
		

	@Override
	public AllAusschreibungenReport createAllAusschreibungenReport() throws IllegalArgumentException {

		if(this.getProjektmarktplatzAdministration()==null){
		return null;
	}
		AllAusschreibungenReport result = new AllAusschreibungenReport();
		result.setTitel("Alle Ausschreibungen");
		result.setErstelldatum(new Date());
		
		Row headline = new Row();
		headline.addColumn(new Column("Ausschreibender"));
		headline.addColumn(new Column("Zugehöriges Projekt"));
		headline.addColumn(new Column("Bezeichnung"));
		headline.addColumn(new Column("Beschreibung"));
		headline.addColumn(new Column("Enddatum"));
		headline.addColumn(new Column("Ausschreibungsstatus"));
		
		result.addRow(headline);
		
		Vector<Ausschreibung> allAusschreibungen = this.projektmarktplatzadministration.getAllAusschreibungen();
		
		for(Ausschreibung a: allAusschreibungen){
			Row ausschreibungRow = new Row();
			
			/** Ausschreibender mit idOrganisationseinheit erhält zusätzlich idAusschreibender
			 *  Referenz zu idProjekt in Variable zugehörigesProjekt
			 */
			Organisationseinheit ausschreibender = projektmarktplatzadministration.getOrganisationseinheitById(a.getIdAusschreibender());
			Projekt zugehoerigesProjekt = projektmarktplatzadministration.getProjektbyId(a.getIdProjekt());
			
			
			if(ausschreibender instanceof Person){
				ausschreibungRow.addColumn(new Column(((Person)ausschreibender).getVorname()+""+((Person)ausschreibender).getNachname()));
				
			}	else if (ausschreibender instanceof Team){
					ausschreibungRow.addColumn(new Column(((Team)ausschreibender).getTeamName()));
					
			}	else if (ausschreibender instanceof Unternehmen){
					ausschreibungRow.addColumn(new Column(((Unternehmen)ausschreibender).getFirmenName()));
			}
			
			//Neue Spalte: Zugehörige Projektbezeichnung  
			ausschreibungRow.addColumn(new Column(zugehoerigesProjekt.getBezeichnung()));
			
			//Ausschreibungbezeichnung
			ausschreibungRow.addColumn(new Column(a.getBezeichnung()));
			
			//Enddatum
			ausschreibungRow.addColumn(new Column(a.getEndDatum().toString()));
			
			//Ausschreibungstext
			ausschreibungRow.addColumn(new Column(a.getBeschreibung()));
			
			//Status der Ausschreibung
			ausschreibungRow.addColumn(new Column(a.getAusschreibungsstatus().toString()));
			
			//Hinzufügen der Zeile
			result.addRow(ausschreibungRow);
			}
		return result;
		}
		
		

	@Override
	public AllBeteiligungenToProjectReport createAllBeteiligungenToProjectReport() throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AllBewerbungenByAusschreibungReport createAllBewerbungenByAusschreibungReport(Organisationseinheit o)
			throws IllegalArgumentException {
		if(this.getProjektmarktplatzAdministration()==null){
		}
		return null;
		
		AllBewerbungenByAusschreibungReport  result = new AllBewerbungenByAusschreibungReport();
		result.setTitel("Alle Bewerbungen auf eigenerstellte Ausschreibungen");
		result.setErstelldatum(new Date());
		
		Vector<Ausschreibung> allAusschreibungen = projektmarktplatzadministration.getAllAusschreibungen();
		for(Ausschreibung a: allAusschreibungen){
			if(a.getIdAusschreibender()==o.getId()){
				result.addSubReport(this.all.getId()));
			}
		}
		return result;
	}

	@Override
	public AllBewerbungenByOrganisationseinheitReport createAllBewerbungenByOrganisationseinheitReport(Organisationseinheit o)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FanInFanOutReport createFanInFanOutReport() throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FanIn createFanInAnalyse(Organisationseinheit o) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Person findPersonByKey(int id) throws IllegalArgumentException {
		return projektmarktplatzadministration.getPersonById(id);
	}

	@Override
	public Team findTeamByKey(int id) throws IllegalArgumentException {
		return projektmarktplatzadministration.getTeamById(id);
	}

	@Override
	public Unternehmen findUnternehmenByKey(int id) throws IllegalArgumentException {
		return projektmarktplatzadministration.getUnternehmenById(id);
	}

	@Override
	public Vector<Ausschreibung> getAusschreibungByMatchingPartnerprofil(Organisationseinheit o)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProjektverflechtungReport createProjektverflechtungenReport(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AllBewerbungenWithAusschreibungenReport createAllBewerbungenWithAusschreibungenReport(Organisationseinheit o)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AllBewerbungenToOneAusschreibungReport createAllBewerbungenToOneAusschreibungReport(Organisationseinheit o)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	

}

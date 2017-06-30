package de.hdm.itprojekt.server.report;
import java.util.Date;
import java.util.Vector;


import com.google.gwt.user.client.Window;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.itprojekt.server.GreetingServiceImpl;
import de.hdm.itprojekt.shared.GreetingService;
import de.hdm.itprojekt.shared.ReportGenerator;
import de.hdm.itprojekt.shared.bo.Ausschreibung;
import de.hdm.itprojekt.shared.bo.Beteiligung;
import de.hdm.itprojekt.shared.bo.Bewerbung;
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
import de.hdm.itprojekt.shared.report.FanOut;
import de.hdm.itprojekt.shared.report.ProjektverflechtungReport;
import de.hdm.itprojekt.shared.report.Row;
import de.hdm.itprojekt.shared.report.SimpleParagraph;
import de.hdm.itprojekt.shared.report.SimpleReport;


@SuppressWarnings("serial")
public class ReportGeneratorImpl extends RemoteServiceServlet implements ReportGenerator {

	//ReportGenerator greift auf greetingservice zu
	private GreetingService greetingservice = null;

	
	public ReportGeneratorImpl() throws IllegalArgumentException{	
	}
	
	// GreetingServiceImpl Instanz erzeugt um auf Methoden zugreifen zu k�nnen
	public void init() throws IllegalArgumentException{
		GreetingServiceImpl pm = new GreetingServiceImpl();
		pm.init();
		this.greetingservice = pm;
	}
	
	//Auslesen der zugeh�rigen greetingservice
	public GreetingService getGreetingService(){
		 return this.greetingservice;
	}


	
	
	

	



@Override
	public AllAusschreibungenReport createAllAusschreibungenReport() throws IllegalArgumentException {

		if(this.getGreetingService()==null){
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
		headline.addColumn(new Column("EndDatum"));
		headline.addColumn(new Column("Ausschreibungsstatus"));

		
		result.addRow(headline);
		
		Vector<Ausschreibung> allAusschreibungen = this.greetingservice.getAllAusschreibungen();
		
		for(Ausschreibung a: allAusschreibungen){
			Row ausschreibungRow = new Row();
			
			/** Ausschreibender mit idOrganisationseinheit erh�lt zus�tzlich idAusschreibender
			 *  Referenz zu idProjekt in Variable zugeh�rigesProjekt
			 */
			Organisationseinheit ausschreibender = greetingservice.getOrganisationseinheitById(a.getIdAusschreibender());
			Projekt zugehoerigesProjekt = greetingservice.getProjektbyId(a.getIdProjekt());
			
			
			if(ausschreibender instanceof Person){
				ausschreibungRow.addColumn(new Column(((Person)ausschreibender).getVorname()+" "+((Person)ausschreibender).getNachname()));
				
			}	else if (ausschreibender instanceof Team){
					ausschreibungRow.addColumn(new Column(((Team)ausschreibender).getTeamName()));
					
			}	else if (ausschreibender instanceof Unternehmen){
					ausschreibungRow.addColumn(new Column(((Unternehmen)ausschreibender).getFirmenName()));
			}
			
			//Neue Spalte: Zugeh�rige Projektbezeichnung  
			ausschreibungRow.addColumn(new Column(zugehoerigesProjekt.getBezeichnung()));
			
			//Ausschreibungbezeichnung
			ausschreibungRow.addColumn(new Column(a.getBezeichnung()));
			
			//Ausschreibungstext
			ausschreibungRow.addColumn(new Column(a.getBeschreibung()));
			
			//Enddatum
			ausschreibungRow.addColumn(new Column(a.getEndDatum().toString()));
			
			//Status der Ausschreibung
			ausschreibungRow.addColumn(new Column(a.getAusschreibungsstatus().toString()));
			
			//Hinzuf�gen der Zeile
			result.addRow(ausschreibungRow);
			}
		return result;
		}
		
		


	
@Override
	public AllBewerbungenByAusschreibungReport createAllBewerbungenByAusschreibungReport(Organisationseinheit o)
			throws IllegalArgumentException {
		if(this.getGreetingService()==null){
		
		return null;
	}
		AllBewerbungenByAusschreibungReport  result = new AllBewerbungenByAusschreibungReport();
		result.setTitel("Alle Bewerbungen auf eigenerstellte Ausschreibungen");
		result.setErstelldatum(new Date());
		
		Vector<Ausschreibung> allAusschreibungen = greetingservice.getAllAusschreibungen();
		for(Ausschreibung a: allAusschreibungen){
			if(a.getIdAusschreibender()==o.getId()){
				result.addSubReport(this.createAllBewerbungenToOneAusschreibungReport(a.getId()));
			}
		}
		return result;
	}




//auf eigene Bewerbungen ...
@Override
	public AllBewerbungenByOrganisationseinheitReport createAllBewerbungenByOrganisationseinheitReport(int id){
			
		if(this.getGreetingService()==null){
		return null;
	}
	
	AllBewerbungenByOrganisationseinheitReport result = new AllBewerbungenByOrganisationseinheitReport();
	
	result.setTitel("Alle Bewerbungen");
	result.setErstelldatum(new Date());
	
	Row headline = new Row();
	headline.addColumn(new Column("Erstelldatum der Bewerbung"));
	headline.addColumn(new Column("Anschreiben"));
	headline.addColumn(new Column("Bewerbungsstatus"));
	headline.addColumn(new Column("Zugehörige Ausschreibung"));
	headline.addColumn(new Column("Bewerbungsstatus"));
	
	result.addRow(headline);
	
	Vector<Bewerbung>bewerbungen = greetingservice.getBewerbungByBewerber(greetingservice.getOrganisationseinheitById(id));
	
	for(Bewerbung b: bewerbungen){
		
	
		Ausschreibung zugehoerigeAusschreibung = greetingservice.getAusschreibungbyId(b.getIdAusschreibung());
		
		Row bewerbungRow = new Row();
		
		bewerbungRow.addColumn(new Column(b.getErstellDatum().toString()));
		
		bewerbungRow.addColumn(new Column(b.getBewerbungsText()));
		
		bewerbungRow.addColumn(new Column(zugehoerigeAusschreibung.getBezeichnung()));
	
		bewerbungRow.addColumn(new Column(b.getBewerbungsStatus().toString()));
	
			result.addRow(bewerbungRow);
	}
	return result;
}

	
	

@Override
public AllBewerbungenToOneAusschreibungReport createAllBewerbungenToOneAusschreibungReport(int idAusschreibung)
		throws IllegalArgumentException {

		if(this.getGreetingService()==null){
	return null;
}

	
	Ausschreibung a = greetingservice.getAusschreibungbyId(idAusschreibung);
	
	AllBewerbungenToOneAusschreibungReport result = new AllBewerbungenToOneAusschreibungReport();

	result.setTitel("Alle Bewerbungen auf die Ausschreibung: " + a.getBezeichnung() + ", mit der ID" + a.getId());
	
	result.setErstelldatum(new Date());
	
	Row headline = new Row();
	headline.addColumn(new Column("Bewerber"));
	headline.addColumn(new Column("Erstelldatum"));
	headline.addColumn(new Column("Anschreiben"));
	headline.addColumn(new Column("Bewerbungsstatus"));
	
	result.addRow(headline);
	
	
	Vector<Bewerbung> bewerbungen = greetingservice.getBewerbungByAusschreibungId(idAusschreibung);
	
	for (Bewerbung b: bewerbungen){
		
		Row bewerbungRow = new Row();
		
		Organisationseinheit bewerber = greetingservice.getOrganisationseinheitById(b.getIdOrganisationseinheit());
		
		if(bewerber instanceof Person){
			bewerbungRow.addColumn(new Column(((Person)bewerber).getVorname()+""+((Person)bewerber).getNachname()));
			
			
		} else if(bewerber instanceof Team){
			bewerbungRow.addColumn(new Column(((Team)bewerber).getTeamName()));
			
		} else if(bewerber instanceof Unternehmen){
			bewerbungRow.addColumn(new Column(((Unternehmen)bewerber).getFirmenName()));
			
		}	
			bewerbungRow.addColumn(new Column(b.getErstellDatum().toString()));

			bewerbungRow.addColumn(new Column(b.getBewerbungsText()));
			
			bewerbungRow.addColumn(new Column(b.getBewerbungsStatus().toString()));
			
			result.addRow(bewerbungRow);
		
		}
	return result;
		
}
	
	


	
@Override
public AllBewerbungenWithAusschreibungenReport createAllBewerbungenWithAusschreibungenReport(Organisationseinheit o) throws IllegalArgumentException {
	
	
	if(this.getGreetingService()==null){
	return null;
}
	AllBewerbungenWithAusschreibungenReport result = new AllBewerbungenWithAusschreibungenReport();
	
	result.setTitel("Alle Bewerbungen mit den zugehörigen Ausschreibungen");
	
	result.setErstelldatum(new Date());
	
	Row headline = new Row();
	
	headline.addColumn(new Column("Anschreiben"));
	headline.addColumn(new Column("Erstelldatum"));
	headline.addColumn(new Column("Ausschreibender"));
	headline.addColumn(new Column("Zugehörige Ausschreibung"));
	headline.addColumn(new Column("Ausschreibungstext"));
	headline.addColumn(new Column("Bewerbungsstatus"));
	headline.addColumn(new Column("Enddatum"));
	
	result.addRow(headline);
	
	Vector <Bewerbung> allBewerbungenByBewerber = greetingservice.getBewerbungByBewerber(o);

	for(Bewerbung b: allBewerbungenByBewerber){
		
		Ausschreibung a = greetingservice.getAusschreibungbyId(b.getIdAusschreibung());
	
	Person ausschreibender = greetingservice.getPersonById(a.getIdAusschreibender());
	
	Row bewerbungRow = new Row();
	
	bewerbungRow.addColumn(new Column(b.getBewerbungsText()));
	
	bewerbungRow.addColumn(new Column(b.getErstellDatum().toString()));
	
	bewerbungRow.addColumn(new Column(ausschreibender.getVorname()+""+ ausschreibender.getNachname()));
	
	bewerbungRow.addColumn(new Column(a.getBezeichnung()));
	
	bewerbungRow.addColumn(new Column(a.getBeschreibung()));
	
	bewerbungRow.addColumn(new Column(b.getBewerbungsStatus().toString()));
	
	bewerbungRow.addColumn(new Column(a.getEndDatum().toString()));
	
	result.addRow(bewerbungRow);
	
	}
	
	return result;
}







	@Override
	public Person findPersonByKey(int id) throws IllegalArgumentException {
		return greetingservice.getPersonById(id);
	}

	@Override
	public Team findTeamByKey(int id) throws IllegalArgumentException {
		return greetingservice.getTeamById(id);
	}

	@Override
	public Unternehmen findUnternehmenByKey(int id) throws IllegalArgumentException {
		return greetingservice.getUnternehmenById(id);
	}

	

	



	@Override
	public AllBeteiligungenToProjectReport createAllBeteiligungenToProjectReport(int id) throws IllegalArgumentException {
		
		if(this.getGreetingService()==null){
			return null;
		}
		
		AllBeteiligungenToProjectReport result = new AllBeteiligungenToProjectReport();
		
		result.setTitel("Alle Beteiligungen");
		
		result.setErstelldatum(new Date());
		
		Row headline = new Row();
		
		headline.addColumn(new Column("Projekt"));

		headline.addColumn(new Column("Beteiligungszeit"));
		
		result.addRow(headline);
		
		Vector<Beteiligung> allBeteiligungen = greetingservice.getBeteiligungByBeteiligter(greetingservice.getOrganisationseinheitById(id));
		
		for(Beteiligung beteiligung : allBeteiligungen){
			
			Projekt p = greetingservice.getProjektbyId(beteiligung.getIdProjekt());
			
			
			Row beteiligungRow = new Row();
			
			beteiligungRow.addColumn(new Column(p.getBezeichnung()));
			beteiligungRow.addColumn(new Column(beteiligung.getBeteiligungszeit()));
			// beteiligungszeit ist nun int
		
			result.addRow(beteiligungRow);
		}
		
		return result;
	}
	
	
	@Override
	public ProjektverflechtungReport createProjektverflechtungenReport(int id) 
throws IllegalArgumentException {
		
		if (this.getGreetingService()== null){
			return null;
		}
		ProjektverflechtungReport result = new ProjektverflechtungReport();
		
		result.setTitel("Projektverflechtungen");
		
		result.setErstelldatum(new Date());
		
		result.addSubReport(this.createAllBeteiligungenToProjectReport(id));
		result.addSubReport(this.createAllBewerbungenByOrganisationseinheitReport(id));
	
	return result;
	}

	
	


	@Override
	public FanIn createFanInAnalyse() throws IllegalArgumentException {
		if(this.getGreetingService()==null){
			return null;
		}
		
		FanIn result = new FanIn();
		
		result.setTitel("Anzahl der Bewerbungen");
		
		result.setErstelldatum(new Date());
		
		Row headline = new Row();
		headline.addColumn(new Column("ID"));
		headline.addColumn(new Column("Organisationseinheit"));
		headline.addColumn(new Column("angenommen"));
		headline.addColumn(new Column("laufend"));
		headline.addColumn(new Column("abgelehnt"));
		
		result.addRow(headline);
		/** alle Bewerbungen der Organisationseinheit
		 * werden in einen Vector �bergeben
		 */
		Vector<Organisationseinheit> allOrganisationseinheiten = greetingservice.getAllOrganisationseinheiten();
		
		for (Organisationseinheit o : allOrganisationseinheiten){
			
				Vector<Bewerbung> angenommeneBewerbungen = new Vector<Bewerbung>();
				Vector<Bewerbung> laufendeBewerbungen = new Vector<Bewerbung>();
				Vector<Bewerbung> abgelehnteBewerbungen = new Vector<Bewerbung>();
		
		Vector<Bewerbung> allBewerbungen = greetingservice.getBewerbungByBewerber(o);
		
		for (Bewerbung b: allBewerbungen){
			if(b.getBewerbungsStatus().toString().equals("angenommen")){
				angenommeneBewerbungen.add(b);
			} else if (b.getBewerbungsStatus().toString().equals("laufend")){
				laufendeBewerbungen.add(b);
			} else if (b.getBewerbungsStatus().toString().equals("abgelehnt")){
				abgelehnteBewerbungen.add(b);
			}
		}
		Row anzahlRow = new Row();
		
		anzahlRow.addColumn(new Column(String.valueOf(o.getId())));
		
		if(o instanceof Person){
			anzahlRow.addColumn(new Column(((Person)o).getVorname()+""+((Person)o).getNachname()));
		} else if (o instanceof Team){
			anzahlRow.addColumn(new Column(((Team)o).getTeamName()));
		} else if (o instanceof Unternehmen){
			anzahlRow.addColumn(new Column(((Unternehmen)o).getFirmenName()));
		}
		
		anzahlRow.addColumn(new Column(String.valueOf(angenommeneBewerbungen.size())));
		anzahlRow.addColumn(new Column(String.valueOf(laufendeBewerbungen.size())));
		anzahlRow.addColumn(new Column(String.valueOf(abgelehnteBewerbungen.size())));
		
		result.addRow(anzahlRow);
		}
		return result;
	
	}
	
	
	@Override
	public FanOut createFanOutAnalyse() throws IllegalArgumentException {
		
		if(this.getGreetingService()==null){
		return null;
	}
	
	FanOut result = new FanOut();
	
	result.setTitel("Anzahl der Ausschreibungen");
	result.setErstelldatum(new Date());
	
	Row headline = new Row();
	headline.addColumn(new Column("ID"));
	headline.addColumn(new Column("Organisationseinheit"));
	headline.addColumn(new Column("laufend"));
	headline.addColumn(new Column("besetzt"));
	headline.addColumn(new Column("abgebrochen"));
	
	result.addRow(headline);
	
	Vector<Organisationseinheit> allOrganisationseinheiten = greetingservice.getAllOrganisationseinheiten();
	for (Organisationseinheit o: allOrganisationseinheiten){
		
		Vector<Ausschreibung>laufendeAusschreibungen = new Vector<Ausschreibung>();
		Vector<Ausschreibung>besetzteAusschreibungen = new Vector<Ausschreibung>();
		Vector<Ausschreibung>abgebrocheneAusschreibungen = new Vector<Ausschreibung>();
	
		Vector<Ausschreibung> allAusschreibungen = greetingservice.getAusschreibungByAusschreibender(o);
	
		for(Ausschreibung a: allAusschreibungen){
			
			if(a.getAusschreibungsstatus().toString().equals("laufend")){
				laufendeAusschreibungen.add(a);
			}else if(a.getAusschreibungsstatus().toString().equals("besetzt")){
				besetzteAusschreibungen.add(a);
				
			}else if(a.getAusschreibungsstatus().toString().equals("abgebrochen")){
				laufendeAusschreibungen.add(a);
			}
		}
	Row anzahlRow = new Row();
	
	anzahlRow.addColumn(new Column(String.valueOf(o.getId())));
	if(o instanceof Person){
		anzahlRow.addColumn(new Column(((Person)o).getVorname() + " "+((Person)o).getNachname()));
	} else if(o instanceof Team){
		anzahlRow.addColumn(new Column(((Team)o).getTeamName()));
	} else if(o instanceof Unternehmen){
		anzahlRow.addColumn(new Column(((Unternehmen)o).getFirmenName()));		
	}
	anzahlRow.addColumn(new Column(String.valueOf(laufendeAusschreibungen.size())));
	anzahlRow.addColumn(new Column(String.valueOf(besetzteAusschreibungen.size())));
	anzahlRow.addColumn(new Column(String.valueOf(abgebrocheneAusschreibungen.size()))); 
	
	result.addRow(anzahlRow);
	}
	return result;
}
	
	@Override
	public FanInFanOutReport createFanInFanOutReport() throws IllegalArgumentException {
		
		if(this.getGreetingService()== null){
			return null;
		}
		
		FanInFanOutReport result = new FanInFanOutReport();
		result.setTitel("FanIn-/FanOut-Analyse");
		result.setErstelldatum(new Date());
		
		result.addSubReport(this.createFanInAnalyse());
		result.addSubReport(this.createFanOutAnalyse());
		
		return result;
		
	
	}
	@Override
	public AllAusschreibungenByPartnerprofilReport createAllAusschreibungenByPartnerprofilReport(Organisationseinheit o) throws IllegalArgumentException {
			
		
			Vector<Ausschreibung> allAusschreibungen = greetingservice.getAllAusschreibungen();
		
			Partnerprofil passendesPartnerprofil = greetingservice.getPartnerprofilByOrganisationseinheit(o);

			Vector<Eigenschaft> passendeEigenschaften = greetingservice.getEigenschaftByPartnerprofil(passendesPartnerprofil); 

	for (Ausschreibung ausschreibung : allAusschreibungen) {
		Vector<Eigenschaft> eigenschaftenDerAusschreibung = greetingservice.getEigenschaftByIdPartnerprofil(ausschreibung.getIdPartnerprofil());
	}

	for (Eigenschaft eigenschaft : passendeEigenschaften) {
		
	}	
		return null;
	}
		
	
	@Override
	public AllAusschreibungenByPartnerprofilReport getAusschreibungByMatchingPartnerprofil(Organisationseinheit o)
	throws IllegalArgumentException {
		if(this.getGreetingService()==null){
			return null;
		}
		AllAusschreibungenByPartnerprofilReport result = new AllAusschreibungenByPartnerprofilReport();
		result.setTitel("");
		result.setErstelldatum(new Date());
		
		Row headline = new Row();
		headline.addColumn(new Column("Ausschreibung"));
		headline.addColumn(new Column("Ausschreibender"));
		headline.addColumn(new Column("Ausschreibungstext"));
		headline.addColumn(new Column("Enddatum"));
		
		result.addRow(headline);
		
		Vector<Ausschreibung> passendeAusschreibungen = new Vector<Ausschreibung>();
		
		Partnerprofil pp = greetingservice.getPartnerprofilByOrganisationseinheit(o);
		
		Vector<Eigenschaft> e = greetingservice.getEigenschaftByPartnerprofil(pp); 
		
		Vector<Ausschreibung> allAusschreibungen = greetingservice.getAllAusschreibungen();
		System.out.println("Anzahl aller Ausschreibungen: "+ allAusschreibungen.size());
		
		for(Ausschreibung a: allAusschreibungen){
			System.out.println("");
			
			
			
			Partnerprofil partnerprofilByAusschreibung = greetingservice.getPartnerprofilByAusschreibung(a);
			System.out.println("Partnerprofil ID: "+ partnerprofilByAusschreibung.getId());
			
			/** alle Eigenschaften zum Partnerprofil
			 * 
			 */
		Vector<Eigenschaft> eigenschaftenByAusschreibung = greetingservice.getEigenschaftByIdPartnerprofil(partnerprofilByAusschreibung.getId());
		System.out.println("Reihe der Eigenschaften zu diesem Partnerprofil: "+eigenschaftenByAusschreibung.size());
		if (e.size()==eigenschaftenByAusschreibung.size()){
			System.out.println("Reihe der Eigenschaften passen");
			int matchCounter = 0;
			for(int i=0; i<e.size();i++){
				for(Eigenschaft externeEigenschaft: eigenschaftenByAusschreibung){
					if(e.get(i).getAbschluss().equals(externeEigenschaft.getAbschluss())&& e.get(i).getArbeitsgebiet().equals(externeEigenschaft.getArbeitsgebiet()) 
							&& e.get(i).getAusbildung().equals(externeEigenschaft.getAusbildung())&& e.get(i).getBerufserfahrungsJahre().equals(externeEigenschaft.getBerufserfahrungsJahre())
							&&e.get(i).getEmploymentStatus().equals(externeEigenschaft.getEmploymentStatus())
							&& e.get(i).getSprachkenntnisse().equals(externeEigenschaft.getSprachkenntnisse())){
						matchCounter++;
					}
				}
			}
		
		Projekt projektByAusschreibung = greetingservice.getProjektbyId(a.getIdProjekt());
		
		Person personOfProjekt = this.findPersonByKey(projektByAusschreibung.getIdPerson());
		if(matchCounter==e.size()){
			if(personOfProjekt.getIdPartnerprofil()!=pp.getId()){
				System.out.println("Partnerprofil passt �berein");
				passendeAusschreibungen.add(a);
			}
		}
	}
	
}
		for (Ausschreibung a: passendeAusschreibungen){
			Row ausschreibungRow = new Row();
			
			ausschreibungRow.addColumn(new Column(a.getBezeichnung()));
			ausschreibungRow.addColumn(new Column(greetingservice.getPersonById(a.getIdAusschreibender()).getVorname()+""+greetingservice.getPersonById(a.getIdAusschreibender()).getNachname()));
			ausschreibungRow.addColumn(new Column(a.getBeschreibung()));
			ausschreibungRow.addColumn(new Column(a.getEndDatum().toString()));
		
			result.addRow(ausschreibungRow);
		
		}
		
		return result;
	}
	
public Vector<Organisationseinheit> getBewerberByAusschreibungen(Organisationseinheit o) throws IllegalArgumentException{
		
		Vector<Organisationseinheit> bewerber = new Vector<Organisationseinheit>();
		
		/**
		 * @param Organisationseinheit o
		 * @return Vector mit allen Ausschreibung zu dem �bergebenen Organisationseinheit-Objekt
		 */
		Vector<Ausschreibung> meineAusschreibungen = greetingservice.getAusschreibungByAusschreibender(o);
		
		for (Ausschreibung a : meineAusschreibungen) {
			
			/**
			 * @param id der Ausschreibung, welche aus dem Ausschreibung-Objekt gelesen wird
			 * @return Vector mit allen Bewerbungen zur �bergebenen Ausschreibung 
			 */
			Vector<Bewerbung> bewerbungen =  greetingservice.getBewerbungByAusschreibungId(a.getId());
			
				for (Bewerbung bewerbung : bewerbungen) {
					/**
					 * 
					 * @param id der Organisationseinheit, welche aus der jeweiligen Bewerbung gelesen wird
					 * @return Organisationseinheit-Objekt
					 */
					if(bewerber.contains(greetingservice.getOrganisationseinheitById(bewerbung.getIdOrganisationseinheit()))){
						
					}else{
						/**
						 * 
						 * @param id der Organisationseinheit, welche aus der jeweiligen Bewerbung gelesen wird
						 * @return Organisationseinheit-Objekt
						 */
						bewerber.add(greetingservice.getOrganisationseinheitById(bewerbung.getIdOrganisationseinheit()));
					}
					
					
				}
			}
		return bewerber;
}



@Override
public Vector<Person> getAllPersonen() throws IllegalArgumentException {
	// TODO Auto-generated method stub
	return greetingservice.getAllPersons();
}

}
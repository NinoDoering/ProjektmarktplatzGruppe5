package de.hdm.itprojekt.shared;

import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.itprojekt.shared.bo.*;
import de.hdm.itprojekt.server.db.*;

public interface ProjektmarktplatzAdministrationAsync<Person, Unternehmen, Team, Projekt, Ausschreibung, Projektmarktplatz, Bewertung, Bewerbung> {

	void init(AsyncCallback<Void> callback);

	// Projektmarktplatz pm
	void anlegenProjektmarktplatz(Projektmarktplatz pm, String geschaeftsgebiet, String bezeichnung, Projekt projekt,
			AsyncCallback<Projektmarktplatz> callback);

	void editierenProjektmarktplatz(String geschaeftsgebiet, String bezeichnung, Projekt projekt,
			AsyncCallback<Vector<Projektmarktplatz>> callback);

	void loeschenProjektmarktplatz(Projekt p, Projektmarktplatz pm, AsyncCallback<Void> callback);

	void save(Projektmarktplatz pm, AsyncCallback<Void> callback);

	void getProjektmarktplatz(Projekt projekt, AsyncCallback<Vector<Projektmarktplatz>> callback);

	// Projekt p
	void anlegenProjekt(Projekt p, String projektleiter, String beschreibung, Date startDatum, Date endDatum,
			AsyncCallback<Projekt> callback);

	void editierenProjekt(String projektleiter, String beschreibung, Date startDatum, Date endDatum,
			AsyncCallback<Vector<Projekt>> callback);

	void loeschenProjekt(Projekt p, AsyncCallback<Void> callback);

	void getProjekt(Projekt p, AsyncCallback<Vector<Projekt>> callback);

	// Ausschreibung a
	void anlegenAusschreibung(Ausschreibung a, String bezeichnung, String beschreibung, Date endDatum,
			AsyncCallback<Ausschreibung> callback);

	void editierenAusschreibung(Ausschreibung a, String bezeichnung, String beschreibung, Date endDatum,
			AsyncCallback<Vector<Ausschreibung>> callback);

	void loeschenAusschreibung(Ausschreibung a, AsyncCallback<Void> callback);

	void getAusschreibung(Ausschreibung a, String bezeichnung, AsyncCallback<Vector<Ausschreibung>> callback);

	// Partnerprofil pp
	void anlegenPartnerprofil(int idParnterprofil, Partnerprofil pp, String eigenschaft,
			AsyncCallback<Partnerprofil> callback);

	void editierenPartnerprofil(Partnerprofil pp, String eigenschaft, AsyncCallback<Vector<Partnerprofil>> callback);

	void loeschenPartnerprofil(Partnerprofil pp, AsyncCallback<Void> callback);

	void getPartnerprofil(int idPartnerprofil, Partnerprofil pp, String eigenschaften,
			AsyncCallback<Vector<Partnerprofil>> callback);

	// Bewerberung b
	void anlegenBewerbung(Bewerbung b, String bewerber, String bewerbungstext, Date erstellDatum,
			AsyncCallback<Bewerbung> callback);

	void editierenBewerbung(String beweber, String bewerbungstext, Date erstellDatum,
			AsyncCallback<Vector<Bewerbung>> callback);

	void loeschenBewerbung(Bewerbung b, String bewerber, AsyncCallback<Void> callback);

	void getBewerbung(Bewerbung b, String bewerber, AsyncCallback<Vector<Bewerbung>> callback);

	// Eigenschaft e
	void anlegenEigenschaft(Eigenschaft e, String arbeitsgebiet, float berufserfahrungsJahre, String employmentStatus,
			String ausbildung, String sprachkenntnisse, AsyncCallback<Eigenschaft> callback);

	void editierenEigenschaft(Eigenschaft e, String arbeitsgebiet, float berufserfahrungsJahre, String employmentStatus,
			String ausbildung, String sprachkenntnisse, AsyncCallback<Vector<Eigenschaft>> callback);

	void loeschenEigenschaft(Eigenschaft e, String arbeitsgebiet, float berufserfahrungsJahre, String employmentStatus,
			String ausbildung, String sprachkenntnisse, AsyncCallback<Void> callback);

	void getEigenschaft(Eigenschaft e, String arbeitsgebeit, float berufserfahrungsJahre, String employmentStatus,
			AsyncCallback<Vector<Eigenschaft>> callback);

	// Bewertung bg
	void anlegenBewertung(Bewertung bewertung, String textuelleBewertung, float fließKommaBewertung,
			AsyncCallback<Bewertung> callback);

	void editierenBewertung(Bewertung bewertung, String textuelleBewertung, float fließKommaBewertung,
			AsyncCallback<Vector<Bewertung>> callback);

	void loeschenBewertung(Bewertung bewertung, String textuelleBewertung, float fließKommaBewertung,
			AsyncCallback<Void> callback);

	void getBewertung(Bewertung bewertung, float fließKommaBewertung, AsyncCallback<Vector<Bewertung>> callback);

	// Beteiligung beteiligung
	void anlegenBeteiligung(Beteiligung beteiligung, String name, int idBeteiligung, int idProjekt,
			AsyncCallback<Beteiligung> callback);

	void editierenBeteiligung(Beteiligung betiligung, AsyncCallback<Vector<Beteiligung>> callback);

	void loeschenBeteiligung(Beteiligung beteiligung, AsyncCallback<Void> callback);

	void getBeteiligung(Beteiligung beteiligung, int idOrganisationseinheit,
			AsyncCallback<Vector<Beteiligung>> callback);

	// Organisationseinheit org
	void anlegenOrganisationseinheit(Organisationseinheit org, int idOrganisationseinheit,
			AsyncCallback<Organisationseinheit> callback);

	void editierenOrganisationseinheit(Organisationseinheit org, AsyncCallback<Vector<Organisationseinheit>> callback);

	void loeschenOrgansationseinheit(Organisationseinheit org, int idOrganisationseinheit,
			AsyncCallback<Void> callback);

	void getOrganisationseinheit(Organisationseinheit org, int idOrganisationseinheit,
			AsyncCallback<Vector<Organisationseinheit>> callback);

	// Person p
	void anlegenPerson(Person p, int idPerson, char geschlecht, String vorname, String nachname,
			AsyncCallback<Person> callback);

	void editierenPerson(Person p, char geschlecht, String vorname, String nachname,
			AsyncCallback<Vector<Person>> callback);

	void loeschenPerson(Person p, AsyncCallback<Void> callback);

	void getPerson(Person p, int idPerson, char geschlecht, String vorname, String nachname,
			AsyncCallback<Vector<Person>> callback);

	// Team t
	void anlegenTeam(Team t, int idTeam, String teamName, int mitgliederAnzahl, AsyncCallback<Team> callback);

	void editierenTem(Team t, String teamName, int mitgliederAnzahl, AsyncCallback<Vector<Team>> callback);

	void loeschenTeam(Team t, int idTeam, AsyncCallback<Void> callback);

	void getTeam(Team t, int idTeam, String teamName, int mitgliederAnzahl, AsyncCallback<Vector<Team>> callback);

	// Unternehmen u
	void anlegenUnternehmen(Unternehmen u, int idUnternehmen, String firmenName, AsyncCallback<Unternehmen> callback);

	void editierenUnternehmen(Unternehmen u, String firmenName, AsyncCallback<Vector<Unternehmen>> callback);

	void loeschenUnternehmen(Unternehmen u, int idUnternehmen, String firmenName, AsyncCallback<Void> callback);

	void getUnternehmen(Unternehmen u, int idUnternehmen, String firmenName,
			AsyncCallback<Vector<Unternehmen>> callback);

	// Getter-By-All Option
	void getBewertungById(int idBewertung, Bewertung bg, AsyncCallback<Vector<Bewertung>> callback);

	void getBewertungByBewerbungId(Bewertung bg, int idBewertung, Bewerbung b, int idBewerbung,
			AsyncCallback<Vector<Bewertung>> callback);

	void getAlleAusschreibung(Ausschreibung a, int idAusschreibung, AsyncCallback<Vector<Ausschreibung>> callback);

	void getAusschreibungByidPartnerprofil(Ausschreibung a, int idAusschreibung, Partnerprofil pp, int idPartnerprofil,
			AsyncCallback<Vector<Ausschreibung>> callback);

	void getAusschreibungByidBewerbung(Ausschreibung a, int idAusschreibung, Bewerbung b, int idBewerbung,
			AsyncCallback<Vector<Ausschreibung>> callback);

	void getBewerbungByidAusschreibung(Bewerbung b, int idBewerbung, int idAusschreibung,
			AsyncCallback<Vector<Bewerbung>> callback);

	void getBewerbungByidPartnerprofil(Bewerbung b, int idBewerbung, Partnerprofil pp, int idPartnerprofil,
			AsyncCallback<Vector<Bewerbung>> callback);

	void getBewerbungByidOrganisationseinheit(Bewerbung b, int idBewerbung, int idOrganisationseinheit,
			AsyncCallback<Vector<Bewerbung>> callback);

	void getBewertungenByidOrganisationseinheit(int idBewertung, int idOrganisationseinheit,
			AsyncCallback<Vector<Bewertung>> callback);

	void getBeteiligungByidOrganisationseinheit(Beteiligung beteiligung, int idBeteiligung, int idOrgansisationseinheit,
			AsyncCallback<Vector<Beteiligung>> callback);

	void getAllByidBewerbungen(Bewerbung b, int idBewerbung, AsyncCallback<Vector<Bewerbung>> callback);
}

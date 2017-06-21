package de.hdm.itprojekt.client.gui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;

import de.hdm.itprojekt.client.Showcase;
import de.hdm.itprojekt.shared.GreetingService;
import de.hdm.itprojekt.shared.GreetingServiceAsync;

public class AGBMeetProjects extends Showcase {

	private GreetingServiceAsync gwtproxy = GWT.create(GreetingService.class);

	@Override
	protected String getHeadlineText() {
		return "<b> Unsere AGB´s</b>";
	}
	@Override
	protected void run() {
	
		RootPanel.get("Anzeige").setWidth("100%");
		
	
		this.append("<div class="	
				
		+ "<b>" + "Datenschutzerklärung</b><p>"

		+ "Der Schutz und die Sicherheit von persönlichen Daten hat bei uns eine hohe Priorität."
		+ "Daher halten wir uns strikt an die Regeln des deutschen Bundesdatenschutzgesetzes (BDSG)."
		+ "Nachfolgend werden Sie darüber informiert, welche Art von Daten erfasst und zu welchem Zweck sie erhoben werden:"

		+ "1. Datenübermittlung /Datenprotokollierung"
		+"Beim Besuch dieser Seite verzeichnet der Web-Server automatisch Log-Files, die keiner bestimmten Person zugeordnet werden können." 
		+"Diese Daten beinhalten z. B. den Browsertyp und -version, verwendetes Betriebssystem, Referrer URL (die zuvor besuchte Seite),"
		+"IP-Adresse des anfragenden Rechners, Zugriffsdatum und -uhrzeit der Serveranfrage und die Dateianfrage des Client (Dateiname und URL)."
		+"Diese Daten werden nur zum Zweck der statistischen Auswertung gesammelt. Eine Weitergabe an Dritte, zu kommerziellen oder nichtkommerziellen"
		+"Zwecken, findet nicht statt."
		+"<br></br>"
		+"2. Nutzung persönlicher Daten"
		+"Persönliche Daten werden nur erhoben oder verarbeitet, wenn Sie diese Angaben freiwillig, z.B. im Rahmen einer Anfrage mitteilen."
		+"Sofern keine erforderlichen Gründe im Zusammenhang mit einer Geschäftsabwicklung bestehen, können Sie jederzeit die zuvor erteilte Genehmigung Ihrer "
		+"persönlichen Datenspeicherung mit sofortiger Wirkung schriftlich (z.B. per E-Mail oder per Fax) widerrufen. Ihre Daten werden nicht an Dritte weitergeben," 
		+"es sei denn, eine Weitergabe ist aufgrund gesetzlicher Vorschriften erforderlich."
		+"<br></br>"
		+"3. Auskunft, Änderung und Löschung Ihrer Daten"
		+"Gemäß geltendem Recht können Sie jederzeit bei uns schriftlich nachfragen, ob und welche personenbezogenen Daten bei uns über Sie gespeichert sind. "
		+"Eine entsprechende Mitteilung hierzu erhalten Sie umgehend."
		+"<br></br>"
		+"4. Sicherheit Ihrer Daten"
		+"Ihre uns zur Verfügung gestellten persönlichen Daten werden durch Ergreifung aller technischen sowie organisatorischen Sicherheitsmaßnahmen so gesichert, "
		+"dass sie für den Zugriff unberechtigter Dritter unzugänglich sind. Bei Versendung von sehr sensiblen Daten oder Informationen ist es empfehlenswert, "
		+"den Postweg zu nutzen, da eine vollständige Datensicherheit per E-Mail nicht gewährleistet werden kann."
		+"<br></br>"
		+"5. Änderungen dieser Datenschutzbestimmungen"
		+"Wir werden diese Richtlinien zum Schutz Ihrer persönlichen Daten von Zeit zu Zeit aktualisieren. Sie sollten sich diese Richtlinien gelegentlich ansehen, "
		+"um auf dem Laufenden darüber zu bleiben, wie wir Ihre Daten schützen und die Inhalte unserer Website stetig verbessern. Sollten wir wesentliche Änderungen bei der Sammlung," 
		+"der Nutzung und/oder der Weitergabe der uns von Ihnen zur Verfügung gestellten personenbezogenen Daten vornehmen, werden wir Sie durch einen eindeutigen und "
		+"gut sichtbaren Hinweis auf der Website darauf aufmerksam machen. Mit der Nutzung der Webseite erklären Sie sich mit den Bedingungen dieser Richtlinien zum Schutz persönlicher Daten einverstanden."
		+"<p></p>"
		+"Bei Fragen zu diesen Datenschutzbestimmungen wenden Sie sich bitte über unsere Kontakt-Seite an uns."
		+"</div>");
		
		
	}
}                        

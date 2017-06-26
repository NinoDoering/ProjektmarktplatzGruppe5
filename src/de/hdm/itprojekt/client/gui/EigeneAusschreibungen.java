package de.hdm.itprojekt.client.gui;

import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.SimplePager.TextLocation;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionChangeEvent.Handler;
import com.google.gwt.view.client.SingleSelectionModel;

import de.hdm.itprojekt.client.ClientSideSettings;
import de.hdm.itprojekt.client.Navigator;
import de.hdm.itprojekt.client.Showcase;

import de.hdm.itprojekt.shared.GreetingService;
import de.hdm.itprojekt.shared.GreetingServiceAsync;
import de.hdm.itprojekt.shared.bo.Ausschreibung;
import de.hdm.itprojekt.shared.bo.Ausschreibung.Status;
import de.hdm.itprojekt.shared.bo.Marktplatz;
import de.hdm.itprojekt.shared.bo.Organisationseinheit;
import de.hdm.itprojekt.shared.bo.Person;
import de.hdm.itprojekt.shared.bo.Projekt;

public class EigeneAusschreibungen extends Showcase {
	

	/**
	 * Anlegen globaler Variablen und Objekte
	 */
	SimplePager pager;
	HorizontalPanel hp_pager = new HorizontalPanel();
	HorizontalPanel buttonPanel = new HorizontalPanel();
	
	
	private static Vector<Ausschreibung> ausschreibungen = new Vector<>();
	private static int idPartnerprofil = 0;
	private static ClickHandler clickhandler;
	private static ClickEvent clickevent;
	
	CellTable<AusschreibungProjekt> ausschreibungProjektCt = new CellTable<AusschreibungProjekt>();
	Vector <Projekt> projekt = new Vector <Projekt>();
	Vector<AusschreibungProjekt> mix= new Vector<AusschreibungProjekt>();
	
	/**
	 * Auslesen der ProjektmarktplatzAsync Instanz
	 */
	GreetingServiceAsync greetingService = ClientSideSettings.getMarktplatzVerwaltung();
	
	
	/**
	 * Anlegen der Buttons
	 */
	Button ausschreibungLoeschenButton = new Button("Löschen");
	Button ausschreibungBearbeitenButton = new Button("Bearbeiten");
	Button partnerprofilBearbeitenButton = new Button("Gefordertes Partnerprofil anzeigen");
	Button bewerbungenAnzeigenButton = new Button("Bewerbungen anzeigen");
	
	
	private RoleManagement roleManagement=null;
	private Navigator navigator=null;

	
	/**
	 * Konstruktor, dem eine Instanz der roleManagement und der navigator übergeben wird.
	 * @param roleManagement
	 * @param navigator
	 */
	public EigeneAusschreibungen(RoleManagement roleManagement, Navigator navigator) {
		this.roleManagement=roleManagement;
		this.navigator=navigator;
	}
	
	/**
	 * Anlegen des HeadlineTextes
	 */
	@Override
	protected String getHeadlineText() {
		return "Meine erstellten Ausschreibungen";
	}

	/**
	 * Methode die aufgerufen wird, sobald diese Form aufgerufen wird.
	 */
	@Override
	protected void run() {
		
		hp_pager.setWidth("100%");
		hp_pager.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		SimplePager.Resources pagerResources = GWT.create(SimplePager.Resources.class);
		pager = new SimplePager(TextLocation.CENTER, pagerResources, false, 0, true);
		RootPanel.get("Details").setWidth("75%");
		ausschreibungProjektCt.setWidth("100%", true);
		
		/**
		 * CallBack um die Ausschreibungen der gewünschten Person zu laden
		 */
		greetingService.getOrganisationseinheitById(roleManagement.getSelectedRoleID(), new AsyncCallback<Organisationseinheit>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Das Anzeigen der Person ist fehlgeschlagen!");				
			}

			@Override
			public void onSuccess(Organisationseinheit result) {
				/**
				 * Bei erfolgreichem Callback wird eine Organisationseinheit als result zurückgegeben,
				 * diese wird für den neuen Callback übergeben. Der neue Callback liefert dann ein Vector
				 * mit Ausschreibungen zu dieser Organisationseinheit zurück.
				 */
				if (result != null) {
					greetingService.getAusschreibungByAusschreibender(result, new AsyncCallback<Vector<Ausschreibung>>() {

						@Override
						public void onFailure(Throwable caught) {
							Window.alert("Fehler: "+caught.toString());
						}

						/**
						 * In Objekt localmix werden die Attribute der Ausschreibung gesetzt
						 * Attribute der Ausschreibungen gesetzt.
						 */
						@Override
						public void onSuccess(Vector<Ausschreibung> result) {
							for (Ausschreibung ausschreibung : result) {
								final AusschreibungProjekt localmix= new AusschreibungProjekt();
								
								localmix.setBezeichnung(ausschreibung.getBezeichnung());
								localmix.setStatus(ausschreibung.getAusschreibungsstatus());
								localmix.setEndDatum(ausschreibung.getEndDatum());
								localmix.setIdAusschreibender(ausschreibung.getIdAusschreibender());
								localmix.setIdAusschreibung(ausschreibung.getId());
								localmix.setText(ausschreibung.getBeschreibung());
								localmix.setIdProjekt(ausschreibung.getIdProjekt());
								localmix.setIdPartnerprofil(ausschreibung.getIdPartnerprofil());
								ausschreibungProjektCt.setRowCount(mix.size(), true);
								ausschreibungProjektCt.setRowData(mix);
								/**
								 * Neuer Callback um das dazugehörige Projekt zu der übergebenen Ausschreibung zu erhalten.
								 * Dabei wird in den localmix der Projektname gesetzt und in ein Vector namens
								 * mix übergeben, dass später in der Celltable ausgegeben wird
								 */
								
								greetingService.getProjektByAusschreibung(ausschreibung, new AsyncCallback<Projekt>() {

									@Override
									public void onFailure(Throwable caught) {
										Window.alert("Fehler: "+caught.toString());
										
									}

									@Override
									public void onSuccess(Projekt result) {
										localmix.setProjektname(result.getBezeichnung());
										mix.add(localmix);
										
										
										ausschreibungProjektCt.setRowCount(mix.size(), true);
										ausschreibungProjektCt.setRowData(mix);	
										final ListDataProvider dataProvider = new ListDataProvider();
										
										
										
										pager.setDisplay(ausschreibungProjektCt);
										dataProvider.addDataDisplay(ausschreibungProjektCt);
										dataProvider.setList(new ArrayList<AusschreibungProjekt>(mix));
										pager.setPageSize(10);
										
										
									
										
										
									}
								});

								}
							hp_pager.add(pager);
						}
					});
				}
			}
		});
		
		ausschreibungProjektCt.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
		
		//Erstellen der TextColumns der CellTable
				TextColumn<AusschreibungProjekt> projektColumn = new TextColumn<AusschreibungProjekt>(){

				@Override
					public String getValue(AusschreibungProjekt object) {
						return object.getProjektname();
					}
				};
		
		
				TextColumn<AusschreibungProjekt> bezeichnungColumn = new TextColumn<AusschreibungProjekt>(){

					@Override
					public String getValue(AusschreibungProjekt object) {
						return object.getBezeichnung();
					}
				};
				

				TextColumn<AusschreibungProjekt> endDatumColumn = new TextColumn<AusschreibungProjekt>(){

					@Override
					public String getValue(AusschreibungProjekt object) {
						return object.getEndDatum().toString();
					}
				};
				
				TextColumn<AusschreibungProjekt> statusColumn = new TextColumn<AusschreibungProjekt>(){

					@Override
					public String getValue(AusschreibungProjekt object) {
						return object.getStatus().toString();
					}
				};
				
		
		
			// Hinzufügen der Spalten zu unserer CellTable+
				ausschreibungProjektCt.addColumn(projektColumn, "Projektname");
				ausschreibungProjektCt.addColumn(bezeichnungColumn, "Bezeichnung");
				ausschreibungProjektCt.addColumn(endDatumColumn, "Bewerbungsfrist");
				ausschreibungProjektCt.addColumn(statusColumn, "Status");			
				ausschreibungProjektCt.setEmptyTableWidget(new Label("Es sind keine eigenen Ausschreibungen vorhanden"));
				ausschreibungProjektCt.setLoadingIndicator(null);
				
			// Anlegen des SingleSeletion Models
				final SingleSelectionModel<AusschreibungProjekt> selectionModel = new SingleSelectionModel<>();
				ausschreibungProjektCt.setSelectionModel(selectionModel);
				selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
					
					@Override
					public void onSelectionChange(SelectionChangeEvent event) {
						
						
					}
				});
				
					
			
				ausschreibungProjektCt.setWidth("100%");
				
			//Hinzufügen der Buttons zum ButtonPanel
				buttonPanel.add(ausschreibungBearbeitenButton);
				buttonPanel.add(ausschreibungLoeschenButton);
				buttonPanel.add(partnerprofilBearbeitenButton);
				buttonPanel.add(bewerbungenAnzeigenButton);
	
				
			//Style der Buttons
				ausschreibungBearbeitenButton.setStylePrimaryName("cell-btn");
				ausschreibungLoeschenButton.setStylePrimaryName("cell-btn");
				partnerprofilBearbeitenButton.setStylePrimaryName("cell-btn");
				bewerbungenAnzeigenButton.setStylePrimaryName("cell-btn");
				
				
			//Hinzufügen der CellTable und des ButtonPanels zu unserem Showcase
				this.setSpacing(8);
				this.add(buttonPanel);
				this.add(ausschreibungProjektCt);
				this.add(hp_pager);
				
				
			/**
			 * CLICK-HANDLER
			 */
				
				
				/**
				 * Click-Handler zum löschen einer Ausschreibung
				 */
			ausschreibungLoeschenButton.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					/**
					 * Wenn eine Zeile ausgewählt ist wird in das <code>selectedAusschreibung</code> die benötigten
					 * Attribute gesetzt und anschließend gelöscht.
					 */
					if(selectionModel.getSelectedObject() != null){
						AusschreibungProjekt selectedAusschreibung = selectionModel.getSelectedObject();
						Ausschreibung localAusschreibung = new Ausschreibung();
						localAusschreibung.setId(selectedAusschreibung.getIdAusschreibung());
						localAusschreibung.setIdPartnerprofil(selectedAusschreibung.getIdPartnerprofil());
						localAusschreibung.setBezeichnung(selectedAusschreibung.getBezeichnung());
						greetingService.loeschenAusschreibung(localAusschreibung, new AsyncCallback<Void>() {
						
						@Override
						public void onSuccess(Void result) {
							Window.alert("Die Ausschreibung wurde erfolgreich gelöscht.");
							navigator.reload();
						}
						
						@Override
						public void onFailure(Throwable caught) {
							Window.alert("Fehler: Die Ausschreibung konnte nicht gelöscht werden.");
						}
					});
				} else {
					Window.alert("Bitte wähle zuerst die zu löschende Ausschreibung aus.");
				}
			}
		});
			
			
			ausschreibungBearbeitenButton.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					
						
								final DialogBox ausschreibungBearbeitenDialogBox = new DialogBox();
								FlexTable ausschreibungBearbeitenFlexTable = new FlexTable();
								HorizontalPanel buttonPanel = new HorizontalPanel();
								VerticalPanel dialogBoxPanel = new VerticalPanel();
								Label ausschreibungBezeichungLabel = new Label("Bezeichnung:");
								Label ausschreibungBewerbungsfristLabel = new Label("Bewerbungsfrist:");
								Label ausschreibungstextLabel = new Label("Ausschreibungstext:");
								Label ausschreibungsStatusLabel = new Label("Ausschreibungstatus:");
								final TextBox ausschreibungBezeichungBox = new TextBox();
								final DateBox ausschreibungBewerbungsfristBox = new DateBox();
								final TextArea ausschreibungstextBox = new TextArea();
								final ListBox ausschreibungsStatusBox = new ListBox();
								Button abbrechenButton = new Button("Abbrechen");
								Button speichernButton = new Button("Speichern");
								Button zurueckButton = new Button("Zurück");

								//Setzen des Formats und weiterer Standardwerte
								DateTimeFormat dateformat = DateTimeFormat.getFormat("dd.MM.yyyy");
								ausschreibungBewerbungsfristBox.setFormat(new DateBox.DefaultFormat(dateformat));
								ausschreibungstextBox.setVisibleLines(20);
								ausschreibungstextBox.setCharacterWidth(70);
								ausschreibungsStatusBox.addItem("laufend");
								ausschreibungsStatusBox.addItem("abgebrochen");
								ausschreibungsStatusBox.addItem("besetzt");
								ausschreibungBearbeitenDialogBox.setGlassEnabled(true);
								ausschreibungBearbeitenDialogBox.setAnimationEnabled(false);
								
								//Erstellen der FlexTable
								ausschreibungBearbeitenFlexTable.setWidget(0, 1, ausschreibungBezeichungBox);
								ausschreibungBearbeitenFlexTable.setWidget(0, 0, ausschreibungBezeichungLabel);
								
								ausschreibungBearbeitenFlexTable.setWidget(1, 1, ausschreibungBewerbungsfristBox);
								ausschreibungBearbeitenFlexTable.setWidget(1, 0, ausschreibungBewerbungsfristLabel);
								
								ausschreibungBearbeitenFlexTable.setWidget(2, 1, ausschreibungstextBox);
								ausschreibungBearbeitenFlexTable.setWidget(2, 0, ausschreibungstextLabel);
								
								ausschreibungBearbeitenFlexTable.setWidget(3, 1, ausschreibungsStatusBox);
								ausschreibungBearbeitenFlexTable.setWidget(3, 0, ausschreibungsStatusLabel);
								
							//Hinzufügen der Buttons zum Buttonpanel
								buttonPanel.add(speichernButton);
								buttonPanel.add(abbrechenButton);
								abbrechenButton.setStylePrimaryName("cell-btn");
								speichernButton.setStylePrimaryName("cell-btn");
								
							//Hinzufügen der FlexTable zur DialogBox
								dialogBoxPanel.setSpacing(8);
								dialogBoxPanel.add(buttonPanel);
								dialogBoxPanel.add(ausschreibungBearbeitenFlexTable);
								ausschreibungBearbeitenDialogBox.add(dialogBoxPanel);
								
								
								
							if(selectionModel.getSelectedObject() != null){	
							//	Anzeigen der DialogBox
								ausschreibungBezeichungBox.setValue(selectionModel.getSelectedObject().getBezeichnung());
								ausschreibungBewerbungsfristBox.setValue(selectionModel.getSelectedObject().getEndDatum());
								ausschreibungstextBox.setValue(selectionModel.getSelectedObject().getText());
								ausschreibungBearbeitenDialogBox.center();
								
								} else {
									Window.alert("Bitte wähle zuerst die Ausschreibung aus, die bearbeitet werden soll.");
								}
							
								/**
								 * CLICK-HANDLER für den <code>abbrechenButton</code>
								 */
									abbrechenButton.addClickHandler(new ClickHandler() {
										public void onClick(ClickEvent event) {
											ausschreibungBearbeitenDialogBox.hide();
										}
									});
								
								/**
								 * Clickhandler für den speichern
								 * Zur Bearbeitung werden die ID's in das bearbeiteteAusschreibung Objekt gesetzt
								 */
									speichernButton.addClickHandler(new ClickHandler() {
										public void onClick(ClickEvent event) {
											
							
											AusschreibungProjekt selectedAusschreibung = selectionModel.getSelectedObject();
											if(ausschreibungBezeichungBox.getText() != "" && ausschreibungstextBox.getText() != ""){
												Ausschreibung bearbeiteteAusschreibung = new Ausschreibung();
										
												bearbeiteteAusschreibung.setId(selectedAusschreibung.getIdAusschreibung());
												bearbeiteteAusschreibung.setIdAusschreibender(selectedAusschreibung.getIdAusschreibender());
												
												bearbeiteteAusschreibung.setIdPartnerprofil(selectedAusschreibung.getPartnerprofil());
												bearbeiteteAusschreibung.setIdProjekt(selectedAusschreibung.getIdProjekt());

												if(ausschreibungsStatusBox.getSelectedItemText().equals("laufend")){
													bearbeiteteAusschreibung.setAusschreibungsstatus(Status.laufend);
												}else if(ausschreibungsStatusBox.getSelectedItemText().equals("besetzt")){
													bearbeiteteAusschreibung.setAusschreibungsstatus(Status.besetzt);
												}else if(ausschreibungsStatusBox.getSelectedItemText().equals("abgebrochen")){
													bearbeiteteAusschreibung.setAusschreibungsstatus(Status.abgebrochen);
												}
												
												//bearbeiteteAusschreibung.setId(selectionModel.getSelectedObject().getId());
												//bearbeiteteAusschreibung.setidAusschreibender(selectionModel.getSelectedObject().getidAusschreibender());

												bearbeiteteAusschreibung.setBeschreibung(ausschreibungstextBox.getText());
												bearbeiteteAusschreibung.setEndDatum(ausschreibungBewerbungsfristBox.getValue());
												bearbeiteteAusschreibung.setBezeichnung(ausschreibungBezeichungBox.getText());
										
												
												greetingService.saveAusschreibung(bearbeiteteAusschreibung, new AsyncCallback<Void>() {
													
													
													
													@Override
													public void onFailure(Throwable caught) {
														Window.alert("Das bearbeiten der Ausschreibung ist fehlgeschlagen.");
														
													}

													@Override
													public void onSuccess(Void result) {
														Window.alert("Die Ausschreibung wurde erfolgreich bearbeitet.");
														
														ausschreibungBearbeitenDialogBox.hide();					
														navigator.reload();
													}
												});
											} else{
												Window.alert("Bitte fülle die Felder vollständig aus.");
											}	
										}
								});	
						}
				});
			
			
			/**
			 * Click-Handler für den "Partnerprofil anzeigen/bearbeiten" Button
			 * Weiterleitung auf PartnerprofilByAusschreibungSeite mit der ausgewählten Id.
			 */
			
			partnerprofilBearbeitenButton.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					
					AusschreibungProjekt selectedAusschreibung = selectionModel.getSelectedObject();

					if(selectionModel.getSelectedObject() != null){
						
						//geändert
						idPartnerprofil = selectedAusschreibung.getIdPartnerprofil();
					
						RootPanel.get("Details").clear();
						RootPanel.get("Details").add(new PartnerprofilByAusschreibungSeite(idPartnerprofil,  roleManagement, navigator));
					
						clickhandler = this;
						clickevent = event;

						
						} else {
							Window.alert("Bitte wähle zuerst eine Ausschreibung aus.");
					}
				}
			});
			
			/**
			 * Click-Handler um die eingegangenen Bewerbungen auf eine Ausschreibung einzusehen
			 * Weiterleitung zur BewerbungenAufAusschreibungForm Klasse
			 * @see de.hdm.itProjektSS17.client.gui.BewerbungAufAusschreibungForm
			 */
			bewerbungenAnzeigenButton.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					
					AusschreibungProjekt selectedAusschreibung = selectionModel.getSelectedObject();

					
					if(selectionModel.getSelectedObject() != null){

						RootPanel.get("Anzeige").clear();
						RootPanel.get("Anzeige").add(new BewerbungenAufAusschreibungSeite(selectionModel.getSelectedObject().getIdAusschreibung(), navigator, roleManagement));
						navigator.setCurrentClickHandler(this);
						navigator.setCurrentClickEvent(event);

						
					} else {
						Window.alert("Bitte wähle zuerst eine Ausschreibung aus.");
					}
					
				}
			});
			
			/**
			 * Click-Handler um den Ausschreibungstext der ausgewählten Ausschreibung
			 * in einer DialogBox anzuzeigen
			 */
//			ausschreibungstextButton.addClickHandler(new ClickHandler(){
//				public void onClick(ClickEvent event) {
//					AusschreibungProjekt selectedAusschreibung = selectionModel.getSelectedObject();
//
//					if (selectionModel.getSelectedObject() == null)
//					{
//						Window.alert("Bitte wählen Sie eine Stellenausschreibung aus");
//					}
//					
//					DialogBoxAusschreibungBearbeiten text = new DialogBoxAusschreibungBearbeiten(selectedAusschreibung.getText());
//					int left = Window.getClientWidth() / 3;
//					int top = Window.getClientHeight() / 8;
//					text.setPopupPosition(left, top);
//					text.show();
//					
//			}
//			});
		
			
			
	}
	


	
	public static int getIdPartnerprofilOfSelectedAusschreibung(){
		return idPartnerprofil;
	}
	public static ClickHandler getClickHandlerForBewerbungen(){
		return clickhandler;
	}
	public static ClickEvent getClickEventForBewerbungen(){
		return clickevent;
	}
	


	public class AusschreibungProjekt{
		

			private int idProjekt;
			private String projektname;
			private String bezeichnung;
			private Date endDatum;
			private int idAusschreibung;
			private Status status;
			
			private int idAusschreibender;
			private String Text;
			private int partnerprofil;
			private int idPartnerprofil;
			
	
			public int getIdPartnerprofil() {
				return idPartnerprofil;
			}
			

			public void setIdPartnerprofil(int idPartnerprofil) {
				this.idPartnerprofil = idPartnerprofil;
			}
		
			/**
			 * 
			 * @return partnerprofil
			 */
			public int getPartnerprofil() {
				return partnerprofil;
			}
			/**
			 * 
			 * @param partnerprofil
			 */
			public void setPartnerprofil(int partnerprofil) {
				this.partnerprofil = partnerprofil;
			}
			/**
			 * 
			 * @param text
			 */
			public void setText(String text) {
				Text = text;
			}
			/**
			 * 
			 * @return Text
			 */
			public String getText() {
				return Text;
			}
			/**
			 * 
			 * @param idAusschreibender
			 */
			public void setIdAusschreibender(int idAusschreibender) {
				this.idAusschreibender = idAusschreibender;
			}
			/**
			 * 
			 * @return ausschreibendid
			 */
			public int getIdAusschreibender() {
				return idAusschreibender;
			}
			
			/**
			 * 
			 * @return ausschreibungid
			 */
			public int getIdAusschreibung() {
				return idAusschreibung;
			}
			/**
			 * 
			 * @param ausschreibungid
			 */
			public void setIdAusschreibung(int idAusschreibung) {
				this.idAusschreibung = idAusschreibung;
			}
			/**
			 * 
			 * @return bewerbungsfrist
			 */
			public Date getEndDatum() {
				return endDatum;
			}
			/**
			 * 
			 * @param bewerbungsfrist
			 */
			public void setEndDatum(Date endDatum) {
				this.endDatum = endDatum;
			}
			/**
			 * 
			 * @return bezeichnung
			 */
			public String getBezeichnung() {
				return bezeichnung;
			}
			/**
			 * 
			 * @param bezeichnung
			 */
			 public void setBezeichnung(String bezeichnung) {
				this.bezeichnung = bezeichnung;
			}
			/**
			 * 
			 * @returnIdProjekt
			 */
			public int getIdProjekt() {
				return idProjekt;
			}
			/**
			 * 
			 * @param IdProjekt
			 */
			public void setIdProjekt(int idProjekt) {
				this.idProjekt = idProjekt;
			}
			/**
			 * 
			 * @return projektname
			 */
			public String getProjektname() {
				return projektname;
			}
			/**
			 * 
			 * @param projektname
			 */
			public void setProjektname(String projektname) {
				this.projektname = projektname;
			}
			public Status getStatus() {
				return status;
			}
			public void setStatus(Status status) {
				this.status = status;
			}
			
			
			
		}

}

	
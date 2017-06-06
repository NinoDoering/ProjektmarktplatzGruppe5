package de.hdm.itprojekt.shared.report;

	
	import java.util.Vector;

	

	public class CompositeReport extends Report{

	
		private static final long serialVersionUID = 1L;

		// Speichen der Teilreports in einen Vector Objekt
		private Vector<Report> subReports = new Vector<Report>();
		
		
	
		public void addSubReport(Report r){
			this.subReports.addElement(r);
		}
		
	
		public void removeSubReport(Report r) {
			this.subReports.removeElement(r);
		}
		
		
		public int getSubReportsSize() {
			return this.subReports.size();
		}
		
	
		public Report getSubReportByIndex(int i) {
			return this.subReports.elementAt(i);
	}

}

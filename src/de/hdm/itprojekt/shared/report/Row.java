package de.hdm.itprojekt.shared.report;
import java.io.Serializable;
import java.util.Vector;


public class Row implements Serializable {
	
	
	private static final long serialVersionUID = 1L;

	private Vector<Column> columns = new Vector<Column>();
	
	

	public void addColumn(Column c){
		this.columns.addElement(c);
	}
	

	public void removeColumn(Column c){
		this.columns.removeElement(c);
	}
	

	public Vector<Column> getColumns(){
		return this.columns;
	}
	

	public int getColumnsSize(){
		return this.columns.size();
	}
	
	/**
	 * Auslesen eines einzelnen Spalten Objekts
	 * i der Index der auszulesenden Spalte
	 * zurückgeben des gewünschten Spalten Objekts
	 */
	public Column getColumnByIndex(int i){
		return this.columns.elementAt(i);
}

}

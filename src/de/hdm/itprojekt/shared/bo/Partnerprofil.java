package de.hdm.itprojekt.shared.bo;

public class Partnerprofil extends BusinessObject {
	
	private static final long serialVersionUID = 1L; //?
	
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String toString(){
		return super.toString() + this.id;
	}
}

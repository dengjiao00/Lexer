package bean;

import java.io.Serializable;

public class Input implements Serializable{
	private int id;
	private String name_in;
	public int getId() {
		return id;
	}
	public String getName_in() {
		return name_in;
	}
	public void setName_in(String name_in) {
		this.name_in = name_in;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}

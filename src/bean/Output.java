package bean;

import java.io.Serializable;

public class Output implements Serializable {
	private int id;
	private String name_out;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName_out() {
		return name_out;
	}
	public void setName_out(String name_out) {
		this.name_out = name_out;
	}
}

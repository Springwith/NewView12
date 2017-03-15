package admi.newview.view;

import java.io.Serializable;

public class SectorItem implements Serializable {
	private int startAngle;
	private int endAngle;
	private String name;

	public int getStartAngle() {
		return startAngle;
	}

	public void setStartAngle(int startAngle) {
		this.startAngle = startAngle;
	}

	public int getEndAngle() {
		return endAngle;
	}

	public void setEndAngle(int endAngle) {
		this.endAngle = endAngle;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
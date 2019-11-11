package model;

import java.util.Comparator;

public class Stadium implements Comparable<Stadium>, Comparator<Stadium>{
	
	private String name;
	private int capacity;
	private int area;
	
	public Stadium(String name, int capacity, int area) {
		
		this.name = name;
		this.capacity = capacity;
		this.area = area;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public int getArea() {
		return area;
	}

	public void setArea(int area) {
		this.area = area;
	}

	@Override
	public String toString() {
		return "Stadium [name=" + name + ", capacity=" + capacity + ", area=" + area + "]";
	}

	@Override
	public int compareTo(Stadium o) {
		return capacity-o.capacity;
	}

	@Override
	public int compare(Stadium o1, Stadium o2) {
		return o1.area-o2.area;
	}
}

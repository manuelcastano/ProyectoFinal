package model;

import java.util.Comparator;

public class Stadium implements Comparable<Stadium>, Comparator<Stadium>{
	
	private String name;
	private int capacity;
	private double area;
	
	public Stadium(String name, int capacity, double area) {
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

	public double getArea() {
		return area;
	}

	public void setArea(double area) {
		this.area = area;
	}

	@Override
	public String toString() {
		return name + "," + capacity + "," + area;
	}

	@Override
	public int compareTo(Stadium o) {
		return capacity-o.capacity;
	}

	@Override
	public int compare(Stadium o1, Stadium o2) {
		return (int) (o1.area-o2.area);
	}
	
	public String stadiumCapacity() {
		return name + "\t" + capacity;
	}
	
	public String stadiumArea() {
		return name + "\t" + area;
	}
	
	public String search() {
		return "Name: "+ name + "\n" + "Capacity: " + capacity + "\n" + "Area: " + area;
	}
}

package model;

public class Ball {
	
	private String color;
	private String type;
	private String id;
	//ABB
	private Ball left;
	private Ball right;
	
	public Ball(String color, String type, String id) {
		
		this.color = color;
		this.type = type;
		this.id = id;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Ball getLeft() {
		return left;
	}

	public void setLeft(Ball left) {
		this.left = left;
	}

	public Ball getRight() {
		return right;
	}

	public void setRight(Ball right) {
		this.right = right;
	}
	
	

}

package model;

public class Technical extends Person {
	
	private Technical left;
	private Technical right;
	private int hoursWorked;

	public Technical(String name, String id, double salary, int hoursWorked) {
		super(name, id, salary);
		this.hoursWorked = hoursWorked;
	}

	public Technical getLeft() {
		return left;
	}

	public void setLeft(Technical left) {
		this.left = left;
	}

	public Technical getRight() {
		return right;
	}

	public void setRight(Technical right) {
		this.right = right;
	}

	public int getHoursWorked() {
		return hoursWorked;
	}

	public void setHoursWorked(int hoursWorked) {
		this.hoursWorked = hoursWorked;
	}
	
	public boolean isSheet() {
		return (left == null) && (right == null);
	}
	
	public Technical getMinor( )
    {
        return (left == null) ? this : left.getMinor();
    }
	
	public Technical eliminateTechnical( String nameTechnical )
    {
        if(isSheet())
            return null;
        if(getName().compareToIgnoreCase(nameTechnical) == 0 ) {
            if(left == null )
                return right;
            if( right == null )
                return left;
            Technical successor = right.getMinor();
            right = right.eliminateTechnical(successor.getName());
            successor.left = left;
            successor.right = right;
            return successor;
        }
        else if(getName().compareToIgnoreCase(nameTechnical) > 0 )
            left = left.eliminateTechnical(nameTechnical);
        else
            right = right.eliminateTechnical(nameTechnical);
        return this;
    }
}

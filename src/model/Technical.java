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
	
	public Technical searchTechnicalByName(String nameTechnical) {
		if(getName().equals(nameTechnical)) {
			return this;
		}
		else if(getName().compareTo(nameTechnical) > 0) {
			if(left != null) {
				return left.searchTechnicalByName(nameTechnical);
			}
			else {
				return null;
			}
		}
		else {
			if(right != null) {
				return right.searchTechnicalByName(nameTechnical);
			}
			else {
				return null;
			}
		}
	}
	
	public String searchTechnicalsByPosition(String position) {
		String msg = "";
		if(left != null) {
			msg += left.searchTechnicalsByPosition(position);
		}
		if(position.equals("Coach")) {
			if(this instanceof Coach) {
				msg += toString();
			}
		}
		else if(position.equals("Technical Assitant")) {
			if(this instanceof TechnicalAssistant) {
				msg += toString();
			}
		}
		else if(position.equals("Physical Trainer")) {
			if(this instanceof PhysicalTrainer) {
				msg += toString();
			}
		}
		if(right != null) {
			msg += right.searchTechnicalsByPosition(position);
		}
		return msg;
	}

	@Override
	public String toString() {
		return left + "," + right + "," + hoursWorked;
	}

	public void addTechnical(Technical newTechnical )  {
		if (compareTo(newTechnical)>0) {	
			if (left == null) {
				left = newTechnical;
			}
			else {
				left.addTechnical(newTechnical);
				}
		}else if (compareTo(newTechnical)<0) {
			if (right==null) {
				right=newTechnical;
			}else {
				right.addTechnical(newTechnical);
			}
		}		
	}
	
	private int compareTo(Technical newTechnical) {
		
		 return this.getId().compareToIgnoreCase(newTechnical.getId());
	}

	
}

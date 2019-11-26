package model;

public class Technical extends Person implements Tax , Holiday {
	
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
		return getName() + "," + getId() + "," + getSalary() + "," + hoursWorked;
	}

	public void addTechnical(Technical newTechnical ) throws ElementExistException  {
		if (compareTo(newTechnical)>0) {	
			if (left == null) {
				left = newTechnical;
			}
			else {
				left.addTechnical(newTechnical);
				}
		}
		else if (compareTo(newTechnical)<0) {
			if (right==null) {
				right=newTechnical;
			}
			else {
				right.addTechnical(newTechnical);
			}
		}
		else {
			throw new ElementExistException();
		}
	}
	
	private int compareTo(Technical newTechnical) {
		 return getId().compareTo(newTechnical.getId());
	}

	public Technical updateWonGames(String nameTechnical, int numberWonGames) {
		if(this instanceof Coach && getName().equals(nameTechnical)) {
			Coach c = (Coach) this;
			c.setWonGames(numberWonGames);
			return this;
		}
		else if(getName().compareTo(nameTechnical) > 0) {
			if(left != null) {
				left.updateWonGames(nameTechnical, numberWonGames);
			}
			else {
				return null;
			}
		}
		else if(getName().compareTo(nameTechnical) < 0) {
			if(right != null) {
				right.updateWonGames(nameTechnical, numberWonGames);
			}
			else {
				return null;
			}
		}
		return null;
	}

	@Override
	public double Taxes() {
		double salaryMin = 828116;
		double tax=0;
		if(this.getSalary()>=salaryMin) {
			if (hoursWorked <= 100) {
				tax = this.getSalary()* 0.12;	
			}
			else{
				tax = this.getSalary()*0.19;
			}
		}
		return tax;
	}
	
	//vaciones por mes  
	@Override
	public int day() {
		int day = 0;
		int dayByMonth = 6*22;
		if (hoursWorked == dayByMonth ) {
			day = 3;
		}else  if (hoursWorked >dayByMonth){
			day = 5;
		}
		return day;
	}	
}

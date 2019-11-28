package model;

public class Technical extends Person implements Tax , Holiday {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1826024285119332171L;
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
	
	public Technical eliminateTechnical( String nameTechnical ) throws EliminateException
    {
        if(getName().compareTo(nameTechnical) == 0 ) {
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
        if(isSheet())
            throw new EliminateException();
        else if(getName().compareTo(nameTechnical) > 0 ) {
            if(left != null) {
            	left = left.eliminateTechnical(nameTechnical);
            }
        }
        else {
            if(right != null) {
            	right = right.eliminateTechnical(nameTechnical);
            }
        }
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
				msg += search()+"\n";
			}
		}
		else if(position.equals("Technical Assitant")) {
			if(this instanceof TechnicalAssistant) {
				msg += search()+"\n";
			}
		}
		else if(position.equals("Physical Trainer")) {
			if(this instanceof PhysicalTrainer) {
				msg += search()+"\n";
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
	public double taxes() {
		double salaryMin = 828116;
		double tax = 0;
		if(this.getSalary() >= salaryMin) {
			if (hoursWorked <= 100) {
				tax = this.getSalary() * 0.19;	
			}
			else{
				tax = this.getSalary()*0.12;
			}
		}
		return tax;
	}
	
	public String theTaxes() {
		String msg = "";
		if(left != null) {
			msg += left.theTaxes();
		}
		msg += getName() + "\t" + taxes() + "\n";
		if(right != null) {
			msg += right.theTaxes();
		}
		return msg;
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
	
	public String holidays() {
		String msg = "";
		if(left != null) {
			msg += left.holidays();
		}
		msg += getName() + "\t" + day() + "\n";
		if(right != null) {
			msg += right.holidays();
		}
		return msg;
	}
	
	public String search() {
		return "Name: " + getName() + "\n" + "Id: " + getId() + "\n" + "Salary: " + getSalary() + "\n" + "Hours worked: " + hoursWorked;
	}
}

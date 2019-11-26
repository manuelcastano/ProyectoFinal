package model;

public class Ball implements Comparable<Ball>{
	
	private String color;
	private String type;
	private String id;
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
	

	public void addBall(Ball b) throws ElementExistException {
		if(compareTo(b) < 0) {
			if(right == null) {
				right = b;
			}
			else {
				right.addBall(b);
			}
		}
		else if(compareTo(b) > 0) {
			if(left == null) {
				left = b;
			}
			else {
				left.addBall(b);
			}
		}
		else {
			throw new ElementExistException();
		}
	}

	@Override
	public String toString() {
		return color + "," + type + "," + id;
	}

	@Override
	public int compareTo(Ball b) {
		return id.compareTo(b.id);
	}
	
	public boolean isSheet() {
		return (left == null) && (right == null);
	}
	
	public Ball getMinor( )
    {
        return (left == null) ? this : left.getMinor();
    }
	
	public Ball eliminateBall( String idToEliminate ) throws EliminateException
    {
        if( id.compareTo(idToEliminate) == 0 ) {
            if(left == null )
                return right;
            if( right == null )
                return left;
            Ball successor = right.getMinor();
            right = right.eliminateBall(successor.getId());
            successor.left = left;
            successor.right = right;
            return successor;
        }
        if( isSheet( ))
            throw new EliminateException();
        else if(id.compareTo(idToEliminate) > 0 ) {
            if(left != null)
            	left = left.eliminateBall(idToEliminate);
        }
        else {
            if(right != null)
            	right = right.eliminateBall(idToEliminate);
        }
        return this;
    }
	
	//recursivo
	public Ball searchBallById(String idBall) {
		if (id.compareTo(idBall) == 0) {
			return this;
		}
		else if (id.compareTo(idBall) > 0){
			if (left != null) {
				return left.searchBallById(idBall);
			}else {
				return null;
			}
		}
		else {
			if (right != null) {
				return right.searchBallById(idBall);	
			}
			else {
				return null;
			}
		}
	}
	
	//Recursivo busca por color 
	public Ball searchBallByColor(String colorr) throws NotFindedException {
		if(color.equals(colorr)) {
			return this;
		}
		if(left != null) {
			Ball b = left.searchBallByColor(colorr);
			if(b != null) {
				return b;
			}
		}
		if(right != null) {
			Ball b = right.searchBallByColor(colorr);
			if(b != null) {
				return b;
			}
		}
		return null;
	}
	
	//Actualiza el tipo del balon
	public Ball updateTypeBall(String id, String newType) {
		if (this.id.compareTo(id) == 0) {
			this.setType(newType);
			return this;
		}
		else if (this.id.compareTo(id)>0) {
			return  (left == null) ? null : left.updateTypeBall(id, newType);
		}
		else {
			return (right == null) ? null : right.updateTypeBall(id, newType);
		}
	}
	
	public String search() {
		return "Id: "+ id + "\n" + "Type: " + type + "\n" + "Color: " + color;
	}
}

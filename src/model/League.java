package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

public class League {
	
	public final static String CLUB_FILE = "clubs.txt";
	public final static String STADIUM_FILE = "stadiums.txt";
	public final static String BALL_FILE = "balls.txt";
	public final static String REFEREE_FILE = "referees.txt";
	
	//Relations
	private ArrayList<Club> clubs;
	private ArrayList<Stadium> stadium;
	private Ball firstBall;
	private Referee firstReferee;
	
	//Constructor
	
	public League() {
		clubs = new ArrayList<Club>();
		stadium = new ArrayList<Stadium>();
	}

	public ArrayList<Club> getClubs() {
		return clubs;
	}

	public void setClubs(ArrayList<Club> clubs) {
		this.clubs = clubs;
	}

	public ArrayList<Stadium> getStadium() {
		return stadium;
	}

	public void setStadium(ArrayList<Stadium> stadium) {
		this.stadium = stadium;
	}

	public Ball getFirstBall() {
		return firstBall;
	}

	public void setFirstBall(Ball firstBall) {
		this.firstBall = firstBall;
	}

	public Referee getFirstReferee() {
		return firstReferee;
	}

	public void setFirstReferee(Referee firstReferee) {
		this.firstReferee = firstReferee;
	}
	
	public void addClub(Club c) throws FileNotFoundException, IOException {
		clubs.add(c);
		writeClubs();
	}
	
	public void addStadium(Stadium s) throws IOException {
		stadium.add(s);
		writeStadiums();
	}
	
	public void addBall(Ball b) throws ElementExist, IOException {
		if(firstBall == null) {
			firstBall = b;
		}
		else {
			firstBall.addBall(b);
		}
		writeBall(b);
	}
	
	public void addReferee(Referee r) throws IOException {
		if(firstReferee == null) {
			firstReferee = r;
		}
		else {
			r.setNext(firstReferee);
			firstReferee.setPrev(r);
			firstReferee = r;
		}
		writeReferees();
	}
	
	public void loadBalls() throws IOException, ElementExist {
		File f = new File(BALL_FILE);
		BufferedReader bw = new BufferedReader(new FileReader(f));
		String line = "";
		while((line = bw.readLine()) != null) {
			String[] s = line.split(",");
			Ball b = new Ball(s[0], s[1], s[2]);
			if(firstBall == null) {
				firstBall = b;
			}
			else {
				firstBall.addBall(b);
			}
		}
		bw.close();
	}
	
	public void loadStadiums() throws IOException {
		File f = new File(STADIUM_FILE);
		BufferedReader bw = new BufferedReader(new FileReader(f));
		String line = "";
		while((line = bw.readLine()) != null) {
			String[] s = line.split(",");
			Stadium b = new Stadium(s[0], Integer.parseInt(s[1]), Integer.parseInt(s[2]));
			stadium.add(b);
		}
		bw.close();
	}
	
	public void loadReferees() throws IOException {
		File f = new File(REFEREE_FILE);
		BufferedReader bw = new BufferedReader(new FileReader(f));
		String line = "";
		while((line = bw.readLine()) != null) {
			String[] s = line.split(",");
			Referee r = new Referee(s[0], s[1], Double.parseDouble(s[2]), Integer.parseInt(s[3]), Integer.parseInt(s[4]),Integer.parseInt(s[5]));
			if(firstReferee == null) {
				firstReferee = r;
			}
			else {
				r.setNext(firstReferee);
				firstReferee.setPrev(r);
				firstReferee = r;
			}
		}
		bw.close();
	}
	
	public void loadClubs() throws FileNotFoundException, IOException, ClassNotFoundException {
		File f = new File(CLUB_FILE);
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
		clubs = (ArrayList<Club>)ois.readObject();
		ois.close();
	}
	
	public void writeClubs() throws FileNotFoundException, IOException {
		File f = new File(CLUB_FILE);
		ObjectOutputStream ois = new ObjectOutputStream(new FileOutputStream(f));
		ois.writeObject(clubs);
		ois.close();
	}
	
	public void writeReferees() throws IOException {
		File f = new File(REFEREE_FILE);
		BufferedWriter bw = new BufferedWriter(new FileWriter(f, true));
		Referee actual = firstReferee;
		while(actual != null) {
			bw.write(actual.toString());
			actual = actual.getNext();
		}
		bw.close();
	}
	
	public void writeStadiums() throws IOException {
		File f = new File(STADIUM_FILE);
		BufferedWriter bw = new BufferedWriter(new FileWriter(f, true));
		for(int i = 0; i < stadium.size(); i++) {
			bw.write(stadium.get(i).toString());
		}
		bw.close();
	}
	
	public void writeBall(Ball b) throws IOException {
		File f = new File(CLUB_FILE);
		BufferedWriter bw = new BufferedWriter(new FileWriter(f, true));
		bw.write(b.toString());
		bw.close();
	}
	
	public void eliminateReferee(String name) throws IOException {
		Referee actual = firstReferee;
		boolean finded = false;
		while(actual != null && !finded) {
			if(actual.getName().equals(name)) {
				if(actual.getPrev() != null) {
					actual.getPrev().setNext(actual.getNext());
				}
				if(actual.getNext() != null) {
					actual.getNext().setPrev(actual.getPrev());
				}
				finded = true;
				File f = new File(REFEREE_FILE);
				File tempFile = new File(f.getAbsolutePath()+".txt");
				BufferedReader br = new BufferedReader(new FileReader(f));
		        PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
		        String line = null;
		        while ((line = br.readLine()) != null) {
		            if (!line.trim().equals(actual.toString())) {
		                pw.println(line);
		                pw.flush();
		            }
		        }
		        pw.close();
		        br.close();
		        f.delete();
		        tempFile.renameTo(f);
			}
			actual = actual.getNext();
		}
	}
	
	public void eliminateBall(String idBall) throws IOException {
		if(firstBall != null) {
			firstBall = firstBall.eliminateBall(idBall);
			File f = new File(BALL_FILE);
			File tempFile = new File(f.getAbsolutePath()+".txt");
			BufferedReader br = new BufferedReader(new FileReader(f));
	        PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
	        String line = null;
	        while ((line = br.readLine()) != null) {
	        	String[] s = line.split(",");
	            if (s[2].equals(idBall)) {
	                pw.println(line);
	                pw.flush();
	            }
	        }
	        pw.close();
	        br.close();
	        f.delete();
	        tempFile.renameTo(f);
		}
	}
	
	public void eliminateStadium(String nameStadium) throws IOException {
		boolean finded = false;
		for(int i = 0; i < stadium.size() && !finded; i++) {
			if(stadium.get(i).getName().equals(nameStadium)) {
				File f = new File(STADIUM_FILE);
				File tempFile = new File(f.getAbsolutePath()+".txt");
				BufferedReader br = new BufferedReader(new FileReader(f));
		        PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
		        String line = null;
		        while ((line = br.readLine()) != null) {
		            if (!line.trim().equals(stadium.get(i).toString())) {
		                pw.println(line);
		                pw.flush();
		            }
		        }
		        pw.close();
		        br.close();
		        f.delete();
		        tempFile.renameTo(f);
				stadium.remove(i);
				finded = true;			
			}
		}
	}
	
	public void eliminateTechnical(String clubName, String nameTechnical) throws FileNotFoundException, IOException {
		boolean finded = false;
		for(int i = 0; i < clubs.size() && !finded; i++) {
			if(clubs.get(i).getName().equals(clubName)) {
				finded = true;
				clubs.get(i).eliminateTechnical(nameTechnical);
				writeClubs();
			}
		}
	}
	
	//By burble
	public void orderClubsByPoints() {
		for(int i = clubs.size(); i > 0; i--) {
			for(int j = 0; j < i-1; j++) {
				if(clubs.get(i).compare(clubs.get(i), clubs.get(i+1)) > 0) {
					Club temp = clubs.get(i);
					clubs.set(j, clubs.get(j+1));
					clubs.set(j+1, temp);
				}
			}
		}
	}
	
	//bySeleccion
	public void orderClubsBynames() {
		
			for(int i = 0; i < clubs.size()-1; i++) {
				Club less = clubs.get(i);
				int aux= i;
				for(int j = i+1; j < clubs.size(); j++) {
					if(less.compareBynames(clubs.get(j)) > 0) {
						less = clubs.get(j);
						aux = j;
					}
				}
				Club temp = clubs.get(i);
				clubs.set(i, less);
				clubs.set(aux, temp);
			}
		}
	
	
	//Binario
	public Club searchClub(String nameClub) {
		boolean finded = false;
		Club club = null;
		int star = 0;
		int end = clubs.size()-1;
		while(star <= end && ! finded) {
			
			int middle = (star + end)/2;
			
			if (clubs.get(middle).getName().equals(nameClub)) {
				club = clubs.get(middle);
				finded =true;
			}else if (stadium.get(middle).getName().compareTo(nameClub)>0) {
			
				end = middle -1;
		
			}else {
				
				star= middle+1;
			}
		}	
		return club;
	}
	
	
	//Binario
	public Stadium searchEstadium(String name) {
		boolean finded = false;
		Stadium stadiums = null;
		int star = 0;
		int end = stadium.size()-1;
		while(star <= end && ! finded) {
			
			int middle = (star + end)/2;
			
			if (stadium.get(middle).getName().equals(name)) {
				stadiums = stadium.get(middle);
				finded =true;
			}else if (stadium.get(middle).getName().compareTo(name)>0) {
				end = middle -1;
			}else {	
				star= middle+1;
			}
		}	
		return stadiums;
	}
	
	//recursivo
	public Ball searchBall (String idBall) {
		
		if (firstBall != null) {
			return firstBall.searchBall(idBall);
		}else {
			return null;
		}
	}
	
	//recursivo
	public Ball searchBallByBrand (String color) {
		
		if (firstBall != null) {
			return firstBall.searchBallByColor(color);
		}else {
			return null;
		}
	}
	
	
	
	
	
	
}

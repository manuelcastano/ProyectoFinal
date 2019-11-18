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
	
	public League() throws IOException, ElementExistException, ClassNotFoundException {
		clubs = new ArrayList<Club>();
		stadium = new ArrayList<Stadium>();
		loadClubs();
		loadStadiums();
		loadBalls();
		loadReferees();
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
	
	public void addClub(Club c) throws FileNotFoundException, IOException, ElementExistException {
		if(!clubExist(c.getName())) {
			clubs.add(c);
			writeClubs();
		}
		else {
			throw new ElementExistException();
		}
	}
	
	public void addStadium(Stadium s) throws IOException, ElementExistException {
		if(!stadiumExist(s.getName())) {
			stadium.add(s);
			writeStadiums();
		}
		else {
			throw new ElementExistException();
		}
	}
	
	public boolean stadiumExist(String nameStadium) {
		boolean finded = false;
		for(int i = 0; i < stadium.size() && !finded; i++) {
			if(stadium.get(i).getName().equals(nameStadium)) {
				finded = true;
			}
		}
		return finded;
	}
	
	public void addBall(Ball b) throws ElementExistException, IOException {
		if(firstBall == null) {
			firstBall = b;
		}
		else {
			firstBall.addBall(b);
		}
		writeBall(b);
	}
	
	public void addReferee(Referee r) throws IOException, ElementExistException {
		if(firstReferee == null) {
			firstReferee = r;
		}
		else {
			if(!refereeExist(r.getName())) {
				r.setNext(firstReferee);
				firstReferee.setPrev(r);
				firstReferee = r;
			}
			else {
				throw new ElementExistException();
			}
		}
		writeReferees();
	}
	
	public boolean refereeExist(String nameReferee) {
		boolean finded = false;
		Referee actual = firstReferee;
		while(actual != null && !finded) {
			if(actual.getName().equals(nameReferee)) {
				finded = true;
			}
			actual = actual.getNext();
		}
		return finded;
	}
	
	public void loadBalls() throws IOException, ElementExistException {
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
	
	public void eliminateReferee(String name) throws IOException, EliminateException {
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
		if(!finded) {
			throw new EliminateException();
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
	
	public void eliminateStadium(String nameStadium) throws IOException, EliminateException {
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
		if(!finded) {
			throw new EliminateException();
		}
	}
	
	public void eliminateClub(String nameClub) throws EliminateException, FileNotFoundException, IOException {
		boolean finded = false;
		for(int i = 0; i < clubs.size() && ! finded ; i++) {
			if(clubs.get(i).getName().equals(nameClub)) {
				clubs.remove(i);
				writeClubs();
				finded = true;			
			}
		}
		if(!finded) {
			throw new EliminateException();
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
	
	//By selection
	public void orderClubsByPoints() {
		for(int i = 0; i < clubs.size()-1; i++) {
			Club less = clubs.get(i);
			int aux= i;
			for(int j = i+1; j < clubs.size(); j++) {
				if(less.compare(less, clubs.get(j)) > 0) {
					less = clubs.get(j);
					aux = j;
				}
			}
			Club temp = clubs.get(i);
			clubs.set(i, less);
			clubs.set(aux, temp);
		}
	}
	
	//bySeleccion
	public void orderClubsBynames() {
		for(int i = 0; i < clubs.size()-1; i++) {
			Club less = clubs.get(i);
			int aux= i;
			for(int j = i+1; j < clubs.size(); j++) {
				if(less.compareTo(clubs.get(j)) > 0) {
					less = clubs.get(j);
					aux = j;
				}
			}
			Club temp = clubs.get(i);
			clubs.set(i, less);
			clubs.set(aux, temp);
		}
	}

	
	//Busqueda binaria
	public Club searchClubByName(String nameClub) throws NotFindedException {
		boolean finded = false;
		Club club = null;
		int start = 0;
		int end = clubs.size()-1;
		while(start <= end && ! finded) {
			int middle = (start + end)/2;
			if (clubs.get(middle).getName().equals(nameClub)) {
				club = clubs.get(middle);
				finded =true;
			}
			else if (stadium.get(middle).getName().compareTo(nameClub)>0) {
				end = middle -1;
			}
			else {
				start= middle+1;
			}
		}	
		if(!finded) {
			throw new NotFindedException();
		}
		return club;
	}
	
	
	//Busqueda Binaria
	public Stadium searchStadiumByName(String name) throws NotFindedException {
		boolean finded = false;
		Stadium stadiums = null;
		int start = 0;
		int end = stadium.size()-1;
		while(start <= end && ! finded) {
			int middle = (start + end)/2;
			if (stadium.get(middle).getName().equals(name)) {
				stadiums = stadium.get(middle);
				finded =true;
			}
			else if (stadium.get(middle).getName().compareTo(name)>0) {
				end = middle -1;
			}
			else {	
				start= middle+1;
			}
		}
		if(!finded) {
			throw new NotFindedException();
		}
		return stadiums;
	}
	
	//recursivo
	public Ball searchBallById(String idBall) throws NotFindedException {
		if (firstBall != null) {
			Ball b = firstBall.searchBallById(idBall);
			if(b == null) {
				throw new NotFindedException();
			}
			else {
				return b;
			}
		}
		else {
			return null;
		}
	}
	
	//recursivo
	public Ball searchBallByColor (String color) throws NotFindedException {
		if (firstBall != null) {
			Ball b = firstBall.searchBallByColor(color);
			if(b == null) {
				throw new NotFindedException();
			}
			else {
				return b;
			}
		}
		else {
			return null;
		}
	}
	
	//insertion
	public void orderStadiumsByCapacity() {
		for(int i = 1; i < stadium.size(); i++) {
			for(int j = 1; j > 0 && (stadium.get(j-1).compareTo(stadium.get(j))) > 0; j--){
				Stadium temp = stadium.get(j);
				stadium.set(j, stadium.get(j-1));
				stadium.set(j-1, temp);
			}
		}
	}
	
	//insertion
	public void orderStadiumsByArea() {
		for(int i = 1; i < stadium.size(); i++) {
			for(int j = 1; j > 0 && (stadium.get(j-1).compare(stadium.get(j-1), stadium.get(j))) > 0; j--){
				Stadium temp = stadium.get(j);
				stadium.set(j, stadium.get(j-1));
				stadium.set(j-1, temp);
			}
		}
	}
	
	public void orderPlayersByGoals() {
		for(int i = 0; i < clubs.size(); i++) {
			clubs.get(i).orderPlayersByGoals();
		}
	}
	
	public void orderPlayersByAssists() {
		for(int i = 0; i < clubs.size(); i++) {
			clubs.get(i).orderPlayersByAssists();
		}
	}
	
	public Technical searchTechnicalByName(String nameTechnical, String nameClub) throws NotFindedException {
		Technical t = null;
		boolean finded = false;
		for(int i = 0; i < clubs.size() && !finded; i++) {
			if(clubs.get(i).getName().equals(nameClub)) {
				t = clubs.get(i).searchTechnicalByName(nameTechnical);
			}
		}
		return t;
	}
	
	public String searchTechnicalsByPosition(String position, String nameClub) throws NotFindedException {
		String msg = "";
		boolean finded = false;
		for(int i = 0; i < clubs.size() && !finded; i++) {
			if(clubs.get(i).getName().equals(nameClub)) {
				msg = clubs.get(i).searchTechnicalsByPosition(position);
			}
		}
		return msg;
	}
	
	public boolean updateNameClub(String nameClub,String newNameClub) {
		boolean update =false;
		for (int i = 0; i < clubs.size() && ! update; i++) {
			if (clubs.get(i).getName().contentEquals(nameClub)) {
				clubs.get(i).setName(newNameClub);
				update=true;
			}	
		}
		return update;
	}
	
	//responsability add player
	public void addPlayer (String nameClub,Player newPlayer) throws ElementExistException {
		boolean finded = false;
		for (int i = 0;i<clubs.size() && !finded ;i ++) {
			if (clubs.get(i).getName().equals(nameClub)) {
				clubs.get(i).addPlayer(newPlayer);
				finded =true;
			}
		}
	}
	//responsability updateGoalsPlayer
	public boolean updateNumberGoalsPlayer(String nameClub,String namePlayer,int numberGoals) {
		boolean finded = false;
		for (int i = 0; i< clubs.size() && !finded ;i++) {
			if (clubs.get(i).getName().equals(nameClub)) {
				clubs.get(i).updateGoalsPlayer(namePlayer, numberGoals);
				finded =true;
			}		
		}
		return finded;
	}
	//Actualiza el tipo del balon
	public Ball updateTypeBall (String idBall , String newBallType ) {
		if (firstBall == null) {
			return null;
		}
		else {
			return firstBall.updateTypeBall(idBall , newBallType);
		}
		
	}
	//update Name estadium
	public boolean updateNameStadium(String nameStadium ,String newNameStadium) {
		boolean finded = false;
		for (int i = 0 ; i < stadium.size() && !finded  ; i++ ) {
			if (stadium.get(i).getName().equals(nameStadium)) {
				stadium.get(i).setName(newNameStadium);
				finded =true;
			}
		}
		return finded;
	}
	public boolean eliminatePlayer(String nameClan,String namePlayer) throws EliminateException, FileNotFoundException, IOException {
		boolean finded = false;
		for (int i = 0 ; i <clubs.size() && !finded ; i++) {
			if (clubs.get(i).getName().equals(nameClan)) {
				finded = clubs.get(i).eliminatePlayer(namePlayer);
				writeClubs();
			}
		}
		return finded;
	}
	//
	public boolean addTechnical(String nameClan,Technical technical) throws ElementExistException {
		boolean finded = false;
		for (int i = 0; i<clubs.size() && !finded ;i++) {
			if (clubs.get(i).getName().equals(nameClan)) {
				 clubs.get(i).addTechnical(technical);
				 finded = true;
			}	
		}
		return finded;
	}
	//Actualiza el numero de faltas de un arbitro
	
	public boolean updateNumberFouls(String nameReferee , int newNumberReferee) {
		boolean finded = false;
		Referee actual = firstReferee;
		while(actual != null && !finded) {
			if (actual.getName().contentEquals(nameReferee)) {
				actual.setFouls(newNumberReferee);
				finded = true;				
			}
			actual = actual.getNext();
		}
		return finded;
	}
	
	public boolean clubExist(String nameClub) {
		boolean exist = false;
		for(int i = 0; i < clubs.size() && !exist; i++) {
			if(clubs.get(i).getName().equals(nameClub)) {
				exist = true;
			}
		}
		return exist;
	}
}

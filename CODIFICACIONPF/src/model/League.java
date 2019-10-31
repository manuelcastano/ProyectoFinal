package model;

import java.util.ArrayList;

public class League {
	
	
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

}

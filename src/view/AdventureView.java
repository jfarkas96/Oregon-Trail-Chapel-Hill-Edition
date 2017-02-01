package view;

import java.awt.*;
import javax.swing.*;

import person.*;
import model.*;

public class AdventureView extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Adventure adventure;
	private PersonView[] pvs;
 	
	public AdventureView(Adventure adventure) {
		this.adventure = adventure;
		
		setLayout(new GridBagLayout());
		
		Squad s = adventure.getSquad();
		Person[] players = new Person[s.getNumPlayers()];
		pvs = new PersonView[s.getNumPlayers()];
		
		//container for squad info and other info
		JPanel statusBar = new JPanel();
		statusBar.setLayout(new GridLayout(0,1,0,0));
		GridBagConstraints statusGBC = new GridBagConstraints();
		statusGBC.gridx = 0;
		statusGBC.gridy = 0;
		statusGBC.fill = GridBagConstraints.BOTH;
		
		// adds info for each player
		for (int i=0; i<s.getNumPlayers(); i++) {
			players[i] = s.getPlayer(i);
			PersonView pv = new PersonView(players[i]);
			pvs[i] = pv;
			statusBar.add(pv);
		}
		
		// adds Day, Current City, and Balance info
		StatsView stats = new StatsView(this.adventure);
		this.adventure.addTravelObserver(stats);
		this.adventure.getSquad().getKnapsack().addObserver(stats);
		statusBar.add(stats);
		
		add(statusBar, statusGBC);
		
		// adds Knapsack panel
		KnapsackView knapsackItems = new KnapsackView(this.adventure);
		this.adventure.getSquad().getKnapsack().addObserver(knapsackItems);
		GridBagConstraints knapGBC = new GridBagConstraints();
		knapGBC.gridx = 1;
		knapGBC.gridy = 0;
		knapGBC.fill = GridBagConstraints.BOTH;
		
		add(knapsackItems, knapGBC);
		
		// adds panel for output
		OutputView output = new OutputView();
		this.adventure.addTravelObserver(output);
		GridBagConstraints outputGBC = new GridBagConstraints();
		outputGBC.gridx = 0;
		outputGBC.gridy = 1;
		outputGBC.gridwidth = 2;
		outputGBC.fill = GridBagConstraints.HORIZONTAL;
		
		add(output, outputGBC);
		
		// adds panel that allows travelling
		TravelControlsView travel = new TravelControlsView(this.adventure);
		GridBagConstraints travelGBC = new GridBagConstraints();
		travelGBC.gridx = 0;
		travelGBC.gridy = 2;
		travelGBC.gridwidth = 2;
		travelGBC.fill = GridBagConstraints.HORIZONTAL;
		
		add(travel, travelGBC);
		
		// adds panel that allows buying of supplies
		BuyView buy = new BuyView(this.adventure);
		GridBagConstraints buyGBC = new GridBagConstraints();
		buyGBC.gridx = 0;
		buyGBC.gridy = 3;
		buyGBC.gridwidth = 2;
		buyGBC.fill = GridBagConstraints.HORIZONTAL;
		
		add(buy, buyGBC);
		
		
		
		
		// adds panel that allows for the feeding of edible supplies
		FeedView feed = new FeedView(this.adventure, players);
		this.adventure.addTravelObserver(feed);
		this.adventure.getSquad().getKnapsack().addObserver(feed);
		GridBagConstraints feedGBC = new GridBagConstraints();
		feedGBC.gridx = 0;
		feedGBC.gridy = 4;
		feedGBC.gridwidth = 2;
		feedGBC.fill = GridBagConstraints.HORIZONTAL;
		
		add(feed, feedGBC);
		
		setBackground(new Color(0,0,0,0));
	}
}

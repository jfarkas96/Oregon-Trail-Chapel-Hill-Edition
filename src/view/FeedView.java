package view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import person.Person;
import supplies.Food;
import model.Adventure;
import model.City;
import model.Knapsack;
import model.TravelObserver;

public class FeedView extends JPanel implements Observer, ActionListener, TravelObserver {

	private Adventure adv;
	private Knapsack k;
	private Person[] players;
	private JButton button;
	private JComboBox<Food> supplies;
	private JComboBox<Person> people;
	
	public FeedView(Adventure adv, Person[] players) {
		this.adv = adv;
		this.k = this.adv.getSquad().getKnapsack();
		this.players = players;
		
		setLayout(new FlowLayout());
		button = new JButton("Feed"); 
		supplies = new JComboBox<Food>(k.getEdibleSupplies());
		people = new JComboBox<Person>(this.players);
		add(button);
		add(new JLabel("<html><h3><font color='white'> 1 of </font></h3></html>"));
		add(supplies);
		add(new JLabel("<html><h3><font color='white'> to </font></h3></html>"));
		add(people);
		button.addActionListener(this);
		setOpaque(false);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				int numSupp = supplies.getItemCount();
				int suppidx = supplies.getSelectedIndex();
				int peopidx = people.getSelectedIndex();
				
				supplies.removeAllItems();
				for (Food f : k.getEdibleSupplies()) {
					supplies.addItem(f);
				}
				
				people.removeAllItems();
				for (Person p : players) {
					people.addItem(p);
				}
				
				if (numSupp > 0) {
					supplies.setSelectedIndex(suppidx);
				}
				people.setSelectedIndex(peopidx);
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			adv.getSquad().feed((Food) supplies.getSelectedItem(), (Person) people.getSelectedItem());
		} catch (Exception e) {}
	}

	@Override
	public void travelUpdate(Adventure adventure, int distance_to_destination,
			City destination) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				
				int numSupp = supplies.getItemCount();
				int suppidx = supplies.getSelectedIndex();
				int peopidx = people.getSelectedIndex();
				
				supplies.removeAllItems();
				for (Food f : k.getEdibleSupplies()) {
					supplies.addItem(f);
				}
				
				people.removeAllItems();
				for (Person p : players) {
					people.addItem(p);
				}
				
				if (numSupp > 0) {
					supplies.setSelectedIndex(suppidx);
				}
				people.setSelectedIndex(peopidx);
			}
		});
	}

}

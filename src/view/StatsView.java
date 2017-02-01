package view;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.Adventure;
import model.City;
import model.Squad;
import model.TravelObserver;

public class StatsView extends JPanel implements Observer, TravelObserver {
	
	private Adventure adv;
	private Squad squad;
	private JLabel day;
	private JLabel balance;
	private JLabel currentCity;
	
	public StatsView(Adventure adv) {
		this.adv = adv;
		this.squad = adv.getSquad();
		
		setLayout(new GridLayout(0,1,0,0));
		setBackground(Color.LIGHT_GRAY);
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		
		day = new JLabel(" Day: " + this.adv.getDay());
		balance = new JLabel(" Balance: $" + squad.getBalance() + "  ");
		try {
			currentCity = new JLabel(" Current City: " + this.adv.getCurrentCity() + "  ");
		} catch (Exception e) {
			currentCity = new JLabel(" Current City: Traveling  ");
		}
		
		add(day);
		add(balance);
		add(currentCity);
	}

	@Override
	public void update(Observable o, Object arg) {
		day.setText(" Day: " + adv.getDay() + "  ");
		balance.setText(" Balance: $" + squad.getBalance() + "  ");
		try {
			currentCity.setText(" Current City: " + adv.getCurrentCity() + "  ");
		} catch (Exception e) {
			currentCity.setText(" Current City: Traveling  ");
		}
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
	}

	@Override
	public void travelUpdate(Adventure adventure, int distance_to_destination,
			City destination) {
		day.setText(" Day: " + adv.getDay() + "  ");
		balance.setText(" Balance: $" + squad.getBalance() + "  ");
		try {
			currentCity.setText(" Current City: " + adv.getCurrentCity() + "  ");
		} catch (Exception e) {
			currentCity.setText(" Current City: Traveling  ");
		}
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
	}
}

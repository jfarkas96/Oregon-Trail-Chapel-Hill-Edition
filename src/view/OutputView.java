package view;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Adventure;
import model.City;
import model.TravelObserver;

public class OutputView extends JPanel implements TravelObserver {
	private JLabel label;
	
	public OutputView() {
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		label = new JLabel("<html><h1>&nbsp;<h1><html>", JLabel.CENTER);
		add(label);
		setBackground(Color.LIGHT_GRAY);
	}

	@Override
	public void travelUpdate(Adventure adventure, int distance_to_destination,
			City destination) {
		if (distance_to_destination != 0) {
			label.setText("<html><h1>On day " + adventure.getDay() + " you are " + distance_to_destination + 
					" miles from " + destination.toString() + "</h1></html>");
		} else {
			label.setText("<html><h1>Arrived at " + destination.toString() + "</h1></html>");
		}
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
	}

}

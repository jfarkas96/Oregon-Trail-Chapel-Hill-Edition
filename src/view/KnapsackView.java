package view;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import supplies.Supplies;
import model.Adventure;

public class KnapsackView extends JPanel implements Observer {
	
	private Adventure adv;
	private JLabel label;

	public KnapsackView(Adventure adv) {
		this.adv = adv;
		Supplies[] supplies = this.adv.getSquad().getKnapsack().getSupplies();
		
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		
		String knapItems = "<html>&nbsp;Knapsack:<br>";
		
		for (Supplies sup : supplies) {
			knapItems = knapItems + "&nbsp;" + sup.toString() + "&nbsp;<br>";
		}
		
		knapItems = knapItems + "</html>";
		label = new JLabel(knapItems);
		add(label, BorderLayout.NORTH);
		setBackground(Color.LIGHT_GRAY);
	}
	
	
	@Override
	public void update(Observable o, Object arg) {		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				label.setText("");
				String newKnapItems = "<html>&nbsp;Knapsack:<br>";
				for (Supplies sup : adv.getSquad().getKnapsack().getSupplies()) {
					newKnapItems = newKnapItems + "&nbsp;" + sup.toString() + "&nbsp;<br>";
				}
				
				newKnapItems = newKnapItems + "</html>";
				label.setText(newKnapItems);
				setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
			}
		});
	}
}

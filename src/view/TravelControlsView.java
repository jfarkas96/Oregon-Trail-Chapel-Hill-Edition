package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import model.Adventure;
import model.City;

public class TravelControlsView extends JPanel implements ActionListener {

	private Adventure adv;
	private JComboBox<City> combo;
	
	public TravelControlsView(Adventure adv) {
		this.adv = adv;
		
		setLayout(new FlowLayout());
		add(new JLabel("<html><h3><font color='white'>Travel to: </font></h3></html>"));
		combo = new JComboBox<City>(this.adv.getCities());
		add(combo);
		JButton button = new JButton("Travel");
		add(button);
		button.addActionListener(this);
		setOpaque(false);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		int idx = 0;
		switch (combo.getSelectedIndex()) {
			case 0: idx=0; break;
			case 1: idx=1; break;
			case 2: idx=2; break;
			case 3: idx=3; break;
			case 4: idx=4; break;
			case 5: idx=5; break;
			case 6: idx=6; break;
			case 7: idx=7; break;
			case 8: idx=8; break;
			case 9: idx=9; break;
			case 10: idx=10; break;
		}
		
		if (!adv.isTravelling()) {
			adv.travel(adv.getCities()[idx]);
		} else {}
	}

}

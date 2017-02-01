package view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import model.Adventure;
import model.InTransitException;
import model.InsufficientFundsException;
import model.ItemNotForSaleException;
import model.Store;

public class BuyView extends JPanel implements Observer, ActionListener {

	private Adventure adv;
	private JSpinner spin;
	private JComboBox<String> combo;
	private JButton button;
	private String[] itemNames;
	
	public BuyView(Adventure adv) {
		this.adv = adv;
		Store stor = this.adv.getCities()[0].getStore();
		itemNames = stor.getItemNames();
		String[] storeItems = new String[itemNames.length];
		try {
			for (int i=0; i < itemNames.length; i++) {
				storeItems[i] = itemNames[i] + " ($" + stor.getPrice(itemNames[i]) + ")";
			}
		} catch (ItemNotForSaleException e1) {
		}
		
		setLayout(new FlowLayout());
		add(new JLabel("<html><h3><font color='white'>Buy </font></h3></html>"));
		spin = new JSpinner(new SpinnerNumberModel(0,0,10000,1));
		add(spin);
		add(new JLabel("<html><h3><font color='white'> of </font></h3></html>"));
		combo = new JComboBox<String>(storeItems);
		add(combo);
		button = new JButton("Place Order");
		add(button);
		button.addActionListener(this);
		setOpaque(false);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int count;
		try {
			count = (int) spin.getValue();
		} catch (Exception e2) {
			count = 0;
		}
		try {
			adv.getSquad().purchaseSupply(itemNames[combo.getSelectedIndex()], count, adv.getCurrentCity().getStore());
		} catch (ItemNotForSaleException e1) {
			JOptionPane.showMessageDialog(null,"That's not for sale here!","",JOptionPane.WARNING_MESSAGE);
		} catch (InsufficientFundsException e1) {
			JOptionPane.showMessageDialog(null,"You can't afford that!","",JOptionPane.WARNING_MESSAGE);
		} catch (InTransitException e1) {
			JOptionPane.showMessageDialog(null,"You can't buy anything while traveling!","",JOptionPane.WARNING_MESSAGE);
		}
		
	}

}

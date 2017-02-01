package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import person.*;

public class PersonView extends JPanel implements Observer {
	
	private Person person;
	
	public PersonView(Person p) {
		person = p;
		setLayout(new GridLayout(0,1,0,0));
		setBackground(Color.LIGHT_GRAY);
		add(new JLabel(" " + p.getName() + "  "));
		add(new JLabel(" HP: " + p.getCurrentHP() + "/" + p.getMaxHP() + "  "));
		add(new JLabel(" Java Skill: " + p.getSkill() + "  "));
		add(new JLabel(" Status: " + p.getStatus() + "  "));
		
		String occ;
		if (p instanceof Student) {
			occ = "Student";
		} else if (p instanceof Brogrammer) {
			occ = "Brogrammer";
		} else {
			occ = "Hacker";
		}
		
		add(new JLabel(" Occupation: " + occ + "  "));
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		person.addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				removeAll();
				add(new JLabel(" " + person.getName() + "  "), BorderLayout.NORTH);
				add(new JLabel(" HP: " + person.getCurrentHP() + "/" + person.getMaxHP() + "  "), BorderLayout.AFTER_LAST_LINE);
				add(new JLabel(" Java Skill: " + person.getSkill() + "  "), BorderLayout.AFTER_LAST_LINE);
				add(new JLabel(" Status: " + person.getStatus() + "  "), BorderLayout.AFTER_LAST_LINE);
				
				String occ;
				if (person instanceof Student) {
					occ = "Student";
				} else if (person instanceof Brogrammer) {
					occ = "Brogrammer";
				} else {
					occ = "Hacker";
				}
				
				add(new JLabel(" Occupation: " + occ + "  "));
				setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
			}
		});
	}
}

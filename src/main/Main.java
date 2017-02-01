package main;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import view.AdventureConsole;
import view.AdventureView;
import view.ImagePanel;
import model.Adventure;
import model.Squad;
import model.SquadImpl;


public class Main {

	public static void main(String[] args) throws IOException {
		JFrame main_frame = new JFrame();
		main_frame.setTitle("Adventure");
		main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Squad s = SquadImpl.generateSquad(5);		
		Adventure a = new Adventure(Adventure.Difficulty.HARD, s);

		AdventureConsole c = new AdventureConsole(a);
		c.start();

		BufferedImage img;
		img = ImageIO.read(new File("computer.jpg"));
		ImagePanel p = new ImagePanel(img);
		p.setOpaque(false);
		
		AdventureView v = new AdventureView(a);
		v.setOpaque(false);
		
		JLayeredPane layers = new JLayeredPane();
		layers.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridx = 1;
		gbc.gridy = 1;
		
		layers.add(v, gbc, 1);
		layers.add(p, gbc, 2);
		layers.setOpaque(true);
		main_frame.setContentPane(layers);
		
		
		main_frame.pack();
		main_frame.setVisible(true);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension preferred = new Dimension(800,750);
		main_frame.setSize(preferred);
		main_frame.setLocation(dim.width/2-main_frame.getSize().width/2, (dim.height/2-main_frame.getSize().height/2) - 20);
		main_frame.setResizable(false);
		
		try {
			c.join();
		} catch (InterruptedException e) {
		}
		System.exit(0);
	}
}

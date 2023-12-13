package p1;
import javax.swing.*;
import java.awt.*;

public class Display extends JFrame {


	Display() {
		super("2D Runner Game");
		this.setLayout(new GridLayout(1,1));
		this.setSize(800,600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setResizable(false);
		this.add(new Game());

	}
	
	public static void main(String[] args) {
		new Display();
	}
}

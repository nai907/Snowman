package p2;
import javax.swing.*;
import java.awt.event.*;
import p1.Game;

public class Enemy {
	public int x,y,size;
	private int xstart;
	public static int score = 0;
	public static boolean inprogress = false;
	public static boolean stop = false;
	public static boolean firstgame = false;
	public Timer timer;
	
	public Enemy(int x, int y, int size,JPanel game) {
		firstgame = true;
		this.x = x;
		this.xstart = x;
		this.y = y;
		this.size =size;
		move(game);
	}
	
	public void move(JPanel game) {
		timer = new Timer(1000/60,new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				x -= Game.gamespeed2;
				game.repaint();
				if (x<0) {
					x = xstart;
					score += 1;
				}
				if (Math.abs(Game.enemyline1.x-Game.enemyline2.x)<=100) {
					Game.enemyline1.x = (int) (1000+Math.floor(Math.random()*5000));
				}
				if (Math.abs(Game.enemyline2.x-Game.enemyline3.x)<=100) {
					Game.enemyline2.x = (int) (1000+Math.floor(Math.random()*5000));
			}
			}
		});
		timer.restart();
		
	}
}

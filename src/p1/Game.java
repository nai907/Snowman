package p1;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import p2.Runner;
import p2.Enemy;
import p2.FinalEvent;

public class Game extends JPanel implements KeyListener, ActionListener, MouseListener {
	public static int gamespeed1 = 10;
	public static double gamespeed2 = gamespeed1;
	public int level = 1;
	public static Enemy enemyline1;
	public static Enemy enemyline2;
	public static Enemy enemyline3;
	Runner runner = new Runner(50,250,50);
	Timer speedtimer = new Timer(10000,this);
	JButton btexit = new JButton("Exit");
	JButton btmainmenu = new JButton("Main Menu");
	JPanel btpanel = new JPanel();
	private boolean gamestart = false;
	private boolean gameover = false;
	private boolean cont = false;
	private boolean finalscore = false;
	public static int fscore;
	
	Game() {
		this.setLayout(new BorderLayout());
		btpanel.setLayout(new FlowLayout());
		this.setSize(800,600);
		this.setBackground(Color.black);
		this.setFocusable(true);
		this.requestFocusInWindow();
		this.addKeyListener(this);
		btexit.addActionListener(this);
		btmainmenu.addActionListener(this);
		this.addMouseListener(this);
		
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics g2 = g.create();
		if (gameover && cont == false && finalscore == false) {
			FinalEvent finalscore1 = new FinalEvent(Enemy.score);
			finalscore1.getScore();
			finalscore = true;
		}
		else if (gameover && cont == false && finalscore == true) {
			repaint();
			speedtimer.stop();
			removeAll();
			this.add(btpanel,BorderLayout.SOUTH);
			btpanel.remove(btmainmenu);
			btpanel.setBackground(Color.black);
			btpanel.add(btexit);
			revalidate();
			g2.setColor(Color.WHITE);
			g2.setFont(new Font("Verdena",Font.BOLD,36));
			g2.drawString("Game Over !",290,75);
			g2.drawString("Score: "+fscore,310,175);
			g2.drawRect(230, 250, 325, 100);
			g2.drawString("Main Menu",305,310);
			Enemy.inprogress = false;
		}
		else if(gamestart) {
			repaint();
			removeAll();
			revalidate();
			g2.setColor(Color.WHITE);
			btpanel.remove(btexit);
			btpanel.setBackground(Color.black);
			btpanel.add(btmainmenu);
			this.add(btpanel,BorderLayout.SOUTH);
			super.paintComponent(g);
			speedtimer.start();
			g2.setFont(new Font("Verdena",Font.BOLD,36));
			g2.drawString("Level: "+String.valueOf(level),50,50);
			g2.drawString("Score: "+String.valueOf(Enemy.score),570,50);
			g2.drawLine(0,125,800,125);
			g2.drawLine(0,275,800,275);
			g2.drawLine(0,425,800,425);
			g2.setColor(Color.YELLOW);
			g2.fillRect(runner.x,runner.y,runner.size,runner.size);
			g2.setColor(Color.RED);
			g2.fillOval(enemyline1.x, enemyline1.y-150, enemyline1.size, enemyline1.size);
			if (FinalEvent.calculateDistance(runner.x, runner.y, enemyline1.x, enemyline1.y-150)<=enemyline1.size) {
				gameover = true;
			}
			g2.fillOval(enemyline2.x, enemyline2.y, enemyline2.size, enemyline2.size);
			if (FinalEvent.calculateDistance(runner.x, runner.y, enemyline2.x, enemyline2.y)<=enemyline2.size) {
				gameover = true;
			}
			g2.fillOval(enemyline3.x, enemyline3.y+150, enemyline3.size, enemyline3.size);
			if (FinalEvent.calculateDistance(runner.x, runner.y, enemyline3.x, enemyline3.y+150)<=enemyline3.size) {
				gameover = true;
			}
		}
		else if (gamestart==false) {
			repaint();
			level = 1;
			Enemy.score = 0;
			speedtimer.stop();
			removeAll();
			btpanel.remove(btmainmenu);
			this.add(btpanel,BorderLayout.SOUTH);
			btpanel.setBackground(Color.black);
			btpanel.add(btexit);
			revalidate();
			g2.setColor(Color.WHITE);
			g2.setFont(new Font("Verdena",Font.BOLD,36));
			g2.drawString("2D Endless Runner Game",175,125);
			g2.drawRect(230, 250, 325, 100);
			g2.drawString("Play",355,310);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_SPACE) {
			runner.moveUp();
			this.repaint();
		}
		if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_SHIFT) {
			runner.moveDown();
			this.repaint();
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		level += 1;
		gamespeed2 += 5;
		if (e.getSource() == btexit) {
			System.exit(0);
		}
		if (e.getSource() == btmainmenu) {
			Enemy.inprogress = false;
			gamestart = false;
			Enemy.stop = true;
			repaint();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (gamestart == false && e.getX()>=230 && e.getX() <= 555 && e.getY() >= 250 && e.getY() <= 350) {
			if (Enemy.firstgame == false) {
				enemyline1 = new Enemy((int)(1000+Math.floor(Math.random()*5000)), 250, 50,  this);
				enemyline2 = new Enemy((int)(1000+Math.floor(Math.random()*5000)), 250, 50,  this);
				enemyline3 = new Enemy((int)(1000+Math.floor(Math.random()*5000)), 250, 50,  this);
			}
			else {
				enemyline1.timer.stop();
				enemyline2.timer.stop();
				enemyline3.timer.stop();
				enemyline1 = new Enemy((int)(1000+Math.floor(Math.random()*5000)), 250, 50, this);
				enemyline2 = new Enemy((int)(1000+Math.floor(Math.random()*5000)), 250, 50, this);
				enemyline3 = new Enemy((int)(1000+Math.floor(Math.random()*5000)), 250, 50, this);
			}
			Enemy.inprogress = true;
			gamestart = true;
			gameover = false;
			finalscore = false;
			cont = false;
			gamespeed2 = 10;
			runner.y = 250;
			repaint();

		}
		
		if (gameover == true && e.getX()>=230 && e.getX() <= 555 && e.getY() >= 250 && e.getY() <= 350) {
			cont = true;
			gamestart = false;
			repaint();
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}

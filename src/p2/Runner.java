package p2;

public class Runner {
	public int x,y,size;
	
	public Runner(int x,int y,int size) {
		this.x = x;
		this.y = y;
		this.size = size;
	}
	
	public void moveUp() {
		if (y == 250) {
			y = 100;
		}
		else if (y == 400) {
			y = 250;
		}
	}
	
	public void moveDown() {
		if (y == 250) {
			y = 400;
		}
		else if (y == 100) {
			y = 250;
		}
		
	}

}

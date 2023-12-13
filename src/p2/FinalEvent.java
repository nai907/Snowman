package p2;
import p1.Game;

public class FinalEvent extends Event {
	int finalscore;

    public FinalEvent(int finalscore) {
        this.finalscore = finalscore;
    }
    
    public void getScore() {
    	Game.fscore = finalscore;
    }

}


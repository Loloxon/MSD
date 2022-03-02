import java.util.ArrayList;
import java.util.Random;

public class Point {
	private ArrayList<Point> neighbors;
	private int currentState;
	private int nextState;
	private int numStates = 6;
	
	public Point() {
		currentState = 0;
		nextState = 0;
		neighbors = new ArrayList<Point>();
	}

	public void clicked() {
		currentState=(++currentState)%numStates;	
	}
	
	public int getState() {
		return currentState;
	}

	public void setState(int s) {
		currentState = s;
	}

	public void calculateNewState() {
        if(this.currentState==1)
            if(this.sumAliveNeighbors()>=2 && this.sumAliveNeighbors()<=5)
                this.nextState = 1;
            else
                this.nextState = 0;
        if(this.currentState==0)
            if(this.sumAliveNeighbors()>=4 && this.sumAliveNeighbors()<=8)
                this.nextState = 1;
            else
                this.nextState = 0;
		//TODO: insert logic which updates according to currentState and number of active neighbors
	}

	public void changeState() {
		currentState = nextState;
	}
	
	public void addNeighbor(Point nei) {
		neighbors.add(nei);
	}

    public int sumAliveNeighbors(){
        int tmp=0;
        for (Point neighbor : neighbors)
            if (neighbor.getState() == 1)
                tmp += 1;
        return tmp;
    }
	//TODO: write method counting all active neighbors of THIS point

//    public void drop(){
//        Random random = new Random();
//        if(random.nextInt(100)<5)
//            this.currentState = 6;
//    }
}

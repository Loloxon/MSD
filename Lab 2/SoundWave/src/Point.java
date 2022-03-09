public class Point {

	public Point nNeighbor;
	public Point wNeighbor;
	public Point eNeighbor;
	public Point sNeighbor;
	public float nVel;
	public float eVel;
	public float wVel;
	public float sVel;
	public float pressure;

    public int sinInput;
    
    public static Integer []types ={0,1,2};
    int type;

	public Point() {
		clear();
        type = 0;
	}

	public void clicked() {
		pressure = 1;
	}
	
	public void clear() {
		// TODO: clear velocity and pressure
        nVel = 0;
        eVel = 0;
        wVel = 0;
        sVel = 0;
	    pressure = 0;
    }

	public void updateVelocity() {
		// TODO: velocity update
        if(type==0) {
            nVel = nVel - (nNeighbor.pressure - pressure);
            eVel = eVel - (eNeighbor.pressure - pressure);
            wVel = wVel - (wNeighbor.pressure - pressure);
            sVel = sVel - (sNeighbor.pressure - pressure);
        }
//        float nVeltmp = nVel - (nNeighbor.pressure - pressure);
//        float eVeltmp = eVel - (eNeighbor.pressure - pressure);
//        float wVeltmp = wVel - (wNeighbor.pressure - pressure);
//        float sVeltmp = sVel - (sNeighbor.pressure - pressure);
//        nVel = nVeltmp;
//        eVel = eVeltmp;
//        sVel = sVeltmp;
//        wVel = wVeltmp;
    }

	public void updatePresure() {
		// TODO: pressure update
        if(type==0) {
            float summedVel = 0;
//        summedVel += nVel - (nNeighbor.pressure - pressure);
//        summedVel += eVel - (eNeighbor.pressure - pressure);
//        summedVel += wVel - (wNeighbor.pressure - pressure);
//        summedVel += sVel - (sNeighbor.pressure - pressure);
            summedVel = nVel + eVel + sVel + wVel;
            pressure = pressure - (summedVel) / 2;
        }
        else if(type==2){
            double radians = Math.toRadians(sinInput);
            pressure = (float) (Math.sin(radians));
        }
	}

	public float getPressure() {
		return pressure;
	}
}
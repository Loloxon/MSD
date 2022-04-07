import java.util.ArrayList;

public class Point {

    public ArrayList<Point> neighbors;
    public static Integer []types ={0,1,2,3};
    public int type;
//    public int staticField;
    public float staticField;
    public boolean isPedestrian;

    boolean blocked = false;

    int maxx = 100000;
    
    public Point() {
        type=0;
        staticField = maxx;
        neighbors= new ArrayList<Point>();
    }

    public void clear() {
        staticField = maxx;

    }

    public boolean calcStaticField() {
        if(type==1)
            return false;
//        int min_pot = maxx;
        float min_pot = maxx;
        int idx = -1;
//        boolean diag = true;
        for(int i=0;i<neighbors.size();i++){
            min_pot=Math.min(min_pot, neighbors.get(i).staticField);
            idx = i;
        }
//        int dist = ((idx-4<0)?5:7);
        float dist = ((idx-4<0)?1:(float)Math.sqrt(2));
        if(staticField>min_pot+dist){
            staticField=min_pot+dist;
            return true;
        }
        return false;
    }

    public void move(){
        if(isPedestrian && !blocked){
//            int min_pot = maxx;
            float min_pot = maxx;
            int idx = -1;
            for(int i=0;i< neighbors.size();i++){
                if(neighbors.get(i).staticField<min_pot && !neighbors.get(i).isPedestrian){
                    min_pot=neighbors.get(i).staticField;
                    idx = i;
                }
            }
            if(idx==-1)
                return;
            if(neighbors.get(idx).type!=2) {
                neighbors.get(idx).isPedestrian = true;
                neighbors.get(idx).blocked=true;
            }
            isPedestrian=false;
        }
    }

    public void addNeighbor(Point nei) {
        neighbors.add(nei);
    }
}
import static java.lang.Math.min;

public class Point {

    // TODO
    int type;
    Point next;
    boolean moved;

    int v;
    public void move() {
        // TODO
//        if(type==1 && !moved && next.type==0) {
//            type = 0;
//            next.type = 1;
//            moved = true;
//            next.moved = true;
//        }
        if(type==1 && !moved && next.type==0) {
            type = 0;
            Point nextP = this;
            for (int i = 0; i < v; i++) {
                nextP = nextP.next;
            }
            nextP.type = 1;
            nextP.v = v;
            v = 0;
            moved = true;
            nextP.moved = true;
        }
    }

    public void clicked() {
        // TODO
        type=1;
        v = 1;
    }

    public void clear() {
        // TODO
        type=0;
        v = 0;
    }
}


import static java.lang.Math.min;

public class Point {

    // TODO
    int type;
    Point next, left, right;
    boolean moved;

    public static Integer[] types = {0, 1, 2, 3, 5};

    int v;
    public void move() {
        // TODO
        if(5>type && type >= 1 && !moved && next.type==0) {

            v = min(v + 1, type * 2 + 1);
            Point nextP = next;
            int tmpV = 1;
            while (tmpV < v && nextP.next.type == 0) {
                tmpV += 1;
                nextP = nextP.next;
            }
            nextP.type = type;
            type = 0;
            nextP.v = tmpV;
            v = 0;
            moved = true;
            nextP.moved = true;
        }
    }

    public void change(){
        left.type = type;
        left.v = v+1;
        type = 0;
        v = 0;

        moved = true;
        left.moved = true;
    }
    
    public void back(){
        right.type = type;
        right.v = v;
        type = 0;
        v = 0;

        moved = true;
        right.moved = true;
    }
    
    public void clicked() {
        this.type = 0;
    }

    public void clear() {
        // TODO
        if(type>0 && type<5) {
            type = 0;
            v = 0;
        }
    }
}


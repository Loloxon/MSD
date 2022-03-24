import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;

import static java.lang.Math.min;

public class Board extends JComponent implements MouseInputListener, ComponentListener {
    private static final long serialVersionUID = 1L;
    private Point[][] points;
    private int size = 25;
    public int editType = 0;

    public Board(int length, int height) {
        addMouseListener(this);
        addComponentListener(this);
        addMouseMotionListener(this);
        setBackground(Color.WHITE);
        setOpaque(true);
    }

    private void initialize(int length, int height) {
        points = new Point[length][height];

        for (int x = 0; x < points.length; ++x) {
            for (int y = 0; y < points[x].length; ++y) {
                points[x][y] = new Point();
            }
        }
        // TODO
        for (int x = 0; x < points.length; ++x) {
            for (int y = 0; y < points[x].length; ++y) {
                points[x][y].next = points[(x+1)%points.length][y];
                if(y==3)
                    points[x][y].left = points[x][(y-1)%points[x].length];
                if(y==2)
                    points[x][y].right = points[x][(y+1)%points[x].length];
            }
        }

        for (int x = 0; x < points.length; ++x) {
            for (int y = 0; y < points[x].length; ++y) {
                if(y<2 || y>3)
                    points[x][y].type=5;
//                points[x][y].next = points[(x+1)%points.length][y];
            }
        }
    }
    int D_M = 7;
    public void iteration() {
        // TODO
        for (int x = 0; x < points.length; ++x) {
            for (int y = 0; y < points[x].length; ++y) {
                points[x][y].moved=false;
            }
        }
        for (int x = 0; x < points.length; ++x) {
            for (int y = 0; y < points[x].length; ++y) {
                if(5>points[x][y].type && points[x][y].type > 0){
                    int D_br=1;
                    while(points[(points.length+x-D_br)%points.length][3].type==0 && D_br<=D_M+1){
                        D_br++;
                    }
                    D_br--;
                    int D_bl=1;
                    while(points[(points.length+x-D_bl)%points.length][2].type==0 && D_bl<=D_M+1){
                        D_bl++;
                    }
                    D_bl--;
                    int D_fr=1;
                    while(points[(points.length+x+D_fr)%points.length][3].type==0 && D_fr<=D_M+1){
                        D_fr++;
                    }
                    D_fr--;
                    int D_fl=1;
                    while(points[(points.length+x+D_fr)%points.length][2].type==0 && D_fl<=D_M+1){
                        D_fl++;
                    }
                    D_fl--;
                    //back
                    if(y == 2 && D_br>=D_M && D_bl<=D_M && D_fr>=points[x][y].v && points[x][3].type==0 && !points[x][y].moved){
                        points[x][y].back();
                    }
                    //change
                    else if(y == 3 && points[x][y].v<points[x][y].type*2+1 &&
                            D_br>=D_M && D_bl>=D_M && D_fl>=points[x][y].v && points[x][2].type == 0 && !points[x][y].moved){
                        points[x][y].change();
                    }
                    //movement
                    else{
                        points[x][y].move();
                    }
                }
            }
        }

//        int chance = 5;
//        for (int x = 0; x < points.length; ++x) {
//            for (int y = 0; y < points[x].length; ++y) {
//                if(points[x][y].v>0){
//                    int r = (int) ((Math.random() * (100 - 1)) + 1);;
//                    if(r<chance){
//                        points[x][y].v-=1;
//                    }
//                }
//            }
//        }

//        for (int x = 0; x < points.length; ++x) {
//            for (int y = 0; y < points[x].length; ++y) {
//                // TODO
//                points[x][y].move();
//            }
//        }
        this.repaint();
    }

    public void clear() {
        for (int x = 0; x < points.length; ++x)
            for (int y = 0; y < points[x].length; ++y) {
                points[x][y].clear();
            }
        this.repaint();
    }


    protected void paintComponent(Graphics g) {
        if (isOpaque()) {
            g.setColor(getBackground());
            g.fillRect(0, 0, this.getWidth(), this.getHeight());
        }
        g.setColor(Color.GRAY);
        drawNetting(g, size);
    }

    private void drawNetting(Graphics g, int gridSpace) {
        Insets insets = getInsets();
        int firstX = insets.left;
        int firstY = insets.top;
        int lastX = this.getWidth() - insets.right;
        int lastY = this.getHeight() - insets.bottom;

        int x = firstX;
        while (x < lastX) {
            g.drawLine(x, firstY, x, lastY);
            x += gridSpace;
        }

        int y = firstY;
        while (y < lastY) {
            g.drawLine(firstX, y, lastX, y);
            y += gridSpace;
        }

        for (x = 0; x < points.length; ++x) {
            for (y = 0; y < points[x].length; ++y) {
                float a = 1.0F;

                // TODO
                //g.setColor(new Color(R, G, B, 0.7f));
                if (points[x][y].type==0)
                    g.setColor(new Color(1f,1f,1f,1f));
                else if(points[x][y].type==1)
                    g.setColor(new Color(1f,0.8f,0,1f));
                else if(points[x][y].type==2)
                    g.setColor(new Color(0,0,0.5f,1f));
                else if(points[x][y].type==3)
                    g.setColor(new Color(0.5f,0,0,1f));
                else if(points[x][y].type==5)
                    g.setColor(new Color(0,1f,0,1f));
                else
                    g.setColor(new Color(0,0,0,1f));
                g.fillRect((x * size) + 1, (y * size) + 1, (size - 1), (size - 1));
            }
        }

    }

    public void mouseClicked(MouseEvent e) {
        int x = e.getX() / size;
        int y = e.getY() / size;
        if ((x < points.length) && (x > 0) && (y < points[x].length) && (y > 0)) {
            if(editType==0){
                points[x][y].clicked();
            }
            else {
                points[x][y].type= editType;
            }
            this.repaint();
        }
    }

    public void componentResized(ComponentEvent e) {
        int dlugosc = (this.getWidth() / size) + 1;
        int wysokosc = (this.getHeight() / size) + 1;
        initialize(dlugosc, wysokosc);
    }

    public void mouseDragged(MouseEvent e) {
        int x = e.getX() / size;
        int y = e.getY() / size;
        if ((x < points.length) && (x > 0) && (y < points[x].length) && (y > 0)) {
            if(editType==0){
                points[x][y].clicked();
            }
            else {
                points[x][y].type= editType;
            }
            this.repaint();
        }
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void componentShown(ComponentEvent e) {
    }

    public void componentMoved(ComponentEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseMoved(MouseEvent e) {
    }

    public void componentHidden(ComponentEvent e) {
    }

    public void mousePressed(MouseEvent e) {
    }

}

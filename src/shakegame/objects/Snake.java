package shakegame.objects;

import shakegame.SnakeGameMain;

public class Snake {

    public int length = 2;
    public int direction = 2;

    public int sX[] = new int[300];
    public int sY[] = new int[300];

    public Snake(int x1, int y1,int x2, int y2){
        sX[0] = x1;
        sX[1] = x2;
        sY[0] = y1;
        sY[1] = y2;
    }

    public void move(){
        for (int x = length; x > 0; x--){
            sX[x] = sX[x-1];
            sY[x] = sY[x-1];
        }
        //up
        if (direction == 0) sY[0]--;
        //down
        if (direction == 2) sY[0]++;
        //right
        if (direction == 1) sX[0]++;
        //left
        if (direction == 3) sX[0]--;

        if (sX[0] > SnakeGameMain.WIDTH -1) sX[0] =0;
        if (sX[0] < 0) sX[0] = SnakeGameMain.WIDTH -1;

        if (sY[0] > SnakeGameMain.HEIGHT -1) sY[0] =0;
        if (sY[0] < 0) sY[0] = SnakeGameMain.HEIGHT -1;


    }
}

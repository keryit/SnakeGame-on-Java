package shakegame.objects;

import shakegame.SnakeGameMain;

public class Apple {

    public int positionY;
    public int positionX;

    public Apple(int positionX, int positionY){
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public void  setRandomPosition(){
        positionX = Math.abs((int)(Math.random()* SnakeGameMain.WIDTH -1));
        positionY = Math.abs((int)(Math.random()* SnakeGameMain.HEIGHT -1));
    }
}

package shakegame;

import shakegame.objects.Apple;
import shakegame.objects.Snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SnakeGameMain extends JPanel implements ActionListener {

    public static JFrame jFrame;
    public static final int SCALE = 32;
    public static final int WIDTH = 20;
    public static final int HEIGHT = 20;
    public int speed = 5;

    Snake snake = new Snake(5,6,5,5);
    Apple apple = new Apple(Math.abs((int)(Math.random()* SnakeGameMain.WIDTH -1)),
            Math.abs((int)(Math.random()* SnakeGameMain.HEIGHT -1)));
    Timer timer = new Timer(1000/speed, this);

    public SnakeGameMain(){
        timer.start();
        addKeyListener(new KeyBoard());
        setFocusable(true);
    }

    public void paint(Graphics graphics){
        graphics.setColor(Color.LIGHT_GRAY);
        graphics.fillRect(0, 0, WIDTH * SCALE, HEIGHT * SCALE);

        for (int x = 0; x < WIDTH * SCALE; x += SCALE){
            graphics.setColor(Color.WHITE);
            graphics.drawLine(x, 0, x, HEIGHT * SCALE);
        }

        for (int y = 0; y < HEIGHT * SCALE; y += SCALE){
            graphics.setColor(Color.WHITE);
            graphics.drawLine(0, y, WIDTH * SCALE, y);
        }

        for (int z = 1; z < snake.length; z++){
            graphics.setColor(Color.DARK_GRAY);
            graphics.fillRect(snake.sX[z]*SCALE+3, snake.sY[z]*SCALE+3,
                    SCALE-6, SCALE-6);
            graphics.setColor(Color.WHITE);
            graphics.fillRect(snake.sX[0]*SCALE+3, snake.sY[0]*SCALE+3,
                    SCALE-6, SCALE-6);
        }
        //apple
        graphics.setColor(Color.RED);
        graphics.fillOval(apple.positionX*SCALE+4, apple.positionY*SCALE+4, SCALE-8, SCALE-8);
    }

    public static void main(String[] args) {

        jFrame = new JFrame("Title");
        jFrame.setSize(WIDTH * SCALE + 6, HEIGHT * SCALE + 10);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setResizable(false);
        jFrame.setLocationRelativeTo(null);
        jFrame.add(new SnakeGameMain());
        jFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        snake.move();
        if ((snake.sX[0] == apple.positionX) && (snake.sY[0]==apple.positionY)){
            apple.setRandomPosition();
            snake.length++;
        }
        for (int x =1; x < snake.length; x++) {
            if ((snake.sX[x] == apple.positionX) && (snake.sY[x] == apple.positionY)) {
                apple.setRandomPosition();
            }
            if ((snake.sX[0] == snake.sX[x]) && (snake.sY[0] == snake.sY[x])){
                timer.stop();
                JOptionPane.showMessageDialog(null,"Game over my dear!");
                jFrame.setVisible(false);
                snake.length =2;
                snake.direction =0;
                apple.setRandomPosition();
                jFrame.setVisible(true);
                timer.start();
            }
        }


        repaint();
    }

    public class KeyBoard extends KeyAdapter{
        public void keyPressed(KeyEvent event){
            int key = event.getKeyCode();

            if ((key == KeyEvent.VK_UP) && (snake.direction !=2)) snake.direction =0;
            if ((key == KeyEvent.VK_DOWN) &&(snake.direction != 0)) snake.direction =2;
            if ((key == KeyEvent.VK_LEFT)&&(snake.direction != 1)) snake.direction =3;
            if ((key == KeyEvent.VK_RIGHT)&&(snake.direction != 3)) snake.direction =1;
        }
    }
}

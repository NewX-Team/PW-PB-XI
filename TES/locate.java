import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class AvoidTheEnemies extends JFrame implements ActionListener, KeyListener {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int PLAYER_SIZE = 50;
    private static final int ENEMY_SIZE = 50;
    private static final int PLAYER_SPEED = 5;
    private static final int ENEMY_SPEED = 7;

    private Timer timer;
    private ArrayList<Rectangle> enemies;
    private Rectangle player;

    public AvoidTheEnemies() {
        setTitle("Avoid the Enemies");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        player = new Rectangle(WIDTH / 2 - PLAYER_SIZE / 2, HEIGHT - 2 * PLAYER_SIZE, PLAYER_SIZE, PLAYER_SIZE);
        enemies = new ArrayList<>();

        timer = new Timer(30, this);
        timer.start();

        addKeyListener(this);
        setFocusable(true);
    }

    public void createEnemy() {
        int enemySize = new Random().nextInt(ENEMY_SIZE) + ENEMY_SIZE / 2;
        int enemyX = new Random().nextInt(WIDTH - enemySize);
        enemies.add(new Rectangle(enemyX, 0, enemySize, enemySize));
    }

    public void moveEnemies() {
        for (Rectangle enemy : new ArrayList<>(enemies)) {
            enemy.y += ENEMY_SPEED;
            if (enemy.y > HEIGHT) {
                enemies.remove(enemy);
            }
        }
    }

    public void checkCollisions() {
        for (Rectangle enemy : new ArrayList<>(enemies)) {
            if (player.intersects(enemy)) {
                System.out.println("Game Over!");
                System.exit(0);
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        moveEnemies();
        createEnemy();
        checkCollisions();
        repaint();
    }

    public void keyTyped(KeyEvent e) {}

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT && player.x > 0) {
            player.x -= PLAYER_SPEED;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT && player.x < WIDTH - PLAYER_SIZE) {
            player.x += PLAYER_SPEED;
        }
    }

    public void keyReleased(KeyEvent e) {}

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        g.setColor(Color.RED);
        g.fillRect(player.x, player.y, player.width, player.height);

        g.setColor(Color.RED);
        for (Rectangle enemy : enemies) {
            g.fillRect(enemy.x, enemy.y, enemy.width, enemy.height);
        }

        Toolkit.getDefaultToolkit().sync();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AvoidTheEnemies game = new AvoidTheEnemies();
            game.setVisible(true);
        });
    }
}

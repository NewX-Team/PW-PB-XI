import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class locate extends JFrame {
    private int stickmanX = 50;
    private int stickmanY = 200;

    public locate() {
        setTitle("Bird Fly Angry");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        Timer timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                update();
                repaint();
            }
        });
        timer.start();
    }

    private void update() {
        stickmanX += 1; // Perbarui posisi stickman (contoh pergerakan ke kanan)
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        drawStickman(g);
    }

    private void drawStickman(Graphics g) {
        // Gambar kepala
        g.drawOval(stickmanX, stickmanY, 30, 30);

        // Gambar tubuh
        g.drawLine(stickmanX + 15, stickmanY + 30, stickmanX + 15, stickmanY + 70);

        // Gambar tangan kiri
        g.drawLine(stickmanX + 15, stickmanY + 30, stickmanX, stickmanY + 50);

        // Gambar tangan kanan
        g.drawLine(stickmanX + 15, stickmanY + 30, stickmanX + 30, stickmanY + 50);

        // Gambar kaki kiri
        g.drawLine(stickmanX + 15, stickmanY + 70, stickmanX, stickmanY + 100);

        // Gambar kaki kanan
        g.drawLine(stickmanX + 15, stickmanY + 70, stickmanX + 30, stickmanY + 100);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new locate().setVisible(true);
            }
        });
    }
}

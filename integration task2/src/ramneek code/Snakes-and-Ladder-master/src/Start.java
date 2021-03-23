import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Start {

	public static void main(String[] args) {

		new Ladders();
	}

}

class Ladders extends JFrame {

	{
		setSize(300, 200);
		setLocation(500, 100);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel l1 = new JLabel("welcome to snakes and ladders");
		l1.setBounds(30, 5, 400, 30);
		JPanel j1 = new JPanel();
		j1.setBounds(0, 0, 500, 500);
		j1.add(l1);
		j1.setLayout(null);
		add(j1);

		/// adding buttons
		JButton b1 = new JButton("Start");
		JButton b2 = new JButton("end");
		b1.setBounds(30, 50, 100, 25);
		b2.setBounds(140, 50, 100, 25);
		j1.add(b1);
		j1.add(b2);

		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ladder l = new Ladder();
			}
		});

		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				System.exit(0);
			}
		});

	}
}
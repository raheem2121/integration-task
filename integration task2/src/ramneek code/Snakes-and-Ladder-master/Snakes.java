/*
 * 1. import java.awt.* :- to create an applet and the classes an applet uses to communicate with its applet context.
 * applet are basically small java application or you can say a GUI with which you can interact with
 * 
 * 2. import sun.audio.* :- to use the imported audio to play in the background for our ludo game
 * 
 * 3. import java.awt.event.* :- It will help to identify an event which indicates that a mouse 
 * action occurred in a component or a keystroke happened
 * 
 * 4. import java.io.* :- Provides functions that can be used to handle input and output operations.
 * 
 * 5. import javax.swing.* :- used to create window-based applications. It is built on the top of 
 * AWT (Abstract Windowing Toolkit) API and entirely written in java.
 * 
 * 
 */
import java.awt.*;
import sun.audio.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;



/*
 * We are extending JFrame to inherit some functions of and also JFrame works like the 
 * main window where components like labels, buttons,text fields are added to create a GUI.
 */
class Ladder extends JFrame {

	//JTextField allows the editing of a single line of text on the screen
	JTextField t3,t4;
	
	//ImageIcon is an implementation of the Icon interface that paints Icons from Images.
	ImageIcon i3,i4,i5,i7,i8,i9,i10,i11,i12,h1,h2,h3,h4,h5,h6,i13,i14,i15,i16;
	
	//JPanel provides better organisation of components,
	JPanel panel, panel2;
	
	//JLable helps to create a short area for having or storing a short image or text string
	JLabel l7,l6,l5,l4,l3,l2,l1,d1,d2,d3,d4,d5,d6;
	
	//JButton class is used here to create a labelled button and will result in some 
	//action when the button is pushed.
	JButton b1,b2,b3,b4,b5,b6,b7;
	
	// JTextArea represents a multi-line area that displays text
	JTextArea a1;
	
	// A class provided by sun.audio to fetch the audio
	AudioStream as,winas,clickas;	
	
	// InputStream used to read data from a source where these variables
	// reads sound in .wav format
	InputStream in,winin,clickin;
	
	// class that provides primary functionalities for playing back an audio file 
	//like play, stop, pause, and resume. It is based on the Java Sound API.
	AudioPlayer play = AudioPlayer.player;
	
	
	public Ladder()
	{   
		//Setting up the window size
		setSize(800,700);
		
		setLocation(250,5);
		setVisible(true);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		///adding background music
		try {
			in = new FileInputStream("bg.wav");
			as = new AudioStream(in);
			play.start(as);
			winin=new FileInputStream("win1.wav");
			winas=new AudioStream(winin);
			clickin=new FileInputStream("crick.wav");
			clickas=new AudioStream(clickin);
		} 
		catch (Exception e1) 
		{
			e1.printStackTrace();
		}
		
		//creating title
		i5=new ImageIcon(getClass().getResource("title.png"));
		JLabel l1=new JLabel("");
		l1.setSize(600, 95);
		l1.setLocation(100, 5);
		l1.setIcon(i5);
		repaint();

		//adding title
		panel=new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 800,100 );
		panel.setBackground(Color.yellow);
		panel.add(l1);
		add(panel);

		// adding board and creating  panel2
		panel2=new JPanel();
		panel2.setLayout(null);
		panel2.setBounds(0,100, 800, 570);
		ImageIcon i=new ImageIcon(getClass().getResource("map.gif"));
		JLabel l2=new JLabel(i);
		l2.setBounds(0, 0, 500, 500);
		panel2.add(l2);

		////turn pointer
		l5=new JLabel();
	    i11=new ImageIcon(getClass().getResource("up.gif"));
		l5.setSize(24,24);
		l5.setLocation(550,136);
		l5.setIcon(i11);
		panel2.add(l5);
		///snake image bottom right

		i13=new ImageIcon(getClass().getResource("snake2.png"));
		l6=new JLabel();
		l6.setBounds(510,300,270,270);
		l6.setIcon(i13);
		i14=new ImageIcon(getClass().getResource("snake3.png"));
		l7=new JLabel();
		l7.setBounds(100,500,400,50);
		l7.setIcon(i14);
		panel2.add(l6);
		panel2.add(l7);
		repaint();

		////*************adding sound play mute buttons b6 b7
		b6=new JButton();
		i15=new ImageIcon(getClass().getResource("stop.png"));
		b6.setBounds(510,0,70,70);
		b6.setIcon(i15);
		b6.setOpaque(false);
		b6.setContentAreaFilled(false);
		b6.setBorderPainted(false);
		b7=new JButton();
		i16=new ImageIcon(getClass().getResource("play.png"));
		b7.setBounds(580,0,70,70);
		b7.setIcon(i16);
		b7.setOpaque(false);
		b7.setContentAreaFilled(false);
		b7.setBorderPainted(false);
		panel2.add(b6);
		panel2.add(b7);

		//labels for position and dice roll
		i9=new ImageIcon(getClass().getResource("dice.png"));
		i10=new ImageIcon(getClass().getResource("position.png"));
		l3=new JLabel("");
		l4=new JLabel("");
		l3.setBounds(620, 70, 50, 25);
		l4.setBounds(680, 70, 100, 25);
		l3.setIcon(i9);
		l4.setIcon(i10);
		panel2.add(l3);
		panel2.add(l4);
		
		//adding panel2
		add(panel2);
		panel2.setBackground(Color.green);
		repaint();

		//adding buttons
		i7=new ImageIcon(getClass().getResource("player1.png"));
		i8=new ImageIcon(getClass().getResource("player2.png"));
		i12=new ImageIcon(getClass().getResource("newgame.png"));
		b1=new JButton("");
		b2=new JButton("");
		b5=new JButton("");
		b1.setBounds(510, 110, 100, 25);
		b2.setBounds(510, 170, 100, 25);
		b5.setBounds(510, 230, 100, 25);
		b1.setBackground(Color.RED);
		b2.setBackground(Color.BLUE);
		b1.setIcon(i7);
		b2.setIcon(i8);
		b5.setIcon(i12);
		b5.setOpaque(false);
		b5.setContentAreaFilled(false);
		b5.setBorderPainted(false);
		panel2.add(b1);
		panel2.add(b2);
		panel2.add(b5);
		repaint();
		
		//showing dice
		h1=new ImageIcon(getClass().getResource("1.gif"));
		h2=new ImageIcon(getClass().getResource("2.gif"));
		h3=new ImageIcon(getClass().getResource("3.gif"));
		h4=new ImageIcon(getClass().getResource("4.gif"));
		h5=new ImageIcon(getClass().getResource("5.gif"));
		h6=new ImageIcon(getClass().getResource("6.gif"));
		d1=new JLabel();
		d2=new JLabel();
		d3=new JLabel();
		d4=new JLabel();
		d5=new JLabel();
		d6=new JLabel();
		d1.setIcon(h1);
		d2.setIcon(h2);
		d3.setIcon(h3);
		d4.setIcon(h4);
		d5.setIcon(h5);
		d6.setIcon(h6);
		panel2.add(d1);
		panel2.add(d2);
		panel2.add(d3);
		panel2.add(d4);
		panel2.add(d5);
		panel2.add(d6);

		///displaying random numbers in text fields
		
		///displaying position
		t3=new JTextField("0");
		t3.setBounds(680,110,100,25);
		t4=new JTextField("0");
		t4.setBounds(680,170,100,25);
		panel2.add(t3);
		panel2.add(t4);
		repaint();
		
		///adding text area for updates
		a1=new JTextArea("Player 1 takes Red and Player 2 takes Blue\nBegin the game by clicking on player 1");
		a1.setBackground(Color.pink);
		a1.setBounds(510,260,260,40);
		panel2.add(a1);
		repaint();

		//creating  buttons for moving pieces
		b3=new JButton("");
		b4=new JButton("");
		b3.setSize(50,50);
		b4.setSize(50,50);
		i3=new ImageIcon(getClass().getResource("b.png"));
		i4=new ImageIcon(getClass().getResource("a.png"));																																								
		b3.setIcon(i3);
		b4.setIcon(i4);
		b3.setOpaque(false);
		b3.setContentAreaFilled(false);
		b3.setBorderPainted(false);
		b4.setOpaque(false);
		b4.setContentAreaFilled(false);
		b4.setBorderPainted(false);
		panel2.add(b3);
		panel2.add(b4);
		b3.setLocation(0, 500);
		b4.setLocation(50,500);
		repaint();
		
		//adding action for player 1 and player2 
		b1.addActionListener(new ActionListener() 
		{public int x1=0,count1=0;
		public void actionPerformed(ActionEvent e) 
		{
			try{
				clickin=new FileInputStream("crick.wav");
				clickas=new AudioStream(clickin);
			}
			catch(Exception e4)
			{
				e4.printStackTrace();
			}
			AudioPlayer.player.start(clickas);
			a1.setText("");
			x1=Integer.parseInt(t3.getText());
			if(l5.getY()==196)
			{

				JOptionPane.showMessageDialog(null,"Player 2's turn");
			}

			else
			{
				int c1=(int)( Math.random()*6);
				c1+=1;
				switch(c1)
				{
				case 1:
					d1.setBounds(620,110,50,50);
					panel2.add(d1);
					panel2.remove(d2);
					panel2.remove(d3);
					panel2.remove(d4);
					panel2.remove(d5);
					panel2.remove(d6);
					break;
				case 2:
					d2.setBounds(620,110,50,50);
					panel2.add(d2);
					panel2.remove(d1);
					panel2.remove(d3);
					panel2.remove(d4);
					panel2.remove(d5);
					panel2.remove(d6);break;
				case 3:
					d3.setBounds(620,110,50,50);
					panel2.add(d3);
					panel2.remove(d2);
					panel2.remove(d1);
					panel2.remove(d4);
					panel2.remove(d5);
					panel2.remove(d6);break;
				case 4:
					d4.setBounds(620,110,50,50);
					panel2.add(d4);
					panel2.remove(d2);
					panel2.remove(d3);
					panel2.remove(d1);
					panel2.remove(d5);
					panel2.remove(d6);break;
				case 5:
					d5.setBounds(620,110,50,50);
					panel2.add(d5);
					panel2.remove(d2);
					panel2.remove(d3);
					panel2.remove(d4);
					panel2.remove(d1);
					panel2.remove(d6);break;
				case 6:
					d6.setBounds(620,110,50,50);
					panel2.add(d6);
					panel2.remove(d2);
					panel2.remove(d3);
					panel2.remove(d4);
					panel2.remove(d5);
					panel2.remove(d1);

					break;

				}
				
				//position assigning
				x1=x1+c1;
				System.out.println("x1...."+x1);

				if(x1>100)
				{
					l5.setLocation(550,196);
					int k1=6*count1;
					System.out.println("k1...."+k1);
					System.out.println("count1...."+count1);
					x1=x1-k1-c1;
					count1=0;
					t3.setText(Integer.toString(x1));
					a1.setText("You are going beyond 100!!Just wait for the next turn");

				}
				else
				{
					if(c1==6)
					{
						count1+=1;
						l5.setLocation(550,136);
						t3.setText(Integer.toString(x1));
						a1.setText("it's a 6!!You get another chance");
					}
					else
					{
						count1=0;
						panel2.remove(b3);
						l5.setLocation(550, 196);
						panel2.remove(b3);
						t3.setText(Integer.toString(x1));
						repaint();

						switch(x1)
						{
						case 1:
							x1=38;
							a1.setText("Ladder Climbed");break;
						case 4:
							x1=14;
							a1.setText("Ladder Climbed");break;
						case 9:
							x1=31;
							a1.setText("Ladder Climbed");break;
						case 21:
							x1=42;
							a1.setText("Ladder Climbed");break;
						case 28:
							x1=84;
							a1.setText("Ladder Climbed");break;
						case 51:
							x1=67;
							a1.setText("Ladder Climbed");break;
						case 71:
							x1=91;
							a1.setText("Ladder Climbed");break;
						case 80:
							x1=100;
							a1.setText("Ladder Climbed");break;
						case 17:
							x1=7;
							a1.setText("Snake bite!!");break;
						case 62:
							x1=19;
							a1.setText("Snake bite!!");break;
						case 64:
							x1=60;
							a1.setText("Snake bite!!");break;
						case 98:
							x1=79;
							a1.setText("Snake bite!!");break;
						case 95:
							x1=75;
							a1.setText("Snake bite!!");break;
						case 87:
							x1=24;
							a1.setText("Snake bite!!");break;
						case 54:
							x1=34;
							a1.setText("Snake bite!!");break;
						case 93:
							x1=73;
							a1.setText("Snake bite!!");break;
						}

						if(x1<100)
						{
							t3.setText(Integer.toString(x1));
							if(t3.getText().equals(t4.getText()))
							{
								t4.setText("0");
								b4.setLocation(50,500);
								panel2.add(b4);
								a1.setText("Red takes Blue!!Blue starts again");
								repaint();
							}
							int a1,b1,d1;
							d1=x1/10;

							if(x1%10==0)
							{	
								int f1=d1-1;
								b1=(9-f1)*50;
							}
							else
							{
								b1=(9-d1)*50;
							}
							int g1;
							if(x1%10==0)
							{	
								g1=x1%10+1;}
							else
							{
								g1=x1%10;}
							if(d1%2==0)
							{
								a1=(g1-1)*50;
								l2.add(b3);
								b3.setLocation(a1,b1);
							}

							else
							{
								a1=(10-g1)*50;
								l2.add(b3);
								b3.setLocation(a1,b1);
							}
							//setting x coordinate
						}
						else
						{
							t3.setText(Integer.toString(x1));
							AudioPlayer.player.stop(as);
							AudioPlayer.player.start(winas);
							a1.setText("Player 1 wins");
							int c=JOptionPane.showConfirmDialog(null,"Player 1 wins\n\nPlay Again?");
							if(c==0)
							{
								AudioPlayer.player.stop(winas);
								AudioPlayer.player.start(as);
								playagain();
							}
							else
							{
								if(c==1)
									System.exit(0);
								else
								{};
							}
							repaint();
						}
					}
				}
			}
			repaint();
		}
		});

		b2.addActionListener(new ActionListener() 
		{  int x2=0,count2=0;

		public void actionPerformed(ActionEvent e) 


		{
			try{
				clickin=new FileInputStream("crick.wav");
				clickas=new AudioStream(clickin);
			}
			catch(Exception e4)
			{
				e4.printStackTrace();
			}
			AudioPlayer.player.start(clickas);
			a1.setText("");
			x2=Integer.parseInt(t4.getText());
			if(l5.getY()==136)
			{
				JOptionPane.showMessageDialog(null,"player 1's turn");
			}
			else
			{
				int c2=(int)( Math.random()*6);
				c2+=1;
				switch(c2)
				{
				case 1:
					d1.setBounds(620,170,50,50);
					panel2.add(d1);
					panel2.remove(d2);
					panel2.remove(d3);
					panel2.remove(d4);
					panel2.remove(d5);
					panel2.remove(d6);
					repaint();break;
				case 2:
					d2.setBounds(620,170,50,50);
					panel2.add(d2);
					panel2.remove(d1);
					panel2.remove(d3);
					panel2.remove(d4);
					panel2.remove(d5);
					panel2.remove(d6);
					repaint();break;
				case 3:
					d3.setBounds(620,170,50,50);
					panel2.add(d3);
					panel2.remove(d1);
					panel2.remove(d2);
					panel2.remove(d4);
					panel2.remove(d5);
					panel2.remove(d6);
					repaint();break;
				case 4:
					d4.setBounds(620,170,50,50);
					panel2.add(d4);
					panel2.remove(d1);
					panel2.remove(d2);
					panel2.remove(d3);
					panel2.remove(d5);
					panel2.remove(d6);
					repaint();break;
				case 5:
					d5.setBounds(620,170,50,50);
					panel2.add(d5);
					panel2.remove(d1);
					panel2.remove(d2);
					panel2.remove(d3);
					panel2.remove(d4);
					panel2.remove(d6);
					repaint();break;
				case 6:
					d6.setBounds(620,170,50,50);
					panel2.add(d6);
					panel2.remove(d1);
					panel2.remove(d2);
					panel2.remove(d3);
					panel2.remove(d4);
					panel2.remove(d5);

					repaint();
					break;

				}
				//position assigning
				x2=x2+c2;
				System.out.println("x2...."+x2);

				if(x2>100)
				{
					l5.setLocation(550,136);
					int k2=6*count2;
					System.out.println("k2...."+k2);
					System.out.println("count2...."+count2);
					x2=x2-k2-c2;
					count2=0;
					System.out.println(x2);
					t4.setText(Integer.toString(x2));
					a1.setText("You are going beyond 100!!Just wait for the next turn");
				}
				else
				{
					if(c2==6)
					{
						count2+=1;
						l5.setLocation(550,196);
						t4.setText(Integer.toString(x2));
						a1.setText("It's a 6!!You get another chance");

					}
					else
					{
						count2=0;
						panel2.remove(b4);
						l5.setLocation(550, 136);
						panel2.remove(b4);
						t4.setText(Integer.toString(x2));
						repaint();

						/*
						 * This switch is working to climb to that number up to which the ladder is reaching and if the snake bits then 
						 * take the user back to the snake's tail
						 */
						switch(x2)
						{
						case 1:
							x2=38;
							a1.setText("Ladder Climbed");break;
						case 4:
							x2=14;
							a1.setText("Ladder Climbed");break;
						case 9:
							x2=31;
							a1.setText("Ladder Climbed");break;
						case 21:
							x2=42;
							a1.setText("Ladder Climbed");break;
						case 28:
							x2=84;
							a1.setText("Ladder Climbed");break;
						case 51:
							x2=67;
							a1.setText("Ladder Climbed");break;
						case 71:
							x2=91;
							a1.setText("Ladder Climbed");break;
						case 80:
							x2=100;
							a1.setText("Ladder Climbed");break;
						case 17:
							x2=7;
							a1.setText("Snake bite!!");break;
						case 62:
							x2=19;
							a1.setText("Snake bite!!");break;
						case 64:
							x2=60;
							a1.setText("Snake bite!!");break;
						case 98:
							x2=79;
							a1.setText("Snake bite!!");break;
						case 95:
							x2=75;
							a1.setText("Snake bite!!");break;
						case 87:
							x2=24;
							a1.setText("Snake bite!!");break;
						case 54:
							x2=34;
							a1.setText("Snake bite!!");break;
						case 93:
							x2=73;
							a1.setText("Snake bite!!");break;

						}
						if(x2<100)
						{
							t4.setText(Integer.toString(x2));
							if(t4.getText().equals(t3.getText()))
							{
								t3.setText("0");
								b3.setLocation(0,500);
								panel2.add(b3);
								a1.setText("Blue takes Red!!Red starts again");
								repaint();
							}
							int a2,b2,d2;
							d2=x2/10;
							if(x2%10==0)
							{	
								int f2=d2-1;
								b2=(9-f2)*50;
							}
							else
							{
								b2=(9-d2)*50;}
							int g2;
							if(x2%10==0)
							{	g2=x2%10+1;
							}
							else
							{
								g2=x2%10;
							}
							if(d2%2==0)
							{
								a2=(g2-1)*50;
								l2.add(b4);
								b4.setLocation(a2,b2);
							}
							else
							{
								a2=(10-g2)*50;
								l2.add(b4);
								b4.setLocation(a2,b2);
							}
						}
						else
						{
							t4.setText(Integer.toString(x2));
							AudioPlayer.player.stop(as);
							AudioPlayer.player.start(winas);
							a1.setText("Player 2 wins");
							int c=JOptionPane.showConfirmDialog(null,"Player 2 wins\n\nPlay Again?");
							if(c==0)
							{
								AudioPlayer.player.stop(winas);
								AudioPlayer.player.start(as);
								playagain();
							}
							else
							{
								if(c==1)
									System.exit(0);
								else
								{};
							}
							repaint();

						}
					}	
				}	
			}
			repaint();
		}
		});

		b5.addActionListener(new ActionListener() 
		{ 
			public void actionPerformed(ActionEvent e2) 
			{
				try{
					clickin=new FileInputStream("crick.wav");
					clickas=new AudioStream(clickin);
				}
				catch(Exception e4)
				{
					e4.printStackTrace();
				}
				AudioPlayer.player.start(clickas);
				playagain();
			}
		});

		b6.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e3)
			{
				try{
					clickin=new FileInputStream("crick.wav");
					clickas=new AudioStream(clickin);
				}
				catch(Exception e4)
				{
					e4.printStackTrace();
				}
				AudioPlayer.player.start(clickas);
				AudioPlayer.player.stop(as);
			}
		});

		b7.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e4)
			{
				try{
					clickin=new FileInputStream("crick.wav");
					clickas=new AudioStream(clickin);
				}
				catch(Exception e5)
				{
					e5.printStackTrace();
				}
				AudioPlayer.player.start(clickas);
				AudioPlayer.player.start(as);
			}
		});
	}
	
	/*
	 * This function will help to reset the functionality again from the 
	 * beginning if the user wants to play the game again
	 */
	void playagain()
	{
		t3.setText("0");
		b3.setLocation(0, 500);
		t4.setText("0");
		b4.setLocation(50,500);
		panel2.remove(d1);
		panel2.remove(d2);
		panel2.remove(d3);
		panel2.remove(d4);
		panel2.remove(d5);
		panel2.remove(d6);
		l5.setLocation(550,136);
		panel2.add(b3);
		panel2.add(b4);
		a1.setText("begin the game by clicking on player 1");
		repaint();
	}
}

public class Snakes
{
	public static void main(String args[])
	{
		new Ladder();
	}
}
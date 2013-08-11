package net.patrickvogt.snake.frontend.swing;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame
{
	private static final long serialVersionUID = 2269971701250845501L;
	private static final String _TITLE = "SnakeSwing";
	
	public static final int _WIDTH = 800;
	public static final int _HEIGHT = 600;
	
	public MainFrame()
	{
		super(MainFrame._TITLE);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel p = new GamePanel();
		this.add(p);
		
		this.pack();
	}
}

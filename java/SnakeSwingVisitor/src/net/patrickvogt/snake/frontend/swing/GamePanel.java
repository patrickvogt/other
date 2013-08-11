package net.patrickvogt.snake.frontend.swing;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import net.patrickvogt.snake.backend.Food;
import net.patrickvogt.snake.backend.IPainter;
import net.patrickvogt.snake.backend.Snake;
import net.patrickvogt.snake.backend.SnakeElem;


public class GamePanel extends JPanel
{
	private static final long serialVersionUID = 1552746400473185110L;
	
private Timer _timer = null;
	
    /// <summary>
    /// the interval of the ticks
    /// </summary>
	private final int TIMER_INTERVAL = 300;
	
	 /// <summary>
    /// the snake
    /// </summary>
    private Snake s = null;

    /// <summary>
    /// the food for the snake
    /// </summary>
    private Food h = null;

    /// <summary>
    /// the painter /visitor which should draw the element onto the canvas
    /// </summary>
    IPainter _painter = null;
	
    /// <summary>
    /// tests, if the game is over
    /// </summary>
    public Boolean TestGameOver()
    {
        if (s.testGameOverSituation(MainFrame._WIDTH, MainFrame._HEIGHT))
        {
        	JOptionPane.showMessageDialog(this, "Game Over!");
            this._timer.stop();

            return true;
        }

        return false;
    }

    /// <summary>
    /// resets the game to its initial state
    /// </summary>
    private void reset()
    {
        s.reset();
        h.reset(0, 0, MainFrame._WIDTH, MainFrame._HEIGHT);
        this._timer.start();
    }

    /// <summary>
    /// a.o.t. initializes the XAMl components and the game timer
    /// </summary>
    public GamePanel()
    {
		this.addKeyListener(new KeyAdapter(){
			@Override
			public void keyReleased(KeyEvent key)
			{	
				if(key.getKeyCode() == KeyEvent.VK_DOWN)
				{
					s.down();
				}
				else if(key.getKeyCode() == KeyEvent.VK_UP)
				{
					s.up();
				}
				else if(key.getKeyCode() == KeyEvent.VK_LEFT)
				{
					s.left();
				}
				else if(key.getKeyCode() == KeyEvent.VK_RIGHT)
				{
					s.right();
				}
				else if(key.getKeyCode() == KeyEvent.VK_P)
				{
					if(GamePanel.this._timer.isRunning())
					{
						GamePanel.this._timer.stop();
					}
					else
					{
						GamePanel.this._timer.start();
					}
				}
			}
		});
		
		this._painter = new SwingPainter(this);

        s = new Snake(SnakeElem.WIDTH+Snake.PADDING, SnakeElem.HEIGHT+Snake.PADDING);
        h = new Food();
        h.reset(0, 0, MainFrame._WIDTH, MainFrame._HEIGHT);
	
	this._timer = new Timer(TIMER_INTERVAL, new ActionListener()
	{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (!TestGameOver())
		        {
		            s.pullTail();
		            s.move();
		        }

		    if (s.touches(h))
		        {
		            h.reset(0, 0, MainFrame._WIDTH, MainFrame._HEIGHT);
		            s.grow();
		    }

	        GamePanel.this._painter.reset();
	        h.paint( GamePanel.this._painter);
	        s.paint( GamePanel.this._painter);
	        
			
		}
		
	});
	this._timer.start();
	
	this.setFocusable(true);
	this.requestFocus();
    }
	
	@Override
	public Dimension getPreferredSize()
	{
		return new Dimension(MainFrame._WIDTH, MainFrame._HEIGHT);
	}

}

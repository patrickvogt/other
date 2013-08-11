package net.patrickvogt.snake.frontend.swing;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import net.patrickvogt.snake.backend.Barrier;
import net.patrickvogt.snake.backend.FigureCollection;
import net.patrickvogt.snake.backend.Food;
import net.patrickvogt.snake.backend.IPainter;
import net.patrickvogt.snake.backend.Rectangle;
import net.patrickvogt.snake.backend.Snake;
import net.patrickvogt.snake.backend.SnakeElem;
import net.patrickvogt.snake.backend.SnakeHead;


public class SwingPainter implements IPainter
{
	private JPanel _panel = null;
	
	public SwingPainter(JPanel panel)
	{
		this._panel = panel;
	}
	
	private void drawRectangle(Rectangle r, Color c)
	{
		Graphics g = this._panel.getGraphics();
		g.setColor(c);
		
		g.fillRect(r.getFoot().getX(), r.getFoot().getY(), 
				r.getDimension().getX(), r.getDimension().getY());
	}
	
	 /// <summary>
    /// paints the given Barrier onto the silverlight canvas
    /// </summary>
    /// <param name="b">the barrier which should be drawn</param>
	@Override
	public void paint(Barrier b) {
		this.drawRectangle(b, Color.magenta);
	}

    /// <summary>
    /// paints the given figure collection onto the silverlight canvas
    /// </summary>
    /// <param name="fc">the figureCollection which should be drawn onto the silverlight canvas</param>
	@Override
	public void paint(FigureCollection fc) {
		 for (int i = 0; i < fc.size(); i++)
	        {
	            fc.elementAt(i).paint(this);
	        }
	}

	 /// <summary>
    /// paints the given Food onto the silverlight canvas
    /// </summary>
    /// <param name="f">the food which should be drawn</param>
	@Override
	public void paint(Food f) {
		this.drawRectangle(f, Color.red);
	}


    /// <summary>
    /// paints the given Rectangle onto the silverlight canvas
    /// </summary>
    /// <param name="r">the rectangle which should be drawn</param>
	@Override
	public void paint(Rectangle r) {
		this.drawRectangle(r, Color.white);
	}

	/// <summary>
    /// paints the given Snake onto the silverlight canvas
    /// </summary>
    /// <param name="s">the snake which should be drawn</param>
	@Override
	public void paint(Snake s) {
		this.paint((FigureCollection)s);
	}

	/// <summary>
    /// paints the given Snake element onto the silverlight canvas
    /// </summary>
    /// <param name="e">the snake element which should be drawn</param>
	@Override
	public void paint(SnakeElem e) {
		this.paint((Rectangle)e);
	}

    /// <summary>
    /// paints the given Snake head onto the silverlight canvsd
    /// </summary>
    /// <param name="h">the snake head which should be drawn</param>
	@Override
	public void paint(SnakeHead h) {
		this.drawRectangle(h, Color.green);
	}

	 /// <summary>
    /// resets the graphic context to its initial state
    /// </summary>
	@Override
	public void reset()
	{
		Graphics g = this._panel.getGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, MainFrame._WIDTH, MainFrame._HEIGHT);
	}
	
}

<<<<<<< HEAD
package windows;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class MyCanvas extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5816233945679767459L;
	private Color color;

	public MyCanvas(Color color){
		this.color = color;
	}
	
	public void paint(Graphics g){
		super.paint(g);
		Color c = g.getColor();
		g.setColor(color);
		g.fillOval(50, 50, 50, 50);
		g.setColor(c);
	}
	public void setColor(Color color){
		this.color = color;
	}
}
=======
package windows;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class MyCanvas extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5816233945679767459L;
	private Color color;

	public MyCanvas(Color color){
		this.color = color;
	}
	
	public void paint(Graphics g){
		super.paint(g);
		Color c = g.getColor();
		g.setColor(color);
		g.fillOval(50, 50, 50, 50);
		g.setColor(c);
	}
	public void setColor(Color color){
		this.color = color;
	}
}
>>>>>>> refs/remotes/origin/master

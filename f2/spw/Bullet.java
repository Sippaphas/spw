package f2.spw;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Bullet extends Sprite {

	 
	private int step = 12;
	private boolean alive = true;

	public Bullet(int x, int y) {
		super(x, y, 5, 5);
	}

	@Override
	public void draw(Graphics2D g) {
/*
		if(y < Y_TO_FADE)
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
		else{
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 
					(float)(Y_TO_DIE - y)/(Y_TO_DIE - Y_TO_FADE)));
		}
*/
		g.setColor(Color.GREEN);
		g.fillRect(x, y, width, height);
		
	}

	public void proceed(){
		y += step;
	/*	if(y > Y_TO_DIE){
			alive = false;
		}*/
	}
	
	
	
	public boolean isAlive(){
		return alive;
	}

	public void fire(){
		System.out.println("Fire");
	}
}

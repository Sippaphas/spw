package f2.spw;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class SpaceShip extends Sprite {

	int step = 25;
	int gunx = x ;
	int guny = y ; 
	int guntmp = 0; 
	
	public SpaceShip(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.BLUE);
		g.fillRect(x, y, width, height);
		g.fillRect(x+5,y-20,10,20);
	}

	public void setPosition(){
		x = 250;
		y = 550;
	}

	public void mouseMove(int mousex,int mousey){
		x=mousex;
		y=mousey;
	}

	public void stickMove(int stickx,int sticky){
		x=stickx;
		y=sticky;
	}

	public void move(int direction){
		x += (step * direction);
		if(x < 0)
			x = 0;
		if(x > 400 - width)
			x = 400 - width;
		
		System.out.println("X Position:"+x);

	}

	public void movey(int ydirection){
		y += (step * ydirection);
		if(y < 0)
			y = 0;
		if(y > 550)
			y = 550;
		System.out.println("Y Position:"+y);
	}
}

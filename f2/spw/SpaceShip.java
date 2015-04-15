package f2.spw;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class SpaceShip extends Sprite {

	int step = 50;
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

	public void move(int direction){
		x += (step * direction);
		if(x < 0)
			x = 0;
		if(x > 400 - width)
			x = 400 - width;
		
		

	}

	public void movey(int ydirection){
		y += (step * ydirection);
		if(y < 0)
			y = 0;
		if(y > 550)
			y = 550;
	}

	public void moveGunX(int gunxdirection){
		
		/*gunx += (step * gunxdirection);
		if(gunx < 0)
			gunx = 0;
		if(gunx > 400 - width)
			gunx = 400 - width;
		*/


	}

	public void moveGunY(int gunydirection){

		/*guny += (step * gunydirection);
		if(guny < 0)
			guny = 0;
		if(guny > 400 - width)
			guny = 400 - width;
		*/


	}

	public void turnGun(int gundirection){
		guntmp += gundirection ; 
		if(guntmp == 0){
			gunx = x+5  ; 
			guny = y+30; 
		}else if (guntmp == 1){
			gunx = x ; 
			guny = y + 10 ;
		}else if (guntmp == 2){
			gunx = x ; 
			guny = y - 10 ; 
		}else if (guntmp == 3){
			gunx = x - 10; 
			guny = y; 
		}else {
			guntmp = 0 ; 
		}
	}

}

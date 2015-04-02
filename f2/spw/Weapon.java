package f2.spw;

import java.awt.Color;
import java.awt.Graphics2D;

public class Weapon extends Sprite{

	int step = 50;
	
	public Weapon(int x, int y, int width, int height) {
		super(x, y, width, height);
		
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.GREEN);
		g.drawLine(x+5, y+5, width, height);
		
	}

	
	public void moveGun(int direction){
		x += (step * direction);
		System.out.println("Gun axis"+x);
		if(x < 0)
			x = 0;
		if(x > 200)
			x = 0;
	}
}

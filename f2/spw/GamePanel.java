package f2.spw;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.EventQueue;
import java.awt.*;
import net.java.games.input.*;


public class GamePanel extends JPanel{
	
	private BufferedImage bi;	
	Graphics2D big;
	ArrayList<Sprite> sprites = new ArrayList<Sprite>();
	ArrayList<String> choice = new ArrayList<String>();
	JFrame frame = new JFrame();
	ControllerEnvironment ce = ControllerEnvironment.getDefaultEnvironment(); 
	Controller[] cs = ce.getControllers(); 
	
	public GamePanel() {
		bi = new BufferedImage(400, 600, BufferedImage.TYPE_INT_ARGB);
		big = (Graphics2D) bi.getGraphics();
		big.setBackground(Color.BLACK);
	}

	public void updateGameUI(GameReporter reporter){
		big.clearRect(0, 0, 400, 600);
		
		big.setColor(Color.WHITE);		
		big.drawString(String.format("%08d", reporter.getScore()), 300, 20);
		for(Sprite s : sprites){
			s.draw(big);
		}
		
		repaint();
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(bi, null, 0, 0);
	}

	public String askUser() {

		for (int i = 0; i < cs.length; i++){
			choice.add(cs[i].getType().toString());
			//System.out.println(choices[i]);
		}
		
		Object[] choices = choice.toArray();
		
        String s = (String) JOptionPane.showInputDialog(
                frame,"EnterInput","EnterInputDevice",
                JOptionPane.QUESTION_MESSAGE, 
        		null, 
		        choices, 
		        choices[0]);
        return s ;
    }

  //  public

}

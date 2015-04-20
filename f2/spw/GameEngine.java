package f2.spw;

import java.awt.event.ActionEvent;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.Timer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import net.java.games.input.*;



public class GameEngine  implements KeyListener, GameReporter,MouseMotionListener,MouseListener {
	GamePanel gp;
		
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();	
	private SpaceShip v;
	//private Bullet bul; 
	private String inputstatus;
	private Timer timer;
	
	private long score = 0;
	public int life = 1; 
	private double difficulty = 0.1;
	private JFrame frame = new JFrame();
	JInputJoystick joystick = new JInputJoystick(Controller.Type.STICK);

	


	public GameEngine(GamePanel gp, SpaceShip v) {
		this.gp = gp;
		this.v = v;	
		askInputUser();
		gp.sprites.add(v);

		if(inputstatus == "Mouse"){
			gp.addMouseMotionListener(this);
			gp.addMouseListener(this);

		}else if(inputstatus == "Keyboard"){
			//do nothing
		}

		System.out.println(inputstatus);
		
		timer = new Timer(50, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				process();
			}
		});
		timer.setRepeats(true);

		

	}
	
	public void start(){
		timer.start();
	}

	public void stop(){
		timer.stop();
	}

	public void run(){
		stickTypeJoystick();
	}	

	public void restart(){
		gp.sprites.clear();
		
		enemies.clear();
		
		v.setPosition();
		gp.sprites.add(v);
		difficulty = 0.1;
		score = 0;
		life = 1 ; 
		timer.restart();	
	}
	
	private void generateEnemy(){
		Enemy e = new Enemy((int)(Math.random()*390), 30);
		gp.sprites.add(e);
		enemies.add(e);
	}
	
	private void process(){
		if(Math.random() < difficulty){
			generateEnemy();
		}
		
		Iterator<Enemy> e_iter = enemies.iterator();
		while(e_iter.hasNext()){
			Enemy e = e_iter.next();
			e.proceed();
			
			if(!e.isAlive()){
				e_iter.remove();
				gp.sprites.remove(e);
				score += 100;
				System.out.println("Score:"+score);

				if(score%10000 == 0 ){
					life++;
					System.out.println("Life:"+life);
				}
			}

			
		}
		
		gp.updateGameUI(this);
		
		Rectangle2D.Double vr = v.getRectangle();
		
		Rectangle2D.Double er;
		for(Enemy e : enemies){
			er = e.getRectangle();
			if(er.intersects(vr)){
				//die();
				life--;
				if(life<=0){
					die();
				}
				return;
			}
		}

		//polling Joystick 
		if(inputstatus == "Stick"){
			stickTypeJoystick();
		}
	}
	
	public void die(){
		timer.stop();
		
		Object[] options = {"Exit",
                    "Try Again"};
		int n = JOptionPane.showOptionDialog(frame,
	    "Do you want try again?",
	    "You Die!! Game Over",
	    JOptionPane.YES_NO_OPTION,
	    JOptionPane.QUESTION_MESSAGE,
	    null,     //do not use a custom Icon
	    options,  //the titles of buttons
	    options[0]); //default button title

		if(n==0){
			System.exit(0);
		}else {
			restart();
		}

	}
	
	void controlVehicle(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			v.move(-1);
			//v.moveGunX(-1);
			break;
		case KeyEvent.VK_RIGHT:
			v.move(1);
			//v.moveGunX(1);
			break;
		case KeyEvent.VK_UP:
			v.movey(-1);
			//v.moveGunY(-1);
			break;
		case KeyEvent.VK_DOWN:
			v.movey(1);
			//v.moveGunY(1);
			break;			
				
		case KeyEvent.VK_F4:
			difficulty += 0.1;
			break;

		case KeyEvent.VK_F1:
			difficulty -= 0.1;
			break;	
		case KeyEvent.VK_F:
		//	bul.proceed();


		}
	}

	void controlmouseVehicle(MouseEvent m){
		v.mouseMove(m.getX(),m.getY());
	}

	public void askInputUser(){
		inputstatus = gp.askUser();
	}
	
	public String getInputStatus(){
		return inputstatus;
	}

	public long getScore(){
		return score;
	}

	public int getLife(){
		return life;
	}



	public void stickTypeJoystick(){
            joystick.pollController();
            int xAxisValuePercentage = (joystick.getXAxisPercentage()*380)/100;
            int yAxisValuePercentage = (joystick.getYAxisPercentage()*550)/100;
            
            v.stickMove(xAxisValuePercentage,yAxisValuePercentage);

            //System.out.println("X:"+xAxisValuePercentage);
            //System.out.println("Y:"+yAxisValuePercentage);

	}



	@Override
	public void keyPressed(KeyEvent e) {

		if(inputstatus == "Keyboard"){
			controlVehicle(e);	
		}
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		//do nothing
	}

	@Override
	public void keyTyped(KeyEvent e) {
		//do nothing		
	}

	@Override
	public void mouseMoved(MouseEvent m){
		controlmouseVehicle(m);
	}

	@Override
	public void mouseDragged(MouseEvent m) {
		//do nothing
	}

	@Override
	public void mousePressed(MouseEvent m) {
       // System.out.println("Fire !!!");
    }

    public void mouseClicked(MouseEvent em) {
		System.out.println("Fire !!!");    
	}

    @Override
    public void mouseEntered(MouseEvent m) {
    	//
    }
    @Override
    public void mouseExited(MouseEvent m) {

    }
    @Override
    public void mouseReleased(MouseEvent m) {

    }

	
}

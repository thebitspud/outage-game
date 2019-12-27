package net.tbs.cmd.main;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JPanel;

import net.tbs.cmd.assets.ResourceLoader;
import net.tbs.cmd.states.State;

//class that runs the gameloop and graphics engine

public class Engine extends JPanel implements Runnable {
	private static final long serialVersionUID = 1L;
	
	//importing main classes
	
	private Window window;
	private Thread thread;
	
	//gameloop variables
	
	private boolean running = false;
	private final int maxFPS = 60;
	
	//graphics management
	
	private Graphics g;
	private BufferStrategy bs;
	private ResourceLoader rl;
	
	//game manager
	
	private Manager manager;

	//starting the gameloop and accessing an icon image
	
	public Engine(Window window) {
		this.window = window;
		
		rl = new ResourceLoader();
		rl.loadIcons(window);
		
		manager = new Manager(this);
		
		start();
	}
	
	//initializing post-gameloop classes and variables
	
	public void init() {
		manager.init();
	}
	
	//updating classes and variables as gameloop runs
	
	public void update() {
		if(State.getState() == null) State.setState(manager.getMenuState());
		else State.getState().update();
		
		manager.update();
	}
	
	//buffered graphics
	
	public void render() {
		bs = window.getBufferStrategy();
		
		if(bs == null) {
			window.createBufferStrategy(3);
			return;
		}
		
		g = bs.getDrawGraphics();
		
		g.clearRect(0, 0, window.getWidth(), window.getHeight());
		
		//rendering the active state
		
		if(State.getState() != null) State.getState().render(g);
		
		bs.show();
		g.dispose();
	}
	
	//improved gameloop
	
	private double FPS;

	public void run() {
		init();
		
		double tickDelay = 1000000000 / maxFPS;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		
		long timer = 0;
		int frames = 0;
		
		while(running) {
			now = System.nanoTime();
			delta += (now - lastTime) / tickDelay;
			timer += now - lastTime;
			lastTime = now;
			
			if(delta >= 1) {
				update();
				render();
				
				frames++;
				delta--;
			}
			
			if(timer >= 1000000000) {
				FPS = frames;
				frames = 0;
				timer = 0;
			}
		}
		
		stop();
	}
	
	//getters and setters
	
	public synchronized void start() {
		if(running) return;
		
		running = true;
		
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop() {
		if(!running) return;
		
		running = false;
		
		try{
			thread.join();
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public Window getWindow() {
		return window;
	}
	
	public double getFPS() {
		return FPS;
	}
	
	public int getMaxFPS() {
		return maxFPS;
	}
	
	public ResourceLoader getResourceLoader() {
		return rl;
	}
}
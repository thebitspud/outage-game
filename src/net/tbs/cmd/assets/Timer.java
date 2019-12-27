package net.tbs.cmd.assets;

//a basic but versatile timer that can be utilized to place delays on command

public class Timer {
	private int frames, delay;
	private boolean activated;
	
	public Timer(int delay) {
		this.delay = delay;
		this.frames = 0;
		
		//frames and delay are measured in centiseconds
	}

	public void tick() {
		if(frames >= delay) {
			activated = true;
		}else{
			frames++;
			activated = false;
		}
	}
	
	//getters and setters
	
	public boolean isActivated() {
		tick();

		return activated;
	}

	public int getDelay() {
		return delay;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}
	
	public int getFrame() {
		return frames;
	}
	
	public void reset() {
		frames = 0;
	}
}
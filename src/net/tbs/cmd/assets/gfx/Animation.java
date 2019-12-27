package net.tbs.cmd.assets.gfx;

//simple class that allows for the quick creation of animated sprites

public class Animation {
	protected int CurrentFrame, FrameCount, CycleSpeed, duration;
	protected boolean activated;
	
	public Animation(int frames, int cycle) {
		FrameCount = frames;
		CurrentFrame = 0;
		CycleSpeed = cycle;
		duration = 0;
		
		//frames = number of separate frames
		//cycle = the number of ticks between each frame
		//CS/FPS = milliseconds per frame
	}
	
	public void tick() {
		if(duration == CycleSpeed) activated = true;
		else activated = false;
		
		if(duration >= CycleSpeed) {
			duration = 0;
			
			CurrentFrame++;
		}
		
		if(CurrentFrame >= FrameCount) CurrentFrame = 0;
		duration++;
		
		//Using a 2-step rotating timer to run the animation
	}

	public int getFrame() {
		return CurrentFrame;
	}

	public int getCycleSpeed() {
		return CycleSpeed;
	}
	
	public boolean isActivated() {
		tick();
		
		return activated;
	}
}
package org.msn;

import javax.swing.JLabel;

import org.msn.visual.MSNpanel;

public class Results {

	private int delay;
	private int delayVar;
	private int lostPkg;
	private int hops;
	private MSNpanel parent;
	
	public Results(MSNpanel parent) 
	{
		this.parent = parent;
		this.delay = 0;
		this.delayVar = 0;
		this.lostPkg = 0;
		this.hops = 0;
	}
	
	public void getResults() 
	{
		this.parent.setLbl_results_11("" + this.delay);
		this.parent.setLbl_results_21("" + this.delayVar);
		this.parent.setLbl_results_31("" + this.lostPkg);
		this.parent.setLbl_results_41("" + this.hops);
	}
	
	public void setResults(int delay, int delayVar, int lostPkg, int hops)
	{
		this.delay = delay;
		this.delayVar = delayVar;
		this.lostPkg = lostPkg;
		this.hops = hops;
	}
	
	
	public int getDelay() {
		return delay;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}

	public int getDelayVar() {
		return delayVar;
	}

	public void setDelayVar(int delayVar) {
		this.delayVar = delayVar;
	}

	public int getLostPkg() {
		return lostPkg;
	}

	public void setLostPkg(int lostPkg) {
		this.lostPkg = lostPkg;
	}

	public int getHops() {
		return hops;
	}

	public void setHops(int hops) {
		this.hops = hops;
	}
}

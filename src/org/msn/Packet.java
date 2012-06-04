package org.msn;

public class Packet {
	private Address destinationAddress;
	private Address sourceAddress;
	private int ttl;
	private int hopsCount;
	private int delay; //opoznienie w sensie ilosci krokow - jesli pakiet czeka w buforze kolejke to te¿ +1 (tym siê ró¿ni od hopsCounta)
	private int[] dr;
	
	public Packet(Address destinationAddress, Address sourceAddress, int ttl, int netSize, boolean deadReckoning) 
	{
		this.destinationAddress = destinationAddress;
		this.sourceAddress = sourceAddress;
		this.ttl = ttl;
		this.hopsCount = 0;
		this.delay = 0;
		this.dr = new int[2];
		if (deadReckoning) {
			//int half = (int) Math.ceil((double)((netSize-1)/2));
			int pathXlength = destinationAddress.getX()-sourceAddress.getX();
			int pathYlength = destinationAddress.getY()-sourceAddress.getY();
			if (pathXlength <= (netSize-pathXlength))
				this.dr[0]=0;	//idz do przodu
			else
				this.dr[0]=1; //idz do ty³u
			if (pathYlength <= (netSize-pathYlength))
				this.dr[1]=0;	//idz do przodu
			else
				this.dr[1]=1; //idz do ty³u
		}
	}
	
	public Address getDestinationAddress() {
		return this.destinationAddress;
	}

	public boolean update() {
		++hopsCount;
		++delay;
		if (--ttl==0) 
			return false;
		else
			return true;
	}
	
	public void updateDelay() {
		++delay;
	}

	public int getHopsCount() {
		return hopsCount;
	}

	public Address getSourceAddress() {
		return this.sourceAddress;
	}
	
	public int[] getDRheader() {
		return dr;
	}

	public int getDelay() {
		return delay;
	}
	
}

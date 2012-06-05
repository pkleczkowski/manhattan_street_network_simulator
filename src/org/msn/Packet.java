package org.msn;

import java.util.Random;

public class Packet {
	private Address destinationAddress;
	private Address sourceAddress;
	private int ttl;
	private int hopsCount;
	private int delay; //opoznienie w sensie ilosci krokow - jesli pakiet czeka w buforze kolejke to te� +1 (tym si� r�ni od hopsCounta)
	private int[] dr;
	
	public Packet(Address destinationAddress, Address sourceAddress, int ttl, int netSize, boolean deadReckoning) 
	{
		Random rand = new Random();
		this.destinationAddress = destinationAddress;
		this.sourceAddress = sourceAddress;
		this.ttl = ttl;
		this.hopsCount = 0;
		this.delay = 0;
		this.dr = new int[2];
		if (deadReckoning) {
			//int half = (int) Math.ceil((double)((netSize-1)/2));
			int pathXlength = destinationAddress.getX()-sourceAddress.getX();
			int goBylineX=netSize-Math.abs(pathXlength);
			int pathYlength = destinationAddress.getY()-sourceAddress.getY();
			int goBylineY=netSize-Math.abs(pathYlength);
			
			if(pathXlength<0){
				if(goBylineX==Math.abs(pathXlength)){
					int i=rand.nextInt(100);
					if(i<50)
						this.dr[0]=0;
					else
						this.dr[0]=1;
				}else if(goBylineX<Math.abs(pathXlength)){
					dr[0]=0;
				}
				else
					dr[0]=1;
			}
			else{
				if(goBylineX==Math.abs(pathXlength)){
					int i=rand.nextInt(100);
					if(i<50)
						this.dr[0]=0;
					else
						this.dr[0]=1;
				}else if(goBylineX<Math.abs(pathXlength)){
					dr[0]=1;
				}
				else
					dr[0]=0;
				
			}
			
			if(pathYlength<0){
				if(goBylineY==Math.abs(pathYlength)){
					int i=rand.nextInt(100);
					if(i<50)
						this.dr[1]=0;
					else
						this.dr[1]=1;
				}else if(goBylineY<Math.abs(pathYlength)){
					dr[1]=1;
				}
				else
					dr[1]=0;
			}
			else{
				if(goBylineY==Math.abs(pathYlength)){
					int i=rand.nextInt(100);
					if(i<50)
						this.dr[1]=0;
					else
						this.dr[1]=1;
				}else if(goBylineY<Math.abs(pathYlength)){
					dr[1]=0;
				}
				else
					dr[1]=1;
				
			}
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

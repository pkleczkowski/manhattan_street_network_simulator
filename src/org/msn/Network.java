package org.msn;

import org.msn.visual.MSNpanel;

public class Network implements Runnable {
	private MSNpanel parent;
	private Parameters parameters;
	public Router[][] routers;
	private int lostPackets;
	private int packetsCount;
	
	private boolean working;

	public Network(Parameters parameters, MSNpanel panel) {
		this.parameters = parameters;
		this.parent = panel;
		int n = this.parameters.getNetSize();
		routers = new Router[n][n];
		for (int i=0;i<n;i++)
		{
			for (int j=0;j<n;j++)
			{
				 routers[i][j] = new Router(i,j,parameters,this);
			};
		}
	}

	@Override
	public void run() {
		this.lostPackets = 0;
		this.packetsCount = 0;
		while (true) {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (working) {

				// first step is to do work on routers - generate packets,
				// receive packets from input interfaces and forward them to
				// the proper output interfaces
				int n = this.parameters.getNetSize();
				for (int i = 0; i < n; i++) {
					for (int j = 0; j < n; j++) {
						routers[i][j].sendPackets();
					}
				}
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				for (int i = 0; i < n; i++) {
					for (int j = 0; j < n; j++) {
						routers[i][j].generatePackets(n,
								this.parameters.getTtl());
						routers[i][j].forwardPackets();
					}
				}
				// second step is to send packets, it means that each router
				// output interface send only one packet
			}
		}
	}
	
	synchronized public void setWorking(boolean b) {
		this.working = b;
	}
	
	public void updateParameters(Parameters parameters) {
		this.parameters = parameters; //TODO m.innymi trzeba setDelay w FCin routera zrobic uaktualnic
	}

	public void sendPacketX(Address nextHopAddress, Packet packet) {
		if (packet!=null)
			routers[nextHopAddress.getX()][nextHopAddress.getY()].receivePacketX(packet);
	}
	public void sendPacketY(Address nextHopAddress, Packet packet) {
		if (packet!=null)
			routers[nextHopAddress.getX()][nextHopAddress.getY()].receivePacketY(packet);
	}

	public void updateReceived(int hopsCount, int delay) {
		this.parent.updateReceived(++packetsCount,hopsCount,delay);
	}

	public void updatelostPackets() {
		this.parent.updateLost(++lostPackets);
	}

}

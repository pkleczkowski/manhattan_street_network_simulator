package org.msn;

import java.util.Random;

public class RandomRoutingTable implements RoutingTable {

	private Address routerAddress;
	private Random ran; 
	public RandomRoutingTable(Address address) {
			this.routerAddress = address;
			this.ran = new Random();
		}
		
	@Override
	public int getOutputInterfaceId(Packet packet) {
		if (packet.getDestinationAddress().getX()==this.routerAddress.getX() 
				&& packet.getDestinationAddress().getY()==this.routerAddress.getY())
			return 3;
		else
			return ran.nextInt(2)+1; //zwraca 1 albo 2 - sprawdzone
	}

}

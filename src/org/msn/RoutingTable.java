package org.msn;

public interface RoutingTable {
	//private Address routerAddress;
	
	//public RoutingTable(Address address) {
	//	this.routerAddress = address;
	//}

	public int getOutputInterfaceId(Packet packet);
	/*{
		if (destinationAddress.getX()==this.routerAddress.getX() 
				&& destinationAddress.getY()==this.routerAddress.getY())
			return 3;
		else
			return 1;
	}*/
	
}

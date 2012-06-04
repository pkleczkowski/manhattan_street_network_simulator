package org.msn;

import java.util.Random;

public class IntelligentRandomRoutingTable implements RoutingTable {

	private Router parentRouter;
	private Random ran; 
	public IntelligentRandomRoutingTable(Router router) {
			this.parentRouter = router;
			this.ran = new Random();
		}
		
	@Override
	public int getOutputInterfaceId(Packet packet) {
		if (packet.getDestinationAddress().getX()==this.parentRouter.getAddress().getX() 
				&& packet.getDestinationAddress().getY()==this.parentRouter.getAddress().getY())
			return 3;
		else if (packet.getDestinationAddress().getX()==this.parentRouter.b1.getNextHopAddress().getX()
				&& packet.getDestinationAddress().getY()==this.parentRouter.b1.getNextHopAddress().getY())
			return 1;
		else if (packet.getDestinationAddress().getX()==this.parentRouter.b2.getNextHopAddress().getX()
				&& packet.getDestinationAddress().getY()==this.parentRouter.b2.getNextHopAddress().getY())
			return 2;
		else 
		{
			return ran.nextInt(2)+1;
		}
	}

}

package org.msn;

import java.util.Random;


public class DeadReckoningRoutingTable implements RoutingTable {

	private Router parentRouter;
	private Random rand;
	
	public DeadReckoningRoutingTable(Router router) {
			this.parentRouter = router;
		}
		
	@Override
	public int getOutputInterfaceId(Packet packet) {
		this.rand = new Random();
		if (packet.getDestinationAddress().getX()==this.parentRouter.getAddress().getX() 
				&& packet.getDestinationAddress().getY()==this.parentRouter.getAddress().getY())
			return 3;
		else {
			if (packet.getDestinationAddress().getX()==this.parentRouter.getAddress().getX()) { //je¿eli doszedl do wiersza w ktorym jest dest row to przesylaj juz tylko po kolumnie
				return 2;
			}
			else if (packet.getDestinationAddress().getY()==this.parentRouter.getAddress().getY()) { //je¿eli doszedl do kolumny w ktorym jest dest row to przesylaj juz tylko po kolumnie
				return 1;
			}
			else {	
				int i=rand.nextInt(100);
				if(i<50) {
					if (packet.getDRheader()[0]==1) {//czy preferencja jest zeby isc w dó³ jak tak to sprawdzam czy b1 idzie w dol
						if (this.parentRouter.b1.getNextHopAddress().getX()>this.parentRouter.getAddress().getX())
							return 1;
						else //no to niech idzie po kolumnie i niech nastepny sie martwi
							return 2;
					}
					else {
						if (this.parentRouter.b1.getNextHopAddress().getX()<this.parentRouter.getAddress().getX())
							return 1;
						else //no to niech idzie po kolumnie i niech nastepny sie martwi
							return 2;
					}
				}
				else {
					if (packet.getDRheader()[0]==1) {//czy preferencja jest zeby isc w prawo jak tak to sprawdzam czy b2 idzie w prawo
						if (this.parentRouter.b2.getNextHopAddress().getY()>this.parentRouter.getAddress().getY())
							return 2;
						else //no to niech idzie po kolumnie i niech nastepny sie martwi
							return 1;
					}
					else {
						if (this.parentRouter.b2.getNextHopAddress().getY()<this.parentRouter.getAddress().getY())
							return 2;
						else //no to niech idzie po kolumnie i niech nastepny sie martwi
							return 1;
					}
				}
				
			}
		}
	}

}

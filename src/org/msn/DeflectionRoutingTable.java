package org.msn;


public class DeflectionRoutingTable implements RoutingTable {

	private Router parentRouter;
	public DeflectionRoutingTable(Router router) {
			this.parentRouter = router;
		}
		
	@Override
	public int getOutputInterfaceId(Packet packet) { //ret: 3 - to tutaj (w tym routerze odbior); 1 = idz przez b1 interfejs (gora/dol); 2-lewo/prawo
		int step = shortestPath(packet);
		if (step==3)
			return 3;
		else if (step==1) 
		{
			if (!this.parentRouter.b1.isPacketInBufferCell(1)) 
	        	   return 1;
	           else if (!this.parentRouter.b2.isPacketInBufferCell(1)) 
	        	   return 2;
	           else {
	        	   System.out.println("TUTAJ TKWI SZKOP POCHOWANY");
	        	   return 0; //odrzuc pakiet
	           }
		}
		else if (step==2) {
			if (!this.parentRouter.b2.isPacketInBufferCell(1)) 
	        	   return 2;
	           else if (!this.parentRouter.b1.isPacketInBufferCell(1)) 
	        	   return 1;
	           else {
	        	   System.out.println("TUTAJ TKWI SZKOP POCHOWANY");
	        	   return 0; //odrzuc pakiet
	           }
		}
		else {
			System.out.println("AHA!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			return 0;
			
		}
		
		
	}

	private int shortestPath(Packet packet) {
		if (packet.getDestinationAddress().getX()==this.parentRouter.getAddress().getX() //jesli to tutaj
				&& packet.getDestinationAddress().getY()==this.parentRouter.getAddress().getY())
			return 3;
		else { //narazie najkrotsza sciezka tylko (nie deflection jeszcze)
			int wDol = 1; //counter czy isc w dol = 1-tak 0-to w tym wierszu destination, -1 to w gore
			int wPrawo = 1;
			boolean zgodnieX = true; //czy isc normalnie czy na oko³o
			boolean zgodnieY = true;
			
			int odlegloscX = packet.getDestinationAddress().getX() - this.parentRouter.getAddress().getX(); //jakie sa odleglosci 
			int odlegloscY = packet.getDestinationAddress().getY() - this.parentRouter.getAddress().getY(); //pomiedzy dest a aktual
			
			int roznicaX = this.parentRouter.getNetSize() - Math.abs(odlegloscX);	//jakie sa odleglosci w droga strone (na okolo)
			int roznicaY = this.parentRouter.getNetSize() - Math.abs(odlegloscY);	//pomiedzy pkt dest a aktual
			if (Math.abs(odlegloscX) <= roznicaX) {//blizej lub tak samo bezposrednio
				if (odlegloscX<0)
					wDol = -1;
				else if (odlegloscX==0)
					wDol = 0;
			}
			else {//lepiej na oko³o
				zgodnieX = false;
				if (odlegloscX>0)
					wDol = -1;
				else if (odlegloscX==0)
					wDol = 0;
			}
			if (Math.abs(odlegloscY) <= roznicaY) {//blizej lub tak samo bezposrednio
				if (odlegloscY<0)
					wPrawo = -1;
				else if (odlegloscY==0)
					wPrawo = 0;
			}
			else {//lepiej na oko³o
				zgodnieY = false;
				if (odlegloscY>0)
					wPrawo = -1;
				else if (odlegloscY==0)
					wPrawo = 0;
			}
			
			int drogaX = 0;
			int drogaY=0;
			if (zgodnieX) drogaX = Math.abs(odlegloscX);
			else drogaX = Math.abs(roznicaX);
			if (zgodnieY) drogaY = Math.abs(odlegloscY);
			else drogaY = Math.abs(roznicaY);
			
			//teraz jesli ktoras z preferencji ale tylko jedna jest spe³niona czyli np. najbli¿ej mi w gore i w prawo
			//a z routera wychodzi w gore i lewo
			//to nie ma siê co zastanawiac tylko popierdzielac w gore (nastepny niech sie martwi czy nie skrecic)
			//nawet jak juz jestem w danej kolumnie czy wierszu to dojdzie do niego wiec spoko
			
			int nextHopX = this.parentRouter.b1.getNextHopAddress().getX() - this.parentRouter.getAddress().getX();	//w ktora strone
			int nextHopY = this.parentRouter.b2.getNextHopAddress().getY() - this.parentRouter.getAddress().getY(); //wyjscia
			boolean wDobraStroneX = ((wDol*nextHopX) > 0);	
			boolean wDobraStroneY = ((wPrawo*nextHopY) > 0);
			
			if (wDobraStroneX && !wDobraStroneY) {	//jesli tylko x jest w dobra strone to jedz po nim
				return 1;
			}
			else if (!wDobraStroneX && wDobraStroneY) {	//jesli tylko y jest w dobra strone to jedz po nim	
				return 2;
			}
			else if (wDobraStroneX && wDobraStroneY) {	//jesli oba sa w dobra strone to jedz po tym co jest parzyscie
				if (drogaX%2 == 0)					//jesli oba sa nieparzyscie to czekaj chyba
					return 1;
				//if (drogaY%2 == 0)
				else
					return 2;
			}
			else {	//oba sa w zla to idz tam gdzie mniejsza droga
				if (drogaX>=drogaY) {
					if (wPrawo!=0)
						return 2;
					else	//jeœli to ju¿ ta kolumna
						return 1;
				}
				else {
					if (wDol!=0) {
						return 1;
					}
					else return 2;
				}
			}
			
		}
	}

}

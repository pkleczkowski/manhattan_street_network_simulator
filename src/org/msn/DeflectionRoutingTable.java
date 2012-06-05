package org.msn;


public class DeflectionRoutingTable implements RoutingTable {

	private Router parentRouter;
	public DeflectionRoutingTable(Router router) {
			this.parentRouter = router;
		}
		
	@Override
	public int getOutputInterfaceId(Packet packet) { //ret: 3 - to tutaj (w tym routerze odbior); 1 = idz przez b1 interfejs (gora/dol); 2-lewo/prawo
		GenerateStepsAlgorytm alg = new GenerateStepsAlgorytm(parentRouter.getAddress().getX(), 
				parentRouter.getAddress().getY(),
				packet.getDestinationAddress().getX(),
				packet.getDestinationAddress().getY(),
				parentRouter.getNetSize());
		
		Step step = alg.getNextStep();
		 if(step==Step.DOWN || step==Step.TOP){
	           if (!this.parentRouter.b1.isPacketInBufferCell(1)) 
	        	   return 1;
	           else if (!this.parentRouter.b2.isPacketInBufferCell(1)) 
	        	   return 2;
	           else return 0; //odrzuc pakiet
	       }
	       else if(step==Step.LEFT || step==Step.RIGHT){
	    	   if (!this.parentRouter.b2.isPacketInBufferCell(1)) 
	        	   return 2;
	           else if (!this.parentRouter.b1.isPacketInBufferCell(1)) 
	        	   return 1;
	           else {
	        	   System.out.println("aaaaaaa");
	        	   return 0; 
	           //odrzuc pakiet
	           }
	       }
	       else //if(step==Step.NO_STEP){
	           return 3;
		 	
	       //}
		
	}

}

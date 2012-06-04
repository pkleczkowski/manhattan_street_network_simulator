package org.msn;


public class ShortestPathRoutingTable implements RoutingTable {

	private Router parentRouter;
	public ShortestPathRoutingTable(Router router) {
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
	           return 1;
	       }
	       else if(step==Step.LEFT || step==Step.RIGHT){
	           return 2;
	       }
	       else //if(step==Step.NO_STEP){
	           return 3;
	       //}
	}

}

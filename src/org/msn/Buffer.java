package org.msn;

import java.util.Iterator;
import java.util.LinkedList;

public class Buffer {
	private Address nextHopAddress;
	private int bufferSize;
	public LinkedList<Packet> fifo;
	private Router parentRouter;
	
	public Buffer(Router router, Address address, int buffer) {
		parentRouter = router;
		nextHopAddress = address;
		bufferSize = buffer;
		fifo = new LinkedList<Packet>();
	}
	
	public void offer(Packet packet) {
		//if (fifo.size()) //TODO
		if (fifo.size()==bufferSize) {
			//odrzuc pakiet
			updateLost();
		}
		else if (fifo.size()>bufferSize) {
			System.out.println("Coœ jest kuTwa nie tak! za duzo w buforach!");
		}
		else
			fifo.offer(packet);
	}
	
	private void updateLost() {
		parentRouter.updateLost();
	}

	public Address getNextHopAddress() {
		return nextHopAddress;
	}
	
	public int getBufferSize() {
		return bufferSize;
	}
	
	public Packet getPacket() {
		Packet tmp = fifo.poll();
		Iterator<Packet> itr = fifo.iterator();
		while(itr.hasNext())
		    {
		      itr.next().updateDelay();
		    }
		return tmp;
	}
	
	public boolean isPacketInBufferCell(int cellID) {
		if (fifo.size()>=cellID) 
			return true;
		else
			return false;
	}
}

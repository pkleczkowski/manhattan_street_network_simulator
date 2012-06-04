package org.msn;

import java.util.Iterator;
import java.util.LinkedList;

public class Buffer {
	private Address nextHopAddress;
	private int bufferSize;
	public LinkedList<Packet> fifo;
	
	public Buffer(Address address, int buffer) {
		nextHopAddress = address;
		bufferSize = buffer;
		fifo = new LinkedList<Packet>();
	}
	
	public void offer(Packet packet) {
		//if (fifo.size()) //TODO
		fifo.offer(packet);
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

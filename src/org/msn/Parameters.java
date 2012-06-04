package org.msn;

public class Parameters {

	private int netSize;
	private int newPkg;
	private int bufferOut;
	private int ttl;
	private String routing;
	
	public Parameters() {}
	
	public Parameters(int netSize, int newPkg, int bufferOut, int ttl, String routingType)
	{
		this.netSize = netSize;
		this.newPkg = newPkg;
		this.bufferOut = bufferOut;
		this.ttl = ttl;
		this.routing = routingType;
		System.out.println(routingType);
	}

	public int getNetSize() {
		return netSize;
	}

	public void setNetSize(int netSize) {
		this.netSize = netSize;
	}

	public int getNewPkt() {
		return newPkg;
	}

	public void setNewPkg(int newPkg) {
		this.newPkg = newPkg;
	}

	public int getBufferOut() {
		return bufferOut;
	}

	public void setBufferOut(int bufferOut) {
		this.bufferOut = bufferOut;
	}

	public int getTtl() {
		return ttl;
	}

	public void setTtl(int ttl) {
		this.ttl = ttl;
	}

	public String getRoutingType() {
		return routing;
	}

	public void setRoutingType(String routing) {
		this.routing = routing;
	}
	
	
	
}

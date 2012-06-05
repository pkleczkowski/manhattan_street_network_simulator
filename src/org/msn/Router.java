package org.msn;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import org.uncommons.maths.random.GaussianGenerator;

public class Router {
	private Network parentNetwork;
	public FCin fcIn;
	public FCout fcOut;
	public D d1;
	public D d2;
	public Buffer b1;
	public Buffer b2;
	private Address address;
	private RoutingTable routingTable;
	private String routingType;
	private int netSize;
	
	//private int newPktDelay;
	//private int newPktCounter;
	
	public Router(int i, int j, Parameters parameters, Network network)
	{
		netSize = parameters.getNetSize();
		int bufferSize = parameters.getBufferOut();
		//newPktDelay = parameters.getNewPkt();
		//newPktCounter = 1;
		
		parentNetwork = network;
		address = new Address(i,j);
		
		fcIn = new FCin(netSize, address, parameters.getNewPkt());
		fcOut = new FCout();
		d1 = new D();
		d2 = new D();
		
		createOutputsB(i,j,netSize,bufferSize);
		//System.out.println("Ja: "+this.address.getAddress());
		//System.out.println("b1: " + b1.getNextHopAddress().getAddress());
		//System.out.println("b2: " + b2.getNextHopAddress().getAddress());
		this.routingType = parameters.getRoutingType();
		if (routingType.equals("Random routing"))
				routingTable = new RandomRoutingTable(this.address);
		else if (routingType.equals("Intelligent random routing"))
			routingTable = new IntelligentRandomRoutingTable(this);
		else if (routingType.equals("Dead reckoning"))
			routingTable = new DeadReckoningRoutingTable(this);
		else if (routingType.equals("Deflection routing"))
			routingTable = new DeflectionRoutingTable(this);
		else if (routingType.equals("Shortest path"))
			routingTable = new ShortestPathRoutingTable(this);
	}
	

	public void generatePackets(int n, int ttl) {
		this.fcIn.generateRandomlyAddressedPacket(n, ttl, routingType);
	}
	
	public void forwardPackets() {
		//this method receive and forward to the proper output interface, packets (one from each) from all router input interfaces
		//trace
		//System.out.print("Router: "+this.address.getAddress());

		//System.out.println("Router: "+this.address.getAddress()+" przed forwardem sprawdzam bufory: b1 - "+this.b1.fifo.size() + " b2 - "+this.b2.fifo.size());
		Packet tmp = d1.getPacket();
		if (tmp!=null) 
			forwardPacket(tmp);
		tmp = d2.getPacket();
		if (tmp!=null) 
			forwardPacket(tmp);
		tmp = fcIn.getPacket();
		if (tmp!=null) 
			forwardPacket(tmp);
		//System.out.println("Router: "+this.address.getAddress()+" po forward sprawdzam bufory: b1 - "+this.b1.fifo.size() + " b2 - "+this.b2.fifo.size());
		if (this.b1.fifo.size()>0 || this.b2.fifo.size()>0)
			System.out.println("Router: "+this.address.getAddress()+" b1 - "+this.b1.fifo.size() + " b2 - "+this.b2.fifo.size());
	}
	
	private void forwardPacket(Packet packet) {
		int interfaceID = routingTable.getOutputInterfaceId(packet);
		System.out.println("Ja: "+this.address.getAddress() + " routuje pakiet z adresem: "+packet.getDestinationAddress().getAddress() + " to: "+interfaceID+" (1-b1;2-b2;3-FCout)");
		switch (interfaceID) {
			case 1 :
				b1.offer(packet);
				break;
			case 2 :
				b2.offer(packet);
				break;
			case 3 :
				fcOut.offer(packet);
				parentNetwork.updateReceived(packet.getHopsCount(),packet.getDelay());
				break;
			case 0 :
				parentNetwork.updatelostPackets();
				System.out.println("KURWAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
				break;
			default :
				System.out.println("Error. No such interface");
				break;
		}
		//Address nextHopAddress = routingTable.getNextHopAddress(this.address, packet.getDestinationAddress());
	}
	
	private void createOutputsB(int i, int j, int netSize, int bufferSize) {
		if ((j % 2) == 0)
		{
		    //isEven = true
			if(i==0)
				b1 = new Buffer(this, new Address(netSize-1,j),bufferSize);	//jeœli parzysta kolumna i zerowy wiersz to B1 (wyjœcie 1) jest do najni¿szego w tej samej kolumnie
			else
				b1 = new Buffer(this, new Address(i-1,j),bufferSize);			//jesli parzysta kolumna i wiersz gdzieœ w œrodku to B1 na wy¿szy wiersz ta sama kolumna
		}
		else
		{
		    //isEven = false
			if (i==(netSize-1))
				b1 = new Buffer(this, new Address(0,j),bufferSize);
			else
				b1 = new Buffer(this, new Address(i+1,j),bufferSize);
		}
		if ((i % 2) == 0)
		{
		    //isEven = true
			if(j==(netSize-1))
				b2 = new Buffer(this, new Address(i,0),bufferSize);	//jeœli parzysta kolumna i zerowy wiersz to B1 (wyjœcie 1) jest do najni¿szego w tej samej kolumnie
			else
				b2 = new Buffer(this, new Address(i,j+1),bufferSize);			//jesli parzysta kolumna i wiersz gdzieœ w œrodku to B1 na wy¿szy wiersz ta sama kolumna
		}
		else
		{
		    //isEven = false
			if (j==0)
				b2 = new Buffer(this, new Address(i,netSize-1),bufferSize);
			else
				b2 = new Buffer(this, new Address(i,j-1),bufferSize);
		}
		
	}
	
	public void sendPackets() {
		parentNetwork.sendPacketX(b1.getNextHopAddress(),b1.getPacket());
		parentNetwork.sendPacketY(b2.getNextHopAddress(),b2.getPacket());
		
	}

	public void receivePacketX(Packet packet) {
		if (!d1.offer(packet))
			System.out.println(" in router: "+this.address.getAddress());
	}
	public void receivePacketY(Packet packet) {
		if (!d2.offer(packet))
			System.out.println(" in router: "+this.address.getAddress());
	}
	
	public void updateLost() {
		parentNetwork.updatelostPackets();
	}

	//local input interface (generate packets and input them into router forwarding table)
	public class FCin {
		private int netSize;
		private Address routerAddress;
		private LinkedList<Packet> fifo;
		private int delay;
		private int nextPacket;
		private Random randomGenerator;
		private Random randomDelay;
		private GaussianGenerator gauss;
		private boolean drawPacket;
		
		public FCin(int netSize, Address address, int delay) {
			this.netSize = netSize;
			this.routerAddress = address;
			this.fifo = new LinkedList<Packet>();
			this.delay = delay;
			this.randomGenerator = new Random();
			this.randomDelay = new Random();
			this.gauss = new GaussianGenerator(this.delay+1, 1.5, this.randomDelay); //gaussian new packet generating
			this.nextPacket=(int)(gauss.nextValue()*1);
		}
		
		private Packet getPacket() {
			return this.fifo.poll();
			
		}
		
		public boolean isEmpty() {
			if (drawPacket) {
				drawPacket=false;
				return false;
			}
			else
				return true;
		}

		private void generateRandomlyAddressedPacket(int netSize, int ttl, String routType) {
			if (nextPacket-- ==0) {
				nextPacket=(int)(gauss.nextValue()*1);
				//System.out.println("Delay new: "+nextPacket);
			
				//create new packet 
				//create destinationAddress of packet (in random way)
				Address randomAddress;
				do {
					int randomX = randomGenerator.nextInt(netSize);
					int randomY = randomGenerator.nextInt(netSize);
					randomAddress = new Address(randomX,randomY);
				}
				while (randomAddress.getX()==this.routerAddress.getX() && randomAddress.getY()==this.routerAddress.getY());
				System.out.println("Adres nowego pakietu: "+randomAddress.getAddress());
				if (routType.equals("Dead reckoning"))
					fifo.offer(new Packet(randomAddress, this.routerAddress, ttl, netSize, true));
				else
					fifo.offer(new Packet(randomAddress, this.routerAddress, ttl, netSize, false));
				this.drawPacket=true;
				
				//trace
				System.out.println("Generuje pakiet w routerze: "+this.routerAddress.getX()+ " "+this.routerAddress.getY());
			}
		}
		
		public void setDelay(int delay) {
			this.delay=delay;
		}
	}
	
	
	//local output interface (receive packet)
	public class FCout {
		private LinkedList<Packet> fifo;
		
		public FCout() {
			fifo = new LinkedList<Packet>();
		}
		
		private void offer(Packet packet) {
			fifo.offer(packet);
			//System.out.println(" received packet: " + packet.getDestinationAddress().getAddress() + " source: " + packet.getSourceAddress().getAddress());
		}
		
		public boolean isEmpty() {
			if (!fifo.isEmpty()) {
				fifo.poll();
				return false;
			}
			else
				return true;
		}
	}
	
	public class D {
		private LinkedList<Packet> fifo;
		
		private D() {
			fifo = new LinkedList<Packet>();
		}
		
		private Packet getPacket() {
			return fifo.poll(); 
		}

		public boolean offer(Packet packet) {
			if (!packet.update())
			{
				//it means that ttl is 0 - packet to be destroyed/lost
				parentNetwork.updatelostPackets();
				return false;
			}
			else 
			{
				fifo.offer(packet);
				return true;
			}
		}
		
		public boolean isEmpty() {
			return fifo.isEmpty();
		}
	}

	public Address getAddress() {
		return address;
	}

	public int getNetSize() {
		return netSize;
	}


	public void setNetSize(int netSize) {
		this.netSize = netSize;
	}
	
}
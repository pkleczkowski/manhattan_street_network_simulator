package org.msn;

public class Routing {
	//public static final String[] routings
	//public static final String DETERMINISTIC_ROUTING_STRATEGY_1 = "Deterministic routing strategy 1";
	//public static final String DETERMINISTIC_ROUTING_STRATEGY_2 = "Deterministic routing strategy 2";
	//public static final String DETERMINISTIC_ROUTING_STRATEGY_3 = "Deterministic routing strategy 3";
	//public static final String RANDOM_ROUTING = "Random routing";
	//public static final String DEAD_RECKONING = "Dead reckoning";
	//public static final String SHORTES_PATH = "Shortest path";
	//public static final String WAVELENGTH = "Wavelength";
	
	private static final String[] ROUTINGS =  
		{
		
		"Random routing",
		"Intelligent random routing",
		"Deflection routing",
		"Dead reckoning",
		"Shortest path"
		};
	
	public Routing()
	{
		
	}
	
	public Routing(String routing_type) {
		// TODO Auto-generated constructor stub
	}

	public static String[] getRoutings() {
		return ROUTINGS;
	}

}

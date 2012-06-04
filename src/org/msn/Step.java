package org.msn;

import java.util.HashMap;
import java.util.Map;


public enum Step {

	RIGHT(-2), LEFT(2), TOP(1), DOWN(-1), NO_STEP(0);
		
	
	private static final Map<Integer, Step> intMap = new HashMap<Integer, Step>();

	static {
	        for (Step status : Step.values())
	            intMap.put(status.getCode(), status);
	        }

	    private final int code;

	    private Step(Integer init_code)
	    {
	        this.code = init_code;
	    }

	    public int getCode()
	    {
	        return this.code;
	    }

	    public static Step get(int code) {
	        return intMap.get(code);
	    }
	
	
}


package com.applyjobsmartly.ajs.util;

public class Snippet {
	public AtsType detectAts(String careerUrl) {
	
	    if (careerUrl.contains("greenhouse.io")) {
	        return AtsType.GREENHOUSE;
	    }
	    if (careerUrl.contains("lever.co")) {
	        return AtsType.LEVER;
	    }
	    if (careerUrl.contains("workday")) {
	        return AtsType.WORKDAY;
	    }
	    return AtsType.CUSTOM;
	}
	
}


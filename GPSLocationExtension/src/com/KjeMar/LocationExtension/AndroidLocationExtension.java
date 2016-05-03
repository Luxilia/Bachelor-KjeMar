package com.KjeMar.LocationExtension;


import com.adobe.fre.FREContext;
import com.adobe.fre.FREExtension;

public class AndroidLocationExtension implements FREExtension {
	
	AndroidLocationExtensionContext context;
	AndroidGPSLocationListener listener;
	
	@Override
	public FREContext createContext(String arg0) {
		
		this.context = new AndroidLocationExtensionContext();
		return this.context;
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void initialize() {
		

	}

}

package com.KjeMar.AndroidGPSExtension;


import com.adobe.fre.FREContext;
import com.adobe.fre.FREExtension;

public class AndroidGPSLocationExtension implements FREExtension {
	
	AndroidGPSLocationContext context;
	AndroidGPSLocationListener listener;
	
	@Override
	public FREContext createContext(String arg0) {
		this.context = new AndroidGPSLocationContext();
		this.listener = new AndroidGPSLocationListener(this.context);
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

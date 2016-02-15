package com.KjeMar.AndroidGPSExtension;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;

public class AndroidGPSLocationInitFunction implements FREFunction {

	@Override
	public FREObject call(FREContext context, FREObject[] args)
	{
	    AndroidGPSLocationContext adec=(AndroidGPSLocationContext)context;
	    android.app.Activity activity=adec.getActivity();
	    adec.activity=activity;
	    return null;
	}

}

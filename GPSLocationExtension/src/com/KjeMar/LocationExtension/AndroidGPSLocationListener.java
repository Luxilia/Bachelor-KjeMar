package com.KjeMar.LocationExtension;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

public class AndroidGPSLocationListener {

	LocationManager locationManager;
	AndroidGPSLocationContext context;
	Context appContext;
	
	AndroidGPSLocationListener(AndroidGPSLocationContext context){
		this.context = context;
		context.dispatchStatusEventAsync("GPS", "700,900");
		this.appContext = context.getActivity().getApplicationContext();
		locationManager = (LocationManager)
                appContext.getSystemService(Context.LOCATION_SERVICE);
        LocationListener locationListener = new GPSLocationListener();
        locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER, 5000, 10, locationListener);
	}
	
	private class GPSLocationListener implements LocationListener {

        @Override
        public void onLocationChanged(Location loc) {
            double lat = loc.getLatitude();
            double lng = loc.getLongitude();
            String location = lat + "," + lng;
            context.dispatchStatusEventAsync("GPS", location);
        }

		@Override
		public void onProviderDisabled(String arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onProviderEnabled(String arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
			// TODO Auto-generated method stub
			
		}
	}

	
}

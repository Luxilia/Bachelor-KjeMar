package com.KjeMar.LocationExtension;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.location.Geocoder;
import android.location.Location;
import android.location.Address;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

public class AndroidGPSLocationListener {

	LocationManager locationManager;
	AndroidGPSLocationContext context;
	Context appContext;
	String locationInfo;
	
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
//            double lat = loc.getLatitude();
//            double lng = loc.getLongitude();
//            String location = lat + "," + lng;
//            context.dispatchStatusEventAsync("GPS", location);
            Geocoder gcd = new Geocoder(appContext, Locale.getDefault());
            List<Address> addresses;
            try {
                addresses = gcd.getFromLocation(loc.getLatitude(),
                        loc.getLongitude(), 1);
                if (addresses.size() > 0){
                	locationInfo = "Locality: " + addresses.get(0).getLocality() + " Feature: " + addresses.get(0).getFeatureName() + "Thoroughfare: " + addresses.get(0).getThoroughfare();
                	context.dispatchStatusEventAsync("GPS", locationInfo);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
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

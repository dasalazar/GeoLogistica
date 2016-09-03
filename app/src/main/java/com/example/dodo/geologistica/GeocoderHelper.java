package com.example.dodo.geologistica;import android.content.Context;import android.location.Address;import android.location.Geocoder;import com.google.android.gms.maps.model.LatLng;import java.io.IOException;import java.util.List;import java.util.Locale;public class GeocoderHelper {    public LatLng getLatLng(Context context, String locationName){        if(!Geocoder.isPresent()){        }        Geocoder geoCoder = new Geocoder(context, Locale.getDefault());        try {            locationName += ", Porto Alegre - RS";            List<Address> addresses = geoCoder.getFromLocationName(locationName, 1);            int tentatives = 0;            while (addresses.size()==0 && (tentatives < 10)) {                addresses = geoCoder.getFromLocationName(locationName, 1);                tentatives ++;            }            if(addresses.size() > 0){                return new LatLng(addresses.get(0).getLatitude(), addresses.get(0).getLongitude());            }else{                //use http api            }        } catch (IOException e) {            //Log.d(Geocoding.class.getName(), "not possible finding LatLng for Address : " + locationName);        }        return null;    }}
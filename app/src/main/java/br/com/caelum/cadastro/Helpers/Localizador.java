package br.com.caelum.cadastro.Helpers;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by android7087 on 25/11/17.
 */

public class Localizador {

    private Geocoder geo;
    private LatLng latLng;
    public Localizador(Context ctx){
        geo = new Geocoder(ctx, Locale.getDefault());
    }

    public LatLng getCoordenada(String endereco){


        if(!endereco.isEmpty()){
            try{
                List<Address> listaEndereco;
                listaEndereco = geo.getFromLocationName(endereco,1);
                if(!listaEndereco.isEmpty()){
                    Address address = listaEndereco.get(0);
                    latLng = new LatLng(address.getLatitude(),address.getLongitude());
                }else{
                    latLng = new LatLng(-23.5784097,-46.6396839);
                }
            }catch (IOException e){
                Log.e("get Coordenada",e.getMessage());
                latLng = new LatLng(-23.5784097,-46.6396839);
            }
        }else{
            latLng = new LatLng(-23.5784097,-46.6396839);
        }
        return latLng;
    }
}

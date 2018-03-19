package es.uplace.uplace.fragments

import kotlinx.android.synthetic.main.fragment_map.*

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng

import es.uplace.uplace.R

class MapSearchFragment : Fragment(), OnMapReadyCallback {

    internal lateinit var mapView: MapView
    internal lateinit var map: GoogleMap

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater!!.inflate(R.layout.fragment_map, container, false)

        mapView = v.findViewById(R.id.map)
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)

        return v
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        // Gets to GoogleMap from the MapView and does initialization stuff
        map.uiSettings.isMyLocationButtonEnabled = false

        // Needs to call MapsInitializer before doing any CameraUpdateFactory calls
        MapsInitializer.initialize(this.activity)

        // Updates the location and zoom of the MapView
        //        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(42.0, 2.0), 10);
        //        map.animateCamera(cameraUpdate);
        val location = LatLng(40.416775, -3.0)
        map.moveCamera(CameraUpdateFactory.newLatLng(location))
    }

    override fun onResume() {
        mapView.onResume()
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }
}// Required empty public constructor

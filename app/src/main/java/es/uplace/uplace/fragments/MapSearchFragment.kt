package es.uplace.uplace.fragments

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
import com.google.android.gms.maps.model.MarkerOptions

import es.uplace.uplace.R
import es.uplace.uplace.domain.Property

class MapSearchFragment : Fragment(), OnMapReadyCallback {

    private var properties: ArrayList<Property> = arrayListOf()

    private lateinit var mapView: MapView
    private lateinit var map: GoogleMap

    companion object {
        fun instance(properties: ArrayList<Property>): MapSearchFragment {
            val fragment = MapSearchFragment()
            val args = Bundle()
            args.putParcelableArrayList("properties", properties)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_map, container, false)

        mapView = v.findViewById(R.id.map)
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)

        return v
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

//        Gets to GoogleMap from the MapView and does initialization stuff
        map.uiSettings.isMyLocationButtonEnabled = false

//        Needs to call MapsInitializer before doing any CameraUpdateFactory calls
        MapsInitializer.initialize(this.activity)

//        Updates the location and zoom of the MapView
//            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(42.0, 2.0), 10);
//            map.animateCamera(cameraUpdate);
        val location = LatLng(40.416775, -3.0)
        map.moveCamera(CameraUpdateFactory.newLatLng(location))
        updateProperties(this.properties)
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

    fun updateProperties(properties: ArrayList<Property>) {
        if (!::map.isInitialized) return
        for (p in properties) {
            val location = p.location
            val position = LatLng(location.latitude, location.longitude)
            map.addMarker(MarkerOptions().position(position).title(p.title))
        }
    }
}

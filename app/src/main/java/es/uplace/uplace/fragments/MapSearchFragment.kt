package es.uplace.uplace.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

import es.uplace.uplace.R
import es.uplace.uplace.domain.Property
import kotlinx.android.synthetic.main.fragment_map.view.*

class MapSearchFragment : Fragment()/*, OnMapReadyCallback*/ {

    private var properties: ArrayList<Property> = arrayListOf()

    private lateinit var mapView: MapView
    lateinit var map: GoogleMap
    var initialized: Boolean = false

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

        mapView = v.map

        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync {
            this.map = it

            this.map.uiSettings.isMyLocationButtonEnabled = false
            MapsInitializer.initialize(activity?.applicationContext)

            val location = LatLng(40.416775, -3.0)
            map.moveCamera(CameraUpdateFactory.newLatLng(location))
            initialized = true
            Log.d("ncs", "OnMapReady end")
//            Toast.makeText(activity?.applicationContext, "OnMapReady end", Toast.LENGTH_SHORT).show()

            if (!::map.isInitialized) Log.d("ncs", "Not initialized")
            else Log.d("ncs", "initialized")

//            updateProperties(this.properties)
            this.properties.forEach {
                Log.d("ncs", it.toString())
                val pLocation = it.location
                val position = LatLng(pLocation.latitude, pLocation.longitude)
                this.map.addMarker(MarkerOptions().position(position).title(it.title))
            }
        }
        Log.d("ncs", "Map is not initialized and properties is ${if (this.properties.isEmpty()) "empty" else "not empty"}")

        return v
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

    fun updateProperties(properties: ArrayList<Property>, newSearch : Boolean = false) {
        this.properties = properties
        Log.d("ncs", "Properties is ${if (this.properties.isEmpty()) "empty" else "not empty"}")

        if (newSearch) {
            this.map.clear()
            this.properties.forEach {
                Log.d("ncs", it.toString())
                val pLocation = it.location
                val position = LatLng(pLocation.latitude, pLocation.longitude)
                this.map.addMarker(MarkerOptions().position(position).title(it.title))
            }
        }
    }


}

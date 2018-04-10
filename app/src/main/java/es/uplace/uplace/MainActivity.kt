package es.uplace.uplace

import kotlinx.android.synthetic.main.activity_main.*

import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setClickListeners()

//        R.array.dwelling_types

    }

    private fun setClickListeners() {
        btnBuy.setOnClickListener {
            changeSelected(btnBuy)
        }
        btnRent.setOnClickListener {
            changeSelected(btnRent)
        }
        btnTransfer.setOnClickListener {
            changeSelected(btnTransfer)
        }
        btnSearch.setOnClickListener {
            startActivity(Intent(this, SearchActivity::class.java))
        }
    }

    fun changeSelected(btn: Button) {
        btn.isSelected = !btn.isSelected
        if (btn.isSelected) {
            btn.setBackgroundColor(resources.getColor(R.color.primaryDark))
            btn.setTextColor(Color.WHITE)
        } else {
            btn.setBackgroundColor(resources.getColor(R.color.primary))
            btn.setTextColor(resources.getColor(R.color.textPrimary))
        }
    }
}

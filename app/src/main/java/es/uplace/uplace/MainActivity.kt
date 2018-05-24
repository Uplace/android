package es.uplace.uplace

import kotlinx.android.synthetic.main.activity_main.*

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setClickListeners()

//        R.array.dwelling_types

    }

    private fun setClickListeners() {
//        btnBuy.setOnClickListener {
//            changeSelected(btnBuy)
//        }
//        btnRent.setOnClickListener {
//            changeSelected(btnRent)
//        }
//        btnTransfer.setOnClickListener {
//            changeSelected(btnTransfer)
//        }
        search_button.setOnClickListener {
            startActivity(Intent(this, SearchActivity::class.java))
        }
    }

//    fun changeSelected(btn: Button) {
//        btn.isSelected = !btn.isSelected
//        if (btn.isSelected) {
//            btn.background = resources.getDrawable(R.drawable.transaction_button_pressed, theme)
//        } else {
//            btn.background = resources.getDrawable(R.drawable.transaction_button, theme)
//        }
//    }
}

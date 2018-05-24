package es.uplace.uplace

import kotlinx.android.synthetic.main.activity_main.*

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        search_button.setOnClickListener {
            if (!isCityValid(city_edit_text.text)) {
                city_text_input.error = getString(R.string.error_city)
            } else {
                city_text_input.error = null

                val intent = Intent(this, SearchActivity::class.java)
                intent.putExtra("city.equals", city_edit_text.text)
                startActivity(intent)
            }
        }

    }

    private fun isCityValid(text: Editable?): Boolean {
        return text != null && text.length >= 4
    }
}

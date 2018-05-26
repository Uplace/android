package es.uplace.uplace

import kotlinx.android.synthetic.main.activity_main.*

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.ArrayAdapter
import android.widget.Toast
import es.uplace.uplace.domain.Filters
import es.uplace.uplace.retrofit.FiltersService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Constants.categories.add("Category")

        findCategories()

        main_search_button.setOnClickListener {
            if (!isCityValid(main_city_edit_text.text)) {
                main_city_text_input.error = getString(R.string.error_city)
            } else {
                main_city_text_input.error = null

                val intent = Intent(this, SearchActivity::class.java)
                intent.putExtra("city", main_city_edit_text.text.toString())
                if (main_category_spinner.selectedItemPosition != 0)
                    intent.putExtra("category", main_category_spinner.selectedItem.toString())

                startActivity(intent)
            }
        }
    }

    private fun setCategories() {
        val adapter: ArrayAdapter<CharSequence> = ArrayAdapter(
                this@MainActivity,
                android.R.layout.simple_spinner_dropdown_item,
                Constants.categories as List<CharSequence>?)

        main_category_spinner.adapter = adapter
    }

    private fun isCityValid(text: Editable?): Boolean {
        return text != null && text.length >= 4
    }

    private fun findCategories() {
        val filtersService = FiltersService.create()
        val call = filtersService.findFilters()

        call.enqueue(object : Callback<Filters> {
            override fun onFailure(call: Call<Filters>?, t: Throwable?) {
                Toast.makeText(this@MainActivity, "Error en Callback", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<Filters>?, response: Response<Filters>?) {
                if (response != null) {
                    val filters: Filters? = response.body()
                    filters?.typeProperties?.let { Constants.categories.addAll(it) }
                    setCategories()
                } else {
                    main_category_spinner.isEnabled = false
                    main_category_spinner.isClickable = false
                }
            }
        })
    }
}

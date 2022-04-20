package com.hssoft.counries.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hssoft.counries.R
import com.hssoft.counries.ui.countries.CountriesFragment
import com.hssoft.counries.ui.countries.CountryDetailsFragment

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container_countries, CountriesFragment())
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container_country_details, CountryDetailsFragment())
        }
    }
}
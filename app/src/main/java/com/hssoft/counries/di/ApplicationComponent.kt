package com.hssoft.counries.di

import com.hssoft.counries.AndroidApplication
import com.hssoft.counries.di.module.ApplicationModule
import com.hssoft.counries.di.module.NetworkModule
import com.hssoft.counries.ui.countries.details.CountryDetailsFragment
import com.hssoft.counries.ui.countries.list.CountriesFragment
import com.hssoft.counries.ui.main.MainActivity
import dagger.Component

@Component(
    modules = [
        ApplicationModule::class,
        NetworkModule::class
    ]
)
interface ApplicationComponent {
    fun inject(application: AndroidApplication)
    fun inject(mainActivity: MainActivity)
    fun inject(countriesFragment: CountriesFragment)
    fun inject(countryDetailsFragment: CountryDetailsFragment)
}
package com.hssoft.counries.ui.countries.list.items

import android.view.View
import com.hssoft.counries.R
import com.hssoft.counries.data.model.Country
import com.hssoft.counries.databinding.ItemCountryBinding
import com.xwray.groupie.viewbinding.BindableItem

class CountryItem(
    private val model: Country,
    private val onCountryClick: (Country) -> Unit
) : BindableItem<ItemCountryBinding>() {

    override fun bind(viewBinding: ItemCountryBinding, position: Int) {
        viewBinding.country = model
        viewBinding.root.setOnClickListener {
            onCountryClick.invoke(model)
        }
    }

    override fun getLayout() = R.layout.item_country

    override fun initializeViewBinding(view: View) = ItemCountryBinding.bind(view)
}
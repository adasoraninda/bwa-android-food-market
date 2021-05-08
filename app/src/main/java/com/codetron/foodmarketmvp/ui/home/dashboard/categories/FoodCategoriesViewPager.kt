package com.codetron.foodmarketmvp.ui.home.dashboard.categories

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class FoodCategoriesViewPager(fragment: Fragment, private val contents: List<Fragment>) :
    FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return contents.size
    }

    override fun createFragment(position: Int): Fragment {
        return contents[position]
    }

}
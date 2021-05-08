package com.codetron.foodmarketmvp.ui.home.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.codetron.foodmarketmvp.databinding.FragmentDashboardBinding
import com.codetron.foodmarketmvp.ui.home.dashboard.categories.FoodCategoriesViewPager
import com.codetron.foodmarketmvp.ui.home.dashboard.categories.FoodCategory
import com.codetron.foodmarketmvp.ui.home.dashboard.categories.FoodCategoryType
import com.codetron.foodmarketmvp.util.dummy.FoodDataDummy
import com.google.android.material.tabs.TabLayoutMediator

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding

    private val bindingHeader by lazy { binding?.lytContentDashboardHeader }
    private val bindingBody by lazy { binding?.lytContentDashboardBody }

    private val foodsAdapter: FoodListAdapter by lazy {
        FoodListAdapter(ListType.HORIZONTAL)
    }

    private val foodCategoriesViewPager: FoodCategoriesViewPager by lazy {
        FoodCategoriesViewPager(this, FoodCategory.getFragments())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListFoods()
        initTabLayout()
    }

    private fun initListFoods() {
        bindingHeader?.lytContentListFoods?.lstFoods?.adapter = foodsAdapter.apply {
            setItemFoods(FoodDataDummy.getFoods())
        }
    }

    private fun initTabLayout() {
        bindingBody
            ?.lytContentDashboardCategories
            ?.vpgFoodCategories
            ?.adapter = foodCategoriesViewPager

        val viewPager = bindingBody
            ?.lytContentDashboardCategories
            ?.vpgFoodCategories

        val tabLayout = bindingBody
            ?.lytContentDashboardCategories
            ?.tblFoodCategories

        if (tabLayout != null && viewPager != null) {
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = getString(FoodCategoryType.values()[position].title)
            }.attach()
        }
    }

}
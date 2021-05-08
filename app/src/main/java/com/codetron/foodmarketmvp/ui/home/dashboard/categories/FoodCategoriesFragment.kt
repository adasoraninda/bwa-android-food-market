package com.codetron.foodmarketmvp.ui.home.dashboard.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.codetron.foodmarketmvp.R
import com.codetron.foodmarketmvp.databinding.FragmentFoodCategoriesBinding

class FoodCategoriesFragment : Fragment() {

    private var _binding: FragmentFoodCategoriesBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFoodCategoriesBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    companion object {
        private const val CATEGORY_KEY = "CATEGORY_KEY"

        fun getInstance(foodCategory: FoodCategoryType): FoodCategoriesFragment {
            return FoodCategoriesFragment().apply {
                bundleOf(Pair(CATEGORY_KEY, foodCategory))
            }
        }
    }

}

object FoodCategory {
    fun getFragments(): List<Fragment> {
        return FoodCategoryType.values().map { category ->
            FoodCategoriesFragment.getInstance(category)
        }
    }
}

enum class FoodCategoryType(@StringRes val title: Int) {
    NEW_TASTE(R.string.new_taste),
    POPULAR(R.string.popular),
    RECOMMENDED(R.string.recommended),
}
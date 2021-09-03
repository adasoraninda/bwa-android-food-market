package com.codetron.foodmarketmvp.ui.home.dashboard.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.codetron.foodmarketmvp.R
import com.codetron.foodmarketmvp.databinding.FragmentFoodCategoriesBinding
import com.codetron.foodmarketmvp.model.domain.food.FoodItem
import com.codetron.foodmarketmvp.ui.home.dashboard.adapter.FoodListAdapter
import com.codetron.foodmarketmvp.ui.home.dashboard.adapter.ListType
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FoodCategoriesFragment : Fragment(), (Int?) -> Unit, FoodCategoriesContract.View {

    private var _binding: FragmentFoodCategoriesBinding? = null
    private val binding get() = _binding

    private val foodsAdapter: FoodListAdapter by lazy {
        FoodListAdapter(ListType.VERTICAL, this)
    }

    private val args by lazy { arguments?.get(CATEGORY_KEY) as FoodCategoryType }

    @Inject
    lateinit var presenterFactory: FoodCategoriesPresenter.Factory

    private val presenter by lazy { presenterFactory.create(args.params) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFoodCategoriesBinding.inflate(inflater, container, false)

        if (savedInstanceState == null) {
            presenter.subscribe()
        }

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListFoods()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        presenter.unSubscribe()
    }

    private fun initListFoods() {
        binding?.lstFoodsCategories?.adapter = foodsAdapter
    }

    companion object {
        private const val CATEGORY_KEY = "CATEGORY_KEY"

        fun getInstance(foodCategory: FoodCategoryType): FoodCategoriesFragment {
            return FoodCategoriesFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(CATEGORY_KEY, foodCategory)
                }
            }
        }
    }

    override fun invoke(id: Int?) {
        if (id != null) {
            Toast.makeText(requireContext(), "$id", Toast.LENGTH_SHORT).show()
//            val dashboardDirections = DashboardFragmentDirections.dashboardToDetailFood(id)
//            findNavController().navigate(dashboardDirections)
        }
    }

    override fun showLoading() {
        binding?.pbrFoodCategories?.visibility = View.VISIBLE
    }

    override fun dismissLoading() {
        binding?.pbrFoodCategories?.visibility = View.GONE
    }

    override fun onGetFoodCategoriesSuccess(foods: List<FoodItem>) {
        foodsAdapter.setItemFoods(foods)
    }

    override fun onGetFoodCategoriesFailed(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}

object FoodCategory {
    fun getFragments(): List<Fragment> {
        return FoodCategoryType.values().map { category ->
            FoodCategoriesFragment.getInstance(category)
        }
    }
}

enum class FoodCategoryType(@StringRes val title: Int, val params: String) {
    NEW_TASTE(R.string.new_taste, "new"),
    POPULAR(R.string.popular, "popular"),
    RECOMMENDED(R.string.recommended, "recommended"),
}
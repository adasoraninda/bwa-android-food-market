package com.codetron.foodmarketmvp.ui.home.dashboard

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.codetron.foodmarketmvp.FoodMarketApplication
import com.codetron.foodmarketmvp.customview.SnackBarError
import com.codetron.foodmarketmvp.databinding.FragmentDashboardBinding
import com.codetron.foodmarketmvp.di.module.ui.DashboardModule
import com.codetron.foodmarketmvp.model.domain.food.FoodItem
import com.codetron.foodmarketmvp.model.domain.user.User
import com.codetron.foodmarketmvp.ui.home.adapter.SectionViewPager
import com.codetron.foodmarketmvp.ui.home.dashboard.adapter.FoodListAdapter
import com.codetron.foodmarketmvp.ui.home.dashboard.adapter.ListType
import com.codetron.foodmarketmvp.ui.home.dashboard.categories.FoodCategory
import com.codetron.foodmarketmvp.ui.home.dashboard.categories.FoodCategoryType
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
class DashboardFragment : Fragment(), (Int?) -> Unit, DashboardContract.View {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding

    private val bindingHeader by lazy { binding?.lytContentDashboardHeader }
    private var snackBarError: SnackBarError? = null

    private val foodsAdapter: FoodListAdapter by lazy {
        FoodListAdapter(ListType.HORIZONTAL, this)
    }

    private val foodCategoriesViewPager: SectionViewPager by lazy {
        SectionViewPager(this, FoodCategory.getFragments())
    }

    @Inject
    lateinit var presenter: DashboardContract.Presenter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as FoodMarketApplication)
            .appComponent
            .newUiComponentBuilder()
            .dashboardModule(DashboardModule(this))
            .build()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        snackBarError = container?.let { SnackBarError(it) }
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)

        if (savedInstanceState == null) {
            presenter.subscribe()
        }

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListFoods()
        initTabLayout()
    }

    private fun initListFoods() {
        bindingHeader?.lytContentListFoods?.lstFoods?.adapter = foodsAdapter
    }

    private fun initTabLayout() {
        binding?.vpgSection?.adapter = foodCategoriesViewPager

        val viewPager = binding?.vpgSection
        val tabLayout = binding?.tblSection

        if (tabLayout != null && viewPager != null) {
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = getString(FoodCategoryType.values()[position].title)
            }.attach()
        }
    }

    override fun invoke(id: Int?) {
        if (id != null) {
            val dashboardDirections = DashboardFragmentDirections.dashboardToDetailFood(id)
            findNavController().navigate(dashboardDirections)
        }
    }

    override fun showLoading() {
        bindingHeader?.lytContentListFoods?.pbrHorizontalListFood?.visibility = View.VISIBLE
    }

    override fun dismissLoading() {
        bindingHeader?.lytContentListFoods?.pbrHorizontalListFood?.visibility = View.GONE
    }

    override fun onGetFoodSuccess(foods: List<FoodItem>) {
        foodsAdapter.setItemFoods(foods)
    }

    override fun onGetUserSuccess(user: User) {
        binding?.lytImagePhoto?.root?.visibility = View.GONE
    }

    override fun onGetDataFailed(message: String) {
        snackBarError?.setMessage(message)
        snackBarError?.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        presenter.unSubscribe()
        snackBarError?.dismiss()
    }
}
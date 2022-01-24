package com.codetron.foodmarketmvp.ui.home.profile

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.codetron.foodmarketmvp.FoodMarketApplication
import com.codetron.foodmarketmvp.databinding.FragmentProfileBinding
import com.codetron.foodmarketmvp.di.module.ui.fragment.FragmentModule
import com.codetron.foodmarketmvp.model.domain.user.User
import com.codetron.foodmarketmvp.ui.home.adapter.SectionViewPager
import com.codetron.foodmarketmvp.ui.home.profile.menu.ProfileMenuType
import com.codetron.foodmarketmvp.ui.home.profile.menu.ProfileSection
import com.codetron.foodmarketmvp.util.setImageResource
import com.google.android.material.tabs.TabLayoutMediator

import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
class ProfileFragment : Fragment(), ProfileContract.View {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding

    @Inject
    lateinit var presenter: ProfileContract.Presenter

    private val sectionViewpagerAdapter: SectionViewPager by lazy {
        SectionViewPager(this, ProfileSection.getFragments())
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as FoodMarketApplication)
            .appComponent
            .newFragmentComponentBuilder()
            .fragmentModule(FragmentModule(this))
            .build()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        if (savedInstanceState == null) {
            presenter.subscribe()
        }

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewPager()
        initTabLayout()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        presenter.unSubscribe()
    }

    override fun initState() {
        binding?.lytProfileHeader?.imgPhotoProfile?.visibility = View.GONE
    }

    override fun showLoading() {
        binding?.pbrProfile?.visibility = View.VISIBLE
    }

    override fun dismissLoading() {
        binding?.pbrProfile?.visibility = View.GONE
    }

    override fun onGetUserDataSuccess(user: User) {
        val layout = binding?.lytProfileHeader

        layout?.imgPhotoProfile?.visibility = View.VISIBLE
        layout?.lytImagePhoto?.root?.visibility = View.GONE

        layout?.txtName?.text = user.name
        layout?.txtEmail?.text = user.email
        layout?.imgPhotoProfile?.setImageResource(user.profilePhotoUrl)
    }

    override fun onGetUserDataFailed(message: String) {
        Toast.makeText(
            requireContext(),
            message,
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun initViewPager() {
        binding?.vpgSection?.adapter = sectionViewpagerAdapter
    }

    private fun initTabLayout() {
        val viewPager = binding?.vpgSection
        val tabLayout = binding?.tblSection

        if (viewPager != null && tabLayout != null) {
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = getString(ProfileMenuType.values()[position].title)
            }.attach()
        }
    }
}
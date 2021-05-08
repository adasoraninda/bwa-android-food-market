package com.codetron.foodmarketmvp.ui.home.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.codetron.foodmarketmvp.databinding.FragmentProfileBinding
import com.codetron.foodmarketmvp.ui.home.adapter.SectionViewPager
import com.codetron.foodmarketmvp.ui.home.profile.menu.ProfileMenuType
import com.codetron.foodmarketmvp.ui.home.profile.menu.ProfileSection
import com.google.android.material.tabs.TabLayoutMediator

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding

    private val sectionViewpagerAdapter: SectionViewPager by lazy {
        SectionViewPager(this, ProfileSection.getFragments())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewPager()
        initTabLayout()
    }

    private fun initViewPager() {
        binding?.lytContentProfileMenu?.vpgSection?.adapter = sectionViewpagerAdapter
    }

    private fun initTabLayout() {
        val viewPager = binding?.lytContentProfileMenu?.vpgSection
        val tabLayout = binding?.lytContentProfileMenu?.tblSection

        if (viewPager != null && tabLayout != null) {
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = getString(ProfileMenuType.values()[position].title)
            }.attach()
        }
    }

}
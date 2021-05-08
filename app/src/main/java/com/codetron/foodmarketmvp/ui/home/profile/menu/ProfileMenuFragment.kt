package com.codetron.foodmarketmvp.ui.home.profile.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.codetron.foodmarketmvp.R
import com.codetron.foodmarketmvp.data.model.profile.ProfileMenuResources
import com.codetron.foodmarketmvp.databinding.FragmentProfileMenuBinding

class ProfileMenuFragment : Fragment(), ProfileMenuClickListener {

    private var _binding: FragmentProfileMenuBinding? = null
    private val binding get() = _binding

    private val profileMenuAdapter: ProfileMenuListAdapter by lazy {
        ProfileMenuListAdapter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileMenuBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListMenu()
    }

    private fun initListMenu() {
        binding?.lstProfileMenu?.adapter = profileMenuAdapter
        val listProfileMenu = when (arguments?.getSerializable(MENU_KEY) as ProfileMenuType) {
            ProfileMenuType.ACCOUNT -> ProfileMenuResources.getAccountResources()
            ProfileMenuType.FOOD_MARKET -> ProfileMenuResources.getFoodMarketResources()
        }
        profileMenuAdapter.setMenuItems(listProfileMenu)
    }

    companion object {
        private const val MENU_KEY = "MENU_KEY"

        fun getInstance(type: ProfileMenuType): ProfileMenuFragment {
            return ProfileMenuFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(MENU_KEY, type)
                }
            }
        }
    }

    override fun setOnClickListener(id: Long) {

    }

}

object ProfileSection {
    fun getFragments(): List<Fragment> {
        return ProfileMenuType.values().map {
            ProfileMenuFragment.getInstance(it)
        }
    }
}

enum class ProfileMenuType(@StringRes val title: Int) {
    ACCOUNT(R.string.account), FOOD_MARKET(R.string.app_name)
}
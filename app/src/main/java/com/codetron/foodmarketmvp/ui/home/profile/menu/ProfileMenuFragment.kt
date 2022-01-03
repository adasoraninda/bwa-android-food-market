package com.codetron.foodmarketmvp.ui.home.profile.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.codetron.foodmarketmvp.R
import com.codetron.foodmarketmvp.databinding.FragmentProfileMenuBinding
import com.codetron.foodmarketmvp.model.domain.view.profile.ProfileMenu
import com.codetron.foodmarketmvp.ui.auth.AuthActivity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
class ProfileMenuFragment : Fragment(), ProfileMenuClickListener,
    ProfileMenuContract.View {

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

        if (savedInstanceState == null) {

        }

        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListMenu()
    }

    private fun initListMenu() {
        binding?.lstProfileMenu?.adapter = profileMenuAdapter
    }

    override fun setOnClickListener(id: Long) {

    }

    override fun navigate(id: Long) {
        Toast.makeText(
            requireContext(),
            "$id",
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onLogoutSuccess() {
        // TODO(NAVIGATE TO AUTH ACT)
        requireActivity().finishAffinity()
    }

    override fun onLogoutFailed(message: String) {
        Toast.makeText(
            requireContext(),
            message,
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onGetMenuItemSuccess(menus: List<ProfileMenu>) {
        profileMenuAdapter.setMenuItems(menus)
    }

    override fun onGetMenuItemFailed(message: String) {
        Toast.makeText(
            requireContext(),
            message,
            Toast.LENGTH_SHORT
        ).show()
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
}

@ExperimentalCoroutinesApi
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
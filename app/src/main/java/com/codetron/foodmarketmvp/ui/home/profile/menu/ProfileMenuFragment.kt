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
import com.codetron.foodmarketmvp.ui.customview.CustomAlertDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class ProfileMenuFragment : Fragment(), ProfileMenuClickListener, ProfileMenuContract.View {

    private var _binding: FragmentProfileMenuBinding? = null
    private val binding get() = _binding

    @Inject
    lateinit var presenterFactory: ProfileMenuPresenter.Factory

    private val presenter by lazy {
        presenterFactory.create(
            arguments?.getSerializable(MENU_KEY) as ProfileMenuType
        )
    }

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
            presenter.subscribe()
        }

        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        presenter.unSubscribe()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListMenu()
    }

    private fun initListMenu() {
        binding?.lstProfileMenu?.adapter = profileMenuAdapter
    }

    override fun setOnClickListener(id: Long) {
        presenter.onMenuClicked(id)
    }

    override fun navigate(id: Long) {
        Toast.makeText(
            requireContext(),
            "$id",
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onLogoutSuccess() {
        AuthActivity.navigate(requireContext())
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
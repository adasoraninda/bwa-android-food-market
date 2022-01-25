package com.codetron.foodmarketmvp.ui.auth.signin

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.codetron.foodmarketmvp.R
import com.codetron.foodmarketmvp.customview.LoadingDialog
import com.codetron.foodmarketmvp.databinding.FragmentSignInBinding
import com.codetron.foodmarketmvp.model.domain.user.User
import com.codetron.foodmarketmvp.model.validation.SignInFormValidation
import com.codetron.foodmarketmvp.ui.auth.AuthActivity
import com.codetron.foodmarketmvp.util.fragmentComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

private const val SIGN_IN_LOADING_TAG = "SIGN_IN_LOADING_TAG"

@ExperimentalCoroutinesApi
class SignInFragment : Fragment(), SignInContract.View {

    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding

    private val loadingDialog by lazy { LoadingDialog() }

    @Inject
    lateinit var presenter: SignInContract.Presenter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity() as AuthActivity).activityComponent
            .fragmentComponent(this)
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.edtEmail?.addTextChangedListener {
            binding?.edtEmail?.error = null
            binding?.edtEmail?.setBackgroundResource(R.drawable.bg_outline_primary)
            binding?.txtLabelEmail?.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.black
                )
            )
        }

        binding?.edtPassword?.addTextChangedListener {
            binding?.edtPassword?.error = null
            binding?.edtPassword?.setBackgroundResource(R.drawable.bg_outline_primary)
            binding?.txtLabelPassword?.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.black
                )
            )
        }

        binding?.btnSignIn?.setOnClickListener {
            val email = binding?.edtEmail?.text?.toString()?.trim()
            val password = binding?.edtPassword?.text?.toString()?.trim()

            presenter.submitLogin(email, password)
        }

        binding?.btnSignUp?.setOnClickListener {
            findNavController().navigate(
                R.id.sign_in_to_sign_up
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        presenter.unSubscribe()
    }

    override fun inputFormMessage(messageMap: Map<String, String?>) {
        if (messageMap[SignInFormValidation.KEY_EMAIL] != null) {
            binding?.edtEmail?.error = messageMap[SignInFormValidation.KEY_EMAIL]
            binding?.edtEmail?.setBackgroundResource(R.drawable.bg_outline_primary_error)
            binding?.txtLabelEmail?.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.red_primary
                )
            )
            binding?.edtEmail?.requestFocus()
            return
        }

        if (messageMap[SignInFormValidation.KEY_PASSWORD] != null) {
            binding?.edtPassword?.error = messageMap[SignInFormValidation.KEY_PASSWORD]
            binding?.edtPassword?.setBackgroundResource(R.drawable.bg_outline_primary_error)
            binding?.txtLabelPassword?.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.red_primary
                )
            )
            binding?.edtPassword?.requestFocus()
            return
        }
    }

    override fun showLoading() {
        loadingDialog.show(childFragmentManager, SIGN_IN_LOADING_TAG)
    }

    override fun dismissLoading() {
        loadingDialog.dismiss()
    }

    override fun onLoginSuccess(user: User) {
        binding?.edtEmail?.error = null
        binding?.edtPassword?.error = null

        findNavController().navigate(
            R.id.home_activity
        )
        requireActivity().finishAffinity()
    }

    override fun onLoginFailed(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}
package com.codetron.foodmarketmvp.ui.auth.signup.address

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.codetron.foodmarketmvp.R
import com.codetron.foodmarketmvp.databinding.FragmentSignUpAddressBinding
import com.codetron.foodmarketmvp.model.domain.user.User
import com.codetron.foodmarketmvp.model.domain.validation.SignUpAddressFormValidation
import com.codetron.foodmarketmvp.customview.LoadingDialog
import com.codetron.foodmarketmvp.customview.SnackBarError
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

private const val SIGN_UP_ADDRESS_LOADING_TAG = "SIGN_UP_ADDRESS_LOADING_TAG"

@ExperimentalCoroutinesApi
class SignUpAddressFragment : Fragment(), SignUpAddressContract.View {

    private var _binding: FragmentSignUpAddressBinding? = null
    private val binding get() = _binding

    private val navArgs: SignUpAddressFragmentArgs by navArgs()

    private var snackBarError: SnackBarError? = null

    private val loadingDialog by lazy { LoadingDialog() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        snackBarError = container?.let { SnackBarError(it) }
        _binding = FragmentSignUpAddressBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        snackBarError?.dismiss()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.edtPhone?.addTextChangedListener {
            binding?.edtPhone?.error = null
            binding?.edtPhone?.setBackgroundResource(R.drawable.bg_outline_primary)
            binding?.txtLabelPhone?.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.black
                )
            )
        }

        binding?.edtAddress?.addTextChangedListener {
            binding?.edtAddress?.error = null
            binding?.edtAddress?.setBackgroundResource(R.drawable.bg_outline_primary)
            binding?.txtLabelAddress?.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.black
                )
            )
        }

        binding?.edtHouse?.addTextChangedListener {
            binding?.edtHouse?.error = null
            binding?.edtHouse?.setBackgroundResource(R.drawable.bg_outline_primary)
            binding?.txtLabelHouse?.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.black
                )
            )
        }

        binding?.edtCity?.addTextChangedListener {
            binding?.edtCity?.error = null
            binding?.edtCity?.setBackgroundResource(R.drawable.bg_outline_primary)
            binding?.txtLabelCity?.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.black
                )
            )
        }

        binding?.btnSignUp?.setOnClickListener {
            val phoneNumber = binding?.edtPhone?.text?.toString()?.trim()
            val address = binding?.edtAddress?.text?.toString()?.trim()
            val houseNumber = binding?.edtHouse?.text?.toString()?.trim()
            val city = binding?.edtCity?.text?.toString()?.trim()
        }
    }

    override fun showLoading() {
        loadingDialog.show(childFragmentManager, SIGN_UP_ADDRESS_LOADING_TAG)
    }

    override fun dismissLoading() {
        loadingDialog.dismiss()
    }

    override fun onRegisterSuccess(user: User) {
        findNavController().navigate(
            R.id.sign_up_address_to_sign_up_success
        )
        requireActivity().finishAffinity()
    }

    override fun onRegisterFailed(message: String) {
        snackBarError?.setMessage(message)
        snackBarError?.show()
    }

    override fun inputFormMessage(messageMap: Map<String, String?>) {
        if (messageMap[SignUpAddressFormValidation.KEY_PHONE] != null) {
            binding?.edtPhone?.requestFocus()
            binding?.edtPhone?.error = messageMap[SignUpAddressFormValidation.KEY_PHONE]
            binding?.edtPhone?.setBackgroundResource(R.drawable.bg_outline_primary_error)
            binding?.txtLabelPhone?.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.red_primary
                )
            )
            return
        }

        if (messageMap[SignUpAddressFormValidation.KEY_ADDRESS] != null) {
            binding?.edtAddress?.requestFocus()
            binding?.edtAddress?.error = messageMap[SignUpAddressFormValidation.KEY_ADDRESS]
            binding?.edtAddress?.setBackgroundResource(R.drawable.bg_outline_primary_error)
            binding?.txtLabelAddress?.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.red_primary
                )
            )
            return
        }

        if (messageMap[SignUpAddressFormValidation.KEY_HOUSE] != null) {
            binding?.edtHouse?.requestFocus()
            binding?.edtHouse?.error = messageMap[SignUpAddressFormValidation.KEY_HOUSE]
            binding?.edtHouse?.setBackgroundResource(R.drawable.bg_outline_primary_error)
            binding?.txtLabelHouse?.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.red_primary
                )
            )
            return
        }

        if (messageMap[SignUpAddressFormValidation.KEY_CITY] != null) {
            binding?.edtCity?.requestFocus()
            binding?.edtCity?.error = messageMap[SignUpAddressFormValidation.KEY_CITY]
            binding?.edtCity?.setBackgroundResource(R.drawable.bg_outline_primary_error)
            binding?.txtLabelCity?.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.red_primary
                )
            )
            return
        }
    }
}
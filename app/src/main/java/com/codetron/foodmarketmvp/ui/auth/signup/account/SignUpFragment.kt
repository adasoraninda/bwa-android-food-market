package com.codetron.foodmarketmvp.ui.auth.signup.account

import android.app.Activity
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.codetron.foodmarketmvp.R
import com.codetron.foodmarketmvp.customview.LoadingDialog
import com.codetron.foodmarketmvp.databinding.FragmentSignUpBinding
import com.codetron.foodmarketmvp.model.domain.user.UserRegister
import com.codetron.foodmarketmvp.model.validation.SignUpFormValidation
import com.codetron.foodmarketmvp.ui.auth.AuthActivity
import com.codetron.foodmarketmvp.util.fragmentComponent
import com.github.dhaval2404.imagepicker.ImagePicker
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

private const val SIGN_UP_LOADING_TAG = "SIGN_UP_LOADING_TAG"

@ExperimentalCoroutinesApi
class SignUpFragment : Fragment(), SignUpContract.View {

    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding

    @Inject
    lateinit var presenter: SignUpContract.Presenter

    private val loadingDialog by lazy { LoadingDialog() }

    private var imageUri: Uri? = null

    private val startForProfileImageResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val resultCode = result.resultCode
        val data = result.data

        dismissLoading()

        if (resultCode == Activity.RESULT_OK && data != null) {
            val fileUri = data.data

            imageUri = fileUri
            setImagePhoto(fileUri)

            return@registerForActivityResult
        }

        if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(
                requireContext(),
                ImagePicker.getError(data),
                Toast.LENGTH_SHORT
            ).show()
            return@registerForActivityResult
        }
    }

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
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.edtName?.addTextChangedListener {
            binding?.edtName?.error = null
            binding?.edtName?.setBackgroundResource(R.drawable.bg_outline_primary)
            binding?.txtLabelName?.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.black
                )
            )
        }

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

        binding?.btnContinue?.setOnClickListener {
            val fullName = binding?.edtName?.text?.toString()?.trim()
            val email = binding?.edtEmail?.text?.toString()?.trim()
            val password = binding?.edtPassword?.text?.toString()?.trim()

            presenter.submitUser(fullName, email, password, imageUri)
        }

        binding?.lytImagePhoto?.imagePhoto?.setOnClickListener {
            requestImagePicker()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    private fun setImagePhoto(imageUri: Uri?) {
        binding?.lytImagePhoto?.imagePhoto?.let {
            Glide.with(this)
                .load(imageUri)
                .apply(RequestOptions.circleCropTransform())
                .into(it)
        }
    }

    private fun requestImagePicker() {
        ImagePicker.with(this)
            .cameraOnly()
            // Image size will be less than 512 KB
            .compress(512)
            // Image resolution will be less than 200 x 200
            .maxResultSize(200, 200)
            .createIntent { intent ->
                showLoading()
                startForProfileImageResult.launch(intent)
            }
    }

    override fun showLoading() {
        loadingDialog.show(childFragmentManager, SIGN_UP_LOADING_TAG)
    }

    override fun dismissLoading() {
        loadingDialog.dismiss()
    }

    override fun validInput(user: UserRegister) {
        val directions = SignUpFragmentDirections.signUpToSignUpAddress(user)
        findNavController().navigate(directions)
    }

    override fun inputFormMessage(messageMap: Map<String, String?>) {
        if (messageMap[SignUpFormValidation.KEY_NAME] != null) {
            binding?.edtName?.error = messageMap[SignUpFormValidation.KEY_NAME]
            binding?.edtName?.setBackgroundResource(R.drawable.bg_outline_primary_error)
            binding?.txtLabelName?.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.red_primary
                )
            )
            binding?.edtName?.requestFocus()
            return
        }

        if (messageMap[SignUpFormValidation.KEY_EMAIL] != null) {
            binding?.edtEmail?.error = messageMap[SignUpFormValidation.KEY_EMAIL]
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

        if (messageMap[SignUpFormValidation.KEY_PASSWORD] != null) {
            binding?.edtPassword?.error = messageMap[SignUpFormValidation.KEY_PASSWORD]
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

}
package com.codetron.foodmarketmvp.ui.auth.signup.address

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.codetron.foodmarketmvp.R
import com.codetron.foodmarketmvp.databinding.FragmentSignUpAddressBinding

class SignUpAddressFragment : Fragment() {

    private var _binding: FragmentSignUpAddressBinding? = null
    private val binding get() = _binding

    private val navArgs: SignUpAddressFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignUpAddressBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val user = navArgs.userRegister

        if (user != null) {
            Toast.makeText(requireContext(), "$user", Toast.LENGTH_SHORT).show()
        }

        binding?.btnSignUp?.setOnClickListener {
            findNavController().navigate(
                R.id.sign_up_address_to_sign_up_success
            )
        }
    }

}
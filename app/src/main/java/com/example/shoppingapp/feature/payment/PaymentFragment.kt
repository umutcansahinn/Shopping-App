package com.example.shoppingapp.feature.payment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.shoppingapp.R
import com.example.shoppingapp.core.common.viewBinding
import com.example.shoppingapp.databinding.FragmentPaymentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PaymentFragment : Fragment(R.layout.fragment_payment) {

    private val binding by viewBinding(FragmentPaymentBinding::bind)
    private val viewModel by viewModels<PaymentViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        binding.apply {
            imageButtonBack.setOnClickListener {
                findNavController().popBackStack()
            }

            buttonPay.setOnClickListener {
                val cardName = editTextCardName.text.toString()
                val cardNumber = editTextCardNumber.text.toString()
                val cardMonth = editTextCardMonth.text.toString()
                val cardYear = editTextCardYear.text.toString()
                val cardCVV = editTextCardCVV.text.toString()
                val address = editTextAddress.text.toString()

                if (
                    cardName.isNotBlank() &&
                    cardNumber.isNotBlank() &&
                    cardMonth.isNotBlank() &&
                    cardYear.isNotBlank() &&
                    cardCVV.isNotBlank() &&
                    address.isNotBlank()
                ) {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.siparisiniz_alinmistir),
                        Toast.LENGTH_SHORT
                    ).show()
                    viewModel.deleteAllEntityFromRoom()
                    findNavController().popBackStack()
                }
            }
        }
    }
}
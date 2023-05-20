package ru.app.fastestdelivery.presentation.paymentResult

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import ru.app.fastestdelivery.R
import ru.app.fastestdelivery.databinding.FragmentPaymentResultBinding

@AndroidEntryPoint
class PaymentResultFragment : Fragment(R.layout.fragment_payment_result) {

    private val viewBinding: FragmentPaymentResultBinding by viewBinding(FragmentPaymentResultBinding::bind)
    private val viewModel: PaymentResultViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() = with(viewBinding) {
        paymentResultContinueButton.setOnClickListener {
            viewModel.onContinueClicked()
        }
    }

    companion object {

        fun newInstance() = PaymentResultFragment()

    }

}
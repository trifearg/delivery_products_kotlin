package ru.app.fastestdelivery.presentation.productDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import ru.app.fastestdelivery.R
import ru.app.fastestdelivery.databinding.FragmentProductDetailBinding

@AndroidEntryPoint
class ProductDetailFragment : Fragment(R.layout.fragment_product_detail) {

    private val viewBinding: FragmentProductDetailBinding by viewBinding(FragmentProductDetailBinding::bind)
    private val viewModel: ProductDetailViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        initViews()
    }

    private fun initViews() = with(viewBinding) {
        productDetailBack.setOnClickListener {
            viewModel.onBackClicked()
        }
    }

    private fun initViewModel() {
        arguments?.getInt(PRODUCT_ID_KEY)?.let(viewModel::init)
    }

    companion object {

        private const val PRODUCT_ID_KEY = "PRODUCT_ID_KEY"

        fun newInstance(productId: Int) = ProductDetailFragment().apply {
            arguments = Bundle().apply {
                putInt(PRODUCT_ID_KEY, productId)
            }
        }

    }
}
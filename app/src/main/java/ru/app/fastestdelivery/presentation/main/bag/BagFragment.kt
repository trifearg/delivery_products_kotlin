package ru.app.fastestdelivery.presentation.main.bag

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import ru.app.fastestdelivery.R
import ru.app.fastestdelivery.databinding.FragmentBagBinding
import ru.app.fastestdelivery.presentation.main.MainViewModel
import ru.app.fastestdelivery.presentation.main.bag.recycler.BagProductsAdapter
import ru.app.fastestdelivery.util.observe

@AndroidEntryPoint
class BagFragment : Fragment(R.layout.fragment_bag) {

    private val viewBinding: FragmentBagBinding by viewBinding(FragmentBagBinding::bind)
    private val viewModel: BagViewModel by viewModels()

    private val viewMainModel: MainViewModel by activityViewModels()

    private val adapter by lazy {
        BagProductsAdapter(
            onProductPlusClick = viewModel::onProductPlusClicked,
            onProductMinusClick = viewModel::onProductMinusClicked,
            onProductDeleteClick = viewModel::onProductDeleteClicked
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initObservers()
    }

    private fun initViews() = with(viewBinding) {
        bagBuyButton.setOnClickListener { viewModel.onBuyButtonClicked() }

        bagProductsRecycler.adapter = adapter
        bagProductsRecycler.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )
    }

    private fun initObservers() = with(viewBinding) {
        viewModel.state.observe(viewLifecycleOwner) {
            bagProductsRecyclerPlaceholder.isVisible = it.items.isEmpty()
            bagProductsRecycler.isVisible = it.items.isNotEmpty()
            adapter.setItems(it.items)
            bagTotalAmount.text = context?.getString(R.string.bag_total_amount, it.totalPrice.toString())
        }

        viewMainModel.state.observe(viewLifecycleOwner) {
            myAddressBag.text = it.myLocationAddress
        }
    }

    companion object {

        fun newInstance() = BagFragment()

    }

}
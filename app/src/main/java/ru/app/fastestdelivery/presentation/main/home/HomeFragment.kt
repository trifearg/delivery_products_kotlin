package ru.app.fastestdelivery.presentation.main.home

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import ru.app.fastestdelivery.R
import ru.app.fastestdelivery.databinding.FragmentHomeBinding
import ru.app.fastestdelivery.databinding.FragmentMainBinding
import ru.app.fastestdelivery.presentation.main.MainViewModel
import ru.app.fastestdelivery.presentation.main.home.recycler.HomeProductsAdapter
import ru.app.fastestdelivery.util.observe

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewBinding: FragmentHomeBinding by viewBinding(FragmentHomeBinding::bind)
    private val viewModel: HomeViewModel by viewModels()

    private val viewMainModel: MainViewModel by activityViewModels()

    private val adapter by lazy {
        HomeProductsAdapter(
            onProductClicked = viewModel::onProductClicked,
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initObservers()
    }

    private fun initViews() = with(viewBinding) {
        homeProductsRecycler.adapter = adapter
        homeProductsRecycler.layoutManager = GridLayoutManager(
            requireContext(),
            2,
            LinearLayoutManager.VERTICAL,
            false
        )
    }

    private fun initObservers() = with(viewBinding) {
        viewModel.state.observe(viewLifecycleOwner) {
            adapter.setItems(it.items)
        }

        viewMainModel.state.observe(viewLifecycleOwner) {
            myAdress.text = it.myLocationAddress
        }
    }

    companion object {

        fun newInstance() = HomeFragment()

    }

}
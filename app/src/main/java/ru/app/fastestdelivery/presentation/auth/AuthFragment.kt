package ru.app.fastestdelivery.presentation.auth

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import ru.app.fastestdelivery.R
import ru.app.fastestdelivery.databinding.FragmentAuthBinding
import ru.app.fastestdelivery.presentation.auth.login.LoginFragment
import ru.app.fastestdelivery.presentation.auth.models.AuthTab
import ru.app.fastestdelivery.presentation.auth.register.RegisterFragment
import ru.app.fastestdelivery.util.observe

@AndroidEntryPoint
class AuthFragment : Fragment(R.layout.fragment_auth) {

    private val viewBinding: FragmentAuthBinding by viewBinding(FragmentAuthBinding::bind)
    private val viewModel: AuthViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initObservers()
    }

    private fun initViews() = with(viewBinding) {
        authBack.setOnClickListener { viewModel.onBackClicked() }
        authTabLogin.tabAuthTitle.text = context?.getString(R.string.fragment_auth_tab_login_text)
        authTabRegister.tabAuthTitle.text = context?.getString(R.string.fragment_auth_tab_register_text)
        authTabLogin.tabAuthTitle.setOnClickListener { viewModel.onTabClicked(AuthTab.LOGIN) }
        authTabRegister.tabAuthTitle.setOnClickListener { viewModel.onTabClicked(AuthTab.REGISTER) }
    }

    private fun initObservers() = with(viewBinding) {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            authTabLogin.tabAuthUnderline.isVisible = false
            authTabRegister.tabAuthUnderline.isVisible = false

            when (state.selectedTab) {
                AuthTab.LOGIN -> authTabLogin.tabAuthUnderline.isVisible = true
                AuthTab.REGISTER -> authTabRegister.tabAuthUnderline.isVisible = true
            }

            getTabFragment(state.selectedTab).let { fragment ->
                childFragmentManager
                    .beginTransaction()
                    .replace(R.id.auth_container, fragment)
                    .commit()
            }
        }
    }

    private fun getTabFragment(tab: AuthTab) = when (tab) {
        AuthTab.LOGIN -> LoginFragment.newInstance()
        AuthTab.REGISTER -> RegisterFragment.newInstance()
    }

    companion object {

        fun newInstance() = AuthFragment()

    }

}
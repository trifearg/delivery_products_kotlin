package ru.app.fastestdelivery.presentation.auth.login

import android.os.Bundle
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import ru.app.fastestdelivery.R
import ru.app.fastestdelivery.databinding.FragmentLoginBinding
import ru.app.fastestdelivery.util.observe
import ru.app.fastestdelivery.util.ui.inputState.InputState
import ru.app.fastestdelivery.util.ui.inputState.setDefaultState
import ru.app.fastestdelivery.util.ui.inputState.setErrorState

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {

    private val viewBinding: FragmentLoginBinding by viewBinding(FragmentLoginBinding::bind)
    private val viewModel: LoginViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initObservers()
    }

    private fun initViews() = with(viewBinding) {
        loginContinueButton.setOnClickListener { viewModel.onContinueButtonClicked() }
        loginEmail.inputMailViewTitle.text = context?.getString(R.string.fragment_login_email_input_title)
        loginEmail.inputMailViewEditText.hint = context?.getString(R.string.fragment_login_email_input_hint)
        loginPassword.inputPasswordViewTitle.text = context?.getString(R.string.fragment_login_password_input_title)
        loginPassword.inputPasswordViewEditText.hint = context?.getString(R.string.fragment_login_password_input_hint)
        loginEmail.inputMailViewEditText.doAfterTextChanged(viewModel::onEmailUpdated)
        loginPassword.inputPasswordViewEditText.doAfterTextChanged(viewModel::onPasswordUpdated)
    }

    private fun initObservers() = with(viewBinding) {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state.email) {
                is InputState.Data -> loginEmail.inputMailViewEditText.setDefaultState(requireContext())
                InputState.Error.EmptyInput -> loginEmail.inputMailViewEditText.setErrorState(requireContext())
            }

            when (state.password) {
                is InputState.Data -> loginPassword.inputPasswordViewEditText.setDefaultState(requireContext())
                InputState.Error.EmptyInput -> loginPassword.inputPasswordViewEditText.setErrorState(requireContext())
            }
        }

        viewModel.errorEvent.observe(viewLifecycleOwner) { message ->
            activity?.findViewById<View>(android.R.id.content)?.let {
                Snackbar
                    .make(it, message.asString(requireContext()), Snackbar.LENGTH_LONG)
                    .setAction(getString(R.string.global_ok)) { }
                    .show()
            }
        }
    }

    companion object {

        fun newInstance() = LoginFragment()

    }

}
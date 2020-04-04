package com.cheise_proj.spiice_ui_challenge.onBoarding.ui.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.cheise_proj.presentation.utils.InputValidation
import com.cheise_proj.presentation.viewmodel.UserViewModel
import com.cheise_proj.presentation.vo.STATUS
import com.cheise_proj.presentation.vo.UserSession
import com.cheise_proj.spiice_ui_challenge.R
import com.cheise_proj.spiice_ui_challenge.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_sign_up.*
import org.jetbrains.anko.support.v4.toast
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class SignUpFragment : BaseFragment() {
    @Inject
    lateinit var viewModel: UserViewModel

    @Inject
    lateinit var inputValidation: InputValidation

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tv_footer.setOnClickListener {
            navigateToSignInPage()
        }
        btn_sign_up.setOnClickListener {
            registerUser()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        configViewModel()
    }

    private fun configViewModel() {
        viewModel = ViewModelProvider(this, factory)[UserViewModel::class.java]
    }

    private fun registerUser() {
        if (!inputValidation.isEditTextFilled(et_first_name)) return
        if (!inputValidation.isEditTextFilled(et_last_name)) return
        if (!inputValidation.isEditTextFilled(et_email, null, true)) return
        if (!inputValidation.isEditTextFilled(et_password)) return

        val name = "${et_first_name.text} ${et_last_name.text}"
        val email = et_email.text.toString()
        val password = et_password.text.toString()

        subscribeRegisterObserver(name, email, password)

    }

    private fun subscribeRegisterObserver(name: String, email: String, password: String) {
        viewModel.registerUser(name, email, password)
            .observe(viewLifecycleOwner, Observer { resource ->
                when (resource.status) {
                    STATUS.LOADING -> showProgress(progressBar)
                    STATUS.SUCCESS -> {
                        hideProgress(progressBar)
                        with(resource.data) {
                            val session =
                                UserSession(true, this?.email, this?.avatarUrl, this?.name)
                            session.accessToken = this?.accessToken ?: ""
                            session.refreshToken = this?.refreshToken ?: ""
                            prefs.setUserSession(session)
                        }
                        navigateToFeedPage()
                    }
                    STATUS.ERROR -> {
                        hideProgress(progressBar)
                        toast("error: ${resource?.message}")
                    }
                }

            })
    }

    private fun navigateToFeedPage() {
        val action = SignUpFragmentDirections.actionSignUpFragmentToSpiiceNavActivity()
        findNavController().navigate(action)
    }

    private fun navigateToSignInPage() {
        val action = SignUpFragmentDirections.actionSignUpFragmentToSignInFragment()
        findNavController().navigate(action)
    }

}

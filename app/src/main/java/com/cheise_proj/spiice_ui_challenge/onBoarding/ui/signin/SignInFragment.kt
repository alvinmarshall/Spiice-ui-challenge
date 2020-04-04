package com.cheise_proj.spiice_ui_challenge.onBoarding.ui.signin

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
import kotlinx.android.synthetic.main.fragment_sign_in.*
import org.jetbrains.anko.support.v4.toast
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class SignInFragment : BaseFragment() {
    @Inject
    lateinit var viewModel: UserViewModel

    @Inject
    lateinit var inputValidation: InputValidation


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideProgress(progressBar)
        tv_footer.setOnClickListener {
            val action = SignInFragmentDirections.actionSignInFragmentToSignUpFragment()
            findNavController().navigate(action)
        }

        btn_sign_in.setOnClickListener {
            loginUser()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        configViewModel()
    }

    private fun configViewModel() {
        viewModel = ViewModelProvider(this, factory)[UserViewModel::class.java]
    }

    private fun loginUser() {
        if (!inputValidation.isEditTextFilled(et_email, null, true)) return
        if (!inputValidation.isEditTextFilled(et_password)) return

        val email = et_email.text.toString()
        val password = et_password.text.toString()
        showProgress(progressBar)
        subscribeLoginObserver(email, password)
    }

    private fun subscribeLoginObserver(email: String, password: String) {
        viewModel.getAuthenticatedUser(email, password)
            .observe(viewLifecycleOwner, Observer { resource ->
                when (resource.status) {
                    STATUS.LOADING -> {}
                    STATUS.SUCCESS -> {
                        hideProgress(progressBar)
                        println("user- ${resource.data}")
                        with(resource.data) {
                            val session =
                                UserSession(true, this?.email, this?.avatarUrl, this?.name)
                            session.accessToken = resource.data?.accessToken ?: ""
                            session.refreshToken = resource.data?.refreshToken ?: ""
                            prefs.setUserSession(session)
                        }
                        navigateToFeed()
                    }
                    STATUS.ERROR -> {
                        hideProgress(progressBar)
                        println("error ${resource.message}")
                        toast("error: ${resource?.message}")
                    }
                }
            })
    }

    private fun navigateToFeed() {
        val action = SignInFragmentDirections.actionSignInFragmentToSpiiceNavActivity()
        findNavController().navigate(action)
        activity?.finish()

    }


}

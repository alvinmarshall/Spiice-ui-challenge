package com.cheise_proj.spiice_ui_challenge.onBoarding.ui.starter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.cheise_proj.spiice_ui_challenge.R
import com.cheise_proj.spiice_ui_challenge.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_starter.*
import timber.log.Timber

/**
 * A simple [Fragment] subclass.
 */
class StarterFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_starter, container, false)
    }

    override fun onStart() {
        super.onStart()
        if (prefs.getUserSession().isLogin) {
            Timber.i("session: ${prefs.getUserSession()}")
            return navigateToFeed()
        }
    }

    private fun navigateToFeed() {
        val action = StarterFragmentDirections.actionStarterFragmentToSpiiceNavActivity()
        findNavController().navigate(action)
        activity?.finish()
        Timber.i("actionStarterFragmentToSpiiceNavActivity")

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tv_footer.setOnClickListener {
            val action = StarterFragmentDirections.actionStarterFragmentToSignInFragment()
            findNavController().navigate(action)
            Timber.i("actionStarterFragmentToSignInFragment")
        }
        btn_discover.setOnClickListener {
            val action = StarterFragmentDirections.actionStarterFragmentToOnBoardingFragment()
            findNavController().navigate(action)
            Timber.i("actionStarterFragmentToOnBoardingFragment")
        }
    }

}

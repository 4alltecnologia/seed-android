package com.fourall.seedandroid.views

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.fourall.seedandroid.R
import com.fourall.seedandroid.api.RestClient
import com.fourall.seedandroid.databinding.ActivityFeatureBinding
import com.fourall.seedandroid.featuredata.BuildConfig
import com.fourall.seedandroid.remote.UserRemoteDataSource
import com.fourall.seedandroid.remote.endpoint.UserApiClient
import com.fourall.seedandroid.repository.UserDataRepository
import com.fourall.seedandroid.viewmodels.FeatureViewModel
import com.fourall.seedandroid.viewmodels.factory.FeatureViewModelFactory
import com.fourall.seedandroid.viewmodels.utils.CommandInjector
import com.fourall.seedandroid.viewmodels.utils.GenericCommand
import kotlinx.android.synthetic.main.activity_feature.*

class FeatureActivity : AppCompatActivity() {

    private lateinit var activityFeatureBinding: ActivityFeatureBinding
    private lateinit var featureViewModel: FeatureViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        prepareUi()
        prepareViewModel()
    }

    override fun onResume() {

        super.onResume()

        featureViewModel.loadUsers()
    }

    private fun prepareUi() {

        activityFeatureBinding = DataBindingUtil.setContentView(this, R.layout.activity_feature)
    }

    private fun prepareViewModel() {

        val featureViewModelFactory = FeatureViewModelFactory(
            UserDataRepository(
                UserRemoteDataSource(
                    RestClient.getApiClient(UserApiClient::class.java, BuildConfig.REST_API_URL)
                )
            ),
            CommandInjector)

        featureViewModel = ViewModelProviders.of(this, featureViewModelFactory)
            .get(FeatureViewModel::class.java)

        featureViewModel.apply {

            viewState.observe(this@FeatureActivity, Observer { vs ->

                vs?.let { render(it) }
            })

            command.observe(this@FeatureActivity, Observer { cmd ->

                cmd?.let { triggerCommand(it) }
            })
        }

        activityFeatureBinding.featureViewModel = featureViewModel
    }

    private fun render(viewState: FeatureViewModel.ViewState) {

        when (viewState.isLoadingUsers) {

            true -> { loadingUsersProgressBar.visibility = View.VISIBLE }
            false -> { loadingUsersProgressBar.visibility = View.INVISIBLE }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun triggerCommand(command: GenericCommand) {

        when (command) {

            is FeatureViewModel.Command.ShowUsers -> {

                ownersLabelTextView.visibility = View.VISIBLE

                owner1TextView.text = "@${command.users[0]}"
                owner2TextView.text = "@${command.users[1]}"
            }

            is FeatureViewModel.Command.ShowErrorMessage -> {

                Toast.makeText(this@FeatureActivity,
                    "There was a problem when loading owners.",
                    Toast.LENGTH_LONG).show()
            }
        }
    }
}
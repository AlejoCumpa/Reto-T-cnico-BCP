package cumpa.alejandro.retotcnicobcp.ui.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import cumpa.alejandro.retotcnicobcp.databinding.ActivityFullscreenBinding
import cumpa.alejandro.retotcnicobcp.ui.viewmodel.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FullscreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFullscreenBinding

    private val splashViewModel : SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFullscreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        splashViewModel.isLoading.observe(this, Observer {
            binding.progressbar.isVisible = it
            binding.tvProgress.isVisible = it
        })

        splashViewModel.finishedFetching.observe(this, Observer { finishFetching ->
            if(finishFetching){
                startActivity();
            }
        })

        splashViewModel.onCreate()
    }

    private fun startActivity(){
        startActivity(Intent(this, ExchangeActivity::class.java))
        finish()
    }
}
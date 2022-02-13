    package com.samuelnunes.too_dooapp.presentation

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.snackbar.Snackbar
import com.samuelnunes.too_dooapp.R
import com.samuelnunes.too_dooapp.common.extensions.visibilityIf
import com.samuelnunes.too_dooapp.databinding.ActivityMainBinding
import com.samuelnunes.too_dooapp.presentation.todo_list.ListTodoFragmentDirections
import kotlinx.coroutines.flow.collect
import timber.log.Timber

    class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = navigationSetup()
        setupListeners(navController)
    }

        private fun setupListeners(navController: NavController) {
            binding.apply {
                lifecycleScope.launchWhenStarted {
                    mainViewModel.loading.collect {
                        progressCircular.visibilityIf(it)
                    }
                }

                mainViewModel.fabClickListener.observe(this@MainActivity) {
                    fab.setOnClickListener(it)
                }

                mainViewModel.screenState.observe(this@MainActivity) {
                    fab.setImageResource(it.icon)
                }
            }

        }

        private fun navigationSetup(): NavController {
            setSupportActionBar(binding.toolbar)
            val navController = findNavController(R.id.nav_host_fragment_content_main)
            appBarConfiguration = AppBarConfiguration(navController.graph)
            setupActionBarWithNavController(navController, appBarConfiguration)
            return navController
        }

        override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}
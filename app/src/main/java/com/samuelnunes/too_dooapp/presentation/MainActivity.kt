    package com.samuelnunes.too_dooapp.presentation

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.snackbar.Snackbar
import com.samuelnunes.too_dooapp.R
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

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        lifecycleScope.launchWhenStarted {
            mainViewModel.loading.collect {
                binding.progressCircular.visibility = if (it) VISIBLE else GONE
            }
        }

        mainViewModel.screenState.observe(this){
            Timber.d("Atualizar fab: $it")
            binding.fab.setImageResource(it.icon)
        }

        binding.fab.setOnClickListener {
            val direction =
                ListTodoFragmentDirections.actionGlobalDetailTodoFragment(ScreenState.SAVE)
            navController.navigate(direction)
            mainViewModel.setState(ScreenState.SAVE)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}
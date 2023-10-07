package com.example.daznapplication.ui.main

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.daznapplication.R
import com.example.daznapplication.databinding.ActivityMainBinding
import com.example.daznapplication.utils.extensions.launchAndRepeatWhenStarted
import com.example.daznapplication.ui.player.PlayerFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this)[(MainActivityViewModel::class.java)]
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        launchAndRepeatWhenStarted {
            viewModel.stateFlow.collect {
                when (it) {
                    MainActivityStates.INIT -> Unit
                    MainActivityStates.TOGGLING_ACTION_BAR -> toggleActionBar()
                    MainActivityStates.SHOWING_VIDEO_VIEW -> showVideoFragment()
                    MainActivityStates.DISMISSING_VIDEO_VIEW -> {
                        replaceFragment(MainFragment())
                        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
                    }
                }
            }
        }
    }

    private fun toggleActionBar() {
        if (supportActionBar?.isShowing == true) supportActionBar?.hide()
        else supportActionBar?.show()
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, fragment)
            commit()
        }
    }

    private fun showVideoFragment() {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        replaceFragment(PlayerFragment())
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                viewModel.onEvent(MainActivityEvent.DismissVideoView)
                supportActionBar?.hide()
            }

            else -> Unit
        }
        return return super.onOptionsItemSelected(item)
    }
}
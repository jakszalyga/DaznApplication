package com.example.daznapplication.ui.player

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.example.daznapplication.ui.main.MainActivityEvent
import com.example.daznapplication.ui.main.MainActivityViewModel
import com.example.daznapplication.databinding.FragmentPlayerBinding


class PlayerFragment : Fragment() {

    private lateinit var player: Player

    private var _binding: FragmentPlayerBinding? = null
    private val binding get() = _binding!!

    private lateinit var mainActivityViewModel: MainActivityViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mainActivityViewModel =
            ViewModelProvider(requireActivity())[MainActivityViewModel::class.java]
        _binding = FragmentPlayerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupVideoView()
        setupClickListeners()
    }

    override fun onDestroy() {
        super.onDestroy()
        player.release()

    }

    private fun setupClickListeners() {
        binding.root.setOnClickListener {
            mainActivityViewModel.onEvent(MainActivityEvent.ToggleActionBar)
        }
        binding.playerView.setOnClickListener {
            mainActivityViewModel.onEvent(MainActivityEvent.ToggleActionBar)
        }
    }

    private fun setupVideoView() {
        val playerView = binding.playerView
        val mediaItem: MediaItem = MediaItem.fromUri(mainActivityViewModel.currentVideoUrl!!)
        player = ExoPlayer.Builder(requireContext()).build()
        playerView.player = player
        player.apply {
            setMediaItem(mediaItem)
            prepare()
            playWhenReady = true
        }
    }
}
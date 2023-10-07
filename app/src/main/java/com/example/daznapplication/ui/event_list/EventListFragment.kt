package com.example.daznapplication.ui.event_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.daznapplication.adapters.EventsAdapter
import com.example.daznapplication.databinding.FragmentEventListBinding
import com.example.daznapplication.ui.main.MainActivityEvent
import com.example.daznapplication.ui.main.MainActivityViewModel
import com.example.daznapplication.utils.extensions.launchAndRepeatWhenStarted
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class EventListFragment : Fragment() {

    private var _binding: FragmentEventListBinding? = null
    private val binding get() = _binding!!

    private lateinit var mainActivityViewModel: MainActivityViewModel
    private val viewModel by viewModels<EventListFragmentViewModel>()

    private val recyclerViewAdapter = EventsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mainActivityViewModel =
            ViewModelProvider(requireActivity())[(MainActivityViewModel::class.java)]
        _binding = FragmentEventListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpRecyclerView()
        launchAndRepeatWhenStarted { viewModel.state.collectLatest { recyclerViewAdapter.setList(it.events) } }
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setUpRecyclerView() {
        binding.eventRecyclerView.apply {
            itemAnimator = null
            adapter = recyclerViewAdapter
        }
        recyclerViewAdapter.eventClickListener = { event ->
            mainActivityViewModel.onEvent(MainActivityEvent.ShowVideoView(event.videoUrl))
        }
    }
}
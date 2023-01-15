package com.example.testtask.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.entities.Event
import com.example.testtask.R
import com.example.testtask.common.BaseFragment
import com.example.testtask.common.ViewModelFactory
import com.example.testtask.databinding.FragmentHomeBinding
import com.example.testtask.di.Injector
import javax.inject.Inject

class HomeFragment : BaseFragment(), EventsRecyclerViewAdapter.Listener {

    @Inject
    lateinit var factory: ViewModelFactory

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = _binding ?: throw RuntimeException("HomeBinding is null")

    override val viewModel: HomeViewModel by viewModels { factory }

    private val adapter = EventsRecyclerViewAdapter(this)

    init {
        Injector.inject(this)
    }

    override fun onEventClicked(event: Event) {
        navigateToEvent(event)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        observers()
        listeners()
        getEvents()
    }

    private fun setupAdapter() = with(binding) {
        rvEvents.adapter = adapter
        rvEvents.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun observers() {
        viewModel.events.observe(viewLifecycleOwner) {
            adapter.updateData(it)
        }
        viewModel.errorEvent.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), getString(R.string.weather_error, it), Toast.LENGTH_SHORT).show()
        }
    }

    private fun listeners() = with(binding) {
        btnAdd.setOnClickListener {
            navigateToEvent()
        }
        swipe.setOnRefreshListener {
            updateWeather()
            swipe.isRefreshing = false
        }
    }

    private fun getEvents() {
        viewModel.getEvents()
    }

    private fun updateWeather() {
        viewModel.updateWeather()
    }

    private fun navigateToEvent(event: Event? = null) {
        val navController = findNavController()
        val action = HomeFragmentDirections.actionHomeFragmentToEventFragment(event)
        navController.navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
package com.apprecipe.rijkscollection.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.apprecipe.rijkscollection.databinding.FragmentListBinding
import com.apprecipe.rijkscollection.ui.NavDestination
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ArtListFragment : Fragment() {
    private var _binding: FragmentListBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: ArtListViewModel by viewModels()

    private val adapter = ArtListAdapter {
        viewModel.onItemClicked(it)
    }

    private var getDataJob: Job? = null

    @OptIn(InternalCoroutinesApi::class)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)

        binding.list.adapter = adapter

        // Make sure we cancel the previous job before creating a new one
        getDataJob?.cancel()
        getDataJob = viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getArtListData().collectLatest {
                adapter.submitData(it)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.navDestination.collect {
                navigateTo(it)
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun navigateTo(destination: NavDestination) {
        when (destination) {
            is NavDestination.ArtDetail -> findNavController()
                .navigate(ArtListFragmentDirections.actionArtListFragmentToArtDetailFragment(destination.objectNumber))
        }
    }
}

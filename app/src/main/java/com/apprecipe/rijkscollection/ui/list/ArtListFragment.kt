package com.apprecipe.rijkscollection.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.apprecipe.rijkscollection.databinding.FragmentListBinding
import com.apprecipe.rijkscollection.ui.NavDestination
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        binding.list.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {

                launch {
                    viewModel.uiState.collect { uiState ->
                        when (uiState) {
                            is ArtListUiState.Success -> adapter.submitData(uiState.data)
                            is ArtListUiState.Error -> TODO()
                        }
                    }
                }

                launch {
                    viewModel.navDestination.collect {
                        navigateTo(it)
                    }
                }
            }
        }
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

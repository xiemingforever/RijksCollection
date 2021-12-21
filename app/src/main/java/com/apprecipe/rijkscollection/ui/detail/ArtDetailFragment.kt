package com.apprecipe.rijkscollection.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.composethemeadapter.MdcTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ArtDetailFragment : Fragment() {

    private val args: ArtDetailFragmentArgs by navArgs()

    private val viewModel: ArtDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = ComposeView(requireContext()).apply {

        viewLifecycleOwner.lifecycleScope.launch {
            val artDetail = viewModel.getArtDetailData(args.objectNumber)

            setContent {
                MdcTheme {
                    ArtDetailsScreen(
                        artDetail = artDetail,
                        onBackClick = {
                            findNavController().navigateUp()
                        }
                    )
                }
            }
        }
    }
}

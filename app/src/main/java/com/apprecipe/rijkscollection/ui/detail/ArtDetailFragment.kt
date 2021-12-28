package com.apprecipe.rijkscollection.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.material.Surface
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.apprecipe.rijkscollection.ui.theme.RijksCollectionTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArtDetailFragment : Fragment() {

    private val viewModel: ArtDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = ComposeView(requireContext()).apply {

        setContent {
            RijksCollectionTheme {
                Surface {
                    ArtDetailsScreen(
                        artDetailViewModel = viewModel,
                        onBackClick = {
                            findNavController().navigateUp()
                        }
                    )
                }
            }
        }
    }
}

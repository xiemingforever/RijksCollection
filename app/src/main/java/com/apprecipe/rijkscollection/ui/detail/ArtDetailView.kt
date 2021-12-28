package com.apprecipe.rijkscollection.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.apprecipe.rijkscollection.R
import com.apprecipe.rijkscollection.data.ArtDetail
import com.apprecipe.rijkscollection.data.WebImage
import com.apprecipe.rijkscollection.ui.theme.RijksCollectionTheme

@Composable
fun ArtDetailsScreen(
    artDetailViewModel: ArtDetailViewModel,
    onBackClick: () -> Unit
) {
    val uiState: ArtDetailUiState by artDetailViewModel.uiState.collectAsState()
    when (uiState) {
        is ArtDetailUiState.Loading -> EmptyScreen("Loading")
        is ArtDetailUiState.Success -> {
            ArtDetailsView((uiState as ArtDetailUiState.Success).data, onBackClick)
        }
        is ArtDetailUiState.Error -> EmptyScreen("Error")
    }
}

@Composable
fun EmptyScreen(text: String) {
    // TODO Handle Loading and Error states
    Text(text)
}

@Composable
fun ArtDetailsView(
    artDetail: ArtDetail,
    onBackClick: () -> Unit
) {
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        ArtDetailsToolbar(
            artDetail = artDetail,
            onBackClick = onBackClick,
        )

        ArtImage(
            imageUrl = artDetail.webImage.url
        )

        ArtInfo(artDetail = artDetail/*, toolbarState = toolbarState*/)
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
private fun ArtImage(
    imageUrl: String
) {
    val painter = rememberImagePainter(
        data = imageUrl,
        builder = {
            crossfade(true)
        },
    )

    Image(
        painter = painter,
        contentScale = ContentScale.FillWidth,
        contentDescription = stringResource(R.string.content_description_art_photo_art_detail),
        modifier = Modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = dimensionResource(R.dimen.list_image_height))
    )

    if (painter.state is ImagePainter.State.Loading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.primaryVariant)
        )
    }
}

@Composable
private fun ArtInfo(
    artDetail: ArtDetail,
) {
    Column(
        Modifier
            .padding(dimensionResource(R.dimen.margin_16))
            .widthIn(max = dimensionResource(R.dimen.text_max_width))
    ) {
        Text(
            text = artDetail.longTitle,
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.margin_16)))

        Text(
            text = artDetail.subTitle,
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.margin_16)))

        Text(
            text = artDetail.descriptionEN ?: artDetail.descriptionNL ?: "",
            style = MaterialTheme.typography.body1,
        )
    }
}

@Composable
private fun ArtDetailsToolbar(
    artDetail: ArtDetail,
    onBackClick: () -> Unit,
) {
    Surface {
        TopAppBar(
            title = {
                Text(
                    text = artDetail.title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            },
            navigationIcon = {
                IconButton(onBackClick) {
                    Icon(
                        Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.content_description_back_button_art_detail)
                    )
                }
            },
            backgroundColor = MaterialTheme.colors.primary
        )
    }
}

@Preview
@Composable
private fun ArtDetailsScreenPreview() {
    RijksCollectionTheme {
        Surface {
            ArtDetailsView(
                ArtDetail(
                    objectNumber = "SK-C-5",
                    title = "The Night Watch",
                    longTitle = "The Night Watch",
                    webImage = WebImage(
                        "bbd1fae8-4023-4859-8ed1-d38616aec96c",
                        "https://lh3.googleusercontent.com/SsEIJWka3_cYRXXSE8VD3XNOgtOxoZhqW1uB6UFj78eg8gq3G4jAqL4Z_5KwA12aD7Leqp27F653aBkYkRBkEQyeKxfaZPyDx0O8CzWg=s0"
                    ),
                    descriptionEN = "Rembrandt’s largest, most famous canvas was made for the Arquebusiers guild hall. This was one of several halls of Amsterdam’s civic guard, the city’s militia and police. \r\nRembrandt was the first to paint figures in a group portrait actually doing something. The captain, dressed in black, is telling his lieutenant to start the company marching. The guardsmen are getting into formation. Rembrandt used the light to focus on particular details, like the captain’s gesturing hand and the young girl in the foreground. She was the company mascot.\r\n",
                    descriptionNL = "Rembrandt’s largest, most famous canvas was made for the Arquebusiers guild hall. This was one of several halls of Amsterdam’s civic guard, the city’s militia and police. \r\nRembrandt was the first to paint figures in a group portrait actually doing something. The captain, dressed in black, is telling his lieutenant to start the company marching. The guardsmen are getting into formation. Rembrandt used the light to focus on particular details, like the captain’s gesturing hand and the young girl in the foreground. She was the company mascot.\r\n",
                    subTitle = "Rembrandt van Rijn",
                )
            ) { }
        }
    }
}

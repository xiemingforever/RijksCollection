package com.apprecipe.rijkscollection.ui

sealed class NavDestination {
    data class ArtDetail(val objectNumber: String) : NavDestination()
}

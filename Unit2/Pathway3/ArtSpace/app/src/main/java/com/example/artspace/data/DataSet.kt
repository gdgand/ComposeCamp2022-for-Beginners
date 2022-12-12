package com.example.artspace.data

import com.example.artspace.R
import com.example.artspace.model.ArtItem

object DataSet {
    val artItem = listOf(
        ArtItem(
            painterId = R.drawable.the_last_supper,
            title = "The Last Supper",
            artist = "Leonardo da Vinci",
            year = 1495
        ),
        ArtItem(
            painterId = R.drawable.mona_lisa,
            title = "Mona Lisa",
            artist = "Leonardo da Vinci",
            year = 1503
        ),
        ArtItem(
            painterId = R.drawable.the_grils_on_the_bridge,
            title = "The Girls on the Bridge",
            artist = "Edvard Munch",
            year = 1901
        ),
        ArtItem(
            painterId = R.drawable.the_scream,
            title = "The Scream",
            artist = "Edvard Munch",
            year = 1893
        ),
        ArtItem(
            painterId = R.drawable.madonna_of_the_magnificat,
            title = "Madonna of the Magnificat",
            artist = "Sandro Botticelli",
            year = 1481
        ),
        ArtItem(
            painterId = R.drawable.la_nascita_di_venere,
            title = "Nascita di Venere",
            artist = "Sandro Botticelli",
            year = 1486
        ),
        ArtItem(
            painterId = R.drawable.il_giudizio_universale,
            title = "Il Giudizio universale",
            artist = "Michelangelo Buonarroti",
            year = 1535
        ),
        ArtItem(
            painterId = R.drawable.david,
            title = "David",
            artist = "Michelangelo Buonarroti",
            year = 1501
        ),
        ArtItem(
            painterId = R.drawable.almond_blossom,
            title = "Almond blossom",
            artist = "Vincent Willem van Gogh",
            year = 1890
        ),
        ArtItem(
            painterId = R.drawable.self_portrait,
            title = "Self Portrait",
            artist = "Vincent Willem van Gogh",
            year = 1889
        )
    )
}

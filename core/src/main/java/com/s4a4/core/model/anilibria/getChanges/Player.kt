package com.s4a4.core.model.anilibria.getChanges

data class Player(
    val alternative_player: String,
    val episodes: Episodes,
    val host: String,
    val is_rutube: Boolean,
    val listSeries: List<SeriesAnimeData>,
    val rutube: Rutube
)
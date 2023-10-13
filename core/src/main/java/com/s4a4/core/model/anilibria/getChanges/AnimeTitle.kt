package com.s4a4.core.model.anilibria.getChanges

data class AnimeTitle(
    val announce: Any?,
    val blocked: Blocked,
    val code: String,
    val description: String,
    val franchises: List<Franchise>,
    val genres: List<String>,
    val id: Int,
    val in_favorites: Int,
    val last_change: Int,
    val names: NamesX,
    val player: Player,
    val posters: Posters,
    val season: Season,
    val status: Status,
    val team: Team,
    val torrents: Torrents,
    val type: Type,
    val updated: Int
)
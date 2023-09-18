package com.example.home.data.anime_vost

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.core.model.ItemAnimeModel
import com.example.core.model.VoiceModel
import com.example.home.data.anime_vost.repository.PageAnimeVostRepository
import com.example.home.data.anime_vost.repository.anilibria.PageAnilibriaRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class GetPageFromAnimeVostUseCase @Inject constructor(
    private val repositoryAnimeVost: PageAnimeVostRepository,
    private val repositoryAnilibria: PageAnilibriaRepository
) {
    private val allAnimeList = mutableStateListOf<ItemAnimeModel>()
    private var page = 1

    suspend fun execute(): SnapshotStateList<ItemAnimeModel> {
        animeVostList()
        anilibriaList()

        return allAnimeList
    }

    suspend fun newPage(): SnapshotStateList<ItemAnimeModel> {
        page++
        runBlocking {
            val a = async {
                animeVostList()
            }
            val b = async {
                anilibriaList()
            }
            a.await()
            b.await()
        }




        return allAnimeList
    }

    private suspend fun animeVostList() {

        val list = repositoryAnimeVost.getPageAnime(page)
        if (list.isSuccessful) {
            list.body()?.data?.forEach { forEachItem ->
                val voiceModel = VoiceModel(
                    "AnimeVost",
                    forEachItem.timer,
                    com.example.core.R.drawable.anime_vost
                )
                val nameTitleRu = forEachItem.title.split("/")[0].split("(")[0].split("-")[0]
                val nameTitleEng =
                    forEachItem.title.split("/")[1].split("(")[0].split("-")[0].split("[")[0]
                val itemAnimeModel = ItemAnimeModel(
                    forEachItem.urlImagePreview,
                    nameTitleRu,
                    nameTitleEng,
                    genre = forEachItem.genre.split(","),
                    voice = mutableStateListOf(voiceModel)
                )


                val listCurrentItem =
                    allAnimeList.find {
                        levenshteinDistance( it.nameRu.trimEnd(),nameTitleRu.trimEnd()) >= 0.5 ||
                                levenshteinDistance(it.nameEng.trimEnd(),nameTitleEng.trimEnd()) >= 0.5
                    }
                if (listCurrentItem == null) {
                    allAnimeList.add(itemAnimeModel)
                } else {
                    val voiceAnimeVost =
                        listCurrentItem.voice.find { it.voice.trimEnd() == voiceModel.voice.trimEnd() }
                    if (voiceAnimeVost == null) {
                        listCurrentItem.voice.add(voiceModel)
                    }
                }
            }
        }

    }

    private suspend fun anilibriaList() {

        val list = repositoryAnilibria.getPageAnime(page)
        if (list.isSuccessful) {
            list.body()?.list?.forEach { forEachItem ->
                val voiceModel = VoiceModel(
                    "Anilibria",
                    forEachItem.last_change,
                    com.example.core.R.drawable.anilibria
                )
                val nameTitle = if (forEachItem.franchises.isNotEmpty()) {
                    forEachItem.franchises[0].franchise.name
                } else {
                    forEachItem.names.ru
                }

                val ordinal =
                    if (forEachItem.franchises.isNotEmpty() && forEachItem.franchises.last().releases.isNotEmpty()) {
                        forEachItem.franchises.last().releases.last().ordinal
                    } else {
                        null
                    }

                val itemAnimeModel = ItemAnimeModel(
                    "https://anilibria.tv" + forEachItem.posters.original.url,
                    nameTitle,
                    forEachItem.names.en,
                    genre = forEachItem.genres,
                    ordinal = ordinal,
                    voice = mutableStateListOf(voiceModel)
                )

                val listCurrentItem =
                    allAnimeList.find {
                        levenshteinDistance( it.nameRu.trimEnd(),nameTitle.trimEnd()) >= 0.5 ||
                                levenshteinDistance(it.nameEng.trimEnd(),forEachItem.names.en.trimEnd()) >= 0.5
                    }
                if (listCurrentItem == null) {
                    allAnimeList.add(itemAnimeModel)
                } else {
                    val voiceAnimeVost =
                        listCurrentItem.voice.find { it.voice.trimEnd() == voiceModel.voice.trimEnd() }
                    if (voiceAnimeVost == null) {
                        listCurrentItem.voice.add(voiceModel)
                    }
                }
            }
        }
    }
    private fun levenshteinDistance(s1: String, s2: String): Double {
        val m = s1.length
        val n = s2.length
        val dp = Array(m + 1) { IntArray(n + 1) }

        for (i in 0..m) {
            for (j in 0..n) {
                when {
                    i == 0 -> dp[i][j] = j
                    j == 0 -> dp[i][j] = i
                    s1[i - 1] == s2[j - 1] -> dp[i][j] = dp[i - 1][j - 1]
                    else -> dp[i][j] = 1 + minOf(dp[i - 1][j], dp[i][j - 1], dp[i - 1][j - 1])
                }
            }
        }
        val result = dp[m][n]
        val maxLength = maxOf(s1.length, s2.length)

        val similarity = 1.0 - result.toDouble() / maxLength.toDouble()
        return similarity
    }
}
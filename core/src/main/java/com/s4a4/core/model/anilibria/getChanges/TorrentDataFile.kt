package com.s4a4.core.model.anilibria.getChanges

data class TorrentDataFile(
    val downloads: Int,
    val episodes: Episodes,
    val hash: String,
    val leechers: Int,
    val magnet: String,
    val metadata: Any?,
    val quality: Quality,
    val raw_base64_file: Any?,
    val seeders: Int,
    val size_string: String,
    val torrent_id: Int,
    val total_size: Long,
    val uploaded_timestamp: Int,
    val url: String
)
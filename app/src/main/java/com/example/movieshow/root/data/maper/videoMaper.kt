package com.example.movieshow.root.data.maper

import com.example.movieshow.root.data.movieExtraDetails.remote.respond.extraDetailMovie.video.VideoResult
import com.example.movieshow.root.data.movieExtraDetails.remote.respond.extraDetailTv.video.TvVideoResult
import com.example.movieshow.root.domain.model.TvVideos
import com.example.movieshow.root.domain.model.Video

fun VideoResult.toVideo():Video{
    return Video(
        id = id,
        iso_3166_1 = iso_3166_1,
        iso_639_1 = iso_639_1,
        key = key,
        name = name,
        official = official,
        published_at = published_at,
        site = site,
        size = size,
        type = type
    )
}

fun TvVideoResult.toTvVideo():TvVideos{
    return TvVideos(
        id = id,
        iso_3166_1 = iso_3166_1,
        iso_639_1 = iso_639_1,
        key = key,
        name = name,
        official = official,
        published_at = published_at,
        site = site,
        size = size,
        type = type
    )
}
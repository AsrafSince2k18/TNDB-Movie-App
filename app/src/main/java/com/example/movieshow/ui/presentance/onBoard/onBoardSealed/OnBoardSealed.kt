package com.example.movieshow.ui.presentance.onBoard.onBoardSealed

import com.example.movieshow.R

sealed class OnBoardSealed (
    val title : String,
    val description : String,
    val image : Int
){
    object FirstScreen : OnBoardSealed(
        title = "Movie Show",
        description = "Embark on a cinematic adventure with FilmFinder, your go-to destination for exploring, searching, and immersing yourself in the world of movies. Uncover a vast array of films across genres, from timeless classics to the latest releases.",
        image = R.raw.movie
    )
    object SecondScreen : OnBoardSealed(
        title = "Video Play",
        description = "Dive into an extensive collection of movies, carefully curated for every taste and preference. Let FilmFinder be your guide in discovering hidden gems and cinematic masterpieces.",
        image = R.raw.tv
    )
    object ThirdScreen : OnBoardSealed(
        title = "Discover",
        description = "Dive into an extensive collection of movies, carefully curated for every taste and preference. Let FilmFinder be your guide in discovering hidden gems and cinematic masterpieces.",
        image = R.raw.discover
    )

}
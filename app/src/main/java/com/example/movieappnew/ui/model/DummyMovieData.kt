package com.example.movieappnew.ui.model

import com.example.movieappnew.R

object DummyMovieData {
    val movies = arrayListOf(
        Movie(
            title = "Harry Potter and the Sorcerer's Stone",
            description = "An orphaned boy enrolls in a school of wizardry, where he learns the truth about himself, his family and the terrible evil that haunts the magical world. An orphaned boy enrolls in a school of wizardry, where he learns the truth about himself, his family and the terrible evil that haunts the magical world. An orphaned boy enrolls in a school of wizardry, where he learns the truth about himself, his family and the terrible evil that haunts the magical world. An orphaned boy enrolls in a school of wizardry, where he learns the truth about himself, his family and the terrible evil that haunts the magical world.",
            rating = 4.6,
            genre = "Fantasy",
            director = "Chris Columbus",
            releaseYear = 2001,
            posterResId = R.drawable.harry_potter,
            id = 1,
            posterPath = "",
            isFavorite = false
        ),
        Movie(
            title = "Frozen II",
            description = "Anna, Elsa, Kristoff, Olaf and Sven leave Arendelle to travel to an ancient, autumn-bound forest of an enchanted land. They set out to find the origin of Elsa's powers in order to save their kingdom.",
            rating = 4.5,
            genre = "Animation",
            director = "Chris Buck, Jennifer Lee",
            releaseYear = 2019,
            posterResId = R.drawable.frozen_2,
            id = 2,
            posterPath = "",
            isFavorite = false
        ),
        Movie(
            title = "Knives Out",
            description = "A detective investigates the death of a patriarch of an eccentric, combative family.",
            rating = 4.4,
            genre = "Mystery",
            director = "Rian Johnson",
            releaseYear = 2019,
            posterResId = R.drawable.knives_out,
            id = 3,
            posterPath = "",
            isFavorite = false
        ),
        Movie(
            title = "Pirates of the Caribbean: The Curse of the Black Pearl",
            description = "Blacksmith Will Turner teams up with eccentric pirate Captain Jack Sparrow to save his love, Elizabeth Swann, from Jack's former pirate allies, who are now undead.",
            rating = 4.7,
            genre = "Fantasy",
            director = "Gore Verbinski",
            releaseYear = 2003,
            posterResId = R.drawable.pirates_of_the_caribbean,
            id = 4,
            posterPath = "",
            isFavorite = false
        ),
    )
}
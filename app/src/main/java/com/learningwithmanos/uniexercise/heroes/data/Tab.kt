package com.learningwithmanos.uniexercise.heroes.data

sealed interface Tab {
    data object Heroes: Tab
    data object SortedByNameHeroes: Tab
    data object SortedByComicHeroes: Tab
}
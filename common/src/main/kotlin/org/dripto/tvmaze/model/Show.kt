package org.dripto.tvmaze.model

import java.io.Serializable
import java.time.LocalDate

data class Show(
    val id: Long,
    val url: String,
    val name: String,
    val language: String,
    val genres: List<String>,
    val status: String,
    val premiered: LocalDate,
    val ended: LocalDate?,
    val rating: Rating,
    val network: Network?,
    val externals: Externals,
    val image: Image,
    val summary: String,
    val updated: Long,
): Serializable

data class Rating(
    val average: Double,
): Serializable

data class Network(
    val name: String,
    val country: Country
): Serializable

data class Country(
    val name: String,
    val code: String
): Serializable

data class Externals(
    val tvrage: Long,
    val thetvdb: Long,
    val imdb: String,
): Serializable

data class Image(
    val medium: String,
    val original: String,
): Serializable
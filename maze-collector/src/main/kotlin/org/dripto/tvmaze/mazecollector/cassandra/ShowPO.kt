package org.dripto.tvmaze.mazecollector.cassandra

import org.dripto.tvmaze.model.Country
import org.dripto.tvmaze.model.Externals
import org.dripto.tvmaze.model.Image
import org.dripto.tvmaze.model.Network
import org.dripto.tvmaze.model.Rating
import org.dripto.tvmaze.model.Show
import org.springframework.data.cassandra.core.cql.PrimaryKeyType
import org.springframework.data.cassandra.core.mapping.CassandraType
import org.springframework.data.cassandra.core.mapping.PrimaryKey
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn
import org.springframework.data.cassandra.core.mapping.Table
import org.springframework.data.cassandra.core.mapping.UserDefinedType
import java.io.Serializable
import java.time.LocalDate

@Table("shows")
data class ShowPO(
    @PrimaryKey
    val key: Key,
    val url: String,
    val name: String,
    val language: String,
    @CassandraType(type = CassandraType.Name.SET, typeArguments = [CassandraType.Name.TEXT])
    val genres: List<String>,
    val status: String,
    val ended: LocalDate?,
    val rating: RatingPO,
    val network: NetworkPO?,
    val externals: ExternalsPO,
    val image: ImagePO,
    val summary: String,
    val updated: Long,
) {
    @PrimaryKeyClass
    class Key(
        @PrimaryKeyColumn(name = "show_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
        val id: Long,
        @PrimaryKeyColumn(ordinal = 1, type = PrimaryKeyType.CLUSTERED)
        val premiered: LocalDate,
    ) : Serializable
}

@UserDefinedType("rating")
data class RatingPO(
    val average: Double,
)

@UserDefinedType("network")
data class NetworkPO(
    val name: String,
    val country: CountryPO
)

@UserDefinedType("country")
data class CountryPO(
    val name: String,
    val code: String
)

@UserDefinedType("externals")
data class ExternalsPO(
    val tvrage: Long,
    val thetvdb: Long,
    val imdb: String,
)

@UserDefinedType("image")
data class ImagePO(
    val medium: String,
    val original: String,
)

fun Show.po(): ShowPO = ShowPO(
    key = ShowPO.Key(id, premiered),
    url = url,
    name = name,
    language = language,
    genres = genres,
    status = status, ended = ended,
    rating = rating.po(),
    network = network?.po(),
    externals = externals.po(),
    image = image.po(),
    summary = summary,
    updated = updated,
)

fun Rating.po() = RatingPO(average)
fun Network.po() = NetworkPO(name, country.po())
fun Country.po() = CountryPO(name, code)
fun Externals.po() = ExternalsPO(tvrage, thetvdb, imdb)
fun Image.po() = ImagePO(medium, original)
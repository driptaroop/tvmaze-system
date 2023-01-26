CREATE KEYSPACE IF NOT EXISTS tvmaze WITH replication = {'class': 'SimpleStrategy', 'replication_factor': '1'};

CREATE TYPE IF NOT EXISTS tvmaze.Rating(
    average DOUBLE
    );

CREATE TYPE IF NOT EXISTS tvmaze.Country(
    name TEXT,
    code TEXT
    );

CREATE TYPE IF NOT EXISTS tvmaze.Network(
    name TEXT,
    country FROZEN<Country>
    );

CREATE TYPE IF NOT EXISTS tvmaze.Externals(
    tvrage BIGINT,
    thetvdb BIGINT,
    imdb TEXT
    );

CREATE TYPE IF NOT EXISTS tvmaze.Image(
    medium TEXT,
    original TEXT,
    );

CREATE TABLE IF NOT EXISTS tvmaze.shows
(
    show_id   BIGINT,
    premiered DATE,
    url       TEXT,
    name      TEXT,
    language  TEXT,
    genres    List<TEXT>,
    status    TEXT,
    ended     DATE,
    rating    FROZEN<Rating>,
    network   FROZEN<Network>,
    externals FROZEN<Externals>,
    image     FROZEN<Image>,
    summary   TEXT,
    updated   BIGINT,
    PRIMARY KEY ( (show_id), premiered )
) WITH CLUSTERING ORDER BY (premiered DESC);
package sample.Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Datasource {
    public static final String DB_NAME = "music.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:/Users/CHI/project/JAVA/MusicUI/" + DB_NAME;
    public static final String TABLE_ALBUMS = "albums";
    public static final String COLUMN_ALBUM_ID = "_id";
    public static final String COLUMN_ALBUM_NAME = "name";
    public static final String COLUMN_ALBUM_ARTIST = "artist";
    public static final int INDEX_ALBUM_ID = 1;
    public static final int INDEX_ALBUM_NAME = 2;
    public static final int INDEX_ALBUM_ARTIST = 3;

    public static final String TABLE_ARTISTS = "artists";
    public static final String COLUMN_ARTIST_ID = "_id";
    public static final String COLUMN_ARTIST_NAME = "name";
    public static final int INDEX_ARTIST_ID = 1;
    public static final int INDEX_ARTIST_NAME = 2;

    public static final String TABLE_SONGS = "songs";
    public static final String COLUMN_SONG_ID = "_id";
    public static final String COLUMN_SONG_TRACK = "track";
    public static final String COLUMN_SONG_TITLE = "title";
    public static final String COLUMN_SONG_ALBUM = "album";
    public static final int INDEX_SONG_ID = 1;
    public static final int INDEX_SONG_TRACK = 2;
    public static final int INDEX_SONG_TITLE = 3;
    public static final int INDEX_SONG_ALBUM = 4;

    public static final int ORDER_BY_NONE = 1;
    public static final int ORDER_BY_ASC = 2;
    public static final int ORDER_BY_DESC = 3;
    /* SELECT albums.name FROM albums INNER JOIN artists ON albums.artist=artists._id
     * WHERE artists.name="Carole King" */
    public static final String QUERY_ALBUMS_BY_ARTIST_START =
            "SELECT " + TABLE_ALBUMS + '.' + COLUMN_ALBUM_NAME +
                    " FROM " + TABLE_ALBUMS + " INNER JOIN " + TABLE_ARTISTS + " ON " + TABLE_ALBUMS + '.' + COLUMN_ALBUM_ARTIST +
                    " = " + TABLE_ARTISTS + '.' + COLUMN_ARTIST_ID +
                    " WHERE " + TABLE_ARTISTS + '.' + COLUMN_ARTIST_NAME + " = \"";

    /* ORDER BY albums.name COLLATE NOCASE ASC*/
    public static final String QUERY_ALBUMS_BY_ARTIST_SORT =
            " ORDER BY " + TABLE_ALBUMS + '.' + COLUMN_ALBUM_NAME + " COLLATE NOCASE ";

    /*SELECT artists.name, albums.name, songs.track FROM albums
        INNER JOIN artists ON albums.artist=artists._id
        INNER JOIN songs ON songs.album=albums._id
        WHERE  songs.title="Go Your Own Way"*/
    public static final String QUERY_ARTIST_FOR_SONG_START =
            "SELECT " + TABLE_ARTISTS + '.' + COLUMN_ARTIST_NAME + ',' +
                    TABLE_ALBUMS + '.' + COLUMN_ALBUM_NAME + ',' +
                    TABLE_SONGS + '.' + COLUMN_SONG_TRACK + " FROM " + TABLE_ALBUMS +
                    " INNER JOIN " + TABLE_ARTISTS + " ON " + TABLE_ALBUMS + '.' + COLUMN_ALBUM_ARTIST + " = " + TABLE_ARTISTS + '.' + COLUMN_ARTIST_ID +
                    " INNER JOIN " + TABLE_SONGS + " ON " + TABLE_SONGS + '.' + COLUMN_SONG_ALBUM + " = " + TABLE_ALBUMS + '.' + COLUMN_ALBUM_ID +
                    " WHERE " + TABLE_SONGS + '.' + COLUMN_SONG_TITLE + " =\"";

    /*ORDER BY artists.name, albums.name COLLATE NOCASE ASC */
    public static final String QUERY_ARTIST_FOR_SONG_SORT =
            " ORDER BY " + TABLE_ARTISTS + '.' + COLUMN_ARTIST_NAME + ',' +
                    TABLE_ALBUMS + '.' + COLUMN_ALBUM_NAME + " COLLATE NOCASE ";

    /*CREATE VIEW IF NOT EXISTS artists_list AS
    SELECT artists.name AS artist, albums.name AS album, songs.track, songs.title FROM songs
    INNER JOIN artists ON albums.artist=artists._id
    INNER JOIN albums ON songs.album=albums._id
    ORDER BY artists.name, albums.name, songs.track;*/
    public static final String TABLE_ARTIST_SONG_VIEW = "artist_list";
    public static final String CREATE_ARTIST_FOR_SONG_VIEW = "CREATE VIEW IF NOT EXISTS " +
            TABLE_ARTIST_SONG_VIEW + " AS SELECT "
            + TABLE_ARTISTS + '.' + COLUMN_ARTIST_NAME + " AS " + COLUMN_ALBUM_ARTIST + ',' +
            TABLE_ALBUMS + '.' + COLUMN_ALBUM_NAME + " AS " + COLUMN_SONG_ALBUM + ',' +
            TABLE_SONGS + '.' + COLUMN_SONG_TRACK + ',' + TABLE_SONGS + '.' + COLUMN_SONG_TITLE +
            " FROM " + TABLE_SONGS +
            " INNER JOIN " + TABLE_ARTISTS + " ON " + TABLE_ALBUMS + '.' + COLUMN_ALBUM_ARTIST + " = " + TABLE_ARTISTS + '.' + COLUMN_ARTIST_ID +
            " INNER JOIN " + TABLE_ALBUMS + " ON " + TABLE_SONGS + '.' + COLUMN_SONG_ALBUM + " = " + TABLE_ALBUMS + '.' + COLUMN_ALBUM_ID +
            " ORDER BY " + TABLE_ARTISTS + '.' + COLUMN_ARTIST_NAME + ',' +
            TABLE_ALBUMS + '.' + COLUMN_ALBUM_NAME + ',' + TABLE_SONGS + '.' + COLUMN_SONG_TRACK;

    //SELECT artist, album,track FROM artist_list WHERE title ="Go Your Own Way" //use Statement
    public static final String QUERY_VIEW_SONG_INFO = "SELECT "
            + COLUMN_ALBUM_ARTIST + ',' + COLUMN_SONG_ALBUM + ',' + COLUMN_SONG_TRACK + " FROM " + TABLE_ARTIST_SONG_VIEW +
            " WHERE " + COLUMN_SONG_TITLE + " =\"";

    // SELECT artist, album, track FROM artist_list WHERE title = ? // use PreparedStatement
    public static final String QUERY_VIEW_SONG_INFO_PREP = "SELECT "
            + COLUMN_ALBUM_ARTIST + ',' + COLUMN_SONG_ALBUM + ',' + COLUMN_SONG_TRACK + " FROM " + TABLE_ARTIST_SONG_VIEW +
            " WHERE " + COLUMN_SONG_TITLE + " = ?";

    // INSERT artists = INSERT INTO artists (name) VALUES (?) // use PreparedStatement
    public static final String INSERT_ARTIST = "INSERT INTO " + TABLE_ARTISTS + '(' + COLUMN_ARTIST_NAME + ") VALUES(?)";

    // INSERT albums = INSERT INTO albums (name, artist) VALUES (?,?) // use PreparedStatement
    public static final String INSERT_ALBUMS = "INSERT INTO " + TABLE_ALBUMS + '(' + COLUMN_ALBUM_NAME + ',' + COLUMN_ALBUM_ARTIST
            + ") VALUES(?,?)";

    // INSERT songs = INSERT INTO songs (tack, title, album) VALUES (?,?,?) // use PreparedStatement
    public static final String INSERT_SONGS = "INSERT INTO " + TABLE_SONGS + '(' + COLUMN_SONG_TRACK + ',' + COLUMN_SONG_TITLE + ',' + COLUMN_SONG_ALBUM
            + ") VALUES(?,?,?)";

    // SELECT artist_id FROM artists WHERE name = ?
    public static final String QUERY_ARTIST = "SELECT " + COLUMN_ARTIST_ID + " FROM " +
            TABLE_ARTISTS + " WHERE " + COLUMN_ARTIST_NAME + " =?";
    // SELECT album_id FROM albums WHERE name = ?
    public static final String QUERY_ALBUM = "SELECT " + COLUMN_ALBUM_ID + " FROM " +
            TABLE_ALBUMS + " WHERE " + COLUMN_ALBUM_NAME + " =?";

    // SELECT * FROM ALBUMS WHERE artist = ? ORDER BY name COLLATE NOCASE
    public static final String QUERY_ALBUMS_BY_ARTIST_ID = "SELECT * FROM " + TABLE_ALBUMS + " WHERE " +
            COLUMN_ALBUM_ARTIST + " = ? ORDER BY " + COLUMN_ALBUM_NAME + " COLLATE NOCASE";

    // UPDATE artists SET name = ? WHERE _id = ?
    public static final String UPDATE_ARTIST_NAME = "UPDATE " + TABLE_ARTISTS + " SET " +
            COLUMN_ARTIST_NAME + " = ? WHERE " + COLUMN_ARTIST_ID + " = ? ";

    private Connection conn;

    private PreparedStatement querySongInfoView; // need to be open and close

    private PreparedStatement insertIntoArtists; // need to be open and close
    private PreparedStatement insertIntoAlbums; // need to be open and close
    private PreparedStatement insertIntoSongs; // need to be open and close

    private PreparedStatement queryArtist; // need to be open and close
    private PreparedStatement queryAlbum; // need to be open and close
    private PreparedStatement queryAlbumsByArtistId; // need to be open and close
    private PreparedStatement updateArtistName; // need to be open and close

    private static Datasource instance = new Datasource();

    private Datasource() {
    }

    public static Datasource getInstance() {
        return instance;
        // Datasource.getInstance().methodName();
    }

    public boolean open() {
        try {
            conn = DriverManager.getConnection(CONNECTION_STRING);
            querySongInfoView = conn.prepareStatement(QUERY_VIEW_SONG_INFO_PREP);
            insertIntoArtists = conn.prepareStatement(INSERT_ARTIST, Statement.RETURN_GENERATED_KEYS);
            insertIntoAlbums = conn.prepareStatement(INSERT_ALBUMS, Statement.RETURN_GENERATED_KEYS);
            insertIntoSongs = conn.prepareStatement(INSERT_SONGS);
            queryArtist = conn.prepareStatement(QUERY_ARTIST);
            queryAlbum = conn.prepareStatement(QUERY_ALBUM);
            queryAlbumsByArtistId = conn.prepareStatement(QUERY_ALBUMS_BY_ARTIST_ID);
            updateArtistName = conn.prepareStatement(UPDATE_ARTIST_NAME);
            return true;
        } catch (SQLException e) {
            System.out.println("Couldn't connect to database" + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public void close() {
        try {
            if (querySongInfoView != null) {
                querySongInfoView.close();
            }
            if (insertIntoArtists != null) {
                insertIntoArtists.close();
            }
            if (insertIntoAlbums != null) {
                insertIntoAlbums.close();
            }
            if (insertIntoSongs != null) {
                insertIntoSongs.close();
            }
            if (queryArtist != null) {
                queryArtist.close();
            }
            if (queryAlbum != null) {
                queryAlbum.close();
            }
            if (queryAlbumsByArtistId != null) {
                queryAlbumsByArtistId.close();
            }
            if (updateArtistName != null) {
                updateArtistName.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("Couldn't close connection" + e.getMessage());
//            e.printStackTrace();
        }
    }

    public List<Artist> queryArtists(int sortOrder) {
        StringBuilder sb = new StringBuilder("SELECT * FROM ");
        sb.append(TABLE_ARTISTS);
        if (sortOrder != ORDER_BY_NONE) {
            sb.append(" ORDER BY ");
            sb.append(COLUMN_ARTIST_NAME);
            sb.append(" COLLATE NOCASE ");
            if (sortOrder == ORDER_BY_DESC) { // =3
                sb.append("DESC");
            } else {
                sb.append("ASC");
            }
        }
        try (Statement statement = conn.createStatement();
//             ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_ARTISTS)) {
             ResultSet resultSet = statement.executeQuery(sb.toString())) {
            List<Artist> artists = new ArrayList<>();
            while (resultSet.next()) {
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    System.out.println("Interrupted: " + e.getMessage());
                }
                Artist artist = new Artist();
//                artist.setId(resultSet.getInt(COLUMN_ARTIST_ID));
                artist.setId(resultSet.getInt(INDEX_ARTIST_ID));
//                artist.setName(resultSet.getString(COLUMN_ARTIST_NAME));
                artist.setName(resultSet.getString(INDEX_ARTIST_NAME));
                artists.add(artist);
            }
            return artists;
        } catch (SQLException e) {
            System.out.println("Query failed" + e.getMessage());
            return null;
        }
    }

    public List<Album> queryAlbumsForArtistId(int id) { //356
        try {
            queryAlbumsByArtistId.setInt(1, id);
            ResultSet resultSet = queryAlbumsByArtistId.executeQuery();

            List<Album> albums = new ArrayList<>();
            while (resultSet.next()) {
                Album album = new Album();
                album.setId(resultSet.getInt(1));
                album.setName(resultSet.getString(2));
                album.setArtistId(id);
                albums.add(album);
            }
            return albums;
        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }

    public List<String> queryAlbumsForArtist(String artistName, int sortOrder) {
        StringBuilder sb = new StringBuilder(QUERY_ALBUMS_BY_ARTIST_START);
        sb.append(artistName);
        sb.append("\"");
        if (sortOrder != ORDER_BY_NONE) {
            sb.append(QUERY_ALBUMS_BY_ARTIST_SORT);
            if (sortOrder == ORDER_BY_DESC) {
                sb.append("DESC");
            } else {
                sb.append("ASC");
            }
        }
        System.out.println("SQL statement = " + sb.toString());

        try (Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(sb.toString())) {

            List<String> albums = new ArrayList<>();
            while (resultSet.next()) {
                albums.add(resultSet.getString(1));
            }
            return albums;
        } catch (SQLException e) {
            System.out.println("Query failed " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }


    public void querySongMetadata() { // like .schema
        String sql = "SELECT * FROM " + TABLE_SONGS;

        try (Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            ResultSetMetaData meta = resultSet.getMetaData();
            int numColumns = meta.getColumnCount();
            for (int i = 1; i <= numColumns; i++) {
                System.out.format("Column %d in the songs table is names %s\n", i, meta.getColumnName(i));
            }
        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public int getCount(String table) { // get the count in the table
//        String sql = "SELECT COUNT (*), MIN (_id) FROM " + table;
        String sql = "SELECT COUNT (*) AS count FROM " + table; // it would be better tp set the name
        try (Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

//            int count = resultSet.getInt(1);
            int count = resultSet.getInt("count");
            System.out.format("Count = %d\n", count);
            return count;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return -1;
        }
    }

    public boolean createViewForSongArtists() {
        try (Statement statement = conn.createStatement()) {
            statement.execute(CREATE_ARTIST_FOR_SONG_VIEW);
            System.out.println("View created\n" + "SQL CODE: " + CREATE_ARTIST_FOR_SONG_VIEW);
            return true;
        } catch (SQLException e) {
            System.out.println("Create View failed: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

//    public List<SongArtist> querySongInfoView(String title) { // use statement Method
//        StringBuilder sb = new StringBuilder(QUERY_VIEW_SONG_INFO);
//        sb.append(title);
//        sb.append("\"");
//        System.out.println(sb.toString());
//
//        try (Statement statement = conn.createStatement();
//             ResultSet resultSet = statement.executeQuery(sb.toString())) {
//            List<SongArtist> songArtists = new ArrayList<>();
//            while (resultSet.next()) {
//                SongArtist songArtist = new SongArtist();
//                songArtist.setArtistName(resultSet.getString(1));
//                songArtist.setAlbumName(resultSet.getString(2));
//                songArtist.setTrack(resultSet.getInt(3));
//                songArtists.add(songArtist);
//            }
//            return songArtists;
//        } catch (SQLException e) {
//            System.out.println("Query failed: " + e.getMessage());
//            return null;
//        }
//    }

    // 358
    public boolean updateArtistName(int id, String newName) {
        try {
            // UPDATE artists SET name = ? WHERE _id = ?
            updateArtistName.setString(1, newName);
            updateArtistName.setInt(2, id);
            int affectedRecords = updateArtistName.executeUpdate();
            return affectedRecords == 1; //return true
        } catch (SQLException e) {
            System.out.println("Update failed: " + e.getMessage());
            return false;
        }
    }

    private int insertArtist(String name) throws SQLException {
        queryArtist.setString(1, name);
        ResultSet resultSet = queryArtist.executeQuery();
        System.out.println("artist ID: " + resultSet.getInt(1));
        if (resultSet.next()) { // detect data existed or not
            return resultSet.getInt(1);
        } else {
            // insert the artist
            insertIntoArtists.setString(1, name); // "INSERT INTO " artists (name)
            int affectedRows = insertIntoArtists.executeUpdate(); // Returns an integer
            if (affectedRows != 1) {
                throw new SQLException("Couldn't insert artist !");
            }
            ResultSet generatedKeys = insertIntoArtists.getGeneratedKeys();
            System.out.println("The new artist ID: " + generatedKeys.getInt(1));
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            } else {
                throw new SQLException("Couldn't get _id for artist");
            }
        }
    }

    private int insertAlbum(String name, int artistId) throws SQLException {
        queryAlbum.setString(1, name);
        ResultSet resultSet = queryAlbum.executeQuery();
        System.out.println("album ID: " + resultSet.getInt(1));
        if (resultSet.next()) {
            return resultSet.getInt(1);
        } else {
            // insert the album
            insertIntoAlbums.setString(1, name); // INSERT INTO " albums (name, artist)
            insertIntoAlbums.setInt(2, artistId);
            int affectedRows = insertIntoAlbums.executeUpdate(); // Returns an integer

            if (affectedRows != 1) {
                throw new SQLException("Couldn't insert album !");
            }

            ResultSet generatedKeys = insertIntoAlbums.getGeneratedKeys();
//            System.out.println("The new album ID: " + generatedKeys.getInt(1));
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            } else {
                throw new SQLException("Couldn't get _id for album");
            }
        }
    }

    public void insertSong(String title, String artist, String album, int track) {
        try {
            conn.setAutoCommit(false); // need to set to false for "rollback" conduct

            int artistId = insertArtist(artist);
            int albumId = insertAlbum(album, artistId);
            insertIntoSongs.setInt(1, track); // "INSERT INTO " songs (tack, title, album)
            insertIntoSongs.setString(2, title);
            insertIntoSongs.setInt(3, albumId);
            int affectedRows = insertIntoSongs.executeUpdate(); // Returns an integer
//            System.out.println("affectedRow: " + affectedRows);
            if (affectedRows == 1) {
                System.out.println("Song added");
                conn.commit();
            } else {
                throw new SQLException("The song insert failed");
            }
        } catch (SQLException e) {
            System.out.println("Insert song exception: " + e.getMessage());
            try {
                System.out.println("Performing rollback");
                conn.rollback();
            } catch (SQLException e2) {
                System.out.println("Oh boy ! Things are really bad!  " + e2.getMessage());
            }
        } finally {
            try {
                System.out.println("Resetting default commit behavior");
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                System.out.println("Couldn't reset auto-commit " + e.getMessage());
            }
        }
    }

}


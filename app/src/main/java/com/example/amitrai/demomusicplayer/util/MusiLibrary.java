package com.example.amitrai.demomusicplayer.util;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.BaseColumns;
import android.provider.MediaStore;

import com.example.amitrai.demomusicplayer.modals.Album;
import com.example.amitrai.demomusicplayer.modals.Artist;
import com.example.amitrai.demomusicplayer.modals.Playlist;
import com.example.amitrai.demomusicplayer.modals.Song;

import java.util.ArrayList;

/**
 * Created by amitrai on 23/12/16.
 */

public class MusiLibrary {

    ContentResolver resolver;

    public MusiLibrary(ContentResolver resolver) {
        this.resolver = resolver;
    }

    private final Cursor makeArtistCursor() {
        return resolver.query(MediaStore.Audio.Artists.EXTERNAL_CONTENT_URI,
                new String[] {
          /* 0 */ BaseColumns._ID,
          /* 1 */ MediaStore.Audio.ArtistColumns.ARTIST,
          /* 2 */ MediaStore.Audio.ArtistColumns.NUMBER_OF_ALBUMS,
          /* 3 */ MediaStore.Audio.ArtistColumns.NUMBER_OF_TRACKS
                }, null, null, MediaStore.Audio.Artists.DEFAULT_SORT_ORDER);
    }

    public ArrayList<Artist> getArtists() {
        ArrayList<Artist> mArtistsList = new ArrayList<Artist>();
        Cursor mCursor = makeArtistCursor();
        if (mCursor != null && mCursor.moveToFirst()) {
            do {
                final long id = mCursor.getLong(0);
                final String artistName = mCursor.getString(1);
                final int albumCount = mCursor.getInt(2);
                final int songCount = mCursor.getInt(3);
                final Artist artist = new Artist(id, artistName, songCount, albumCount);
                mArtistsList.add(artist);
            } while (mCursor.moveToNext());
        }
        if (mCursor != null) {
            mCursor.close();
            mCursor = null;
        }
        return mArtistsList;
    }

    private final Cursor makeAlbumCursor() {
        return resolver.query(MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI,
                new String[] {
          /* 0 */ BaseColumns._ID,
          /* 1 */ MediaStore.Audio.AlbumColumns.ALBUM,
          /* 2 */ MediaStore.Audio.AlbumColumns.ARTIST,
          /* 3 */ MediaStore.Audio.AlbumColumns.NUMBER_OF_SONGS,
          /* 4 */ MediaStore.Audio.AlbumColumns.FIRST_YEAR
                }, null, null, MediaStore.Audio.Albums.DEFAULT_SORT_ORDER);
    }

    private final Cursor makeArtistAlbumCursor(final Long artistId) {
        return resolver.query(MediaStore.Audio.Artists.Albums.getContentUri("external", artistId),
                new String[] {
          /* 0 */ BaseColumns._ID,
          /* 1 */ MediaStore.Audio.AlbumColumns.ALBUM,
          /* 2 */ MediaStore.Audio.AlbumColumns.ARTIST,
          /* 3 */ MediaStore.Audio.AlbumColumns.NUMBER_OF_SONGS,
          /* 4 */ MediaStore.Audio.AlbumColumns.FIRST_YEAR
                }, null, null, MediaStore.Audio.Albums.DEFAULT_SORT_ORDER);
    }

    public ArrayList<Album> getAlbums(final Long artistId) {
        ArrayList<Album> mAlbumList = new ArrayList<Album>();
        Cursor mCursor;
        if (artistId != null) {
            mCursor = makeArtistAlbumCursor(artistId);
        } else {
            mCursor = makeAlbumCursor();
        }
        if (mCursor != null && mCursor.moveToFirst()) {
            do {
                final long id = mCursor.getLong(0);
                final String albumName = mCursor.getString(1);
                final String artist = mCursor.getString(2);
                final int songCount = mCursor.getInt(3);
                final String year = mCursor.getString(4);
                final Album album = new Album(id, albumName, artist, songCount, year);
                mAlbumList.add(album);
            } while (mCursor.moveToNext());
        }
        if (mCursor != null) {
            mCursor.close();
            mCursor = null;
        }
        return mAlbumList;
    }

    private final Cursor makePlaylistCursor() {
        return resolver.query(MediaStore.Audio.Playlists.EXTERNAL_CONTENT_URI,
                new String[] {
          /* 0 */ BaseColumns._ID,
          /* 1 */ MediaStore.Audio.PlaylistsColumns.NAME
                }, null, null, MediaStore.Audio.Playlists.DEFAULT_SORT_ORDER);
    }

    public ArrayList<Playlist> getPlaylists() {
        ArrayList<Playlist> mPlaylistList = new ArrayList<Playlist>();
        Cursor mCursor = makePlaylistCursor();
        if (mCursor != null && mCursor.moveToFirst()) {
            do {
                final long id = mCursor.getLong(0);
                final String playlistName = mCursor.getString(1);
                final Playlist playlist = new Playlist(id, playlistName);
                mPlaylistList.add(playlist);
            } while (mCursor.moveToNext());
        }
        if (mCursor != null) {
            mCursor.close();
            mCursor = null;
        }
        return mPlaylistList;
    }

    private final Cursor makeArtistSongCursor(final Long artistId) {
        final StringBuilder selection = new StringBuilder();
        selection.append(MediaStore.Audio.AudioColumns.IS_MUSIC + "=1");
        selection.append(" AND " + MediaStore.Audio.AudioColumns.TITLE + " != ''");
        selection.append(" AND " + MediaStore.Audio.AudioColumns.ARTIST_ID + "=" + artistId);
        return resolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                new String[] {
          /* 0 */ BaseColumns._ID,
          /* 1 */ MediaStore.Audio.AudioColumns.TITLE,
          /* 2 */ MediaStore.Audio.AudioColumns.ARTIST,
          /* 3 */ MediaStore.Audio.AudioColumns.ALBUM
                }, selection.toString(), null, MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
    }

    public ArrayList<Song> getArtistSongs(final Long artistId) {
        ArrayList<Song> mSongList = new ArrayList<Song>();
        Cursor mCursor = makeArtistSongCursor(artistId);
        if (mCursor != null && mCursor.moveToFirst()) {
            do {
                final long id = mCursor.getLong(0);
                final String songName = mCursor.getString(1);
                final String artist = mCursor.getString(2);
                final String album = mCursor.getString(3);
                final Song song = new Song(id, songName, artist, album, -1);
                mSongList.add(song);
            } while (mCursor.moveToNext());
        }
        if (mCursor != null) {
            mCursor.close();
            mCursor = null;
        }
        return mSongList;
    }

    private final Cursor makeAlbumSongCursor(final Long albumId) {
        final StringBuilder selection = new StringBuilder();
        selection.append(MediaStore.Audio.AudioColumns.IS_MUSIC + "=1");
        selection.append(" AND " + MediaStore.Audio.AudioColumns.TITLE + " != ''");
        selection.append(" AND " + MediaStore.Audio.AudioColumns.ALBUM_ID + "=" + albumId);
        return resolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                new String[] {
          /* 0 */ BaseColumns._ID,
          /* 1 */ MediaStore.Audio.AudioColumns.TITLE,
          /* 2 */ MediaStore.Audio.AudioColumns.ALBUM,
          /* 3 */ MediaStore.Audio.AudioColumns.ALBUM
                }, selection.toString(), null, MediaStore.Audio.Media.TRACK + ", " + MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
    }

    public ArrayList<Song> getAlbumSongs(final Long albumId) {
        ArrayList<Song> mSongList = new ArrayList<Song>();
        Cursor mCursor = makeAlbumSongCursor(albumId);
        if (mCursor != null && mCursor.moveToFirst()) {
            do {
                final long id = mCursor.getLong(0);
                final String songName = mCursor.getString(1);
                final String artist = mCursor.getString(2);
                final String album = mCursor.getString(3);
                final Song song = new Song(id, songName, artist, album, -1);
                mSongList.add(song);
            } while (mCursor.moveToNext());
        }
        if (mCursor != null) {
            mCursor.close();
            mCursor = null;
        }
        return mSongList;
    }

}

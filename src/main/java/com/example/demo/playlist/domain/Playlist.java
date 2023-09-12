package com.example.demo.playlist.domain;
import jakarta.persistence.*;
import java.util.List;

import com.example.demo.song.domain.Song;



@Entity
@Table(name = "playlist")
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String title;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "playlist_song",
            joinColumns = {@JoinColumn(name = "playlist_id")},
            inverseJoinColumns = {@JoinColumn(name = "song_id")}
    )
    private List<Song> songs;

    private String coverImage;

    public Playlist() {}

    public Playlist(Long id, String title, List<Song> songs, String coverImage) {
        this.id = id;
        this.title = title;
        this.songs = songs;
        this.coverImage = coverImage;
    }

    // Getters 
    public Long getId() {
        return this.id;
    }
    public String getTitle() {
        return this.title;
    }
    public List<Song> getSongs(){
        return this.songs;
    }
    public String getCoverImage(){
        return this.coverImage;
    }

    //setters

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }
        this.title = title;
    }

    public void setSongs(List<Song> songs){
        this.songs=songs;
    }

    public void setCoverImage(String coverImage){
        this.coverImage=coverImage;
    }


}
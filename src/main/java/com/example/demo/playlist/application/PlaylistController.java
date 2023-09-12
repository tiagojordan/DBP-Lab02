package com.example.demo.playlist.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.playlist.domain.PlaylistRepository;
//import com.example.demo.song.domain.Song;
import com.example.demo.playlist.domain.Playlist;


import java.util.List;
//import java.util.Optional;
import java.util.Optional;

@RestController
@RequestMapping("/playlist")
public class PlaylistController{

    @Autowired
    private PlaylistRepository playlistRepository;

    @GetMapping
    public ResponseEntity<List<Playlist>> playlists() {
        List<Playlist> playlists = playlistRepository.findAll();
        return new ResponseEntity<>(playlists, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> playlist(@RequestBody Playlist playlist){
        playlistRepository.save(playlist);
        return ResponseEntity.status(201).body("Created");
    }

   @PutMapping("/{id}")
    public ResponseEntity<String> updatePlaylist(@PathVariable Long id, @RequestBody Playlist newPlaylist) {
        Optional<Playlist> optionalPlaylist = playlistRepository.findById(id);
        if(optionalPlaylist.isPresent()) {
            Playlist existingPlaylist = optionalPlaylist.get();
            existingPlaylist.setTitle(newPlaylist.getTitle());
            existingPlaylist.setSongs(newPlaylist.getSongs());
            existingPlaylist.setCoverImage(newPlaylist.getCoverImage());
            playlistRepository.save(existingPlaylist);
            return ResponseEntity.status(200).body("Updated");
        } else {
            return ResponseEntity.status(404).body("Not Found");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSong(@PathVariable Long id) {
        Optional<Playlist> optionalSong = playlistRepository.findById(id);
        if(optionalSong.isPresent()) {
            playlistRepository.deleteById(id);
            return ResponseEntity.status(200).body("Deleted");
        } else {
            return ResponseEntity.status(404).body("Not Found");
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String>partialUpdateSong(@PathVariable Long id,@RequestBody Playlist newPlaylist) {
        Optional<Playlist>optionalPlaylist =playlistRepository.findById(id);
        if (optionalPlaylist.isPresent()){
                Playlist existingPlaylist=optionalPlaylist.get();
            if (newPlaylist.getTitle()!=null) {
                    existingPlaylist.setTitle(newPlaylist.getTitle());
                }
                playlistRepository.save(existingPlaylist);
            return ResponseEntity.status(200).body("Partially updated");
            }
        else{
                return ResponseEntity.status(404).body(" Not found");
            }
        
        }

}
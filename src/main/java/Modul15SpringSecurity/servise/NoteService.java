package Modul15SpringSecurity.servise;

import Modul15SpringSecurity.entity.Note;
import Modul15SpringSecurity.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoteService {
    private final NoteRepository noteRepository;

    public List<Note> allNote(int id) {
        return noteRepository.findByUserId(id);
    }

    public Note addNewNote(Note note, int userId) {
        note.setUserId(userId);
        return noteRepository.save(note);
    }

    public void deleteById(String id) {
        getById(id).ifPresent(noteRepository::delete);
    }

    public void update(Note note) {
        noteRepository.save(note);
    }

    public Optional<Note> getById(String id) {
        return noteRepository.findById(id);
    }

    public boolean isNoteExist(String id) {
        return getById(id).isPresent();
    }
}

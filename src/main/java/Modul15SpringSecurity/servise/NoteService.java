package Modul15SpringSecurity.servise;

import Modul15SpringSecurity.entity.Note;
import Modul15SpringSecurity.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class NoteService {
    private final NoteRepository noteRepository;

    public List<Note> allNote() {

        return (List<Note>) noteRepository.findAll();
    }

    public Note addNewNote(Note note) {

        return noteRepository.save(note);
    }

    public void deleteById(long id) {

        noteRepository.delete(getById(id));
    }

    public void update(Note note) {
        getById(note.getId());
        noteRepository.save(note);
    }


    public Note getById(long id) {
        Optional<Note> optionalNote = noteRepository.findById(id);
        if (optionalNote.isPresent()) {
            return optionalNote.get();
        } else {
            throw new IllegalArgumentException("No note found for id: " + id);
        }
    }

}

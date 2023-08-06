package Modul15SpringSecurity.repository;

import Modul15SpringSecurity.entity.Note;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends CrudRepository<Note, String> {
    List<Note>findByUserId(int userId);
}

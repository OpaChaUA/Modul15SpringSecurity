package Modul15SpringSecurity.controller;

import Modul15SpringSecurity.entity.Note;
import Modul15SpringSecurity.limitation.NoteValidator;
import Modul15SpringSecurity.servise.NoteService;
import Modul15SpringSecurity.servise.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Controller
@RequestMapping(value = {"/note", "/"})

public class NoteController {
    private final NoteService noteService;
    private final UserService userService;
    private final NoteValidator noteValidator;

    @GetMapping("/create")
    public ModelAndView showCreateForm(HttpSession session) {
        Note note = (Note) session.getAttribute("note");
        if (note == null) {
            return new ModelAndView("create")
                    .addObject("isEmpty", true);
        }
        return new ModelAndView("create")
                .addObject("note", note)
                .addObject("isEmpty", false);
    }


    @PostMapping("/create")
    public ModelAndView addNote(@ModelAttribute Note note, Principal principal, HttpSession session) {
        List<String> errorsList = noteValidator.validNote(note);
        if (errorsList.isEmpty()) {
            int usersId = userService.getIdByUsername(principal.getName());
            noteService.addNewNote(note, usersId);
            return new ModelAndView("redirect:/note/list");
        }
        session.setAttribute("note", note);
        return new ModelAndView("error")
                .addObject("bac", "/note/create")
                .addObject("errorMessage", errorsList);
    }


    @GetMapping("/list")
    public ModelAndView allNote(Principal principal, HttpSession session) {
        session.removeAttribute("note");
        int userId = userService.getIdByUsername(principal.getName());
        return new ModelAndView("list")
                .addObject("allNotes", noteService.allNote(userId));
    }

    @PostMapping("/delete")
    public ModelAndView deleteNote(@RequestParam("id") String id) {
        noteService.deleteById(id);
        return new ModelAndView("redirect:/note/list");

    }

    @GetMapping("/edit")
    public ModelAndView showEditPage(@RequestParam("id") String id, HttpSession session) {
        Note noteToEdit = (Note) session.getAttribute("note");
        if (noteToEdit == null) {
            Optional<Note> optionalNote = noteService.getById(id);
            if (optionalNote.isPresent()) {
                return new ModelAndView("edit")
                        .addObject("note", optionalNote.get());
            } else {
                return new ModelAndView("redirect:/note/list");
            }
        }
        return new ModelAndView("edit")
                .addObject("note", noteToEdit);
    }


    @PostMapping("/edit")
    public ModelAndView editNote(@ModelAttribute("note") Note note, HttpSession session) {
        ModelAndView result = new ModelAndView("redirect:/note/list");
        List<String> errorsList = noteValidator.validNote(note);
        if (!noteService.isNoteExist(note.getId())) {
            return result;
        }
        if (errorsList.isEmpty()) {
            noteService.update(note);
            return result;
        }
        session.setAttribute("note", note);
        return new ModelAndView("error")
                .addObject("bac", "/note/edit?id=" + note.getId())
                .addObject("errorMessage", errorsList);
    }

    @GetMapping("/")
    public String redirectToNoteList() {
        return "redirect:/note/list";
    }

    @GetMapping("/share/{id}")
    public ModelAndView showSharePage1(@PathVariable String id) {
        Optional<Note> note = noteService.getById(id);

        if (note.isPresent() && "public".equalsIgnoreCase(note.get().getAccess())) {
            return new ModelAndView("share")
                    .addObject("note", note.get())
                    .addObject("isPublic", true);
        } else {
            return new ModelAndView("share")
                    .addObject("message", "This Note is private or doesn't exist")
                    .addObject("isPublic", false);
        }

    }


}

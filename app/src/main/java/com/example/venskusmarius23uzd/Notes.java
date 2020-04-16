package com.example.venskusmarius23uzd;

public class Notes {
    private String NoteName;
    private String NoteCat;
    private String NoteText;

    public Notes(String nName, String nCat, String nText) {
        NoteName = nName;
        NoteCat = nCat;
        NoteText = nText;
    }

    public String getNoteName() {
        return NoteName;
    }

    public void setNoteName(String noteName) {
        NoteName = noteName;
    }

    public String getNoteCat() {
        return NoteCat;
    }

    public void setNoteCat(String noteCat) {
        NoteCat = noteCat;
    }

    public String getNoteText() {
        return NoteText;
    }

    public void setNoteText(String noteText) {
        NoteText = noteText;
    }
}

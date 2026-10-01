package fr.istic.aco.editor;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EngineTest {

    private Engine engine;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        engine = new EngineImpl();
        String buffer = "hello world";
        engine.insert(buffer);
        assertEquals(buffer, engine.getBufferContents());
    }


    @Test
    @DisplayName("Buffer must be empty after initialisation")
    void getSelection() {
        Selection selection = engine.getSelection();
        assertEquals(selection.getBufferBeginIndex(), selection.getBeginIndex());
    }

    @Test
    void getBufferContents() {
        engine.insert("hello");
        assertEquals("hello", engine.getBufferContents());
    }

    @Test
    void getClipboardContents() {
        engine.insert("hello");
        engine.getSelection().setBeginIndex(1);
        engine.getSelection().setEndIndex(4);
        engine.copySelectedText();
        assertEquals("ell", engine.getClipboardContents());
    }

    @Test
    void cutSelectedText() {
        engine.insert("abcd");
        engine.getSelection().setBeginIndex(1);
        engine.getSelection().setEndIndex(3);
        engine.cutSelectedText();
        assertEquals("ad", engine.getBufferContents());
        assertEquals("bc", engine.getClipboardContents());
    }

    @Test
    void copySelectedText() {
        engine.insert("abcd");
        engine.getSelection().setBeginIndex(1);
        engine.getSelection().setEndIndex(3);
        engine.copySelectedText();
        assertEquals("bc", engine.getClipboardContents());
    }

    @Test
    void pasteClipboard() {
        engine.insert("abcd");
        engine.getSelection().setBeginIndex(1);
        engine.getSelection().setEndIndex(3);
        engine.copySelectedText();
        engine.getSelection().setBeginIndex(0);
        engine.getSelection().setEndIndex(0);
        engine.pasteClipboard();
        assertEquals("bcabcd", engine.getBufferContents());
    }
}

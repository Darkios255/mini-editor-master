package fr.istic.aco.editor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EngineTest {

    private Engine engine;

    @BeforeEach
    void setUp() {
        engine = new EngineImpl();
    }

    @Test
    @DisplayName("Buffer must be empty after initialisation")
    void getSelection() {
        Selection selection = engine.getSelection();
        assertEquals(0, selection.getBufferBeginIndex());
        assertEquals(0, selection.getBeginIndex());
        assertEquals(0, selection.getEndIndex());
        assertEquals(0, selection.getBufferEndIndex());
        assertEquals("", engine.getBufferContents());
        assertEquals("", engine.getClipboardContents());
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

    @Test
    void copyEmptySelection() {
        engine.insert("abcd");
        engine.getSelection().setBeginIndex(2);
        engine.getSelection().setEndIndex(2);
        engine.copySelectedText();
        assertEquals("", engine.getClipboardContents());
    }

    @Test
    void selectionIndexOutOfBoundsThrowsException() {
        engine.insert("abc");
        assertThrows(IndexOutOfBoundsException.class, () -> engine.getSelection().setBeginIndex(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> engine.getSelection().setBeginIndex(5));
        assertThrows(IndexOutOfBoundsException.class, () -> engine.getSelection().setEndIndex(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> engine.getSelection().setEndIndex(5));
    }

    @Test
    void selectionIndexAutoAdjustment() {
        engine.insert("abcd");
        engine.getSelection().setBeginIndex(1);
        engine.getSelection().setEndIndex(3);

        engine.getSelection().setBeginIndex(4);
        assertEquals(4, engine.getSelection().getEndIndex());

        engine.getSelection().setBeginIndex(1);
        engine.getSelection().setEndIndex(3);

        engine.getSelection().setEndIndex(0);
        assertEquals(0, engine.getSelection().getBeginIndex());
    }
}
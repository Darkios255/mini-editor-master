package fr.istic.aco.editor;

public class EngineImpl implements Engine {

    private final StringBuilder buffer;
    private String clipboard;
    private final Selection selection;


    /**
     * EngineImpl create
     *
     */
    public EngineImpl() {
        this.buffer = new StringBuilder();
        this.selection = new SelectionImpl(buffer);
        this.clipboard = "";
    }

    /**
     * Provides access to the selection control object
     *
     * @return the selection object
     */
    @Override
    public Selection getSelection() {
        return this.selection;
    }

    /**
     * Provides the whole contents of the buffer, as a string
     *
     * @return a copy of the buffer's contents
     */
    @Override
    public String getBufferContents() {
        return this.buffer.toString();
    }

    /**
     * Provides the clipboard contents
     *
     * @return a copy of the clipboard's contents
     */
    @Override
    public String getClipboardContents() {
        return this.clipboard;
    }

    /**
     * Removes the text within the interval
     * specified by the selection control object,
     * from the buffer.
     */
    @Override
    public void cutSelectedText() {
        this.copySelectedText();
        this.delete();

    }

    /**
     * Copies the text within the interval
     * specified by the selection control object
     * into the clipboard.
     */
    @Override
    public void copySelectedText() {
        int beginIndex = this.selection.getBeginIndex();
        int endIndex = this.selection.getEndIndex();

        if (beginIndex < 0 || endIndex > buffer.length() || beginIndex > endIndex) {
            return; // invalid
        }
        if (beginIndex == endIndex) {
            this.clipboard = "";
        } else {
            this.clipboard = this.buffer.substring(beginIndex, endIndex);
        }
    }

    /**
     * Replaces the text within the interval specified by the selection object with
     * the contents of the clipboard.
     */
    @Override
    public void pasteClipboard() {
        if (this.clipboard == null) {
            return;
        }
        this.insert(this.clipboard);
    }

    /**
     * Inserts a string in the buffer, which replaces the contents of the selection
     *
     * @param s the text to insert
     */
    @Override
    public void insert(String s) {
        int beginIndex = this.selection.getBeginIndex();
        int endIndex = this.selection.getEndIndex();
        this.buffer.replace(beginIndex, endIndex, s); // replace the selected text with the inserted text
        this.selection.setBeginIndex(beginIndex + s.length());
        this.selection.setEndIndex(beginIndex + s.length());
    }

    /**
     * Removes the contents of the selection in the buffer
     */
    @Override
    public void delete() {
        int beginIndex = this.selection.getBeginIndex();
        int endIndex = this.selection.getEndIndex();
        this.buffer.delete(beginIndex, endIndex);
        selection.setBeginIndex(beginIndex);
        selection.setEndIndex(beginIndex);
    }
}

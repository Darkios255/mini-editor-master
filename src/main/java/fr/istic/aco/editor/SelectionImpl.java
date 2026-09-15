package fr.istic.aco.editor;

public class SelectionImpl {
    private final int BUFFER_BEGIN_INDEX = 0;
    private int beginIndex;
    private int endIndex;
    private StringBuilder buffer;

    public int getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }



    public int getBeginIndex() {
        return beginIndex;
    }

    public void setBeginIndex(int beginIndex) {
        this.beginIndex = beginIndex;
    }

    public int getBufferBeginIndex() {
        return BUFFER_BEGIN_INDEX;
    }

    public int getBufferEndIndex() {
        return buffer.length();
    }

}

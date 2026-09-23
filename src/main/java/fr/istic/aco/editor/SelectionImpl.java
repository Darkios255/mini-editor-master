package fr.istic.aco.editor;

public class SelectionImpl implements Selection {
    private final int BUFFER_BEGIN_INDEX = 0;
    private int beginIndex;
    private int endIndex;
    private final StringBuilder buffer;

    public SelectionImpl(StringBuilder buffer) {
        this.buffer = buffer;
        this.beginIndex = 0;
        this.endIndex = 0;
    }

    @Override
    public int getBeginIndex() {
        return this.beginIndex;
    }

    @Override
    public int getEndIndex() {
        return this.endIndex;
    }

    @Override
    public void setEndIndex(int endIndex) {
        if (endIndex < getBufferBeginIndex()) {
            this.endIndex = getBufferBeginIndex();
        } else if (endIndex > getBufferEndIndex()) {
            this.endIndex = getBufferEndIndex();
        } else {
            this.endIndex = endIndex;
        }
        if (this.beginIndex > this.endIndex) {
            this.beginIndex = this.endIndex;
        }
    }

    @Override
    public void setBeginIndex(int beginIndex) {
        if (beginIndex < getBufferBeginIndex() || beginIndex > getBufferEndIndex()) {
            throw new IndexOutOfBoundsException("beginIndex is out of bounds");
        }
        this.beginIndex = beginIndex;
        if (this.endIndex < this.beginIndex) {
            this.endIndex = this.beginIndex;
        }
    }


    @Override
    public int getBufferBeginIndex() {
        return BUFFER_BEGIN_INDEX;
    }

    @Override
    public int getBufferEndIndex() {
        return buffer.length();
    }

}

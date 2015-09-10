package modupdater.gui.util;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class MaxLengthDocument extends PlainDocument {

    private int maxChars;

    @Override
    public void insertString(int offset, String string, AttributeSet attributeSet) throws BadLocationException {
        if (string != null && (getLength() + string.length() < maxChars)) {
            super.insertString(offset, string, attributeSet);
        }
    }

    public void setMaxChars(int chars) {
        maxChars = chars;
    }
}

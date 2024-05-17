
package project2;

import java.awt.*;
import javax.swing.JTextArea;
import java.awt.print.*;

public class TextPrintable implements Printable {
    private final JTextArea textArea;

    public TextPrintable(JTextArea textArea) {
        this.textArea = textArea;
    }

    @Override
    public int print(Graphics g, PageFormat pf, int pageIndex) throws PrinterException {
        if (pageIndex > 0) {
            return Printable.NO_SUCH_PAGE;
        }

        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(pf.getImageableX(), pf.getImageableY());

        textArea.print(g2d);
        
        return Printable.PAGE_EXISTS;
    }
}
    


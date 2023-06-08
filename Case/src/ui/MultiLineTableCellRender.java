package ui;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

public class MultiLineTableCellRender extends DefaultTableCellRenderer {
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        label.setText("<html>" + value.toString().replaceAll("\n", "<br>") + "</html>");
        return label;
    }

}

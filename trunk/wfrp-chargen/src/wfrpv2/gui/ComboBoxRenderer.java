package wfrpv2.gui;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class ComboBoxRenderer extends JLabel implements ListCellRenderer {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ComboBoxRenderer() {
		setOpaque(true);
		//setHorizontalAlignment(CENTER);
		setVerticalAlignment(CENTER);
		setAutoscrolls(true);
	}
	
	public Component getListCellRendererComponent(
									JList list,
									Object value, 
									int index, 
									boolean isSelected, 
									boolean cellHasFocus) {
		
		
		if (isSelected) {
			setBackground(list.getSelectionBackground());
			setForeground(list.getSelectionForeground());
		} else {
			setBackground(list.getBackground());
			setForeground(list.getForeground());
		}
		setText((String) value);
		setToolTipText((String) value);
		
		return this;
	}

}

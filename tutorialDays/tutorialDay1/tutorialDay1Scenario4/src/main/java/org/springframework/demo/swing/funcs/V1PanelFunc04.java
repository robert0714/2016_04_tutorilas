package org.springframework.demo.swing.funcs;

import javax.swing.JPanel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import java.util.Locale;

import javax.swing.JLabel;

@Component
@Lazy
public class V1PanelFunc04 extends JPanel {
	@Autowired
	private Locale locale ;
 
	private MessageSource messageSource;
	/**
	 * 
	 */
	private static final long serialVersionUID = 5919269474534579682L;

	/**
	 * Create the panel.
	 */
	public V1PanelFunc04() {
		setLayout(new FormLayout(new ColumnSpec[] {
				com.jgoodies.forms.layout.FormSpecs.RELATED_GAP_COLSPEC,
				com.jgoodies.forms.layout.FormSpecs.DEFAULT_COLSPEC,},
			new RowSpec[] {
				com.jgoodies.forms.layout.FormSpecs.RELATED_GAP_ROWSPEC,
				com.jgoodies.forms.layout.FormSpecs.DEFAULT_ROWSPEC,}));
		
		
	}
	@Autowired
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
		JLabel label = new JLabel(messageSource.getMessage("menu_utility_msg_rut_logs_mtn", new Object[]{},  locale));
		add(label, "2, 2");

	}
}

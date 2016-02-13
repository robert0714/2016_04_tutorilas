package org.springframework.demo.swing.funcs;

import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.plaf.PanelUI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import java.util.Locale;

import javax.annotation.Resource;
import javax.swing.JLabel;

@Component
@Lazy
public class V1PanelFunc01 extends JPanel {
	
	private MessageSource messageSource;
	/**
	 * 
	 */
	private static final long serialVersionUID = 5919269474554579682L;

	/**
	 * Create the panel.
	 */
	public V1PanelFunc01() {
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
		
		JLabel label = new JLabel(messageSource.getMessage("menu_admin_account_mtn", new Object[]{}, Locale.ENGLISH));
		add(label, "2, 2");
	}

}

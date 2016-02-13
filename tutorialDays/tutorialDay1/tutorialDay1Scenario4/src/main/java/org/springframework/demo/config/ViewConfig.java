package org.springframework.demo.config;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.demo.swing.funcs.V1PanelFunc01;
import org.springframework.demo.swing.funcs.V1PanelFunc02;
import org.springframework.demo.swing.funcs.V1PanelFunc03;
import org.springframework.demo.swing.funcs.V1PanelFunc04;
import org.springframework.demo.swing.funcs.V1PanelFunc05;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

@Configuration 
@ComponentScan(basePackages={ "org.springframework.demo.swing.funcs"})
public class ViewConfig {
	
	
	private static final String TITLE ="application_name";
	private static final String ROOT_MENU_01 ="menu_admin_config_mtn";	
	private static final String ROOT_MENU_02 ="menu_log_mtn";
	
	@Autowired
	private Locale locale  ;

	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private V1PanelFunc01 demoFunc01 ;
	
	@Autowired
	private V1PanelFunc02 demoFunc02 ;
	
	@Autowired
	private V1PanelFunc03 demoFunc03 ;
	
	@Autowired
	private V1PanelFunc04 demoFunc04 ;
	
	@Autowired
	private V1PanelFunc05 demoFunc05 ;
	
	@Bean 
	public Locale getLocale(){
		return  Locale.ENGLISH;
	}
	
	@Bean(name="mainAppFrame")
	public JFrame buildMainFrame(){		 
		final String title = 	messageSource.getMessage(TITLE, new Object[]{}, locale);
		final String rootMenu01 = 	messageSource.getMessage(ROOT_MENU_01, new Object[]{}, locale);
		final String rootMenu02 = 	messageSource.getMessage(ROOT_MENU_02, new Object[]{}, locale);
		
		JFrame	result = new JFrame();
		result.setTitle(title);
		result.setBounds(100, 100, 680, 434);
		result.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		result.getContentPane()
				.setLayout(new FormLayout(
						new ColumnSpec[] { com.jgoodies.forms.layout.FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"), },
						new RowSpec[] { com.jgoodies.forms.layout.FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("default:grow"), }));

		JMenuBar menuBar = new JMenuBar();
		result.setJMenuBar(menuBar);

		JMenu funcMenu = new JMenu(rootMenu01);
		menuBar.add(funcMenu);
		config(result, funcMenu, getFunctionMap(),result);

		JMenu tableProcessMenu = new JMenu(rootMenu02);
		menuBar.add(tableProcessMenu);
		config(result, tableProcessMenu, getTableProcessMap(),result);
		  
		
		return result;
	}
	
 
	private <T extends JPanel> void config(final JFrame body, final JMenu funcMenu, final Map<String, T> aMap ,final JFrame main) {
		final String title = 	messageSource.getMessage(TITLE, new Object[]{}, Locale.getDefault());
		List<String> labelList = new ArrayList<String>(aMap.keySet());
		Collections.sort(labelList);
		for (final String label : labelList) {
			final T selectUnit = aMap.get(label);
			body.getContentPane().add(selectUnit, "2, 2, fill, fill");
			selectUnit.setVisible(false);

			JMenuItem menuItem = new JMenuItem(label);
			menuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					for (T unit : aMap.values()) {
						unit.setVisible(false);
					}
					main.setTitle(label + "---" + title);
					selectUnit.setVisible(true);
				}
			});
			funcMenu.add(menuItem);
		}
	}
	private <T extends JPanel> Map<String, ? extends JPanel> getFunctionMap() {
		Map<String, T> result = new HashMap<String, T>();
		result.put(messageSource.getMessage("menu_admin_account_mtn", new Object[]{}, locale), (T) demoFunc01); 
		result.put(messageSource.getMessage("menu_admin_role_mtn", new Object[]{}, locale), (T) demoFunc02); 
		result.put(messageSource.getMessage("menu_admin_basic_setting_mtn", new Object[]{}, locale), (T) demoFunc03); 
		
		return result;
	}

	private <T extends JPanel> Map<String, ? extends JPanel> getTableProcessMap() {
		Map<String, T> result = new HashMap<String, T>();
		result.put(messageSource.getMessage("menu_utility_msg_rut_logs_mtn", new Object[]{}, locale), (T) demoFunc04); 
		result.put(messageSource.getMessage("menu_monitor_errorstat", new Object[]{}, locale), (T) demoFunc05); 
		return result;
	}
}

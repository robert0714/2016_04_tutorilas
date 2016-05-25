package org.springframework.demo.swing;

import java.awt.EventQueue; 

import javax.swing.JFrame; 
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.demo.swing.util.OSValidator;

import com.jgoodies.looks.windows.WindowsLookAndFeel;

/**
 * Hello world!
 *
 */
public class App {
	private JFrame mainFrame;
	private  final ApplicationContext context ;
	private static final Logger LOGGER = LoggerFactory.getLogger(App.class);
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) { 
		final ApplicationContext context = 
				new  ClassPathXmlApplicationContext("META-INF/spring/application-context.xml");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {					 
					App window = new App(context); 
					window.initialize();
				} catch (Exception e) {
					LOGGER.error(e.getMessage() , e );					
				}
			}
		});
	}
	private App(ApplicationContext context){
		this.context = context;
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		settingLook();
		mainFrame = context.getBean("mainAppFrame", JFrame.class);
		mainFrame.setVisible(true);
	}

	private void settingLook() {
		try {
			OSValidator osValidator = context.getBean(OSValidator.class);

			if (osValidator.isWindows()) {
				UIManager.setLookAndFeel(new WindowsLookAndFeel());
			} else if (osValidator.isMac()) {
				LOGGER.info("This is Mac");
			} else if (osValidator.isUnix()) {
				UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
			} else if (osValidator.isSolaris()) {
				LOGGER.info("This is Solaris");
			} else {
				LOGGER.info("Your OS is not support!!");
			}
		} catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException
				| IllegalAccessException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}
	 
} 
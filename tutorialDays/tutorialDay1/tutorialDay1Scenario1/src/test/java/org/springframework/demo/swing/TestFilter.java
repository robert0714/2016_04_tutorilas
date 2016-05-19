package org.springframework.demo.swing;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.io.FileUtils;

public class TestFilter {

	public static void main(String[] args) throws IOException {
		File scrFile00= new File("/Users/leerobert/Desktop/db-status.log");
		File scrFile01= new File("/Users/leerobert/Desktop/db-status.log.1");
		File scrFile02= new File("/Users/leerobert/Desktop/db-status.log.2");
		File scrFile03= new File("/Users/leerobert/Desktop/db-status.log.3");
		File scrFile04= new File("/Users/leerobert/Desktop/db-status.log.4");
		final Set<String> filter =new HashSet<String>();
		filter.addAll(FileUtils.readLines(scrFile00));
		filter.addAll(FileUtils.readLines(scrFile01));
		filter.addAll(FileUtils.readLines(scrFile02));
		filter.addAll(FileUtils.readLines(scrFile03));
		filter.addAll(FileUtils.readLines(scrFile04));
		for(String line: filter){
			System.out.println(line);
		}
	}

}

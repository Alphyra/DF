package df.projectSettings;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ProjectSettingsReader {
	
	public static Properties properties = new Properties();
	
	static{
		try{
			File file = new File ("project.Properties");
			FileInputStream fileInput = new FileInputStream(file);
			properties.load(fileInput);
			fileInput.close();
		} catch (FileNotFoundException e) {
			System.out.print(e.getMessage());
		} catch (IOException e){
			System.out.print(e.getMessage());
		}
	}

}

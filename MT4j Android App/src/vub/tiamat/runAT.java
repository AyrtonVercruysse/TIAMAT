package vub.tiamat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class runAT extends Activity{
	
	public void execute(String string){
		
		String FILENAME = "ATcode";

		try {
			FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
			fos.write(string.getBytes());
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	Intent i = new Intent("edu.vub.at.launcher.RUN");
	i.setData(Uri.fromFile(new File("/sdcard/ATcode")));
	startActivity(i);
	}
	
	

}

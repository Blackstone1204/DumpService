package me.backservice;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import com.thoughtworks.xstream.XStream;

import android.os.Environment;
import android.util.Log;

public class DumpRunnable implements Runnable {
	private Node node=null;
	
	public DumpRunnable(Node node) {
		this.node=node;
		
	}

	@Override
	public void run() {
		String path=Environment.getExternalStorageDirectory().getPath()+"/uidump.xml";
		//String path=File.separator+"storage"+File.separator+"uidump.xml";

		 String res=DumpUtils.toXmlStr(node);
		 DumpUtils.toXmlFile(path,res);
	}

}

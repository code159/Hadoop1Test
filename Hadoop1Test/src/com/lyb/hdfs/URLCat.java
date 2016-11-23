package com.lyb.hdfs;
import java.io.InputStream;
import java.net.URL;

import org.apache.hadoop.fs.FsUrlStreamHandlerFactory;
import org.apache.hadoop.io.IOUtils;


public class URLCat {
	//每个java虚拟机只能调用一次setURLStreamHandlerFactory方法，因此通常在表态方法中调用。若有其他不受自己控制的组件已经调用，则本程序则无法调用
	static{
		URL.setURLStreamHandlerFactory(new FsUrlStreamHandlerFactory());
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		InputStream in = null;
		try{
			in = new URL(args[0]).openStream();
			IOUtils.copyBytes(in, System.out, 4096, false);
		}finally{
			IOUtils.closeStream(in);
		}
	}

}

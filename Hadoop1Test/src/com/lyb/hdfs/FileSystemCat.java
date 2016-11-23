package com.lyb.hdfs;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

public class FileSystemCat {
	static String uri="file:///";
	static String uri2="hdfs://localhost:9000";
	static String filePath="/etc/passwd";
	static String filePath2="/user/hadoop/input/students";
	static Configuration conf=new Configuration();
	static FileSystem fs=null;
	static FileSystem fs2=null;
	static InputStream in=null;
	static OutputStream out=null;
	
	@Before
	public void init() throws IOException{
		fs=FileSystem.get(URI.create(uri), conf);
		fs2=FileSystem.get(URI.create(uri2), conf);
	}
	
	public static void main(String[] args) throws Exception{
		try{
			in=fs.open(new Path(filePath));
			IOUtils.copyBytes(in, System.out, 4096, false);
			
			((FSDataInputStream) in).seek(0);
		
			out=fs.create(new Path("/home/hadoop/students2"));
			IOUtils.copyBytes(in, out, 4096, false);
		}finally{
			IOUtils.closeStream(in);
			IOUtils.closeStream(out);
		}
	}
	
	@Test
	public void TestMkdir() throws IOException{
		boolean flag=fs.mkdirs(new Path("/home/hadoop/test/"));
		System.out.println(flag);
		boolean flag2=fs2.mkdirs(new Path("hahaha"));
		System.out.println(flag2);
	}
	
	@Test
	public void TestDel() throws IOException{
		boolean flag=fs.delete(new Path("/home/hadoop/test"),true);
		System.out.println(flag);	
		boolean flag2=fs2.delete(new Path("hahaha"),true);
		System.out.println(flag);	
	}
	
	@Test
	public void TestDownload() throws IOException{
		fs2=FileSystem.get(URI.create(uri2), conf);
		InputStream in=fs2.open(new Path(filePath2));
		OutputStream out=new FileOutputStream("/home/hadoop/fromHDFS");
		IOUtils.copyBytes(in, out, 4096, true);
	}

}

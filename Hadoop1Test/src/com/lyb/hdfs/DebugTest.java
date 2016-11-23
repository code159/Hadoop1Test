package com.lyb.hdfs;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

public class DebugTest {
	public static void main(String[] args) throws IOException{
		FileSystem fs=FileSystem.get(URI.create("hdfs://172.168.2.9:9000/"), new Configuration());
		InputStream in=fs.open(new Path("/user/hadoop/test3"));
		OutputStream out=new FileOutputStream("c:\\testdir\\a");
		IOUtils.copyBytes(in, out, 4096, true);
	}

}

package com.lyb.hdfs;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

public class FileSystemTest {
	public FileSystemTest(){}
	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException{
		String uri="hdfs://localhost:9000/";
		Configuration conf = new Configuration();
		FileSystem fs = FileSystem.get(URI.create(uri),conf);
		
		//创建文件夹
		final String pathString="/d1";
		boolean exists=fs.exists(new Path(pathString));
		if(!exists){
			boolean result=fs.mkdirs(new Path(pathString));
			System.out.println(result);
		}
		//写文件
		final String fileString="/d1/f1";
		final FSDataOutputStream fsDataOutputStream=fs.create(new Path(fileString));
		IOUtils.copyBytes(new ByteArrayInputStream("my name is LI YUBIN".getBytes()),fsDataOutputStream,conf,true);
		//读文件
		final FSDataInputStream fsDataInputStream=fs.open(new Path(fileString));
		IOUtils.copyBytes(fsDataInputStream, System.out, conf, false);
		//查看目录列表和文件详细信息
		final String listString="/user/hadoop/";
		FileStatus[] status=fs.listStatus(new Path(listString));
		for(FileStatus s:status){
			String type=s.isDir()?"目录":"文件";
			short replication=s.getReplication();
			String permission=s.getPermission().toString();
			long len=s.getLen();
			Path path=s.getPath();
			System.out.print("\n"+type+"\t"+permission+"\t"+replication+"\t"+len+"\t"+path);
		}
		//删除文件或目录
		//fs.deleteOnExit(new Path(fileString));
	}
}

package com.lyb.mapre;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.util.Properties;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class WordCount {

	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		Configuration conf=new Configuration();
		conf.addResource("config/mapred-site.xml");
		conf.addResource("config/hdfs-site.xml");
		conf.addResource("config/core-site.xml");
		
		Properties prop=new Properties();
		prop.load(new FileInputStream("config/config.properties"));
		String host=prop.getProperty("hadoop.host");
		
		//构建Job对象
		Job job=new Job(new Configuration());
		
		String uri="hdfs://"+host+":9000";
		String in="/data/words.dat";
		String out="/out/out1";
		FileSystem fs=FileSystem.get(URI.create(uri), conf);
		if(fs.exists(new Path(out))) {
			fs.delete(new Path(out),true);
			System.out.println(uri+out+" deleted!");
		}
		
		//注意：设置main方法所在的类
		job.setJarByClass(WordCount.class);
		
		//设置Mapper相关属性
		job.setMapperClass(WCMapper.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(LongWritable.class);
		FileInputFormat.setInputPaths(job, new Path(uri+in));
		
		//设置Reducer相关属性
		job.setReducerClass(WCReducer.class);
		//setOutputKeyClass既适用于Mapper，也适用于Reducer
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(LongWritable.class);
		FileOutputFormat.setOutputPath(job, new Path(uri+out));
		
		//打印进度和详情
		job.waitForCompletion(true);
	}

}

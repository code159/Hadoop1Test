package com.lyb.mapre;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class WordCount {

	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		//构建Job对象
		Job job=new Job(new Configuration());
		
//		Configuration conf=new Configuration();
//		conf.addResource("E:\\Hadoop\\hadoop-1.0.3\\conf\\mapred-site.xml");
//		conf.addResource("E:\\Hadoop\\hadoop-1.0.3\\conf\\hdfs-site.xml");
//		conf.addResource("E:\\Hadoop\\hadoop-1.0.3\\conf\\core-site.xml");

		
		//注意：设置main方法所在的类
		job.setJarByClass(WordCount.class);
		
		//设置Mapper相关属性
		job.setMapperClass(WCMapper.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(LongWritable.class);
		FileInputFormat.setInputPaths(job, new Path("hdfs://172.168.2.9:9000/home/hadoop/students2"));
		
		//设置Reducer相关属性
		job.setReducerClass(WCReducer.class);
		//setOutputKeyClass既适用于Mapper，也适用于Reducer
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(LongWritable.class);
		FileOutputFormat.setOutputPath(job, new Path("hdfs://172.168.2.9:9000/home/hadoop/students2_count"));
		
		//打印进度和详情
		job.waitForCompletion(true);
	}

}

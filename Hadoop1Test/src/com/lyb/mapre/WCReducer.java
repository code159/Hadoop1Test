package com.lyb.mapre;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class WCReducer extends Reducer<Text, LongWritable, Text, LongWritable>{

	@Override
	protected void reduce(Text k2, Iterable<LongWritable> v2s,
			Reducer<Text, LongWritable, Text, LongWritable>.Context context)
			throws IOException, InterruptedException {
		//接收数据
		Text k3=k2;
		//定义一个计数器
		long counter=0;
		//循环v2s
		for(LongWritable i:v2s){
			counter+=i.get();
		}
		//输出
		context.write(k3, new LongWritable(counter));
	}
	
}

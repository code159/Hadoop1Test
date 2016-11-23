package com.lyb.hdfs.rpc;

import java.io.IOException;

public class MyBiz implements MyBizable {
	public static long BIZ_VERSION=2345234L;
	@Override
	public String hello(String name){
		System.out.println("i was called");
		return "Hello "+name;
	}
	@Override
	public long getProtocolVersion(String protocol,long clientVersion) throws IOException{
		return BIZ_VERSION;
	}
}

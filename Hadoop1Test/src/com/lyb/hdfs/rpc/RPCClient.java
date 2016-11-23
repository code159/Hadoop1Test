package com.lyb.hdfs.rpc;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;

public class RPCClient {

	public static void main(String[] args) throws IOException {
		final MyBizable proxy=(MyBizable)RPC.getProxy(MyBizable.class, MyBiz.BIZ_VERSION, 
				new InetSocketAddress(RPCServer.SERVER_ADDRESS,RPCServer.SERVER_PORT), new Configuration());
		//调用接口中的方法
		final String result=proxy.hello("liyubin");
		final long version=proxy.getProtocolVersion("AA", 1L);
		System.out.println(result);
		System.out.println(version);
		//本质是关闭网络连接
		RPC.stopProxy(proxy);
	}

}

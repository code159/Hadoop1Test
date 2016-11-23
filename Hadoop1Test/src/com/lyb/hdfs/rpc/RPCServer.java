package com.lyb.hdfs.rpc;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;
import org.apache.hadoop.ipc.RPC.Server;



public class RPCServer {
	public static final String SERVER_ADDRESS="localhost";
	public static final int SERVER_PORT=8890;

	public String sayHi(String name){
		return "HI,"+name;
	}
	public static void main(String[] args) throws IOException {
		final Server server=RPC.getServer(new MyBiz(), SERVER_ADDRESS, SERVER_PORT, new Configuration());
		server.start();
	}

}

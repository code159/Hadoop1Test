package com.lyb.hdfs.rpc;

import java.io.IOException;

import org.apache.hadoop.ipc.VersionedProtocol;

public interface MyBizable extends VersionedProtocol {
	abstract String hello(String name);

	long getProtocolVersion(String protocol, long clientVersion) throws IOException;
}

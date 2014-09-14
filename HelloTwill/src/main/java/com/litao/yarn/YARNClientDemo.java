package com.litao.yarn;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.net.NetUtils;
import org.apache.hadoop.security.SecurityInfo;
import org.apache.hadoop.yarn.conf.YarnConfiguration;
import org.apache.hadoop.yarn.security.client.ClientRMSecurityInfo;

import java.net.InetSocketAddress;

/**
 * Created by Tao Li on 9/7/14.
 */
public class YARNClientDemo {
    public static void main(String[] args) {
        Configuration conf = new Configuration();
        YarnConfiguration yarnConf = new YarnConfiguration(conf);
        InetSocketAddress rmAddress = NetUtils.createSocketAddr(yarnConf.get(
                YarnConfiguration.RM_ADDRESS, YarnConfiguration.DEFAULT_RM_ADDRESS));
        System.out.println("Connecting to Resource");
        Configuration rmServerConf = new Configuration(conf);
        rmServerConf.setClass(
                YarnConfiguration.YARN_SECURITY_INFO,
                ClientRMSecurityInfo.class, SecurityInfo.class);
        ClientRMProtocol resourceManager = ((ClientRMProtocol) rpc.getProxy(
                ClientRMProtocol.class, rmAddress, appsManagerServerConf));

    }
}

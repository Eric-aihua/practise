package com.eric.dockerclient;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.AuthConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.DockerClientConfig;

/**
 * @author aihua.sun
 *
 */
public class ImageTest {
	public static void main(String[] args) {
		String testImage = "dokk.co/mysql5:latest";
		DockerClientConfig config = DockerClientConfig.createDefaultConfigBuilder()
			    .withUri("http://192.168.58.135:2375")
			    .build();
			DockerClient docker = DockerClientBuilder.getInstance(config).build();
			docker.pullImageCmd(testImage).withAuthConfig(new AuthConfig()).exec();
	}

}

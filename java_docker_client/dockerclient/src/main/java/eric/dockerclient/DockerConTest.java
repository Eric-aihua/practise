package eric.dockerclient;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.DockerClientConfig;

public class DockerConTest {
	public static void main(String[] args) {
		DockerClientConfig config = DockerClientConfig.createDefaultConfigBuilder()
			    .withVersion("1.18")
			    .withUri("http://10.0.0.136:2375")
//			    .withUsername("dockeruser")
//			    .withPassword("ilovedocker")
//			    .withEmail("dockeruser@github.com")
//			    .withServerAddress("https://index.docker.io/v1/")
//			    .withDockerCertPath("/home/user/.docker")
			    .build();
			DockerClient docker = DockerClientBuilder.getInstance(config).build();
			System.out.println(docker.listImagesCmd().exec());
			System.out.print(docker.infoCmd().exec());
	}
}

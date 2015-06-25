package eric.dockerclient;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.LineIterator;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.DockerClientConfig;

public class DockerConTest {
	public static void main(String[] args) {
		DockerClientConfig config = DockerClientConfig.createDefaultConfigBuilder()
			    .withUri("http://192.168.40.83:2375")
			    .build();
			DockerClient docker = DockerClientBuilder.getInstance(config).build();
			System.out.println(docker.listImagesCmd().exec());
			System.out.println(docker.listImagesCmd().exec().size());
			InputStream is=docker.logContainerCmd("18a3").withStdErr().withStdOut().exec();
			System.out.println(consumeAsString(is));
			
			
	}
	
	public static String consumeAsString(InputStream response)  {

		StringWriter logwriter = new StringWriter();

		try {
			LineIterator itr = IOUtils.lineIterator(
					response, "UTF-8");

			while (itr.hasNext()) {
				String line = itr.next();
				logwriter.write(line + (itr.hasNext() ? "\n" : ""));
			}
			response.close();

			return logwriter.toString();
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			IOUtils.closeQuietly(response);
		}
	}

}

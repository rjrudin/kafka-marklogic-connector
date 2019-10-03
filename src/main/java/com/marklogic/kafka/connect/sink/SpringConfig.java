package com.marklogic.kafka.connect.sink;

import com.marklogic.hub.HubConfig;
import com.marklogic.hub.flow.FlowRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class SpringConfig {

	@Autowired
	private HubConfig hubConfig;

	@Autowired
	private FlowRunner flowRunner;

	@PostConstruct
	public void hey() {
		System.out.println("POST CONSTRUCT!!!");

		hubConfig.createProject("/Users/rrudin/dev/workspace/marklogic-data-hub/examples/dh-5-example");
		hubConfig.withPropertiesFromEnvironment("local");
		System.out.println("REFRESHING PROJECT!!!");
		hubConfig.refreshProject();

		System.out.println("RUNNING FLOW!!!");
		flowRunner.runFlow("ingestion_mapping_mastering-flow");

		System.out.println("AWAITING COMPLETION");
		flowRunner.awaitCompletion();
		System.out.println("DONE!");
	}
}

package com.vinsguru;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration(proxyBeanMethods = false)
class TestcontainersConfiguration {

	@Bean
	@ServiceConnection
	PostgreSQLContainer<?> postgresContainer() {
		// Let's skip the Dockerhub to fetch the image and use AWS ECR
		//return new PostgreSQLContainer<>(DockerImageName.parse("postgres:latest"));
		var dockerImage = DockerImageName.parse("public.ecr.aws/docker/library/postgres:latest")
								.asCompatibleSubstituteFor("postgres");
		return new PostgreSQLContainer<>(dockerImage)
				.withDatabaseName("movie")
				.withInitScript("init-db.sql");
	}

}
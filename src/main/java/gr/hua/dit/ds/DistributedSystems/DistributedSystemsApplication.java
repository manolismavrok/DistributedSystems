package gr.hua.dit.ds.DistributedSystems;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @deprecated This Spring Boot project is OBSOLETE, as the hibernate core has altered.<br/>
 * 			   Starting from Hibernate version 6.0, the legacy dialects have been moved to a separate artifact, called hibernate-community-dialects.<br/> 
 * 			   This project will only be kept for archive purposes.
 */
@Deprecated
@SpringBootApplication
public class DistributedSystemsApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(DistributedSystemsApplication.class, args);
	}

}

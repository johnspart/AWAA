/**
 * 
 */
package org.awaa.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author john.lopez
 *
 */
@Configuration
@EnableWebMvc
@ComponentScan("org.awaa.*")
public class WebConfig {

}

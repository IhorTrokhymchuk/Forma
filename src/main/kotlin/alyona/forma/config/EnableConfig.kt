package alyona.forma.config

import alyona.forma.config.data.JwtSecProperty
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity

@Configuration
@EnableConfigurationProperties(
    JwtSecProperty::class,
)
@EnableWebSecurity
@EnableMethodSecurity
@EnableJpaRepositories(
    basePackages = [
        "alyona.forma.repository",
    ]
)
@EnableScheduling
class EnableConfig {

}
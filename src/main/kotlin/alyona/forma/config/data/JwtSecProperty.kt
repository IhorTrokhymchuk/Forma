package alyona.forma.config.data

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "forma.security.jwt")
data class JwtSecProperty(
    val lifetime:Long,
    val secret:String
)

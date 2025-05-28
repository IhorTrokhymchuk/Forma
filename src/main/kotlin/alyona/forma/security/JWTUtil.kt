package alyona.forma.security

import alyona.forma.config.data.JwtSecProperty
import io.fusionauth.jwt.Signer
import io.fusionauth.jwt.Verifier
import io.fusionauth.jwt.domain.JWT
import io.fusionauth.jwt.hmac.HMACSigner
import io.fusionauth.jwt.hmac.HMACVerifier
import org.springframework.stereotype.Service
import java.time.Instant
import java.time.ZoneOffset
import java.time.ZonedDateTime

@Service
class JWTUtil(
    private val jwtSecProperty: JwtSecProperty,
) {
    fun getToken(email: String): String {
        val signer: Signer = HMACSigner.newSHA256Signer(jwtSecProperty.secret)
        val jwt = JWT()
//            .setIssuer("Forma")
            .setIssuedAt(ZonedDateTime.now(ZoneOffset.UTC))
            .setSubject(email)
            .setExpiration(ZonedDateTime.now(ZoneOffset.UTC).plusSeconds(jwtSecProperty.lifetime))
        return JWT.getEncoder().encode(jwt, signer)
    }

    fun expiration(jwtE: String): Instant {
        val verifier: Verifier = HMACVerifier.newVerifier(jwtSecProperty.secret)
        val jwtD = JWT.getDecoder().decode(jwtE, verifier)
        return jwtD.expiration.toInstant()
    }

    fun email(jwtE: String): String {
        val verifier: Verifier = HMACVerifier.newVerifier(jwtSecProperty.secret)
        val jwtD = JWT.getDecoder().decode(jwtE, verifier)
        return jwtD.subject
    }
}
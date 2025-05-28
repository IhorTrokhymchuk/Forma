package alyona.forma.exception

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.validation.ObjectError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.time.LocalDateTime

@ControllerAdvice
@RestControllerAdvice
class CustomGlobalExceptionHandler : ResponseEntityExceptionHandler() {

    override fun handleMethodArgumentNotValid(
        ex: MethodArgumentNotValidException,
        headers: HttpHeaders,
        status: HttpStatusCode,
        request: WebRequest
    ): ResponseEntity<Any>? {
        val errorsBody = linkedMapOf(
            "errors" to ex.allErrors.map { getErrorMessage(it) },
            "timestamp" to LocalDateTime.now().toString(),
            "status" to HttpStatus.BAD_REQUEST.value().toString()
        )
        return ResponseEntity(errorsBody, headers, status)
    }

    private fun getErrorMessage(e: ObjectError): String {
        return if (e is FieldError) "${e.field} ${e.defaultMessage}"
            else e.defaultMessage ?: "Unknown error"
    }

    @ExceptionHandler(EntityNotFoundException::class)
    fun handleCustomException(ex: EntityNotFoundException): ResponseEntity<Any> {
        return getObjectResponseEntity(ex.message, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(EntityExistException::class)
    fun handleCustomException(ex: EntityExistException): ResponseEntity<Any> {
        return getObjectResponseEntity(ex.message, HttpStatus.CONFLICT)
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleCustomException(ex: IllegalArgumentException): ResponseEntity<Any> {
        return getObjectResponseEntity(ex.message, HttpStatus.BAD_REQUEST)
    }


    private fun getObjectResponseEntity(message: String?, httpStatus: HttpStatus): ResponseEntity<Any> {
        val errorsBody = linkedMapOf(
            "timestamp" to LocalDateTime.now().toString(),
            "status" to httpStatus.value().toString(),
            "errors" to (message ?: "Unknown error")
        )
        return ResponseEntity(errorsBody, httpStatus)
    }
}

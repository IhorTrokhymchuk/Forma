package alyona.forma.controller.constant

import alyona.forma.repository.TrStatusRepository
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/tr-status")
class TrStatusController(
    private val trStatusRepository: TrStatusRepository
) {
    @GetMapping
    @PreAuthorize("hasAuthority('USER')")
    fun findAll() = trStatusRepository.findAll()
}

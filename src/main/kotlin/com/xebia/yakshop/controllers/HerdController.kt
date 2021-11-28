package com.xebia.yakshop.controllers

import com.xebia.yakshop.models.Herd
import com.xebia.yakshop.repositories.HerdRepository
import com.xebia.yakshop.service.HerdService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
class HerdController(private val herdService: HerdService, private val herdRepository: HerdRepository) {

    @PostMapping("/load", consumes = ["application/json", "application/xml"])
    fun loadHerd(@RequestBody herd: Herd): ResponseEntity<Herd> {
        herdRepository.deleteAll()
        repeat(herd.labyak.size) { index -> herd.labyak[index].herd = herd }
        herdRepository.save(herd)
        return ResponseEntity.status(HttpStatus.RESET_CONTENT).body(herd)
    }

    @GetMapping("/herd/{days}", consumes = ["application/json", "application/xml"])
    fun queryHerd(@RequestBody herd: Herd, @PathVariable days: Int): ResponseEntity<Any> {
        val result = herdService.queryHerd(herd, days)
        return ResponseEntity.ok(result)
    }
}

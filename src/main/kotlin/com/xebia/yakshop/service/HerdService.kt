package com.xebia.yakshop.service

import com.xebia.yakshop.models.Herd
import com.xebia.yakshop.models.LabYak
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody

interface HerdService {
    fun queryHerd(@RequestBody herd: Herd, @PathVariable days: Int): HashMap<String, List<LabYak>>
}

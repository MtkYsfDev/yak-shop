package com.xebia.yakshop.service.impl

import com.xebia.yakshop.models.Herd
import com.xebia.yakshop.models.LabYak
import com.xebia.yakshop.service.HerdService
import com.xebia.yakshop.utils.Functions
import org.springframework.stereotype.Service

@Service
class HerdServiceImpl : HerdService {
    override fun queryHerd(herd: Herd, days: Int): HashMap<String, List<LabYak>> {
        val result: HashMap<String, List<LabYak>> = HashMap()
        val validatedHerd: Herd = Functions.calculateStock(Functions.validateHerd(herd, days), days).second
        result["herd"] = Functions.calculateAge(validatedHerd, days).labyak
        return result
    }

}

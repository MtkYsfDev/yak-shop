package com.xebia.yakshop.repositories

import com.xebia.yakshop.models.LabYak
import org.springframework.data.repository.CrudRepository

interface YakRepository : CrudRepository<LabYak, Long>

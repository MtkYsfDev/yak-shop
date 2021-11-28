package com.xebia.yakshop.repositories

import com.xebia.yakshop.models.Herd
import org.springframework.data.repository.CrudRepository

interface HerdRepository : CrudRepository<Herd, Long>

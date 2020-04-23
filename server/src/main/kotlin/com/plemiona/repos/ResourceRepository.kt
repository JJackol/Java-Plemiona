package com.plemiona.repos

import com.plemiona.models.Resource
import org.springframework.data.jpa.repository.JpaRepository

interface ResourceRepository : JpaRepository<Resource, Long> {
}
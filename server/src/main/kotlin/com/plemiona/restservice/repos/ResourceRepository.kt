package com.plemiona.restservice.repos

import com.plemiona.restservice.models.Resource
import org.springframework.data.jpa.repository.JpaRepository

interface ResourceRepository : JpaRepository<Resource, Long> {
}
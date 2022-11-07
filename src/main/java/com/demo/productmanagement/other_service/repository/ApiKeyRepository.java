package com.demo.productmanagement.other_service.repository;

import com.demo.productmanagement.other_service.entiry.ApiKeyStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiKeyRepository extends JpaRepository<ApiKeyStore,Long> {
}

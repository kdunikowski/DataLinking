package com.kdunikowski.data.linking.repositories;

import com.kdunikowski.data.linking.models.DatabaseNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DatabaseNumberRepository extends JpaRepository<DatabaseNumber, Integer> {
}

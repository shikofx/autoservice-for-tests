package com.epam.ws.repository;

import com.epam.ws.model.AutoOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutoOrderRepository extends JpaRepository<AutoOrder, Long> {

}

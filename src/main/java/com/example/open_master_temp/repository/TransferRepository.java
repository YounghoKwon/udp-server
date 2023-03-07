package com.example.open_master_temp.repository;

import com.example.open_master_temp.entity.TransferObject;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TransferRepository extends JpaRepository<TransferObject, Long> {

    @Query("SELECT o FROM TransferObject o WHERE o.idx >= ?1")
    List<TransferObject> findAllByIdxIsAfter(long idx);
}

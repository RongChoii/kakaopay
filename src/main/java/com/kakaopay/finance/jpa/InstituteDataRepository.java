package com.kakaopay.finance.jpa;

import com.kakaopay.finance.model.file.InstituteData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstituteDataRepository extends JpaRepository<InstituteData, String> {

}
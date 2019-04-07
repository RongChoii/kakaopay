package com.kakaopay.finance.jpa;

import com.kakaopay.finance.model.file.Agency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgencyRepository extends JpaRepository<Agency, String> {

}

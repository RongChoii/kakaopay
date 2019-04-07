package com.kakaopay.finance.jpa;

import com.kakaopay.finance.model.file.FileDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FinanceRepository extends JpaRepository<FileDto, String> {

}

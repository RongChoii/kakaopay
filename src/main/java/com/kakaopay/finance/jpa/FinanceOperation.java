package com.kakaopay.finance.jpa;

import com.kakaopay.finance.configuration.H2LocalConfig;
import com.kakaopay.finance.model.file.FileDto;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Map;

public class FinanceOperation {

    EntityManager entityManager;

    public FinanceOperation(){
        entityManager = H2LocalConfig.getEntityManagerFactory().createEntityManager();
    }

    /**
     * begin for insert, update, delete
     * @return
     */
    public FinanceOperation begin(){
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        return this;
    }

    /**
     * insert query
     * @param param
     * @return
     */
    public FinanceOperation insertEntity(List<Map<String, Integer>> param) {

        param.forEach(map -> {
            entityManager.persist(
                    new FileDto()
                            .builder()
                            .year(map.get("year"))
                            .month(map.get("month"))
                            .molitFd(map.get("molitFd"))
                            .kbBank(map.get("kbBank"))
                            .wrBank(map.get("wrBank"))
                            .shBank(map.get("shBank"))
                            .citiBank(map.get("citiBank"))
                            .hnBank(map.get("hnBank"))
                            .nhBank(map.get("nhBank"))
                            .kebBank(map.get("kebBank"))
                            .etcBank(map.get("etcBank"))
                            .build()
            );
        });

        return this;
    }

    /**
     * select 1 entity from h2 table
     * @param sequence
     * @return
     */
    public FileDto selectOneEntity(int sequence) {
        return entityManager.find(FileDto.class, sequence);
    }


    /**
     * select all entity from h2 table
     * @return
     */
    public List<FileDto> selectAllEntity() {
        TypedQuery<FileDto> query = entityManager.createQuery(
                "SELECT fd FROM FILE_DTO fd ", FileDto.class);
        return query.getResultList();
    }


    /**
     * select count of entity from h2 table
     * @return
     */
    public Integer selectCountEntity(){
        return entityManager
                .createQuery("SELECT COUNT(fd) FROM FILE_DTO fd", Integer.class)
                .getSingleResult();
    }


    /**
     * commit and close (for insert, update, delete)
     */
    public void commitAndClose(){
        entityManager.getTransaction().commit();
        entityManager.close();
    }



}
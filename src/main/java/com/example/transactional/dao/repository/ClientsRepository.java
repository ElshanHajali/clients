package com.example.transactional.dao.repository;

import com.example.transactional.dao.entity.ClientsEntity;
import com.example.transactional.model.request.ClientsRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Transactional(readOnly = true)
public interface ClientsRepository extends JpaRepository<ClientsEntity, Long> {

    @Modifying
    @Query(value = """
            insert into clients(name, birth_date) values(:name, :birthDate)
            """, nativeQuery = true)
    void saveClient(@Param("name") String name,
                    @Param("birthDate") LocalDate birthDate);

    @Query(value = """
            Select c.name, c.birthDate
            from ClientsEntity c
            where c.name = :name
            """, nativeQuery = true)
    ClientsEntity findByName(@Param("name") String name);

    @Modifying
    @Query("""
            update ClientsEntity c set c.birthDate = :birthDate
            where c.name = :name
            """)
    void updateBirthDate(@Param("name") String name,
                         @Param("birthDate") LocalDate birthDate);
}

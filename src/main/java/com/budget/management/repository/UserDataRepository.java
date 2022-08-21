package com.budget.management.repository;

import com.budget.management.model.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDataRepository extends JpaRepository<UserData, Long> {

    public Optional <UserData> findByUser(String user);
}

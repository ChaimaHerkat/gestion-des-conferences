package com.conference.api.repositories;


import com.conference.api.entities.UserRole;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
}

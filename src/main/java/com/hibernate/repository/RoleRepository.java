package com.hibernate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hibernate.entitiy.Role;



@Repository
public interface RoleRepository extends JpaRepository<Role,Integer>{

}

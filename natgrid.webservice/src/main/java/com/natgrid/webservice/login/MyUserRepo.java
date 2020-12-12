package com.natgrid.webservice.login;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MyUserRepo extends JpaRepository<MyUser, Long> {

	MyUser findByusername(String uname);
}

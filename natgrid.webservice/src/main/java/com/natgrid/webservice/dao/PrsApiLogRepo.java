package com.natgrid.webservice.dao;

import java.sql.Timestamp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.natgrid.webservice.entity.PrsApiLog;

@Repository
public interface PrsApiLogRepo extends JpaRepository<PrsApiLog, Timestamp> {

}

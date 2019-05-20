package edu.dch.dao;

import org.springframework.stereotype.Component;


public interface IadminDao {
	int identilyVerify(String identily);

	int selectbynameAndpassword(String name, String password);
}

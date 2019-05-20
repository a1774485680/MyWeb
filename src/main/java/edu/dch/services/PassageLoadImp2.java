package edu.dch.services;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;


import edu.dch.dao.IPassageDao;
import org.springframework.stereotype.Component;

import edu.dch.bean.Passage;


@Component("PassageLoadImp2")
public class PassageLoadImp2 implements IPassageServices2 {
	@Resource(name="IPassageDao")
	public IPassageDao passdao;

	public void setPassdao(IPassageDao passdao) {
		this.passdao = passdao;
	}
	public void test() {
		List<Passage> selectPassage = passdao.SelectPassage();
		for (Passage passage : selectPassage) {
			System.out.println(passage.toString());
		}
	}
	
}

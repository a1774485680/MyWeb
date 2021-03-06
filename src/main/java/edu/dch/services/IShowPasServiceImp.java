package edu.dch.services;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;


import edu.dch.dao.IPassageDao;
import org.apache.ibatis.session.SqlSession;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import edu.dch.bean.Passage;
;
import edu.dch.utils.MybatisSqlSessionutils;
import edu.dch.utils.PassageReageUtil;

@Component("IShowPasServiceImp")
public class IShowPasServiceImp implements IShowPassageServices {
	public PassageReageUtil passread;
	@Resource(name="IPassageDao")
	public IPassageDao passdao;
	public IShowPasServiceImp(){
		passread=new PassageReageUtil();
	}
	@Override
	public String SelectByNameAndTitle(String name, String title) {
		// TODO Auto-generated method stub
		List<Passage> as= passdao.selectByAuthorAndName(name,title);
		if(as.toString().equals("[]")){

			return "没有该文章";
		}

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		System.out.println(df.format(new Date()));
		String passName=name+"@"+title; 
		String txtPassageToRead="";

		try {
			txtPassageToRead = passread.txtPassageToRead(passName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return txtPassageToRead;    
		
	}
	//通过作者姓名和文章标题获得文章信息
	@Override
	public String SelectByNameAndTitleOfpassageinfrom(String name, String title) {
		// TODO Auto-generated method stub
		List<Passage> as= passdao.selectByAuthorAndName(name,title);
		if(as.toString().equals("[]")){

			return "{\"success\":0}";

		}

		//创建一个json对象
				ObjectMapper mapper=new ObjectMapper();
					String a="";//json返回的数组
					try {
						a = mapper.writeValueAsString(as);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					passdao.updateByAuthorAndNameForVisit(name,title);
		return a;
	}

}

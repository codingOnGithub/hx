package com.hotent.platform.system.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hotent.web.controller.BaseController;


@Controller
@RequestMapping("/platform/console")
public class MainController extends BaseController {
			private Resources addListToTest(List<Resources> result,Long parentId,Long resId,String name){
				Resources r=new Resources();
				r.setResId(resId);
				r.setParentId(parentId);
				r.setResName("菜单"+name);
				result.add(r);
				return r;
			}
		  @RequestMapping("getMenuData")
		  @ResponseBody
          public List<Resources> getMenuData(HttpServletRequest request,HttpServletResponse response)throws Exception{
			  //TODO 测试数据
			  List<Resources> result =new ArrayList<Resources>();
			  Random rand = new Random(); 
			  for(Integer i=0;i<20;i++){
				  Resources r= addListToTest(result,0L,rand.nextLong(),i.toString());
				  for(Integer j=0;j<4;j++){
					  Resources s=addListToTest(result,r.getResId(),rand.nextLong(),j.toString());
					  for(Integer k=0;k<2;k++){
						  Resources sy=  addListToTest(result,s.getResId(),rand.nextLong(),k.toString());
//						  for(int l=0;l<2;l++){
//							  Resources sl=  addListToTest(result,sy.getResId(),rand.nextLong());
//							  for(int ll=0;ll<2;ll++){
//								  Resources sll=  addListToTest(result,sl.getResId(),rand.nextLong());
//							  }
//						  }
					  }
				  }
			  }
        	  return result;
          }
}

/**
 * @版权所有 2013-2015 广州宏天软件有限公司
 */
package com.hotent.base.db.mapping;

import java.io.File;
import java.io.FileNotFoundException;

import javax.xml.bind.JAXBException;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.hotent.base.test.BaseTestCase;

/**
 * <pre> 
 * 描述：MappingUtil的单元测试类
 * 构建组：x5-base-db
 * 作者：Winston Yan
 * 邮箱：yancm@jee-soft.cn
 * 日期：2013-12-16-下午4:52:43
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 */
@ContextConfiguration({"classpath:conf/x5-examples.xml"})
public class MappingUtilTest extends BaseTestCase{
	@Test
	public void getMappingLocations() throws FileNotFoundException, JAXBException{
		String defXml = Class.class.getClass().getResource("/").getPath() + "/conf/x5-mapping-all.xml";		
		File file = new File(defXml);
		String[] locs = MappingUtil.getMappingLocations(file);
		Assert.assertEquals(locs.length, 1);
	}
}

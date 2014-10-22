package com.hotent.base.core.engine.script;

import groovy.lang.GroovyShell;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import com.hotent.base.core.util.BeanUtils;

/**
 * 脚本引擎用于执行groovy脚本。
 * 
 * <pre>
 * 脚本引擎在web容器启动的时候，spring将当前项目中的所配置接口的类可以并注册到spring容器中管理的类。
 * 注册到脚本引擎中，在编写脚本时可以直接使用。
 * 例如：
 * 1.service类。
 *  service的使用方法，我们采用spring注释方式管理service，使用service的方法是：
 *  如BpmService，那么在脚本中使用该service的是否只需 将BpmService的首字母小写就可以使用了。
 *  bpmService.getActiveTasks("任务ID"); 这样就可以了。
 * 
 * 2.实现IScript。
 *  在spring注册类。
 *  &lt;bean id="scriptImpl" class="com.hotent.platform.service.bpm.ScriptImpl">&lt;/bean>
 *  使用类的方法如下：
 *  获取当前用户：
 *  scriptImpl.getCurrentUser() ，返回当前用户的ID。
 * </pre>
 * 
 * 
 * @author ray
 * 
 */
public class GroovyScriptEngine implements BeanPostProcessor {

	private Log logger = LogFactory.getLog(GroovyScriptEngine.class);
	private GroovyBinding binding = new GroovyBinding();
	/** 绑定接口 */
	private List<String> bindingInterface;

	/**
	 * @return the bindingInterface
	 */
	public List<String> getBindingInterface() {
		return bindingInterface;
	}

	/**
	 * @param bindingInterface
	 *            the bindingInterface to set
	 */
	public void setBindingInterface(List<String> bindingInterface) {
		this.bindingInterface = bindingInterface;
	}

	/**
	 * 执行groovy代码无返回。
	 * 
	 * @param script
	 */
	public void execute(String script) {
		executeObject(script, null);
	}

	/**
	 * 执行groovy代码无返回。
	 * 
	 * @param script
	 *            脚本
	 * @param vars
	 *            变量
	 */
	public void execute(String script, Map<String, Object> vars) {
		executeObject(script, vars);
	}

	/**
	 * 执行groovy代码返回布尔型。
	 * 
	 * @param script
	 * @return
	 * @throws Exception
	 */
	public boolean executeBoolean(String script, Map<String, Object> vars) {
		return (Boolean) executeObject(script, vars);
	}

	/**
	 * 执行脚本返回字符串数据。
	 * 
	 * @param script
	 * @return 字符串数据
	 */
	public String executeString(String script, Map<String, Object> vars) {
		return (String) executeObject(script, vars);
	}

	/**
	 * 执行脚本返回整形数据。
	 * 
	 * @param script
	 * @return 整形数据
	 */
	public int executeInt(String script, Map<String, Object> vars) {
		return (Integer) executeObject(script, vars);
	}

	/**
	 * 执行脚本返回浮点型数据。
	 * 
	 * @param script
	 * @return 浮点型数据
	 */
	public float executeFloat(String script, Map<String, Object> vars) {
		return (Float) executeObject(script, vars);
	}

	/**
	 * 执行脚本返回对象数据。
	 * 
	 * @param script
	 * @return 对象数据
	 */
	public Object executeObject(String script, Map<String, Object> vars) {
		logger.debug("执行:" + script);
		GroovyShell shell = new GroovyShell(binding);
		this.setParameters(shell, vars);

		script = script.replace("&apos;", "'").replace("&quot;", "\"")
				.replace("&gt;", ">").replace("&lt;", "<")
				.replace("&nuot;", "\n").replace("&amp;", "&");

		Object rtn = shell.evaluate(script);
		binding.clearVariables();
		return rtn;

	}

	/**
	 * 设置执行参数。
	 * 
	 * @param shell
	 * @param vars
	 */
	private void setParameters(GroovyShell shell, Map<String, Object> vars) {
		if (vars == null)
			return;
		Set<Map.Entry<String, Object>> set = vars.entrySet();
		for (Iterator<Map.Entry<String, Object>> it = set.iterator(); it
				.hasNext();) {
			Map.Entry<String, Object> entry = (Map.Entry<String, Object>) it
					.next();
			shell.setVariable(entry.getKey(), entry.getValue());
			// shell.setProperty(property, newValue)
		}
	}

	/**
	 * 给groovy脚本引擎注入对象的引用。
	 */
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName)
			throws BeansException {
		for (String str : bindingInterface) {
			try {
				Class<?> clazz = Class.forName(str);
				// 是否是实现该接口
				boolean isImpl = BeanUtils.isInherit(bean.getClass(), clazz);
				if (isImpl)
					binding.setProperty(beanName, bean);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				logger.debug(e.getException());
			}
		}
		return bean;
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName)
			throws BeansException {
		return bean;
	}

}

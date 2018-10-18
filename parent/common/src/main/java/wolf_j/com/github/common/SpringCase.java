/**
 * 
 */
package wolf_j.com.github.common;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 * @author wolf-J
 *
 */
public class SpringCase {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:spring/**/*.xml");
		Map<String, Object> beans = context.getBeansOfType(Object.class);
		context.close();

		beans.forEach((name, value) -> {
			if (name.contains("Case"))
				System.out.println("beanName : " + name + " , bean : " + value.getClass());
			if (name.equalsIgnoreCase("validationCase1"))
				System.out.println("validationCase1.value : " + ((ValidationCase) value).getValue());
		});

	}

}

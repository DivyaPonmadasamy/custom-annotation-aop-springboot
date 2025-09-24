package com.customannotations.customdemo;

import java.lang.reflect.Field;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class CustomdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomdemoApplication.class, args);
		ApplicationContext context = new AnnotationConfigApplicationContext(CustomConfig.class);

		User usr = context.getBean(User.class);

		System.out.println("\nMethod level annotation...");

		usr.getAllUsers();
		usr.deleteUser(); // does not use custom annotation
		usr.printUser();

		System.out.println("\nField annotation...");

		User user = new User();
		user.setUserid(100);
		user.setUsername("Avantika");
		user.setEmail("avantika@gmail.com");
		user.setA(10); // only to demonstrate how field.getAnnotation returns null for non-annotated fields

		for (Field field : user.getClass().getDeclaredFields()) {	// returns all the fields including private
			field.setAccessible(true); // private to public to access

			DBField dbField = field.getAnnotation(DBField.class); // only used with annotations - RUNTIME policy
			if (dbField != null) {
				System.out.println("field name: " + dbField.name());

				try {
					System.out.println("field value: " + field.get(user));
				} catch (IllegalAccessException e) {
					System.out.println("Caught exception" + e.getMessage());
				}

				System.out.println("field type: " + dbField.type());
				System.out.println("is primary: " + dbField.isPrimary());
				System.out.println();
			}
		}

		((ConfigurableApplicationContext) context).close();



		// // Uncomment the below section to work with XML configuration and comment the above codes to disable java configuration

		// //using xml - manual configuration (config.xml in resources)
		// ApplicationContext context = new ClassPathXmlApplicationContext("config.xml"); 
		// // using xml - auto configuration (autoconfig.xml in resources)
		// // ApplicationContext context = new ClassPathXmlApplicationContext("autoconfig.xml"); 

		// User usr = context.getBean(User.class); // automatically detects the bean with the class name insteadof ("user", User.class)
		// try{
		// 	usr.getAllUsers();
		// } catch(Exception e){
		// 	System.out.println("The caught exception is " + e);
		// }
		// usr.deleteUser();
		// usr.printUser();

		// ((ConfigurableApplicationContext)context).close();
	}
}
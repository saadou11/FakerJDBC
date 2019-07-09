package application;

import entities.Person;
import utils.GenerateData;
import utils.JdbcUtils;

public class JdbcMain {

	public static void main(String[] args) {
		
		for (int i = 0; i < 20000; i++) {
			JdbcUtils.insertData(JdbcUtils.connect("fake"), Person.class,
					GenerateData.fakeJsonData(GenerateData.getFaker()));
		}
	}

}

package utils;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.time.DateFormatUtils;

import org.json.JSONObject;

import com.github.javafaker.Faker;

 public class GenerateData {
	static public Faker faker = null;

	/**
	 * singleton data faker
	 * 
	 * @return faker
	 */
	static public Faker getFaker() {
		if (faker == null)
			faker = new Faker(Locale.ENGLISH);

		return faker;
	}

	/**
	 * 
	 * @return
	 */
	static public JSONObject fakeJsonData(Faker faker) {

		JSONObject json = new JSONObject();

		/*
		 * Random fields from data faker
		 */
		json.put("id_number", String.valueOf(faker.number().randomNumber(8, true)));
		json.put("full_name", faker.name().fullName());
		json.put("card_number", faker.business().creditCardNumber());
		json.put("card_type", faker.business().creditCardType());
		json.put("validation_phone", faker.phoneNumber().cellPhone());

		return json;
	}
	//use a hashmap is better key/value java/sql get
	static public String convertDataTypeJavaSql(String type) {
		String result = null;
		
		if (type.equals("String"))
			result = "varchar(100)";
 		
		return result;
	}

	static public void multipleFakerExample(int number) {
		while (number > 0) {
			System.out.println(fakeJsonData(getFaker()));
			number--;
		}
	}

}

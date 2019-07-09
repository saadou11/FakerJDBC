package entities;

public class Person extends Table {

	private String id_number;
	private String full_name;
	private String card_number;
	private String card_type;
	private String validation_phone;
	
	public Person() {}
	
	
	public Person(String id_number, String full_name, String card_number, String card_type, String validation_phone) {
		super();
		this.id_number = id_number;
		this.full_name = full_name;
		this.card_number = card_number;
		this.card_type = card_type;
		this.validation_phone = validation_phone;
	}

	public String getId_number() {
		return id_number;
	}

	public String getFull_name() {
		return full_name;
	}

	public String getCard_number() {
		return card_number;
	}

	public String getCard_type() {
		return card_type;
	}

	public String getValidation_phone() {
		return validation_phone;
	}
	
	
	
}

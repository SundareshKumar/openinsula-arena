package org.openinsula.arena.validator;

import static org.junit.Assert.assertEquals;

import org.hibernate.validator.ClassValidator;
import org.junit.Test;

public class PersonTestCase {

	@Test
	public void testCpf() {
		ClassValidator<Person> classValidator = new ClassValidator<Person>(Person.class);
		Person person = new Person();

		person.setCpf("005.333.839-19");
		assertEquals(0, classValidator.getInvalidValues(person, "cpf").length);

		person.setCpf("00533383919");
		assertEquals(0, classValidator.getInvalidValues(person, "cpf").length);

		person.setCpf("005.333.839-19");
		assertEquals(0, classValidator.getInvalidValues(person, "cpf").length);

		person.setCpf("00533383919");

		person.setCpf("030.405.039-36");
		assertEquals(0, classValidator.getInvalidValues(person, "cpf").length);

		person.setCpf("03040503936");
		assertEquals(0, classValidator.getInvalidValues(person, "cpf").length);

		person.setCpf("046.428.359-03");
		assertEquals(0, classValidator.getInvalidValues(person, "cpf").length);

		person.setCpf("04642835903");
		assertEquals(0, classValidator.getInvalidValues(person, "cpf").length);

		person.setCpf("023.750.169-47");
		assertEquals(0, classValidator.getInvalidValues(person, "cpf").length);

		person.setCpf("02375016947");
		assertEquals(0, classValidator.getInvalidValues(person, "cpf").length);

		person.setCpf("855.826.525-90");
		assertEquals(0, classValidator.getInvalidValues(person, "cpf").length);

		person.setCpf("669.712.265-00");
		assertEquals(0, classValidator.getInvalidValues(person, "cpf").length);

		person.setCpf("123-123.123");
		assertEquals(1, classValidator.getInvalidValues(person, "cpf").length);

		person.setCpf("02375016");
		assertEquals(1, classValidator.getInvalidValues(person, "cpf").length);

		person.setCpf("02375016947123");
		assertEquals(1, classValidator.getInvalidValues(person, "cpf").length);

		person.setCpf(null);
		assertEquals(1, classValidator.getInvalidValues(person, "cpf").length);

		person.setCpf("");
		assertEquals(1, classValidator.getInvalidValues(person, "cpf").length);

		person.setCpf("0");
		assertEquals(1, classValidator.getInvalidValues(person, "cpf").length);

		person.setCpf("00");
		assertEquals(1, classValidator.getInvalidValues(person, "cpf").length);

		person.setCpf("000000000000");
		assertEquals(1, classValidator.getInvalidValues(person, "cpf").length);

		person.setCpf("00000000000");
		assertEquals(1, classValidator.getInvalidValues(person, "cpf").length);

		person.setCpf("111.111.111-11");
		assertEquals(1, classValidator.getInvalidValues(person, "cpf").length);

		person.setCpf("22222222222");
		assertEquals(1, classValidator.getInvalidValues(person, "cpf").length);

		person.setCpf("333.333.333-33");
		assertEquals(1, classValidator.getInvalidValues(person, "cpf").length);

		person.setCpf("44444444444");
		assertEquals(1, classValidator.getInvalidValues(person, "cpf").length);

		person.setCpf("555.555.555-55");
		assertEquals(1, classValidator.getInvalidValues(person, "cpf").length);

		person.setCpf("66666666666");
		assertEquals(1, classValidator.getInvalidValues(person, "cpf").length);

		person.setCpf("777.777.777-77");
		assertEquals(1, classValidator.getInvalidValues(person, "cpf").length);

		person.setCpf("88888888888");
		assertEquals(1, classValidator.getInvalidValues(person, "cpf").length);

		person.setCpf("999.999.999-99");
		assertEquals(1, classValidator.getInvalidValues(person, "cpf").length);
	}

	@Test
	public void testCnpj() {
		ClassValidator<Person> classValidator = new ClassValidator<Person>(Person.class);
		Person person = new Person();

		person.setCnpj("06.305.901/0001-78");
		assertEquals(0, classValidator.getInvalidValues(person, "cnpj").length);

		person.setCnpj("06305901000178");
		assertEquals(0, classValidator.getInvalidValues(person, "cnpj").length);

		person.setCnpj("23.144.170/0001-45");
		assertEquals(0, classValidator.getInvalidValues(person, "cnpj").length);

		person.setCnpj("23144170000145");
		assertEquals(0, classValidator.getInvalidValues(person, "cnpj").length);

		person.setCnpj("46.868.328/0001-25");
		assertEquals(0, classValidator.getInvalidValues(person, "cnpj").length);

		person.setCnpj("42.767.194/0001-03");
		assertEquals(0, classValidator.getInvalidValues(person, "cnpj").length);

		person.setCnpj("58.647.246/0001-30");
		assertEquals(0, classValidator.getInvalidValues(person, "cnpj").length);

		person.setCnpj("37.961.612/0001-50");
		assertEquals(0, classValidator.getInvalidValues(person, "cnpj").length);

		person.setCnpj("43.256.675/0001-09");
		assertEquals(0, classValidator.getInvalidValues(person, "cnpj").length);

		person.setCnpj("24.152.237/0001-56");
		assertEquals(0, classValidator.getInvalidValues(person, "cnpj").length);

		person.setCnpj("60.871.888/0001-60");
		assertEquals(0, classValidator.getInvalidValues(person, "cnpj").length);

		person.setCnpj("66.845.982/0001-20");
		assertEquals(0, classValidator.getInvalidValues(person, "cnpj").length);

		person.setCnpj("06.074.614/0001-02");
		assertEquals(0, classValidator.getInvalidValues(person, "cnpj").length);

		person.setCnpj("06074614000102");
		assertEquals(0, classValidator.getInvalidValues(person, "cnpj").length);

		person.setCnpj("06074614000112");
		assertEquals(1, classValidator.getInvalidValues(person, "cnpj").length);

		person.setCnpj("06074614000113");
		assertEquals(1, classValidator.getInvalidValues(person, "cnpj").length);

		person.setCnpj(null);
		assertEquals(1, classValidator.getInvalidValues(person, "cnpj").length);

		person.setCnpj("");
		assertEquals(1, classValidator.getInvalidValues(person, "cnpj").length);

		person.setCnpj("000");
		assertEquals(1, classValidator.getInvalidValues(person, "cnpj").length);

		person.setCnpj("00000000000000000000");
		assertEquals(1, classValidator.getInvalidValues(person, "cnpj").length);

		person.setCnpj("00000-./.-000000000000000");
		assertEquals(1, classValidator.getInvalidValues(person, "cnpj").length);
	}

	@Test
	public void testCpfOrCnpj() {
		ClassValidator<Person> classValidator = new ClassValidator<Person>(Person.class);
		Person person = new Person();

		person.setCpfOrCnpj("06.305.901/0001-78");
		assertEquals(0, classValidator.getInvalidValues(person, "cpfOrCnpj").length);

		person.setCpfOrCnpj("06305901000178");
		assertEquals(0, classValidator.getInvalidValues(person, "cpfOrCnpj").length);

		person.setCpfOrCnpj("23.144.170/0001-45");
		assertEquals(0, classValidator.getInvalidValues(person, "cpfOrCnpj").length);

		person.setCpfOrCnpj("23144170000145");
		assertEquals(0, classValidator.getInvalidValues(person, "cpfOrCnpj").length);

		person.setCpfOrCnpj("46.868.328/0001-25");
		assertEquals(0, classValidator.getInvalidValues(person, "cpfOrCnpj").length);

		person.setCpfOrCnpj("42.767.194/0001-03");
		assertEquals(0, classValidator.getInvalidValues(person, "cpfOrCnpj").length);

		person.setCpfOrCnpj("58.647.246/0001-30");
		assertEquals(0, classValidator.getInvalidValues(person, "cpfOrCnpj").length);

		person.setCpfOrCnpj("37.961.612/0001-50");
		assertEquals(0, classValidator.getInvalidValues(person, "cpfOrCnpj").length);

		person.setCpfOrCnpj("43.256.675/0001-09");
		assertEquals(0, classValidator.getInvalidValues(person, "cpfOrCnpj").length);

		person.setCpfOrCnpj("24.152.237/0001-56");
		assertEquals(0, classValidator.getInvalidValues(person, "cpfOrCnpj").length);

		person.setCpfOrCnpj("60.871.888/0001-60");
		assertEquals(0, classValidator.getInvalidValues(person, "cpfOrCnpj").length);

		person.setCpfOrCnpj("66.845.982/0001-20");
		assertEquals(0, classValidator.getInvalidValues(person, "cpfOrCnpj").length);

		person.setCpfOrCnpj("06.074.614/0001-02");
		assertEquals(0, classValidator.getInvalidValues(person, "cpfOrCnpj").length);

		person.setCpfOrCnpj("06074614000102");
		assertEquals(0, classValidator.getInvalidValues(person, "cpfOrCnpj").length);

		person.setCpfOrCnpj("011.189.364-00");
		assertEquals(0, classValidator.getInvalidValues(person, "cpfOrCnpj").length);

		person.setCpfOrCnpj("038.976.964-95");
		assertEquals(0, classValidator.getInvalidValues(person, "cpfOrCnpj").length);

		person.setCpfOrCnpj("001.332.345-84");
		assertEquals(0, classValidator.getInvalidValues(person, "cpfOrCnpj").length);

		person.setCpfOrCnpj("045.476.064-71");
		assertEquals(0, classValidator.getInvalidValues(person, "cpfOrCnpj").length);

		person.setCpfOrCnpj("06074614000112");
		assertEquals(1, classValidator.getInvalidValues(person, "cpfOrCnpj").length);

		person.setCpfOrCnpj(null);
		assertEquals(1, classValidator.getInvalidValues(person, "cpfOrCnpj").length);

		person.setCpfOrCnpj("");
		assertEquals(1, classValidator.getInvalidValues(person, "cpfOrCnpj").length);

		person.setCpfOrCnpj("000");
		assertEquals(1, classValidator.getInvalidValues(person, "cpfOrCnpj").length);

		person.setCpfOrCnpj("00000000000000000000");
		assertEquals(1, classValidator.getInvalidValues(person, "cpfOrCnpj").length);

		person.setCpfOrCnpj("00000-./.-000000000000000");
		assertEquals(1, classValidator.getInvalidValues(person, "cpfOrCnpj").length);
	}

	@Test
	public void testDateFormat() {
		ClassValidator<Person> classValidator = new ClassValidator<Person>(Person.class);
		Person person = new Person();

		person.setDateFormat("04/12/1978");
		assertEquals(0, classValidator.getInvalidValues(person, "dateFormat").length);

		person.setDateFormat("25/04/1980");
		assertEquals(0, classValidator.getInvalidValues(person, "dateFormat").length);

		person.setDateFormat("20/11/2001");
		assertEquals(0, classValidator.getInvalidValues(person, "dateFormat").length);

		person.setDateFormat("31/03/2005");
		assertEquals(0, classValidator.getInvalidValues(person, "dateFormat").length);

		person.setDateFormat("39/32/2010");
		assertEquals(1, classValidator.getInvalidValues(person, "dateFormat").length);

		person.setDateFormat("29/02/2010");
		assertEquals(1, classValidator.getInvalidValues(person, "dateFormat").length);

		person.setDateFormat(null);
		assertEquals(1, classValidator.getInvalidValues(person, "dateFormat").length);

		person.setDateFormat("asgdfd");
		assertEquals(1, classValidator.getInvalidValues(person, "dateFormat").length);

		person.setDateFormat("10/10/2000a");
		assertEquals(1, classValidator.getInvalidValues(person, "dateFormat").length);

		person.setDateFormat(null);
		assertEquals(1, classValidator.getInvalidValues(person, "dateFormat").length);
	}

	@Test
	public void testPisPasep() {
		ClassValidator<Person> classValidator = new ClassValidator<Person>(Person.class);
		Person person = new Person();

		person.setPisPasep("124.39635.08-3");
		assertEquals(0, classValidator.getInvalidValues(person, "pisPasep").length);

		person.setPisPasep("18000505504");
		assertEquals(0, classValidator.getInvalidValues(person, "pisPasep").length);

		person.setPisPasep("126.23422.15.1");
		assertEquals(0, classValidator.getInvalidValues(person, "pisPasep").length);

		person.setPisPasep("12098383977");
		assertEquals(0, classValidator.getInvalidValues(person, "pisPasep").length);

		person.setPisPasep("127.33642.17-2");
		assertEquals(0, classValidator.getInvalidValues(person, "pisPasep").length);

		person.setPisPasep("126.22002.16.7");
		assertEquals(0, classValidator.getInvalidValues(person, "pisPasep").length);

		person.setPisPasep("12228859410");
		assertEquals(0, classValidator.getInvalidValues(person, "pisPasep").length);

		person.setPisPasep("12648652142");
		assertEquals(0, classValidator.getInvalidValues(person, "pisPasep").length);

		person.setPisPasep("123.45410.32.0");
		assertEquals(0, classValidator.getInvalidValues(person, "pisPasep").length);

		person.setPisPasep("170.591.296.75");
		assertEquals(0, classValidator.getInvalidValues(person, "pisPasep").length);

		person.setPisPasep("107.624.829.55");
		assertEquals(0, classValidator.getInvalidValues(person, "pisPasep").length);

		person.setPisPasep("124.90836.61.9");
		assertEquals(0, classValidator.getInvalidValues(person, "pisPasep").length);

		person.setPisPasep("123.45410.32.3");
		assertEquals(1, classValidator.getInvalidValues(person, "pisPasep").length);

		person.setPisPasep("123.32983.32.3");
		assertEquals(1, classValidator.getInvalidValues(person, "pisPasep").length);

		person.setPisPasep(null);
		assertEquals(1, classValidator.getInvalidValues(person, "pisPasep").length);

		person.setPisPasep("ASDASDASD");
		assertEquals(1, classValidator.getInvalidValues(person, "pisPasep").length);

		person.setPisPasep("00000000000");
		assertEquals(1, classValidator.getInvalidValues(person, "pisPasep").length);

		person.setPisPasep("00000000001");
		assertEquals(1, classValidator.getInvalidValues(person, "pisPasep").length);

		person.setPisPasep("00000000002");
		assertEquals(1, classValidator.getInvalidValues(person, "pisPasep").length);

		person.setPisPasep("00000000003");
		assertEquals(1, classValidator.getInvalidValues(person, "pisPasep").length);

		person.setPisPasep("00000000004");
		assertEquals(1, classValidator.getInvalidValues(person, "pisPasep").length);

		person.setPisPasep("00000000005");
		assertEquals(1, classValidator.getInvalidValues(person, "pisPasep").length);

		person.setPisPasep("00000000006");
		assertEquals(1, classValidator.getInvalidValues(person, "pisPasep").length);

		person.setPisPasep("00000000007");
		assertEquals(1, classValidator.getInvalidValues(person, "pisPasep").length);

		person.setPisPasep("00000000008");
		assertEquals(1, classValidator.getInvalidValues(person, "pisPasep").length);

		person.setPisPasep("00000000009");
		assertEquals(1, classValidator.getInvalidValues(person, "pisPasep").length);
	}
}

package org.openinsula.arena.gwt.json.server;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import org.springframework.util.ObjectUtils;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

// TODO melhorar testes
public class XStreamJsonSerializerTestCase extends TestCase {

	private static final XStreamJsonSerializer serializer = new XStreamJsonSerializer();

	public void testFromJson() {
		String json = "{\"mock\":{\"aBoolean\":false,\"aByte\":0,\"aShort\":0,\"anInt\":0,\"aFloat\":0,\"aDouble\":0,\"aChar\":\"\"}}";
		MockJson expected = new MockJson();
		MockJson actual = serializer.fromJson(json, new MockJson());
		assertEquals(expected, actual);
		
//		json = "{\"mocklist\":{[{\"aBoolean\":false,\"aByte\":0,\"aShort\":0,\"anInt\":0,\"aFloat\":0,\"aDouble\":0,\"aChar\":\"\"},{\"aBoolean\":true,\"aByte\":1,\"aShort\":2,\"anInt\":3,\"aFloat\":5.0,\"aDouble\":0,\"aChar\":\"\"}]}}";
//		System.out.println(serializer.fromJson(json, new MockJsonList()));
		
	}

	public void testToJson() {
		MockJsonList list = new MockJsonList();
		list.list = new ArrayList<MockJson>();
		list.list.add(new MockJson());
		list.list.add(new MockJson());
		
		String actual = serializer.toJson(list);
		System.out.println(actual);
//		String expected = "{\"mock\":{\"aBoolean\":false,\"aByte\":0,\"aShort\":0,\"anInt\":0,\"aFloat\":0,\"aDouble\":0,\"aChar\":\"\"}}";
//		assertEquals(expected, actual);
	}
	
	public void testFromJsonCollection() {
		MockJsonList list = new MockJsonList();
		list.list = new ArrayList<MockJson>();
		list.list.add(new MockJson());
		list.list.add(new MockJson());
		
		String json = serializer.toJson(list);
		MockJsonList list2 = serializer.fromJson(json, new MockJsonList());
		
		assertTrue(ObjectUtils.nullSafeEquals(list.list, list2.list));
	}

}

@XStreamAlias("mock-list")
class MockJsonList implements Serializable {
	
	@XStreamImplicit
	List<MockJson> list;
	
	@Override
	public String toString() {
		return list == null ? "null" : list.toString();
	}
	
}

@XStreamAlias("mock")
class MockJson implements Serializable {
	boolean aBoolean;

	byte aByte;

	short aShort;

	int anInt;

	float aFloat;

	double aDouble;

	char aChar;

	String aString;

	BigDecimal aBigDecimal;

	Date aDate;

	MockJson[] aArray;

	@XStreamImplicit
	Collection<MockJson> aCollection;

	Map<String, MockJson> aMap;

	@Override
	public int hashCode() {
		final int PRIME = 17;
		int result = PRIME + new Boolean(aBoolean).hashCode();
		result = PRIME * result + new Byte(aByte).hashCode();
		result = PRIME * result + new Short(aShort).hashCode();
		result = PRIME * result + new Integer(anInt).hashCode();
		result = PRIME * result + new Float(aFloat).hashCode();
		result = PRIME * result + new Double(aDouble).hashCode();
		result = PRIME * result + new Character(aChar).hashCode();
		result = PRIME * result + ObjectUtils.nullSafeHashCode(aString);
		result = PRIME * result + ObjectUtils.nullSafeHashCode(aBigDecimal);
		result = PRIME * result + ObjectUtils.nullSafeHashCode(aDate);
		result = PRIME * result + ObjectUtils.nullSafeHashCode(aArray);
		result = PRIME * result + ObjectUtils.nullSafeHashCode(aCollection);
		result = PRIME * result + ObjectUtils.nullSafeHashCode(aMap);

		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof MockJson)) {
			return false;
		}

		MockJson other = (MockJson) obj;

		return aBoolean == other.aBoolean && aByte == other.aByte && aShort == other.aShort && anInt == other.anInt
				&& aFloat == other.aFloat && aDouble == other.aDouble && aChar == other.aChar
				&& ObjectUtils.nullSafeEquals(aString, other.aString)
				&& ObjectUtils.nullSafeEquals(aBigDecimal, other.aBigDecimal)
				&& ObjectUtils.nullSafeEquals(aDate, other.aDate) && ObjectUtils.nullSafeEquals(aArray, other.aArray)
				&& ObjectUtils.nullSafeEquals(aCollection, other.aCollection)
				&& ObjectUtils.nullSafeEquals(aMap, other.aMap);
	}

	@Override
	public String toString() {
		return String
				.format(
						"aBoolean=%b%naByte=%d%naShort=%d%nanInt=%d%naFloat=%f%naDouble=%f%naChar=%c%naString=%s%naBigDecimal=%s%naDate=%s%nanArray=%s%naCollection=%s%naMap=%s",
						aBoolean, aByte, aShort, anInt, aFloat, aDouble, aChar, aString, aBigDecimal, aDate, Arrays
								.toString(aArray), aCollection, aMap);
	}
}

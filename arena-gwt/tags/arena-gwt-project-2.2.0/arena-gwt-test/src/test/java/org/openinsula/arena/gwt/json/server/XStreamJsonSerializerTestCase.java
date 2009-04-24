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

import org.openinsula.arena.gwt.json.client.JsonListWrapper;
import org.openinsula.arena.gwt.json.client.VoFactory;
import org.springframework.util.ObjectUtils;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

public class XStreamJsonSerializerTestCase extends TestCase {

	public void testMissingVoFactory() {
		XStreamJsonSerializer serializer = new XStreamJsonSerializer();
		String jsonString = "{\"mock\":{\"aBoolean\":true}}";

		try {
			serializer.fromJson(jsonString, new MockJson());
			fail();
		}
		catch (RuntimeException e) {
		}

		jsonString = serializer.toJson(new MockJson());
		assertFalse(jsonString.startsWith("{\"mock\":"));
	}
	
	public void testValidVoFactory() {
		XStreamJsonSerializer serializer = new XStreamJsonSerializer(new VoFactory() {
			public Class<?>[] getTypes() {
				return new Class[] {
						MockJson.class,
						MockChild.class
				};
			}
		});
		
		String jsonString = "{\"mock\":{\"aBoolean\":true}}";

		MockJson fromJson = serializer.fromJson(jsonString, new MockJson());
		assertTrue(fromJson.aBoolean);

		jsonString = serializer.toJson(new MockJson());
		assertTrue(jsonString.startsWith("{\"aBoolean\":false"));
	}

	public void testFromJson() {
		// TODO
	}

	public void testToJson() {
		// TODO		
	}

	public void testFromJsonCollection() {
		XStreamJsonSerializer serializer = new XStreamJsonSerializer(new VoFactory() {
			public Class<?>[] getTypes() {
				return new Class[] {
						MockJsonList.class,
						MockJson.class,
						MockChild.class
				};
			}
		});

		MockJsonList jsonList = new MockJsonList();
		jsonList.list = new ArrayList<MockJson>(Arrays.asList(new MockJson(), new MockJson()));

		String jsonListString = serializer.toJson(jsonList);
		
		JsonListWrapper<MockJson> fromJson = serializer.fromJson(jsonListString, new JsonListWrapper<MockJson>());
		assertTrue(fromJson.getList().size() == 2);
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

	MockChild child;

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
						"aBoolean=%b%naByte=%d%naShort=%d%nanInt=%d%naFloat=%f%naDouble=%f%naChar=%c%naString=%s%naBigDecimal=%s%naDate=%s%nanArray=%s%naCollection=%s%naMap=%s%nchild=%s",
						aBoolean, aByte, aShort, anInt, aFloat, aDouble, aChar, aString, aBigDecimal, aDate, Arrays
								.toString(aArray), aCollection, aMap, child);
	}
}

@XStreamAlias("mock-child")
class MockChild implements Serializable {
	long id;
}
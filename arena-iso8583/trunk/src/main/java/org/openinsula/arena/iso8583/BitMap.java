package org.openinsula.arena.iso8583;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Class that represents a ISO8583 message.
 * 
 * <p>
 * Useful for parsing and generating ISO8583 messages from {@link String} and
 * {@link InputStream} sources.
 * 
 * <p>
 * Developers can call the <code>toString()</code> method to generate the
 * {@link String} representing the ISO8583 {@link BitMap}. Useful for sending
 * the {@link BitMap} through an {@link OutputStream}.
 * 
 * @author yanaga
 * @since 1.0
 * @see BitMapParser
 */
public class BitMap {
	protected final Log logger = LogFactory.getLog(getClass());

	private static BitMapParser bitMapParser = new BitMapParser();

	protected static Map<Integer, Integer> fixedLengthFields = new HashMap<Integer, Integer>();

	protected static Map<Integer, Integer> variableLengthFields = new HashMap<Integer, Integer>();

	protected static Set<Integer> asciiFields = new HashSet<Integer>();

	private Map<Integer, String> map = new HashMap<Integer, String>();

	private String messageType;

	static {
		fixedLengthFields.put(1, 32);
		fixedLengthFields.put(3, 6);
		fixedLengthFields.put(4, 12);
		fixedLengthFields.put(5, 12);
		fixedLengthFields.put(6, 12);
		fixedLengthFields.put(7, 10);
		fixedLengthFields.put(8, 8);
		fixedLengthFields.put(9, 8);
		fixedLengthFields.put(10, 8);
		fixedLengthFields.put(11, 6);
		fixedLengthFields.put(12, 6);
		fixedLengthFields.put(13, 4);
		fixedLengthFields.put(14, 4);
		fixedLengthFields.put(15, 4);
		fixedLengthFields.put(16, 4);
		fixedLengthFields.put(17, 4);
		fixedLengthFields.put(18, 4);
		fixedLengthFields.put(19, 3);
		fixedLengthFields.put(20, 3);
		fixedLengthFields.put(21, 3);
		fixedLengthFields.put(22, 3);
		fixedLengthFields.put(23, 3);
		fixedLengthFields.put(24, 3);
		fixedLengthFields.put(25, 2);
		fixedLengthFields.put(26, 2);
		fixedLengthFields.put(27, 1);
		fixedLengthFields.put(28, 8);
		fixedLengthFields.put(29, 8);
		fixedLengthFields.put(30, 8);
		fixedLengthFields.put(31, 8);
		fixedLengthFields.put(37, 12);
		fixedLengthFields.put(38, 6);
		fixedLengthFields.put(39, 2);
		fixedLengthFields.put(40, 3);
		fixedLengthFields.put(41, 8);
		fixedLengthFields.put(42, 15);
		fixedLengthFields.put(43, 40);
		fixedLengthFields.put(49, 3);
		fixedLengthFields.put(50, 3);
		fixedLengthFields.put(51, 3);
		fixedLengthFields.put(52, 16);
		fixedLengthFields.put(53, 18);
		fixedLengthFields.put(54, 120);
		fixedLengthFields.put(64, 16);
		fixedLengthFields.put(65, 16);
		fixedLengthFields.put(66, 1);
		fixedLengthFields.put(67, 2);
		fixedLengthFields.put(68, 3);
		fixedLengthFields.put(69, 3);
		fixedLengthFields.put(70, 3);
		fixedLengthFields.put(71, 4);
		fixedLengthFields.put(72, 4);
		fixedLengthFields.put(73, 6);
		fixedLengthFields.put(74, 10);
		fixedLengthFields.put(75, 10);
		fixedLengthFields.put(76, 10);
		fixedLengthFields.put(77, 10);
		fixedLengthFields.put(78, 10);
		fixedLengthFields.put(79, 10);
		fixedLengthFields.put(80, 10);
		fixedLengthFields.put(81, 10);
		fixedLengthFields.put(82, 12);
		fixedLengthFields.put(83, 12);
		fixedLengthFields.put(84, 12);
		fixedLengthFields.put(85, 12);
		fixedLengthFields.put(86, 15);
		fixedLengthFields.put(87, 15);
		fixedLengthFields.put(88, 15);
		fixedLengthFields.put(89, 15);
		fixedLengthFields.put(90, 42);
		fixedLengthFields.put(91, 1);
		fixedLengthFields.put(92, 2);
		fixedLengthFields.put(93, 5);
		fixedLengthFields.put(94, 7);
		fixedLengthFields.put(95, 42);
		fixedLengthFields.put(96, 8);
		fixedLengthFields.put(97, 16);
		fixedLengthFields.put(98, 25);
		fixedLengthFields.put(101, 17);
		fixedLengthFields.put(128, 16);

		variableLengthFields.put(2, 2);
		variableLengthFields.put(32, 2);
		variableLengthFields.put(33, 2);
		variableLengthFields.put(34, 2);
		variableLengthFields.put(35, 2);
		variableLengthFields.put(36, 3);
		variableLengthFields.put(44, 2);
		variableLengthFields.put(45, 2);
		variableLengthFields.put(46, 3);
		variableLengthFields.put(47, 3);
		variableLengthFields.put(48, 3);
		variableLengthFields.put(55, 3);
		variableLengthFields.put(56, 3);
		variableLengthFields.put(57, 3);
		variableLengthFields.put(58, 3);
		variableLengthFields.put(59, 3);
		variableLengthFields.put(60, 1);
		variableLengthFields.put(61, 3);
		variableLengthFields.put(62, 3);
		variableLengthFields.put(63, 3);
		variableLengthFields.put(99, 11);
		variableLengthFields.put(100, 11);
		variableLengthFields.put(102, 2);
		variableLengthFields.put(103, 2);
		variableLengthFields.put(104, 3);
		variableLengthFields.put(105, 3);
		variableLengthFields.put(106, 3);
		variableLengthFields.put(107, 3);
		variableLengthFields.put(108, 3);
		variableLengthFields.put(109, 3);
		variableLengthFields.put(110, 3);
		variableLengthFields.put(111, 3);
		variableLengthFields.put(112, 3);
		variableLengthFields.put(113, 2);
		variableLengthFields.put(114, 2);
		variableLengthFields.put(115, 2);
		variableLengthFields.put(116, 2);
		variableLengthFields.put(117, 2);
		variableLengthFields.put(118, 2);
		variableLengthFields.put(119, 2);
		variableLengthFields.put(120, 2);
		variableLengthFields.put(121, 2);
		variableLengthFields.put(122, 2);
		variableLengthFields.put(123, 2);
		variableLengthFields.put(124, 2);
		variableLengthFields.put(125, 3);
		variableLengthFields.put(126, 2);
		variableLengthFields.put(127, 3);

		asciiFields.add(38);
		asciiFields.add(39);
		asciiFields.add(41);
		asciiFields.add(42);
		asciiFields.add(45);
		asciiFields.add(48);
		asciiFields.add(52);
		asciiFields.add(62);
		asciiFields.add(104);
	}

	/**
	 * Static method that parses a {@link BitMap} instance from a {@link String}.
	 * 
	 * @param s The ISO8583 {@link String} to be parsed.
	 * @return The {@link BitMap} instance represented by the {@link String}.
	 * @throws IOException
	 */
	public static BitMap parse(String s) throws IOException {
		return bitMapParser.parse(s);
	}

	/**
	 * Static method that parses a {@link BitMap} instance from a
	 * {@link InputStream}.
	 * 
	 * <p>
	 * Please note that this method <strong>does not close</strong> the
	 * {@link InputStream}. So it is possible to parse multiple {@link BitMap}
	 * instances by calling this method many times in sequence in the same
	 * {@link InputStream}.
	 * 
	 * @param in The ISO8583 {@link InputStream} to be parsed.
	 * @return The {@link BitMap} instance represented by the
	 * {@link InputStream}.
	 * @throws IOException
	 */
	public static BitMap parse(InputStream in) throws IOException {
		return bitMapParser.parse(in);
	}

	/**
	 * Adds a field in the ISO8583 {@link BitMap}.
	 * 
	 * @param bit The number of the field.
	 * @param value The field value.
	 */
	public void addField(int bit, String value) {
		if (bit == 0) {
			throw new IllegalArgumentException("Bit '0' must be passed by method setMessageType().");
		}

		map.put(bit, value);
	}

	/**
	 * Returns a field in the ISO8583 {@link BitMap}
	 * @param bit The number of the field.
	 * @return The field value.
	 */
	public String getField(int bit) {
		if (bit == 0) {
			throw new IllegalArgumentException("Bit '0' must be returned by method getMessageType().");
		}

		return map.get(bit);
	}

	protected String calculatePrimaryBitmap(Set<Integer> keys) {
		Set<Integer> keySet = new TreeSet<Integer>(keys);

		StringBuilder sb = new StringBuilder();

		int iterations = 16;

		if (Collections.max(keySet) > 64) {
			keySet.add(1);
			iterations = 32;
		}

		for (int i = 0; i < iterations; i++) {
			int value = 0;
			for (int j = 0; j < 4; j++) {
				if (keySet.contains(4 * i + 1 + j)) {
					value |= 1 << (3 - j);
				}
			}
			sb.append(Integer.toHexString(value));
		}

		return sb.toString().toUpperCase();
	}

	@Override
	public String toString() {
		Map<Integer, String> bits = new TreeMap<Integer, String>(map);

		Set<Integer> keys = new TreeSet<Integer>(bits.keySet());
		StringBuilder sb = new StringBuilder(200);

		if (validateMessageType()) {
			sb.append(messageType);
		}
		else {
			throw new IllegalArgumentException("Invalid value for ISO8583 Message Type: " + messageType);
		}

		sb.append(calculatePrimaryBitmap(keys));

		for (int key : keys) {
			if (bits.get(key) == null || key == 1) {
				continue;
			}
			if (fixedLengthFields.containsKey(key)) {
				sb.append(bits.get(key));
			}
			else if (variableLengthFields.containsKey(key)) {
				if (variableLengthFields.get(key) == 2) {
					sb.append(new DecimalFormat("00").format(bits.get(key).length()));
					sb.append(bits.get(key));
				}
				else {
					sb.append(new DecimalFormat("000").format(bits.get(key).length()));
					sb.append(bits.get(key));
				}
			}
			else {
				throw new IllegalArgumentException("BitMap field not mapped (probably its implementation is missing): "
						+ key);
			}
		}

		return sb.toString();
	}

	protected boolean validateMessageType() {
		return (messageType != null && messageType.matches("\\d{4}"));
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

}

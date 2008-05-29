package org.openinsula.arena.iso8583;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.openinsula.arena.iso8583.BitMap;

public class BitMapTestCase {
	@Test
    public void testCalculatePrimaryBitmapWithSecondary() {
        Set<Integer> keys = new HashSet<Integer>();
        keys.add(0);
        keys.add(1);
        keys.add(2);
        keys.add(3);
        keys.add(4);
        keys.add(7);
        keys.add(11);
        keys.add(12);
        keys.add(13);
        keys.add(22);
        keys.add(35);
        keys.add(41);
        keys.add(42);
        keys.add(49);
        keys.add(67);

        BitMap bitMap = new BitMap();
        assertEquals("F238040020C080002000000000000000", bitMap.calculatePrimaryBitmap(keys));
    }

	@Test
    public void testCalculatePrimaryBitmapWithoutSecondary() {
        Set<Integer> keys = new HashSet<Integer>();
        keys.add(2);
        keys.add(3);
        keys.add(4);
        keys.add(5);
        keys.add(7);
        keys.add(11);
        keys.add(12);
        keys.add(13);
        keys.add(22);
        keys.add(32);
        keys.add(41);
        keys.add(42);
        keys.add(48);
        keys.add(49);

        BitMap bitMap = new BitMap();
        assertEquals("7A38040100C18000", bitMap.calculatePrimaryBitmap(keys));
    }

	@Test
    public void testCalculatePrimaryBitmapWithoutSecondaryAura() {
        Set<Integer> keys = new HashSet<Integer>();
        keys.add(3);
        keys.add(4);
        keys.add(5);
        keys.add(7);
        keys.add(11);
        keys.add(12);
        keys.add(13);
        keys.add(32);
        keys.add(41);
        keys.add(42);
        keys.add(48);
        keys.add(49);
        keys.add(61);

        BitMap bitMap = new BitMap();
        assertEquals("3A38000100C18008", bitMap.calculatePrimaryBitmap(keys));
    }

	
	@Test
    public void testBitmapToString() {
        String s = "0200F238040020C08000200000000000000016603522000174450700300000000000063512081708300815931708301208021376035220001744507=06111013879017000000RP00000700001420103702607601";

        BitMap bitMap = new BitMap();
        bitMap.setMessageType("0200");
        bitMap.addField(1, "F238040020C080002000000000000000"); 
        bitMap.addField(2, "6035220001744507");
        bitMap.addField(3, "003000");
        bitMap.addField(4, "000000000635");
        bitMap.addField(7, "1208170830");
        bitMap.addField(11, "081593");
        bitMap.addField(12, "170830");
        bitMap.addField(13, "1208");
        bitMap.addField(22, "021");
        bitMap.addField(35, "6035220001744507=06111013879017000000");
        bitMap.addField(41, "RP000007");
        bitMap.addField(42, "000014201037026");
        bitMap.addField(49, "076");
        bitMap.addField(67, "01");

        assertEquals(s, bitMap.toString());
    }
	
	@Test
	public void testBitMapToStringAura() {
		String s = "02007A38040100C1800012001199999999001000000000000100000000000000071207510800147407510807120001100011110001ABCDEFGH000001002000800012000000000001076";
        BitMap bitMap = new BitMap();
        
        bitMap.setMessageType("0200");
        bitMap.addField(2, "001199999999");
        bitMap.addField(3, "001000");
        bitMap.addField(4, "000000000100");
        bitMap.addField(5, "000000000000");
        bitMap.addField(7, "0712075108");
        bitMap.addField(11, "001474");
        bitMap.addField(12, "075108");
        bitMap.addField(13, "0712");
        bitMap.addField(22, "000");
        bitMap.addField(32, "00011110001");
        bitMap.addField(41, "ABCDEFGH");
        bitMap.addField(42, "000001002000800");
        bitMap.addField(48, "000000000001");
        bitMap.addField(49, "076");
        
        assertEquals(s, bitMap.toString());
	}
    
	@Test
	public void testBit1CoopercredMultiCompraAvista() {
		String s = "0210B238000002C1800600000000000000020030000000000001000811130219000001100219081100CC0000010053869500001100112004V01.0CC9860010020TESTE CARDCELL MULTI009000898603";
		
		BitMap bitMap = new BitMap();
		
		bitMap.setMessageType("0210");
		bitMap.addField(3, "003000");
		bitMap.addField(4, "000000000100");
		bitMap.addField(7, "0811130219");
		bitMap.addField(11, "000001");
		bitMap.addField(12, "100219");
		bitMap.addField(13, "0811");
		bitMap.addField(39, "00");
		bitMap.addField(41, "CC000001");
		bitMap.addField(42, "005386950000110");
		bitMap.addField(48, "2004V01.0CC");
		bitMap.addField(49, "986");
		bitMap.addField(62, "0");
		bitMap.addField(63, "TESTE CARDCELL MULTI");
		bitMap.addField(127, "000898603");
		
		assertEquals(s, bitMap.toString());
	}
	
	@Test
	public void testValidateMessageType() {
		BitMap bitMap = new BitMap();

		assertFalse(bitMap.validateMessageType());
		
		bitMap.setMessageType("0200");
		assertTrue(bitMap.validateMessageType());
		
		bitMap.setMessageType("0300");
		assertTrue(bitMap.validateMessageType());
		
		bitMap.setMessageType("A200");
		assertFalse(bitMap.validateMessageType());
	}
}

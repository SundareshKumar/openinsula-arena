package org.openinsula.arena.iso8583;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.junit.Test;
import org.openinsula.arena.iso8583.BitMap;
import org.openinsula.arena.iso8583.BitMapParser;

public class BitMapParserTestCase {
	
	@Test
    public void testParseFromInputStream1() throws IOException {
        String s = "0210B238000002C0000420000000000000020030000000000001001219211102000001191102121900CARDCELL000040100106002023AYRTON A SILVA FILHO   01006651083";

        ByteArrayInputStream bais = new ByteArrayInputStream(s.getBytes());
        
        BitMapParser bitMapParser = new BitMapParser();
        BitMap bitMap = bitMapParser.parse(bais);

        assertEquals("0210", bitMap.getMessageType());
        assertEquals("B238000002C000042000000000000002", bitMap.getField(1));
        assertEquals("003000", bitMap.getField(3));
        assertEquals("000000000100", bitMap.getField(4));
        assertEquals("1219211102", bitMap.getField(7));
        assertEquals("000001", bitMap.getField(11));
        assertEquals("191102", bitMap.getField(12));
        assertEquals("1219", bitMap.getField(13));
        assertEquals("00", bitMap.getField(39));
        assertEquals("CARDCELL", bitMap.getField(41));
        assertEquals("000040100106002", bitMap.getField(42));
        assertEquals("AYRTON A SILVA FILHO   ", bitMap.getField(62));
        assertEquals("01", bitMap.getField(67));
        assertEquals("651083", bitMap.getField(127));
    }
    
	@Test
    public void testParseFromInputStream2() throws IOException {
        String s = "0202B220000002C000000000000000000002003000000000000557122017294551179800UN000001000092100010205009002637000";
        
        ByteArrayInputStream bais = new ByteArrayInputStream(s.getBytes());
        
        BitMapParser bitMapParser = new BitMapParser();
        BitMap bitMap = bitMapParser.parse(bais);
        
        assertEquals("0202", bitMap.getMessageType());
        assertEquals("B220000002C000000000000000000002", bitMap.getField(1));
        assertEquals("003000", bitMap.getField(3));
        assertEquals("000000000557", bitMap.getField(4));
        assertEquals("1220172945", bitMap.getField(7));
        assertEquals("511798", bitMap.getField(11));
        assertEquals("00", bitMap.getField(39));
        assertEquals("UN000001", bitMap.getField(41));
        assertEquals("000092100010205", bitMap.getField(42));
        assertEquals("002637000", bitMap.getField(127));
    }
    
	@Test
    public void testParseFromString() throws Exception {
        String s = "0210B238000002C0000420000000000000020030000000000006351208170830081593170830120800RP000007000014201037026023RONALDA DA SILVA LOPES 01006354464";
     
        BitMapParser bitMapParser = new BitMapParser();
        BitMap bitMap = null;
        bitMap = bitMapParser.parse(s);
        
        assertEquals("0210", bitMap.getMessageType());
        assertEquals("B238000002C000042000000000000002", bitMap.getField(1));
        assertEquals("003000", bitMap.getField(3));
        assertEquals("000000000635", bitMap.getField(4));
        assertEquals("1208170830", bitMap.getField(7));
        assertEquals("081593", bitMap.getField(11));
        assertEquals("170830", bitMap.getField(12));
        assertEquals("1208", bitMap.getField(13));
        assertEquals("00", bitMap.getField(39));
        assertEquals("RP000007", bitMap.getField(41));
        assertEquals("000014201037026", bitMap.getField(42));
        assertEquals("RONALDA DA SILVA LOPES ", bitMap.getField(62));
        assertEquals("01", bitMap.getField(67));
        assertEquals("354464", bitMap.getField(127));
    }
    
}

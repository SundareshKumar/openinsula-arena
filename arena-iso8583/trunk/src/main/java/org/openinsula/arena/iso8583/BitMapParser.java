package org.openinsula.arena.iso8583;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;
import java.util.TreeSet;

class BitMapParser {
    protected Set<Integer> getKeysFromBit1(String bit1) {
        Set<Integer> keys = new TreeSet<Integer>();
        
        int iterations = 16;
        
        if ((Integer.parseInt(bit1.substring(0, 1), 16) & 8) == 8) {
        	iterations = 32;
        }
        
        for (int i = 0; i < iterations; i++) {
            int value = Character.digit(bit1.charAt(i), 16);
            if ((value & 0x08) == 0x08) {
                keys.add(4 * i + 1);
            }
            if ((value & 0x04) == 0x04) {
                keys.add(4 * i + 2);
            }
            if ((value & 0x02) == 0x02) {
                keys.add(4 * i + 3);
            }
            if ((value & 0x01) == 0x01) {
                keys.add(4 * i + 4);
            }
        }
        
        keys.remove(1);
        
        return keys;
    }
    
    public BitMap parse(String s) throws IOException {
    	ByteArrayInputStream bais = new ByteArrayInputStream(s.getBytes());
    	
        return parse(bais);
    }
    
    public BitMap parse(InputStream in) throws IOException {
        BitMap bitMap = new BitMap();
        
        byte[] messageTypeBytes = new byte[4];
        in.read(messageTypeBytes);
        bitMap.setMessageType(new String(messageTypeBytes));

        byte[] primaryBitMapBytes = new byte[32];
        in.read(primaryBitMapBytes, 0, 16);

        if ((Integer.parseInt(String.valueOf((char)primaryBitMapBytes[0]), 16) & 8) == 8) {
        	in.read(primaryBitMapBytes, 16, 16);
        }
        
        bitMap.addField(1, new String(primaryBitMapBytes));

        for (int key: getKeysFromBit1(bitMap.getField(1))) {
            StringBuilder sb = new StringBuilder();
            if (BitMap.fixedLengthFields.containsKey(key)) {
                for (int i = 0; i < BitMap.fixedLengthFields.get(key); i++) {
                    sb.append((char)in.read());
                }
            } else {
                StringBuilder sizeBuilder = new StringBuilder();
                for (int i = 0; i < BitMap.variableLengthFields.get(key); i++) {
                    sizeBuilder.append((char)in.read());
                }
                for (int i = 0; i < Integer.valueOf(sizeBuilder.toString()); i++) {
                    sb.append((char)in.read());
                }
            }
            bitMap.addField(key, sb.toString());
        }
        return bitMap;
    }
    
}

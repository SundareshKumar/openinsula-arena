package org.openinsula.arena.validator;

import org.openinsula.arena.validator.DefaultEmailValidator;
import org.openinsula.arena.validator.Validator;

import junit.framework.TestCase;

public class DefaultEmailValidatorTestCase extends TestCase {
    public void testValidate() {
        Validator validator = new DefaultEmailValidator();
        assertTrue(validator.validate("eu@eu.com"));
        assertTrue(validator.validate("a@abc.com"));
        assertTrue(validator.validate("a@a.c"));
        assertTrue(validator.validate("a@aa.bb.com.br"));
        assertTrue(validator.validate("a@aa.bb.c.br"));
        assertTrue(validator.validate("a@a"));
        assertFalse(validator.validate("a@123.b"));
        assertFalse(validator.validate("tt@t."));
        assertFalse(validator.validate("tt@t@t.com"));
        assertFalse(validator.validate("tt@t..b.br"));
        assertFalse(validator.validate("@c.bbb"));
    }

    public void testLocalName() {
        DefaultEmailValidator validator = new DefaultEmailValidator();
        assertTrue(validator.checkLocalName("abc"));
        assertTrue(validator.checkLocalName("a"));
        assertTrue(validator.checkLocalName("-a-a"));
        assertTrue(validator.checkLocalName("b_c"));
        assertTrue(validator.checkLocalName("11-a"));
        assertTrue(validator.checkLocalName("bc--a__b"));
        assertFalse(validator.checkLocalName(""));
        assertFalse(validator.checkLocalName("$aaa"));
        assertFalse(validator.checkLocalName("t:d"));
        assertFalse(validator.checkLocalName("adfadsf>"));
        assertFalse(validator.checkLocalName("dfafd@"));
        assertFalse(validator.checkLocalName("bb/"));
    }

    public void testCheckDomain() {
        DefaultEmailValidator validator = new DefaultEmailValidator();
        assertTrue(validator.checkDomain("a"));
        assertTrue(validator.checkDomain("ab"));
        assertTrue(validator.checkDomain("abc"));
        assertTrue(validator.checkDomain("a123"));
        assertTrue(validator.checkDomain("ab.com"));
        assertTrue(validator.checkDomain("ab.com.br"));
        assertTrue(validator.checkDomain("a--123"));
        assertTrue(validator.checkDomain("b123"));
        assertTrue(validator.checkDomain("b--1"));
        assertFalse(validator.checkDomain("1abc"));
        assertFalse(validator.checkDomain("abc-"));
        assertFalse(validator.checkDomain("ab22c11-"));
        assertTrue(validator.checkDomain("abc.xyz.wwa.com"));
        assertTrue(validator.checkDomain("a--bc.xyz.wwa.com"));
        assertTrue(validator.checkDomain("abc.x-yz.wwa.com"));
        assertTrue(validator.checkDomain("abc.xyz.w-wa.com"));
        assertTrue(validator.checkDomain("abc.xyz.wwa.c-om"));
        assertTrue(validator.checkDomain("a-bc.xyz.w-wa.c-om"));
        assertTrue(validator.checkDomain("a12c.xyz.w-wa.c-om"));
        assertTrue(validator.checkDomain("a-bc.x123--yz.w-wa.c-om"));
        assertTrue(validator.checkDomain("a-bc.xyz.w123123-wa.c-om"));
        assertTrue(validator.checkDomain("com"));
        assertFalse(validator.checkDomain("com-"));
        assertFalse(validator.checkDomain("a-bc-.xyz.w123123-wa.c-om"));
        assertFalse(validator.checkDomain("1a-bc.xyz.w123123-wa.c-om"));
        assertFalse(validator.checkDomain("a-bc-.1xyz.w123123-wa.c-om"));
        assertFalse(validator.checkDomain("a-bc.xyz.1w123123-wa.c-om"));
        assertFalse(validator.checkDomain("a-bc.xyz-.w123123-wa.c-om"));
        assertFalse(validator.checkDomain("a-bc.xyz.w123123-wa.c-om-"));
        assertFalse(validator.checkDomain("a-bc.xyz.w123123-wa.1c-om"));
        assertFalse(validator.checkDomain("a-bc.xyz.w123123-wa-.c-om"));
        assertFalse(validator.checkDomain("a-bc.xyz.w123123-wa.c-om-"));
    }
}

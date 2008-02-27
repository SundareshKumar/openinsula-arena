package org.openinsula.arena.io.textfile;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import org.junit.Test;
import org.openinsula.arena.io.textfile.DefaultFileTransformer;
import org.openinsula.arena.io.textfile.field.StringField;
import org.openinsula.arena.io.textfile.line.AbstractLineFactory;
import org.openinsula.arena.io.textfile.line.Line;
import org.openinsula.arena.io.textfile.line.LineFactory;

public class DefaultFileTranformerTestCase {
	@Test
	public void testSetOutput() {
		DefaultFileTransformer transformerImpl = new DefaultFileTransformer(null);
		transformerImpl.setOutput(new ByteArrayOutputStream());

		try {
			transformerImpl.setOutput(new ByteArrayOutputStream());
			fail();
		}
		catch (Exception ex) {
		}

		try {
			transformerImpl.setOutput(new PrintWriter(new ByteArrayOutputStream()));
			fail();
		}
		catch (Exception ex) {
		}
	}

	@Test
	public void testClose() {
		DefaultFileTransformer transformerImpl = new DefaultFileTransformer(null);
		try {
			transformerImpl.close();
			fail();
		}
		catch (Exception ex) {
		}

		transformerImpl.setOutput(new ByteArrayOutputStream());
		transformerImpl.close();
	}

	@Test
	public void testAddLine() throws Exception {
		LineFactory lineFactory = new AbstractLineFactory() {
			@Override
			protected boolean doMatches(String s) {
				return true;
			}
		};

		lineFactory.addField(new StringField(10));
		Line line = lineFactory.createLine();
		line.setValue(0, "abcd");

		DefaultFileTransformer transformerImpl = new DefaultFileTransformer(null);
		try {
			transformerImpl.addLine(line);
			fail();
		}
		catch (Exception ex) {
		}

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		transformerImpl.setOutput(baos);
		transformerImpl.addLine(line);
		transformerImpl.close();

		BufferedReader br = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(baos.toByteArray())));
		String s = br.readLine();
		assertNotNull(s);
		assertEquals("abcd      ", s);
	}
}

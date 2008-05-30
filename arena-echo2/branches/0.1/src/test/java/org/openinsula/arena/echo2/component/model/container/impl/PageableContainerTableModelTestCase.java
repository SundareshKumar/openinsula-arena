package org.openinsula.arena.echo2.component.model.container.impl;

import static org.junit.Assert.*;

import org.junit.Test;

public class PageableContainerTableModelTestCase {

	@Test
	public void testGetPageCount() {
		PageableContainerTableModel<String> model = new PageableContainerTableModel<String>();
		model.setPageSize(2);

		assertEquals(Integer.valueOf(0), Integer.valueOf(model.getPageCount()));
		
		model.addItem("A");
		assertEquals(Integer.valueOf(1), Integer.valueOf(model.getPageCount()));
		model.addItem("B");
		assertEquals(Integer.valueOf(1), Integer.valueOf(model.getPageCount()));

		model.addItem("C");
		assertEquals(Integer.valueOf(2), Integer.valueOf(model.getPageCount()));
		model.addItem("D");
		assertEquals(Integer.valueOf(2), Integer.valueOf(model.getPageCount()));
		
		model.addItem("E");
		assertEquals(Integer.valueOf(3), Integer.valueOf(model.getPageCount()));
		model.addItem("F");
		assertEquals(Integer.valueOf(3), Integer.valueOf(model.getPageCount()));
		
		model.addItem("G");
		assertEquals(Integer.valueOf(4), Integer.valueOf(model.getPageCount()));
		
	}

	@Test
	public void testGetCurrentPageSize() {
		PageableContainerTableModel<String> model = new PageableContainerTableModel<String>();
		model.setPageSize(3);
		model.setCurrentPage(0);
		
		assertEquals(Integer.valueOf(0), Integer.valueOf(model.getCurrentPageSize()));
		
		model.addItem("A");

		assertEquals(Integer.valueOf(1), Integer.valueOf(model.getCurrentPageSize()));

		model.addItem("B");

		assertEquals(Integer.valueOf(2), Integer.valueOf(model.getCurrentPageSize()));

		model.addItem("C");

		assertEquals(Integer.valueOf(3), Integer.valueOf(model.getCurrentPageSize()));

		model.addItem("D");
		model.setCurrentPage(1);

		assertEquals(Integer.valueOf(1), Integer.valueOf(model.getCurrentPageSize()));

		model.setCurrentPage(0);
		model.setPageSize(4);

		assertEquals(Integer.valueOf(1), Integer.valueOf(model.getPageCount()));
		assertEquals(Integer.valueOf(4), Integer.valueOf(model.getCurrentPageSize()));
		
		model.addItem("E");
		model.setCurrentPage(1);
		
		assertEquals(Integer.valueOf(2), Integer.valueOf(model.getPageCount()));
		assertEquals(Integer.valueOf(1), Integer.valueOf(model.getCurrentPageSize()));
	}

}

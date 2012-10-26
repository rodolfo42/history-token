package com.github.rodolfo42.historytoken;

import static org.junit.Assert.*;

import org.junit.Test;

public class HistoryTokenTest {
	
	private String tkn;
	private HistoryToken token;
	
	@Test
	public void testNull() {
		tkn = null;
		token = new HistoryToken(tkn);
		
		assertTrue(token.isEmpty());
		assertFalse(token.isCommandOnly());
		assertFalse(token.isCommandWithParams());
		
		assertEquals("", token.getToken());
	}
	
	@Test
	public void testEmpty() {
		tkn = "";
		token = new HistoryToken(tkn);
		
		assertTrue(token.isEmpty());
		assertFalse(token.isCommandOnly());
		assertFalse(token.isCommandWithParams());
		
		assertEquals(tkn, token.getToken());
	}
	
	@Test
	public void testSlashOnly() {
		tkn = "/";
		token = new HistoryToken(tkn);
		
		assertTrue(token.isEmpty());
		assertFalse(token.isCommandOnly());
		assertFalse(token.isCommandWithParams());
		
		assertEquals(tkn, token.getToken());
	}
	
	@Test
	public void testCommandOnly() {
		tkn = "foo";
		token = new HistoryToken(tkn);
		
		assertTrue(token.isCommandOnly());
		assertFalse(token.isCommandWithParams());
		assertEquals("foo", token.getCommand());
		
		assertEquals(tkn, token.getToken());
	}
	
	@Test
	public void testCommandOnlyAndSlash() {
		tkn = "foo/";
		token = new HistoryToken(tkn);
		
		assertTrue(token.isCommandOnly());
		assertFalse(token.isCommandWithParams());
		assertEquals("foo", token.getCommand());
		
		assertEquals(tkn, token.getToken());
	}
	
	@Test
	public void testCommandSingleParam() {
		tkn = "command/123";
		token = new HistoryToken(tkn);
		
		assertFalse(token.isEmpty());
		assertFalse(token.isCommandOnly());
		assertTrue(token.isCommandWithParams());
		assertEquals("command", token.getCommand());
		
		assertEquals(1, token.getParams().length);
		assertEquals("123", token.getParam(0));
		assertArrayEquals(new String[] { "123" }, token.getParams());
		
		assertEquals(tkn, token.getToken());
	}
	
	@Test
	public void testCommandSingleParamAndSlash() {
		tkn = "command/123/";
		token = new HistoryToken(tkn);
		
		assertFalse(token.isEmpty());
		assertFalse(token.isCommandOnly());
		assertTrue(token.isCommandWithParams());
		assertEquals("command", token.getCommand());
		
		assertEquals(1, token.getParams().length);
		assertEquals("123", token.getParam(0));
		assertArrayEquals(new String[] { "123" }, token.getParams());
		
		assertEquals(tkn, token.getToken());
	}
	
	@Test
	public void testCommandMultipleParams() {
		tkn = "commandparams/123/a2";
		token = new HistoryToken(tkn);
		
		assertFalse(token.isEmpty());
		assertFalse(token.isCommandOnly());
		assertTrue(token.isCommandWithParams());
		assertEquals("commandparams", token.getCommand());
		
		assertEquals(2, token.getParams().length);
		assertEquals("123", token.getParam(0));
		assertEquals("a2", token.getParam(1));
		assertArrayEquals(new String[] { "123", "a2" }, token.getParams());
		
		assertNull(token.getParam(2));
		
		assertEquals(tkn, token.getToken());
	}
	
	@Test
	public void testCommandMultipleParamsAndSlash() {
		tkn = "commandparams/123/a2/";
		token = new HistoryToken(tkn);
		
		assertFalse(token.isEmpty());
		assertFalse(token.isCommandOnly());
		assertTrue(token.isCommandWithParams());
		assertEquals("commandparams", token.getCommand());
		
		assertEquals(2, token.getParams().length);
		assertEquals("123", token.getParam(0));
		assertEquals("a2", token.getParam(1));
		assertArrayEquals(new String[] { "123", "a2" }, token.getParams());
		
		assertEquals(tkn, token.getToken());
	}
	
	@Test
	public void testCommandMultipleParamsSurroundingSlashes() {
		tkn = "//commandparams/123/a2///";
		token = new HistoryToken(tkn);
		
		assertFalse(token.isEmpty());
		assertFalse(token.isCommandOnly());
		assertTrue(token.isCommandWithParams());
		assertEquals("commandparams", token.getCommand());
		
		assertEquals(2, token.getParams().length);
		assertEquals("123", token.getParam(0));
		assertEquals("a2", token.getParam(1));
		assertArrayEquals(new String[] { "123", "a2" }, token.getParams());
		
		assertEquals(tkn, token.getToken());
	}
	
	// TODO testInvalid com caracteres inválidos (descobrir quais são)
}
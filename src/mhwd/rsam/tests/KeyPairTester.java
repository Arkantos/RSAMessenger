package mhwd.rsam.tests;

import static org.junit.Assert.*;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import mhwd.rsam.keys.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class KeyPairTester {
	KeyPairGenerator keyGen;
	@Before
	public void setUp() throws Exception {
		keyGen=KeyPairGenerator.getInstance();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testOwner() {
		try {
			setUp();
		} catch (Exception e) {
			assertTrue(false);
		}
		Key key=keyGen.generateKey("test", "123", 256);
		PrivateKey p1key=key.getPrivateKey();
		PublicKey p2key=key.getPublicKey();
		assertEquals(p1key.owner, "test");
	}
	
	@Test
	public void testBitLength()
	{
		try {
			setUp();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			assertTrue(false);
		}
		Key key=keyGen.generateKey("test", "123", 256);
		BigInteger keyN1=key.getPublicKey().getN();
		BigInteger keyN2=key.getPrivateKey().getN();
		
		assertEquals(keyN1, keyN2);
		assertTrue(keyN1.bitLength()== 512);
		
	}
	
	
	@Test
	public void testPhi()
	{
		try {
			setUp();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			assertTrue(false);
		}
		
		Key key=keyGen.generateKey("test", "123", 256);
		BigInteger e=key.getPublicKey().getE();
		BigInteger d=key.getPrivateKey().getD();
		BigInteger n=key.getPublicKey().getN();
		BigInteger test=BigInteger.TEN;
		
		test=test.modPow(e, n);
		test=test.modPow(d, n);
		assertEquals(test, BigInteger.TEN);
		
	}
	
	@Test
	public void test1024Key()
	{
		try {
			setUp();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			assertTrue(false);
		}
		Key key=keyGen.generateKey("test", "123", 512);
	//	Key key2048=keyGen.generateKey("test", "123", 1024);
	//	Key key4096=keyGen.generateKey("test", "123", 2048);
	//	Key key8192=keyGen.generateKey("test", "123", 4096);
		String strMessage="This is a test message!";
		BigInteger intMessage=new BigInteger(strMessage.getBytes(StandardCharsets.UTF_8));
		intMessage=intMessage.modPow(key.getPublicKey().getE(), key.getPublicKey().getN());
		intMessage=intMessage.modPow(key.getPrivateKey().getD(), key.getPrivateKey().getN());
		String check=new String(intMessage.toByteArray(), StandardCharsets.UTF_8);
		assertEquals(strMessage,check);
		
	}
	
	@Test
	public void test2048Key()
	{
		try {
			setUp();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			assertTrue(false);
		}
	//	Key key1024=keyGen.generateKey("test", "123", 512);
		Key key=keyGen.generateKey("test", "123", 1024);
	//	Key key4096=keyGen.generateKey("test", "123", 2048);
	//	Key key8192=keyGen.generateKey("test", "123", 4096);
		String strMessage="This is a test message!";
		BigInteger intMessage=new BigInteger(strMessage.getBytes(StandardCharsets.UTF_8));
		intMessage=intMessage.modPow(key.getPublicKey().getE(), key.getPublicKey().getN());
		intMessage=intMessage.modPow(key.getPrivateKey().getD(), key.getPrivateKey().getN());
		String check=new String(intMessage.toByteArray(), StandardCharsets.UTF_8);
		assertEquals(strMessage,check);
		
	}
	
	
	@Test
	public void testNewGen()
	{
		try {
			setUp();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			assertTrue(false);
		}
		Key key=keyGen.generateKey("Bob", "H", "Andrews:", "123", 256);
		String strMessage="This is a test message!";
		BigInteger intMessage=new BigInteger(strMessage.getBytes(StandardCharsets.UTF_8));
		intMessage=intMessage.modPow(key.getPublicKey().getE(), key.getPublicKey().getN());
		intMessage=intMessage.modPow(key.getPrivateKey().getD(), key.getPrivateKey().getN());
		String check=new String(intMessage.toByteArray(), StandardCharsets.UTF_8);
		assertEquals(strMessage,check);
	}
	
	@Test
	public void testSeed()
	{
		try {
			setUp();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			assertTrue(false);
		}
		
		HashMap<String, BigInteger> map=new HashMap<String, BigInteger>();
		
		for(int i=0; i<50; i++)
		{
			Key key=keyGen.generateKey("Bob", "H", "Andrews:", i+"", 256);
			assertFalse(map.containsValue(key.getPublicKey().getE()));
			map.put(i+"", key.getPublicKey().getE());
		}
		
		
	}

}

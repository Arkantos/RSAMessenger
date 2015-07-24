package mhwd.rsam.keys;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Random;



public class KeyPairGenerator {

	private static KeyPairGenerator inst;

	protected KeyPairGenerator()
	{
		
	}
	
	public static KeyPairGenerator getInstance()
	{
		if(inst==null)
			inst=new KeyPairGenerator();
		return inst;
	}
	
	public Key generateKey(String fName, String mName, String lName, String seed, int bitLength)
	{
		String owner=fName+" "+mName+" "+lName;
		BigInteger fNameInt=new BigInteger(fName.getBytes(StandardCharsets.UTF_8));
		BigInteger mNameInt=new BigInteger(mName.getBytes(StandardCharsets.UTF_8));
		BigInteger lNameInt=new BigInteger(lName.getBytes(StandardCharsets.UTF_8));
		BigInteger seedString=new BigInteger(seed.getBytes(StandardCharsets.UTF_8));
		BigInteger lseedInt=fNameInt.xor(mNameInt).xor(lNameInt).xor(seedString);
		long lseed=lseedInt.longValue();
		return keyGen(owner, lseed, bitLength);
	}
	public Key generateKey(String owner, String seed, int bitLength)
	{
		long lseed=new BigInteger(seed.getBytes()).longValue();
		return keyGen(owner, lseed, bitLength);
		
		
	}
	
	
	private Key keyGen(String owner, long seed, int bitLength)
	{
		BigInteger p,q,n,m,e,d;
		
		Random rand=new Random(seed);
		p=new BigInteger(bitLength, 32,rand);
		q=new BigInteger(bitLength, 32, rand);
		n=p.multiply(q);
		m=(p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
		do{
			e=new BigInteger(3*bitLength/2, rand);
		}while(!e.gcd(m).equals(BigInteger.ONE));
		d=e.modInverse(m);
		PublicKey p1key=new PublicKey(owner, n, e);
		PrivateKey p2key=new PrivateKey(owner, n, d);
		return new Key(owner, p1key, p2key);
	}
}

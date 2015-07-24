package mhwd.rsam.keys;

import java.io.Serializable;
import java.math.BigInteger;

public class PublicKey implements Serializable{

	public String owner;
	private BigInteger n, e;
	public PublicKey(String owner, BigInteger n, BigInteger e)
	{
		this.owner=owner;
		this.n=n;
		this.e=e;
	}
	
	public BigInteger getN()
	{
		return n;
		
	}
	public BigInteger getE()
	{
		return e;
	}
}

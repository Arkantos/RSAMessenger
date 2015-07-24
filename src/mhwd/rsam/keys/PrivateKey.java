package mhwd.rsam.keys;

import java.io.Serializable;
import java.math.BigInteger;

public class PrivateKey implements Serializable{
	public String owner;
	private BigInteger n, d;
	
	public PrivateKey(String owner, BigInteger n, BigInteger d)
	{
		this.owner=owner;
		this.n=n;
		this.d=d;
	}
	
	public BigInteger getN()
	{
		return n;
		
	}
	public BigInteger getD()
	{
		return d;
	}
	
	
}

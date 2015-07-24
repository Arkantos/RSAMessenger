package mhwd.rsam.keys;

import java.io.Serializable;
import java.math.BigInteger;

public class Key implements Serializable{
	public String owner;
	private PublicKey publicKey;
	private PrivateKey privateKey;
	public Key()
	{
		owner="Default";
		
		
	}
	public Key(String owner, PublicKey pubkey, PrivateKey privkey)
	{
		this.owner=owner;
		this.publicKey=pubkey;
		this.privateKey=privkey;
		
	}
	public String getOwner()
	{
		return owner;
	}
	
	public PublicKey getPublicKey()
	{
		return publicKey;
	}
	
	public PrivateKey getPrivateKey()
	{
		return privateKey;
	}
	
	

}

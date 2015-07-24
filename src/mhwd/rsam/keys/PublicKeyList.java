package mhwd.rsam.keys;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Set;

public class PublicKeyList implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public HashMap<String, PublicKey> keyMap;
	private PublicKeyList instance;
	public PublicKeyList()
	{
		keyMap=new HashMap<String, PublicKey>();
		
	}

	public PublicKeyList getInstance()
	{
		if(instance==null)
			instance=new PublicKeyList();
		return instance;
	}
	
	public Set<String> getNames()
	{
		return keyMap.keySet();
	}
	
	public PublicKey getKey(String name) throws FileNotFoundException
	{
		if(keyMap.containsKey(name))
			return keyMap.get(name);
		else throw new FileNotFoundException();
	}
	
	public boolean addKey(PublicKey key)
	{
		String name=key.owner;
		if(keyMap.containsKey(name))
			return false;
		else keyMap.put(name, key);
		return true;
	}
	
	public boolean removeKey(String name)
	{
		if(!keyMap.containsKey(name))
			return false;
		else
			keyMap.remove(name);
		return true;
	}
}

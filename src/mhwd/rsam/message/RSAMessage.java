package mhwd.rsam.message;

import mhwd.rsam.keys.PublicKey;

public class RSAMessage {
	public Byte[] encryptedMessage;
	public Byte[] hash;
	public PublicKey senderPubKey;
	public PublicKey recipientPubKey;
	public String sender;
	public String recipient;
	
}

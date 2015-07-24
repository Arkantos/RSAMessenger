package mhwd.rsam.primes;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class PhiTester {

	public static void main(String[] args) {
		BigInteger p,q,n,m,e,d;
		int count=0;
		long seed=10;
		int bitLength=2048;
		Random rand=new Random(seed);
		p=new BigInteger(bitLength, 32,rand);
		q=new BigInteger(bitLength, 32, rand);
		n=p.multiply(q);
		m=(p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
		do{
			e=new BigInteger(3*bitLength/2, rand);
			count++;
		}while(!e.gcd(m).equals(BigInteger.ONE));
		d=e.modInverse(m);
		
		System.out.println("count: "+count);
		
		/*
		System.out.println("Base 10");
		System.out.println("p: "+p.toString(10));
		System.out.println("q: "+q.toString(10));
		System.out.println("n: "+n.toString(10));
		System.out.println("m: "+m.toString(10));
		System.out.println("e: "+e.toString(10));
		System.out.println("d: "+d.toString(10));
		*/
		
		System.out.println("Base 16");
		System.out.println("p: "+p.toString(16));
		System.out.println("q: "+q.toString(16));
		System.out.println("n: "+n.toString(16));
		System.out.println("m: "+m.toString(16));
		System.out.println("e: "+e.toString(16));
		System.out.println("d: "+d.toString(16));
		
		/*
		System.out.println("Base MAX");
		System.out.println("p: "+p.toString(Character.MAX_RADIX));
		System.out.println("q: "+q.toString(Character.MAX_RADIX));
		System.out.println("n: "+n.toString(Character.MAX_RADIX));
		System.out.println("m: "+m.toString(Character.MAX_RADI2X));
		System.out.println("e: "+e.toString(Character.MAX_RADIX));
		System.out.println("d: "+d.toString(Character.MAX_RADIX));
	*/
		/*
		BigInteger message=new BigInteger("ab234092eff202088492ffc",16);
		System.out.println("message: "+message.toString(Character.MAX_RADIX));
		message=message.modPow(e, n);
		System.out.println("message: "+message.toString(Character.MAX_RADIX));
		message=message.modPow(d, n);
		System.out.println("message: "+message.toString(Character.MAX_RADIX));
		*/
		
		String strMessage1="Lorem ipsum";
		String strMessage2="Lorem ipsum dolor sit amet, consectetur adipiscing elit. In quis quam pharetra, hendrerit urna sit amet, faucibus ipsum. Curabitur posuere placerat scelerisque. Integer non sodales nisi, sed semper dolor. Duis in gravida sapien. Donec fermentum egestas eros, et laoreet dolor pulvinar vitae. Nunc ornare et erat id tristique. Nullam eget commodo lectus. Aliquam non elit gravida, volutpat urna eu, ornare erat. Proin vitae auctor arcu, eu fermentum justo.";
		
		BigInteger biMessage=new BigInteger(strMessage2.getBytes(StandardCharsets.UTF_8));
	//	System.out.println(biMessage.toString(2));
	//	System.out.println(biMessage.toString(10));
		System.out.println(biMessage.toString(16));
		biMessage=biMessage.modPow(e, n);
	//	System.out.println(biMessage.toString(2));
	//	System.out.println(biMessage.toString(10));
		System.out.println(biMessage.toString(16));
		biMessage=biMessage.modPow(d, n);
	//	System.out.println(biMessage.toString(2));
	//	System.out.println(biMessage.toString(10));
		System.out.println(biMessage.toString(16));
		strMessage2=new String(biMessage.toByteArray(), StandardCharsets.UTF_8);
		System.out.println(strMessage2);
		
	}

}

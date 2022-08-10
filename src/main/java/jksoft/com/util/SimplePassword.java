package jksoft.com.util;

public class SimplePassword {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String[] password = {
				"50426",
				"mmty04",
				"miffy2253",
				"ikegaya",
				"kantishna2001",
				"toko1103",
				"ikkobon195",
				"1202",
				"cojicoji",
				"moto9638",
				"miwako0305",
				"terumi625",
				"hanashiro",
				"fujifam920",
				"88888888",
				"runx2500",
				"hiyama24",
				"ekdanceno12",
				"503503",
				"652555",
				"091428",
				"000009",
				"195966",
				"3590045",
				"051822",
				"116121103",
				"181221",
				"10101973",
				"g09kjj5r7n8ncy",
				"0906",
				"161616",
				"240223",
				"r0541",
				"12170122"

		};
		
		try {
			for(String s : password){
				System.out.println(s + " : " + CryptoUtil.encodeUserPassword(s));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

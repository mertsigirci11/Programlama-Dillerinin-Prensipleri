/**
*
* @author Mert Sýðýrcý - B191210078 - mert.sigirci@ogr.sakarya.edu.tr
* @since 03/04/2021
* <p>
* 2020-2021 Bahar Dönemi Programlama Dillerinin Prensipleri Dersi 1. Ödevi
* </p>
* 
*/

//Gerekli kutuphaneleri ekliyoruz.
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Main
{
	public static String sinifAdi="";  //Sinif adini saklamak ve her yerden erismek icin public static seklinde degisken olusturuyoruz. 
	public static Boolean erisimKontrolu=true;  //Okunacak metodun public olup olmadigini sorgulamak amaciyla her yerden erismek icin public static seklinde degisken olusturuyoruz.
	public static HashMap<String,Integer> liste = new HashMap<String,Integer>();  //Super Class'larin isimlerini ve kalitim sayilarini saklamak amaciyla her yerden erismek icin public static seklinde degisken olusturuyoruz.

	public static void sinifBulma(String satir)
	{
		String satirBirlestirme = satir;
		satirBirlestirme = satirBirlestirme.replaceAll("\\s", "");
	    if (satirBirlestirme.startsWith("class")) //okunan satirin class olup olmadigini kontrol ederiz. 
	    {
	        if (satirBirlestirme.endsWith("{")) //sonu "{" ile biten class'lari kontrol ederiz.
	        {
	            //Kalitim içeren sýnýftan veri çekme
	            if (satir.contains(":")) //Kalitim var mi onu kontrol ederiz.
	            {
	                String satirBilgisiYedek = satir; //Kalitim varsa okunan satiri parcalamak icin degisken olustururuz.
	                
	                //Class adina ulasabimek icin gerekli temizleme islemlerini yapariz.
	                satirBilgisiYedek = satirBilgisiYedek.replaceAll("\\s","");
	                satirBilgisiYedek = satirBilgisiYedek.replaceAll("class","");
	                satirBilgisiYedek = satirBilgisiYedek.substring(0,satirBilgisiYedek.indexOf(":"));

	                sinifAdi=satirBilgisiYedek;//Ileride kurucu metot kontrolu icin global degiskene isim atamasi yapariz.
	                System.out.println("Class Adi: " + satirBilgisiYedek);
	            }

	            //Kalýtým içermeyen sýnýftan veri çekme
	            if (!satir.contains(":"))
	            {
	                String satirBilgisiYedek = satir; //Kalitim yoksa okunan satiri parcalamak icin degisken olustururuz.
	                
	                //Class adina ulasabimek icin gerekli temizleme islemlerini yapariz.
	                satirBilgisiYedek = satirBilgisiYedek.replaceAll("\\s","");
	                satirBilgisiYedek = satirBilgisiYedek.replaceAll("class","");
	                satirBilgisiYedek = satirBilgisiYedek.replaceAll("\\{","");

	                sinifAdi=satirBilgisiYedek; //Ileride kurucu metot kontrolu icin global degiskene isim atamasi yapariz.
	                System.out.println("Class Adi: " + satirBilgisiYedek);
	            }
	        }
	        if (!satir.endsWith("{"))//sonu "{" ile bitmeyen class'lari kontrol ederiz.
	        {
	            //Kalitim iceren siniftan veri cekme
	            if (satir.contains(":")) //Kalitim var mi onu kontrol ederiz.
	            {
	                String satirBilgisiYedek = satir; //Kalitim varsa okunan satiri parcalamak icin degisken olustururuz.
	                
	                //Class adina ulasabimek icin gerekli temizleme islemlerini yapariz.
	                satirBilgisiYedek = satirBilgisiYedek.replaceAll("\\s","");
	                satirBilgisiYedek = satirBilgisiYedek.replaceAll("class","");
	                satirBilgisiYedek = satirBilgisiYedek.substring(0, satirBilgisiYedek.indexOf(":"));

	                sinifAdi=satirBilgisiYedek; //Ileride kurucu metot kontrolu icin global degiskene isim atamasi yapariz.
	                System.out.println("Class Adi: " + satirBilgisiYedek);
	            }

	            //Kalýtým içermeyen sýnýftan veri çekme
	            if (!satir.contains(":")) //Kalitim yok mu onu kontrol ederiz.
	            {
	                String satirBilgisiYedek = satir;
	                
	                //Class adina ulasabimek icin gerekli temizleme islemlerini yapariz.
	                satirBilgisiYedek = satirBilgisiYedek.replaceAll("\\s", "");
	                satirBilgisiYedek = satirBilgisiYedek.replaceAll("class","");

	                sinifAdi=satirBilgisiYedek; //Ileride kurucu metot kontrolu icin global degiskene isim atamasi yapariz.
	                System.out.println("Class Adi: " + satirBilgisiYedek);
	            }
	        }
	    }
	}

	public static void metotOzellikleriBulma(String satir)
	{


	    if (satir.contains("void") || satir.contains("int")
	            || satir.contains("float") || satir.contains("string")
	            || satir.contains("char") || satir.contains("bool")
	            || satir.contains("byte") || satir.contains("long")
	            || satir.contains("short") || satir.contains("double")
	            || satir.contains(sinifAdi) && !satir.contains(";")
	            || satir.contains("const"))
	    {
	        if (satir.contains("(") && satir.contains(")"))
	        {
	            if (!satir.contains("main"))//main metodunu es gecmesi için sart
	            {
	                if (!satir.contains("for")&&!satir.contains("if")&&!satir.contains("if else")&&!satir.contains(";"))//for dungusunu es gecmesi icin sart
	                {
	                    String satir1 = satir;//islem yapmak icin okunan satýrý degiskene atariz.
	                    String satir2 = satir;
	                    String metotAdi ;//Metot adýný tutmak için deðiþken tanýmlariz.
	                    String parametre="";//Parametreleri tutmak icin degisken tanimlariz.
	                    String donusTuru="";//Donus turunu tutmak icin degisken tanimlariz.

	                    if (satir1.contains(":"))//Kalitim varsa ona gore parametre atamasi yapilir
	                    {
	                        //Satirdaki parametreyi olusturulan degiskene atariz
	                        parametre=satir1.substring(satir1.indexOf("("),satir1.indexOf(":"));
	                    }
	                    
	                    if (satir1.contains("{") && !satir1.contains(":"))// Kalitim yoksa ve sonda "{" varsa ona gore parametre atamasi yapilir
	                    {
	                        //Satirdaki parametreyi olusturulan degiskene atariz
	                        parametre = satir1.substring(satir1.indexOf("("),satir1.indexOf(")")+1);
	                    }
	                    
	                    if (!satir1.contains("{") && !satir1.contains(":"))//Kalitim yoksa ve sonda "{" yoksa ona gore parametre atamasi yapilir
	                    {
	                        //Satirdaki parametreyi olusturulan degiskene atariz
	                        parametre = satir1.substring(satir1.indexOf("("));
	                    }

	                    satir1 = satir1.replaceAll("\\s","");//Satirlardaki bosluklari sileriz.

	                    if (satir1.startsWith("void"))
	                    {
	                        //Metot void ise metot adini bulmak için bazi silme islemleri yapariz.
	                        satir1 = satir1.replaceAll("void","");
	                        donusTuru="void";
	                    }
	                    
	                    if (satir1.startsWith("int"))
	                    {
	                        //Metot int ise metot adini bulmak için bazi silme islemleri yapariz.
	                        satir1 = satir1.replaceAll("int","");
	                        donusTuru="int";
	                    }
	                    
	                    if (satir1.startsWith("float"))
	                    {
	                        //Metot float ise metot adini bulmak için bazi silme islemleri yapariz.
	                        satir1 = satir1.replaceAll("float","");
	                        donusTuru="float";
	                    }
	                    
	                    if (satir1.startsWith("string"))
	                    {
	                        //Metot string ise metot adini bulmak için bazi silme islemleri yapariz.
	                        satir1 = satir1.replaceAll("string","");
	                        donusTuru="string";
	                    }
	                    
	                    if (satir1.startsWith("char"))
	                    {
	                        //Metot char ise metot adini bulmak için bazi silme islemleri yapariz.
	                        satir1 = satir1.replaceAll("char","");
	                        donusTuru="char";
	                    }
	                    
	                    if (satir1.startsWith("bool"))
	                    {
	                        //Metot bool ise metot adini bulmak için bazi silme islemleri yapariz.
	                        satir1 = satir1.replaceAll("bool","");
	                        donusTuru="bool";
	                    }
	                    
	                    if (satir1.startsWith("byte"))
	                    {
	                        //Metot byte ise metot adini bulmak için bazi silme islemleri yapariz.
	                        satir1 = satir1.replaceAll("byte","");
	                        donusTuru="byte";
	                    }
	                    
	                    if (satir1.startsWith("long"))
	                    {
	                        //Metot long ise metot adini bulmak için bazi silme islemleri yapariz.
	                        satir1 = satir1.replaceAll("long","");
	                        donusTuru="long";
	                    }
	                    
	                    if (satir1.startsWith("short"))
	                    {
	                        //Metot short ise metot adini bulmak için bazi silme islemleri yapariz.
	                        satir1 = satir1.replaceAll("short","");
	                        donusTuru="short";
	                    }
	                    
	                    if (satir1.startsWith("double"))
	                    {
	                        //Metot double ise metot adini bulmak için bazi silme islemleri yapariz.
	                        satir1 = satir1.replaceAll("double","");
	                        donusTuru="double";
	                    }
	                    
	                    if (satir1.startsWith(sinifAdi))
	                    {
	                        //Metot kurucu ise donus tipini ayarlamak için bazi silme islemleri yapariz.
	                        donusTuru="Nesne Adresi";
	                    }
	                    
	                    if (satir1.startsWith("const"))
	                    {
	                    	//Metot const ise donus tipini ayarlamak icin bazi silme islemleri yapariz.
	                        satir1=satir1.replaceAll("const","");

	                        if (satir2.contains("const "))
	                        {
	                            satir2 = satir2.replaceAll("const ","");
	                            donusTuru=satir2.substring(0,satir2.indexOf(" ")+1);
	                        }
	                    }
	                    
	                    if (satir1.contains("operator"))
	                    {
	                    	//Metot operator ise donus tipi ayarlmak icin bazi islemler yapariz.
	                        donusTuru=satir.substring(satir.indexOf(" ")+1,satir.indexOf("operator"));
	                    }

	                    metotAdi = satir1.substring(0,satir1.indexOf("("));//Metot adini buluruz

	                    if (metotAdi.contains("&"))
	                    {
	                    	//Metot adinda & varsa ona göre temizleme islemi yapilir.
	                        metotAdi = satir1.substring(satir1.indexOf("&")+1,satir1.indexOf("("));
	                    }
	                    
	                    if (metotAdi.contains("operator"))
	                    {
	                        metotAdi=satir1.substring(satir1.indexOf("operator"), satir1.indexOf("("));
	                    }
	                    
	                    if (metotAdi.startsWith("~"))
	                    {
	                    	//Metot eger yikici metotsa donus turu atamasi yapilir.
	                        donusTuru="void";
	                    }

	                    String parametreBoslukTemizleme;
	                    parametreBoslukTemizleme=parametre.replaceAll("\\s","");

	                    //Parametresi olmayan metot
	                    if (parametreBoslukTemizleme.equals("()"))
	                    {
	                        int parametreSayisi=0;

	                        System.out.println("    Metot Adi: "+ metotAdi);
	                        System.out.println("        Parametre: "+parametreSayisi);
	                        System.out.println("        Donus Turu: "+donusTuru);
	                        System.out.println("    ---------------------------------");
	                    }

	                    //1'den fazla parametresi olan metot
	                    if (parametre.contains(","))
	                    {

	                        int parametreSayisi;
	                        StringBuilder parametreTipleri= new StringBuilder();
	                        
	                        //Parametre temizleme islemleri yapilir
	                        parametre= parametre.replaceAll("\\(","");
	                        parametre= parametre.replaceAll("\\)","");
	                        
	                      //Parametre temizleme islemleri yapilir
	                        while(parametre.contains(", "))
	                        {
	                            parametre = parametre.replaceAll(", ",",");//yeni
	                        }
	                        parametre=parametre.replaceAll("\\s"," ");

	                        //Temizlenen parametreler "," baz alinarak "array" dizisine atanir.
	                        String[] array=parametre.split(",");
	                        parametreSayisi = array.length;

	                        //array dizisine atanan elemanlar yani parametre turlerinin son temizligi yapilir
	                        for (String s:array)
	                        {
	                            s=s.substring(0,s.lastIndexOf(" "));//indexOf()
	                            if (s.contains(" "))
	                            {
	                                if (s.startsWith(" "))
	                                {
	                                    while(s.startsWith(" "))
	                                    {
	                                        s=s.substring(s.indexOf(" ")+1);
	                                    }

	                                    s=s.substring(0,s.indexOf(" "));
	                                }
	                                
	                                if (!s.startsWith(" ") && s.contains(" "))
	                                {
	                                    s=s.substring(0,s.indexOf(" "));
	                                }
	                            }
	                            //Temizlenen parametre turlerini yazdýrmak icin bir degiskene atadik.
	                            parametreTipleri.append(s).append(",");
	                        }

	                        if (satir1.startsWith(sinifAdi+"()"))
	                        {
	                            donusTuru="Nesne Adresi";
	                        }

	                        if (satir1.startsWith(sinifAdi))
	                        {
	                            donusTuru="Nesne Adresi";
	                        }

	                        parametreTipleri = new StringBuilder(parametreTipleri.substring(0, parametreTipleri.lastIndexOf(",")));
	                        System.out.println("    Metot Adi: "+ metotAdi);
	                        System.out.println("        Parametre: "+parametreSayisi+" ("+parametreTipleri+")");
	                        System.out.println("        Donus Turu: "+donusTuru);
	                        System.out.println("    ---------------------------------");
	                    }

	                    //1 tane parametresi olan metot
	                    if (!parametre.contains("()") && !parametre.contains(",") && !parametreBoslukTemizleme.equals("()"))
	                    {
	                        int parametreSayisi=1;
	                        
	                        //Gerekli temizleme islemleri yapilir
	                        String parametreTipi=parametre.replaceAll("\\(","");
	                        parametreTipi=parametreTipi.replaceAll("\\)","");
	                        parametreTipi=parametreTipi.replaceAll("\\s"," ");
	                        
	                        //Gerekli temizleme islemleri yapilir
	                        if (!parametreTipi.startsWith(" "))
	                        {
	                            parametreTipi=parametreTipi.substring(0,parametreTipi.indexOf(" "));
	                        }
	                        //Gerekli temizleme islemleri yapilir
	                        if (parametreTipi.startsWith(" "))
	                        {
	                            while(parametreTipi.startsWith(" "))
	                            {
	                                parametreTipi=parametreTipi.substring(parametreTipi.indexOf(" ")+1);
	                            }
	                            parametreTipi=parametreTipi.substring(0,parametreTipi.indexOf(" "));
	                        }

	                        System.out.println("    Metot Adi: "+ metotAdi);
	                        System.out.println("        Parametre: "+parametreSayisi+" ("+parametreTipi+")");
	                        System.out.println("        Donus Turu: "+donusTuru);
	                        System.out.println("    ---------------------------------");
	                    }
	                }
	            }
	        }
	    }
	}
	
	public static void kalitimBulma(String satirBilgisi)
	{
		if (satirBilgisi.contains("class") && satirBilgisi.contains(":"))
	    {
	        String satir=satirBilgisi;
	        satir=satir.replaceAll("\\s","");
	        satir=satir.substring(satir.indexOf(":")+1);

	        if (!satirBilgisi.contains(","))//Coklu kalitim yoksa
	        {
	        	//Gerekli satir temizleme islemleri yapilir.
	            if (satir.length()-1==satir.lastIndexOf("{"))
	            {
	                satir=satir.substring(0,satir.lastIndexOf("{"));

	                if (satir.contains("public"))
	                {
	                    satir=satir.replaceAll("public","");
	                }
	                
	                if (satir.contains("protected"))
	                {
	                    satir=satir.replaceAll("protected","");
	                }
	                
	                 
	                //Olusturdugumuz hashmap listesinde satirda okudugumuz veri varsa o key'in value'si 1 artirilir.
	                if (liste.containsKey(satir))
	                {
	                    int kalitimSayisi;
	                    kalitimSayisi = liste.get(satir);
	                    liste.put(satir,kalitimSayisi+1);
	                }
	                
	                //Olusturgumuz hashmap listesinde satirda okunan veri yoksa eklenir ve 1 degerini alir.
	                if (!liste.containsKey(satir))
	                {
	                    liste.put(satir,1);
	                }
	            }
	        }
	        
	        if (satirBilgisi.contains(","))//Coklu kalitim varsa
	        {
	        	//Gerekli satir temizleme islemleri yapilir.
	            if (satir.length()-1 == satir.lastIndexOf("{"))
	            {
	                String[] array;
	                satir=satir.substring(0,satir.lastIndexOf("{"));

	                if (satir.contains("public"))
	                {
	                    satir=satir.replaceAll("public","");
	                }
	                if (satir.contains("protected"))
	                {
	                    satir=satir.replaceAll("protected","");
	                }

	                //Okudugumuz satirdaki verinin virgulleri baz alinarak array dizisine elemanlar atanir.
	                array=satir.split(",");

	                for (String eleman:array)
	                {
	                	//Olusturdugumuz hashmap listesinde, dizideki eleman varsa o key'in value'si 1 artirilir.
	                    if (liste.containsKey(eleman))
	                    {
	                        int kalitimSayisi;
	                        kalitimSayisi = liste.get(eleman);
	                        liste.put(eleman,kalitimSayisi+1);
	                    }
	                    
	                    //Olusturgumuz hashmap listesinde, dizideki eleman yoksa; eleman eklenir ve 1 degerini alir.
	                    if (!liste.containsKey(eleman))
	                    {
	                        liste.put(eleman,1);
	                    }
	                }

	            }
	        }
	    }
	}	
	
	public static void main(String[] args) 
	{
		try(Scanner scanner = new Scanner(new BufferedReader(new FileReader("src/Program.cpp"))))//Dosyayi okumak icin gerekli kodlari yazip dosya yolunu belirttik.
        {
            while(scanner.hasNextLine())
            {

                String satirBilgisi = scanner.nextLine();//Okunan satiri degiskene atadik.
                String erisimKontrolSatiri=satirBilgisi;
                erisimKontrolSatiri=erisimKontrolSatiri.replaceAll("\\s","");//Erisim kontrolu icin tum bosluklarý sildik.
                satirBilgisi = satirBilgisi.replaceAll("\\s"," ");//Tum bosluklari tek bosluk haline getirdik.

                //erisimKontrolu global degiskeninin degerini belirledik
                if (erisimKontrolSatiri.contains("protected:"))
                {
                    erisimKontrolu=false;
                }
                if (erisimKontrolSatiri.contains("private:"))
                {
                    erisimKontrolu=false;
                }
                if (erisimKontrolSatiri.contains("public:"))
                {
                    erisimKontrolu=true;
                }

                sinifBulma(satirBilgisi);//SINIFLARI BULDUK

                if (erisimKontrolu)//METODUN PUBLIC OLUP OLMADIGINI KONTROL ETTIK
                {
                	try
                    {
                        metotOzellikleriBulma(satirBilgisi);//METOT OZELLIKLERINI BULDUK
                    }
                    catch(StringIndexOutOfBoundsException e)
                    {
                        continue;
                    }
                }
                
                kalitimBulma(satirBilgisi);//SUPER CLASS'LARI VE KAC KEZ KALITIM ALINDIKLARINI BULDUK
            }
            
            //SUPER CLASS'LARI VE KAC KEZ KALITIM ALINDIKLARINI YAZDIRDIK
            System.out.println("Super Class'lar: ");
            for (Map.Entry<String, Integer> eleman:liste.entrySet())
            {
                String key = eleman.getKey();
                int value = eleman.getValue();
                System.out.println(key+": "+value);
            }
        }
    	catch (FileNotFoundException e)
        {
            System.out.println("Dosya bulunamadý.");
        }
	}

}

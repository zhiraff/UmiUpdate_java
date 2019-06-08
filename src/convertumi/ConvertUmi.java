/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package convertumi;
import java.io.*;
import java.util.Scanner;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import convertumi.NewJFrame;
import java.awt.Rectangle;
import javafx.scene.paint.Color;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;



/**
 *
 * @author Tima
 */
public class ConvertUmi {
     public static String NSCproj = "";         //Путь к проекту НСЦ
     public static String Pproj = "";           //Путь к прлекту узла растворения 53
     public static String Mproj = "";           //Путь к проекту узла маточников 54
     public static String Eproj = "";           //Путьк к прлекьу узоа экстракции 05
     public static String Sproj = "";           //Путь к проекту узла серебра 54/2
     public static String Gproj = "";           //Путь к проекту узла порошка 55
     public static String deffile = "default.xml";   //Файл в котором описан проект дисплея
     public static String mnemos[] ;            //Массив схем в проекте
      
       // db = 1 --- база аналогов
       // db = 0 --- база дискретов
       // num = 55 --- Номер параетра следом за типом базы
       
    public static void main(String[] args) {
      //  Thread mthread = Thread.currentThread();
        
       Mproj = "D:\\UMIKON_my\\";
       File filefrom = new File(Mproj, deffile);
            
       NewJFrame window = new NewJFrame();
       window.setResizable(false);
       window.setLocationRelativeTo(null);
       window.setTitle("Перенос мнемосхем из проектов в проект v 1.1");
       window.setVisible(true);
     
      
     //  File fileto = new File(NSCproj, deffile);
     
    //        boolean res = cpSchemas(filefrom, fileto);
    //   if (res) {
    //       System.out.println("file copied");
    //   }
     try{
     DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	Document doc = dBuilder.parse(filefrom);
        Node root = doc.getDocumentElement();   //Корневая нода
        NodeList sc = root.getChildNodes();
        System.out.println("Ok");

     }
     catch(Exception e){
         System.out.println("ERROR");
     }
    }
    
        public static boolean cpSchemas(File f1, File f2){
           // Попытаемся выкачать все схемы со всеми файлами из Path1 в Path2
           System.out.println(Mproj+deffile);
           try{
               FileReader fr = new FileReader(f1);
               Scanner scan = new Scanner(fr);
               System.out.println(scan.findInLine("version"));
               if (scan.hasNextLine()){
                   System.out.println(scan.nextLine());
               }

               System.out.println("file 1 opened");
               fr.close();
           }
           catch(Exception e){
               System.out.println("file 1 not opened");
           }
           return true;
       }
        /*
        public static class myThread extends Thread{
            //конструктор
            myThread(){
                super("Второй поток");
                start();
            }
            public void run(){
                       NewJFrame window = new NewJFrame();
                        window.setResizable(false);
                       window.setLocationRelativeTo(null);
                       window.setVisible(true);
            }
        }
*/

}


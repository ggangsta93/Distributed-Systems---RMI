package servidor.utilidades;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class UtilidadesArchivo {
    
    
     public static void escribirRegistroEnArchivo(String cadena, String nombreArchivo){
        File archivo=null; 
        FileWriter fw = null;
        PrintWriter pw = null;
        
        try {
                /*COMPROBACION*/
                archivo = new File("Logs/"+nombreArchivo+".txt");
                if (archivo.createNewFile()) {
                        System.out.println("       File created: " + archivo.getName());
                } else {
                        //System.out.println("File already exists.");
                }
                
                /*ESCRITURA*/                    
                 try {
                        fw = new FileWriter(archivo,true);
                        pw = new PrintWriter(fw);
                        Calendar c = new GregorianCalendar();
                        
                        pw.println(cadena);
                        
                }
                catch(IOException e){
                }finally{
                        try{                    
                                if( null != fw ){fw.close();}     
                        }catch (IOException e2){ 
                        }
                }    
                
        } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
        }
    }
     
     
     public static String leerRegistrosEnArchivo(String nombreArchivo){
        String tiempo="";
        int cantidad = 0;
        File archivo=null; 
        FileReader fr = null;
        BufferedReader br = null;
        
        try {
                /*COMPROBACION*/
                archivo = new File("Logs/"+nombreArchivo+".txt");
                if (archivo.createNewFile()) {
                        System.out.println("       File created: " + archivo.getName());
                } else {
                       // System.out.println("File already exists.");
                }
                
                /*LECTURA*/
                try {
                        fr = new FileReader (archivo);
                        br = new BufferedReader(fr);
                        String hora;
                        String minuto;
                        String linea;

                        Calendar c = new GregorianCalendar();
                        c.add(Calendar.MINUTE, -1);
                        
                        hora=c.get(Calendar.HOUR_OF_DAY)+"";
                        minuto=c.get(Calendar.MINUTE)+"";

                        while((linea=br.readLine())!=null){
                                String[] parts = linea.split(" ");
                                if(parts[1].split(":")[0].equalsIgnoreCase(hora) && parts[1].split(":")[1].equalsIgnoreCase(minuto)){
                                    cantidad++;
                                }
                                
                        }   
                        tiempo=nombreArchivo+" "+hora+":"+minuto+" -> "+cantidad+" Mensajes";
                        System.out.println(tiempo);
                }
                catch (IOException | NumberFormatException e) {
                }finally{/*Cerrando archivo*/
                        try{                    
                           if( null != fr ){fr.close();}                  
                        }catch (IOException e2){ 
                        }
                }
                               
        } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
        }
        
         return tiempo;
     }
     
     public static String leerRegistrosEnArchivoParaPromedio(String nombreArchivo){
        String tiempo="";
        int cantidad = 0;
        File archivo=null; 
        FileReader fr = null;
        BufferedReader br = null;
        
        try {
                /*COMPROBACION*/
                archivo = new File("Logs/"+nombreArchivo+".txt");
                if (archivo.createNewFile()) {
                        System.out.println("       File created: " + archivo.getName());
                } else {
                       // System.out.println("File already exists.");
                }
                
                /*LECTURA*/
                try {
                        fr = new FileReader (archivo);
                        br = new BufferedReader(fr);
                        
                        boolean esPrimeraLinea =true;
                                                     
                        String linea;
                        String primeraLinea="";
                        String ultimaLinea="";
                        while((linea=br.readLine())!=null){
                                if(esPrimeraLinea){
                                    esPrimeraLinea=false;
                                    primeraLinea = linea;                                    
                                }
                                ultimaLinea = linea;
                                cantidad++;           
                        }
                        
                        // Custom date format
                        SimpleDateFormat format = new SimpleDateFormat("yy/MM/dd HH:mm:ss");  

                        
                        String dateStart=primeraLinea.split(" ")[0]+" "+primeraLinea.split(" ")[1] ;
                        String dateStop=ultimaLinea.split(" ")[0]+" "+ultimaLinea.split(" ")[1] ;
                        
                        Date d1 = null;
                        Date d2 = null;
                        try {
                            d1 = format.parse(dateStart);
                            d2 = format.parse(dateStop);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }    

                        // Get msec from each, and subtract.
                        long diff = d2.getTime() - d1.getTime();   
                        long diffMinutes = diff / (60 * 1000);                            

                        System.out.println("Time in minutes: " + diffMinutes + " minutes.");         
                        System.out.println("Contador = " + cantidad); 
                        
                        DecimalFormat formato1 = new DecimalFormat("#.00");
                        float promedioMensajePorMinuto = Float.valueOf(diffMinutes)/(float)cantidad;            
                        tiempo="Minutos transcurridos: "+diffMinutes+"\n"+"No mensajes: "+cantidad+"\n"+formato1.format(promedioMensajePorMinuto)+ " Mensajes/minuto";   
                }
                catch (IOException | NumberFormatException e) {
                }finally{/*Cerrando archivo*/
                        try{                    
                           if( null != fr ){fr.close();}                  
                        }catch (IOException e2){ 
                        }
                }
                               
        } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
        }
        
         return tiempo;
     }
    

    
}

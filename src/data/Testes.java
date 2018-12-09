package data;

import java.awt.BorderLayout;
import java.awt.Container;
import java.io.IOException;
import java.io.PrintStream;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Testes {
    
    Command cm = new Command();
    JFrame frame = new JFrame ("Output");
    FormataTestes ft = new FormataTestes();
    JTextArea textArea = new JTextArea (25, 80);
    
    public void quantidadeUsuarios(String path, String ipAndMasc) throws IOException
    {
        showOutput();
        frame.setVisible(true);
        System.out.print("\n------------------------------- Quantidade de usuários -----------------------------------------------\n");
        cm.executeCommand("nmap -sP "+ipAndMasc, path+"/quant_usuarios.txt", true, 1);
        System.out.print("\n------------------------------------------------------------------------------------------------------\n");
        System.out.print("Formatando quantidade de usuários ...");
        System.out.print("\n------------------------------------------------------------------------------------------------------\n");
        ft.quantidadeUsuarios(path+"/quant_usuarios.txt", path);
    }
    
    public void potenciaSinal(String path, int repeat) throws IOException, InterruptedException
    {
        System.out.print("\n------------------------------------ Potência do Sinal -----------------------------------------------\n");
        for(int i=0; i<repeat; i++)
        {
            cm.executeCommand("iwconfig | grep -i signal", path+"/potencia_sinal.txt", true, i);
            Thread.sleep(1000);
        }
        System.out.print("\n------------------------------------------------------------------------------------------------------\n");
        
        System.out.print("Formatando potência de sinal ...");
        ft.potenciaDoSinal(path+"/potencia_sinal.txt", path+"/");
        System.out.print("\n------------------------------------------------------------------------------------------------------\n");
        
    }
    
    public void latencia(String path, String ipServer, int repeat) throws IOException, InterruptedException
    {
        System.out.print("\n--------------------------------------------- Atraso -------------------------------------------------\n");
        for (int i = 0; i <= repeat; i++) {
            cm.executeCommand("ping "+ipServer+" -c 1", path+"/atraso.txt", true, i);
            Thread.sleep(1000);
        }
        System.out.print("\n------------------------------------------------------------------------------------------------------\n");
        System.out.print("Formatando atraso ...\n\n");
        ft.atraso(path+"/atraso.txt", path+"/");
        System.out.print("\n------------------------------------------------------------------------------------------------------\n");
    }
    
    public void cliente(String path, String ipServidor, String param, int time, String tag) throws IOException
    {
        System.out.print("\n------------------------------------- TESTES IPERF3 --------------------------------------------------\n");
        cm.executeCommand("iperf3 -c "+ipServidor+" "+param+" -t "+time+" -f m -i 1", path+"/cliente"+tag+".txt", true, 1);
        
        System.out.print("\n------------------------------------------------------------------------------------------------------\n");
        System.out.print("Formatando dados iperf..");
        System.out.print("\n------------------------------------------------------------------------------------------------------\n");
        
        //Chama métodos para formatar os testes 
        if(tag.contentEquals("_TCP"))
        {
           ft.larguraDeBanda(path+"/cliente"+tag+".txt", path+"/", tag, time);
        }
        if(tag.contentEquals("_UDP"))
        {
            ft.larguraDeBanda(path+"/cliente"+tag+".txt", path+"/", tag, time);
            ft.perdaDePacote(path+"/cliente"+tag+".txt", path+"/", time);
            ft.jitter(path+"/cliente"+tag+".txt", path+"/", time);
        }
        System.out.println("\n------------------------------------------------------------------------------------------------------\n");
    }
    
    public void showOutput()
    {
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        Container contentPane = frame.getContentPane ();
        contentPane.setLayout (new BorderLayout ());
        contentPane.add (new JScrollPane (textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), BorderLayout.CENTER);
        frame.pack ();
        OutStream out = new OutStream(textArea);
        System.setOut (new PrintStream (out));
    }
}

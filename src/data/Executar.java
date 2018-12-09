package data;

import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Executar {
    
    static Testes exec = new Testes();
    static JFrame frame = new JFrame ("");
    static ManageDirectory md = new ManageDirectory();
    static HorarioTeste ht = new HorarioTeste();
    static FormataTestes ft = new FormataTestes();
    static Command cmf = new Command();
    
    public static void main(String[] args) throws IOException, InterruptedException
    {
        executaTeste();
    }

    private static void executaTeste() throws IOException, InterruptedException
    {
        //Cria o diretório padrão do programa
        md.createDirectory(System.getProperty("user.home")+"/NetworkAnalyser");
        
        //Cria o diretório do local de teste
        String localTeste = JOptionPane.showInputDialog("Local do Teste");
        md.createDirectory(System.getProperty("user.home")+"/NetworkAnalyser/"+localTeste);
        
        
        //Cria o diretório com o respectivo número do teste
        String numTeste = JOptionPane.showInputDialog("Número do teste");
        md.createDirectory(System.getProperty("user.home")+"/NetworkAnalyser/"+localTeste+"/"+"teste_"+numTeste);
        
        //Cria arquivo com o horário do teste
        ht.dateTime(System.getProperty("user.home")+"/NetworkAnalyser/"+localTeste+"/"+"teste_"+numTeste, "/horario.txt");
        
        //Números de pontos que definem a matriz do teste
        String numPontos = JOptionPane.showInputDialog("Nº DE PONTOS");
        int n = Integer.parseInt(numPontos);
        
        String ipAndMask = JOptionPane.showInputDialog("IP/MASC [REDE]");
        String ipServidor = JOptionPane.showInputDialog("IP [SERVIDOR]");
        String time = JOptionPane.showInputDialog("Time");
        int t = Integer.parseInt(time);
        String bandwidth = JOptionPane.showInputDialog("Bandwidth [UDP][ex.: 10m]");
        String winSize = JOptionPane.showInputDialog("Size buffer [TCP][ex.: 64KB]");
        
        String path = "";
        
        for (int i = 1; i <= n; i++)
        {
            md.createDirectory(System.getProperty("user.home")+"/NetworkAnalyser/"+localTeste+"/"+"teste_"+numTeste+"/p"+i);
        
            path = System.getProperty("user.home")+"/NetworkAnalyser/"+localTeste+"/"+"teste_"+numTeste+"/p"+i;
            
            //TESTES
            exec.quantidadeUsuarios(path+"/", ipAndMask);
            exec.potenciaSinal(path, t);
            exec.latencia(path, ipServidor, t);
            
            //TESTES IPERF COM TCP (Largura de banda e atraso)
            withTCP(path, ipServidor, "-4 -V -w "+winSize, t);
            
            //TESTES IPERF COM UDO (Largura de banda, Perda de pacotes e Jitter)
            withUDP(path, ipServidor, "-u -4 -V -R -b "+bandwidth, t);
            
            JOptionPane.showMessageDialog(frame, "Ponto "+i+" coletado!");
            if(i+1<=n)
            {
                JOptionPane.showMessageDialog(frame, "Vá para o ponto "+(i+1));
                JOptionPane.showMessageDialog(frame, "Clique em 'OK' para coletar!");
            }
            
        }
        
        JOptionPane.showMessageDialog(frame, "Formatação concluída!");
        JOptionPane.showMessageDialog(frame, "Teste finalizado!");
        String reeniciarTeste = JOptionPane.showInputDialog("Fazer um novo teste?\n\n1. Sim\n2.Não");
        
        if(reeniciarTeste.equals("1"))
        {
            executaTeste();
        }
        else { frame.setVisible(false); System.exit(0); }
    }
    
    private static void withTCP(String path, String ipServidor, String param, int time) throws IOException
    {
        
        exec.cliente(path, ipServidor, param, time, "_TCP");
        
    }
    
    private static void withUDP(String path, String ipServidor, String param, int time) throws IOException
    {
        exec.cliente(path, ipServidor, param, time, "_UDP");
    }
}

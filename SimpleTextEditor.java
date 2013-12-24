
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class SimpleTextEditor
{
    private JTextArea textSpace;
    private JScrollPane scrollPane;
    private JFrame frame;
    private JPanel panel;
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem newMenuItem;
    private JMenuItem openMenuItem;
    private JMenuItem saveMenuItem;
    private JMenuItem exitMenuItem;
    private JFileChooser saveFileChooser;
    private JFileChooser openFileChooser;
    private boolean savedFlag;
    private boolean keyPressedFlag;
    private boolean isOpenFlag;
    private String path;
   
    public void go()   //Constrói a GUI
    {
        frame = new JFrame("Editor de texto");
        frame.setSize(1100,700);
        
        Font font = new Font("sanserif",Font.BOLD,16);
        panel = new JPanel();      
        textSpace = new JTextArea(31,76);
        textSpace.setFont(font);
        textSpace.setLineWrap(true);
        textSpace.setWrapStyleWord(true);
        scrollPane = new JScrollPane(textSpace);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        panel.add(scrollPane);
       
        frame.add(panel);
        
        menuBar = new JMenuBar();
        menuBar.setBackground(new Color(0,170,250));
        fileMenu = new JMenu("Arquivo");
        newMenuItem = new JMenuItem("Novo arquivo");
        openMenuItem = new JMenuItem("Abrir arquivo");
        saveMenuItem = new JMenuItem("Salvar arquivo");
        exitMenuItem = new JMenuItem("Sair");
        newMenuItem.addActionListener(new NewMenuItemListener());
        openMenuItem.addActionListener(new OpenMenuItemListener());
        saveMenuItem.addActionListener(new SaveMenuItemListener());
        exitMenuItem.addActionListener(new ExitMenuItemListener());
        textSpace.addKeyListener(new KeyTest());
        fileMenu.add(newMenuItem);
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.add(exitMenuItem);
        menuBar.add(fileMenu);
        
        frame.setJMenuBar(menuBar);
        frame.getContentPane().add(BorderLayout.CENTER,panel);
        frame.setVisible(true);
        
        savedFlag = false;
        keyPressedFlag = false;
        isOpenFlag = false;
        path = null;
    }
        
    public void saveFile(File file)
    {
        try
        {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            bufferedWriter.write(textSpace.getText());
            bufferedWriter.flush();
            
        } catch(IOException ioex)
          {
             JOptionPane.showMessageDialog(frame, "Ocorreu um erro","Erro",JOptionPane.ERROR_MESSAGE);
             ioex.printStackTrace();
          }
    }
    
    public void loadFile(File file)
    {
        try
        {
            //Em primeiro lugar, criamos um objeto FileReader, que é um leitor de arquivos. Um objeto
            //FileReader recebe um objeto File, que representa o arquivo que ele lerá.
            //
            //Depois, encadeamos o objeto FileReader a um objeto BufferedReader, para uma leitura mais
            //eficiente. O BufferedReader só voltará ao arquivo para ler mais texto quando ele estiver
            //totalmente vazio. É parecido com o BufferedWriter, que armazena texto até o limite, e só
            //então vai ao arquivo para gravar os dados.
            
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line = null;
            
            while ((line = bufferedReader.readLine()) != null)
            {
                textSpace.append(line + "\n");
                
                //Aqui queremos dizer: leia uma linha de texto do buffer, e exiba-a na tela do
                //editor. Continue até nao existirem mais linhas no buffer (null).
            }
            
        } catch(IOException ioex)
          {
            JOptionPane.showMessageDialog(frame, "Ocorreu um erro","Erro",JOptionPane.ERROR_MESSAGE);
            ioex.printStackTrace();
          }
    }
    
    public class NewMenuItemListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
             if (keyPressedFlag == false)
             {
                 textSpace.setText("");
                 isOpenFlag = false;
             }
             else
             {
                 int option1 = JOptionPane.showConfirmDialog(frame,"O arquivo não foi salvo. Deseja salvá-lo?");
                 
                 if (option1 == 0)
                 {
                     saveFileChooser = new JFileChooser();
                     saveFileChooser.showSaveDialog(frame);
                     File file = saveFileChooser.getSelectedFile();
                     
                     if (file.exists())
                     {
                        int option2 = JOptionPane.showConfirmDialog(frame,"Já existe um arquivo com este nome. Deseja sobrescrevê-lo?");
                        
                        if (option2 == 0)
                        {
                           saveFile(file);
                           textSpace.setText("");
                           keyPressedFlag = false;
                           isOpenFlag = false;
                        }
                     } 
                     else
                     {
                        saveFile(file);
                        textSpace.setText("");
                        keyPressedFlag = false;
                        isOpenFlag = false;
                     }
                 }
                 else
                 {
                     textSpace.setText("");
                     keyPressedFlag = false;
                     isOpenFlag = false;
                 }
             }
        }
    }
    
    public class OpenMenuItemListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            if (keyPressedFlag == false)
            {
                openFileChooser = new JFileChooser();
                openFileChooser.showOpenDialog(frame);
                textSpace.setText("");
                loadFile(openFileChooser.getSelectedFile());
                path = openFileChooser.getSelectedFile().getPath();
                isOpenFlag = true;
            }
            else
            {
                int option1 = JOptionPane.showConfirmDialog(frame,"Deseja salvar as alterações do arquivo?");
                
                if (option1 == 0)
                {
                    saveFile(new File(path));
                    textSpace.setText("");
                    loadFile(openFileChooser.getSelectedFile());
                    path = openFileChooser.getSelectedFile().getPath();
                    keyPressedFlag = false;
                    isOpenFlag = true;
                }
                else
                {
                   openFileChooser = new JFileChooser();
                   openFileChooser.showOpenDialog(frame);
                   textSpace.setText("");
                   loadFile(openFileChooser.getSelectedFile());
                   path = openFileChooser.getSelectedFile().getPath();
                   keyPressedFlag = false;
                   isOpenFlag = true;
                 }
            }   
        }
    }
    
    public class SaveMenuItemListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            if (isOpenFlag == false && savedFlag == false)
            {
                saveFileChooser = new JFileChooser();
                saveFileChooser.showSaveDialog(frame);
                File file = saveFileChooser.getSelectedFile();
                saveFile(file);
                path = saveFileChooser.getSelectedFile().getPath();
                savedFlag = true;
            }
            
            if (savedFlag == true)
            {
                saveFile(new File(path));
            }           
            
            if (isOpenFlag == true)
            {
                saveFile(new File(path));
            }
        }
    }
    
    public class ExitMenuItemListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            if (savedFlag == false)
            {
                int option1 = JOptionPane.showConfirmDialog(frame,"O arquivo não foi salvo. Deseja salvá-lo antes de sair?");
                
                if (option1 == 0)
                {
                    saveFileChooser = new JFileChooser();
                    saveFileChooser.showSaveDialog(frame);
                    File file = saveFileChooser.getSelectedFile();
 
                    if (file.exists())
                    {
                        int option2 = JOptionPane.showConfirmDialog(frame, "Já existe um arquivo com este nome. Deseja sobrescrevê-lo?");
                
                        if (option2 == 0)
                        {
                             saveFile(file);
                             frame.dispose();
                             System.exit(0);
                        }
                    }
                    else
                    {
                        saveFile(file);
                        frame.dispose();
                        System.exit(0);
                    }
                }
                else
                {
                    frame.dispose();
                    System.exit(0);
                }
            }
            else
            {
                frame.dispose();
                System.exit(0);
            }
        }
    }
    
    public class KeyTest implements KeyListener
    {
        public void keyTyped(KeyEvent event)
        {
            keyPressedFlag = true;
        }

        public void keyPressed(KeyEvent event)
        {    
          keyPressedFlag = true;
        }

        public void keyReleased(KeyEvent event)
        {
        }       
    }
    
    public static void main(String[] args)
    {
        SimpleTextEditor simpleTextEditor = new SimpleTextEditor();
        simpleTextEditor.go();
    }
}

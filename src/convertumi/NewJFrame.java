/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package convertumi;
import java.io.*;
import javax.xml.parsers.*;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import java.nio.channels.FileChannel;
import java.util.*;
import java.util.concurrent.TimeUnit;
import convertumi.About;
import java.awt.Image;
import java.net.URL;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.security.MessageDigest;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.xml.bind.DatatypeConverter;
import javax.xml.transform.OutputKeys;
import org.w3c.dom.Element;


/**
 *
 * @author Tima
 */
public class NewJFrame extends javax.swing.JFrame {
     public static String NSCproj = "";         //Путь к проекту НСЦ
     public static String Pproj = "";           //Путь к прлекту узла растворения 53
     public static String Mproj = "";           //Путь к проекту узла маточников 54
     public static String Eproj = "";           //Путьк к прлекьу узоа экстракции 05
     public static String Sproj = "";           //Путь к проекту узла серебра 54/2
     public static String Gproj = "";           //Путь к проекту узла порошка 55
     public static String deffile = "default.xml";   //Файл в котором описан проект дисплея
     public static File[] mnemos1 = new File[30];            //Массив схем в проекте 1
     public static File[] mnemos2 = new File[30];             //Массив схем в проекте 2
     public static File[] mnemos3 = new File[30];             //Массив схем в проекте 3
     public static File[] mnemos4 = new File[30];             //Массив схем в проекте 4
     public static File[] mnemos5 = new File[30];             //Массив схем в проекте 5
     public static File[] mnemos6 = new File[30];            //Массив схем в проекте 6
     public static String logfile = "Covert_UPP_Log.txt";       //Файл лог
     public static String[] textlog = new String[1000];             //Массив строк в логфайл
     public static File CurDir = new File(".");                 //Текущая директория
     public static String configur = "ConvertUPP_config.txt";          //Файл конфигурации настроек
     public static String line_separator = System.getProperty("line.separator");
     public static int indexlog=0;                              //Индекс для массива строк логфайла
     public static int[] CountM = {0,0,0,0,0,0,0};                   //реальные длины массивов
     public static String preff;                            //Префикс названия
     public static String preff2 = "УПП_";
     public static String podval = ".*свод.*";                          //Мнемосхема подвал
     public static String btm = "BTM";                      //Комментарий для кнопки в подвале
                                                            //button to mnemoscheme
     public static String BS = "BS";                        //Коммент для фрейма в котором подвал
                                                            // Basement
     public static String TITLE = "Title";                  //Коммент для заголовка окна
     public static String BARS = "BARS";                    //коммент для прямоуг барсов
     public static String PLT_UPP_E = ".*[Ээ][Кк][Сс][Тт][Рр].*";
                                                            //Постфикс в элементах палетки для экстракции
     public static String PLT_UPP_P = ".*[Рр][Аа][Сс][Тт][Вв].*";         
                                                            //Постфикс в элементах палетки для растворения
     public static String PLT_UPP_S = ".*[сС][еЕ][Рр][Ее][Бб].*";         
                                                            //Постфикс в элементах палетки для серебра
     public static String PLT_UPP_G = ".*[Пп][Оо][Рр][Оо][Шш].*";         
                                                            //Постфикс в элементах палетки для порошка
     public static String PLT_UPP_A = ".*[Аа][Мм][Ее][Рр][Ии].*";         
                                                            //Постфикс в элементах палетки для америция
     public static String PLT_UPP_M = ".*[Мм][Аа][Тт][Оо][Чч].*";         
                                                            //Постфикс в элементах палетки для маточников     
     public static String BTMI = "BTMI";                     //Button to mnemos invers
     public static String DNT = "DNT";                      // Коммент к переменным которых ненадо переподвязывать
                                                            // Do not touch
     public static String justlook = "Просмотр";            //Слово на мнемосхеме просмотра (водяной знак)
     public static Node JLOOK;
     public static Boolean allfiles = false;                //Выгружать ли все файлы подложки
     public static String watermark = "00043520-85-300";    //Переменная параметров для водянного знака
                                                            // цвет прозрачность высота букв
     public static String pomp  =   ".*[Нн][Аа][Сс][Оо][Сс].*"; // Маска для насосв
     public static String pulse =   ".*[Пп][Уу][Лл][Ьь][Сс].*"; // Маска для пульсаторв
     
     public NewJFrame() {
        URL resURL = getClass().getResource("icon.png");
        Image img = new ImageIcon(resURL).getImage();
        setIconImage(img);
        initComponents();
        jTextField20.setText(CurDir.getAbsolutePath());
        open_config();
        jTextField2.setEditable(jCheckBox5.isSelected());
        jButton3.setEnabled(jCheckBox5.isSelected());
        jTextField3.setEditable(jCheckBox2.isSelected());
        jButton4.setEnabled(jCheckBox2.isSelected());
        jTextField4.setEditable(jCheckBox3.isSelected());
        jButton5.setEnabled(jCheckBox3.isSelected());
        jTextField5.setEditable(jCheckBox4.isSelected());
        jButton6.setEnabled(jCheckBox4.isSelected());
        jTextField6.setEditable(jCheckBox6.isSelected());
        jButton7.setEnabled(jCheckBox6.isSelected());
        jTextField7.setEditable(jCheckBox7.isSelected());
        jButton8.setEnabled(jCheckBox7.isSelected());
        jCheckBox28.setEnabled(jCheckBox5.isSelected());
        jCheckBox8.setEnabled(jCheckBox5.isSelected());   
        jCheckBox29.setEnabled(jCheckBox2.isSelected());
        jCheckBox9.setEnabled(jCheckBox2.isSelected());
        jCheckBox30.setEnabled(jCheckBox3.isSelected());
        jCheckBox10.setEnabled(jCheckBox3.isSelected());
        jCheckBox31.setEnabled(jCheckBox4.isSelected());
        jCheckBox11.setEnabled(jCheckBox4.isSelected());
        jCheckBox32.setEnabled(jCheckBox6.isSelected());
        jCheckBox12.setEnabled(jCheckBox6.isSelected());
        jCheckBox33.setEnabled(jCheckBox7.isSelected());
        jCheckBox13.setEnabled(jCheckBox7.isSelected());
        jTextField20.setEditable(jCheckBox1.isSelected());
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fileChooser = new javax.swing.JFileChooser();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jCheckBox4 = new javax.swing.JCheckBox();
        jCheckBox5 = new javax.swing.JCheckBox();
        jCheckBox6 = new javax.swing.JCheckBox();
        jCheckBox7 = new javax.swing.JCheckBox();
        jButton9 = new javax.swing.JButton();
        jCheckBox8 = new javax.swing.JCheckBox();
        jCheckBox9 = new javax.swing.JCheckBox();
        jCheckBox10 = new javax.swing.JCheckBox();
        jCheckBox11 = new javax.swing.JCheckBox();
        jCheckBox12 = new javax.swing.JCheckBox();
        jCheckBox13 = new javax.swing.JCheckBox();
        jTextField20 = new javax.swing.JTextField();
        progress = new javax.swing.JProgressBar();
        comm = new javax.swing.JLabel();
        watching = new javax.swing.JLabel();
        jCheckBox26 = new javax.swing.JCheckBox();
        jCheckBox27 = new javax.swing.JCheckBox();
        jCheckBox28 = new javax.swing.JCheckBox();
        jCheckBox29 = new javax.swing.JCheckBox();
        jCheckBox30 = new javax.swing.JCheckBox();
        jCheckBox31 = new javax.swing.JCheckBox();
        jCheckBox32 = new javax.swing.JCheckBox();
        jCheckBox33 = new javax.swing.JCheckBox();
        jCheckBox34 = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jTextField12 = new javax.swing.JTextField();
        jTextField13 = new javax.swing.JTextField();
        jTextField14 = new javax.swing.JTextField();
        jTextField15 = new javax.swing.JTextField();
        jTextField16 = new javax.swing.JTextField();
        jTextField17 = new javax.swing.JTextField();
        jTextField18 = new javax.swing.JTextField();
        jTextField19 = new javax.swing.JTextField();
        jCheckBox14 = new javax.swing.JCheckBox();
        jCheckBox15 = new javax.swing.JCheckBox();
        jCheckBox16 = new javax.swing.JCheckBox();
        jCheckBox17 = new javax.swing.JCheckBox();
        jCheckBox18 = new javax.swing.JCheckBox();
        jCheckBox19 = new javax.swing.JCheckBox();
        jCheckBox20 = new javax.swing.JCheckBox();
        jCheckBox21 = new javax.swing.JCheckBox();
        jCheckBox22 = new javax.swing.JCheckBox();
        jCheckBox23 = new javax.swing.JCheckBox();
        jCheckBox24 = new javax.swing.JCheckBox();
        jCheckBox25 = new javax.swing.JCheckBox();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jTextField21 = new javax.swing.JTextField();
        jTextField22 = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jTextField23 = new javax.swing.JTextField();
        jTextField24 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        PCPA = new javax.swing.JFormattedTextField();
        PCPD = new javax.swing.JFormattedTextField();
        PCEA = new javax.swing.JFormattedTextField();
        PCED = new javax.swing.JFormattedTextField();
        PCGA = new javax.swing.JFormattedTextField();
        PCGD = new javax.swing.JFormattedTextField();
        PCSA = new javax.swing.JFormattedTextField();
        PCSD = new javax.swing.JFormattedTextField();
        PCAA = new javax.swing.JFormattedTextField();
        PCAD = new javax.swing.JFormattedTextField();
        PCMA = new javax.swing.JFormattedTextField();
        PCMD = new javax.swing.JFormattedTextField();
        jCheckBox35 = new javax.swing.JCheckBox();
        jCheckBox36 = new javax.swing.JCheckBox();
        jCheckBox37 = new javax.swing.JCheckBox();
        jCheckBox38 = new javax.swing.JCheckBox();
        jCheckBox39 = new javax.swing.JCheckBox();
        jCheckBox40 = new javax.swing.JCheckBox();
        jCheckBox41 = new javax.swing.JCheckBox();
        jCheckBox42 = new javax.swing.JCheckBox();
        jCheckBox43 = new javax.swing.JCheckBox();
        jCheckBox44 = new javax.swing.JCheckBox();
        jCheckBox45 = new javax.swing.JCheckBox();
        jCheckBox46 = new javax.swing.JCheckBox();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        Open = new javax.swing.JMenuItem();
        Save = new javax.swing.JMenuItem();
        Exit = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        About = new javax.swing.JMenuItem();

        fileChooser.setDialogTitle("Выберите директорию проекта");
        fileChooser.setFileSelectionMode(javax.swing.JFileChooser.DIRECTORIES_ONLY);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(226, 226, 226));
        setLocation(new java.awt.Point(100, 100));

        jLabel1.setText("0. Проект НСЦ");

        jLabel2.setText("1. UPP_P");

        jLabel3.setText("2. UPP_E");

        jLabel4.setText("3. UPP_G");

        jLabel5.setText("4. UPP_S");

        jLabel6.setText("5. UPP_A");

        jLabel7.setText("6. UPP_M");

        jTextField1.setText("D:\\UPP_NEW\\");
            jTextField1.addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusLost(java.awt.event.FocusEvent evt) {
                    jTextField1FocusLost(evt);
                }
            });

            jTextField2.setText("D:\\UMIKON_my");

            jButton1.setText("Выполнить");
            jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    jButton1MouseClicked(evt);
                }
            });
            jButton1.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton1ActionPerformed(evt);
                }
            });

            jButton2.setText("jButton2");
            jButton2.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton2ActionPerformed(evt);
                }
            });

            jButton3.setText("jButton3");
            jButton3.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton3ActionPerformed(evt);
                }
            });

            jButton4.setText("jButton4");
            jButton4.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton4ActionPerformed(evt);
                }
            });

            jButton5.setText("jButton5");
            jButton5.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton5ActionPerformed(evt);
                }
            });

            jButton6.setText("jButton6");
            jButton6.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton6ActionPerformed(evt);
                }
            });

            jButton7.setText("jButton7");
            jButton7.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton7ActionPerformed(evt);
                }
            });

            jButton8.setText("jButton8");
            jButton8.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton8ActionPerformed(evt);
                }
            });

            jCheckBox1.setSelected(true);
            jCheckBox1.setText("сохранить лог");
            jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jCheckBox1ActionPerformed(evt);
                }
            });

            jCheckBox2.setSelected(true);
            jCheckBox2.setText("вкл");
            jCheckBox2.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jCheckBox2ActionPerformed(evt);
                }
            });

            jCheckBox3.setSelected(true);
            jCheckBox3.setText("вкл");
            jCheckBox3.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jCheckBox3ActionPerformed(evt);
                }
            });

            jCheckBox4.setSelected(true);
            jCheckBox4.setText("вкл");
            jCheckBox4.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jCheckBox4ActionPerformed(evt);
                }
            });

            jCheckBox5.setSelected(true);
            jCheckBox5.setText("вкл");
            jCheckBox5.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    jCheckBox5MouseClicked(evt);
                }
            });
            jCheckBox5.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jCheckBox5ActionPerformed(evt);
                }
            });
            jCheckBox5.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
                public void propertyChange(java.beans.PropertyChangeEvent evt) {
                    jCheckBox5PropertyChange(evt);
                }
            });

            jCheckBox6.setSelected(true);
            jCheckBox6.setText("вкл");
            jCheckBox6.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jCheckBox6ActionPerformed(evt);
                }
            });

            jCheckBox7.setSelected(true);
            jCheckBox7.setText("вкл");
            jCheckBox7.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jCheckBox7ActionPerformed(evt);
                }
            });

            jButton9.setText("Выход");
            jButton9.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    jButton9MouseClicked(evt);
                }
            });
            jButton9.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton9ActionPerformed(evt);
                }
            });

            jCheckBox8.setText("Base/звуки");

            jCheckBox9.setText("Base/звуки");

            jCheckBox10.setText("Base/звуки");

            jCheckBox11.setText("Base/звуки");

            jCheckBox12.setText("Base/звуки");

            jCheckBox13.setText("Base/звуки");

            jTextField20.setText("C:\\");
                jTextField20.addFocusListener(new java.awt.event.FocusAdapter() {
                    public void focusLost(java.awt.event.FocusEvent evt) {
                        jTextField20FocusLost(evt);
                    }
                });

                progress.setForeground(new java.awt.Color(51, 51, 255));
                progress.setStringPainted(true);

                watching.setText(".");

                jCheckBox26.setSelected(true);
                jCheckBox26.setText("+префикс");

                jCheckBox27.setText("+edit");

                jCheckBox28.setText("PLT");

                jCheckBox29.setText("PLT");

                jCheckBox30.setText("PLT");

                jCheckBox31.setText("PLT");

                jCheckBox32.setText("PLT");

                jCheckBox33.setText("PLT");

                jCheckBox34.setText("WM");

                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                    jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comm, javax.swing.GroupLayout.PREFERRED_SIZE, 479, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(watching, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(0, 0, Short.MAX_VALUE)
                                    .addComponent(progress, javax.swing.GroupLayout.PREFERRED_SIZE, 479, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jCheckBox10)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel3)
                                                .addGap(18, 18, 18)
                                                .addComponent(jCheckBox2)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jCheckBox29)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jCheckBox9)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jCheckBox11)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jCheckBox12)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jCheckBox13)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                    .addComponent(jLabel1)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(jCheckBox26)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(jCheckBox27))
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                    .addComponent(jLabel4)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(jCheckBox3)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(jCheckBox30))
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                    .addComponent(jLabel5)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(jCheckBox4)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(jCheckBox31))
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                    .addComponent(jLabel6)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(jCheckBox6)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(jCheckBox32))
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                            .addComponent(jLabel7)
                                                            .addGap(18, 18, 18)
                                                            .addComponent(jCheckBox7))
                                                        .addComponent(jButton9))
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jCheckBox1)
                                                        .addComponent(jCheckBox33))))
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                    .addGap(3, 3, 3)
                                                    .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(jButton1))
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(jCheckBox34)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel2)
                                            .addGap(18, 18, 18)
                                            .addComponent(jCheckBox5)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jCheckBox28)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jCheckBox8)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGap(0, 0, Short.MAX_VALUE))))
                        .addGap(0, 0, Short.MAX_VALUE))
                );
                jPanel1Layout.setVerticalGroup(
                    jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2)
                            .addComponent(jCheckBox26)
                            .addComponent(jCheckBox27)
                            .addComponent(jCheckBox34))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3)
                            .addComponent(jCheckBox5)
                            .addComponent(jCheckBox8)
                            .addComponent(jCheckBox28))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jButton4)
                            .addComponent(jCheckBox2)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCheckBox9)
                            .addComponent(jCheckBox29))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jButton5)
                            .addComponent(jCheckBox3)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCheckBox10)
                            .addComponent(jCheckBox30))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton6)
                            .addComponent(jCheckBox4)
                            .addComponent(jCheckBox11)
                            .addComponent(jCheckBox31))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton7)
                            .addComponent(jCheckBox6)
                            .addComponent(jCheckBox12)
                            .addComponent(jCheckBox32))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton8)
                            .addComponent(jCheckBox7)
                            .addComponent(jCheckBox13)
                            .addComponent(jCheckBox33))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jCheckBox1)
                            .addComponent(jButton9)
                            .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(progress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(watching)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comm))
                );

                jTabbedPane1.addTab("Директории проектов", jPanel1);

                jLabel8.setText("1. UPP_P");

                jLabel9.setText("2. UPP_E");

                jLabel10.setText("3. UPP_G");

                jLabel11.setText("4. UPP_S");

                jLabel12.setText("5. UPP_A");

                jLabel13.setText("6. UPP_M");

                jLabel14.setText("База А");

                jLabel15.setText("База D");

                jTextField8.setText("0");

                jCheckBox14.setSelected(true);

                jCheckBox15.setSelected(true);

                jCheckBox16.setSelected(true);

                jCheckBox17.setSelected(true);

                jCheckBox18.setSelected(true);

                jCheckBox19.setSelected(true);

                jCheckBox20.setSelected(true);

                jCheckBox21.setSelected(true);

                jCheckBox22.setSelected(true);

                jCheckBox23.setSelected(true);

                jCheckBox24.setSelected(true);

                jCheckBox25.setSelected(true);

                jLabel16.setText("1 шкаф УСО");

                jLabel17.setText("2 шкаф УСО");

                jLabel18.setText("Граница");

                jTextField21.setText("4000");

                jTextField22.setText("7000");

                jLabel19.setText("База А");

                jLabel20.setText("База D");

                jLabel21.setText("База А");

                jLabel22.setText("База D");

                jTextField23.setText("0");

                jTextField24.setText("0");

                javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
                jPanel2.setLayout(jPanel2Layout);
                jPanel2Layout.setHorizontalGroup(
                    jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel14))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBox14)
                            .addComponent(jCheckBox19)
                            .addComponent(jCheckBox18)
                            .addComponent(jCheckBox17)
                            .addComponent(jCheckBox16)
                            .addComponent(jCheckBox15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jCheckBox20))
                                            .addComponent(jLabel15))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(jLabel19)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel20))
                                            .addComponent(jLabel18)))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jCheckBox25))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jCheckBox22))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jCheckBox24))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jCheckBox23)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jCheckBox21)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField23, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel21))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel22)
                                    .addComponent(jTextField24, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(65, 65, 65))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel17)
                        .addGap(104, 104, 104))
                );
                jPanel2Layout.setVerticalGroup(
                    jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap(13, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel16))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel21)
                                    .addComponent(jLabel22))
                                .addGap(20, 20, 20))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addGap(17, 17, 17))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel19)
                                .addComponent(jLabel20)))
                        .addGap(1, 1, 1)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel8)
                                            .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jCheckBox14))
                                    .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jCheckBox15)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel9)
                                        .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jCheckBox20)
                                .addGap(10, 10, 10)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jCheckBox21)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextField23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextField24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBox16)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel10)
                                .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCheckBox22))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBox23)
                            .addComponent(jCheckBox17)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel11)
                                .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jCheckBox24)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jCheckBox25))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jCheckBox18)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel12)
                                        .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jCheckBox19)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel13)
                                            .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jTextField19, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(66, Short.MAX_VALUE))
                );

                jTabbedPane1.addTab("Сдвиги привязок проектов", jPanel2);

                jLabel23.setText("База A");

                jLabel24.setText("База D");

                jLabel25.setText("1. UPP_P");

                jLabel26.setText("2. UPP_E");

                jLabel27.setText("3. UPP_G");

                jLabel28.setText("4. UPP_S");

                jLabel29.setText("5. UPP_A");

                jLabel30.setText("6. UPP_M");

                PCPA.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
                PCPA.setText("0");

                PCPD.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
                PCPD.setText("0");

                PCEA.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
                PCEA.setText("0");

                PCED.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
                PCED.setText("0");

                PCGA.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
                PCGA.setText("0");

                PCGD.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
                PCGD.setText("0");

                PCSA.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
                PCSA.setText("0");

                PCSD.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
                PCSD.setText("0");

                PCAA.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
                PCAA.setText("0");

                PCAD.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
                PCAD.setText("0");

                PCMA.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
                PCMA.setText("0");

                PCMD.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
                PCMD.setText("0");

                jCheckBox41.setToolTipText("");

                javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
                jPanel3.setLayout(jPanel3Layout);
                jPanel3Layout.setHorizontalGroup(
                    jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(PCMA, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(PCAA, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                        .addComponent(jCheckBox45)
                                        .addGap(18, 18, 18)
                                        .addComponent(PCMD, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jCheckBox46))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                        .addComponent(jCheckBox43)
                                        .addGap(18, 18, 18)
                                        .addComponent(PCAD, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jCheckBox44))))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(105, 105, 105)
                                .addComponent(jLabel23)
                                .addGap(94, 94, 94)
                                .addComponent(jLabel24))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jLabel27, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(PCPA, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jCheckBox35)
                                                .addGap(18, 18, 18)
                                                .addComponent(PCPD, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jCheckBox36))
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(PCEA, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jCheckBox37)
                                                .addGap(18, 18, 18)
                                                .addComponent(PCED, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jCheckBox38))
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(PCGA, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jCheckBox39)
                                                .addGap(18, 18, 18)
                                                .addComponent(PCGD, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jCheckBox40))))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(PCSA, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jCheckBox41)
                                        .addGap(18, 18, 18)
                                        .addComponent(PCSD, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jCheckBox42)))))
                        .addGap(93, 159, Short.MAX_VALUE))
                );
                jPanel3Layout.setVerticalGroup(
                    jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel23)
                            .addComponent(jLabel24))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel25)
                                .addComponent(PCPA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jCheckBox35))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(PCPD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jCheckBox36)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel26)
                                .addComponent(PCEA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jCheckBox37))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(PCED, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jCheckBox38)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel27)
                                .addComponent(PCGA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jCheckBox39))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(PCGD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jCheckBox40)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel28)
                                .addComponent(PCSA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jCheckBox41))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jCheckBox42)
                                .addComponent(PCSD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel29)
                                .addComponent(PCAA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jCheckBox43))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(PCAD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jCheckBox44)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel30)
                                    .addComponent(PCMA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jCheckBox45))
                                .addComponent(PCMD, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jCheckBox46)))
                        .addContainerGap(68, Short.MAX_VALUE))
                );

                jTabbedPane1.addTab("ПЧ", jPanel3);

                jMenu1.setText("File");

                Open.setText("Open");
                Open.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        OpenActionPerformed(evt);
                    }
                });
                jMenu1.add(Open);

                Save.setText("Save");
                Save.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        SaveMouseClicked(evt);
                    }
                });
                Save.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        SaveActionPerformed(evt);
                    }
                });
                jMenu1.add(Save);

                Exit.setText("Exit");
                Exit.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        ExitActionPerformed(evt);
                    }
                });
                jMenu1.add(Exit);

                jMenuBar1.add(jMenu1);

                jMenu2.setText("?");

                About.setText("?");
                About.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        AboutActionPerformed(evt);
                    }
                });
                jMenu2.add(About);

                jMenuBar1.add(jMenu2);

                setJMenuBar(jMenuBar1);

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 522, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                layout.setVerticalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTabbedPane1)
                        .addContainerGap())
                );

                pack();
            }// </editor-fold>//GEN-END:initComponents

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        //Закрыть
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
 
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
           myThread Potok1 = new myThread();
       // get_over_here();
 
    }//GEN-LAST:event_jButton1MouseClicked

    public void edit_base(){
        int inst=0;
        int intnum = 0;
          try{
             DocumentBuilder documentBuilder2 = DocumentBuilderFactory.newInstance().newDocumentBuilder();
             if (jCheckBox5.isSelected()){
                for (int i=0; i < CountM[1]; i++){
                  //  System.out.println(mnemos1[i].getName());
                    watching.setText("Сдвигаем привязки " + mnemos1[i].getName());
                    textlog[indexlog++] = "Сдвигаем привязки " + mnemos1[i].getName();
                   Document document2 = documentBuilder2.parse(mnemos1[i]);
                   
                 // Получаем корневой элемент - Нулевой уровень
                   Node schem = document2.getDocumentElement();
                   
                 // получаем все элементы на мнемосхеме - первый уровень
                   NodeList elements1 = schem.getChildNodes();
                 //просматриваем каждый элемент и заходим внутрь если нода не текст - второй уровень
                   for (int j = 0; j<elements1.getLength(); j++)
                   {
                       if (elements1.item(j).getNodeType() != Node.TEXT_NODE){
                           //2 уровень
                           //ЕСЛИ ЭТО ФРЕМ С ПОДВАЛОМ!!
                           if (elements1.item(j).getNodeName().equalsIgnoreCase("frame") &&
                               elements1.item(j).getAttributes().getNamedItem("id").getNodeValue().equalsIgnoreCase(BS)){
                               NodeList elm = elements1.item(j).getChildNodes();
                               for (int k = 0; k < elm.getLength();k++){
                                   if (elm.item(k).getNodeType() != Node.TEXT_NODE){
                                       if (elm.item(k).getNodeName().equalsIgnoreCase("scheme")){
                                           String oldval = elm.item(k).getAttributes().getNamedItem("value").getNodeValue();
                                           elm.item(k).getAttributes().getNamedItem("value").setTextContent(preff+oldval);
                                       }
                                   }
                               }
                           }
                           
                           //ЕСЛИ ЭТО ПРЯМОУГОЛЬНИК БАРС
                           if (elements1.item(j).getNodeName().equalsIgnoreCase("grid") &&
                               elements1.item(j).getAttributes().getNamedItem("type").getNodeValue().equalsIgnoreCase(BARS)){
                               //то все вроде норм
                               
                           }
                           //ЕСЛИ ЭТО САМОПИСЕЦ или СОБЫТИНЫЙ САМОПИСЕЦ
                           if (elements1.item(j).getNodeName().equalsIgnoreCase("grid") &&
                               (elements1.item(j).getAttributes().getNamedItem("type").getNodeValue().equalsIgnoreCase("chart") ||
                                elements1.item(j).getAttributes().getNamedItem("type").getNodeValue().equalsIgnoreCase("tablechart"))){
                               String chartdb = "";
                               String chartnum = "";
                               
                               NodeList elm = elements1.item(j).getChildNodes();
                               for(int k=0;k <elm.getLength();k++){
                                   if (elm.item(k).getNodeType() != Node.TEXT_NODE){
                                       if (elm.item(k).getNodeName().equalsIgnoreCase("instance")){
                                           NodeList elm2 = elm.item(k).getChildNodes();
                                           for(int kk=0;kk <elm2.getLength();kk++){
                                               if (elm2.item(kk).getNodeType() != Node.TEXT_NODE){
                                                   if (elm2.item(kk).getNodeName().equalsIgnoreCase("db")){
                                                       chartdb = elm2.item(kk).getAttributes().getNamedItem("value").getNodeValue();
                                                   }
                                                   if (elm2.item(kk).getNodeName().equalsIgnoreCase("num")){
                                                       intnum =kk;
                                                       chartnum = elm2.item(kk).getAttributes().getNamedItem("value").getNodeValue();
                                                   }
                                               }
                                           }
                                          elm2.item(intnum).getAttributes().getNamedItem("value").setTextContent(get_new_chart(chartdb,
                                                  chartnum,
                                                  Integer.parseInt(jTextField8.getText().trim()),        //Значение для сдвига базы А, 1 шкаф
                                                  Integer.parseInt(jTextField14.getText().trim()),        //Значение для сдвига базы D, 1 шкаф
                                                  Integer.MAX_VALUE,        //Значение границы базы А
                                                  Integer.MAX_VALUE,        //Значение границы базы D
                                                  0, //Значение для сдвига базы А, 2 шкаф
                                                  0)) ;//Значение для сдвига базы D, 2 шкаф
                                          intnum =0;
                                       }
                                   }
                               }
                               
                           }
                           //ЕСЛИ КНОПКА перехода с УПП_мнемосхемы на нормальную
                           if (elements1.item(j).getNodeName().equalsIgnoreCase("grid") &&
                               elements1.item(j).getAttributes().getNamedItem("type").getNodeValue().equalsIgnoreCase("button")){
                               NodeList elm = elements1.item(j).getChildNodes();
                               for (int k = 0; k < elm.getLength();k++){
                                   if (elm.item(k).getNodeType() != Node.TEXT_NODE){
                                       if (elm.item(k).getNodeName().equalsIgnoreCase("comment") &&
                                           elm.item(k).getAttributes().getNamedItem("value").getNodeValue().equalsIgnoreCase(BTMI)){
                                           inst = get_inst(elements1.item(j));
                                           NodeList elm2 = elm.item(inst).getChildNodes();
                                           inst =0;
                                           for (int ii=0;ii<elm2.getLength();ii++){
                                               if (elm2.item(ii).getNodeType() != Node.TEXT_NODE){
                                                   if (elm2.item(ii).getNodeName().equalsIgnoreCase("schemetojump")){
                                                     if (elm2.item(ii).getAttributes().getNamedItem("value").getTextContent().startsWith("УПП")){
                                                         String s1 = elm2.item(ii).getAttributes().getNamedItem("value").getTextContent();
                                                         elm2.item(ii).getAttributes().getNamedItem("value").setTextContent(s1.substring(4));
                                                     } 
                                                   }
                                               }
                                           }
                                       }
                                   }
                               }
                           }
                            //ЕСЛИ ЭТО заглавный текст (должен стать черным)
                           if (elements1.item(j).getNodeName().equalsIgnoreCase("grid") &&
                               elements1.item(j).getAttributes().getNamedItem("type").getNodeValue().equalsIgnoreCase("label")){
                               NodeList elm = elements1.item(j).getChildNodes();
                               for (int k = 0; k < elm.getLength();k++){
                                   if (elm.item(k).getNodeType() != Node.TEXT_NODE){
                                       if (elm.item(k).getNodeName().equalsIgnoreCase("comment") &&
                                           elm.item(k).getAttributes().getNamedItem("value").getNodeValue().equalsIgnoreCase(TITLE)){
                                           inst = get_inst(elements1.item(j));
                                           NodeList elm2 = elm.item(inst).getChildNodes();
                                           inst =0;
                                           for (int ii=0;ii<elm2.getLength();ii++){
                                               if (elm2.item(ii).getNodeType() != Node.TEXT_NODE){
                                                   if (elm2.item(ii).getNodeName().equalsIgnoreCase("color1")){
                                                      elm2.item(ii).getAttributes().getNamedItem("value").setTextContent("0"); 
                                                   }
                                               }
                                           }
                                           
                                       }
                                   }
                               }
                               
                           }
                       NodeList elements2 = elements1.item(j).getChildNodes();
                       for (int k = 0; k < elements2.getLength();k++){
                           if (elements2.item(k).getNodeType() != Node.TEXT_NODE){
                               // если нода не текст и имя её LINK то идем ниже - третий ур
                               if (elements2.item(k).getNodeName().equalsIgnoreCase("link")){
                                   NodeList elements3 = elements2.item(k).getChildNodes();
                                   for (int l=0; l < elements3.getLength();l++){
                                       if (elements3.item(l).getNodeType() != Node.TEXT_NODE){
                                           //зырим атрибуты db и num
                                           // проверяем что это ненасос
                                           if (checking_pump_pulse(elements1.item(j))) {
                                               //насос или пульсатор
                                               //остальное
                                                int num = 0;
                                              if(elements3.item(l).getAttributes().getNamedItem("db").getNodeValue().equalsIgnoreCase("1")){
                                                  num = Integer.parseInt(elements3.item(l).getAttributes().getNamedItem("num").getNodeValue().trim()) ;
                                                  if (jCheckBox14.isSelected() && jCheckBox35.isSelected()){
                                                     num += Integer.parseInt(PCPA.getText().trim()) ;
                                                 // elements3.item(l).getAttributes().getNamedItem("num").setNodeValue(""+num);
                                                  elements3.item(l).getAttributes().getNamedItem("num").setTextContent(""+num);
                                                  }
                                              }else{
                                                  num = Integer.parseInt(elements3.item(l).getAttributes().getNamedItem("num").getNodeValue().trim()) ;
                                                  if (jCheckBox20.isSelected() && jCheckBox36.isSelected()){
                                                     num += Integer.parseInt(PCPD.getText().trim()) ;
                                                //  elements3.item(l).getAttributes().getNamedItem("num").setNodeValue(""+num);
                                                  elements3.item(l).getAttributes().getNamedItem("num").setTextContent(""+num);

                                                  }
                                              }
                                           }
                                           else{
                                               //остальное
                                                int num = 0;
                                              if(elements3.item(l).getAttributes().getNamedItem("db").getNodeValue().equalsIgnoreCase("1")){
                                                  num = Integer.parseInt(elements3.item(l).getAttributes().getNamedItem("num").getNodeValue().trim()) ;
                                                  if (jCheckBox14.isSelected()){
                                                     num += Integer.parseInt(jTextField8.getText().trim()) ;
                                                 // elements3.item(l).getAttributes().getNamedItem("num").setNodeValue(""+num);
                                                  elements3.item(l).getAttributes().getNamedItem("num").setTextContent(""+num);
                                                  }
                                              }else{
                                                  num = Integer.parseInt(elements3.item(l).getAttributes().getNamedItem("num").getNodeValue().trim()) ;
                                                  if (jCheckBox20.isSelected()){
                                                     num += Integer.parseInt(jTextField14.getText().trim()) ;
                                                //  elements3.item(l).getAttributes().getNamedItem("num").setNodeValue(""+num);
                                                  elements3.item(l).getAttributes().getNamedItem("num").setTextContent(""+num);

                                                  }
                                              }
                                           }
                                       }
                                   }
                               }
                           }
                       }
                       }
                   }
                   //Если стоит галка WaterMark => водяной знак
                   if (jCheckBox34.isSelected() && !mnemos1[i].getName().matches(podval)){
                       //сгененрируем ноду WM и сразу добавим
                       schem.appendChild(create_JLOOK(document2));
                    
                   }
                   String schemname = schem.getAttributes().getNamedItem("name").getTextContent();
                   schem.getAttributes().getNamedItem("name").setTextContent(preff+schemname);
                   Transformer transformer = TransformerFactory.newInstance().newTransformer();
                   document2.normalizeDocument();
                   DOMSource source = new DOMSource(document2);
                   StreamResult result  = new StreamResult(mnemos1[i]);
                   transformer.transform(source, result);
                   
               }
             }
             //2
             if (jCheckBox2.isSelected()){
                for (int i=0; i < CountM[2]; i++){
                    watching.setText("Сдвигаем привязки " + mnemos2[i].getName());
                    textlog[indexlog++] = "Сдвигаем привязки " + mnemos2[i].getName();
                   Document document2 = documentBuilder2.parse(mnemos2[i]);
                 // Получаем корневой элемент - Нулевой уровень
                   Node schem = document2.getDocumentElement();
                 // получаем все элементы на мнемосхеме - первый уровень
                   NodeList elements1 = schem.getChildNodes();
                 //просматриваем каждый элемент и заходим внутрь если нода не текст - второй уровень
                   for (int j = 0; j<elements1.getLength(); j++)
                   {
                       if (elements1.item(j).getNodeType() != Node.TEXT_NODE){
                           //2 уровень
                         //ЕСЛИ ЭТО ФРЕМ С ПОДВАЛОМ!!
                           if (elements1.item(j).getNodeName().equalsIgnoreCase("frame") &&
                               elements1.item(j).getAttributes().getNamedItem("id").getNodeValue().equalsIgnoreCase(BS)){
                               NodeList elm = elements1.item(j).getChildNodes();
                               for (int k = 0; k < elm.getLength();k++){
                                   if (elm.item(k).getNodeType() != Node.TEXT_NODE){
                                       if (elm.item(k).getNodeName().equalsIgnoreCase("scheme")){
                                           String oldval = elm.item(k).getAttributes().getNamedItem("value").getNodeValue();
                                           elm.item(k).getAttributes().getNamedItem("value").setTextContent(preff+oldval);
                                       }
                                   }
                               }
                           }
                         //ЕСЛИ ЭТО ПРЯМОУГОЛЬНИК БАРС
                           if (elements1.item(j).getNodeName().equalsIgnoreCase("grid") &&
                               elements1.item(j).getAttributes().getNamedItem("type").getNodeValue().equalsIgnoreCase(BARS)){
                               //то все вроде норм
                               
                           }
                           //ЕСЛИ ЭТО САМОПИСЕЦ или СОБЫТИНЫЙ САМОПИСЕЦ
                           if (elements1.item(j).getNodeName().equalsIgnoreCase("grid") &&
                               (elements1.item(j).getAttributes().getNamedItem("type").getNodeValue().equalsIgnoreCase("chart") ||
                                elements1.item(j).getAttributes().getNamedItem("type").getNodeValue().equalsIgnoreCase("tablechart"))){
                               String chartdb = "";
                               String chartnum = "";
                               
                               NodeList elm = elements1.item(j).getChildNodes();
                               for(int k=0;k <elm.getLength();k++){
                                   if (elm.item(k).getNodeType() != Node.TEXT_NODE){
                                       if (elm.item(k).getNodeName().equalsIgnoreCase("instance")){
                                           NodeList elm2 = elm.item(k).getChildNodes();
                                           for(int kk=0;kk <elm2.getLength();kk++){
                                               if (elm2.item(kk).getNodeType() != Node.TEXT_NODE){
                                                   if (elm2.item(kk).getNodeName().equalsIgnoreCase("db")){
                                                       chartdb = elm2.item(kk).getAttributes().getNamedItem("value").getNodeValue();
                                                   }
                                                   if (elm2.item(kk).getNodeName().equalsIgnoreCase("num")){
                                                       intnum =kk;
                                                       chartnum = elm2.item(kk).getAttributes().getNamedItem("value").getNodeValue();
                                                   }
                                               }
                                           }
                                          elm2.item(intnum).getAttributes().getNamedItem("value").setTextContent(get_new_chart(chartdb, 
                                                  chartnum, 
                                                  Integer.parseInt(jTextField9.getText().trim()),   //Значение для сдвига базы А, 1 шкаф
                                                  Integer.parseInt(jTextField15.getText().trim()),  //Значение для свдига базы D 1 шкаф
                                                  Integer.parseInt(jTextField21.getText().trim()),  //Значение для границы Базы А
                                                  Integer.parseInt(jTextField22.getText().trim()),  //Значение для границы базы D
                                                  Integer.parseInt(jTextField23.getText().trim()),  //Значение для свдига базы А 2 шкаф
                                                  Integer.parseInt(jTextField24.getText().trim()))) ;   //ЗНачение сдвига D 2 шкаф
                                          intnum=0;
                                       }
                                   }
                               }
                               
                           }
                           //ЕСЛИ КНОПКА перехода с УПП_мнемосхемы на нормальную
                           if (elements1.item(j).getNodeName().equalsIgnoreCase("grid") &&
                               elements1.item(j).getAttributes().getNamedItem("type").getNodeValue().equalsIgnoreCase("button")){
                               NodeList elm = elements1.item(j).getChildNodes();
                               for (int k = 0; k < elm.getLength();k++){
                                   if (elm.item(k).getNodeType() != Node.TEXT_NODE){
                                       if (elm.item(k).getNodeName().equalsIgnoreCase("comment") &&
                                           elm.item(k).getAttributes().getNamedItem("value").getNodeValue().equalsIgnoreCase(BTMI)){
                                           inst = get_inst(elements1.item(j));
                                           NodeList elm2 = elm.item(inst).getChildNodes();
                                           inst =0;
                                           for (int ii=0;ii<elm2.getLength();ii++){
                                               if (elm2.item(ii).getNodeType() != Node.TEXT_NODE){
                                                   if (elm2.item(ii).getNodeName().equalsIgnoreCase("schemetojump")){
                                                     if (elm2.item(ii).getAttributes().getNamedItem("value").getTextContent().startsWith("УПП")){
                                                         String s1 = elm2.item(ii).getAttributes().getNamedItem("value").getTextContent();
                                                         elm2.item(ii).getAttributes().getNamedItem("value").setTextContent(s1.substring(4));
                                                     } 
                                                   }
                                               }
                                           }
                                       }
                                   }
                               }
                           }
                            //ЕСЛИ ЭТО заглавный текст (должен стать черным)
                           if (elements1.item(j).getNodeName().equalsIgnoreCase("grid") &&
                               elements1.item(j).getAttributes().getNamedItem("type").getNodeValue().equalsIgnoreCase("label")){
                               NodeList elm = elements1.item(j).getChildNodes();
                               for (int k = 0; k < elm.getLength();k++){
                                   if (elm.item(k).getNodeType() != Node.TEXT_NODE){
                                       if (elm.item(k).getNodeName().equalsIgnoreCase("comment") &&
                                           elm.item(k).getAttributes().getNamedItem("value").getNodeValue().equalsIgnoreCase(TITLE)){
                                           inst = get_inst(elements1.item(j));
                                           NodeList elm2 = elm.item(inst).getChildNodes();
                                           inst =0;
                                           for (int ii=0;ii<elm2.getLength();ii++){
                                               if (elm2.item(ii).getNodeType() != Node.TEXT_NODE){
                                                   if (elm2.item(ii).getNodeName().equalsIgnoreCase("color1")){
                                                      elm2.item(ii).getAttributes().getNamedItem("value").setTextContent("0"); 
                                                   }
                                               }
                                           }
                                           
                                       }
                                   }
                               }
                               
                           }  
                       NodeList elements2 = elements1.item(j).getChildNodes();
                       for (int k = 0; k < elements2.getLength();k++){
                           if (elements2.item(k).getNodeType() != Node.TEXT_NODE){
                               // если нода не текст и имя её LINK то идем ниже - третий ур
                               if (elements2.item(k).getNodeName().equalsIgnoreCase("link")){
                                   NodeList elements3 = elements2.item(k).getChildNodes();
                                   for (int l=0; l < elements3.getLength();l++){
                                       if (elements3.item(l).getNodeType() != Node.TEXT_NODE){
                                           //зырим атрибуты db и num
                                           // проверяем что это ненасос
                                           if (checking_pump_pulse(elements1.item(j))){
                                               //насос или пульсатор
                                                int num = 0;
                                              if(elements3.item(l).getAttributes().getNamedItem("db").getNodeValue().equalsIgnoreCase("0")){
                                                  num = Integer.parseInt(elements3.item(l).getAttributes().getNamedItem("num").getNodeValue().trim()) ;
                                                  if (jCheckBox21.isSelected() && jCheckBox38.isSelected()){
                                                     num += Integer.parseInt(PCED.getText().trim()) ;
                                                 // elements3.item(l).getAttributes().getNamedItem("num").setNodeValue(""+num);
                                                  elements3.item(l).getAttributes().getNamedItem("num").setTextContent(""+num);
                                                  }
                                              }else{
                                                  num = Integer.parseInt(elements3.item(l).getAttributes().getNamedItem("num").getNodeValue().trim()) ;
                                                  if (jCheckBox15.isSelected() && jCheckBox37.isSelected()){
                                                     num += Integer.parseInt(PCEA.getText().trim()) ;
                                                //  elements3.item(l).getAttributes().getNamedItem("num").setNodeValue(""+num);
                                                  elements3.item(l).getAttributes().getNamedItem("num").setTextContent(""+num);

                                                  }
                                              }
                                           }
                                           else{
                                               //остальное
                                           int num = 0;
                                         if(elements3.item(l).getAttributes().getNamedItem("db").getNodeValue().equalsIgnoreCase("0")){
                                             num = Integer.parseInt(elements3.item(l).getAttributes().getNamedItem("num").getNodeValue().trim()) ;
                                             if (jCheckBox21.isSelected()){
                                                 if (num < Integer.parseInt(jTextField22.getText().trim())){
                                                num += Integer.parseInt(jTextField15.getText().trim());
                                                 }
                                                 else{
                                                  num += Integer.parseInt(jTextField24.getText().trim());
                                                 }
                                            // elements3.item(l).getAttributes().getNamedItem("num").setNodeValue(""+num);
                                             elements3.item(l).getAttributes().getNamedItem("num").setTextContent(""+num);
                                             }
                                         }else{
                                             num = Integer.parseInt(elements3.item(l).getAttributes().getNamedItem("num").getNodeValue().trim()) ;
                                             if (jCheckBox15.isSelected()){
                                                 if (num < Integer.parseInt(jTextField21.getText().trim())){
                                                num += Integer.parseInt(jTextField9.getText().trim()) ;
                                                 }
                                                 else{
                                                     num += Integer.parseInt(jTextField23.getText().trim()) ;
                                                 }
                                           //  elements3.item(l).getAttributes().getNamedItem("num").setNodeValue(""+num);
                                             elements3.item(l).getAttributes().getNamedItem("num").setTextContent(""+num);
                                             
                                             }
                                         }
                                         }
                                       }
                                   }
                               }
                           }
                       }
                       }
                   }
                   //Если стоит галка WaterMark => водяной знак
                   if (jCheckBox34.isSelected() && !mnemos2[i].getName().matches(podval)){
                       //сгененрируем ноду WM и сразу добавим
                       schem.appendChild(create_JLOOK(document2));
                    
                   }
                   String schemname = schem.getAttributes().getNamedItem("name").getTextContent();
                   schem.getAttributes().getNamedItem("name").setTextContent(preff+schemname);
                   Transformer transformer = TransformerFactory.newInstance().newTransformer();
                   document2.normalizeDocument();
                   DOMSource source = new DOMSource(document2);
                   StreamResult result = new StreamResult(mnemos2[i]);
                   transformer.transform(source, result);
                   
               }
             }
             //3
             if (jCheckBox3.isSelected()){
                for (int i=0; i < CountM[3]; i++){
                    watching.setText("Сдвигаем привязки " + mnemos3[i].getName());
                    textlog[indexlog++] = "Сдвигаем привязки " + mnemos3[i].getName();
                   Document document2 = documentBuilder2.parse(mnemos3[i]);
                 // Получаем корневой элемент - Нулевой уровень
                   Node schem = document2.getDocumentElement();
                 // получаем все элементы на мнемосхеме - первый уровень
                   NodeList elements1 = schem.getChildNodes();
                 //просматриваем каждый элемент и заходим внутрь если нода не текст - второй уровень
                   for (int j = 0; j<elements1.getLength(); j++)
                   {
                       if (elements1.item(j).getNodeType() != Node.TEXT_NODE){
                           //2 уровень
                            //ЕСЛИ ЭТО ФРЕМ С ПОДВАЛОМ!!
                           if (elements1.item(j).getNodeName().equalsIgnoreCase("frame") &&
                               elements1.item(j).getAttributes().getNamedItem("id").getNodeValue().equalsIgnoreCase("BS")){
                               NodeList elm = elements1.item(j).getChildNodes();
                               for (int k = 0; k < elm.getLength();k++){
                                   if (elm.item(k).getNodeType() != Node.TEXT_NODE){
                                       if (elm.item(k).getNodeName().equalsIgnoreCase("scheme")){
                                           String oldval = elm.item(k).getAttributes().getNamedItem("value").getNodeValue();
                                           elm.item(k).getAttributes().getNamedItem("value").setTextContent(preff+oldval);
                                       }
                                   }
                               }
                           }
                           //ЕСЛИ ЭТО ПРЯМОУГОЛЬНИК БАРС
                           if (elements1.item(j).getNodeName().equalsIgnoreCase("grid") &&
                               elements1.item(j).getAttributes().getNamedItem("type").getNodeValue().equalsIgnoreCase(BARS)){
                               //то все вроде норм
                               
                           }
                           //ЕСЛИ ЭТО САМОПИСЕЦ или СОБЫТИНЫЙ САМОПИСЕЦ
                           if (elements1.item(j).getNodeName().equalsIgnoreCase("grid") &&
                               (elements1.item(j).getAttributes().getNamedItem("type").getNodeValue().equalsIgnoreCase("chart") ||
                                elements1.item(j).getAttributes().getNamedItem("type").getNodeValue().equalsIgnoreCase("tablechart"))){
                               String chartdb = "";
                               String chartnum = "";
                               
                               NodeList elm = elements1.item(j).getChildNodes();
                               for(int k=0;k <elm.getLength();k++){
                                   if (elm.item(k).getNodeType() != Node.TEXT_NODE){
                                       if (elm.item(k).getNodeName().equalsIgnoreCase("instance")){
                                           NodeList elm2 = elm.item(k).getChildNodes();
                                           for(int kk=0;kk <elm2.getLength();kk++){
                                               if (elm2.item(kk).getNodeType() != Node.TEXT_NODE){
                                                   if (elm2.item(kk).getNodeName().equalsIgnoreCase("db")){
                                                       chartdb = elm2.item(kk).getAttributes().getNamedItem("value").getNodeValue();
                                                   }
                                                   if (elm2.item(kk).getNodeName().equalsIgnoreCase("num")){
                                                       intnum =kk;
                                                       chartnum = elm2.item(kk).getAttributes().getNamedItem("value").getNodeValue();
                                                   }
                                               }
                                           }
                                          elm2.item(intnum).getAttributes().getNamedItem("value").setTextContent(get_new_chart(chartdb,
                                                  chartnum,
                                                  Integer.parseInt(jTextField10.getText().trim()),        //Значение для сдвига базы А, 1 шкаф
                                                  Integer.parseInt(jTextField16.getText().trim()),        //Значение для сдвига базы D, 1 шкаф
                                                  Integer.MAX_VALUE,        //Значение границы базы А
                                                  Integer.MAX_VALUE,        //Значение границы базы D
                                                  0, //Значение для сдвига базы А, 2 шкаф
                                                  0)) ;//Значение для сдвига базы D, 2 шкаф
                                          intnum =0;
                                       }
                                   }
                               }
                               
                           }
                           //ЕСЛИ КНОПКА перехода с УПП_мнемосхемы на нормальную
                           if (elements1.item(j).getNodeName().equalsIgnoreCase("grid") &&
                               elements1.item(j).getAttributes().getNamedItem("type").getNodeValue().equalsIgnoreCase("button")){
                               NodeList elm = elements1.item(j).getChildNodes();
                               for (int k = 0; k < elm.getLength();k++){
                                   if (elm.item(k).getNodeType() != Node.TEXT_NODE){
                                       if (elm.item(k).getNodeName().equalsIgnoreCase("comment") &&
                                           elm.item(k).getAttributes().getNamedItem("value").getNodeValue().equalsIgnoreCase(BTMI)){
                                           inst = get_inst(elements1.item(j));
                                           NodeList elm2 = elm.item(inst).getChildNodes();
                                           inst =0;
                                           for (int ii=0;ii<elm2.getLength();ii++){
                                               if (elm2.item(ii).getNodeType() != Node.TEXT_NODE){
                                                   if (elm2.item(ii).getNodeName().equalsIgnoreCase("schemetojump")){
                                                     if (elm2.item(ii).getAttributes().getNamedItem("value").getTextContent().startsWith("УПП")){
                                                         String s1 = elm2.item(ii).getAttributes().getNamedItem("value").getTextContent();
                                                         elm2.item(ii).getAttributes().getNamedItem("value").setTextContent(s1.substring(4));
                                                     } 
                                                   }
                                               }
                                           }
                                       }
                                   }
                               }
                           }
                            //ЕСЛИ ЭТО заглавный текст (должен стать черным)
                           if (elements1.item(j).getNodeName().equalsIgnoreCase("grid") &&
                               elements1.item(j).getAttributes().getNamedItem("type").getNodeValue().equalsIgnoreCase("label")){
                               NodeList elm = elements1.item(j).getChildNodes();
                               for (int k = 0; k < elm.getLength();k++){
                                   if (elm.item(k).getNodeType() != Node.TEXT_NODE){
                                       if (elm.item(k).getNodeName().equalsIgnoreCase("comment") &&
                                           elm.item(k).getAttributes().getNamedItem("value").getNodeValue().equalsIgnoreCase(TITLE)){
                                           inst = get_inst(elements1.item(j));
                                           NodeList elm2 = elm.item(inst).getChildNodes();
                                           inst =0;
                                           for (int ii=0;ii<elm2.getLength();ii++){
                                               if (elm2.item(ii).getNodeType() != Node.TEXT_NODE){
                                                   if (elm2.item(ii).getNodeName().equalsIgnoreCase("color1")){
                                                      elm2.item(ii).getAttributes().getNamedItem("value").setTextContent("0"); 
                                                   }
                                               }
                                           }
                                           
                                       }
                                   }
                               }
                               
                           }
                       NodeList elements2 = elements1.item(j).getChildNodes();
                       for (int k = 0; k < elements2.getLength();k++){
                           if (elements2.item(k).getNodeType() != Node.TEXT_NODE){
                               // если нода не текст и имя её LINK то идем ниже - третий ур
                               if (elements2.item(k).getNodeName().equalsIgnoreCase("link")){
                                   NodeList elements3 = elements2.item(k).getChildNodes();
                                   for (int l=0; l < elements3.getLength();l++){
                                       if (elements3.item(l).getNodeType() != Node.TEXT_NODE){
                                           //зырим атрибуты db и num
                                           // проверяем что это ненасос
                                           if (checking_pump_pulse(elements1.item(j))){
                                               //насос или пульсатор
                                                int num = 0;
                                              if(elements3.item(l).getAttributes().getNamedItem("db").getNodeValue().equalsIgnoreCase("1")){
                                                  num = Integer.parseInt(elements3.item(l).getAttributes().getNamedItem("num").getNodeValue().trim()) ;
                                                  if (jCheckBox16.isSelected() && jCheckBox39.isSelected()){
                                                     num += Integer.parseInt(PCGA.getText().trim()) ;
                                                 // elements3.item(l).getAttributes().getNamedItem("num").setNodeValue(""+num);
                                                  elements3.item(l).getAttributes().getNamedItem("num").setTextContent(""+num);
                                                  }
                                              }else{
                                                  num = Integer.parseInt(elements3.item(l).getAttributes().getNamedItem("num").getNodeValue().trim()) ;
                                                  if (jCheckBox22.isSelected() && jCheckBox40.isSelected()){
                                                     num += Integer.parseInt(PCGD.getText().trim()) ;
                                                //  elements3.item(l).getAttributes().getNamedItem("num").setNodeValue(""+num);
                                                  elements3.item(l).getAttributes().getNamedItem("num").setTextContent(""+num);

                                                  }
                                              }
                                           }
                                           else{
                                               //остальное
                                           int num = 0;
                                         if(elements3.item(l).getAttributes().getNamedItem("db").getNodeValue().equalsIgnoreCase("1")){
                                             num = Integer.parseInt(elements3.item(l).getAttributes().getNamedItem("num").getNodeValue().trim()) ;
                                             if (jCheckBox16.isSelected()){
                                                num += Integer.parseInt(jTextField10.getText().trim()) ;
                                            // elements3.item(l).getAttributes().getNamedItem("num").setNodeValue(""+num);
                                             elements3.item(l).getAttributes().getNamedItem("num").setTextContent(""+num);
                                             }
                                         }else{
                                             num = Integer.parseInt(elements3.item(l).getAttributes().getNamedItem("num").getNodeValue().trim()) ;
                                             if (jCheckBox22.isSelected()){
                                                num += Integer.parseInt(jTextField16.getText().trim()) ;
                                           //  elements3.item(l).getAttributes().getNamedItem("num").setNodeValue(""+num);
                                             elements3.item(l).getAttributes().getNamedItem("num").setTextContent(""+num);
                                             
                                             }
                                         }
                                         }
                                       }
                                   }
                               }
                           }
                       }
                       }
                   }
                   //Если стоит галка WaterMark => водяной знак
                   if (jCheckBox34.isSelected() && !mnemos3[i].getName().matches(podval)){
                       //сгененрируем ноду WM и сразу добавим
                       schem.appendChild(create_JLOOK(document2));
                    
                   }
                   String schemname = schem.getAttributes().getNamedItem("name").getTextContent();
                   schem.getAttributes().getNamedItem("name").setTextContent(preff+schemname);
                   Transformer transformer = TransformerFactory.newInstance().newTransformer();
                   document2.normalizeDocument();
                   DOMSource source = new DOMSource(document2);
                   StreamResult result = new StreamResult(mnemos3[i]);
                   transformer.transform(source, result);
                   
               }
             }
             //4
             if (jCheckBox4.isSelected()){
                for (int i=0; i < CountM[4]; i++){
                    watching.setText("Сдвигаем привязки " + mnemos4[i].getName());
                    textlog[indexlog++] = "Сдвигаем привязки " + mnemos4[i].getName();
                   Document document2 = documentBuilder2.parse(mnemos4[i]);
                 // Получаем корневой элемент - Нулевой уровень
                   Node schem = document2.getDocumentElement();
                 // получаем все элементы на мнемосхеме - первый уровень
                   NodeList elements1 = schem.getChildNodes();
                 //просматриваем каждый элемент и заходим внутрь если нода не текст - второй уровень
                   for (int j = 0; j<elements1.getLength(); j++)
                   {
                       if (elements1.item(j).getNodeType() != Node.TEXT_NODE){
                           //2 уровень
                            //ЕСЛИ ЭТО ФРЕМ С ПОДВАЛОМ!!
                           if (elements1.item(j).getNodeName().equalsIgnoreCase("frame") &&
                               elements1.item(j).getAttributes().getNamedItem("id").getNodeValue().equalsIgnoreCase("BS")){
                               NodeList elm = elements1.item(j).getChildNodes();
                               for (int k = 0; k < elm.getLength();k++){
                                   if (elm.item(k).getNodeType() != Node.TEXT_NODE){
                                       if (elm.item(k).getNodeName().equalsIgnoreCase("scheme")){
                                           String oldval = elm.item(k).getAttributes().getNamedItem("value").getNodeValue();
                                           elm.item(k).getAttributes().getNamedItem("value").setTextContent(preff+oldval);
                                       }
                                   }
                               }
                           }
                        //ЕСЛИ ЭТО ПРЯМОУГОЛЬНИК БАРС
                           if (elements1.item(j).getNodeName().equalsIgnoreCase("grid") &&
                               elements1.item(j).getAttributes().getNamedItem("type").getNodeValue().equalsIgnoreCase(BARS)){
                               //то все вроде норм
                               
                           }
                           //ЕСЛИ ЭТО САМОПИСЕЦ или СОБЫТИНЫЙ САМОПИСЕЦ
                           if (elements1.item(j).getNodeName().equalsIgnoreCase("grid") &&
                               (elements1.item(j).getAttributes().getNamedItem("type").getNodeValue().equalsIgnoreCase("chart") ||
                                elements1.item(j).getAttributes().getNamedItem("type").getNodeValue().equalsIgnoreCase("tablechart"))){
                               String chartdb = "";
                               String chartnum = "";
                               
                               NodeList elm = elements1.item(j).getChildNodes();
                               for(int k=0;k <elm.getLength();k++){
                                   if (elm.item(k).getNodeType() != Node.TEXT_NODE){
                                       if (elm.item(k).getNodeName().equalsIgnoreCase("instance")){
                                           NodeList elm2 = elm.item(k).getChildNodes();
                                           for(int kk=0;kk <elm2.getLength();kk++){
                                               if (elm2.item(kk).getNodeType() != Node.TEXT_NODE){
                                                   if (elm2.item(kk).getNodeName().equalsIgnoreCase("db")){
                                                       chartdb = elm2.item(kk).getAttributes().getNamedItem("value").getNodeValue();
                                                   }
                                                   if (elm2.item(kk).getNodeName().equalsIgnoreCase("num")){
                                                       intnum =kk;
                                                       chartnum = elm2.item(kk).getAttributes().getNamedItem("value").getNodeValue();
                                                   }
                                               }
                                           }
                                          elm2.item(intnum).getAttributes().getNamedItem("value").setTextContent(get_new_chart(chartdb,
                                                  chartnum,
                                                  Integer.parseInt(jTextField11.getText().trim()),        //Значение для сдвига базы А, 1 шкаф
                                                  Integer.parseInt(jTextField17.getText().trim()),        //Значение для сдвига базы D, 1 шкаф
                                                  Integer.MAX_VALUE,        //Значение границы базы А
                                                  Integer.MAX_VALUE,        //Значение границы базы D
                                                  0, //Значение для сдвига базы А, 2 шкаф
                                                  0)) ;//Значение для сдвига базы D, 2 шкаф
                                          intnum =0;
                                       }
                                   }
                               }
                               
                           }
                           //ЕСЛИ КНОПКА перехода с УПП_мнемосхемы на нормальную
                           if (elements1.item(j).getNodeName().equalsIgnoreCase("grid") &&
                               elements1.item(j).getAttributes().getNamedItem("type").getNodeValue().equalsIgnoreCase("button")){
                               NodeList elm = elements1.item(j).getChildNodes();
                               for (int k = 0; k < elm.getLength();k++){
                                   if (elm.item(k).getNodeType() != Node.TEXT_NODE){
                                       if (elm.item(k).getNodeName().equalsIgnoreCase("comment") &&
                                           elm.item(k).getAttributes().getNamedItem("value").getNodeValue().equalsIgnoreCase(BTMI)){
                                           inst = get_inst(elements1.item(j));
                                           NodeList elm2 = elm.item(inst).getChildNodes();
                                           inst =0;
                                           for (int ii=0;ii<elm2.getLength();ii++){
                                               if (elm2.item(ii).getNodeType() != Node.TEXT_NODE){
                                                   if (elm2.item(ii).getNodeName().equalsIgnoreCase("schemetojump")){
                                                     if (elm2.item(ii).getAttributes().getNamedItem("value").getTextContent().startsWith("УПП")){
                                                         String s1 = elm2.item(ii).getAttributes().getNamedItem("value").getTextContent();
                                                         elm2.item(ii).getAttributes().getNamedItem("value").setTextContent(s1.substring(4));
                                                     } 
                                                   }
                                               }
                                           }
                                       }
                                   }
                               }
                           }
                            //ЕСЛИ ЭТО заглавный текст (должен стать черным)
                           if (elements1.item(j).getNodeName().equalsIgnoreCase("grid") &&
                               elements1.item(j).getAttributes().getNamedItem("type").getNodeValue().equalsIgnoreCase("label")){
                               NodeList elm = elements1.item(j).getChildNodes();
                               for (int k = 0; k < elm.getLength();k++){
                                   if (elm.item(k).getNodeType() != Node.TEXT_NODE){
                                       if (elm.item(k).getNodeName().equalsIgnoreCase("comment") &&
                                           elm.item(k).getAttributes().getNamedItem("value").getNodeValue().equalsIgnoreCase(TITLE)){
                                           inst = get_inst(elements1.item(j));
                                           NodeList elm2 = elm.item(inst).getChildNodes();
                                           inst =0;
                                           for (int ii=0;ii<elm2.getLength();ii++){
                                               if (elm2.item(ii).getNodeType() != Node.TEXT_NODE){
                                                   if (elm2.item(ii).getNodeName().equalsIgnoreCase("color1")){
                                                      elm2.item(ii).getAttributes().getNamedItem("value").setTextContent("0"); 
                                                   }
                                               }
                                           }
                                           
                                       }
                                   }
                               }
                               
                           }   
                       NodeList elements2 = elements1.item(j).getChildNodes();
                       for (int k = 0; k < elements2.getLength();k++){
                           if (elements2.item(k).getNodeType() != Node.TEXT_NODE){
                               // если нода не текст и имя её LINK то идем ниже - третий ур
                               if (elements2.item(k).getNodeName().equalsIgnoreCase("link")){
                                   NodeList elements3 = elements2.item(k).getChildNodes();
                                   for (int l=0; l < elements3.getLength();l++){
                                       if (elements3.item(l).getNodeType() != Node.TEXT_NODE){
                                           //зырим атрибуты db и num
                                           // проверяем что это ненасос
                                           if (checking_pump_pulse(elements1.item(j))){
                                               //насос или пульсатор
                                                int num = 0;
                                              if(elements3.item(l).getAttributes().getNamedItem("db").getNodeValue().equalsIgnoreCase("1")){
                                                  num = Integer.parseInt(elements3.item(l).getAttributes().getNamedItem("num").getNodeValue().trim()) ;
                                                  if (jCheckBox17.isSelected() && jCheckBox41.isSelected()){
                                                     num += Integer.parseInt(PCSA.getText().trim()) ;
                                                 // elements3.item(l).getAttributes().getNamedItem("num").setNodeValue(""+num);
                                                  elements3.item(l).getAttributes().getNamedItem("num").setTextContent(""+num);
                                                  }
                                              }else{
                                                  num = Integer.parseInt(elements3.item(l).getAttributes().getNamedItem("num").getNodeValue().trim()) ;
                                                  if (jCheckBox23.isSelected() && jCheckBox42.isSelected()){
                                                     num += Integer.parseInt(PCSD.getText().trim()) ;
                                                //  elements3.item(l).getAttributes().getNamedItem("num").setNodeValue(""+num);
                                                  elements3.item(l).getAttributes().getNamedItem("num").setTextContent(""+num);

                                                  }
                                              }
                                           }
                                           else{
                                               //остальное
                                           int num = 0;
                                         if(elements3.item(l).getAttributes().getNamedItem("db").getNodeValue().equalsIgnoreCase("1")){
                                             num = Integer.parseInt(elements3.item(l).getAttributes().getNamedItem("num").getNodeValue().trim()) ;
                                             if (jCheckBox17.isSelected()){
                                                num += Integer.parseInt(jTextField11.getText().trim()) ;
                                            // elements3.item(l).getAttributes().getNamedItem("num").setNodeValue(""+num);
                                             elements3.item(l).getAttributes().getNamedItem("num").setTextContent(""+num);
                                             }
                                         }else{
                                             num = Integer.parseInt(elements3.item(l).getAttributes().getNamedItem("num").getNodeValue().trim()) ;
                                             if (jCheckBox23.isSelected()){
                                                num += Integer.parseInt(jTextField17.getText().trim()) ;
                                           //  elements3.item(l).getAttributes().getNamedItem("num").setNodeValue(""+num);
                                             elements3.item(l).getAttributes().getNamedItem("num").setTextContent(""+num);
                                             
                                             }
                                         }
                                         }
                                       }
                                   }
                               }
                           }
                       }
                       }
                   }
                   //Если стоит галка WaterMark => водяной знак
                   if (jCheckBox34.isSelected() && !mnemos4[i].getName().matches(podval)){
                       //сгененрируем ноду WM и сразу добавим
                       schem.appendChild(create_JLOOK(document2));
                    
                   }
                   String schemname = schem.getAttributes().getNamedItem("name").getTextContent();
                   schem.getAttributes().getNamedItem("name").setTextContent(preff+schemname);
                   Transformer transformer = TransformerFactory.newInstance().newTransformer();
                   document2.normalizeDocument();
                   DOMSource source = new DOMSource(document2);
                   StreamResult result = new StreamResult(mnemos4[i]);
                   transformer.transform(source, result);
                   
               }
             }
             //5
             if (jCheckBox6.isSelected()){
                for (int i=0; i < CountM[5]; i++){
                    watching.setText("Сдвигаем привязки " + mnemos5[i].getName());
                    textlog[indexlog++] = "Сдвигаем привязки " + mnemos5[i].getName();
                   Document document2 = documentBuilder2.parse(mnemos5[i]);
                 // Получаем корневой элемент - Нулевой уровень
                   Node schem = document2.getDocumentElement();
                 // получаем все элементы на мнемосхеме - первый уровень
                   NodeList elements1 = schem.getChildNodes();
                 //просматриваем каждый элемент и заходим внутрь если нода не текст - второй уровень
                   for (int j = 0; j<elements1.getLength(); j++)
                   {
                       if (elements1.item(j).getNodeType() != Node.TEXT_NODE){
                           //2 уровень
                            //ЕСЛИ ЭТО ФРЕМ С ПОДВАЛОМ!!
                           if (elements1.item(j).getNodeName().equalsIgnoreCase("frame") &&
                               elements1.item(j).getAttributes().getNamedItem("id").getNodeValue().equalsIgnoreCase("BS")){
                               NodeList elm = elements1.item(j).getChildNodes();
                               for (int k = 0; k < elm.getLength();k++){
                                   if (elm.item(k).getNodeType() != Node.TEXT_NODE){
                                       if (elm.item(k).getNodeName().equalsIgnoreCase("scheme")){
                                           String oldval = elm.item(k).getAttributes().getNamedItem("value").getNodeValue();
                                           elm.item(k).getAttributes().getNamedItem("value").setTextContent(preff+oldval);
                                       }
                                   }
                               }
                           }
                           //ЕСЛИ ЭТО ПРЯМОУГОЛЬНИК БАРС
                           if (elements1.item(j).getNodeName().equalsIgnoreCase("grid") &&
                               elements1.item(j).getAttributes().getNamedItem("type").getNodeValue().equalsIgnoreCase(BARS)){
                               //то все вроде норм
                               
                           }
                           //ЕСЛИ ЭТО САМОПИСЕЦ или СОБЫТИНЫЙ САМОПИСЕЦ
                           if (elements1.item(j).getNodeName().equalsIgnoreCase("grid") &&
                               (elements1.item(j).getAttributes().getNamedItem("type").getNodeValue().equalsIgnoreCase("chart") ||
                                elements1.item(j).getAttributes().getNamedItem("type").getNodeValue().equalsIgnoreCase("tablechart"))){
                               String chartdb = "";
                               String chartnum = "";
                               
                               NodeList elm = elements1.item(j).getChildNodes();
                               for(int k=0;k <elm.getLength();k++){
                                   if (elm.item(k).getNodeType() != Node.TEXT_NODE){
                                       if (elm.item(k).getNodeName().equalsIgnoreCase("instance")){
                                           NodeList elm2 = elm.item(k).getChildNodes();
                                           for(int kk=0;kk <elm2.getLength();kk++){
                                               if (elm2.item(kk).getNodeType() != Node.TEXT_NODE){
                                                   if (elm2.item(kk).getNodeName().equalsIgnoreCase("db")){
                                                       chartdb = elm2.item(kk).getAttributes().getNamedItem("value").getNodeValue();
                                                   }
                                                   if (elm2.item(kk).getNodeName().equalsIgnoreCase("num")){
                                                       intnum =kk;
                                                       chartnum = elm2.item(kk).getAttributes().getNamedItem("value").getNodeValue();
                                                   }
                                               }
                                           }
                                          elm2.item(intnum).getAttributes().getNamedItem("value").setTextContent(get_new_chart(chartdb,
                                                  chartnum,
                                                  Integer.parseInt(jTextField12.getText().trim()),        //Значение для сдвига базы А, 1 шкаф
                                                  Integer.parseInt(jTextField18.getText().trim()),        //Значение для сдвига базы D, 1 шкаф
                                                  Integer.MAX_VALUE,        //Значение границы базы А
                                                  Integer.MAX_VALUE,        //Значение границы базы D
                                                  0, //Значение для сдвига базы А, 2 шкаф
                                                  0)) ;//Значение для сдвига базы D, 2 шкаф
                                          intnum =0;
                                       }
                                   }
                               }
                               
                           }
                           //ЕСЛИ КНОПКА перехода с УПП_мнемосхемы на нормальную
                           if (elements1.item(j).getNodeName().equalsIgnoreCase("grid") &&
                               elements1.item(j).getAttributes().getNamedItem("type").getNodeValue().equalsIgnoreCase("button")){
                               NodeList elm = elements1.item(j).getChildNodes();
                               for (int k = 0; k < elm.getLength();k++){
                                   if (elm.item(k).getNodeType() != Node.TEXT_NODE){
                                       if (elm.item(k).getNodeName().equalsIgnoreCase("comment") &&
                                           elm.item(k).getAttributes().getNamedItem("value").getNodeValue().equalsIgnoreCase(BTMI)){
                                           inst = get_inst(elements1.item(j));
                                           NodeList elm2 = elm.item(inst).getChildNodes();
                                           inst =0;
                                           for (int ii=0;ii<elm2.getLength();ii++){
                                               if (elm2.item(ii).getNodeType() != Node.TEXT_NODE){
                                                   if (elm2.item(ii).getNodeName().equalsIgnoreCase("schemetojump")){
                                                     if (elm2.item(ii).getAttributes().getNamedItem("value").getTextContent().startsWith("УПП")){
                                                         String s1 = elm2.item(ii).getAttributes().getNamedItem("value").getTextContent();
                                                         elm2.item(ii).getAttributes().getNamedItem("value").setTextContent(s1.substring(4));
                                                     } 
                                                   }
                                               }
                                           }
                                       }
                                   }
                               }
                           }
                            //ЕСЛИ ЭТО заглавный текст (должен стать черным)
                           if (elements1.item(j).getNodeName().equalsIgnoreCase("grid") &&
                               elements1.item(j).getAttributes().getNamedItem("type").getNodeValue().equalsIgnoreCase("label")){
                               NodeList elm = elements1.item(j).getChildNodes();
                               for (int k = 0; k < elm.getLength();k++){
                                   if (elm.item(k).getNodeType() != Node.TEXT_NODE){
                                       if (elm.item(k).getNodeName().equalsIgnoreCase("comment") &&
                                           elm.item(k).getAttributes().getNamedItem("value").getNodeValue().equalsIgnoreCase(TITLE)){
                                           inst = get_inst(elements1.item(j));
                                           NodeList elm2 = elm.item(inst).getChildNodes();
                                           inst =0;
                                           for (int ii=0;ii<elm2.getLength();ii++){
                                               if (elm2.item(ii).getNodeType() != Node.TEXT_NODE){
                                                   if (elm2.item(ii).getNodeName().equalsIgnoreCase("color1")){
                                                      elm2.item(ii).getAttributes().getNamedItem("value").setTextContent("0"); 
                                                   }
                                               }
                                           }
                                           
                                       }
                                   }
                               }
                               
                           }
                       NodeList elements2 = elements1.item(j).getChildNodes();
                       for (int k = 0; k < elements2.getLength();k++){
                           if (elements2.item(k).getNodeType() != Node.TEXT_NODE){
                               // если нода не текст и имя её LINK то идем ниже - третий ур
                               if (elements2.item(k).getNodeName().equalsIgnoreCase("link")){
                                   NodeList elements3 = elements2.item(k).getChildNodes();
                                   for (int l=0; l < elements3.getLength();l++){
                                       if (elements3.item(l).getNodeType() != Node.TEXT_NODE){
                                           //зырим атрибуты db и num
                                           // проверяем что это ненасос
                                           if (checking_pump_pulse(elements1.item(j))){
                                               //насос или пульсатор
                                                int num = 0;
                                              if(elements3.item(l).getAttributes().getNamedItem("db").getNodeValue().equalsIgnoreCase("1")){
                                                  num = Integer.parseInt(elements3.item(l).getAttributes().getNamedItem("num").getNodeValue().trim()) ;
                                                  if (jCheckBox18.isSelected() && jCheckBox43.isSelected()){
                                                     num += Integer.parseInt(PCAA.getText().trim()) ;
                                                 // elements3.item(l).getAttributes().getNamedItem("num").setNodeValue(""+num);
                                                  elements3.item(l).getAttributes().getNamedItem("num").setTextContent(""+num);
                                                  }
                                              }else{
                                                  num = Integer.parseInt(elements3.item(l).getAttributes().getNamedItem("num").getNodeValue().trim()) ;
                                                  if (jCheckBox24.isSelected() && jCheckBox44.isSelected()){
                                                     num += Integer.parseInt(PCAD.getText().trim()) ;
                                                //  elements3.item(l).getAttributes().getNamedItem("num").setNodeValue(""+num);
                                                  elements3.item(l).getAttributes().getNamedItem("num").setTextContent(""+num);

                                                  }
                                              }
                                           }
                                           else{
                                               //остальное
                                           int num = 0;
                                         if(elements3.item(l).getAttributes().getNamedItem("db").getNodeValue().equalsIgnoreCase("1")){
                                             num = Integer.parseInt(elements3.item(l).getAttributes().getNamedItem("num").getNodeValue().trim()) ;
                                             if (jCheckBox18.isSelected()){
                                                num += Integer.parseInt(jTextField12.getText().trim()) ;
                                            // elements3.item(l).getAttributes().getNamedItem("num").setNodeValue(""+num);
                                             elements3.item(l).getAttributes().getNamedItem("num").setTextContent(""+num);
                                             }
                                         }else{
                                             num = Integer.parseInt(elements3.item(l).getAttributes().getNamedItem("num").getNodeValue().trim()) ;
                                             if (jCheckBox24.isSelected()){
                                                num += Integer.parseInt(jTextField18.getText().trim()) ;
                                           //  elements3.item(l).getAttributes().getNamedItem("num").setNodeValue(""+num);
                                             elements3.item(l).getAttributes().getNamedItem("num").setTextContent(""+num);
                                             
                                             }
                                         }
                                         }
                                       }
                                   }
                               }
                           }
                       }
                       }
                   }
                   //Если стоит галка WaterMark => водяной знак
                   if (jCheckBox34.isSelected() && !mnemos5[i].getName().matches(podval)){
                       //сгененрируем ноду WM и сразу добавим
                       schem.appendChild(create_JLOOK(document2));
                    
                   }
                   String schemname = schem.getAttributes().getNamedItem("name").getTextContent();
                   schem.getAttributes().getNamedItem("name").setTextContent(preff+schemname);
                   Transformer transformer = TransformerFactory.newInstance().newTransformer();
                   document2.normalizeDocument();
                   DOMSource source = new DOMSource(document2);
                   StreamResult result = new StreamResult(mnemos5[i]);
                   transformer.transform(source, result);
                   
               }
             }
             //6
             if (jCheckBox7.isSelected()){
                for (int i=0; i < CountM[6]; i++){
                    watching.setText("Сдвигаем привязки " + mnemos6[i].getName());
                    textlog[indexlog++] = "Сдвигаем привязки " + mnemos6[i].getName();
                   Document document2 = documentBuilder2.parse(mnemos6[i]);
                 // Получаем корневой элемент - Нулевой уровень
                   Node schem = document2.getDocumentElement();
                 // получаем все элементы на мнемосхеме - первый уровень
                   NodeList elements1 = schem.getChildNodes();
                 //просматриваем каждый элемент и заходим внутрь если нода не текст - второй уровень
                   for (int j = 0; j<elements1.getLength(); j++)
                   {
                       if (elements1.item(j).getNodeType() != Node.TEXT_NODE){
                           //2 уровень
                            //ЕСЛИ ЭТО ФРЕМ С ПОДВАЛОМ!!
                           if (elements1.item(j).getNodeName().equalsIgnoreCase("frame") &&
                               elements1.item(j).getAttributes().getNamedItem("id").getNodeValue().equalsIgnoreCase("BS")){
                               NodeList elm = elements1.item(j).getChildNodes();
                               for (int k = 0; k < elm.getLength();k++){
                                   if (elm.item(k).getNodeType() != Node.TEXT_NODE){
                                       if (elm.item(k).getNodeName().equalsIgnoreCase("scheme")){
                                           String oldval = elm.item(k).getAttributes().getNamedItem("value").getNodeValue();
                                           elm.item(k).getAttributes().getNamedItem("value").setTextContent(preff+oldval);
                                       }
                                   }
                               }
                           }
                           //ЕСЛИ ЭТО ПРЯМОУГОЛЬНИК БАРС
                           if (elements1.item(j).getNodeName().equalsIgnoreCase("grid") &&
                               elements1.item(j).getAttributes().getNamedItem("type").getNodeValue().equalsIgnoreCase(BARS)){
                               //то все вроде норм
                               
                           }
                           //ЕСЛИ ЭТО САМОПИСЕЦ или СОБЫТИНЫЙ САМОПИСЕЦ
                           if (elements1.item(j).getNodeName().equalsIgnoreCase("grid") &&
                               (elements1.item(j).getAttributes().getNamedItem("type").getNodeValue().equalsIgnoreCase("chart") ||
                                elements1.item(j).getAttributes().getNamedItem("type").getNodeValue().equalsIgnoreCase("tablechart"))){
                               String chartdb = "";
                               String chartnum = "";
                               
                               NodeList elm = elements1.item(j).getChildNodes();
                               for(int k=0;k <elm.getLength();k++){
                                   if (elm.item(k).getNodeType() != Node.TEXT_NODE){
                                       if (elm.item(k).getNodeName().equalsIgnoreCase("instance")){
                                           NodeList elm2 = elm.item(k).getChildNodes();
                                           for(int kk=0;kk <elm2.getLength();kk++){
                                               if (elm2.item(kk).getNodeType() != Node.TEXT_NODE){
                                                   if (elm2.item(kk).getNodeName().equalsIgnoreCase("db")){
                                                       chartdb = elm2.item(kk).getAttributes().getNamedItem("value").getNodeValue();
                                                   }
                                                   if (elm2.item(kk).getNodeName().equalsIgnoreCase("num")){
                                                       intnum =kk;
                                                       chartnum = elm2.item(kk).getAttributes().getNamedItem("value").getNodeValue();
                                                   }
                                               }
                                           }
                                          elm2.item(intnum).getAttributes().getNamedItem("value").setTextContent(get_new_chart(chartdb,
                                                  chartnum,
                                                  Integer.parseInt(jTextField13.getText().trim()),        //Значение для сдвига базы А, 1 шкаф
                                                  Integer.parseInt(jTextField19.getText().trim()),        //Значение для сдвига базы D, 1 шкаф
                                                  Integer.MAX_VALUE,        //Значение границы базы А
                                                  Integer.MAX_VALUE,        //Значение границы базы D
                                                  0, //Значение для сдвига базы А, 2 шкаф
                                                  0)) ;//Значение для сдвига базы D, 2 шкаф
                                          intnum =0;
                                       }
                                   }
                               }
                               
                           }
                           //ЕСЛИ КНОПКА перехода с УПП_мнемосхемы на нормальную
                           if (elements1.item(j).getNodeName().equalsIgnoreCase("grid") &&
                               elements1.item(j).getAttributes().getNamedItem("type").getNodeValue().equalsIgnoreCase("button")){
                               NodeList elm = elements1.item(j).getChildNodes();
                               for (int k = 0; k < elm.getLength();k++){
                                   if (elm.item(k).getNodeType() != Node.TEXT_NODE){
                                       if (elm.item(k).getNodeName().equalsIgnoreCase("comment") &&
                                           elm.item(k).getAttributes().getNamedItem("value").getNodeValue().equalsIgnoreCase(BTMI)){
                                           inst = get_inst(elements1.item(j));
                                           NodeList elm2 = elm.item(inst).getChildNodes();
                                           inst =0;
                                           for (int ii=0;ii<elm2.getLength();ii++){
                                               if (elm2.item(ii).getNodeType() != Node.TEXT_NODE){
                                                   if (elm2.item(ii).getNodeName().equalsIgnoreCase("schemetojump")){
                                                     if (elm2.item(ii).getAttributes().getNamedItem("value").getTextContent().startsWith("УПП")){
                                                         String s1 = elm2.item(ii).getAttributes().getNamedItem("value").getTextContent();
                                                         elm2.item(ii).getAttributes().getNamedItem("value").setTextContent(s1.substring(4));
                                                     } 
                                                   }
                                               }
                                           }
                                       }
                                   }
                               }
                           }
                            //ЕСЛИ ЭТО заглавный текст (должен стать черным)
                           if (elements1.item(j).getNodeName().equalsIgnoreCase("grid") &&
                               elements1.item(j).getAttributes().getNamedItem("type").getNodeValue().equalsIgnoreCase("label")){
                               NodeList elm = elements1.item(j).getChildNodes();
                               for (int k = 0; k < elm.getLength();k++){
                                   if (elm.item(k).getNodeType() != Node.TEXT_NODE){
                                       if (elm.item(k).getNodeName().equalsIgnoreCase("comment") &&
                                           elm.item(k).getAttributes().getNamedItem("value").getNodeValue().equalsIgnoreCase(TITLE)){
                                           inst = get_inst(elements1.item(j));
                                           NodeList elm2 = elm.item(inst).getChildNodes();
                                           inst =0;
                                           for (int ii=0;ii<elm2.getLength();ii++){
                                               if (elm2.item(ii).getNodeType() != Node.TEXT_NODE){
                                                   if (elm2.item(ii).getNodeName().equalsIgnoreCase("color1")){
                                                      elm2.item(ii).getAttributes().getNamedItem("value").setTextContent("0"); 
                                                   }
                                               }
                                           }
                                           
                                       }
                                   }
                               }
                               
                           }
                       NodeList elements2 = elements1.item(j).getChildNodes();
                       for (int k = 0; k < elements2.getLength();k++){
                           if (elements2.item(k).getNodeType() != Node.TEXT_NODE){
                               // если нода не текст и имя её LINK то идем ниже - третий ур
                               if (elements2.item(k).getNodeName().equalsIgnoreCase("link")){
                                   NodeList elements3 = elements2.item(k).getChildNodes();
                                   for (int l=0; l < elements3.getLength();l++){
                                       if (elements3.item(l).getNodeType() != Node.TEXT_NODE){
                                           //зырим атрибуты db и num
                                           // проверяем что это ненасос
                                           if (checking_pump_pulse(elements1.item(j))){
                                               //насос или пульсатор
                                                int num = 0;
                                              if(elements3.item(l).getAttributes().getNamedItem("db").getNodeValue().equalsIgnoreCase("1")){
                                                  num = Integer.parseInt(elements3.item(l).getAttributes().getNamedItem("num").getNodeValue().trim()) ;
                                                  if (jCheckBox19.isSelected() && jCheckBox45.isSelected()){
                                                     num += Integer.parseInt(PCMA.getText().trim()) ;
                                                 // elements3.item(l).getAttributes().getNamedItem("num").setNodeValue(""+num);
                                                  elements3.item(l).getAttributes().getNamedItem("num").setTextContent(""+num);
                                                  }
                                              }else{
                                                  num = Integer.parseInt(elements3.item(l).getAttributes().getNamedItem("num").getNodeValue().trim()) ;
                                                  if (jCheckBox25.isSelected() && jCheckBox46.isSelected()){
                                                     num += Integer.parseInt(PCMD.getText().trim()) ;
                                                //  elements3.item(l).getAttributes().getNamedItem("num").setNodeValue(""+num);
                                                  elements3.item(l).getAttributes().getNamedItem("num").setTextContent(""+num);

                                                  }
                                              }
                                           }
                                           else{
                                               //остальное
                                           int num = 0;
                                         if(elements3.item(l).getAttributes().getNamedItem("db").getNodeValue().equalsIgnoreCase("1")){
                                             num = Integer.parseInt(elements3.item(l).getAttributes().getNamedItem("num").getNodeValue().trim()) ;
                                             if (jCheckBox19.isSelected()){
                                                num += Integer.parseInt(jTextField13.getText().trim()) ;
                                            // elements3.item(l).getAttributes().getNamedItem("num").setNodeValue(""+num);
                                             elements3.item(l).getAttributes().getNamedItem("num").setTextContent(""+num);
                                             }
                                         }else{
                                             num = Integer.parseInt(elements3.item(l).getAttributes().getNamedItem("num").getNodeValue().trim()) ;
                                             if (jCheckBox25.isSelected()){
                                                num += Integer.parseInt(jTextField19.getText().trim()) ;
                                           //  elements3.item(l).getAttributes().getNamedItem("num").setNodeValue(""+num);
                                             elements3.item(l).getAttributes().getNamedItem("num").setTextContent(""+num);
                                             
                                             }
                                         }}
                                       }
                                   }
                               }
                           }
                       }
                       }
                   }
                   //Если стоит галка WaterMark => водяной знак
                   if (jCheckBox34.isSelected()  && !mnemos6[i].getName().matches(podval)){
                       //сгененрируем ноду WM и сразу добавим
                       schem.appendChild(create_JLOOK(document2));
                    
                   }
                   String schemname = schem.getAttributes().getNamedItem("name").getTextContent();
                   schem.getAttributes().getNamedItem("name").setTextContent(preff+schemname);
                   Transformer transformer = TransformerFactory.newInstance().newTransformer();
                   document2.normalizeDocument();
                   DOMSource source = new DOMSource(document2);
                   StreamResult result = new StreamResult(mnemos6[i]);
                   transformer.transform(source, result);
                   
               }
             }
                textlog[indexlog++] = "ok!";
                }
                catch(Exception e){
                textlog[indexlog++] = "ошибка перепривязки!";
                textlog[indexlog++] = ""+e.getMessage();
                    System.out.println("Еррор");
                }
        
        
        System.out.println("Начинаем сдвигать базы");
    }
    
    //checking_pump_pulse(elements1.item(j))
    public boolean checking_pump_pulse(Node nd1){
        boolean res = false;
            // тип ноды 1
            if (nd1.getAttributes().getNamedItem("type").getTextContent().equalsIgnoreCase("palette")){
                NodeList elements2 = nd1.getChildNodes();
                       for (int k = 0; k < elements2.getLength();k++){
                           if (elements2.item(k).getNodeType() != Node.TEXT_NODE){
                               // если нода не текст и имя её instance то идем ниже - третий ур
                               if (elements2.item(k).getNodeName().equalsIgnoreCase("instance")){
                                   NodeList elements3 = elements2.item(k).getChildNodes();
                                   for (int l=0; l < elements3.getLength();l++){
                                       if (elements3.item(l).getNodeType() != Node.TEXT_NODE){
                                           if ((elements3.item(l).getNodeName().equalsIgnoreCase("guid"))
                                                   &&
                                                (elements3.item(l).getAttributes().getNamedItem("value").getTextContent().matches(pomp)
                                                   ||
                                                   elements3.item(l).getAttributes().getNamedItem("value").getTextContent().matches(pulse))){
                                               res = true;
                                           }
                                       }
                                   }
                               }
                           }
                       }
                
            }
        return res;
    }
    
    public String get_new_chart(String db, String num, int A, int D, int border_A, int border_D, int A2, int D2){
        String res ="";
       // String[] dbs = db.split("\\Q.\\E");
          String[] dbs = db.split(" ");
          String[] nums = num.split(" ");
          for (int qq=0;qq< dbs.length;qq++){
              switch (dbs[qq]){
                  case ("0"):
                      if (Integer.parseInt(nums[qq]) < border_D){
                          res=res+ (Integer.parseInt(nums[qq]) + D) +" ";
                      }
                      else{
                          res=res+ (Integer.parseInt(nums[qq]) + D2) +" ";
                      }
                       break;
                  case ("1"):
                      if (Integer.parseInt(nums[qq]) < border_A){
                          res = res + (Integer.parseInt(nums[qq]) + A) +" ";
                      }
                      else{
                          res = res + (Integer.parseInt(nums[qq]) + A2) +" ";
                      }
                      break;
                  default:
                      
                      break;
              }
          }
       return res;
    }
    public void do_checksum(){
        
            if (jCheckBox5.isSelected()){
                 try{
                     DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                for (int i = 0; i<CountM[1];i++){
                    String cs = get_checksum(mnemos1[i]);
                   
                   Document document = documentBuilder.parse(mnemos1[i]);
                 // Получаем корневой элемент - Нулевой уровень
                   Node schem = document.getDocumentElement();
                    schem.getAttributes().getNamedItem("checksum").setTextContent(cs);
                   Transformer transformer = TransformerFactory.newInstance().newTransformer();
                   document.normalizeDocument();
                   DOMSource source = new DOMSource(document);
                   StreamResult result  = new StreamResult(mnemos1[i]);
                   transformer.transform(source, result);
                    }
                 }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                }
            
    }
    
    public void revers_mnemos(){
        for (int i=0;i< CountM[1];i++){
            if (mnemos1[i] != null){
                mnemos1[i] = new File(jTextField1.getText()+"\\"+preff+mnemos1[i].getName());
            System.out.println(mnemos1[i].getPath());
            }
        }
         for (int i=0;i< CountM[2];i++){
            if (mnemos2[i] != null){
                mnemos2[i] = new File(jTextField1.getText()+"\\"+preff+mnemos2[i].getName());
            System.out.println(mnemos2[i].getPath());
            }
        }
          for (int i=0;i< CountM[3];i++){
            if (mnemos3[i] != null){
                mnemos3[i] = new File(jTextField1.getText()+"\\"+preff+mnemos3[i].getName());
            System.out.println(mnemos3[i].getPath());
            }
        }
           for (int i=0;i< CountM[4];i++){
            if (mnemos4[i] != null){
                mnemos4[i] = new File(jTextField1.getText()+"\\"+preff+mnemos4[i].getName());
            System.out.println(mnemos4[i].getPath());
            }
        }
            for (int i=0;i< CountM[5];i++){
            if (mnemos5[i] != null){
                mnemos5[i] = new File(jTextField1.getText()+"\\"+preff+mnemos5[i].getName());
            System.out.println(mnemos5[i].getPath());
            }
        }
            for (int i=0;i< CountM[6];i++){
            if (mnemos6[i] != null){
                mnemos6[i] = new File(jTextField1.getText()+"\\"+preff+mnemos6[i].getName());
            System.out.println(mnemos6[i].getPath());
            }
        }
    }
    
    public void feel_countM(int m, File[] mnemos){
        for (int i =0;i < mnemos.length; i++){
            if (mnemos[i] != null){
                CountM[m]++;
            }
        }
    }
    
    public void clear_mnemos(){
        // удаляем дефолтные мнемосхемы, если нет галки
        //1
        int c = 0;
        if (!jCheckBox8.isSelected() && jCheckBox5.isSelected()){
            for (int i =0; i< CountM[1]; i++){
               if (mnemos1[i].getName().equalsIgnoreCase("Base.xms") || 
                       mnemos1[i].getName().equalsIgnoreCase("Звуки.xms")
                       //|| mnemos1[i].getName().endsWith("Trends.xms") ||
                       //mnemos1[i].getName().endsWith("Тренды.xms")
                       ){
                   mnemos1[i] = null;
                   c++;
               }
               
            }
            //сдвигаем элементы массива влево
            for (int i = 0; i< CountM[1]; i++){
                if (mnemos1[i] == null){
                    for (int j=CountM[1]-1;j>i;j--){
                        if(mnemos1[j] != null){
                        mnemos1[i] = mnemos1[j];
                        mnemos1[j] = null;
                        break;
                    }
                    }
                }
            }
    
            CountM[1] -= c;
            c = 0;
        }
        //2
                if (!jCheckBox9.isSelected() && jCheckBox2.isSelected()){
            for (int i =0; i< CountM[2]; i++){
               if (mnemos2[i].getName().equalsIgnoreCase("Base.xms") ||
                       mnemos2[i].getName().equalsIgnoreCase("Звуки.xms") 
                      // || mnemos2[i].getName().endsWith("Trends.xms")  ||
                      // mnemos2[i].getName().endsWith("Тренды.xms")
                       ){
                   mnemos2[i] = null;
                   c++;
               }
               
            }
            //сдвигаем элементы массива влево
            for (int i = 0; i< CountM[2]; i++){
                if (mnemos2[i] == null){
                    for (int j=CountM[2]-1;j>i;j--){
                        if(mnemos2[j] != null){
                        mnemos2[i] = mnemos2[j];
                        mnemos2[j] = null;
                        break;
                    }
                    }
                }
            }
            CountM[2] -= c;
            c = 0;
        }
        //3
           if (!jCheckBox10.isSelected() && jCheckBox3.isSelected()){
            for (int i =0; i< CountM[3]; i++){
               if (mnemos3[i].getName().equalsIgnoreCase("Base.xms") ||
                       mnemos3[i].getName().equalsIgnoreCase("Звуки.xms") 
                      // || mnemos3[i].getName().endsWith("Trends.xms") ||
                      // mnemos3[i].getName().endsWith("Тренды.xms")
                       ){
                   mnemos3[i] = null;
                   c++;
               }
               
            }
             //сдвигаем элементы массива влево
            for (int i = 0; i< CountM[3]; i++){
                if (mnemos3[i] == null){
                    for (int j=CountM[3]-1;j>i;j--){
                        if(mnemos3[j] != null){
                        mnemos3[i] = mnemos3[j];
                        mnemos3[j] = null;
                        break;
                    }
                    }
                }
            }
            CountM[3] -= c;
            c = 0;
        }
        //4
           if (!jCheckBox11.isSelected() && jCheckBox4.isSelected()){
            for (int i =0; i< CountM[4]; i++){
               if (mnemos4[i].getName().equalsIgnoreCase("Base.xms") || 
                       mnemos4[i].getName().equalsIgnoreCase("Звуки.xms")
                      // || mnemos4[i].getName().endsWith("Trends.xms") ||
                      // mnemos4[i].getName().endsWith("Тренды.xms")
                       ){
                   mnemos4[i] = null;
                   c++;
               }
               
            }
           //сдвигаем элементы массива влево
            for (int i = 0; i< CountM[4]; i++){
                if (mnemos4[i] == null){
                    for (int j=CountM[4]-1;j>i;j--){
                        if(mnemos4[j] != null){
                        mnemos4[i] = mnemos4[j];
                        mnemos4[j] = null;
                        break;
                    }
                    }
                }
            }
            CountM[4] -= c;
            c = 0;
        }
        //5
           if (!jCheckBox12.isSelected() && jCheckBox6.isSelected()){
            for (int i =0; i< CountM[5]; i++){
               if (mnemos5[i].getName().equalsIgnoreCase("Base.xms") ||
                       mnemos5[i].getName().equalsIgnoreCase("Звуки.xms") 
                      // || mnemos5[i].getName().endsWith("Trends.xms") ||
                      // mnemos5[i].getName().endsWith("Тренды.xms")
                       ){
                   mnemos5[i] = null;
                   c++;
               }
               
            }
            //сдвигаем элементы массива влево
            for (int i = 0; i< CountM[5]; i++){
                if (mnemos5[i] == null){
                    for (int j=CountM[5]-1;j>i;j--){
                        if(mnemos5[j] != null){
                        mnemos5[i] = mnemos5[j];
                        mnemos5[j] = null;
                        break;
                    }
                    }
                }
            }
            CountM[5] -= c;
            c = 0;
        }
        //6
           if (!jCheckBox13.isSelected() && jCheckBox7.isSelected()){
            for (int i =0; i< CountM[6]; i++){
               if (mnemos6[i].getName().equalsIgnoreCase("Base.xms") ||
                      mnemos6[i].getName().equalsIgnoreCase("Звуки.xms")
                      // || mnemos6[i].getName().endsWith("Trends.xms") ||
                      // mnemos6[i].getName().endsWith("Тренды.xms") 
                       ){
                   mnemos6[i] = null;
                   c++;
               }
               
            }
            //сдвигаем элементы массива влево
            for (int i = 0; i< CountM[6]; i++){
                if (mnemos6[i] == null){
                    for (int j=CountM[6]-1;j>i;j--){
                        if(mnemos6[j] != null){
                        mnemos6[i] = mnemos6[j];
                        mnemos6[j] = null;
                        break;
                    }
                    }
                }
            }
           CountM[6] -= c;
            c = 0;
        }
    }
    public void reset_arrays(){
        for (int i =0;i < mnemos1.length;i++)
        {
            mnemos1[i] = null;
            mnemos2[i] = null;
            mnemos3[i] = null;
            mnemos4[i] = null;
            mnemos5[i] = null;
            mnemos6[i] = null;
        }
        
       for(int i =0; i<7;i++){
           CountM[i] = 0;
        }
    }
    
   public void convert_arrays(){
       for (int i = 0; i < mnemos1.length;i++){
           if (mnemos1[i] == null){
               
           }
           else {
           mnemos1[i] = new File (jTextField1.getText()+"\\"+mnemos1[i].getName());
           }
       }
              for (int i = 0; i < mnemos2.length;i++){
           if (mnemos2[i] == null){
               
           }
           else {
           mnemos2[i] = new File (jTextField1.getText()+"\\"+mnemos2[i].getName());
           }
       }
          for (int i = 0; i < mnemos3.length;i++){
           if (mnemos3[i] == null){
               
           }
           else {
           mnemos3[i] = new File (jTextField1.getText()+"\\"+mnemos3[i].getName());
           }
       }
                 for (int i = 0; i < mnemos4.length;i++){
           if (mnemos4[i] == null){
               
           }
           else {
           mnemos4[i] = new File (jTextField1.getText()+"\\"+mnemos4[i].getName());
           }
       }
           for (int i = 0; i < mnemos5.length;i++){
           if (mnemos5[i] == null){
               
           }
           else {
           mnemos5[i] = new File (jTextField1.getText()+"\\"+mnemos5[i].getName());
           }
       }
           for (int i = 0; i < mnemos6.length;i++){
           if (mnemos6[i] == null){
               
           }
           else {
           mnemos6[i] = new File (jTextField1.getText()+"\\"+mnemos6[i].getName());
           }
       }

   }
    public void copy_schemes(){
        if (jCheckBox5.isSelected() ){
            for(int i =0;i<CountM[1];i++){
                   // System.out.println(mnemos1[i].getName());
                    copy_files(mnemos1[i], new File(jTextField1.getText()+"\\"+preff+mnemos1[i].getName()));
            }
        }
        if (jCheckBox2.isSelected() ){
            for(int i =0;i<CountM[2];i++){
                   // System.out.println(mnemos1[i].getName());
                    copy_files(mnemos2[i], new File(jTextField1.getText()+"\\"+preff+mnemos2[i].getName()));
            }
        }
        if (jCheckBox3.isSelected() ){
            for(int i =0;i<CountM[3];i++){
                   // System.out.println(mnemos1[i].getName());
                    copy_files(mnemos3[i], new File(jTextField1.getText()+"\\"+preff+mnemos3[i].getName()));
            }
        }
        if (jCheckBox4.isSelected() ){
            for(int i =0;i<CountM[4];i++){
                   // System.out.println(mnemos1[i].getName());
                    copy_files(mnemos4[i], new File(jTextField1.getText()+"\\"+preff+mnemos4[i].getName()));
            }
        }
        if (jCheckBox6.isSelected() ){
            for(int i =0;i<CountM[5];i++){
                   // System.out.println(mnemos1[i].getName());
                    copy_files(mnemos5[i], new File(jTextField1.getText()+"\\"+preff+mnemos5[i].getName()));
            }
        }
        if (jCheckBox7.isSelected() ){
            for(int i =0;i<CountM[6];i++){
                   // System.out.println(mnemos1[i].getName());
                    copy_files(mnemos6[i], new File(jTextField1.getText()+"\\"+preff+mnemos6[i].getName()));
            }
        }
        
    }
    public void read_xml(){
        try{
         //   TimeUnit.SECONDS.sleep(1);
             DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        for (int i=0; i < CountM[1]; i++){

            textlog[indexlog++] = mnemos1[i].getName();
            watching.setText(mnemos1[i].getName());
            System.out.println(mnemos1[i].getName());
            Document document = documentBuilder.parse(mnemos1[i]);
            // Получаем корневой элемент
            Node schem = document.getDocumentElement();
          seek_other_images(schem.getAttributes().getNamedItem("raster").getNodeValue(), jTextField2.getText());

        }
                for (int i=0; i < CountM[2]; i++){
                    textlog[indexlog++] = mnemos2[i].getName();
                    watching.setText(mnemos2[i].getName());
                   System.out.println(mnemos2[i].getName());
            Document document = documentBuilder.parse(mnemos2[i]);
            // Получаем корневой элемент
            Node schem = document.getDocumentElement();
          seek_other_images(schem.getAttributes().getNamedItem("raster").getNodeValue(), jTextField3.getText());
    
        }
         for (int i=0; i < CountM[3]; i++){
                   textlog[indexlog++] = mnemos3[i].getName();
                   watching.setText(mnemos3[i].getName());
                   System.out.println(mnemos3[i].getName());
            Document document = documentBuilder.parse(mnemos3[i]);
            // Получаем корневой элемент
            Node schem = document.getDocumentElement();
          seek_other_images(schem.getAttributes().getNamedItem("raster").getNodeValue(), jTextField4.getText());

        }     
         for (int i=0; i < CountM[4]; i++){
                   textlog[indexlog++] = mnemos4[i].getName();
                   watching.setText(mnemos4[i].getName());
                   System.out.println(mnemos4[i].getName());
            Document document = documentBuilder.parse(mnemos4[i]);
            // Получаем корневой элемент
            Node schem = document.getDocumentElement();
          seek_other_images(schem.getAttributes().getNamedItem("raster").getNodeValue(), jTextField5.getText());

        }
         for (int i=0; i < CountM[5]; i++){
                   textlog[indexlog++] = mnemos5[i].getName();
                   watching.setText(mnemos5[i].getName());
                   System.out.println(mnemos5[i].getName());
            Document document = documentBuilder.parse(mnemos5[i]);
            // Получаем корневой элемент
            Node schem = document.getDocumentElement();
          seek_other_images(schem.getAttributes().getNamedItem("raster").getNodeValue(), jTextField6.getText());

        }     
           for (int i=0; i < CountM[6]; i++){
                   textlog[indexlog++] = mnemos6[i].getName();
                   watching.setText(mnemos6[i].getName());
                   System.out.println(mnemos6[i].getName());
            Document document = documentBuilder.parse(mnemos6[i]);
            // Получаем корневой элемент
            Node schem = document.getDocumentElement();
          seek_other_images(schem.getAttributes().getNamedItem("raster").getNodeValue(), jTextField7.getText());

        }     
        }
        catch(Exception e){
            textlog[indexlog++] = "Ошибка чтения xms";
            watching.setText("Ошибка чтения xms");
                   if (jCheckBox1.isSelected()){
            addLog();
        }
            System.out.println("ERROR ");
            System.out.println(e);
        }
    }
    
    public void seek_other_images(String img, String path){
        if (img == ""){
            //если нет подложки то хз
        }
        else if (allfiles) {
            //есил каким-то чудом стоит галка "все файлы загружать"
                String[] img_split = img.split("\\Q.\\E");
                File imgpath = new File(path);
                File[] other_img = imgpath.listFiles(new FilenameFilter() 
            {
                 public boolean accept(File dir, String name)
                 {
                    // return name.endsWith("xms");
                     return name.startsWith(img_split[0]);
                 }
            });
                
                for(int i = 0;i<other_img.length;i++){
                    textlog[indexlog++] = "копируем "+other_img[i];
                    watching.setText("копируем "+other_img[i]);
                    System.out.println(other_img[i]);
                    copy_files(other_img[i], new File(jTextField1.getText()+"\\"+other_img[i].getName()));
                }
        }
        else{
            //незагружать все файлы
            File newimg = new File(path+"\\"+img);
                     textlog[indexlog++] = "копируем "+newimg;
                    watching.setText(" копируем "+newimg);
                    System.out.println(newimg);
                    copy_files(newimg, new File(jTextField1.getText()+"\\"+newimg.getName()));
        }
    }
    
    public void copy_files(File source, File dest) {
    FileChannel sourceChannel = null;
    FileChannel destChannel = null;
    try{
     //   TimeUnit.SECONDS.sleep(1);
     sourceChannel = new FileInputStream(source).getChannel();
    destChannel = new FileOutputStream(dest).getChannel();
    destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
               sourceChannel.close();
           destChannel.close();
    }
    catch(Exception e){
        textlog[indexlog++] = "ошибка копировния";
        watching.setText("Ошибка копирования, смотри лог файл");
        System.out.println("ERROR COPY");
        System.out.println(e);
               if (jCheckBox1.isSelected()){
            addLog();
        }
            }
    }
    private void jButton9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton9MouseClicked
        save_config();
        System.exit(0);
    }//GEN-LAST:event_jButton9MouseClicked

    private void OpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OpenActionPerformed
        open_config();
   /*     try{
        File config_open = new File(CurDir, configur);
        FileReader confReader = new FileReader(config_open);
        Scanner scan_conf = new Scanner(confReader);
        String res = "";
        String[] read_str = new String[3];
        
        while (scan_conf.hasNextLine()){
            res = scan_conf.nextLine();
            if ((res.equalsIgnoreCase("[Directories]")) || (res.equalsIgnoreCase("[Bases]"))){
              //  continue;
            }
            else{
                read_str = res.split("\\Q.\\E");
            switch (read_str[0]){
                case ("0"):
                    if(read_str.length > 1)
                    jTextField1.setText(read_str[1]);
                break;
                case ("1"):
                    if(read_str.length > 1)
                    jTextField2.setText(read_str[1]);
                break;
                case ("2"):
                    if(read_str.length > 1)
                    jTextField3.setText(read_str[1]);
                    break;
                 case ("3"):
                    if(read_str.length > 1)
                    jTextField4.setText(read_str[1]);
                    break;
                case ("4"):
                    if(read_str.length > 1)
                    jTextField5.setText(read_str[1]);
                    break;
                case ("5"):
                    if(read_str.length > 1)
                    jTextField6.setText(read_str[1]);
                    break;
                case ("6"):
                    if(read_str.length > 1)
                    jTextField7.setText(read_str[1]);
                    break;
                case ("7"):
                    if(read_str.length > 1)
                    jTextField20.setText(read_str[1]);
                    break;
                case ("1A"):
                    if(read_str.length > 1)
                    jTextField8.setText(read_str[1]);
                    break;
                case ("1D"):
                    if(read_str.length > 1)
                    jTextField14.setText(read_str[1]);
                    break;
                case ("2A"):
                    if(read_str.length > 1)
                    jTextField9.setText(read_str[1]);
                    break;
                case ("2D"):
                    if(read_str.length > 1)
                    jTextField15.setText(read_str[1]);
                    break;
                case ("3A"):
                    if(read_str.length > 1)
                    jTextField10.setText(read_str[1]);
                    break;
                case ("3D"):
                    if(read_str.length > 1)
                    jTextField16.setText(read_str[1]);
                    break;
                case ("4A"):
                    if(read_str.length > 1)
                    jTextField11.setText(read_str[1]);
                    break;
                case ("4D"):
                    if(read_str.length > 1)
                    jTextField17.setText(read_str[1]);
                    break;
                case ("5A"):
                    if(read_str.length > 1)
                    jTextField12.setText(read_str[1]);
                    break;
                case ("5D"):
                    if(read_str.length > 1)
                    jTextField18.setText(read_str[1]);
                    break;
                case ("6A"):
                    if(read_str.length > 1)
                    jTextField13.setText(read_str[1]);
                    break;
                case ("6D"):
                    if(read_str.length > 1)
                    jTextField19.setText(read_str[1]);
                    break;
                default:
                   // System.out.println(read_str[1]);
                break;
                    
            }
            }

            }
            scan_conf.close();
            confReader.close();
           
        }
        catch(Exception e){
            System.out.println("ERROR READ CONFIGURE");
        }
        */
    }//GEN-LAST:event_OpenActionPerformed

    public void open_config(){
                try{
        File config_open = new File(CurDir, configur);
        FileReader confReader = new FileReader(config_open);
        Scanner scan_conf = new Scanner(confReader);
        String res = "";
        String[] read_str = new String[3];
        
        while (scan_conf.hasNextLine()){
            res = scan_conf.nextLine();
            if ((res.equalsIgnoreCase("[Directories]")) || (res.equalsIgnoreCase("[Bases]")) || (res.equalsIgnoreCase("[Checkbox]"))){
              //  continue;
            }
            else{
                read_str = res.split("\\Q.\\E");
            switch (read_str[0]){
                case ("0"):
                    if(read_str.length > 1)
                    jTextField1.setText(read_str[1]);
                break;
                case ("1"):
                    if(read_str.length > 1)
                    jTextField2.setText(read_str[1]);
                break;
                case ("2"):
                    if(read_str.length > 1)
                    jTextField3.setText(read_str[1]);
                    break;
                 case ("3"):
                    if(read_str.length > 1)
                    jTextField4.setText(read_str[1]);
                    break;
                case ("4"):
                    if(read_str.length > 1)
                    jTextField5.setText(read_str[1]);
                    break;
                case ("5"):
                    if(read_str.length > 1)
                    jTextField6.setText(read_str[1]);
                    break;
                case ("6"):
                    if(read_str.length > 1)
                    jTextField7.setText(read_str[1]);
                    break;
                case ("7"):
                    if(read_str.length > 1)
                    jTextField20.setText(read_str[1]);
                    break;
                case ("1A"):
                    if(read_str.length > 1)
                    jTextField8.setText(read_str[1]);
                    break;
                case ("1D"):
                    if(read_str.length > 1)
                    jTextField14.setText(read_str[1]);
                    break;
                case ("2A"):
                    if(read_str.length > 1)
                    jTextField9.setText(read_str[1]);
                    break;
                case ("2A_b"):
                    if(read_str.length > 1)
                    jTextField21.setText(read_str[1]);
                    break;
                case ("2A_2"):
                    if(read_str.length > 1)
                    jTextField23.setText(read_str[1]);
                    break;
                case ("2D"):
                    if(read_str.length > 1)
                    jTextField15.setText(read_str[1]);
                    break;
                case ("2D_b"):
                    if(read_str.length > 1)
                    jTextField22.setText(read_str[1]);
                    break;
                 case ("2D_2"):
                    if(read_str.length > 1)
                    jTextField24.setText(read_str[1]);
                    break;
                case ("3A"):
                    if(read_str.length > 1)
                    jTextField10.setText(read_str[1]);
                    break;
                case ("3D"):
                    if(read_str.length > 1)
                    jTextField16.setText(read_str[1]);
                    break;
                case ("4A"):
                    if(read_str.length > 1)
                    jTextField11.setText(read_str[1]);
                    break;
                case ("4D"):
                    if(read_str.length > 1)
                    jTextField17.setText(read_str[1]);
                    break;
                case ("5A"):
                    if(read_str.length > 1)
                    jTextField12.setText(read_str[1]);
                    break;
                case ("5D"):
                    if(read_str.length > 1)
                    jTextField18.setText(read_str[1]);
                    break;
                case ("6A"):
                    if(read_str.length > 1)
                    jTextField13.setText(read_str[1]);
                    break;
                case ("6D"):
                    if(read_str.length > 1)
                    jTextField19.setText(read_str[1]);
                    break;
                case ("PCPA"):
                    if(read_str.length > 1)
                    PCPA.setText(read_str[1]);
                    break;
                case ("PCPD"):
                    if(read_str.length > 1)
                    PCPD.setText(read_str[1]);
                    break;
                case ("PCEA"):
                    if(read_str.length > 1)
                    PCEA.setText(read_str[1]);
                    break;
                case ("PCED"):
                    if(read_str.length > 1)
                    PCED.setText(read_str[1]);
                    break;
                case ("PCGA"):
                    if(read_str.length > 1)
                    PCGA.setText(read_str[1]);
                    break;
                case ("PCGD"):
                    if(read_str.length > 1)
                    PCGD.setText(read_str[1]);
                    break;
                case ("PCSA"):
                    if(read_str.length > 1)
                    PCSA.setText(read_str[1]);
                    break;
                case ("PCSD"):
                    if(read_str.length > 1)
                    PCSD.setText(read_str[1]);
                    break;
                case ("PCAA"):
                    if(read_str.length > 1)
                    PCAA.setText(read_str[1]);
                    break;
                case ("PCAD"):
                    if(read_str.length > 1)
                    PCAD.setText(read_str[1]);
                    break;
                case ("PCMA"):
                    if(read_str.length > 1)
                    PCMA.setText(read_str[1]);
                    break;
                case ("PCMD"):
                    if(read_str.length > 1)
                    PCMD.setText(read_str[1]);
                    break;
                case ("CB1"):
                    if(read_str.length > 1)
                        jCheckBox1.setSelected(Boolean.parseBoolean(read_str[1]));
                    break;
                case ("CB2"):
                    if(read_str.length > 1)
                        jCheckBox2.setSelected(Boolean.parseBoolean(read_str[1]));
                    break;
                case ("CB3"):
                    if(read_str.length > 1)
                        jCheckBox3.setSelected(Boolean.parseBoolean(read_str[1]));
                    break;
                case ("CB4"):
                    if(read_str.length > 1)
                        jCheckBox4.setSelected(Boolean.parseBoolean(read_str[1]));
                    break;
                case ("CB5"):
                    if(read_str.length > 1)
                        jCheckBox5.setSelected(Boolean.parseBoolean(read_str[1]));
                    break;
                case ("CB6"):
                    if(read_str.length > 1)
                        jCheckBox6.setSelected(Boolean.parseBoolean(read_str[1]));
                    break;
                case ("CB7"):
                    if(read_str.length > 1)
                        jCheckBox7.setSelected(Boolean.parseBoolean(read_str[1]));
                    break;
                case ("CB8"):
                    if(read_str.length > 1)
                        jCheckBox8.setSelected(Boolean.parseBoolean(read_str[1]));
                    break;
                case ("CB9"):
                    if(read_str.length > 1)
                        jCheckBox9.setSelected(Boolean.parseBoolean(read_str[1]));
                    break;
                case ("CB10"):
                    if(read_str.length > 1)
                        jCheckBox10.setSelected(Boolean.parseBoolean(read_str[1]));
                    break;
                 case ("CB11"):
                    if(read_str.length > 1)
                        jCheckBox11.setSelected(Boolean.parseBoolean(read_str[1]));
                    break;
                 case ("CB12"):
                    if(read_str.length > 1)
                        jCheckBox12.setSelected(Boolean.parseBoolean(read_str[1]));
                    break;
                case ("CB13"):
                    if(read_str.length > 1)
                        jCheckBox13.setSelected(Boolean.parseBoolean(read_str[1]));
                    break;
                 case ("CB14"):
                    if(read_str.length > 1)
                        jCheckBox14.setSelected(Boolean.parseBoolean(read_str[1]));
                    break;
                case ("CB15"):
                    if(read_str.length > 1)
                        jCheckBox15.setSelected(Boolean.parseBoolean(read_str[1]));
                    break;
                case ("CB16"):
                    if(read_str.length > 1)
                        jCheckBox16.setSelected(Boolean.parseBoolean(read_str[1]));
                    break;
                case ("CB17"):
                    if(read_str.length > 1)
                        jCheckBox17.setSelected(Boolean.parseBoolean(read_str[1]));
                    break;
                case ("CB18"):
                    if(read_str.length > 1)
                        jCheckBox18.setSelected(Boolean.parseBoolean(read_str[1]));
                    break;
                case ("CB19"):
                    if(read_str.length > 1)
                        jCheckBox19.setSelected(Boolean.parseBoolean(read_str[1]));
                    break;
                case ("CB20"):
                    if(read_str.length > 1)
                        jCheckBox20.setSelected(Boolean.parseBoolean(read_str[1]));
                    break;
                case ("CB21"):
                    if(read_str.length > 1)
                        jCheckBox21.setSelected(Boolean.parseBoolean(read_str[1]));
                    break;
                case ("CB22"):
                    if(read_str.length > 1)
                        jCheckBox22.setSelected(Boolean.parseBoolean(read_str[1]));
                    break;
               case ("CB23"):
                    if(read_str.length > 1)
                        jCheckBox23.setSelected(Boolean.parseBoolean(read_str[1]));
                    break;
                case ("CB24"):
                    if(read_str.length > 1)
                        jCheckBox24.setSelected(Boolean.parseBoolean(read_str[1]));
                    break;
                case ("CB25"):
                    if(read_str.length > 1)
                        jCheckBox25.setSelected(Boolean.parseBoolean(read_str[1]));
                    break;
                case ("CB26"):
                    if(read_str.length > 1)
                        jCheckBox26.setSelected(Boolean.parseBoolean(read_str[1]));
                    break;
                case ("CB27"):
                    if(read_str.length > 1)
                        jCheckBox27.setSelected(Boolean.parseBoolean(read_str[1]));
                    break;
                case ("CB28"):
                    if(read_str.length > 1)
                        jCheckBox28.setSelected(Boolean.parseBoolean(read_str[1]));
                    break;
                case ("CB29"):
                    if(read_str.length > 1)
                        jCheckBox29.setSelected(Boolean.parseBoolean(read_str[1]));
                    break;
                case ("CB30"):
                    if(read_str.length > 1)
                        jCheckBox30.setSelected(Boolean.parseBoolean(read_str[1]));
                    break;
                    case ("CB31"):
                    if(read_str.length > 1)
                        jCheckBox31.setSelected(Boolean.parseBoolean(read_str[1]));
                    break;
                    case ("CB32"):
                    if(read_str.length > 1)
                        jCheckBox32.setSelected(Boolean.parseBoolean(read_str[1]));
                    break;
                    case ("CB33"):
                    if(read_str.length > 1)
                        jCheckBox33.setSelected(Boolean.parseBoolean(read_str[1]));
                    break;
                    case ("CB34"):
                    if(read_str.length > 1)
                        jCheckBox34.setSelected(Boolean.parseBoolean(read_str[1]));
                    break;
                   case ("CB35"):
                    if(read_str.length > 1)
                        jCheckBox35.setSelected(Boolean.parseBoolean(read_str[1]));
                    break;
                    case ("CB36"):
                    if(read_str.length > 1)
                        jCheckBox36.setSelected(Boolean.parseBoolean(read_str[1]));
                    break;
                    case ("CB37"):
                    if(read_str.length > 1)
                        jCheckBox37.setSelected(Boolean.parseBoolean(read_str[1]));
                    break;
                    case ("CB38"):
                    if(read_str.length > 1)
                        jCheckBox38.setSelected(Boolean.parseBoolean(read_str[1]));
                    break;
                    case ("CB39"):
                    if(read_str.length > 1)
                        jCheckBox39.setSelected(Boolean.parseBoolean(read_str[1]));
                    break;
                    case ("CB40"):
                    if(read_str.length > 1)
                        jCheckBox40.setSelected(Boolean.parseBoolean(read_str[1]));
                    break;
                    case ("CB41"):
                    if(read_str.length > 1)
                        jCheckBox41.setSelected(Boolean.parseBoolean(read_str[1]));
                    break;
                    case ("CB42"):
                    if(read_str.length > 1)
                        jCheckBox42.setSelected(Boolean.parseBoolean(read_str[1]));
                    break;
                    case ("CB43"):
                    if(read_str.length > 1)
                        jCheckBox43.setSelected(Boolean.parseBoolean(read_str[1]));
                    break;
                    case ("CB44"):
                    if(read_str.length > 1)
                        jCheckBox44.setSelected(Boolean.parseBoolean(read_str[1]));
                    break;
                    case ("CB45"):
                    if(read_str.length > 1)
                        jCheckBox45.setSelected(Boolean.parseBoolean(read_str[1]));
                    break;
                    case ("CB46"):
                    if(read_str.length > 1)
                        jCheckBox46.setSelected(Boolean.parseBoolean(read_str[1]));
                    break;
                default:
                   // System.out.println(read_str[1]);
                break;
                    
            }
            }

            }
            scan_conf.close();
            confReader.close();
           
        }
        catch(Exception e){
            System.out.println("ERROR READ CONFIGURE");
            watching.setText("Ошибка загрузки конфиг-файла");
        }
    }
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int returnVal = fileChooser.showOpenDialog(this);
    if (returnVal == fileChooser.APPROVE_OPTION) {
        File file = fileChooser.getSelectedFile();
        jTextField1.setText(""+ file);
    } else {
        System.out.println("File access cancelled by user.");
    }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
                int returnVal = fileChooser.showOpenDialog(this);
    if (returnVal == fileChooser.APPROVE_OPTION) {
        File file = fileChooser.getSelectedFile();
        jTextField2.setText(""+ file);
    } else {
        System.out.println("File access cancelled by user.");
    }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
                        int returnVal = fileChooser.showOpenDialog(this);
    if (returnVal == fileChooser.APPROVE_OPTION) {
        File file = fileChooser.getSelectedFile();
        jTextField3.setText(""+ file);
    } else {
        System.out.println("File access cancelled by user.");
    }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
                       int returnVal = fileChooser.showOpenDialog(this);
    if (returnVal == fileChooser.APPROVE_OPTION) {
        File file = fileChooser.getSelectedFile();
        jTextField4.setText(""+ file);
    } else {
        System.out.println("File access cancelled by user.");
    }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
    int returnVal = fileChooser.showOpenDialog(this);
    if (returnVal == fileChooser.APPROVE_OPTION) {
        File file = fileChooser.getSelectedFile();
        jTextField5.setText(""+ file);
    } else {
        System.out.println("File access cancelled by user.");
    }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
                        int returnVal = fileChooser.showOpenDialog(this);
    if (returnVal == fileChooser.APPROVE_OPTION) {
        File file = fileChooser.getSelectedFile();
        jTextField6.setText(""+ file);
    } else {
        System.out.println("File access cancelled by user.");
    }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
                        int returnVal = fileChooser.showOpenDialog(this);
    if (returnVal == fileChooser.APPROVE_OPTION) {
        File file = fileChooser.getSelectedFile();
        jTextField7.setText(""+ file);
    } else {
        System.out.println("File access cancelled by user.");
    }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void SaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SaveMouseClicked
      //   progress.setMinimum(0);
      //  progress.setMaximum(100);
     // System.out.println("!");
     //   progress.setValue(40);
    }//GEN-LAST:event_SaveMouseClicked

    private void SaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveActionPerformed
        save_config();
       
        
        progress.setValue(100);
    }//GEN-LAST:event_SaveActionPerformed

    private void ExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_ExitActionPerformed

    private void AboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AboutActionPerformed
      //  NewJPanel pan = new NewJPanel();
      // pan.setVisible(true);
      About ab = new About();
      ab.setResizable(false);
      ab.setLocationRelativeTo(null);
      ab.setName("О программе");
      ab.setVisible(true);
    }//GEN-LAST:event_AboutActionPerformed

    private void jCheckBox5PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jCheckBox5PropertyChange
        
    }//GEN-LAST:event_jCheckBox5PropertyChange

    private void jCheckBox5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBox5MouseClicked
        
    }//GEN-LAST:event_jCheckBox5MouseClicked

    private void jCheckBox5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox5ActionPerformed
        jTextField2.setEditable(jCheckBox5.isSelected());
        jButton3.setEnabled(jCheckBox5.isSelected());
        jCheckBox28.setEnabled(jCheckBox5.isSelected());
        jCheckBox8.setEnabled(jCheckBox5.isSelected());
        
    }//GEN-LAST:event_jCheckBox5ActionPerformed

    private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox2ActionPerformed
        jTextField3.setEditable(jCheckBox2.isSelected());
        jButton4.setEnabled(jCheckBox2.isSelected());
        jCheckBox29.setEnabled(jCheckBox2.isSelected());
        jCheckBox9.setEnabled(jCheckBox2.isSelected());
    }//GEN-LAST:event_jCheckBox2ActionPerformed

    private void jCheckBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox3ActionPerformed
        jTextField4.setEditable(jCheckBox3.isSelected());
        jButton5.setEnabled(jCheckBox3.isSelected());
        jCheckBox30.setEnabled(jCheckBox3.isSelected());
        jCheckBox10.setEnabled(jCheckBox3.isSelected());
    }//GEN-LAST:event_jCheckBox3ActionPerformed

    private void jCheckBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox4ActionPerformed
        jTextField5.setEditable(jCheckBox4.isSelected());
        jButton6.setEnabled(jCheckBox4.isSelected());
        jCheckBox31.setEnabled(jCheckBox4.isSelected());
        jCheckBox11.setEnabled(jCheckBox4.isSelected());
    }//GEN-LAST:event_jCheckBox4ActionPerformed

    private void jCheckBox6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox6ActionPerformed
        jTextField6.setEditable(jCheckBox6.isSelected());
        jButton7.setEnabled(jCheckBox6.isSelected());
        jCheckBox32.setEnabled(jCheckBox6.isSelected());
        jCheckBox12.setEnabled(jCheckBox6.isSelected());
    }//GEN-LAST:event_jCheckBox6ActionPerformed

    private void jCheckBox7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox7ActionPerformed
        jTextField7.setEditable(jCheckBox7.isSelected());
        jButton8.setEnabled(jCheckBox7.isSelected());
        jCheckBox33.setEnabled(jCheckBox7.isSelected());
        jCheckBox13.setEnabled(jCheckBox7.isSelected());
    }//GEN-LAST:event_jCheckBox7ActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        jTextField20.setEditable(jCheckBox1.isSelected());
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jTextField1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField1FocusLost
        JOptionPane.showMessageDialog(null, "Ты что, пьян? :D");
    }//GEN-LAST:event_jTextField1FocusLost

    private void jTextField20FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField20FocusLost
        if (jTextField20.getText().equalsIgnoreCase("IDDQD")){
             
            jTextField20.setText("");
            jTextField20.setEditable(false);
            jCheckBox1.setSelected(false);
       NewJFrame12 specials = new NewJFrame12();
       specials.setResizable(false);
       specials.setLocationRelativeTo(null);
       specials.setTitle("Специальные настройки! Менять с умом");
       specials.setVisible(true);
        }
    }//GEN-LAST:event_jTextField20FocusLost

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewJFrame().setVisible(true);
            }
        });
    }

    public void edit_downfloor(){
        int inst = 0;           //переменная которая хранит номер элемента инстанс
        if (jCheckBox5.isSelected()){
            for (int i = 0; i < CountM[1];i++){
                if (mnemos1[i].getName().matches(podval)){
                    watching.setText("Редактируем подвал " + mnemos1[i].getName());
                    textlog[indexlog++] = "Редактируем подвал " + mnemos1[i].getName();
                    try {
                        
                DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                    System.out.println(mnemos1[i].getName());
                   Document document = documentBuilder.parse(mnemos1[i]);
                 // Получаем корневой элемент - Нулевой уровень
                   Node schem = document.getDocumentElement();
                 // получаем все элементы на мнемосхеме - первый уровень
                   NodeList elements1 = schem.getChildNodes();
                     for (int j = 0; j< elements1.getLength(); j++)
                   {
                       // если нода не текст
                       if (elements1.item(j).getNodeType() != Node.TEXT_NODE){
                           //Если нода грид и тип её буттон
                           if (elements1.item(j).getNodeName().equalsIgnoreCase("grid") && 
                                   elements1.item(j).getAttributes().getNamedItem("type").getNodeValue().equalsIgnoreCase("button")){
                             //  System.out.println(elements1.item(j).getNodeName());
                            //   System.out.println(elements1.item(j).getAttributes().getNamedItem("type").getTextContent());
                               
                               NodeList elements2 = elements1.item(j).getChildNodes();
                               for (int k=0; k< elements2.getLength();k++){
                                   if (elements2.item(k).getNodeType() != Node.TEXT_NODE){
                                       if (elements2.item(k).getNodeName().equalsIgnoreCase("comment") &&
                                               elements2.item(k).getAttributes().getNamedItem("value").getNodeValue().equalsIgnoreCase(btm)){
                                           //получаем номер ноды инстанс
                                           inst = get_inst(elements1.item(j));
                                           NodeList elements3 = elements2.item(inst).getChildNodes();
                                           inst = 0;
                                           for (int l=0;l < elements3.getLength();l++){
                                               if (elements3.item(l).getNodeType() != Node.TEXT_NODE){
                                                   if (elements3.item(l).getNodeName().equalsIgnoreCase("schemetojump")){
                                                       String s1 = elements3.item(l).getAttributes().getNamedItem("value").getNodeValue();
                                                      // System.out.println(s1);
                                                      elements3.item(l).getAttributes().getNamedItem("value").setTextContent(preff+s1);
                                                   }
                                               }
                                           }
                                       }
                                   }
                               }
                               
                           }
                       }
                   }
                   Transformer transformer = TransformerFactory.newInstance().newTransformer();
                   document.normalizeDocument();
                   DOMSource source = new DOMSource(document);
                   StreamResult result  = new StreamResult(mnemos1[i]);
                   transformer.transform(source, result);
                   textlog[indexlog++] = "Для 1 проекта ok";
                    }
                    catch(Exception e){
                        textlog[indexlog++] = "Для 1 проекта ошибка";
                        textlog[indexlog++] = "" + e.getMessage();
                        e.printStackTrace();
                    }
                 //   System.out.println("Мнемосхема подвала "+mnemos1[i].getName());
                }
            }
        }
        
        // 2 схема
        if (jCheckBox2.isSelected()){
            for (int i = 0; i < CountM[2];i++){
                if (mnemos2[i].getName().matches(podval)){
                    watching.setText("Редактируем подвал " + mnemos2[i].getName());
                    textlog[indexlog++] = "Редактируем подвал " + mnemos2[i].getName();
                    try {
                        
                DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                   
                   Document document = documentBuilder.parse(mnemos2[i]);
                 // Получаем корневой элемент - Нулевой уровень
                   Node schem = document.getDocumentElement();
                 // получаем все элементы на мнемосхеме - первый уровень
                   NodeList elements1 = schem.getChildNodes();
                     for (int j = 0; j< elements1.getLength(); j++)
                   {
                       // если нода не текст
                       if (elements1.item(j).getNodeType() != Node.TEXT_NODE){
                           //Если нода грид и тип её буттон
                           if (elements1.item(j).getNodeName().equalsIgnoreCase("grid") && 
                                   elements1.item(j).getAttributes().getNamedItem("type").getNodeValue().equalsIgnoreCase("button")){

                               NodeList elements2 = elements1.item(j).getChildNodes();
                               for (int k=0; k< elements2.getLength();k++){
                                   if (elements2.item(k).getNodeType() != Node.TEXT_NODE){
                                       if (elements2.item(k).getNodeName().equalsIgnoreCase("comment") &&
                                               elements2.item(k).getAttributes().getNamedItem("value").getNodeValue().equalsIgnoreCase(btm)){
                                           //получаем номер ноды инстанс
                                           inst = get_inst(elements1.item(j));
                                           NodeList elements3 = elements2.item(inst).getChildNodes();
                                           inst = 0;
                                           for (int l=0;l < elements3.getLength();l++){
                                               if (elements3.item(l).getNodeType() != Node.TEXT_NODE){
                                                   if (elements3.item(l).getNodeName().equalsIgnoreCase("schemetojump")){
                                                       String s1 = elements3.item(l).getAttributes().getNamedItem("value").getNodeValue();
                                                      // System.out.println(s1);
                                                      elements3.item(l).getAttributes().getNamedItem("value").setTextContent(preff+s1);
                                                   }
                                               }
                                           }
                                       }
                                   }
                               }
                               
                           }
                       }
                   }
                   Transformer transformer = TransformerFactory.newInstance().newTransformer();
                   document.normalizeDocument();
                   DOMSource source = new DOMSource(document);
                   StreamResult result  = new StreamResult(mnemos2[i]);
                   transformer.transform(source, result);
                    textlog[indexlog++] = "Для 2 проекта ok";
                    }
                    catch(Exception e){
                        textlog[indexlog++] = "Для 2 проекта ошибка";
                        textlog[indexlog++] = "" + e.getMessage();
                        e.printStackTrace();
                    }
                 //   System.out.println("Мнемосхема подвала "+mnemos1[i].getName());
                }
            }
        }
        
                //3 файл
                if (jCheckBox3.isSelected()){
            for (int i = 0; i < CountM[3];i++){
                if (mnemos3[i].getName().matches(podval)){
                    watching.setText("Редактируем подвал " + mnemos3[i].getName());
                    textlog[indexlog++] = "Редактируем подвал " + mnemos3[i].getName();
                    try {
                        
                DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                   
                   Document document = documentBuilder.parse(mnemos3[i]);
                 // Получаем корневой элемент - Нулевой уровень
                   Node schem = document.getDocumentElement();
                 // получаем все элементы на мнемосхеме - первый уровень
                   NodeList elements1 = schem.getChildNodes();
                     for (int j = 0; j< elements1.getLength(); j++)
                   {
                       // если нода не текст
                       if (elements1.item(j).getNodeType() != Node.TEXT_NODE){
                           //Если нода грид и тип её буттон
                           if (elements1.item(j).getNodeName().equalsIgnoreCase("grid") && 
                                   elements1.item(j).getAttributes().getNamedItem("type").getNodeValue().equalsIgnoreCase("button")){

                               NodeList elements2 = elements1.item(j).getChildNodes();
                               for (int k=0; k< elements2.getLength();k++){
                                   if (elements2.item(k).getNodeType() != Node.TEXT_NODE){
                                       if (elements2.item(k).getNodeName().equalsIgnoreCase("comment") &&
                                               elements2.item(k).getAttributes().getNamedItem("value").getNodeValue().equalsIgnoreCase(btm)){
                                           //получаем номер ноды инстанс
                                           inst = get_inst(elements1.item(j));
                                           NodeList elements3 = elements2.item(inst).getChildNodes();
                                           inst = 0;
                                           for (int l=0;l < elements3.getLength();l++){
                                               if (elements3.item(l).getNodeType() != Node.TEXT_NODE){
                                                   if (elements3.item(l).getNodeName().equalsIgnoreCase("schemetojump")){
                                                       String s1 = elements3.item(l).getAttributes().getNamedItem("value").getNodeValue();
                                                      // System.out.println(s1);
                                                      elements3.item(l).getAttributes().getNamedItem("value").setTextContent(preff+s1);
                                                   }
                                               }
                                           }
                                       }
                                   }
                               }
                               
                           }
                       }
                   }
                   Transformer transformer = TransformerFactory.newInstance().newTransformer();
                   document.normalizeDocument();
                   DOMSource source = new DOMSource(document);
                   StreamResult result  = new StreamResult(mnemos3[i]);
                   transformer.transform(source, result);
                   textlog[indexlog++] = "Для 3 проекта ok";
                    }
                    catch(Exception e){
                        textlog[indexlog++] = "Для 3 проекта ошибка";
                        textlog[indexlog++] = e.getMessage();
                        e.printStackTrace();
                    }
                 //   System.out.println("Мнемосхема подвала "+mnemos1[i].getName());
                }
            }
        }
                // 4 file
                if (jCheckBox4.isSelected()){
            for (int i = 0; i < CountM[4];i++){
                if (mnemos4[i].getName().matches(podval)){
                    watching.setText("Редактируем подвал " + mnemos4[i].getName());
                    textlog[indexlog++] = "Редактируем подвал " + mnemos4[i].getName();
                    try {
                        
                DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                  
                   Document document = documentBuilder.parse(mnemos4[i]);
                 // Получаем корневой элемент - Нулевой уровень
                   Node schem = document.getDocumentElement();
                 // получаем все элементы на мнемосхеме - первый уровень
                   NodeList elements1 = schem.getChildNodes();
                     for (int j = 0; j< elements1.getLength(); j++)
                   {
                       // если нода не текст
                       if (elements1.item(j).getNodeType() != Node.TEXT_NODE){
                           //Если нода грид и тип её буттон
                           if (elements1.item(j).getNodeName().equalsIgnoreCase("grid") && 
                                   elements1.item(j).getAttributes().getNamedItem("type").getNodeValue().equalsIgnoreCase("button")){
                               NodeList elements2 = elements1.item(j).getChildNodes();
                               for (int k=0; k< elements2.getLength();k++){
                                   if (elements2.item(k).getNodeType() != Node.TEXT_NODE){
                                       if (elements2.item(k).getNodeName().equalsIgnoreCase("comment") &&
                                               elements2.item(k).getAttributes().getNamedItem("value").getNodeValue().equalsIgnoreCase(btm)){
                                           //получаем номер ноды инстанс
                                           inst = get_inst(elements1.item(j));
                                           NodeList elements3 = elements2.item(inst).getChildNodes();
                                           inst = 0;
                                           for (int l=0;l < elements3.getLength();l++){
                                               if (elements3.item(l).getNodeType() != Node.TEXT_NODE){
                                                   if (elements3.item(l).getNodeName().equalsIgnoreCase("schemetojump")){
                                                       String s1 = elements3.item(l).getAttributes().getNamedItem("value").getNodeValue();
                                                      // System.out.println(s1);
                                                      elements3.item(l).getAttributes().getNamedItem("value").setTextContent(preff+s1);
                                                   }
                                               }
                                           }
                                       }
                                   }
                               }
                               
                           }
                       }
                   }
                   Transformer transformer = TransformerFactory.newInstance().newTransformer();
                   document.normalizeDocument();
                   DOMSource source = new DOMSource(document);
                   StreamResult result  = new StreamResult(mnemos4[i]);
                   transformer.transform(source, result);
                   textlog[indexlog++] = "Для 4 проекта ok";
                    }
                    catch(Exception e){
                        textlog[indexlog++] = "Для 4 проекта ошибка";
                        textlog[indexlog++] = e.getMessage();
                        e.printStackTrace();
                    }
                 //   System.out.println("Мнемосхема подвала "+mnemos1[i].getName());
                }
            }
        }
                // 5 file
            if (jCheckBox6.isSelected()){
            for (int i = 0; i < CountM[5];i++){
                if (mnemos5[i].getName().matches(podval)){
                    watching.setText("Редактируем подвал " + mnemos5[i].getName());
                    textlog[indexlog++] = "Редактируем подвал " + mnemos5[i].getName();
                    try {
                        
                DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                  
                   Document document = documentBuilder.parse(mnemos5[i]);
                 // Получаем корневой элемент - Нулевой уровень
                   Node schem = document.getDocumentElement();
                 // получаем все элементы на мнемосхеме - первый уровень
                   NodeList elements1 = schem.getChildNodes();
                     for (int j = 0; j< elements1.getLength(); j++)
                   {
                       // если нода не текст
                       if (elements1.item(j).getNodeType() != Node.TEXT_NODE){
                           //Если нода грид и тип её буттон
                           if (elements1.item(j).getNodeName().equalsIgnoreCase("grid") && 
                                   elements1.item(j).getAttributes().getNamedItem("type").getNodeValue().equalsIgnoreCase("button")){
                               NodeList elements2 = elements1.item(j).getChildNodes();
                               for (int k=0; k< elements2.getLength();k++){
                                   if (elements2.item(k).getNodeType() != Node.TEXT_NODE){
                                       if (elements2.item(k).getNodeName().equalsIgnoreCase("comment") &&
                                               elements2.item(k).getAttributes().getNamedItem("value").getNodeValue().equalsIgnoreCase(btm)){
                                           //получаем номер ноды инстанс
                                           inst = get_inst(elements1.item(j));
                                           NodeList elements3 = elements2.item(inst).getChildNodes();
                                           inst = 0;
                                           for (int l=0;l < elements3.getLength();l++){
                                               if (elements3.item(l).getNodeType() != Node.TEXT_NODE){
                                                   if (elements3.item(l).getNodeName().equalsIgnoreCase("schemetojump")){
                                                       String s1 = elements3.item(l).getAttributes().getNamedItem("value").getNodeValue();
                                                      // System.out.println(s1);
                                                      elements3.item(l).getAttributes().getNamedItem("value").setTextContent(preff+s1);
                                                   }
                                               }
                                           }
                                       }
                                   }
                               }
                               
                           }
                       }
                   }
                   Transformer transformer = TransformerFactory.newInstance().newTransformer();
                   document.normalizeDocument();
                   DOMSource source = new DOMSource(document);
                   StreamResult result  = new StreamResult(mnemos5[i]);
                   transformer.transform(source, result);
                    textlog[indexlog++] = "Для 5 проекта ok";
                    }
                    catch(Exception e){
                        textlog[indexlog++] = "Для 5 проекта error";
                        textlog[indexlog++] = e.getMessage();
                        e.printStackTrace();
                    }
                 //   System.out.println("Мнемосхема подвала "+mnemos1[i].getName());
                }
            }
        }
                // 6 file
                if (jCheckBox7.isSelected()){
            for (int i = 0; i < CountM[6];i++){
                if (mnemos6[i].getName().matches(podval)){
                    watching.setText("Редактируем подвал " + mnemos6[i].getName());
                    textlog[indexlog++] = "Редактируем подвал " + mnemos6[i].getName();
                    try {
                        
                DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                  //  System.out.println(mnemos6[i].getName());
                   Document document = documentBuilder.parse(mnemos6[i]);
                 // Получаем корневой элемент - Нулевой уровень
                   Node schem = document.getDocumentElement();
                 // получаем все элементы на мнемосхеме - первый уровень
                   NodeList elements1 = schem.getChildNodes();
                     for (int j = 0; j< elements1.getLength(); j++)
                   {
                       // если нода не текст
                       if (elements1.item(j).getNodeType() != Node.TEXT_NODE){
                           //Если нода грид и тип её буттон
                           if (elements1.item(j).getNodeName().equalsIgnoreCase("grid") && 
                                   elements1.item(j).getAttributes().getNamedItem("type").getNodeValue().equalsIgnoreCase("button")){
                               NodeList elements2 = elements1.item(j).getChildNodes();
                               for (int k=0; k< elements2.getLength();k++){
                                   if (elements2.item(k).getNodeType() != Node.TEXT_NODE){
                                       if (elements2.item(k).getNodeName().equalsIgnoreCase("comment") &&
                                               elements2.item(k).getAttributes().getNamedItem("value").getNodeValue().equalsIgnoreCase(btm)){
                                           //получаем номер ноды инстанс
                                           inst = get_inst(elements1.item(j));
                                           NodeList elements3 = elements2.item(inst).getChildNodes();
                                           inst = 0;
                                           for (int l=0;l < elements3.getLength();l++){
                                               if (elements3.item(l).getNodeType() != Node.TEXT_NODE){
                                                   if (elements3.item(l).getNodeName().equalsIgnoreCase("schemetojump")){
                                                       String s1 = elements3.item(l).getAttributes().getNamedItem("value").getNodeValue();
                                                      // System.out.println(s1);
                                                      elements3.item(l).getAttributes().getNamedItem("value").setTextContent(preff+s1);
                                                   }
                                               }
                                           }
                                       }
                                   }
                               }
                               
                           }
                       }
                   }
                   Transformer transformer = TransformerFactory.newInstance().newTransformer();
                   document.normalizeDocument();
                   DOMSource source = new DOMSource(document);
                   StreamResult result  = new StreamResult(mnemos6[i]);
                   transformer.transform(source, result);
                    textlog[indexlog++] = "Для 6 проекта ok";
                    }
                    catch(Exception e){
                        textlog[indexlog++] = "Для 6 проекта error";
                        textlog[indexlog++] = e.getMessage();
                        e.printStackTrace();
                    }
                 //   System.out.println("Мнемосхема подвала "+mnemos1[i].getName());
                }
            }
        }
                //
    }
    
    public static int get_inst(Node nd1){
        int res = 0;
        NodeList list = nd1.getChildNodes();
        for(int w=0; w< list.getLength();w++){
            if (list.item(w).getNodeType() != Node.TEXT_NODE){
                if (list.item(w).getNodeName().equalsIgnoreCase("instance")){
                    res = w;
                }
            }
        }
        return res;
    }
    
    public boolean check(File dir, int UPP){
        
        File[] FilesInDir2 = dir.listFiles(new FilenameFilter() 
            {
                 public boolean accept(File dir, String name)
                 {
                     return (name.endsWith("xms") && !name.startsWith("УПП"));
                    // return name.endsWith("xms");
                 }
            });
        if (FilesInDir2.length == 0){
            return false;
        }
        else{
            fillArr (FilesInDir2, UPP);
        return true;
        }

    }
    public void fillArr(File[] files, int UPP){
       
        
       switch (UPP){
           case (1):
                     System.arraycopy(files, 0, mnemos1, 0, files.length);
              break;
           case (2):
                     System.arraycopy(files, 0, mnemos2, 0, files.length);
              break;
           case (3):
                     System.arraycopy(files, 0, mnemos3, 0, files.length);
              break;
           case (4):
                     System.arraycopy(files, 0, mnemos4, 0, files.length);
              break;
           case (5):
                     System.arraycopy(files, 0, mnemos5, 0, files.length);
              break;
           case (6):
                    System.arraycopy(files, 0, mnemos6, 0, files.length);
                    break;
        }
    }
    
    public void addLog(){
           try{
               FileWriter lf = new FileWriter(jTextField20.getText()+ logfile, false);
            for (int i=0; i<textlog.length; i++){
                if (textlog[i] != null){
                lf.write(""+textlog[i]+line_separator);
                lf.flush();
                }
            }
            
           }
            catch (IOException ex){System.out.println(ex.getMessage());
            }
    }
    
    public Node create_JLOOK(Document doc){
       // Получим корневую ноду документа
      // Node root = doc.getDocumentElement();
       //создаем ноду
       //1. Её название grid type=label
      Element label = doc.createElement("grid");
      label.setAttribute("type", "label");
      //2. pos_x
      Element pos_x = doc.createElement("pos_x");
      pos_x.setAttribute("value", "125");
      //3. pos_y
      Element pos_y = doc.createElement("pos_y");
      pos_y.setAttribute("value", "200");
      //Для прикола
      Element links = doc.createElement("link");
      //4. instance
      Element instance = doc.createElement("instance");
      //4.1 элементы инстанс, color1
      Element color = doc.createElement("color1");
     // color.setAttribute("value", "43520"); "00043520-85-300"
     
              color.setAttribute("value", "" + Integer.parseInt(watermark.substring(0, watermark.indexOf("-", 0))));
              System.out.println(watermark.substring(0, watermark.indexOf("-", 0)));
              System.out.println(watermark.substring(watermark.indexOf("-", 0)+1, watermark.indexOf("-", watermark.indexOf("-", 0)+1)));
              System.out.println(watermark.substring(watermark.indexOf("-", watermark.indexOf("-", 0)+1)+1));
      //4.2 элементы инстанс, transparency1
      Element transparency = doc.createElement("transparency1");
     // transparency.setAttribute("value", "85");
      transparency.setAttribute("value", ""+ Integer.parseInt(watermark.substring(watermark.indexOf("-", 0)+1, watermark.indexOf("-", watermark.indexOf("-", 0)+1))));
      //4.3 элементы инстанс, angle
      Element angle = doc.createElement("angle");
      angle.setAttribute("value", "30");
      //4.4 элементы инстанс, fontsize
      Element fontsize = doc.createElement("fontsize");
     // fontsize.setAttribute("value", "300");
      fontsize.setAttribute("value", ""+ Integer.parseInt(watermark.substring(watermark.indexOf("-", watermark.indexOf("-", 0)+1)+1)));
      //4.5 элементы инстанс, text
      Element text = doc.createElement("text");
      text.setAttribute("value", justlook);
      //собираем ноду instance
      instance.appendChild(color);
      instance.appendChild(transparency);
      instance.appendChild(angle);
      instance.appendChild(fontsize);
      instance.appendChild(text);
      //собираем ноду grid
      label.appendChild(pos_x);
      label.appendChild(pos_y);
      label.appendChild(instance);
      label.appendChild(links);
     // root.appendChild(label);
     return label;
    }
    
    public void get_over_here(){
              // выполнить
              
              // Если стоит галка добавить преффикс
              if (jCheckBox26.isSelected()){
                 // preff = "УПП_";
                 preff = preff2;
              }
              else{
                  preff = "";
              }
                  
              // подготовим массив для лог файла
                progress.setValue(0);
               
              if (jCheckBox1.isSelected()){
                   for (int i = 0; i < 1000; i++){
                        textlog[i] = "";
                      }
              }
            //Првоерим директорию для первого проекта
               progress.setValue(9);
               textlog[indexlog++] = "Выполняем поиск мнемосхем в указанных директориях...";
        if (jCheckBox5.isSelected() ){
            //проверка каталога UPP_P
            File dir1 = new File(jTextField2.getText());
           if (check(dir1, 1)){
               feel_countM(1, mnemos1);
               if (CountM[1] <=2 && ! ( jCheckBox8.isSelected())){
                   textlog[indexlog++] = indexlog+ "В директории 1 " +jTextField2.getText()+" нет мнемосхем кроме base и звуки\n";
                   watching.setText(indexlog+ "В директории 1 " +jTextField2.getText()+" нет мнемосхем кроме base и звуки)");
                   
               }
               // Обновляем палетку
               if (jCheckBox28.isSelected()){
                   update_Palette(jTextField2.getText()+"\\"+"palette\\palette.xmp", 
                           jTextField1.getText()+"\\"+"palette\\palette.xmp", PLT_UPP_P);
                   
               }
           
           }
           else {
               textlog[indexlog++] =indexlog+ ". В директории 1 " +jTextField2.getText()+" нет мнемосхем ВАЩЕ\n";
               watching.setText(indexlog+ ". В директории 1 " +jTextField2.getText()+" нет мнемосхем ВАЩЕ\n");
           }
        }
            //Проверим директорию для второго проекта
            progress.setValue(18);
                   if (jCheckBox2.isSelected() ){
            //проверка каталога UPP_E
            File dir2 = new File(jTextField3.getText());
           if (check(dir2, 2)){
               feel_countM(2, mnemos2);
               if (CountM[2] <=2 && ! ( jCheckBox9.isSelected())){
                   textlog[indexlog++] = indexlog+ "В директории 2 " +jTextField3.getText()+" нет мнемосхем кроме base и звуки\n";
                   watching.setText(indexlog+ "В директории 2 " +jTextField3.getText()+" нет мнемосхем кроме base и звуки\n");
               }
               // Обновляем палетку
           if (jCheckBox29.isSelected()){
                   update_Palette(jTextField3.getText()+"\\"+"palette\\palette.xmp", 
                           jTextField1.getText()+"\\"+"palette\\palette.xmp", PLT_UPP_E);
                   
               }
           }
           else {
               textlog[indexlog++] =indexlog+ ". В директории 2 " +jTextField3.getText()+" нет мнемосхем ВАЩЕ\n";
               watching.setText(indexlog+ ". В директории 2 " +jTextField3.getText()+" нет мнемосхем ВАЩЕ\n");
           }
        }
                   progress.setValue(27);
        //Проверим директорию для третьего проекта
                   if (jCheckBox3.isSelected() ){
            //проверка каталога UPP_G
            File dir3 = new File(jTextField4.getText());
           if (check(dir3, 3)){
               feel_countM(3, mnemos3);
               if (CountM[3] <=2 && ! ( jCheckBox10.isSelected())){
                   textlog[indexlog++] = indexlog+ "В директории 3 " +jTextField4.getText()+" нет мнемосхем кроме base и звуки\n";
                   watching.setText(indexlog+ "В директории 3 " +jTextField4.getText()+" нет мнемосхем кроме base и звуки\n");
               }
               // Обновляем палетку
            if (jCheckBox30.isSelected()){
                   update_Palette(jTextField4.getText()+"\\"+"palette\\palette.xmp", 
                           jTextField1.getText()+"\\"+"palette\\palette.xmp", PLT_UPP_G);
                   
               }
           }
           else {
               textlog[indexlog++] =indexlog+ ". В директории 3 " +jTextField4.getText()+" нет мнемосхем ВАЩЕ\n";
               watching.setText(indexlog+ ". В директории 3 " +jTextField4.getText()+" нет мнемосхем ВАЩЕ\n");
           }
        }
                   progress.setValue(36);
          //Проверим директорию для четвертого проекта
                   if (jCheckBox4.isSelected() ){
            //проверка каталога UPP_S
            File dir4 = new File(jTextField5.getText());
           if (check(dir4, 4)){
               feel_countM(4, mnemos4);
               if (CountM[4] <=2 && ! ( jCheckBox11.isSelected())){
                   textlog[indexlog++] = indexlog+ "В директории 4 " +jTextField5.getText()+" нет мнемосхем кроме base и звуки\n";
                   watching.setText( indexlog+ "В директории 4 " +jTextField5.getText()+" нет мнемосхем кроме base и звуки\n");
               }
               // Обновляем палетку
           if (jCheckBox31.isSelected()){
                   update_Palette(jTextField5.getText()+"\\"+"palette\\palette.xmp", 
                           jTextField1.getText()+"\\"+"palette\\palette.xmp", PLT_UPP_S);
                   
               }
           }
           else {
               textlog[indexlog++] =indexlog+ ". В директории 4 " +jTextField5.getText()+" нет мнемосхем ВАЩЕ\n";
               watching.setText(indexlog+ ". В директории 4 " +jTextField5.getText()+" нет мнемосхем ВАЩЕ\n");
           }
        }
                   progress.setValue(45);
          //Проверим директорию для пятого проекта
                   if (jCheckBox6.isSelected() ){
            //проверка каталога UPP_A
            File dir5 = new File(jTextField6.getText());
           if (check(dir5, 5)){
               feel_countM(5, mnemos5);
               if (CountM[5] <=2 && ! ( jCheckBox12.isSelected())){
                   textlog[indexlog++] = indexlog+ "В директории 5 " +jTextField6.getText()+" нет мнемосхем кроме base и звуки";
                   watching.setText(indexlog+ "В директории 5 " +jTextField6.getText()+" нет мнемосхем кроме base и звуки");
               }
               // Обновляем палетку
           if (jCheckBox32.isSelected()){
                   update_Palette(jTextField6.getText()+"\\"+"palette\\palette.xmp", 
                           jTextField1.getText()+"\\"+"palette\\palette.xmp", PLT_UPP_A);
                   
               }
           }
           else {
               textlog[indexlog++] =indexlog+ ". В директории 5 " +jTextField6.getText()+" нет мнемосхем ВАЩЕ";
               watching.setText(indexlog+ ". В директории 5 " +jTextField6.getText()+" нет мнемосхем ВАЩЕ");
           }
        }
                   progress.setValue(54);
         //Проверим директорию для шестого проекта
           if (jCheckBox7.isSelected() ){
            //проверка каталога UPP_M
            File dir6 = new File(jTextField7.getText());
           if (check(dir6, 6)){
               feel_countM(6, mnemos6);
               if (CountM[6] <=2 && ! ( jCheckBox13.isSelected())){
                   textlog[indexlog++] = indexlog+ "В директории 6 " +jTextField7.getText()+" нет мнемосхем кроме base и звуки";
               }
               // Обновляем палетку
           if (jCheckBox33.isSelected()){
                   update_Palette(jTextField7.getText()+"\\"+"palette\\palette.xmp", 
                           jTextField1.getText()+"\\"+"palette\\palette.xmp", PLT_UPP_M);
                   
               }
           }
           else {
               textlog[indexlog++] =indexlog+ ". В директории 6 " +jTextField7.getText()+" нет мнемосхем ВАЩЕ";
           }
        }
            progress.setValue(63);
            //удалим base и звуки там где не стоит галка
           clear_mnemos();
         //   System.out.println("Checksum "+ mnemos1[0].getName()+"до = "+get_checksum(mnemos1[0]));
         //textlog[indexlog++] = "Ищем файлы подложек найденных мнемосхем...";
           //разбор полученных схем XML + копирование подложек
          read_xml();
           //скопируем сами мнемосхемы
           progress.setValue(72);
           textlog[indexlog++] = "выполняем копирование...";
           copy_schemes();
           textlog[indexlog++] = "ok!";
           progress.setValue(81);
           //конвертируем массивы мнемосхем для дальнейшей работы
         //  convert_arrays();
                     // Реверс массивов баз перед сдвигом
          revers_mnemos();
         
          //редактируем мнемосхемы только если стоит галка+edit
          if (jCheckBox27.isSelected()){
              textlog[indexlog++] = "сдвиг привязок...";
                //Вскрываем скопированные схемы и редактируем из новых массивов

               progress.setValue(90);

               //Сдвиг БАЗ
               edit_base();
             //  System.out.println("Checksum "+ mnemos1[0].getName()+"после edit = "+get_checksum(mnemos1[0]));
             textlog[indexlog++] = "редактируем подвал...";
               //Редактируем подвал!!!!
               progress.setValue(91);
               edit_downfloor();
               //корректировка контргольных сумм мнемосхем
               progress.setValue(92);
             //  do_checksum();
             
          }
        //  System.out.println("Checksum "+ mnemos1[0].getName()+"после editchecksum = "+get_checksum(mnemos1[0]));
               // создадим лог файл
               progress.setValue(95);
       if (jCheckBox1.isSelected()){
            addLog();
        }
       //сброс массивов
       watching.setText("Очистка временных переменных");
       reset_arrays();
       watching.setText("Ок!");
       progress.setValue(100);

    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem About;
    private javax.swing.JMenuItem Exit;
    private javax.swing.JMenuItem Open;
    private javax.swing.JFormattedTextField PCAA;
    private javax.swing.JFormattedTextField PCAD;
    private javax.swing.JFormattedTextField PCEA;
    private javax.swing.JFormattedTextField PCED;
    private javax.swing.JFormattedTextField PCGA;
    private javax.swing.JFormattedTextField PCGD;
    private javax.swing.JFormattedTextField PCMA;
    private javax.swing.JFormattedTextField PCMD;
    private javax.swing.JFormattedTextField PCPA;
    private javax.swing.JFormattedTextField PCPD;
    private javax.swing.JFormattedTextField PCSA;
    private javax.swing.JFormattedTextField PCSD;
    private javax.swing.JMenuItem Save;
    private javax.swing.JLabel comm;
    private javax.swing.JFileChooser fileChooser;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox10;
    private javax.swing.JCheckBox jCheckBox11;
    private javax.swing.JCheckBox jCheckBox12;
    private javax.swing.JCheckBox jCheckBox13;
    private javax.swing.JCheckBox jCheckBox14;
    private javax.swing.JCheckBox jCheckBox15;
    private javax.swing.JCheckBox jCheckBox16;
    private javax.swing.JCheckBox jCheckBox17;
    private javax.swing.JCheckBox jCheckBox18;
    private javax.swing.JCheckBox jCheckBox19;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox20;
    private javax.swing.JCheckBox jCheckBox21;
    private javax.swing.JCheckBox jCheckBox22;
    private javax.swing.JCheckBox jCheckBox23;
    private javax.swing.JCheckBox jCheckBox24;
    private javax.swing.JCheckBox jCheckBox25;
    private javax.swing.JCheckBox jCheckBox26;
    private javax.swing.JCheckBox jCheckBox27;
    private javax.swing.JCheckBox jCheckBox28;
    private javax.swing.JCheckBox jCheckBox29;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox30;
    private javax.swing.JCheckBox jCheckBox31;
    private javax.swing.JCheckBox jCheckBox32;
    private javax.swing.JCheckBox jCheckBox33;
    private javax.swing.JCheckBox jCheckBox34;
    private javax.swing.JCheckBox jCheckBox35;
    private javax.swing.JCheckBox jCheckBox36;
    private javax.swing.JCheckBox jCheckBox37;
    private javax.swing.JCheckBox jCheckBox38;
    private javax.swing.JCheckBox jCheckBox39;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JCheckBox jCheckBox40;
    private javax.swing.JCheckBox jCheckBox41;
    private javax.swing.JCheckBox jCheckBox42;
    private javax.swing.JCheckBox jCheckBox43;
    private javax.swing.JCheckBox jCheckBox44;
    private javax.swing.JCheckBox jCheckBox45;
    private javax.swing.JCheckBox jCheckBox46;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JCheckBox jCheckBox6;
    private javax.swing.JCheckBox jCheckBox7;
    private javax.swing.JCheckBox jCheckBox8;
    private javax.swing.JCheckBox jCheckBox9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField20;
    private javax.swing.JTextField jTextField21;
    private javax.swing.JTextField jTextField22;
    private javax.swing.JTextField jTextField23;
    private javax.swing.JTextField jTextField24;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JProgressBar progress;
    private javax.swing.JLabel watching;
    // End of variables declaration//GEN-END:variables
    public class myThread extends Thread{
        // конструктор потока
        myThread(){
            super("test");
            start();
        }
        //Метож run
        public void run(){
            get_over_here();
        }
    }
    
   public void update_Palette(String from1, String to2, String key_p){
     //  Node[] list_nodes;
     
       File palette_from = new File(from1);
       File palette_to = new File (to2);
       //проверяем существует ли палетка куда сахронять
       if (palette_to.exists()){
           //Существует. вскрываем очищае и готовимся добавлять новое.
           try{
               DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
               Document document = documentBuilder.parse(palette_to);
               Node palette_d = document.getDocumentElement();
               NodeList elements1 = palette_d.getChildNodes();
               for (int i = 0; i<elements1.getLength(); i++)
                   {
                       if (elements1.item(i).getNodeType() != Node.TEXT_NODE){
                           //2 уровень
                           //ЕСЛИ ЭТО grid type = group + guid = ****key!!
                           if (elements1.item(i).getNodeName().equalsIgnoreCase("grid") &&
                               elements1.item(i).getAttributes().getNamedItem("type").getNodeValue().equalsIgnoreCase("group")&&
                                   elements1.item(i).getAttributes().getNamedItem("guid").getNodeValue().matches(key_p)){
                                //Удаляем файлы элемента
                                System.out.println(palette_to.getParent());
                                delete_f_and_d(palette_to.getParent()+"\\"+elements1.item(i).getAttributes().getNamedItem("path").getNodeValue());
                                //Удаляем сам элемент из палетки
                             Node test =  palette_d.removeChild(elements1.item(i));
                                System.out.println("удалили ноду "+test.getNodeName());
                           }
                           }
                   }
               //Сохраняем изменения
               Transformer transformer = TransformerFactory.newInstance().newTransformer();
                   document.normalizeDocument();
                   DOMSource source = new DOMSource(document);
                   StreamResult result  = new StreamResult(palette_to);
                   transformer.transform(source, result);
           }
           catch(Exception e){
               System.out.println("Ошибка вскрытия конечной палетки");
           }
       }
       else{
           //Несщуествует, тупо создаем новую.
           
           try{
               File katalog = new File(palette_to.getParent());
               katalog.mkdirs();
               palette_to.createNewFile();
               DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
               factory.setNamespaceAware(true);
               Document doc = factory.newDocumentBuilder().newDocument();
               Node root = doc.createElement("palette");
               doc.appendChild(root);
               doc.setXmlVersion("1.0");
               
               Transformer transformer = TransformerFactory.newInstance().newTransformer();
                //transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.transform(new DOMSource(doc), new StreamResult(palette_to));
           }
           catch(Exception e){
               System.out.println("Ошибкка создания новой конечной палетки");
           }
       }
       
       try{
           //Вскрываем целевую палетку, для записи
           DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
           Document document = documentBuilder.parse(palette_to);
           Node palette = document.getDocumentElement();
           
           // Вскрываем исходную палетку и ищем элементы с ключом
         DocumentBuilder documentBuilder2 = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                    watching.setText("Залазим в палетку " + from1);
                    textlog[indexlog++] = "Залазим в палетку " + from1;
                   Document document2 = documentBuilder2.parse(palette_from);
                 // Получаем корневой элемент - Нулевой уровень
                   Node palette2 = document2.getDocumentElement();
                 // получаем все элементы на мнемосхеме - первый уровень
                   NodeList elements1 = palette2.getChildNodes();
                 //просматриваем каждый элемент и заходим внутрь если нода не текст - второй уровень
                   for (int i = 0; i<elements1.getLength(); i++)
                   {
                       if (elements1.item(i).getNodeType() != Node.TEXT_NODE){
                           //2 уровень
                           //ЕСЛИ ЭТО grid type = group + guid = ****key!!
                           if (elements1.item(i).getNodeName().equalsIgnoreCase("grid") &&
                               elements1.item(i).getAttributes().getNamedItem("type").getNodeValue().equalsIgnoreCase("group")&&
                                   elements1.item(i).getAttributes().getNamedItem("guid").getNodeValue().matches(key_p)){
                               Node test = elements1.item(i);
                             //  Node test2 = document.adoptNode(test);
                              Node test2 = document.importNode(test, true);
                              
                               palette.appendChild(test2);
                               copy_palette_files(palette_from.getParent()+"\\"+elements1.item(i).getAttributes().getNamedItem("path").getNodeValue(),
                                       palette_to.getParent()+"\\"+elements1.item(i).getAttributes().getNamedItem("path").getNodeValue());
                           }
                       }
                   }
            //Сохраним измененную целевую палетку
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            document.normalizeDocument();
            DOMSource source = new DOMSource(document);
            StreamResult result  = new StreamResult(palette_to);
            transformer.transform(source, result);
       }
       catch (Exception e){
           System.out.println("error в палетке");
           e.printStackTrace();
       }
       
    
       
   }
   
   public void copy_palette_files(String file_f, String file_t){
       File cpfiles_from = new File(file_f);
       File cpfiles_to = new File(file_t);
       cpfiles_to.mkdir();
       File[] files = cpfiles_from.listFiles();
       if (files != null){
           for(int i=0; i < files.length;i++){
               System.out.println(files[i].getName());
               copy_files(files[i], new File(file_t+"\\"+files[i].getName()));
           }
       }
   }
   public void delete_f_and_d(String path){
        //delete files and derictories
       //Создаем эелемент файл с каталогом
        File del_f = new File(path) ;
       //Получим список файлов  в каталоге
       File[] files = del_f.listFiles();
       if (files != null){
        //Удалим все эти файлы
        for(int i =0; i< files.length; i++){
            files[i].delete();
        }
        //удалим пустой каталог
        del_f.delete();
       }
    }
   
   public static void set_specials(String s1,String s2,String s3,String s4,String s5
   ,String s6,String s7,String s8,String s9,String s10,String s11,String s12
   ,String s13,String s14,String s15,String s16, String s17, String s18, Boolean CB1,
   String WM){
       deffile = s1;
       logfile = s2;
       configur = s3;
       preff2 = s4;
       podval = s5;
       btm = s6;
       BTMI = s7;
       BS = s8;
       TITLE = s9;
       BARS = s10;
       PLT_UPP_E = s11;
       PLT_UPP_P = s12;
       PLT_UPP_G = s13;
       PLT_UPP_M = s14;
       PLT_UPP_S = s15;
       PLT_UPP_A = s16;
       DNT = s17;
       justlook = s18;
       allfiles = CB1;
       watermark = WM;
   }
   public void update_logPAth(){
       jTextField20.setText("");
   }
    public void save_config(){
         try{ 
        File conf_save = new File(CurDir, configur);
         FileWriter confWriter = new FileWriter(conf_save, false);
         confWriter.write("[Directories]"+line_separator);
         confWriter.write("0."+jTextField1.getText()+line_separator);
       //  confWriter.flush();
         confWriter.write("1."+jTextField2.getText()+line_separator);
      //   confWriter.flush();
         confWriter.write("2."+jTextField3.getText()+line_separator);
      //   confWriter.flush();
         confWriter.write("3."+jTextField4.getText()+line_separator);
      //   confWriter.flush();
         confWriter.write("4."+jTextField5.getText()+line_separator);
      //   confWriter.flush();
         confWriter.write("5."+jTextField6.getText()+line_separator);
     //    confWriter.flush();
         confWriter.write("6."+jTextField7.getText()+line_separator);
      //   confWriter.flush();
         confWriter.write("7."+jTextField20.getText()+line_separator);
      //   confWriter.flush();
      confWriter.write("[Bases]"+line_separator);
         confWriter.write("1A."+jTextField8.getText()+line_separator);
         confWriter.write("1D."+jTextField14.getText()+line_separator);
         confWriter.write("2A."+jTextField9.getText()+line_separator);
         confWriter.write("2A_b."+jTextField21.getText()+line_separator);
         confWriter.write("2A_2."+jTextField23.getText()+line_separator);
         confWriter.write("2D."+jTextField15.getText()+line_separator);
         confWriter.write("2D_b."+jTextField22.getText()+line_separator);
         confWriter.write("2D_2."+jTextField24.getText()+line_separator);
         confWriter.write("3A."+jTextField10.getText()+line_separator);
         confWriter.write("3D."+jTextField16.getText()+line_separator);
         confWriter.write("4A."+jTextField11.getText()+line_separator);
         confWriter.write("4D."+jTextField17.getText()+line_separator);
         confWriter.write("5A."+jTextField12.getText()+line_separator);
         confWriter.write("5D."+jTextField18.getText()+line_separator);
         confWriter.write("6A."+jTextField13.getText()+line_separator);
         confWriter.write("6D."+jTextField19.getText()+line_separator);
         confWriter.write("PCPA."+PCPA.getText()+line_separator);
         confWriter.write("PCPD."+PCPD.getText()+line_separator);
         confWriter.write("PCEA."+PCEA.getText()+line_separator);
         confWriter.write("PCED."+PCED.getText()+line_separator);
         confWriter.write("PCGA."+PCGA.getText()+line_separator);
         confWriter.write("PCGD."+PCGD.getText()+line_separator);
         confWriter.write("PCSA."+PCSA.getText()+line_separator);
         confWriter.write("PCSD."+PCSD.getText()+line_separator);
         confWriter.write("PCAA."+PCAA.getText()+line_separator);
         confWriter.write("PCAD."+PCAD.getText()+line_separator);
         confWriter.write("PCMA."+PCMA.getText()+line_separator);
         confWriter.write("PCMD."+PCMD.getText()+line_separator);
         confWriter.write("[Checkbox]"+line_separator);
         confWriter.write("CB1."+jCheckBox1.isSelected()+line_separator);
         confWriter.write("CB2."+jCheckBox2.isSelected()+line_separator);
         confWriter.write("CB3."+jCheckBox3.isSelected()+line_separator);
         confWriter.write("CB4."+jCheckBox4.isSelected()+line_separator);
         confWriter.write("CB5."+jCheckBox5.isSelected()+line_separator);
         confWriter.write("CB6."+jCheckBox6.isSelected()+line_separator);
         confWriter.write("CB7."+jCheckBox7.isSelected()+line_separator);
         confWriter.write("CB8."+jCheckBox8.isSelected()+line_separator);
         confWriter.write("CB9."+jCheckBox9.isSelected()+line_separator);
         confWriter.write("CB10."+jCheckBox10.isSelected()+line_separator);
         confWriter.write("CB11."+jCheckBox11.isSelected()+line_separator);
         confWriter.write("CB12."+jCheckBox12.isSelected()+line_separator);
         confWriter.write("CB13."+jCheckBox13.isSelected()+line_separator);
         confWriter.write("CB14."+jCheckBox14.isSelected()+line_separator);
         confWriter.write("CB15."+jCheckBox15.isSelected()+line_separator);
         confWriter.write("CB16."+jCheckBox16.isSelected()+line_separator);
         confWriter.write("CB17."+jCheckBox17.isSelected()+line_separator);
         confWriter.write("CB18."+jCheckBox18.isSelected()+line_separator);
         confWriter.write("CB19."+jCheckBox19.isSelected()+line_separator);
         confWriter.write("CB20."+jCheckBox20.isSelected()+line_separator);
         confWriter.write("CB21."+jCheckBox21.isSelected()+line_separator);
         confWriter.write("CB22."+jCheckBox22.isSelected()+line_separator);
         confWriter.write("CB23."+jCheckBox23.isSelected()+line_separator);
         confWriter.write("CB24."+jCheckBox24.isSelected()+line_separator);
         confWriter.write("CB25."+jCheckBox25.isSelected()+line_separator);
         confWriter.write("CB26."+jCheckBox26.isSelected()+line_separator);
         confWriter.write("CB27."+jCheckBox27.isSelected()+line_separator);
         confWriter.write("CB28."+jCheckBox28.isSelected()+line_separator);
         confWriter.write("CB29."+jCheckBox29.isSelected()+line_separator);
         confWriter.write("CB30."+jCheckBox30.isSelected()+line_separator);
         confWriter.write("CB31."+jCheckBox31.isSelected()+line_separator);
         confWriter.write("CB32."+jCheckBox32.isSelected()+line_separator);
         confWriter.write("CB33."+jCheckBox33.isSelected()+line_separator);
         confWriter.write("CB34."+jCheckBox34.isSelected()+line_separator);
         confWriter.write("CB35."+jCheckBox35.isSelected()+line_separator);
         confWriter.write("CB36."+jCheckBox36.isSelected()+line_separator);
         confWriter.write("CB37."+jCheckBox37.isSelected()+line_separator);
         confWriter.write("CB38."+jCheckBox38.isSelected()+line_separator);
         confWriter.write("CB39."+jCheckBox39.isSelected()+line_separator);
         confWriter.write("CB40."+jCheckBox40.isSelected()+line_separator);
         confWriter.write("CB41."+jCheckBox41.isSelected()+line_separator);
         confWriter.write("CB42."+jCheckBox42.isSelected()+line_separator);
         confWriter.write("CB43."+jCheckBox43.isSelected()+line_separator);
         confWriter.write("CB44."+jCheckBox44.isSelected()+line_separator);
         confWriter.write("CB45."+jCheckBox45.isSelected()+line_separator);
         confWriter.write("CB46."+jCheckBox46.isSelected()+line_separator);

     confWriter.close();
        }
        catch(Exception e){
            // ololo
            System.out.println("ERROR WRITE CONFIG");
        }
    }
    
    public static String get_checksum (File f1){
        String res = "";
        String f2 = f1.toString();
        
         try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
        //   MessageDigest digest = MessageDigest.getInstance("SHA-1");
            byte[] hash = digest.digest(f2.getBytes("UTF-8"));
            return bytesToHex(hash); // make it printable
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        return res;
    }
   
    
    public static String bytesToHex(byte[] hash){
        return DatatypeConverter.printHexBinary(hash).toLowerCase();
    }
}

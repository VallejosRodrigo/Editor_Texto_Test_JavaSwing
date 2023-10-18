import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.StyledEditorKit;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class FrameEditorText extends JFrame
{
    ImageIcon imageIcon = new ImageIcon("images/t.png");
    JTextPane jTextPane = new JTextPane();

    JPopupMenu jPopupMenu = new JPopupMenu();

    JToolBar barra;
    JMenuBar jMenuBar = new JMenuBar();
    JMenu archivo, editar, fuente, estilo, tamano, ayuda,
            fuentePop, estiloPop, tamanoPop, colorPop;

    JMenuItem nuevoArchivoJM, abrirArchivoJM, guardarArchivoJM,
            arialJM, serifJM, monospacedJM,
            negritaJM, cursivaJM, subrraJM,
            _12JM, _18JM, _24JM, _30JM,
            mensajeAyudaJM,
            copiarJM,cortarJM,pegarJM,
            guardarPop,
            arialPop, serifPop, monospacedPop,
            negritaPop, cursivaPop, subrraPop,
            _12Pop, _18Pop, _24Pop, _30Pop,
            copiarPop, cortarPop, pegarPop,
            rojoPop, azulPop, verdePop, negroPop;

    public FrameEditorText(){
        startFrame();
        startComponentComponent();

        setVisible(true);
    }


    private JButton iniciar_botones(String ruta){
        JButton boton = new JButton(new ImageIcon(ruta));

        barra.add(boton);

        return boton;
    }

    private void startFrame(){
        this.setBounds(300,100,600,550);
        this.setTitle("Editor de Texto - Vallejos Rodrigo E.");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setIconImage(imageIcon.getImage());
        this.setLayout(new BorderLayout());

        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                super.windowClosing(e);
               int resp = JOptionPane.showConfirmDialog(FrameEditorText.this,"¿Desea guardar cambios antes de salir?");

               if(resp == JOptionPane.YES_OPTION){
                   JFileChooser fileChooser = new JFileChooser();
                   FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de texto (*.txt)", "txt");
                   fileChooser.setFileFilter(filter);

                   int seleccion = fileChooser.showSaveDialog(abrirArchivoJM);


                   if (seleccion == JFileChooser.APPROVE_OPTION) {
                       java.io.File archivoSeleccionado = fileChooser.getSelectedFile();
                       String rutaArchivo = archivoSeleccionado.getAbsolutePath();

                       try
                       {
                           BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(rutaArchivo));

                           bufferedWriter.write(jTextPane.getText());
                           bufferedWriter.close();
                           JOptionPane.showMessageDialog(null,"¡Se ha guardado correctamente!");

                       } catch (IOException ex)
                       {
                           JOptionPane.showMessageDialog(null,"EL ARCHIVO NO EXISTE");
                       }
                   }

                   setDefaultCloseOperation(EXIT_ON_CLOSE);

               }else if(resp == JOptionPane.NO_OPTION){
                   setDefaultCloseOperation(EXIT_ON_CLOSE);

               }else if(resp == JOptionPane.CANCEL_OPTION){
                   setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
               }

            }
        });

    }

    private void startComponentComponent(){
//TODO------------------------------Barra de herramientas lateral-------------------------------------------------
        barra = new JToolBar();

        iniciar_botones("images/n.png").addActionListener(new StyledEditorKit.BoldAction());
        iniciar_botones("images/k.png").addActionListener(new StyledEditorKit.ItalicAction());
        iniciar_botones("images/s.png").addActionListener(new StyledEditorKit.UnderlineAction());

        barra.addSeparator();

        iniciar_botones("images/red.png").addActionListener(new StyledEditorKit.ForegroundAction("color_rojo",Color.RED));
        iniciar_botones("images/blue.png").addActionListener(new StyledEditorKit.ForegroundAction("color_azul",Color.BLUE));
        iniciar_botones("images/green.png").addActionListener(new StyledEditorKit.ForegroundAction("Color_verde",Color.GREEN));
        iniciar_botones("images/black.png").addActionListener(new StyledEditorKit.ForegroundAction("Color_negro",Color.BLACK));

        barra.addSeparator();

        iniciar_botones("images/izquierda.png").addActionListener(new StyledEditorKit.AlignmentAction("izq",0));
        iniciar_botones("images/centro.png").addActionListener(new StyledEditorKit.AlignmentAction("cen",1));
        iniciar_botones("images/derecha.png").addActionListener(new StyledEditorKit.AlignmentAction("der",2));
        iniciar_botones("images/justificar.png").addActionListener(new StyledEditorKit.AlignmentAction("jus",3));

        barra.addSeparator();

        iniciar_botones("images/n12.png").addActionListener(new StyledEditorKit.FontSizeAction("tamanio_12",12));
        iniciar_botones("images/n18.png").addActionListener(new StyledEditorKit.FontSizeAction("tamanio_18",18));
        iniciar_botones("images/n24.png").addActionListener(new StyledEditorKit.FontSizeAction("tamanio_24",24));
        iniciar_botones("images/n30.png").addActionListener(new StyledEditorKit.FontSizeAction("tamanio_30",30));


//TODO----------------------------------Menu NORTE (JMenuBar)---------------------------------------------------------------------------

        archivo = new JMenu("Archivo"); //-----inicio manejo archivos----------

        archivo.add(nuevoArchivoJM = new JMenuItem("Nuevo Archivo",new ImageIcon("images/new.png"))).addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                jTextPane.setText(null);
            }
        });

        archivo.add(abrirArchivoJM = new JMenuItem("Abrir...",new ImageIcon("images/abrir.png"))).addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                JFileChooser fileChooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de texto (*.txt)", "txt");
                fileChooser.setFileFilter(filter);

                int seleccion = fileChooser.showOpenDialog(abrirArchivoJM);


                if (seleccion == JFileChooser.APPROVE_OPTION) {
                    // El usuario seleccionó una ubicación y un nombre de archivo para guardar.
                    // Aquí puedes obtener la ubicación seleccionada:
                    java.io.File archivoSeleccionado = fileChooser.getSelectedFile();

                    // Ahora puedes usar "archivoSeleccionado" para guardar tu archivo.
                    // Por ejemplo, puedes obtener la ruta del archivo seleccionado así:
                    String rutaArchivo = archivoSeleccionado.getAbsolutePath();

                    try
                    {
                        BufferedReader bufferedReader = new BufferedReader(new FileReader(rutaArchivo));

                        StringBuilder contenido = new StringBuilder();
                        String linea;
                        while ((linea = bufferedReader.readLine()) != null) {
                            contenido.append(linea).append("\n"); // Agregar un salto de línea
                        }

                        // Establecer el contenido en el JTextPane
                        jTextPane.setText(contenido.toString());
                        JOptionPane.showMessageDialog(null,"¡Archivo cargado correctamente!");

                    } catch (IOException ex)
                    {
                        JOptionPane.showMessageDialog(null,"EL ARCHIVO NO EXISTE");
                    }
                }
            }
        });

        archivo.add(guardarArchivoJM = new JMenuItem("Guardar",new ImageIcon("images/guardar.png"))).addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
               int respuesta = JOptionPane.showConfirmDialog(abrirArchivoJM,"¿Desea Guardar?");

               if(respuesta== JOptionPane.YES_OPTION){

                   JFileChooser fileChooser = new JFileChooser();
                   FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de texto (*.txt)", "txt");
                   fileChooser.setFileFilter(filter);

                   int seleccion = fileChooser.showSaveDialog(abrirArchivoJM);

                   if (seleccion == JFileChooser.APPROVE_OPTION) {
                       // El usuario seleccionó una ubicación y un nombre de archivo para guardar.
                       // Aquí puedes obtener la ubicación seleccionada:
                       java.io.File archivoSeleccionado = fileChooser.getSelectedFile();

                       // Ahora puedes usar "archivoSeleccionado" para guardar tu archivo.
                       // Por ejemplo, puedes obtener la ruta del archivo seleccionado así:
                       String rutaArchivo = archivoSeleccionado.getAbsolutePath();

                       try
                       {
                           BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(rutaArchivo));

                           bufferedWriter.write(jTextPane.getText());
                           bufferedWriter.close();
                           JOptionPane.showMessageDialog(null,"¡Se ha guardado correctamente!");

                       } catch (IOException ex)
                       {
                           JOptionPane.showMessageDialog(null,"EL ARCHIVO NO EXISTE");
                       }
                   }

               }

            }
        });


        editar = new JMenu("Editar"); //-------inicio menu editar---------

        fuente = new JMenu("Fuente");
        arialJM = new JMenuItem("Arial");
        arialJM.setFont(new Font("arial",Font.PLAIN,13));
        fuente.add(arialJM).addActionListener(new StyledEditorKit.FontFamilyAction("ariales","arial"));

        serifJM = new JMenuItem("Serif");
        serifJM.setFont(new Font("serif",Font.PLAIN,13));
        fuente.add(serifJM).addActionListener(new StyledEditorKit.FontFamilyAction("serifes","serif"));

        monospacedJM = new JMenuItem("MonoSpaced");
        monospacedJM.setFont(new Font("monospaced",Font.PLAIN,13));
        fuente.add(monospacedJM).addActionListener(new StyledEditorKit.FontFamilyAction("monospacedes","monospaced"));

        estilo = new JMenu("Estilo");
        estilo.add(negritaJM = new JMenuItem("Negrita",new ImageIcon("images/n.png"))).addActionListener(new StyledEditorKit.BoldAction());
        estilo.add(cursivaJM = new JMenuItem("Cursiva",new ImageIcon("images/k.png"))).addActionListener(new StyledEditorKit.ItalicAction());
        estilo.add(subrraJM = new JMenuItem("Subrrayado",new ImageIcon("images/s.png"))).addActionListener(new StyledEditorKit.UnderlineAction());

        tamano = new JMenu("Tamaño");
        tamano.add(_12JM = new JMenuItem("12",new ImageIcon("images/n12.png"))).addActionListener(new StyledEditorKit.FontSizeAction("tama_12",12));
        tamano.add(_18JM = new JMenuItem("18",new ImageIcon("images/n18.png"))).addActionListener(new StyledEditorKit.FontSizeAction("tama_18",18));
        tamano.add(_24JM = new JMenuItem("24",new ImageIcon("images/n24.png"))).addActionListener(new StyledEditorKit.FontSizeAction("tama_24",24));
        tamano.add(_30JM = new JMenuItem("30",new ImageIcon("images/n30.png"))).addActionListener(new StyledEditorKit.FontSizeAction("tama_30",30));


        //------agrega los de copiar, cortar y pegar------
        editar.add(copiarJM = new JMenuItem("Copiar",new ImageIcon("images/copiar.png"))).addActionListener(new StyledEditorKit.CopyAction());
        editar.add( cortarJM = new JMenuItem("Cortar",new ImageIcon("images/cortar.png"))).addActionListener(new DefaultEditorKit.CutAction());
        editar.add(  pegarJM = new JMenuItem("Pegar",new ImageIcon("images/pegar.png"))).addActionListener(new DefaultEditorKit.PasteAction());

        editar.add(fuente);
        editar.add(estilo);
        editar.add(tamano);     //-------fin menu editar---------

        ayuda = new JMenu("Ayuda");

        ayuda.add(mensajeAyudaJM = new JMenuItem("Saber mas sobre el programador...",new ImageIcon("images/programming.png"))).addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                JOptionPane.showMessageDialog(FrameEditorText.this,"" +
                        "Soy Rodrigo, estudiante de la UTN en Chaco, Argentina.." +
                        "\n En esta ocación estoy probando codigos java, en las bibliotecas de Java Swing." +
                        "\nMe gusta mucho programar en este lenguaje ya que su sintaxis y su orientación 100% objeto es lo mejor!." +
                        "\nEste proyecyo simple y chiquito, lo hice para practicar un poco. " +
                        "\nEs un procesador de texto que lo hice sin utilizar ayudas de programas visuales como java GUI por ejemplo." +
                        "\nBueno, espero te guste :D ¡que tengas un excelente día!." +
                        "\n\nmi perfil en LinkedIn: https://www.linkedin.com/in/rodrigo-ezequiel-vallejos-8a8158250/" +
                        "\n GitHub: https://github.com/VallejosRodrigo");
            }
        });




        jMenuBar.add(archivo); //-------agrega los 'menus' a la barra norte-----------
        jMenuBar.add(editar);
        jMenuBar.add(ayuda);


//TODO--------------------------------------Menu desplegable JPopupMenu---------------------------------------------------
        fuentePop = new JMenu("Fuente");
        estiloPop = new JMenu("Estilo");
        tamanoPop = new JMenu("Tamaño");
        colorPop = new JMenu("Color");

        arialPop = new JMenuItem("Arial");
        arialPop.setFont(new Font("arial",Font.PLAIN,13));
        fuentePop.add(arialJM).addActionListener(new StyledEditorKit.FontFamilyAction("ariales","arial"));

        serifPop = new JMenuItem("Serif");
        serifPop.setFont(new Font("serif",Font.PLAIN,13));
        fuentePop.add(serifJM).addActionListener(new StyledEditorKit.FontFamilyAction("serifes","serif"));

        monospacedPop = new JMenuItem("MonoSpaced");
        monospacedPop.setFont(new Font("monospaced",Font.PLAIN,13));
        fuentePop.add(monospacedJM).addActionListener(new StyledEditorKit.FontFamilyAction("monospacedes","monospaced"));

        estiloPop = new JMenu("Estilo");
        estiloPop.add(negritaPop = new JMenuItem("Negrita",new ImageIcon("images/n.png"))).addActionListener(new StyledEditorKit.BoldAction());
        estiloPop.add(cursivaPop = new JMenuItem("Cursiva",new ImageIcon("images/k.png"))).addActionListener(new StyledEditorKit.ItalicAction());
        estiloPop.add(subrraPop = new JMenuItem("Subrrayado",new ImageIcon("images/s.png"))).addActionListener(new StyledEditorKit.UnderlineAction());

        tamanoPop = new JMenu("Tamaño");
        tamanoPop.add(_12Pop = new JMenuItem("12",new ImageIcon("images/n12.png"))).addActionListener(new StyledEditorKit.FontSizeAction("tam_12",12));
        tamanoPop.add(_18Pop = new JMenuItem("18",new ImageIcon("images/n18.png"))).addActionListener(new StyledEditorKit.FontSizeAction("tam_18",18));
        tamanoPop.add(_24Pop = new JMenuItem("24",new ImageIcon("images/n24.png"))).addActionListener(new StyledEditorKit.FontSizeAction("tam_24",24));
        tamanoPop.add(_30Pop = new JMenuItem("30",new ImageIcon("images/n30.png"))).addActionListener(new StyledEditorKit.FontSizeAction("tam_30",30));

        colorPop.add(rojoPop = new JMenuItem("Rojo",new ImageIcon("images/red.png"))).addActionListener(new StyledEditorKit.ForegroundAction("color_roj",Color.RED));
        colorPop.add(azulPop = new JMenuItem("Azul",new ImageIcon("images/blue.png"))).addActionListener(new StyledEditorKit.ForegroundAction("color_az",Color.BLUE));
        colorPop.add(verdePop = new JMenuItem("Verde",new ImageIcon("images/green.png"))).addActionListener(new StyledEditorKit.ForegroundAction("color_ver",Color.GREEN));
        colorPop.add(negroPop = new JMenuItem("Negro",new ImageIcon("images/black.png"))).addActionListener(new StyledEditorKit.ForegroundAction("color_neg",Color.BLACK));

        jPopupMenu.add(copiarPop = new JMenuItem("Copiar",new ImageIcon("images/copiar.png"))).addActionListener(new StyledEditorKit.CopyAction());
        jPopupMenu.add(cortarPop = new JMenuItem("Cortar",new ImageIcon("images/cortar.png"))).addActionListener(new DefaultEditorKit.CutAction());
        jPopupMenu.add(pegarPop = new JMenuItem("Pegar",new ImageIcon("images/pegar.png"))).addActionListener(new DefaultEditorKit.PasteAction());
        jPopupMenu.add(fuentePop);
        jPopupMenu.add(estiloPop);
        jPopupMenu.add(tamanoPop);
        jPopupMenu.add(colorPop);

        jTextPane.setComponentPopupMenu(jPopupMenu);


//TODO---------------------------------------AGREGA LOS ELEMENTOS AL FRAME------------------------------------------------
        barra.setOrientation(1);
        add(barra,BorderLayout.WEST);
        add(jTextPane,BorderLayout.CENTER);
        add(jMenuBar,BorderLayout.NORTH);

    }

}


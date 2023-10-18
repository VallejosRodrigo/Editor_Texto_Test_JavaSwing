import javax.swing.*;
import javax.swing.text.StyledEditorKit;
import java.awt.*;

public class FrameEditorText extends JFrame
{
    ImageIcon imageIcon = new ImageIcon("images/t.png");
    JTextPane jTextPane = new JTextPane();
    JMenu fuente, estilo, tamano;
    JToolBar barra;

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
        this.setBounds(300,100,600,520);
        this.setTitle("Editor de Texto - Vallejos Rodrigo E.");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setIconImage(imageIcon.getImage());
        this.setLayout(new BorderLayout());


    }

    private void startComponentComponent(){

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

        barra.setOrientation(1);
        add(barra,BorderLayout.WEST);
        add(jTextPane,BorderLayout.CENTER);
    }

}


package xo;

import java.awt.event.*;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class XO extends javax.swing.JFrame implements ActionListener{
    Casilla [][]Tablero;
    int Contador;
    
    public XO() {
        initComponents();
        Contador = 0;
        Tablero = new Casilla[3][3];
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                Tablero[i][j] = new Casilla();
                Tablero[i][j].A.setBounds((i*100)+10,/*derecha*/ (j*100)+10,/*abajo*/ 100, 100/*Ancho y Alto*/);//Ajustamos el tamaño y coordenadas de los componentes
                Tablero[i][j].A.addActionListener(this);//receptor de eventos
                this.add(Tablero[i][j].A);
            }
        }
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(320, 340));
        setResizable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new XO().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    void Tiro(Casilla X)
    {
        ImageIcon ICONO=null;
        if(Contador%2==0)
        {
            ICONO = new ImageIcon(this.getClass().getResource("O.png"));
            X.B=1;
        }
        else
        {
            ICONO = new ImageIcon(this.getClass().getResource("X.png"));
            X.B=4;
        }
        
        ICONO = new ImageIcon(ICONO.getImage().getScaledInstance(90, 90, java.awt.Image.SCALE_DEFAULT));//para el tamaño del icono
        X.A.setIcon(ICONO);
        X.A.removeActionListener(this);//para que no se sobreescriba el icono en la casilla
    }

    @Override
    public void actionPerformed(ActionEvent e){
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                if (e.getSource()==Tablero[i][j].A) 
                {
                    Tiro(Tablero[i][j]);
                    if(Revisar())
                    {
                        JOptionPane.showMessageDialog(null,"Has ganado!");
                    }
                    Contador++;
                    if(Contador == 8)
                    {
                        JOptionPane.showMessageDialog(null,"Empate!");
                    }
                }
            }
        }
        
        

    }
    boolean Revisar()
    {
        boolean Gano= false;
        int Suma=0;
        for(int i=0;i<3;i++)
        {
            Suma=Tablero[i][0].B+Tablero[i][1].B+Tablero[i][2].B;//Para revisar las columnas
            if(Suma==3 || Suma==12)
            {
                Gano=true;
                break;
            }
        }
        for(int i=0;i<3;i++)//Para revisar las filas
        {
            Suma=Tablero[0][i].B+Tablero[1][i].B+Tablero[2][i].B;
            if(Suma==3 ||Suma==12)
            {
                Gano=true;
                break;
            } 
        }
        Suma=Tablero[0][2].B+Tablero[1][1].B+Tablero[2][0].B;//Para la Diagonal invertida
        if(Suma==3 ||Suma==12)
                Gano=true;
        Suma=0;
        for(int i=0;i<3;i++)//Para la diagonal principal
            Suma+=Tablero[i][i].B;
        
        if(Suma==3 ||Suma==12)
                Gano=true;            
        
        return Gano;
        
    }
    
}
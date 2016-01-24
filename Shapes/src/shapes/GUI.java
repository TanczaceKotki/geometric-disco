package shapes;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;


public class GUI extends javax.swing.JFrame {

    private class ShapeSelectListener implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            if(e.getValueIsAdjusting())
                jTextArea1.setText(collisionDomain.getCollisionsDesc(collisionDomain.getShape(jTable1.getSelectedRow())));
        }
    }
    
    private class ShapeModifyListener implements TableModelListener {
        @Override
        public void tableChanged(TableModelEvent e) {
            
            int rowIndex = jTable1.getSelectedRow();
            if(rowIndex >= 0){
                DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                String name = (String)model.getValueAt(rowIndex, 0);
                double translateX = (double)model.getValueAt(rowIndex, 2);
                double translateY = (double)model.getValueAt(rowIndex, 3);
                double rotation = (double)model.getValueAt(rowIndex, 4) * Math.PI / 180.0;
                collisionDomain.updateShape(rowIndex, name, new Vector2(translateX, translateY), rotation);
                jTextArea1.setText(collisionDomain.getCollisionsDesc(collisionDomain.getShape(rowIndex)));
            }
            redrawCanvas();
            
            
        }
        
    }
    
    /**
     * Creates new form GUI
     */
    CollisionDomain collisionDomain;
    

    public void createShapes() {
        /*
        Rect rect = new Rect(100, 50);
        rect.setPosition(new Vector2(400, 150));
        collisionDomain.addShape(rect);
        
        ArrayList<Vector2> points = new ArrayList<Vector2>();
        points.add(new Vector2(100, 100));
        points.add(new Vector2(200, 100));
        points.add(new Vector2(150, 50));
        points.add(new Vector2(150, 150));
        
        Ellipse ellipse2 = new Ellipse(points);
        collisionDomain.addShape(ellipse2);
        */
        
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("test.bmp"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        Detector detector = new Detector(image);
        for(Shape shape: detector.shapes) {
            collisionDomain.addShape(shape);
        }
        
        displayShapes();
        
    }
    
    public void displayShapes() {
        
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        int rows = model.getRowCount(); 
        for(int i=rows-1; i>=0; i--) {
           model.removeRow(i); 
        }
        
        for(int i=0; i<collisionDomain.getShapeCount(); i++) {
            Shape shape = collisionDomain.getShape(i);
            Object[] row = new Object[5];
            row[0] = shape.getName();
            row[1] = shape.getDescription();
            row[2] = shape.getPosition().x;
            row[3] = shape.getPosition().y;
            row[4] = shape.getRotation() * 180.0 / Math.PI;
            model.addRow(row);
        }
        redrawCanvas();
    }
    
    public void redrawCanvas() {
        
        canvas.setLines(collisionDomain.getLines());
        canvas.setCollisiondata(collisionDomain.getCollisionEdges());
        Dimension dimension = new Dimension(canvas.getPrefferedWidth(), canvas.getPrefferedHeight());
        canvas.setPreferredSize(dimension);
        canvas.setSize(dimension);
        canvas.repaint();
        
    }
    
    public GUI() {
        collisionDomain = new CollisionDomain();        
        initComponents();
        createShapes();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        canvasContainer = new javax.swing.JScrollPane();
        canvas = new shapes.Canvas();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Shapes");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 866, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 449, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("File", jPanel1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setBorder(null);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Description", "Translate x", "Translate y", "Rotation"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.getSelectionModel().addListSelectionListener(new ShapeSelectListener());
        jTable1.getModel().addTableModelListener(new ShapeModifyListener());
        jScrollPane2.setViewportView(jTable1);
        jTable1.getAccessibleContext().setAccessibleName("shapeTable");

        jTextArea1.setColumns(14);
        jTextArea1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextArea1.setRows(10);
        jTextArea1.setBorder(javax.swing.BorderFactory.createTitledBorder("Collisions"));
        jScrollPane1.setViewportView(jTextArea1);

        canvas.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout canvasLayout = new javax.swing.GroupLayout(canvas);
        canvas.setLayout(canvasLayout);
        canvasLayout.setHorizontalGroup(
            canvasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 864, Short.MAX_VALUE)
        );
        canvasLayout.setVerticalGroup(
            canvasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 309, Short.MAX_VALUE)
        );

        canvasContainer.setViewportView(canvas);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1))
            .addComponent(canvasContainer)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(canvasContainer)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jTabbedPane1.addTab("Collisions", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 871, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 477, Short.MAX_VALUE)
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("Tabs");

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private shapes.Canvas canvas;
    private javax.swing.JScrollPane canvasContainer;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}

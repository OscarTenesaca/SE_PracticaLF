package PROYECTOSE;

public class InferenciaDifusa extends javax.swing.JFrame implements javax.swing.event.ChangeListener{
    
    private net.sourceforge.jFuzzyLogic.FIS FIS;
    private javax.swing.JSlider sldTemp ,sldTemp1;
    
    private net.sourceforge.jFuzzyLogic.plot.JDialogFis dialogFIS;
    
    public InferenciaDifusa(){
        super("Inferencia Difusa");
        setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        setSize(400,400);
        
        FIS = net.sourceforge.jFuzzyLogic.FIS.load("Logica.fcl",true);
        
        FIS.setVariable("sensor1",600.00);
        FIS.setVariable("sensor2",200.00);
        FIS.evaluate();
        double res = FIS.getVariable("angulo").getLatestDefuzzifiedValue();
        System.out.println("Valor: "+res);
        
        getContentPane().setLayout(new java.awt.FlowLayout());
        initComponentes();
    }
    
    private void initComponentes(){
        sldTemp = new javax.swing.JSlider();
        sldTemp.setMaximum(400);
        sldTemp.setMinimum(0);
        sldTemp.setPaintLabels(true);
        sldTemp.setPaintTicks(true);
        sldTemp.setMajorTickSpacing(70);
        sldTemp.setMinorTickSpacing(30);
        sldTemp.setName("fg");
        
        sldTemp.addChangeListener(this);
        
        sldTemp1 = new javax.swing.JSlider();
        sldTemp1.setMaximum(400);
        sldTemp1.setMinimum(0);
        sldTemp1.setPaintLabels(true);
        sldTemp1.setPaintTicks(true);
        sldTemp1.setMajorTickSpacing(70);
        sldTemp1.setMinorTickSpacing(30);
        
        
        sldTemp1.addChangeListener(this);
        
        
        getContentPane().add(sldTemp);
        getContentPane().add(sldTemp1);
        
        dialogFIS = new net.sourceforge.jFuzzyLogic.plot.JDialogFis(FIS);
        net.sourceforge.jFuzzyLogic.plot.JFuzzyChart.get().chart(FIS.getVariable("angulo"), FIS.getVariable("angulo").getDefuzzifier(), true);
    }
    
    public void stateChanged(javax.swing.event.ChangeEvent ce){
        double v =((double)sldTemp.getValue())/10;
        double h =((double)sldTemp1.getValue())/10;
        System.out.println("valor: "+v);
        FIS.setVariable("sensor1",v);
        FIS.setVariable("sensor2",h);
        FIS.evaluate();
        dialogFIS.repaint();
        net.sourceforge.jFuzzyLogic.plot.JFuzzyChart.get().chart(FIS.getVariable("angulo"), FIS.getVariable("angulo").getDefuzzifier(), true);        
        }    
    
    public static void main(String [] args){
        new InferenciaDifusa().setVisible(true);
    }
}

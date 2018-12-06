/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chitrashala;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;


/**
 *
 * @author saw
 */
public class Histogram extends JFrame{
   
    private int width;
    private int height;
    private int p=0,r=0,g=0,b=0;
    private int[] red_count = new int[256];
    private int[] blue_count = new int[256];
    private int[] green_count = new int[256];
    private BufferedImage image;
    
    
    public Histogram(BufferedImage img)
    {
        super("Histogram");
        width = img.getWidth();
        height =img.getHeight();
        setSize(850,700);
        setVisible(true);  
        setBackground(Color.blue);
        image = img;
        for(int q=0;q<256;q++)
        {
            red_count[q]=0;
            blue_count[q]=0;
            green_count[q]=0;
        }
        for(int i=0; i<height;i++)
        {
            for(int j=0;j<width;j++)
            {
                 p=image.getRGB(j,i);
                 r =(p>>16)&0xff;
                 g=(p>>8)&0xff;
                 b =p&0xff;
                 red_count[r] = red_count[r]+1;
                 green_count[g]=green_count[g]+1;
                 blue_count[b]=blue_count[b]+1;
            }
        }
        /*System.out.println(height*width);
        int total=0;
        for(int i=0;i<255;i++){
        System.out.println(red_count[i]+"\t");
        total = total+red_count[i];
        System.out.println(total);
        }*/
            
    
    }
    //getContentPane().add(new Histogram());
    private Image createHistogram(){
        BufferedImage bufferedImage = new BufferedImage(1000,1300,BufferedImage.TYPE_INT_RGB);
        Graphics hist = bufferedImage.getGraphics();
        float total = width*height;
        int temp;
        temp=(int)total;
        int count=0;
        while(temp!=0)
        {
            count++;
            temp=temp/10;
        }
        double factor=1;
        count=count-2;
        while(count!=0)
        {
            factor = factor*10;
            count--;
        }
        //hist.drawString("Histogram", 100, 100);
        hist.setColor(Color.white);
        hist.drawLine(40, 650, 40, 200 );
        hist.drawLine(40, 650, 850, 650);
        factor = factor*2;
        for(int i=0;i<255;i++){
            hist.setColor(Color.red);
            //System.out.println(red_count[i]+"THIS IS ME");
            int y1 = (int)(red_count[i]*(factor/total));
            if(y1>=450){
                y1=450;
            }
            hist.drawLine(45+i,650,45+i,650-y1);
            hist.setColor(Color.green);
            //System.out.println(red_count[i]+"THIS IS ME");
            int y2 = (int)(green_count[i]*(factor/total));
            if(y2>=450){
                y2=450;
            }
            hist.drawLine(300+i,650,300+i,650-y2);
            hist.setColor(Color.blue);
            //System.out.println(red_count[i]+"THIS IS ME");
            int y3 = (int)(blue_count[i]*(factor/total));
            if(y3 >=450)
            {
                y3=450;
            }
            hist.drawLine(555+i,650,555+i,650-y3);
        }
        
        
        
        
        
        return bufferedImage;
    }
    @Override
    public void paint (Graphics g){
        Image image= createHistogram();
        g.drawImage(image, 0, 0, this);
        
    }
}
    
    

package Presentation.Order;

import Presentation.StartWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class OrderWindowController implements ActionListener {

    JFrame frame;

    OrderWindowController(JFrame frame){
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(Objects.equals(e.getActionCommand(), "back")){
            frame.dispose();
            StartWindow startWindow = new StartWindow();
        }
        else if (Objects.equals(e.getActionCommand(), "add")){
            frame.dispose();
            AddOrder addOrder = new AddOrder();
        }
        else if (Objects.equals(e.getActionCommand(), "home")){
            frame.dispose();
            StartWindow startWindow = new StartWindow();
        }
    }
}

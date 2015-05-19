
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import javax.swing.JScrollPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author artamonov
 */
public class DemonstrationNewComponent extends JFrame {

    private MyTextPane exampleTextArea;
    private JLabel countCharacterLabel;

    DemonstrationNewComponent(String title) {
        super(title);
        setBounds(300, 100, 600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        addComponent();
    }

    //набрасывем на форму компоненты
    private void addComponent() {
        exampleTextArea = new MyTextPane("Ooh la la in L.A., Ooh la la in the U.S.A. \n"
                + "One night stands with a one night band is this \n"
                + "Ooh la la in L.A., Ooh la la in the U.S.A. \n"
                + "Making out every turned on a hit and miss. \n"
                + "You see the food and you feel the force, \n"
                + "B.L.T. and there ain't no sauce, \n"
                + "You get enough to feed a horse that's true. \n"
                + "Down at Barney's playing pool, \n"
                + "Minnesota Fats is ulta cool, \n"
                + "A load of balls make you look a fool then you. ");
        add(exampleTextArea);
         
        final JScrollPane scrollBar = new JScrollPane(exampleTextArea);
        scrollBar.setBounds(20, 20, 540, 400); 
        add(scrollBar);
     
        JLabel info = new JLabel("Подсчитать след. сивол:");
        info.setBounds(20, 440, 200, 20);
        add(info);

        JTextField fieldInputChr = new JTextField() {
            @Override
            //при клики выделение поля
            protected void processMouseEvent(MouseEvent e) {
                super.processMouseEvent(e); //To change body of generated methods, choose Tools | Templates.
                selectAll();
            }
        };
        fieldInputChr.setBounds(20, 470, 20, 20);
        fieldInputChr.setDocument(new PlainDocument() {
            @Override
            public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
                if (str == null) {
                    return;
                }
                if ((getLength() + str.length()) <= 1) {
                    super.insertString(offset, str, attr);
                }
            }
        });
        add(fieldInputChr);

        JButton btn = new JButton("Посчитать!");
        btn.setBounds(60, 465, 150, 25);
        btn.setFocusPainted(false);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showCountCharacter(fieldInputChr.getText());
            }
        });
        add(btn);

        countCharacterLabel = new JLabel();
        countCharacterLabel.setBounds(230, 470, 250, 20);
        add(countCharacterLabel);

    }

    private void showCountCharacter(String chr) {
        String temp;
        if (chr.isEmpty()) {
            temp = "Введите символ!";
        } else {
            Character character = chr.toLowerCase().charAt(0);    //у нас и есть всего один символ
            int number = exampleTextArea.getCountCharacter(character);
            temp = "Кол-во вхождения символа '" + chr + "' равно " + String.valueOf(number);
        }       
        countCharacterLabel.setText(temp);
    }

    public static void main(String[] args) {
        DemonstrationNewComponent test = new DemonstrationNewComponent("Пример основ ООП");
        test.setVisible(true);
    }
}

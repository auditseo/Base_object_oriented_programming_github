
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import javax.swing.JTextPane;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author artamonov
 */
public class MyTextPane extends JTextPane {
    //для смены стиля на дефолтный
    private final Style defaultStyle;
    //ГСЧ
    private final Random rnd;
    //карта: буква - кол-во повторов
    private Map<Character, Integer> mapCharacters;

    //конструктор
    MyTextPane(String text) {
        setText(text);
        rnd = new Random(System.currentTimeMillis());
        setFont(new Font(text, Font.PLAIN, 20));
        StyleContext sc = new StyleContext();
        final DefaultStyledDocument doc = new DefaultStyledDocument(sc);     
        defaultStyle = sc.getStyle(StyleContext.DEFAULT_STYLE);
    }

    @Override
    protected void processKeyEvent(KeyEvent e) {
        super.processKeyEvent(e); //To change body of generated methods, choose Tools | Templates.
        Color colorText = new Color(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        setForeground(colorText);
    }

    //подсчитываем кол-во вхождения всех символов
    private void coutingCharacter() {
        //при подсчете создаем по-новому, чтобы сбросить аккумулятор на ноль
        mapCharacters = new TreeMap<Character, Integer>();
        //берем текст с поля ввода сменяем регистр на нижний и разбиваем на массив символов
        for (Character iter : getText().toLowerCase().toCharArray()) {
            mapCharacters.put(iter, (mapCharacters.containsKey(iter)) ? mapCharacters.get(iter) + 1 : 1);
        }
    }

    //возвращаем кол-во вхождения символа
    public int getCountCharacter(Character character) {
        highlightCharacters(character);
        coutingCharacter();
        if (mapCharacters.containsKey(character)) {
            return mapCharacters.get(character);
        }
        return 0;
    }
    //подсвечиваем символы
    public void highlightCharacters(char character) {
        //обнуляем стиль на дефолтный
        getStyledDocument().setCharacterAttributes(0, getText().length(), defaultStyle, true);
        String tempStr = new String(getText());
        char[] arrayChr = tempStr.toLowerCase().toCharArray();
        for (int i = 0; i < arrayChr.length; i++) {
            if (character == arrayChr[i]) {
                StyleContext sc = new StyleContext();
                final Style agreementStyle = sc.addStyle("agreementStyle", null);
                StyleConstants.setForeground(agreementStyle, Color.red);
                StyleConstants.setFontFamily(agreementStyle, "serif");
                StyleConstants.setBold(agreementStyle, true);
                getStyledDocument().setCharacterAttributes(i, 1, agreementStyle, true);
            }
        }
    }
}

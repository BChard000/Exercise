import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class HtmlElement {
    protected String tagName;
    private String text;
    private boolean selfClosing;
    protected List<HtmlElement> children;
    protected Map<String, String> attributes;

    public HtmlElement(String tagName, String text, boolean selfClosing) {
        this.tagName = tagName;
        this.text = text;
        this.selfClosing = selfClosing;
        this.children = new ArrayList<>();
        this.attributes = new HashMap<>();
    }

    public void addElement(HtmlElement element) {
        children.add(element);
    }

    public void addAttribute(String key, String value) {
        // Attribútum hozzáadása az elemhez
        attributes.put(key, value);
    }

    public String htmlBuilder(int indentationLevel) {
        // HTML kód generálása az elemhez és a "children" elemeihez
        StringBuilder stringBuilder = new StringBuilder();
        String indent = " ".repeat(indentationLevel * 4);
        stringBuilder.append(indent).append("<").append(tagName);

        /*
        Egy sorban lévő elemek kezelése
        Ez nem sikerült, a kimenetnél látszik is, ezt a tudást mindenképpen szeretném pótolni
        */
        if (selfClosing) {
            stringBuilder.append(" />\n");
        } else {
            stringBuilder.append(">").append(text);

            for (HtmlElement child : children) {
                if (isInlineElement(child.tagName)) {
                    stringBuilder.append(child.htmlBuilder(0));
                } else {
                    stringBuilder.append("\n").append(child.htmlBuilder(indentationLevel + 1));
                }
            }

            if (!children.isEmpty() && !isInlineElement(children.get(children.size() - 1).tagName)) {
                stringBuilder.append("\n").append(indent);
            }
            stringBuilder.append("</").append(tagName).append(">");
        }
        return stringBuilder.toString();
    }


    //Azt vizsgáljuk, hogy bizonyos elemek egy sorba tartoznak-e
    private boolean isInlineElement(String tagName) {
        return ("<a>".equals(tagName) || "<p>".equals(tagName));
    }
}

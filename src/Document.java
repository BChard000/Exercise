public class Document {
    private HtmlElement rootElement;

    public Document() {
        this.rootElement = new HtmlElement("html", "", false);
    }

    public void addElementToBody(HtmlElement element) {
        getBody().addElement(element);
    }

    private HtmlElement getBody() {
        // Ha a <body> elem nem létezik, létrehozzuk, különben visszakapjuk
        for (HtmlElement element : rootElement.children) {
            if ("body".equals(element.tagName)) {
                return element;
            }
        }

        HtmlElement body = new HtmlElement("body", "", false);
        rootElement.addElement(body);
        return body;
    }

    public void addHeadElement(HtmlElement element) {
        // Hozzáadunk egy elemet a <head> részhez
        getHead().addElement(element);
    }

    private HtmlElement getHead() {
        // Ez a metódus a <head> elemet adja vissza, létrehozza, ha szükséges
        // Ha a <head> elem nem létezik, létrehozzuk, különben visszakapjuk
        HtmlElement head = new HtmlElement("head", "", false);
        rootElement.addElement(head);
        return head;
    }

    public String buildHtml() {
        // Létrehozzuk a HTML dokumentumot
        return "<!DOCTYPE html>\n" + rootElement.htmlBuilder(0);
    }

    public void removeElementsByName(String... tagNames) {
        // Az eltávolítandó elemeket töröljük
        for (String tagName : tagNames) {
            removeElementsRecursively(rootElement, tagName);
        }
    }

    private void removeElementsRecursively(HtmlElement element, String tagName) {
        /*
        Rekurzív módszer az elemek eltávolítására
        Iteratív módszerrel Queue-t kéne használni, de mivel kevés elemmel dolgozunk, a kód egyszerűsége érdekében
       jobban járunk a rekurzív módszerrel
         */
        element.children.removeIf(child -> child.tagName.equals(tagName));
        for (HtmlElement child : element.children) {
            removeElementsRecursively(child, tagName);
        }
    }
}

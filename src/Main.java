import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String name = scanner.nextLine();
        String email = scanner.nextLine();
        String repositoryUrl = "https://repository.url";

        Document document = new Document();

        document.addHeadElement(new HtmlElement("title", name + " - Teszt Feladat", false));

        // <h1>
        document.addElementToBody(new HtmlElement("h1", "Teszt Feladat", false));

        // Link a repositoryhoz egy <p> elemen belül
        HtmlElement paragraphWithLink = new HtmlElement("p", "", false);
        HtmlElement link = new HtmlElement("a", "Megoldás", false);
        link.addAttribute("href=", repositoryUrl);
        paragraphWithLink.addElement(link);
        document.addElementToBody(paragraphWithLink);


        // <p>
        document.addElementToBody(new HtmlElement("p", "A feladat elkészítőjének adatai", false));

        // Táblázat hozzáadása
        HtmlElement table = new HtmlElement("table", "", false);
        table.addAttribute("border", "1px solid black");
        HtmlElement row1 = new HtmlElement("tr", "", false);
        row1.addElement(new HtmlElement("td", "Név", false));
        row1.addElement(new HtmlElement("td", name, false));
        table.addElement(row1);
        HtmlElement row2 = new HtmlElement("tr", "", false);
        row2.addElement(new HtmlElement("td", "Elérhetőség", false));
        row2.addElement(new HtmlElement("td", email, false));
        table.addElement(row2);
        document.addElementToBody(table);

        // L&P Solutions link hozzáadása
        HtmlElement lpLink = new HtmlElement("a", "L&P Solutions", false);
        lpLink.addAttribute("href", "http://lpsolutions.hu");
        document.addElementToBody(lpLink);

        //Törlendő elemek
        System.out.println("\nHa szeretnél elemeket törölni, írd be őket szóközökkel elválasztva, ellenkező esetben nyomj Entert");
        String removeElements = scanner.nextLine();
        if (!removeElements.isEmpty()) {
            String[] split = removeElements.split(" ");
            document.removeElementsByName(split);
        }

        // HTML kód generálása és kiíratása
        System.out.println(document.buildHtml());
    }
}
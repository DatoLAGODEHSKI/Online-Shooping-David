public class ShopApp {
    private MenuManager menuManager;
    private DataInitializer dataInitializer;

    public ShopApp() {
        this.menuManager = new MenuManager();
        this.dataInitializer = new DataInitializer();
    }

    public void start() {
        dataInitializer.init();

        Client client = dataInitializer.getClient();
        Catalog catalog = dataInitializer.getCatalog();

        menuManager.start(client, catalog);
    }
}
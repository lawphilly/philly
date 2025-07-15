Mike's Bikes App


Part B: Update README.md File and First Commit
- Cleared all existing content in `README.md`  
- Added a single line:  New Read Me File!!!
- Commited the change


Part C: Customize User Interface
Line 14: Changed <title>My Bicycle Shop</title> to <title>Mike’s Bikes</title>
Line 19: Changed <h1>Shop</h1> to <h1>Mike’s Brakes<h1>

Part D – About Page
- Added `about.html` template under `src/main/resources/templates/`
- Line 55: Added `/about` handler in `MainScreenControllerr.java`
- Line 20: Inserted “About Us” link on the main screen (`mainscreen.html`)
- Verified navigation: `/mainscreen` ↔ `/about`

Part E: Sample Inventory Seeding
- File: src/main/java/com/example/demo/bootstrap/BootStrapData.java
- Line 21: Remove “throws Exception” from the run(String... args) signature
- Line 26: Add guard: if (partsCount == 0 && productsCount == 0) {
- Lines 28–33: Instantiate and save OutsourcedPart p1 (“Wheel”)
- Lines 35–40: Instantiate and save OutsourcedPart p2 (“Saddle”)
- Lines 42–47: Instantiate and save OutsourcedPart p3 (“Pedal”)
- Lines 49–54: Instantiate and save OutsourcedPart p4 (“Handlebar”)
- Lines 56–61: Instantiate and save OutsourcedPart p5 (“Chain”)
- Lines 64–68: Save five Product instances (“Mountain Bike”, “Road Bike”, “Electric Bike”, “Unicycle”, “Touring Bike”)
- Lines 72–73: Leave System.out.println(...) calls to log “Loaded parts: 5” and “Loaded products: 5”


Part F: Buy Now Button
- File: src/main/resources/application.properties  
  - Lines 4–5: Configure H2 in-memory database (`spring.datasource.url=jdbc:h2:mem:testdb`) and enable schema recreation on each run (`spring.jpa.hibernate.ddl-auto=create-drop`) :contentReference[oaicite:0]{index=0}

- File: src/main/resources/templates/mainscreen.html  
  - Line 3: Under `<div class="container">`, added:  
    ```html
    <div th:if="${message}" class="alert alert-success" th:text="${message}"></div>
    <div th:if="${error}"   class="alert alert-danger"  th:text="${error}"></div>
    ```  
    to render flash attributes for purchase success/failure :contentReference[oaicite:1]{index=1}  
  - Lines 95–100: Immediately after the Delete link in the product `<td>`, inserted:  
    ```html
    <form th:action="@{/buyProduct}" method="post" style="display:inline;">
        <input type="hidden" name="productId" th:value="${tempProduct.id}" />
        <button type="submit" class="btn btn-success btn-sm mb-3">Buy Now</button>
    </form>
    ```  
    to POST the purchase and decrement inventory :contentReference[oaicite:2]{index=2}

- File: src/main/java/com/example/demo/controllers/PurchaseController.java  
  - Lines 27–30: On successful purchase (`p.getInv()>0`), added:  
    ```java
    redirectAttrs.addFlashAttribute("message",
        "Purchased \"" + p.getName() + "\" successfully");
    ```  
    leveraging Spring’s `RedirectAttributes` for PRG flash messages :contentReference[oaicite:3]{index=3}  
  - Lines 31–34: On out-of-stock or missing product, added:  
    ```java
    redirectAttrs.addFlashAttribute("error",
        "Sorry, \"" + p.getName() + "\" is out of stock");
    // or
    redirectAttrs.addFlashAttribute("error",
        "Product not found (ID: " + id + ")");
    ```  
    to communicate failure cases via flash alerts :contentReference[oaicite:4]{index=4}  
  - Line 36: Changed return to `return "redirect:/mainscreen";` so that flash attributes render in the GET handler for `/mainscreen` :contentReference[oaicite:5]{index=5}  


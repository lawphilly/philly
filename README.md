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



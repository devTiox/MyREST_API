## Project TODO

### ProductService
- `getProduct(int id)`: get product from database
- `createProduct(Product product)`: save product to database
- `createProducts(List<Product> products)`: save many products to database
- `getProducts(List<Integer> ids)`: load many products from database
- `updateProduct(int id, Product updatedProduct)`: load existing product by id before update
- `patchProduct(int id, Map<String, Object> updates)`: load existing product by id before patch
- `patchProduct(int id, Map<String, Object> updates)`: load manufacturer from database before assigning it to product
- `deleteProduct(int id)`: delete product from database

### ManufacturerService
- `getManufacturer(int id)`: get manufacturer from database
- `createManufacturer(Manufacturer manufacturer)`: save manufacturer to database
- `createManufacturers(List<Manufacturer> manufacturers)`: save many manufacturers from database
- `getManufacturers(List<Integer> ids)`: load many manufacturers from database
- `updateManufacturer(int id, Manufacturer updatedManufacturer)`: load existing manufacturer by id before update
- `patchManufacturer(int id, Map<String, Object> updates)`: load existing manufacturer by id before patch
- `deleteManufacturer(int id)`: delete manufacturer from database

### Next Wiring
- connect services to repositories
- add controller endpoints
- decide how to expose relations in JSON to avoid recursive responses

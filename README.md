# Favorite Cars (console application)

- [Markdown Guide: Markdown Cheat Sheet](https://www.markdownguide.org/cheat-sheet/)
- [Github: About READMEs](https://docs.github.com/en/repositories/managing-your-repositorys-settings-and-features/customizing-your-repository/about-readmes)

This is a console application for saving and updating 
and deleting (also known as CRUD) cars with make, model 
and year.

All car data will be stored in a local Sqlite database.

## How to run the code

1. Pull the code from Github
2. Open the project in Intellij IDEA
3. Run `main()` from `se.distansakademin.Main`

> When starting the project a database file named `sample.db` will be created if there is none.

## Project structure

### `Main.java`

This file is the entry point and will call the database to manage cars

Here is where all user input / output is done.

### `Database.java`

This file creates and connects to the database to create, read, and update cars.

### `Car.java`

This is the model class for cars.

## Image demo

How to add an image: `![alt text](/assets/DEMO.png)`.

**Demo:**

![alt text](/assets/DEMO.png)

## Link demo

How to add a link: `[Link text](https://example.com/)`.

**Demo:**

[Link text](https://example.com/)

## List demo

### Unordered list

- Unordered one
- Unordered two
- Unordered three

### Ordered list

1. Ordered one
2. Ordered two
3. Ordered three


## Clean code

This code demonstrates using clean code by having descriptive method names such as

- `getAllCars()`: Describes exactly what it does, gets all cars from the database and returns the to the caller
- `throws SQLException`: Throw the exception to the caller so that it can the error correctly
- `executeSelectSql(String sql)`: Describes exactly what it does, takes an SQL query string, executes it and returns 
the result as a `ResultSet`

```java
public ArrayList<Car> getAllCars() throws SQLException {
    ResultSet rs = executeSelectSql("SELECT * FROM cars");
    return getCarListFromResultSet(rs);
}

private ResultSet executeSelectSql(String sql) throws SQLException {
    Statement stmt = conn.createStatement();
    return stmt.executeQuery(sql);
}

private static ArrayList<Car> getCarListFromResultSet(ResultSet rs) throws SQLException {
    ArrayList<Car> cars = new ArrayList<>();

    while (rs.next()) { // For every row (car)
        Car car = getCarFromResultSet(rs);
        cars.add(car); // Add our car object in the list
    }

    return cars;
}

private static Car getCarFromResultSet(ResultSet rs) throws SQLException {
    int id = rs.getInt("id");
    String make = rs.getString("make");
    String model = rs.getString("model");
    int year = rs.getInt("year");

    return new Car(id, make, model, year);
}
```
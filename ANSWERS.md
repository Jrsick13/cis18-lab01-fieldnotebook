# Question 1: private vs. Python naming convention

In Python, prefixing an attribute with an underscore (like `_sightings` or `_species`) is merely a convention indicating intended privacy. It relies on developer discipline, but Python still permits external code to read, overwrite, or mutate the variable directly at runtime.

In Java, declaring fields as `private` provides strict, compiler-enforced access control. The Java compiler physically blocks external classes from directly accessing or altering private fields. This forces callers to interact exclusively through controlled public methods (getters and setters), ensuring true encapsulation and protecting internal class state from unexpected modification.
# Question 2: Composition vs. Inheritance in FieldNotebook

If `FieldNotebook` extends `ArrayList<Sighting>`, it exposes all public collection methods—such as `clear()` and `remove(int)`—directly on the notebook instance, which breaks encapsulation. Callers could bypass notebook validation rules or clear the entire observation log from the outside. Using composition keeps `sightings` private, restricting external code to only the specific domain actions provided by `FieldNotebook` (like `add()`).
# Question 3: Polymorphism and Dynamic Dispatch

- **Mechanism:** Polymorphism (specifically Dynamic Method Dispatch or Late Binding).
- **Decision Timing:** Decided at **runtime**.
- **Explanation:** When `s.describe()` is called inside the `describeAll()` loop, the Java Virtual Machine (JVM) inspects the actual underlying object in memory (`Sighting`, `BirdSighting`, or `TreeSighting`) at execution time to execute the correct overridden `describe()` implementation.
# Question 4: Programming to an Interface

By changing `FieldNotebook` to store and operate on `List<Describable>` rather than `List<Sighting>`, the `describeAll()` method can process completely distinct types like `WeatherNote` without modifying the `Sighting` class or its hierarchy. This demonstrates the Open-Closed Principle: the class is open for extension to new types without requiring modifications to existing code.

# Reflection: C4 Verbosity (Java Streams vs. Python)

While Java's C4 stream expression is significantly more verbose than Python's `max(totals, key=totals.get)`, the extra verbosity is not purely a cost—it buys strong type safety, explicit Intent, and compile-time guarantees.

1. **Type Safety & Refactoring:** In Java, every step of the pipeline operates on explicitly typed stream elements (`Map.Entry<String, Integer>`). If a type mismatch occurs, the compiler catches it immediately rather than causing a runtime failure.
2. **Explicit Data Transformations:** Python conceals key extraction and dictionary lookups behind higher-order functions. Java forces the developer to explicitly state the collection strategy (`Collectors.groupingBy`), downstream reduction (`Collectors.summingInt`), and optional extraction (`.orElse(null)`), avoiding implicit behavior when handling empty collections or null keys.

However, for short scripts, the syntactic overhead of Java streams adds cognitive load and reduces readability compared to Python's concise dictionary comprehensions. Overall, the verbosity represents a trade-off: higher upfront code volume in exchange for robust structural safety in larger systems.

# Part D: Design Smell Hunt

**Where:** `Sighting` class, inside the `describe()` method (and its overrides in `BirdSighting` and `TreeSighting`).

**What breaks:** If a new requirement requires displaying sightings in a different context—such as exporting to a JSON API response, generating a short summary for a mobile UI, or supporting localized languages (e.g., Spanish)—hardcoding string formatting directly inside the domain model forces us to either edit every single class in the hierarchy or add numerous extra string-formatting methods to `Sighting` and its subclasses. This violates the Single Responsibility Principle because display logic is tightly coupled to entity data logic.

**Fix:** Remove string formatting responsibilities from the sighting domain objects and delegate them to a separate formatter class or service. The sighting classes would only hold data and provide getter methods, while the external formatter takes a sighting instance and converts it into whatever text, HTML, or JSON representation the system needs.
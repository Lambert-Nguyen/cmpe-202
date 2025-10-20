┌─────────────────┐
│   ClassName     │  ← Section 1: Name (always in the middle)
├─────────────────┤
│   Attributes    │  ← Section 2: Variables/Properties
├─────────────────┤
│   Operations    │  ← Section 3: Methods/Functions
└─────────────────┘
```

**Real Example - A Dog Class:**
```
┌──────────────────────┐
│        Dog           │
├──────────────────────┤
│ - name : String      │
│ - age : int          │
│ - breed : String     │
├──────────────────────┤
│ + bark() : void      │
│ + eat() : void       │
│ + getAge() : int     │
└──────────────────────┘
```

Let me break down each part:

#### **1. CLASS NAME (Top Section)**
- Always centered and **bolded**
- Uses **PascalCase** (first letter capitalized): `Dog`, `BankAccount`, `ShoppingCart`

#### **2. ATTRIBUTES (Middle Section)**
These are the variables/data the class holds.

**Format:** `visibility name : type = initialValue`

**Example breakdown:**
- `- name : String` means:
  - `-` = private (only this class can see it)
  - `name` = variable name
  - `String` = data type
  
- `- age : int = 0` means:
  - Same as above, but starts with value 0

**Visibility Symbols (SUPER IMPORTANT for your exam):**
- `+` = **public** (everyone can access)
- `-` = **private** (only this class can access)
- `#` = **protected** (this class and its children can access)
- `~` = **package/default** (same package can access)

#### **3. OPERATIONS (Bottom Section)**
These are the methods/functions the class can perform.

**Format:** `visibility methodName(parameter : Type) : ReturnType`

**Example breakdown:**
- `+ bark() : void` means:
  - `+` = public method
  - `bark()` = method name with no parameters
  - `void` = returns nothing

- `+ setAge(newAge : int) : void` means:
  - Takes an integer parameter called `newAge`
  - Returns nothing

- `+ getAge() : int` means:
  - No parameters
  - Returns an integer

---

### **Translating UML to Java Code**

Let's convert our Dog UML to actual Java:

**UML:**
```
┌──────────────────────┐
│        Dog           │
├──────────────────────┤
│ - name : String      │
│ - age : int          │
│ - breed : String     │
├──────────────────────┤
│ + bark() : void      │
│ + eat() : void       │
│ + getAge() : int     │
└──────────────────────┘

public class Dog {
    // Attributes (middle section)
    private String name;    // - means private
    private int age;
    private String breed;
    
    // Operations (bottom section)
    public void bark() {    // + means public
        System.out.println("Woof!");
    }
    
    public void eat() {
        System.out.println("Nom nom");
    }
    
    public int getAge() {   // returns int
        return age;
    }
}
```

**See the pattern?**
- UML `-` → Java `private`
- UML `+` → Java `public`
- UML `:` → Java type comes AFTER the colon in UML, but BEFORE in Java

---

### **RELATIONSHIPS - How Classes Connect**

Now comes the really important part - how classes relate to each other. There are several types:

---

#### **1. ASSOCIATION - "Uses" or "Knows About"**

This is the simplest relationship. One class **uses** or **knows about** another.

**Example:** A `Person` has a `Dog`
```
┌────────┐           ┌──────┐
│ Person │───────────│ Dog  │
└────────┘           └──────┘
```

The line means they're associated. But how many dogs? That's where **multiplicity** comes in.

**Multiplicity Numbers:**
```
┌────────┐  1    0..* ┌──────┐
│ Person │───────────│ Dog  │
└────────┘           └──────┘

This reads: "One Person can have zero or more Dogs"
Common Multiplicities:

1 = exactly one
0..1 = zero or one (optional)
* or 0..* = zero or more (unlimited)
1..* = one or more (at least one)
2..5 = between 2 and 5
3 = exactly 3

Real-World Examples:

Person 1 ──── 0..1 Driver'sLicense (person can have 0 or 1 license)
Mother 1 ──── 1..* Child (mother has at least 1 child)
Team 1 ──── 11 Player (soccer team has exactly 11 players)

public class Person {
    private ArrayList<Dog> dogs;  // 0..* means list of dogs
}

// OR for 0..1:
public class Person {
    private Dog dog;  // might be null
}
```

---

#### **2. COMPOSITION - "Owns" (Strong Relationship)**

Composition is shown with a **SOLID BLACK DIAMOND** at the owner's end.

**Key Rule:** If the owner dies, the parts die too. Parts **cannot exist** without the owner.

**Example:** A `House` has `Rooms`
```
┌────────┐ ◆───────── ┌──────┐
│ House  │            │ Room │
└────────┘            └──────┘

The solid diamond ◆ is at House because the House owns the Rooms.
Think about it:

If you demolish a house, the rooms cease to exist
A room can't just float around without being part of some house
The house is responsible for creating and destroying the rooms

More Examples:

Car ◆──── Engine (engine doesn't exist without the car)
Book ◆──── Chapter (chapters are part of the book)
ATM ◆──── Screen (ATM's screen is built into it)

In Java:

public class House {
    private ArrayList<Room> rooms;  // House owns rooms
    
    public House() {
        // House creates the rooms (owns them)
        rooms = new ArrayList<>();
        rooms.add(new Room("Kitchen"));
        rooms.add(new Room("Bedroom"));
    }
}
```

**Exam Tip:** Look for the solid diamond! It's always at the "whole" end, pointing away from the "parts."

---

#### **3. AGGREGATION - "Has" (Weak Relationship)**

Aggregation is shown with a **HOLLOW/EMPTY DIAMOND** ◇

**Key Rule:** Parts **can exist independently** of the whole.

**Example:** A `University` has `Students`
```
┌────────────┐ ◇───────── ┌─────────┐
│ University │            │ Student │
└────────────┘            └─────────┘

Think about it:

If the university closes, students still exist
Students existed before joining the university
Students can transfer to another university

More Examples:

Pond ◇──── Duck (ducks can fly to another pond)
Library ◇──── Book (books exist independently)
Playlist ◇──── Song (songs exist outside the playlist)

In Java:
public class University {
    private ArrayList<Student> students;
    
    public void enrollStudent(Student s) {
        students.add(s);  // Student created elsewhere
    }
}

public class Student {
    // Student can exist independently
    private String name;
}
```

---

#### **4. INHERITANCE - "Is A" Relationship**

Shown with a **HOLLOW TRIANGLE** pointing to the parent.

**Example:** A `Dog` is an `Animal`
```
        ┌─────────┐
        │ Animal  │  ← Parent/Superclass
        └─────────┘
             △
             │
        ┌────┴────┐
        │   Dog   │  ← Child/Subclass
        └─────────┘
```

**The triangle always points UP to the parent!**

**More Examples:**
```
        ┌──────────┐
        │  Shape   │
        └──────────┘
             △
       ┌─────┴─────┬─────────┐
       │           │         │
  ┌────────┐  ┌────────┐ ┌──────┐
  │ Circle │  │ Square │ │ Triangle │
  └────────┘  └────────┘ └────────┘

  public class Animal {
    protected String name;
    
    public void eat() {
        System.out.println("Eating...");
    }
}

public class Dog extends Animal {  // "extends" = inheritance
    public void bark() {
        System.out.println("Woof!");
    }
}
```

**Dog inherits everything from Animal** - it has `name` and `eat()` method automatically.

---

#### **5. IMPLEMENTATION - "Implements Interface"**

Shown with a **DASHED LINE and HOLLOW TRIANGLE** pointing to interface.

**Example:** A `Dog` implements `Pet`
```
   ┌───────────┐
   │ <<interface>> │
   │    Pet    │  ← Interface
   └───────────┘
        △
        ┆ (dashed line)
   ┌────┴────┐
   │   Dog   │  ← Implements
   └─────────┘

In Java:

public interface Pet {
    void play();
    void feed();
}

public class Dog implements Pet {  // "implements"
    public void play() {
        System.out.println("Playing fetch!");
    }
    
    public void feed() {
        System.out.println("Eating kibble");
    }
}
```

---

### **PUTTING IT ALL TOGETHER - Complete Example**

Let's design a simple **School System**:

**UML Diagram:**
```
        ┌──────────┐
        │  Person  │  ← Parent class
        ├──────────┤
        │ - name   │
        │ - age    │
        └──────────┘
             △
       ┌─────┴─────┐
       │           │
  ┌────────┐  ┌─────────┐
  │ Student│  │ Teacher │  ← Children (inheritance)
  ├────────┤  ├─────────┤
  │ - gpa  │  │ - salary│
  └────────┘  └─────────┘
       │           │
       │           │ teaches
       │  attends  │
       │      ┌────▼────┐
       └─────►│ Course  │  ← Association
              ├─────────┤
              │ - name  │
              └─────────┘
              ◆           
              │ owns
         ┌────▼─────┐
         │ Material │  ← Composition
         └──────────┘

Reading this diagram:

Student and Teacher inherit from Person
Student attends Course (many-to-many association)
Teacher teaches Course (one-to-many association)
Course owns Material (composition - materials die with course)


Practice Exercise
Let me give you a scenario, and you try to identify the relationships:
Scenario: Design a library system

A Library has Books
Books have an Author
Books can be borrowed by Members
Books have Pages (pages are part of the book)

Questions:

What's the relationship between Library and Book?
What's the relationship between Book and Page?
What's the relationship between Member and Book?

Answers:

Aggregation (◇) - Books can exist without the library
Composition (◆) - Pages can't exist without the book
Association - Members borrow books (temporary relationship)

Does this first section make sense? I want to make sure you really understand UML class diagrams before we move on to sequence diagrams.
Can you tell me:

What's the difference between composition and aggregation in your own words?
How do you know which way the inheritance triangle points?

Let me know if anything is unclear, and I'll explain it differently!RetryClaude can make mistakes. Please double-check responses.
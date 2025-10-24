package uml;

import java.lang.reflect.Array;
import java.sql.Date;
import java.util.ArrayList;

public class Library {
    private String libName;
    private ArrayList<Material> libMaterials;
    private ArrayList<User> users;
    private ArrayList<Loan> loans;

    public Library(String name){
        this.libName = name;
        this.libMaterials = new ArrayList<>();
        this.users = new ArrayList<>();
        this.loans = new ArrayList<>();
    }

    public void addMaterial(Material material){
        this.libMaterials.add(material);
    }

    public void addUser(User user){
        this.users.add(user);
    }

}

public abstract class Material {
    protected Integer materialId;
    protected String materialTitle;
    protected ArrayList<Copy> copies; //1..* copies

    public Material(Integer id, String title){
        this.materialId = id;
        this.materialTitle = title;
        this.copies = new ArrayList<>();
    }

    public void addCopy(Copy copy){
        this.copies.add(copy);
    }

    public abstract String getDetails();
}

public class Book extends Material {
    private ArrayList<Author> authors; //1..*

    public Book(Integer id, String title){
        super(id, title));
        this.authors = new ArrayList<>();
    }
    public void addAuthor(Author author){
        this.authors.add(author);
    }

    @Override
    public String getDetails(){
        return;
    }
}

public class Video extends Material{
    private ArrayList<Actor> actors;  //1..*
    private Producer producers; //1
}

public class CD extends Material{
    private ArrayList<Entertainer> entertainers; //1..*
}

public class Copy{
    private Integer copyID;
    private Material material;
    private boolean isReference; //true = 2 hours lend only
}

public class Loan{
    private Date loanDateTime;
    private Copy copy;
    private Date returnDateTime;
    private User user;
}

public class User{
    private String userName;
    private String userAddress;
    private Integer phoneNumber;
    private ArrayList<Loan> loanHistory;
}

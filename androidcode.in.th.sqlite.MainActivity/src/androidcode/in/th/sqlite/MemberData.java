package androidcode.in.th.sqlite;

public class MemberData {
	private int id;
    private String name;
    private String surname;
    private int age;
    
    public MemberData(int id,String name,String surname,int age){
    	this.id = id;
    	this.name = name;
    	this.surname = surname;
    	this.age = age;
    }
    
    public int getId(){
    	return this.id;
    }
    
    public String getName(){
    	return this.name;
    }
    
    public String getSurname(){
    	return this.surname;
    }
    
    public int getAge(){
    	return this.age;
    }
}

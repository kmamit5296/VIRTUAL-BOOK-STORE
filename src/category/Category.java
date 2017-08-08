package category;

import helper.AllCollection;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.ListIterator;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

public class Category implements Serializable{
	private static final long serialVersionUID = 1L;
	int categoryID;
	String name;
	public int getCategoryID(){
		return categoryID;
	}
	public String getName(){
		return name;
	}
	public void setCategoryID(int categoryID){
		this.categoryID=categoryID;
	}
	public void setName(String name){
		this.name=name;
	}
	@SuppressWarnings("unchecked")
	public String retName(int id,ServletContext sc){
		AllCollection alc=new AllCollection();
		Category c1=null;
		alc.setcList((ArrayList<Category>)sc.getAttribute("cList"));
		ListIterator<Category> li=(alc.getcList()).listIterator();
		while(li.hasNext()){
			c1=li.next();
			if(c1.getCategoryID()==id){
				break;
			}
		}
		return c1.getName();
	}
	@SuppressWarnings("unchecked")
	public int retID(String name,ServletContext sc){
		AllCollection alc=new AllCollection();
		Category c1=null;
		alc.setcList((ArrayList<Category>)sc.getAttribute("cList"));
		ListIterator<Category> li=(alc.getcList()).listIterator();
		while(li.hasNext()){
			c1=li.next();
			if(c1.getName().equals(name)){
				break;
			}
		}
		System.out.println(name+"          "+c1.getCategoryID());
		return c1.getCategoryID();
	}
}

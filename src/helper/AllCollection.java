package helper;

import java.util.ArrayList;
import java.util.ListIterator;

import javax.servlet.ServletContext;


import category.Category;

import book.Book;

public class AllCollection {
	Book buk;
	Category cat;
	private ArrayList<Book> bList=new ArrayList<Book>();
	private ArrayList<Category> cList=new ArrayList<Category>();
	ListIterator<Book> allcatliit;
	public ArrayList<Book> bind4Ob(int id,ArrayList<Book> list){
		int i=0;
		ArrayList<Book> fourList=new ArrayList<Book>();
		ListIterator<Book> li=list.listIterator();
		while(li.hasNext()&&i<4){
			buk=li.next();
			if(buk.getCategoryID()==id){
				fourList.add(buk);
				i++;
			}
		}
		return fourList;
	}
	public static int hSize(int id,ArrayList<Book> list){
		int n=0;
		ListIterator<Book> li=list.listIterator();
		while(li.hasNext()){
			if(li.next().getCategoryID()==id){
				n++;
			}
		}
		if(n<4)
			return 520;
		else if(n%4==0)
			return (n/4)*520;
		else return ((n/4+1)*460);
	}
	public ArrayList<Book> forAllCat4Ob(int id,ArrayList<Book> list){
		int i=0;
		ArrayList<Book> fourList=new ArrayList<Book>();
		while(i<4&&allcatliit.hasNext()){
			buk=allcatliit.next();
			if(buk.getCategoryID()==id){
				fourList.add(buk);
				i++;
			}
		}
		return fourList;
	}
	public	void bindAllOb(int id,ArrayList<Book> list,ServletContext serct){
		ArrayList<ArrayList<Book>> allcatbook=new ArrayList<ArrayList<Book>>();
		allcatliit=list.listIterator();
		while(allcatliit.hasNext())
			allcatbook.add(forAllCat4Ob(id,list));
		serct.setAttribute("allcatbook", allcatbook);
	}
	public void bindBookObject(int id,ArrayList<Book> list,ServletContext sc){
		Book book=null;
		ListIterator<Book> li=list.listIterator();
		while(li.hasNext()){
			book=li.next();
			if(book.getBookID()==id)
				break;
		}
		sc.setAttribute("bookPageDetail", book);
	}
	public ArrayList<Category> getcList() {
		return cList;
	}
	public void setcList(ArrayList<Category> cList) {
		this.cList = cList;
	}
	public ArrayList<Book> getbList() {
		return bList;
	}
	public void setbList(ArrayList<Book> bList) {
		this.bList = bList;
	}
}

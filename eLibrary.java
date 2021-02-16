package com.examples;
/*
Author - Raj Mishra
Date of Creation- 16 Feb 2021
 */
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.Scanner;
class library{
    LinkedList<String> availableBooks= new LinkedList<>();
    LinkedList<String> issuedBooks= new LinkedList<>();
    LinkedList<String> issuedTo= new LinkedList<>();
    LinkedList<String> issuedAt= new LinkedList<>();
    LinkedList<String> bookLog= new LinkedList<>();

    library(){
        availableBooks.add("Java");
        availableBooks.add("Python");
        availableBooks.add("Web Development");
    }
    private String atDateTime(){
        LocalDateTime DateTime= LocalDateTime.now();
        return DateTime.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy hh:mm:ss a"));
    }
    private void removeBook(char mode, String book){
        if(mode=='A') {
            availableBooks.remove(book);
        }
        else if(mode== 'I'){
            issuedBooks.remove(book);
        }
        else{
            System.out.println("The book was not found to remove");
        }
    }
    public void addBook(String addBook){
        availableBooks.add(addBook);
        System.out.println("*** "+ addBook +" Book Added in the library ***");
        bookLog.add("*** Book Added to Available Books ***\nBook: "+addBook+ "\nAdded at: "+atDateTime()+"\n");
    }
    public void issueBook(String issueBook,String name){
        int ind= availableBooks.indexOf(issueBook);
        if(ind!= -1) {
            System.out.println("Enter the name of the person to issue book");
            name = new Scanner(System.in).nextLine();
            issuedBooks.add(issueBook);
            bookLog.add("*** Book Issued ***\nIssued Book: "+ issueBook+ "\n"+ "Issued To: "+name + "\n"+ "Issued At: "+atDateTime()+"\n");
            issuedTo.add(name);
            issuedAt.add(atDateTime());
            System.out.println("*** " + issueBook + " Book Issued to " + name + " ***");
            removeBook('A',issueBook);
        }
        else {
            System.out.println("Book not available or typo mistake");
        }
    }
    public void returnBook(String returnBook, String name){
        int ind= issuedBooks.indexOf(returnBook);
        if(ind!= -1) {
            System.out.println("Enter the name of the person who is returning the book");
            name = new Scanner(System.in).nextLine();
            boolean confirm= name.equalsIgnoreCase(issuedTo.get(ind));
            if(!confirm){
                System.out.println("Are you returning the book taken by "+issuedTo.get(ind));
                System.out.println("Press y for yes or any other key for no");
                char cn= new Scanner(System.in).next().charAt(0);
                if(cn=='y') {
                    confirm = true;
                }
            }
            if(confirm) {
                availableBooks.add(returnBook);
                bookLog.add("*** Book Returned ***\nBook: " + returnBook + "\nReturned by: " + name + "\nReturned at: " + atDateTime() + "\n");
                System.out.println("*** " + returnBook + " Book returned ***");
                removeBook('I', returnBook);
            }
            else{
                System.out.println("Sorry then you can't return it");
            }
        }
        else {
            System.out.println("Book was not Issued or typo mistake");
        }
    }
    public void showAvailableBooks(){
        System.out.println("----------Available Books----------");
        int i=0;
        while(i<availableBooks.size()){
            System.out.println(availableBooks.get(i));
            i++;
        }
        System.out.println("---------------------------------");
    }
    public void showIssuedBooks(){
        System.out.println("----------Issued Books----------");
        int i=0;
        while(i<issuedBooks.size()){
            System.out.println("Issued Book: "+ issuedBooks.get(i)+ "\n"+ "Issued To: "+issuedTo.get(i) + "\n"+ "Issued At: "+issuedAt.get(i)+"\n");
            System.out.println();
            i++;
        }
        System.out.println("---------------------------------");
    }
    public void showBookLog(){
        System.out.println("----------Book Logs----------");
        int i=0;
        while(i<bookLog.size()){
            System.out.println(bookLog.get(i));
            i++;
        }
        System.out.println("---------------------------------");
    }
}
public class eLibrary {
    static library myBooks = new library();
    static int choice;
    static String book, name;
    public static void choose(){
        while(true){
            System.out.println("Press 1 to See available books");
            System.out.println("Press 2 to See Issued books");
            System.out.println("Press 3 to Add new book in Available Books");
            System.out.println("Press 4 to Issue Book");
            System.out.println("Press 5 to Return Book");
            System.out.println("Press 6 to See Book Logs");
            Scanner sc = new Scanner(System.in);
            int choice = Integer.parseInt(sc.nextLine());
            if (choice == 1) {
                myBooks.showAvailableBooks();
            } else if (choice == 2) {
                myBooks.showIssuedBooks();
            } else if (choice == 3) {
                System.out.println("Enter the name of the book you want to add");
                book = sc.nextLine();
                myBooks.addBook(book);
            } else if (choice == 4) {
                System.out.println("Enter the name of the book you want to Issue");
                book = sc.nextLine();
                myBooks.issueBook(book, name);
            } else if (choice == 5) {
                System.out.println("Enter the name of the book you want to Return");
                book = sc.nextLine();
                myBooks.returnBook(book,name);
            } else if (choice == 6) {
                myBooks.showBookLog();
            }else{
                System.out.println("Choose proper values!");
            }
            System.out.println("Press any key to continue, 0 to exit");
            char exit= sc.next().charAt(0);
            if(exit=='0'){
                break;
            }

        }
    }
    public static void main(String[] args){
        try{
            choose();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}

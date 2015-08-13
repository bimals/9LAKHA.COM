package blog.model;

import com.mongodb.WriteResult;

public class Link {

    public String bookName;
    public int millionsSold;

    public Link next;


    public Link(String bookName, int millionsSold){
        this.bookName = bookName;
        this.millionsSold = millionsSold;
    }

    public void display(){
        System.out.println(this.bookName + ": " + this.millionsSold + ",000,000");
    }

    public String toString(){
        return this.bookName;
    }
} 

class LinkedList{

    public Link firstLink; //---reference to the first link in the last/the last link that was added to the list


    public LinkedList(){
        firstLink = null; //---by default
    }

    public boolean isEmpty(){
        return (firstLink == null);
    }

    /* How is new Link is added???
     * New Link is created
     * It's Next is assigned the reference to the previous Link created
     * The LinkedList's firstLink is assigned a reference to the newest Link added
     */

    public void insertFirstLink(String bookName, int millionsSold){
        Link newLink = new Link(bookName, millionsSold); //---a new guy has moved into town
        newLink.next = firstLink; //connect firstLink field to the newLink
        firstLink = newLink; //last who moved into town;
    }

    //Removing the first Link
    //The LinkedList's firstLink is assigned the value of the current fistLink's next
    public Link removeFirst(){

        Link linkReference = firstLink; //firstLink is the last guy who moved in to the list

        if(!isEmpty()){
            firstLink = firstLink.next;
        }
        else{
            System.out.println("Empty LinkedList");
        }

        return linkReference;
    }

    //How to cycle through all of the Links
    /*
     * Start at the reference stored in the firstLink for the LinkedList
     * Get the references stored in Next for every Link until Next returns null
     */
    public void display(){
        Link theLink = firstLink;
        while(theLink != null){
            theLink.display();
            System.out.println("Next Link: " + theLink.next);
            theLink = theLink.next;
            System.out.println();
        }
    }

    //How do you find a Link in a LinkedList
    /*
     * Check the data for the firstLink reference stored in the LinkedList
     * If you don't get a match, continue searching every reference stored in Next until Next returns null
     */
    public Link find(String bookName){
        Link theLink = firstLink;
        if(!isEmpty()){
            while(theLink.bookName != bookName){
                if(theLink.next == null)
                    return null;
                else{
                    theLink = theLink.next; 
                }
            }
        }
        else{
            System.out.println("Empty LinkedList");
        }

        return theLink;
    }

    //Remove a specific Link
    public Link removeLink(String bookName){
        Link currentLink = firstLink;
        Link previousLink = firstLink;

        while(currentLink.bookName != bookName){
            if(currentLink.next == null){
                return null;
            }
            else{
                currentLink = previousLink;
                currentLink = currentLink.next;
            }
        }

        if(currentLink == firstLink){
            firstLink = firstLink.next;
        }
        else{
            previousLink.next = currentLink.next;
        }

        return currentLink;
    }

}

class Tester{

    public static void main(String[] args){
        LinkedList theLinkedList = new LinkedList();
        theLinkedList.insertFirstLink("Inferno", 5);
        theLinkedList.insertFirstLink("Code Da Vinci", 100);
        theLinkedList.insertFirstLink("The Principle of 80/20", 10);
        theLinkedList.insertFirstLink("Harry Potter", 500);
        theLinkedList.insertFirstLink("The Lord of the Rings", 150);

        System.out.println(theLinkedList.find("Inferno").bookName + " was found");
        theLinkedList.removeLink("Inferno");
        theLinkedList.display();
        theLinkedList.display();
    }
}
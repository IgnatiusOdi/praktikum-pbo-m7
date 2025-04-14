package Tugas;

import java.util.*;

public class User<T extends User> {
    private String username, password, name, nickname, gender, location;
    private int age;
    private HashSet<String> listlikes = new HashSet<>();
    private ArrayList<T> myqueue = new ArrayList<>();
    private ArrayList<T> mymatches = new ArrayList<>();
    private ArrayList<String> inbox = new ArrayList<String>();
    private ArrayList<String> comments = new ArrayList<String>();

    public User(String username, String password, String name, String nickname, String gender, String location, int age) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
        this.gender = gender;
        this.location = location;
        this.age = age;
    }

    public void resetQueue() {
        while (myqueue.size() > 0) {
            myqueue.remove(0);
        }
    }

    public void addQueue(User u) {
        myqueue.add((T) u);
    }

    public void sortQueue() {
        //REMOVE TIDAK COCOK
        int posisi = 0;
        while (posisi < myqueue.size()) {
            User q = myqueue.get(posisi);
            int poin = 0;
            //GENDER
            if (!this.gender.equalsIgnoreCase(q.getGender())) {
                //AGE
                if (this.age-5 <= q.getAge() && this.age+5 >= q.getAge()) {
                    //LOCATION
                    if (this.location.equalsIgnoreCase(q.getLocation())) {
                        poin++;
                    }
                    //LIST LIKES
                    for (String l : this.listlikes) {
                        for (Object l2 : q.listlikes) {
                            if (l.equals(l2)) {
                                poin++;
                            }
                        }
                    }
                    if (poin == 0) {
                        myqueue.remove(posisi);
                    } else {
                        posisi++;
                    }
                } else {
                    myqueue.remove(posisi);
                }
            } else {
                myqueue.remove(posisi);
            }
        }

        //URUTKAN POIN TERBESAR
        int lastposition = 1;
        int position = 1;
        if (myqueue.size() > 1) {
            while (position != myqueue.size()) {
                User L = myqueue.get(position-1);
                User R = myqueue.get(position);
                int Lpoin = 0;
                int Rpoin = 0;
                //LEFT LOCATION
                if (this.location.equalsIgnoreCase(L.getLocation())) {
                    Lpoin++;
                }
                //RIGHT LOCATION
                if (this.location.equalsIgnoreCase(R.getLocation())) {
                    Rpoin++;
                }
                //LEFT LIST LIKES
                for (String l : this.listlikes) {
                    for (Object l2 : L.listlikes) {
                        if (l.equals(l2)) {
                            Lpoin++;
                        }
                    }
                }
                //RIGHT LIST LIKES
                for (String l : this.listlikes) {
                    for (Object l2 : R.listlikes) {
                        if (l.equals(l2)) {
                            Rpoin++;
                        }
                    }
                }
                //KIRI < KANAN
                if (Lpoin < Rpoin) {
                    T temp = myqueue.get(position-1);
                    myqueue.set(position-1, myqueue.get(position));
                    myqueue.set(position, temp);
                    position--;
                } else {
                    position++;
                }
                if (position == 0) {
                    position = lastposition;
                }
            }
        }
    }

    public void myProfile() {
        System.out.println(
            "Name : " + this.name +
            "\nUsername : " + this.username +
            "\nPassword : " + this.password +
            "\nNickname : " + this.nickname +
            "\nGender : " + this.gender +
            "\nAge : " + this.age +
            "\nLocation : " + this.location +
            "\nList of Likes"
        );
        for (String l : listlikes) {
            System.out.println("- " + l);
        }
    }

    public void deleteUser() {
        System.out.println(this.username + " - " + this.name);
    }

    public int getMyQueueSize() {
        return myqueue.size();
    }

    public T getMyQueueKe(int index) {
        return myqueue.get(index);
    }

    public void myQueueKe(int index) {
        User search = myqueue.get(index);
        System.out.println(
            "Name : " + search.name +
            "\nNickname : " + search.nickname +
            "\nAge : " + search.age +
            "\nGender : " + search.gender +
            "\nLocation : " + search.location +
            "\nList of Likes"
        );
        for (Object l : search.listlikes) {
            System.out.println("- " + l);
        }
    }

    public void addMatches(int index) {
        User search = myqueue.get(index);
        System.out.println(search.getName() + " is Added to My Matches");
        mymatches.add((T) search);
        removeQueue(index);
    }

    public void removeQueue(int index) {
        myqueue.remove(index);
    }

    public int getMyMatchesSize() {
        return mymatches.size();
    }

    public void myMatches() {
        System.out.println("===My Matches===");
        for (User m : mymatches) {
            System.out.println((mymatches.indexOf(m)+1) + ". " + m.getName());
        }
    }

    public void myMatchesKe(int index) {
        User search = mymatches.get(index);
        System.out.println(
                        "===Tinder Profile===" +
                        "\nUsername : " + search.username +
                        "\nName : " + search.name +
                        "\nNickname : " + search.nickname +
                        "\nAge : " + search.age +
                        "\nGender : " + search.gender +
                        "\nLocation : " + search.location +
                        "\nList of Likes"
        );
        for (Object l : search.listlikes) {
            System.out.println("- " + l);
        }
        System.out.println("====================");
        System.out.println("Comments : ");
        if (search.getCommentsSize() > 0) {
            for(Object c : search.comments) {
                System.out.println((search.comments.indexOf(c)+1) + ". " + c);
            }
        } else {
            System.out.println("--There are no comments yet--");
        }
        System.out.println("====================");
    }

    public void addInbox(User u, String output) {
        u.inbox.add(output);
    }

    public void sendMessage(int index, String message) {
        User search = mymatches.get(index);
        String output = this.username + " just sent a message " + message;
        search.addInbox(search, output);
    }

    public void addComments(User u, String output) {
        u.comments.add(output);
    }

    public void addThisComments(String comments) {
        this.comments.add(comments);
    }

    public void addThisInbox(String inbox) {
        this.inbox.add(inbox);
    }

    public void comments(int index, String message) {
        User search = mymatches.get(index);
        String output = this.username + " just commented \"" + message + "\" on your profile";
        search.addInbox(search, output);
        output = "\"" + message + "\" comment by " + this.username;
        search.addComments(search, output);
    }

    public int getCommentsSize() {
        return comments.size();
    }

    public ArrayList<String> getComments() {
        return comments;
    }

    public void printInbox() {
        System.out.println("===Tinder Inbox===");
        if (inbox.size() > 0) {
            for (String i : inbox) {
                System.out.println((inbox.indexOf(i)+1) + ". " + i);
            }
        } else {
            System.out.println("Tidak ada inbox saat ini!");
        }
    }

    public void myTinderProfile() {
        System.out.println(
                "===My Tinder Profile===" +
                        "\nUsername : " + this.username +
                        "\nName : " + this.name +
                        "\nNickname : " + this.nickname +
                        "\nAge : " + this.age +
                        "\nGender : " + this.gender +
                        "\nLocation : " + this.location +
                        "\nList of Likes"
        );
        for (Object l : listlikes) {
            System.out.println("- " + l);
        }
        System.out.println("====================");
        System.out.println("Comments : ");
        if (comments.size() > 0) {
            for(Object c : comments) {
                System.out.println((comments.indexOf(c)+1) + ". " + c);
            }
        } else {
            System.out.println("--There are no comments yet--");
        }
        System.out.println("====================");
    }

    public void addLikes(String likes) {
        listlikes.add(likes);
    }

    public int getLikeSize() {
        return listlikes.size();
    }

    public HashSet<String> getListLikes() {
        return listlikes;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

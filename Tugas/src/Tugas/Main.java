package Tugas;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Scanner scanint = new Scanner(System.in);
        ArrayList<User> user = new ArrayList<>();
        HashSet<String> listlikes = new HashSet<>();

	    do {
            System.out.println("====Welcome=======");
            System.out.println("=======To=========");
            System.out.println("=========Tinder===");
            System.out.println("1. Login");
            System.out.println("2. Create an Account");
            System.out.println("3. Exit");
            System.out.print(">> ");
            int input = scanint.nextInt();
            if (input == 1) {
                //LOGIN
                System.out.println("===Login Tinder Account===");
                System.out.print("Username: ");
                String loginnama = scan.nextLine();
                System.out.print("Password: ");
                String loginpassword = scan.nextLine();

                if (loginnama.equals("admin") && loginpassword.equals("nimda")) {
                    do {
                        System.out.println("===Menu Admin Tinder===");
                        System.out.println("1. List User");
                        System.out.println("2. Delete User");
                        System.out.println("3. List of Likes");
                        System.out.println("0. Back to Main Menu");
                        System.out.print(">> ");
                        input = scanint.nextInt();
                        if (input == 1) {
                            //LIST USER
                            System.out.println("===List User Tinder");
                            if (user.size() > 0) {
                                for (User u : user) {
                                    System.out.println((user.indexOf(u)+1) + ".");
                                    u.myProfile();
                                    System.out.println();
                                }
                            } else {
                                System.out.println("Tidak ada user saat ini!");
                            }
                        }
                        else if (input == 2) {
                            //DELETE USER
                            System.out.println("===Delete User Tinder===");
                            if (user.size() > 0) {
                                for (User u : user) {
                                    System.out.print((user.indexOf(u)+1) + ". ");
                                    u.deleteUser();
                                }
                                System.out.print(">> ");
                                input = scanint.nextInt();
                                if (input > 0 && input <= user.size()) {
                                    input--;
                                    //SETIAP USER YANG ADA
                                    for (User u : user) {
                                        if (user.indexOf(u) != input) {
                                            //SETIAP ISI QUEUE
                                            for (int i = 0; i < u.getMyQueueSize(); i++) {
                                                if (u.getMyQueueKe(i).equals(user.get(input))) {
                                                    u.removeQueue(i);
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                    user.remove(input);
                                    System.out.println("User Deleted!");
                                } else {
                                    System.out.println("Inputan index User tidak ditemukan!");
                                }
                            } else {
                                System.out.println("Tidak ada user saat ini!");
                            }
                        }
                        else if (input == 3) {
                            //LIST OF LIKES
                            System.out.println("===List of Likes===");
                            if (listlikes.size() > 0) {
                                int ctr = 1;
                                for (String l : listlikes) {
                                    System.out.println(ctr + ". " + l);
                                    ctr++;
                                }
                            } else {
                                System.out.println("Tidak ada list of likes saat ini!");
                            }
                        }
                        else if (input == 0) {
                            //EXIT
                            break;
                        }
                    } while (true);
                }
                else {
                    boolean login = false;
                    int index = -1;
                    for (User u : user) {
                        if (loginnama.equals(u.getUsername())) {
                            index = user.indexOf(u);
                            break;
                        }
                    }

                    User active;
                    if (index != -1) {
                        active = user.get(index);
                        if (loginpassword.equals(active.getPassword())) {
                            login = true;
                        } else {
                            System.out.println("Wrong Password!");
                        }
                    } else {
                        System.out.println("Username is not registered!");
                    }

                    while (login) {
                        active = user.get(index);
                        System.out.println("===Menu User Tinder===");
                        System.out.println("Hi " + active.getNickname() + "! Welcome to Tinder!");
                        System.out.println("1. Find a Match");
                        System.out.println("2. My Matches");
                        System.out.println("3. Inbox");
                        System.out.println("4. Search Profile");
                        System.out.println("5. My Profile");
                        System.out.println("0. Logout");
                        System.out.print(">> ");
                        input = scanint.nextInt();
                        if (input == 1) {
                            //FIND A MATCH
                            if (active.getMyQueueSize() > 0) {
                                while (active.getMyQueueSize() > 0) {
                                    System.out.println("===Find a Match===");
                                    active.myQueueKe(0);
                                    System.out.println("==================");
                                    System.out.println("1. Swipe Right");
                                    System.out.println("2. Swipe Left");
                                    System.out.println("0. Back");
                                    System.out.print(">> ");
                                    input = scanint.nextInt();
                                    if (input == 1) {
                                        //SWIPE RIGHT
                                        active.addMatches(0);
                                    } else if (input == 2) {
                                        //SWIPE LEFT
                                        active.removeQueue(0);
                                        System.out.println("Next Match");
                                    } else if (input == 0) {
                                        //BACK
                                        break;
                                    }
                                }
                            } else {
                                System.out.println("===Find a Match===");
                                System.out.println("Tidak ada queue saat ini!");
                            }
                        }
                        else if (input == 2) {
                            //MY MATCHES
                            if (active.getMyMatchesSize() > 0) {
                                active.myMatches();
                                System.out.print(">> ");
                                input = scanint.nextInt();
                                if (input > 0 && input <= active.getMyMatchesSize()) {
                                    input--;
                                    while (true) {
                                        active.myMatchesKe(input);
                                        System.out.println("====Menu Profile====");
                                        System.out.println("1. Send a Message");
                                        System.out.println("2. Comment Profile");
                                        System.out.println("3. Back");
                                        System.out.print(">> ");
                                        int pilihan = scanint.nextInt();
                                        if (pilihan == 1) {
                                            //SEND A MESSAGE
                                            System.out.println("===Send Message===");
                                            System.out.print("Input your Message : ");
                                            String message = scan.nextLine();
                                            active.sendMessage(input,message);
                                            System.out.println("Your message is sent!");
                                        } else if (pilihan == 2) {
                                            //COMMENT PROFILE
                                            System.out.println("===Comment Profile===");
                                            System.out.print("Input your comment : ");
                                            String message = scan.nextLine();
                                            active.comments(input,message);
                                            System.out.println("Successfully added a comment!");
                                        } else if (pilihan == 3) {
                                            //BACK
                                            break;
                                        }
                                    }
                                } else {
                                    System.out.println("Inputan index My Matches tidak ditemukan!");
                                }
                            } else {
                                System.out.println("===My Matches===");
                                System.out.println("Tidak ada matches saat ini!");
                            }
                        }
                        else if (input == 3) {
                            //INBOX
                            active.printInbox();
                        }
                        else if (input == 4) {
                            //SEARCH PROFILE
                            System.out.println("===Search Profile===");
                            System.out.print("Input a username : ");
                            String searchusername = scan.nextLine();
                            int indexsearch = -1;
                            for (User u : user) {
                                if (searchusername.equals(u.getUsername()) && user.indexOf(u) != index) {
                                    indexsearch = user.indexOf(u);
                                    break;
                                }
                            }
                            if (indexsearch != -1) {
                                User search = user.get(indexsearch);
                                System.out.println("User Found!");
                                do {
                                    System.out.println(
                                            "===Tinder Profile===" +
                                            "\nUsername : " + search.getUsername() +
                                            "\nName : " + search.getName() +
                                            "\nNickname : " + search.getNickname() +
                                            "\nAge : " + search.getAge() +
                                            "\nGender : " + search.getGender() +
                                            "\nLocation : " + search.getLocation() +
                                            "\nList of Likes"
                                    );
                                    for (Object l : search.getListLikes()) {
                                        System.out.println("- " + l);
                                    }
                                    System.out.println("====================");
                                    System.out.println("Comments : ");
                                    if (search.getCommentsSize() > 0) {
                                        for(Object c : search.getComments()) {
                                            System.out.println((search.getComments().indexOf(c)+1) + ". " + c);
                                        }
                                    } else {
                                        System.out.println("--There are no comments yet--");
                                    }
                                    System.out.println("====================");
                                    System.out.println("====Menu Profile====");
                                    System.out.println("1. Comment Profile");
                                    System.out.println("2. Back");
                                    System.out.print(">> ");
                                    input = scanint.nextInt();
                                    if (input == 1) {
                                        //COMMENT PROFILE
                                        System.out.println("===Comment Profile===");
                                        System.out.print("Input your comment : ");
                                        String temp = scan.nextLine();
                                        String comments = "\"" + temp + "\" comment by " + active.getUsername();
                                        String inbox = active.getUsername() + " just commented \"" + temp + "\" on your profile";
                                        search.addThisComments(comments);
                                        search.addThisInbox(inbox);
                                    } else if (input == 2) {
                                        //BACK
                                        break;
                                    }
                                } while(true);
                            } else {
                                System.out.println("User not found!");
                            }
                        }
                        else if (input == 5) {
                            //MY PROFILE
                            while (true) {
                                active.myTinderProfile();
                                System.out.println("====Menu Profile====");
                                System.out.println("1. Edit Name");
                                System.out.println("2. Edit Nickname");
                                System.out.println("3. Edit Age");
                                System.out.println("4. Edit Location");
                                System.out.println("5. Edit Likes");
                                System.out.println("0. Back");
                                System.out.print(">> ");
                                input = scanint.nextInt();
                                if (input == 1) {
                                    //EDIT NAME
                                    System.out.print("New Name : ");
                                    String newname = scan.nextLine();
                                    active.setName(newname);
                                    System.out.println("Successfully changed Name to " + newname);
                                } else if (input == 2) {
                                    //EDIT NICKNAME
                                    System.out.print("New Nickname : ");
                                    String newnickname = scan.nextLine();
                                    active.setNickname(newnickname);
                                    System.out.println("Successfully changed Nickname to " + newnickname);
                                } else if (input == 3) {
                                    //EDIT AGE
                                    int newage;
                                    do {
                                        System.out.print("New Age : ");
                                        newage = scanint.nextInt();
                                        if (newage < 18) {
                                            System.out.println("Minimum age is 18");
                                        }
                                    } while (newage < 18);
                                    active.setAge(newage);
                                    System.out.println("Successfully changed Age to " + newage);

                                    //RESET SEMUA QUEUE
                                    for (User u : user) {
                                        u.resetQueue();
                                    }
                                    //ADD & SORT
                                    if (user.size() > 1) {
                                        //ADD
                                        for (int i = 0; i < user.size(); i++) {
                                            for (int j = 0; j < user.size(); j++) {
                                                if (i != j) {
                                                    user.get(i).addQueue(user.get(j));
                                                }
                                            }
                                        }
                                        //SORT
                                        for (User u : user) {
                                            u.sortQueue();
                                        }
                                    }
                                } else if (input == 4) {
                                    //EDIT LOCATION
                                    System.out.print("New Location : ");
                                    String newlocation = scan.nextLine();
                                    active.setLocation(newlocation);
                                    System.out.println("Successfully changed Location to " + newlocation);

                                    //RESET SEMUA QUEUE
                                    for (User u : user) {
                                        u.resetQueue();
                                    }
                                    //ADD & SORT
                                    if (user.size() > 1) {
                                        //ADD
                                        for (int i = 0; i < user.size(); i++) {
                                            for (int j = 0; j < user.size(); j++) {
                                                if (i != j) {
                                                    user.get(i).addQueue(user.get(j));
                                                }
                                            }
                                        }
                                        //SORT
                                        for (User u : user) {
                                            u.sortQueue();
                                        }
                                    }
                                } else if (input == 5) {
                                    //EDIT LIKES
                                    System.out.println("List of Likes");
                                    String newlikes;
                                    do {
                                        System.out.print(">> ");
                                        newlikes = scan.nextLine();
                                        if (newlikes.equals("done")) {
                                            break;
                                        } else {
                                            active.addLikes(newlikes);
                                            listlikes.add(newlikes);
                                        }
                                    } while(true);
                                    System.out.println("Successfully added new List of Likes");

                                    //RESET SEMUA QUEUE
                                    for (User u : user) {
                                        u.resetQueue();
                                    }
                                    //ADD & SORT
                                    if (user.size() > 1) {
                                        //ADD
                                        for (int i = 0; i < user.size(); i++) {
                                            for (int j = 0; j < user.size(); j++) {
                                                if (i != j) {
                                                    user.get(i).addQueue(user.get(j));
                                                }
                                            }
                                        }
                                        //SORT
                                        for (User u : user) {
                                            u.sortQueue();
                                        }
                                    }
                                } else if (input == 0) {
                                    //BACK
                                    break;
                                }
                            }
                        }
                        else if (input == 0) {
                            //LOGOUT
                            login = false;
                        }
                    }
                }
            }
            else if (input == 2) {
                //CREATE
                System.out.println("===Create Tinder Account===");
                System.out.print("Username : ");
                String createusername = scan.nextLine();
                boolean boleh = true;
                //CHECK USERNAME KEMBAR
                if (user.size() > 0) {
                    for (User u : user) {
                        if (u.getUsername().equals(createusername)) {
                            boleh = false;
                            break;
                        }
                    }
                }
                if (boleh) {
                    System.out.print("Password : ");
                    String createpassword = scan.nextLine();
                    System.out.print("Name : ");
                    String createname = scan.nextLine();
                    System.out.print("Nickname : ");
                    String createnickname = scan.nextLine();
                    String creategender;
                    do{
                        System.out.print("Gender (Male/Female): ");
                        creategender = scan.nextLine();
                    } while (!creategender.equalsIgnoreCase("male") && !creategender.equalsIgnoreCase("female"));
                    String temp = creategender;
                    creategender = temp.substring(0,1).toUpperCase() + temp.substring(1);
                    int createage;
                    do {
                        System.out.print("Age : ");
                        createage = scanint.nextInt();
                    } while (createage < 18);
                    System.out.print("Location : ");
                    String createlocation = scan.nextLine();
                    temp = createlocation.toLowerCase();
                    createlocation = temp.substring(0,1).toUpperCase() + temp.substring(1);

                    user.add(new User(createusername, createpassword, createname, createnickname, creategender, createlocation, createage));
                    User active = user.get(user.size()-1);

                    System.out.println("List of Likes");
                    String createlikes;
                    do {
                        System.out.print(">> ");
                        createlikes = scan.nextLine();
                        if (createlikes.equals("done")) {
                            if (active.getLikeSize() > 0) {
                                break;
                            } else {
                                System.out.println("Minimal 1 likes");
                            }
                        } else {
                            temp = createlikes.toLowerCase();
                            createlikes = temp.substring(0,1).toUpperCase() + temp.substring(1);
                            active.addLikes(createlikes);
                            listlikes.add(createlikes);
                        }
                    } while(true);
                    System.out.println("Tinder User is successfully created!");

                    //ADD & SORT
                    if (user.size() > 1) {
                        //ADD QUEUE
                        for (User u : user) {
                            if (!u.equals(active)) {
                                active.addQueue(u);
                                u.addQueue(active);
                            }
                        }
                        //SORT
                        for (User u : user) {
                            u.sortQueue();
                        }
                    }
                } else {
                    System.out.println("Username is already used!");
                }
            }
            else if (input == 3) {
                //EXIT
                break;
            }
        }while(true);
    }
}

//A program that can be used to record all the homework I have
//have add, find, change, delete, read file, save functions

import java.io.*;
import java.util.*;

public class Blog {
	static ArrayList<String> fileList = new ArrayList<String>();
	static ArrayList<Log> mainList = new ArrayList<Log>();
	static String fileName;

	public static void main(String[] args) throws Exception {	
		Scanner sc = new Scanner (System.in);
		while (true) {
			System.out.println("======================================================================");

			System.out.println("");

			System.out.println("1.打开 2.查找  5.显示 6.读取 7.保存 0.退出");

			System.out.println("");

			System.out.println("======================================================================");

			System.out.print("请输入你的选择：");

			int n = sc.nextInt();

			switch (n) {
				case 1:
					menu();
					break;
				case 2:
					
					break;
				case 5:
					show(mainList);
					break;
				case 6:
					read(mainList);
					break;
				case 7:
					saveAs(mainList);
					break;
				case 0:
					System.exit(0);
					break;
			}	

		}

	}


	public static void menu() throws Exception {
		while (true) {
			show(mainList);
			Scanner sc = new Scanner(System.in);
			String input = sc.nextLine();
			int index = 0;
			// int index = sc.nextInt();
			// System.out.println(input + "\t" + index );
			if (input.contains("(")||input.contains(")")) {
				String command = input.substring(0, input.indexOf("("));
				String s = input.substring(input.indexOf("(")+1, input.indexOf(")"));
				index = Integer.parseInt(s);
			}
			if (input.contains("add")) {
				add();
			}else if (input.contains("delete")){
				delete(index);
			}else if (input.contains("open")){
				open(index);
			}else if (input.contains("save")){
				save();
			}
			else if (input.equals("help")) {
				System.out.println("use add, edit(index), or delete(index)");
			}else if(input.equals("0")) {
				break;
			}

		}
	}
	private static void add() {
		while(true) {
			System.out.println("Title: ");
			Scanner sc = new Scanner(System.in);
			String title = sc.nextLine();
			if (title.equals("0")) {
				break;
			}

			System.out.println("Message: ");
			String message = sc.nextLine();
			
			Log t =new Log(title, message);
			mainList.add(t);
		}
	}
	private static void delete(int index) {
		mainList.remove(mainList.get(index));
	}
	private static void edit(int index) {
		String message = mainList.get(index).getMessage();
		Scanner sc = new Scanner(System.in);
	}
	private static void open(int index) {
		System.out.println(mainList.get(index).getTitle());
		System.out.println(mainList.get(index).getMessage());
		while (true) {
			Scanner sc = new Scanner(System.in);
			String s = sc.nextLine();
			if (s.equals("exit")){
				break;
			}
		}
	}
	private static void save() throws Exception {
		if (fileName == null) {
			System.out.println("Please Create the file first");
		}else {
		FileWriter fw = new FileWriter(fileName + ".txt");
		for (int i = 0; i < mainList.size(); i++) {
			fw.write(mainList.get(i).getTitle() + "\n" + mainList.get(i).getMessage() + "\n");
		}
		fw.flush();
		fw.close();
		System.out.println("Save Complete");
		}
	}

	/*public static void search(ArrayList<Work> input) {
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("Search by 1.Due date, 2.Subject, 0.Quit");
			int dec = sc.nextInt();
			String s;
			switch(dec) {
				case 1: s = "Due";
				break;
				case 2: s = "Subject";
				break;

			}
			System.out.println("Search:");
			String find = sc.nextLine();
			for (Work w : input) {
				if (w.get+s().equals(find)) {
					System.out.println(w);
				}
			}
		}
	}*/

	public static void saveAs (ArrayList<Log> input) throws Exception{
		Scanner sc = new Scanner(System.in);
		System.out.println("input a Title for the file");
		String s = sc.nextLine();
		FileWriter fw = new FileWriter(s + ".txt");
		for (int i = 0; i < input.size(); i++) {
			fw.write(input.get(i).getTitle() + "\n" + input.get(i).getMessage() + "\n");
		}
		fw.flush();
		fw.close();
	}


	public static void read (ArrayList<Log> input) throws Exception{
		//I need to clear the list everytime I read a new file
		input.clear(); //clear the list before read another list
		for(int i = 0; i < input.size(); i++) {
			System.out.println(input.get(i).getTitle());  //print out all the file names for the user choose from
		}
		Scanner in = new Scanner(System.in);
		System.out.println("Choose a file");
		fileName = in.nextLine();
		File file = new File(fileName+ ".txt");
		if (file.exists()) {
			FileInputStream is = new FileInputStream(file); //create a stream
			Scanner sc =new Scanner (is); //put it into the scanner
			while (sc.hasNext()) {   //when there is another log
				String s = sc.nextLine();   //get the title/date		
				String s1 = sc.nextLine(); 	//get the details
				Log t = new Log(s,s1);  //save them into an object
				input.add(t);  //put the object into the list
			}
		}
	}

	public static void show (ArrayList<Log> input) {
		for (int a = 0; a < 10; a++) {
			System.out.println("");
		}
		System.out.println("======================================================================");
		for(int i = 0; i < input.size(); i++) {
			
			System.out.println(input.get(i).getTitle());
			System.out.println("index = "+i);
			// System.out.println(input.get(i).getMessage());
			System.out.println("");

		}
		System.out.println("======================================================================");

	}

	public static void getFileList() throws Exception {
		File file = new File("fileList.txt");
		FileInputStream is = new FileInputStream(file); //create a stream
		Scanner sc =new Scanner (is); 
		while (sc.hasNext()) {
			String s = sc.nextLine();
			fileList.add(s);
		}
	}


}
	


class Log {
	String title;
	String message;

	public Log(String d, String m) {
		title = d;
		message = m;
	}
	public String getTitle() {
		return title;
	}

	public String getMessage() {
		return message;

	}

	

	public String toString() {
		return "Title" + title + "\t" + message;
	}
}





				
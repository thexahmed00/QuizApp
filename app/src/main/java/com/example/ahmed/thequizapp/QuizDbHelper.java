package com.example.ahmed.thequizapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import com.example.ahmed.thequizapp.QuizContract.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Ahmed .
 */

public class QuizDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "TheQuizApp.db";
    private static final int DATABASE_VERSION = 7;

    private SQLiteDatabase db;

    public QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION4 + " TEXT, " +
                QuestionsTable.COLUMN_ANSWER_NR + " INTEGER," +
                QuestionsTable.COLUMN_DIFFICULTY + " TEXT "+
                ")";

        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        onCreate(db);
    }

    private void fillQuestionsTable() {

        Question q1 = new Question("How many flip-flops are required to construct a Decade-counter ?",    "4",     "8",     "5",     "10",    1,"Easy");
        addQuestion(q1);
        Question q2 = new Question("When all the inputs are 0 then _____ gate produces an output of 1 ?",    "NOR",     "OR",     "NAND",     "AND",    1,"Medium");
        addQuestion(q2);
        Question q3 = new Question("Which of the following is not a sequential circuit ?",    "Flip-flop",     "Comparator",     "Counter",     "Register",    2,"Hard");
        addQuestion(q3);
        Question q4 = new Question("A circuit has memory elements with logic gates is called ?",    "Sequential circuit",     "Combinational circuit",     "Series circuit",     "Combinational sequence",    1,"Easy");
        addQuestion(q4);
        Question q5 = new Question("TTL family is mostly used in ?",    "LSI chips",     "SSI chips",     "MSI chips",     "Both (2) and (3)",    3,"Medium");
        addQuestion(q5);
        Question q6 = new Question("Comparator circuits are used to compare two ____ ?",    "Binary numbers",     "Octal numbers",     "Decimal numbers",     "Hexa-decimal numbers",    1,"Hard");
        addQuestion(q6);
        Question q7 = new Question("Which of the following is not the output of magnitude comparator ?",    "A>B",     "A<B",     "A-B",     "AB",    3,"Easy");
        addQuestion(q7);
        Question q8 = new Question("The assembler directive DB is used to direct the assembler to reverse _____ ?",    "Only 10 bytes",     "Byte or bytes",     "Only 4 words",     "Words",    2,"Medium");
        addQuestion(q8);
        Question q9 = new Question("8255 PPI consists of ____ bit control word register ?",    "6",     "4",     "8",     "2",    3,"Hard");
        addQuestion(q9);
        Question q10 = new Question("Which of the following are included in 8279 interface ?",    "Keyboard , scan section",     "Display ssection",     "Interface section",     "All the above",    4,"Easy");
        addQuestion(q10);
        Question q11 = new Question("The addressing mode where the operand value is directly specified is _____ ?",    "Immediate",     "Direct",     "Index",     "Relative",    1,"Medium");
        addQuestion(q11);
        Question q12 = new Question("The clock frequency of 80286 is ?",    "12.5 MHz",     "10 MHz",     "8 MHz",     "All the above",    4,"Hard");
        addQuestion(q12);
        Question q13 = new Question("The instruction set of 80286 is compatible to that of ?",    "8051",     "8085",     "8086",     "None of the above",    3,"Easy");
        addQuestion(q13);
        Question q14 = new Question("The unit required to ensure the smooth execution of running of process in memory system is ?",    "Control unit",     "Interface unit",     "Memory management",     "Executive unit",    3,"Medium");
        addQuestion(q14);
        Question q15 = new Question("Which of the following tag helps to add a paragraph break after the text in HTML document ?",    "<PARAGRAPH>",     "<P>",     "<BR>",     "<NEXTLINE>",    2,"Hard");
        addQuestion(q15);
        Question q16 = new Question("Which of the following is I dempotence law ?",    "x+xy=x",     "x(x+y)=x",     "x+x=x",     "1+x=1",    3,"Easy");
        addQuestion(q16);
        Question q17 = new Question("To design 8x1 multiplexer using 2x1 multiplexer only,how many 2x1 multiplexer are required ?",    "7",     "6",     "5",     "4",    1,"Medium");
        addQuestion(q17);
        Question q18 = new Question("How many boolean functions can be formed with 3-variable ?",    "8",     "16",     "256",     "32",    3,"Hard");
        addQuestion(q18);
        Question q19 = new Question("Which of the following logic families uses bipolar transistors ?",    "TTL",     "NMOS",     "CMOS",     "ECL",    1,"Easy");
        addQuestion(q19);
        Question q20 = new Question("The functional difference between SR flip flop and JK flip flop is that ?",    "JK flip flop is faster than SR flip flop",     "JK flip flop accepts both inputs",     "JK flip flop has a feedback path",     "JK flip flop does not require external choices",    4,"Medium");
        addQuestion(q20);
        Question q21 = new Question("Which of the flip flop is free from race around condition ?",    "SR flip flop",     "D flip flop",     "T flip flop",     "Master-Slave flip flop",    4,"Hard");
        addQuestion(q21);
        Question q22 = new Question("How many control lines required for 16 to 1 multiplexer ?",    "2",     "4",     "6",     "8",    2,"Easy");
        addQuestion(q22);
        Question q23 = new Question("The octate equivalent value of hexadecimal value AB is",    "253",     "242",     "221",     "143",    1,"Medium");
        addQuestion(q23);
        Question q24 = new Question("A Boolean function may be transformed into ?",    "logical diagram",     "logical graph",     "map",     "matrix",    1,"Hard");
        addQuestion(q24);
        Question q25 = new Question("Full adder performs addition on ?",    "2 bits",     "3 bits",     "4 bits",     "5 bits",    2,"Easy");
        addQuestion(q25);
        Question q26 = new Question("The Boolean function A + BC is reduced representation of _____ ?",    "AB + BC",     "(A+B)(A+C)",     "A`B+AB`C",     "(A+C)B",    2,"Medium");
        addQuestion(q26);
        Question q27 = new Question("The instruction MOV BX,0008H belongs to the address mode ?",    "Register",     "Direct",     "Immediate",     "Register relative",    3,"Hard");
        addQuestion(q27);
        Question q28 = new Question("The intel 8086 microprocessor is a _______ processor ?",    "8 bit",     "16 bit",     "32 bit",     "4 bit",    2,"Easy");
        addQuestion(q28);
        Question q29 = new Question("Which of the following is not a data transfer/copy instruction ?",    "PUSH",     "MOV",     "POP",     "DAS",    4,"Medium");
        addQuestion(q29);
        Question q30 = new Question("The 80286 is able to address the physical memory of ?",    "8 MB",     "16 MB",     "24 MB",     "64 MB",    2,"Hard");
        addQuestion(q30);
        Question q31 = new Question("The registers that contain the status information is ?",    "control registers",     "instruction registers",     "program status word",     "program counter",    3,"Easy");
        addQuestion(q31);
        Question q32 = new Question("The result of MOV AL,65 is to store ?",    "store 0100 0010 in AL",     "store 42H in AL",     "store 40H in AL",     "store 0100 0001 in AL",    4,"Medium");
        addQuestion(q32);
        Question q33 = new Question("The address bits are sents out on lines through ______ ?",    "A16-19",     "A10-17",     "D10-17",     "B10-C17",    1,"Hard");
        addQuestion(q33);
        Question q34 = new Question("The 8086 fetches instruction one after another from ______ of memory ?",    "Code segment",     "IP",     "ES",     "SS",    1,"Easy");
        addQuestion(q34);
        Question q35 = new Question("The accumulator is 16 bit wide and is called ?",    "AX",     "AH",     "AL",     "DL",    1,"Medium");
        addQuestion(q35);
        Question q36 = new Question("Operating frequency of 80386 microprocessor is ________ ?",    "14 MHz and 20 MHz",     "20 MHz and 33MHz",     "35 MHz and 45 MHz",     "56 MHz and 76 MHz",    2,"Hard");
        addQuestion(q36);
        Question q37 = new Question("Reference that are available in the cache are called ?",    "Cache hits",     "Cache line",     "Cache memory",     "Cache miss",    1,"Easy");
        addQuestion(q37);
        Question q38 = new Question("The position of cache memory exists between ?",    "Main memory and secondary memory",     "CPU and main memory",     "RAM and ROM",     "Inside the processor",    2,"Medium");
        addQuestion(q38);
        Question q39 = new Question("Hard disc is an example of _____ memory of the digital computer ?",    "Primary memory",     "Secondary memory",     "Main memory",     "Random access memory",    2,"Hard");
        addQuestion(q39);
        Question q40 = new Question("The 2's complement representation of the decimal number 7 is ?",    "1010",     "1001",     "1100",     "1110",    2,"Easy");
        addQuestion(q40);
        Question q41 = new Question("The decimal value 0.25 is equivalent to ?",    "Binary 0.1",     "Binary 0.01",     "Binary 0.001",     "Binary 0.0001",    2,"Medium");
        addQuestion(q41);
        Question q42 = new Question("The floating point representation is used to store ?",    "Boolean values",     "Whole numbers",     "Real integers",     "Rational number",    3,"Hard");
        addQuestion(q42);
        Question q43 = new Question("In computers the subtraction can be carried out by ?",    "10's complement",     "9's complement",     "2's complement",     "1's complement",    3,"Easy");
        addQuestion(q43);
        Question q44 = new Question("A memory buffer used to accommodate a speed differential is called ?",    "Cache",     "Register",     "Accumulator",     "RAM",    1,"Medium");
        addQuestion(q44);
        Question q45 = new Question("Which bitwise operator is suitable for turning on a particular bit in a number ?",    "&& operator",     "& operator",     "| operator",     "|| operator",    3,"Hard");
        addQuestion(q45);
        Question q46 = new Question("In C , if you pass an array as an argument to a function , what actually gets ?",    "Value of elements in array",     "Base address of the array",     "First element of the array",     "Last element of the array",    2,"Easy");
        addQuestion(q46);
        Question q47 = new Question("A queue is a ______ ?",    "FIFO",     "LIFO",     "FILO",     "LOFI",    1,"Medium");
        addQuestion(q47);
        Question q48 = new Question("The retrieval of items in a stack is ___________ operation ?",    "push",     "pop",     "retrieval",     "access",    2,"Hard");
        addQuestion(q48);
        Question q49 = new Question("Which of the following is not an application of stack ?",    "finding factorial",     "tower of hanoi",     "infix to postfix",     "solving all the logarithmic functions",    4,"Easy");
        addQuestion(q49);
        Question q50 = new Question("Which of the following data structure cannot store the non-homogenous data elements",    "files",     "records",     "pointers",     "Array",    4,"Medium");
        addQuestion(q50);
        Question q51 = new Question("Which of the following is not a nonlinear data structure ?",    "Graph",     "Tree",     "Map",     "Stack",    4,"Hard");
        addQuestion(q51);
        Question q52 = new Question("Which of the following does not keep track of the address of every element in the list ?",    "Linear array",     "stack",     "Queue",     "Linked list",    1,"Easy");
        addQuestion(q52);
        Question q53 = new Question("The logical or mathematical model of a particular organization of data is called a _______?",    "Data structure",     "Data arrangement",     "Data congiguration",     "Data formation",    1,"Medium");
        addQuestion(q53);
        Question q54 = new Question("PING stands for ?",    "Packet interval gateway",     "Packet internet gateway",     "Peer interval gateway",     "Packet internet groper",    4,"Hard");
        addQuestion(q54);
        Question q55 = new Question("Which of the following is the process of managing data transfer between components with in the network ?",    "Data control",     "Flow control",     "Hop control",     "Error control",    2,"Easy");
        addQuestion(q55);
        Question q56 = new Question("Which of the following layer is not present in TCP/IP ?",    "Internet layer",     "Network layer",     "Transport layer",     "Physical layer",    4,"Medium");
        addQuestion(q56);
        Question q57 = new Question("Number of network ID in class A networks are ?",    "116",     "126",     "156",     "176",    2,"Hard");
        addQuestion(q57);
        Question q58 = new Question("Which of the following is not the application of TCP ?",    "WWW",     "E-mail",     "FTP",     "DNS",    4,"Easy");
        addQuestion(q58);
        Question q59 = new Question("TCP is ?",    "Connection oriented",     "connection less",     "Not use check sum",     "Not reliable in delivering the messages",    1,"Medium");
        addQuestion(q59);
        Question q60 = new Question("The length of the internet protocol is ?",    "8 bits",     "16 bits",     "32 bits",     "64 bits",    3,"Hard");
        addQuestion(q60);
        Question q61 = new Question("Which of the following is not a service of operating system ?",    "User interface",     "I/O operations",     "Communications",     "Provides drivers of application software",    4,"Easy");
        addQuestion(q61);
        Question q62 = new Question("Which of the following is the fast memory ?",    "ROM",     "PROM",     "DRAM",     "SRAM",    4,"Medium");
        addQuestion(q62);
        Question q63 = new Question("Which of the following state is initiated by the process itself ?",    "Running",     "Ready",     "Suspend",     "Block",    4,"Hard");
        addQuestion(q63);
        Question q64 = new Question("System calls are usually invoked by using ?",    "software interrupts",     "Polling",     "Privileged Interrupts",     "Test Editor",    1,"Easy");
        addQuestion(q64);
        Question q65 = new Question("Which of the following is the visible portion of operating system ?",    "Deadlock Handler",     "Process Scheduler",     "File System",     "Memory management",    3,"Medium");
        addQuestion(q65);
        Question q66 = new Question("Which of the following disc scheduling algorithm may suffer from Beledy's Anomaly",    "FIFO",     "LRU",     "MFU",     "LFU",    1,"Hard");
        addQuestion(q66);
        Question q67 = new Question("The primary job of Operating system is ?",    "Manage commands",     "Manage Users",     "Manage Programs",     "Manage Resources",    4,"Easy");
        addQuestion(q67);
        Question q68 = new Question("Which of the following can be chosen as primary key of the relational database design ?",    "Name of the person",     "Age",     "Aadhar card number",     "Address",    3,"Medium");
        addQuestion(q68);
        Question q69 = new Question("________ supports efficient retrieval of records based on the value of search key ?",    "Trigger",     "Cursor",     "Index",     "Package",    3,"Hard");
        addQuestion(q69);
        Question q70 = new Question("The SQL statement select ROUND(67.987,-2) from dual ?",    "It is Illegal",     "prints 68",     "prints 0",     "prints 60",    3,"Easy");
        addQuestion(q70);
        Question q71 = new Question("What operator tests column for the absence of data ?",    "EXISTS operator",     "NOT operator",     "IS NULL operator",     "none of these",    3,"Medium");
        addQuestion(q71);
        Question q72 = new Question("If a class contains pure virtual function, then it is termed as ________ ?",    "Virtual class",     "Sealed class",     "Pure local class",     "Abstract class",    4,"Hard");
        addQuestion(q72);
        Question q73 = new Question("In which parameter passing technique of C++ passes only the contents of the variable to the receiving function ?",    "by reference",     "by value",     "globally",     "locally",    2,"Easy");
        addQuestion(q73);
        Question q74 = new Question("A Constructor without any parameters is called ________ constructor ?",    "Custom",     "Dynamic",     "Static",     "Default",    4,"Medium");
        addQuestion(q74);
        Question q75 = new Question("In C++ , 'friend' keyword can be placed before ? ",    "function declaration",     "function defination",     "main function",     "package",    1,"Hard");
        addQuestion(q75);
        Question q76 = new Question("The if......else statement can be replaced by which operator ?",    "Bitwise operator",     "Conditional operator",     "Multiplicative opeerator",     "Scope Resolution operator",    2,"Easy");
        addQuestion(q76);
        Question q77 = new Question("Evaluate the following expression      (true && false)||true||false",    "0",     "1",     "false",     "01",    2,"Medium");
        addQuestion(q77);
        Question q78 = new Question("Which of the following automatic conversion is supported in java ?",    "short to int",     "byte to int",     "int to long",     "long to int",    3,"Hard");
        addQuestion(q78);
        Question q79 = new Question("What will be the return type of a method that will not return any value in a java program ?",    "void",     "int",     "double",     "string",    1,"Easy");
        addQuestion(q79);
        Question q80 = new Question("Which of the following is valid statement for declaration and initializing an array ?",    "int [] myData;",     "int [] myData = (15,8,22);",     "int myData [][] = {34,19,27,20};",     "int myData [] = {34,63,77};",    4,"Medium");
        addQuestion(q80);
        Question q81 = new Question("The java compiler ?",    "Creates executable file",     "Creates new classes",     "Converts java source code to Byte code",     "Produces java interpreter",    3,"Hard");
        addQuestion(q81);
        Question q82 = new Question("Which of the following package contains Exception class ?",    "java.util",     "java.file",     "java.io",     "java.lang",    4,"Easy");
        addQuestion(q82);
        Question q83 = new Question("What does AWT stands for ?",    "All windows tools",     "All writing tools",     "Abstract window toolkit",     "Abstract writing toolkit",    3,"Medium");
        addQuestion(q83);
        Question q84 = new Question("Which of the following blocks executes automatically whether exception is caught or not ?",    "finally",     "catch",     "throws",     "throw",    1,"Hard");
        addQuestion(q84);
        Question q85 = new Question("________ method cannot be overridden ?",    "super",     "static",     "final",     "private",    3,"Easy");
        addQuestion(q85);
        Question q86 = new Question("What is the syntax in HTML for creating link on a webpage ?",    "<LINK SRC= myexams.html>",     "<A SRC = myexams.html>",     "<BODY LINK= myexams.html>",     "<A HREF= myexams.html>",    4,"Medium");
        addQuestion(q86);
        Question q87 = new Question("Which of the following is valid IP address ?",    "498.11.497.67",     "192.168.321.10",     "1.899.432.456",     "192.168.36.115",    4,"Hard");
        addQuestion(q87);
        Question q88 = new Question("Which of the following protocol is used for e-mail services ?",    "SMAP",     "SMTP",     "SMIP",     "SMOP",    2,"Easy");
        addQuestion(q88);
        Question q89 = new Question("which of the following is not a web server ?",    "Microsoft Bing",     "Apache Tomcat",     "Microsoft IIS",     "Oracle Web Tier",    1,"Medium");
        addQuestion(q89);
        Question q90 = new Question("Which one is the method of Connection object in ADO.net",    "open()",     "new()",     "connectionOpen()",     "connectionStart()",    1,"Hard");
        addQuestion(q90);
        Question q91 = new Question("Which one of the following statements instantiates the mysql class in PHP ?",    "mysqli=new mysqli()",     "$mysqli = new mysqli()",     "$mysqli->new.mysqli()",     "mysqli->new.mysqli()",    2,"Easy");
        addQuestion(q91);
        Question q92 = new Question("Which of the following function is used to erase all session variables stored in the current session ?",    "session_destroy()",     "session_unset()",     "session_change()",     "session_remove()",    2,"Medium");
        addQuestion(q92);
        Question q93 = new Question("How can you make a bulleted list with numbers ?",    "<dl>",     "<ol>",     "<list>",     "<ul>",    2,"Hard");
        addQuestion(q93);
        Question q94 = new Question("The class which is inherited by parent class is termed as ?",    "Base class",     "Derived class",     "Member of class",     "public member of class",    2,"Easy");
        addQuestion(q94);
        Question q95 = new Question("The operator that cannot be overloaded is ?",    "++",     "~",     "()",     "::",    1,"Medium");
        addQuestion(q95);
        Question q96 = new Question("In C++, dynamic memory allocation is accomplished with the operator ?",    "new",     "this",     "malloc()",     "calloc()",    1,"Hard");
        addQuestion(q96);
        Question q97 = new Question("A constructor is called whenever ?",    "An object is used",     "An object is declared",     "A class is declared",     "A class is used",    2,"Easy");
        addQuestion(q97);
        Question q98 = new Question("Status register can be called as ?",    "Stack",     "Accumulator",     "Flags",     "Counter",    3,"Medium");
        addQuestion(q98);
        Question q99 = new Question("Which of the following creates platform independent code from a source file ?",    "Compiler",     "GUI",     "Byte code",     "JVM",    4,"Hard");
        addQuestion(q99);
        Question q100 = new Question("How many interrupt pins 8086 has got ?",    "0",     "4",     "3",     "2",    4,"Easy");
        addQuestion(q100);

    }

    private void addQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionsTable.COLUMN_OPTION4,question.getOption4());
        cv.put(QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        cv.put(QuestionsTable.COLUMN_DIFFICULTY, question.getDifficulty());
        db.insert(QuestionsTable.TABLE_NAME, null, cv);
    }



    public ArrayList<Question> getQuestions(String difficulty,int size,boolean random) {


        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
      //  String[] selectionArgs=new String[]{difficulty};
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME+ " WHERE " +QuestionsTable.COLUMN_DIFFICULTY+"= ?", null);

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION4)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                question.setDifficulty(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_DIFFICULTY)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        if (random){
            Collections.shuffle(questionList);
        }
        if(size> 0 && size<questionList.size()){
            Collection<Question>collection=questionList.subList(0,size);
            ArrayList<Question> sublist=new ArrayList<>();
            sublist.addAll(collection);
            return sublist;

        }

        return questionList;
    }


}


package com.at.hash;

import java.util.Scanner;

public class HashTabDemo {
    public static void main(String[] args) {
        HashTab hashTab = new HashTab(7);
        String key;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("add: 添加雇员");
            System.out.println("find: 查找雇员");
            System.out.println("list: 显示雇员");
            System.out.println("exit: 退出系统");
            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("输入名字");
                    String name = scanner.next();
                    Employer employer = new Employer(name);
                    hashTab.add(employer);
                    break;
                case "find":
                    System.out.println("输入雇员id");
                    int id = Integer.parseInt(scanner.next());
                    hashTab.findEmpById(id);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "exit":
                    System.exit(0);
            }
        }
    }
}


/**
 * 哈希表
 */
class HashTab {
    private EmpLinkedList[] empLinkedList;
    private int size;

    HashTab(int size) {
        this.size = size;
        empLinkedList = new EmpLinkedList[this.size];
        //初始化
        for (int i = 0; i < this.size; i++) {
            empLinkedList[i] = new EmpLinkedList();
        }
    }

    /**
     * 添加雇员
     *
     * @param employer 雇员信息
     */
    public void add(Employer employer) {
        int hash = hash(employer.id);
        empLinkedList[hash].add(employer);
    }


    /**
     * 散列函数
     *
     * @param id 雇员id
     * @return 散列值
     */
    private int hash(int id) {
        return id % size;
    }

    /**
     * 显示雇员
     */
    public void list() {
        for (int i = 0; i < size; i++) {
            empLinkedList[i].list(i);
        }
    }

    /**
     * 根据id查找雇员
     *
     * @param id 雇员id
     */
    public void findEmpById(int id) {
        int hash = hash(id);
        Employer emp = empLinkedList[hash].findEmpById(id);
        if (emp != null) {
            System.out.printf("在第%d条链表中找到 雇员 id=%d name=%s\n", hash, emp.id
                    , emp.name);
        } else {
            System.out.println("在哈希表中没有找到雇员");
        }
    }

    public void delEmpById(int id) {

        int hash = hash(id);
        boolean result = empLinkedList[hash].delEmpById(id);
        if (result) {
            System.out.println("ID为" + id + "的雇员已删除");
        } else {
            System.out.println("删除失败，不存在该");
        }
    }

}

/**
 * 雇员类
 */
class Employer {
    private static int count = 0;
    public int id;
    public String name;
    public Employer next;

    Employer(String name) {
        this.id = count++;
        this.name = name;
    }

    Employer(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

/**
 * 雇员链表
 */
class EmpLinkedList {
    private Employer head = new Employer(-1, "");

    /**
     * 添加雇员到链表
     * id自增长
     *
     * @param employer 雇员信息
     */
    public void add(Employer employer) {
        Employer cur = head;
        while (cur.next != null) {
            cur = cur.next;
        }
        cur.next = employer;
    }

    /**
     * 显示雇员
     */
    public void list(int index) {
        if (head.next == null) {
            System.out.println("第" + index + "条链表为空");
            return;
        }
        Employer cur = head;
        while (cur.next != null) {
            System.out.printf("第%d条链表=> id=%d name=%s\n", index, cur.next.id, cur.next.name);
            cur = cur.next;
        }
    }

    public Employer findEmpById(int id) {
        if (head.next == null) {
            System.out.println("链表为空");
            return null;
        }
        Employer cur = head;
        while (cur.next != null) {
            if (cur.next.id == id) {
                return cur.next;
            }
            cur = cur.next;
        }
        return null;
    }

    /**
     * 根据id删除雇员
     *
     * @param id 雇员id
     * @return 删除结果
     */
    public boolean delEmpById(int id) {
        if (head.next == null) {
            System.out.println("链表为空");
            return false;
        }
        Employer cur = head;
        Employer temp;
        while (cur.next != null) {
            if (cur.next.id == id) {
                cur.next = cur.next.next;
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

}
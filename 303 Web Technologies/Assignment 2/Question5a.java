import java.util.*;
//11 milliseconds

class Question5a {
    public static void intersection(int[] a1, int[] a2) {
        int n1 = a1.length;
        int n2 = a2.length;
        int flag = 1;
        int[] ans = new int[Math.min(n1, n2)];
        int i1 = 0, i2 = 0, index = 0;
        while (i1 < n1 && i2 < n2) {
            if (a1[i1] == a2[i2]) {
                ans[index] = a1[i1];
                index++;
                i1++;
                i2++;
                flag = 0;
            } else if (a1[i1] < a2[i2]) {
                i1++;
            } else i2++;
        }
        System.out.print("Intersection is: ");
        if (flag == 1)
            System.out.print("Phi (empty set)");
        else
            for (int i = 0; i < index; i++)
                System.out.print(ans[i] + " ");
        System.out.println();
    }

    public static void union(int[] a1, int[] a2) {
        int n1 = a1.length;
        int n2 = a2.length;
        int[] ans = new int[n1 + n2];
        int i1 = 0, i2 = 0, index = 0;
        while (i1 < n1 && i2 < n2) {
            if (a1[i1] == a2[i2]) {
                ans[index++] = a1[i1++];
                i2++;
            } else if (a1[i1] < a2[i2])
                ans[index++] = a1[i1++];
            else
                ans[index++] = a2[i2++];
        }

        while (i2 < n2) ans[index++] = a2[i2++];

        while (i1 < n1) ans[index++] = a1[i1++];

        System.out.print("Union is: ");
        if(index == 0)
            System.out.print("Phi (empty set)");
        else{
            for (int i = 0; i < index; i++)
                System.out.print(ans[i] + " ");
        }
        System.out.println();
    }

    public static void complement(int[] a1, int[] universal) {
        int n1 = a1.length, index = 0;
        int[] ans = new int[11];
        int i = 0, j = 0, flag = 1;

        while (index < 11 && i < n1) {
            if (a1[i] == universal[index]) {
                i++;
                index++;
            } else {
                ans[j++] = universal[index++];
                flag = 0;
            }
        }
        while (index < 11) {
            ans[j++] = universal[index++];
            flag = 0;
        }

        System.out.print("Complement is: ");
        if (flag == 1)
            System.out.print("Phi (empty set)");
        else
            for (i = 0; i < j; i++)
                System.out.print(ans[i] + " ");

        System.out.println();
    }

    public static int[] makeSet(int[] a1) {
        int n = a1.length;
        int ref[] = new int[11];
        for (int i = 0; i < 11; i++)
            ref[i] = 0;
        for (int i = 0; i < n; i++)
            ref[a1[i]]++;
        int index = 0;

        for (int i = 0; i < 11; i++) {
            if (ref[i] != 0) {
                a1[index] = i;
                index++;
            }
        }
        int[] ans = new int[index];
        for (int i = 0; i < index; i++)
            ans[i] = a1[i];
        return ans;
        }

    public static void main(String[] args)
    {
        Scanner cin = new Scanner(System.in);

        System.out.println("No. of elements in first set:");
        int n1 = cin.nextInt();
        System.out.println("No. of elements in second set:");
        int n2 = cin.nextInt();

        int count = 0;
        int universal[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] arr1 = new int[n1];
        int[] arr2 = new int[n2];

        System.out.println("Elements of set 1:");
        for (int i = 0; i < n1; i++) {
            arr1[i] = cin.nextInt();
            while (arr1[i] < 0 || arr1[i] > 10) {
                System.out.println("Enter a number between 0 and 10");
                arr1[i] = cin.nextInt();
            }
        }

        System.out.println("Elements of set 2:");
        for (int i = 0; i < n2; i++) {
            arr2[i] = cin.nextInt();
            while (arr2[i] < 0 || arr2[i] > 10) {
                System.out.println("Enter a number between 0 and 10");
                arr2[i] = cin.nextInt();
            }
        }

        long start = System.currentTimeMillis();

        arr1 = makeSet(arr1);
        arr2 = makeSet(arr2);

        union(arr1, arr2);
        intersection(arr1, arr2);
        complement(arr1, universal);
        complement(arr2, universal);

        long end = System.currentTimeMillis();
        System.out.println("\nTime taken: " + (end - start) + " milliseconds");
    }
}
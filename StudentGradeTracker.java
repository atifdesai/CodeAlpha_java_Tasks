import java.util.ArrayList;
import java.util.Scanner;

public class StudentGradeTracker 
{

    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Double> grades = new ArrayList<>();

        System.out.println("Welcome to the Student Grade Tracker!");

        while (true) 
        {
            System.out.print("Enter a student's grade (or -1 to finish): ");
            double grade = scanner.nextDouble();

            if (grade == -1) 
            {
                break;
            }

            if (grade < 0 || grade > 100) 
            {
                System.out.println("Invalid grade! Please enter a grade between 0 and 100.");
                continue;
            }

            grades.add(grade);
        }


        if (grades.isEmpty()) 
        {
            System.out.println("No grades entered.");
        } 
        else 
        {
            System.out.println("\n--- Grade Statistics ---");
            System.out.printf("Average Grade: %.2f\n", calculateAverage(grades));
            System.out.printf("Highest Grade: %.2f\n", findHighestGrade(grades));
            System.out.printf("Lowest Grade: %.2f\n", findLowestGrade(grades));
        }

        scanner.close();
    }

    public static double calculateAverage(ArrayList<Double> grades)
    {
        double sum = 0;
        for (double grade : grades) 
        {
            sum += grade;
        }
        return sum / grades.size();
    }

    public static double findHighestGrade(ArrayList<Double> grades) 
    {
        double highest = grades.get(0);
        for (double grade : grades) 
        {
            if (grade > highest) 
            {
                highest = grade;
            }
        }
        return highest;
    }

    public static double findLowestGrade(ArrayList<Double> grades) 
    {
        double lowest = grades.get(0);
        for (double grade : grades) 
        {
            if (grade < lowest) 
            {
                lowest = grade;
            }
        }
        return lowest;
    }
}